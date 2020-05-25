package schoolManegementSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Properties;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class newAccount extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel container;
	private JLabel header;
	private JLabel logo;
	private JButton browse;
	private JLabel lb, lb1;
	private JTextField sInfo[];
	private JSeparator sem, sem1, sem2;
	private JCheckBox ck, fee, buy;
	private JTextField text;
	private String str[] = { "  School Name", "  Head Teacher Full Name", "  Secretary Full Name",
			"  Enter your gmail account" };
	private String pas[] = { "  Enter password", "  Confirm your password" };
	private JButton btn;
	private JTextField username;
	private JPasswordField pass[];
	private JToggleButton pT, rT;
	private int no;
	private String OTP, s;
	private ButtonGroup gb = new ButtonGroup();

	focusListener focus = new focusListener();

	public newAccount() {
		pane();
		containers();
		logo();
		SchoolInformations();
		security();
		payment();
	}

	private void payment() {

		JLabel lb = new JLabel("Payment agreement");
		lb.setBounds(40, 340, 200, 35);
		lb.setFont(new Font("Roman", Font.PLAIN, 16));
		lb.setForeground(Color.gray);
		container.add(lb);

		JSeparator sem1 = new JSeparator();
		sem1.setBounds(40, 380, 150, 1);
		sem1.setForeground(Color.LIGHT_GRAY);
		container.add(sem1);

		fee = new JCheckBox("  Monthly subscibtion fee");
		fee.setBounds(40, 400, 350, 35);
		fee.setFont(new Font("Roman", Font.BOLD, 16));
		fee.setOpaque(false);
		fee.isMaximumSizeSet();
		fee.setBorder(BorderFactory.createSoftBevelBorder(10));
		fee.setForeground(Color.gray);
		fee.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {
				no = 1;
			}

		});
		container.add(fee);

		JLabel logoLabel = new JLabel(
				"<html>To use the system check monthly subscribtion fee.<br>&nbsp;&nbsp;&nbsp;<b>Note</b> This part allows you to use the system by<br> &nbsp;&nbsp;&nbsp; paying few subscribtion fee per month. </html>");
		logoLabel.setBounds(55, 385, 350, 170);
		logoLabel.setForeground(Color.gray);
		logoLabel.setFont(new Font("Roman", Font.PLAIN, 15));
		gb.add(fee);
		container.add(logoLabel);

		buy = new JCheckBox("   Get professional code");
		buy.setBounds(40, 520, 350, 35);
		buy.setFont(new Font("Roman", Font.BOLD, 16));
		buy.setOpaque(false);
		buy.setBorder(BorderFactory.createSoftBevelBorder(10));
		buy.setForeground(Color.gray);
		buy.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {
				no =2;
			}

		});
		gb.add(buy);
		container.add(buy);

		JLabel logoLabel1 = new JLabel(
				"<html>check to get professional code this menu allows to.<br>&nbsp;&nbsp;&nbsp; to use the system as a professional by<br> &nbsp;&nbsp;&nbsp; paying fixed price with needing to providing monthly fee.</html>");
		logoLabel1.setBounds(55, 500, 400, 170);
		logoLabel1.setForeground(Color.gray);
		logoLabel1.setFont(new Font("Roman", Font.PLAIN, 15));
		container.add(logoLabel1);
	}

	private void security() {

		btn = new JButton("SUBMIT");
		btn.setBounds(590, 610, 300, 40);
		btn.setBorder(BorderFactory.createSoftBevelBorder(10));
		btn.setBackground(new Color(0, 167, 215));
		btn.setFont(new Font("Roman", Font.PLAIN, 25));
		btn.setForeground(Color.white);
		btn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Databases db = new Databases();
				int pg = 0;

				if (no != 0) {
					pg = 1;
					if(no == 1) {
						try {
							db.classForname();
							db.con = DriverManager.getConnection("jdbc:sqlite:login.db");
							db.st = db.con.createStatement();
							LocalDate now = LocalDate.now();
							db.st.executeUpdate("update loging set deanname='"+now+"' , leadername='"+now.plusMonths(1)+"'");
						}catch(Exception e) {
							e.printStackTrace();
						}finally {
							try {
								db.con.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}else {

						try {
							db.classForname();
							db.con = DriverManager.getConnection("jdbc:sqlite:login.db");
							db.st = db.con.createStatement();
							LocalDate now = LocalDate.now();
							db.st.executeUpdate("update loging set leadername='"+now+"'");
						}catch(Exception e) {
							e.printStackTrace();
						}finally {
							try {
								db.con.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "Please select one of the two payment agreements",
							"schmasys warring", JOptionPane.WARNING_MESSAGE);

				}
				if (OTP == null)
					JOptionPane.showMessageDialog(null, "Please select get verification code", "schmasys warring",
							JOptionPane.WARNING_MESSAGE);

				if (!((sInfo[0].getText().equals(str[0]) || sInfo[0].getText().equals(""))
						| (sInfo[1].getText().equals(str[1]) || sInfo[1].getText().equals(""))
						| (sInfo[2].getText().equals(str[2]) || sInfo[2].getText().equals(""))
						| (sInfo[3].getText().equals(str[3]) || sInfo[3].getText().equals(""))
						| (pg == 0)
						| (username.getText().equals("  Enter username") || username.getText().equals(""))
						| (pass[0].getText().equals("  Enter password") || pass[0].getText().equals(""))
						| (pass[1].getText().equals("  Confirm your password") || pass[1].getText().equals("") )
						
					))

				{

					if (text.getText().equals(OTP)) {

						try {
							db.classForname();
							db.con = DriverManager.getConnection("jdbc:sqlite:login.db");
							db.st = db.con.createStatement();
							String ss = s;
							if (!(ss == null)) {

								FileInputStream fis = new FileInputStream(ss);
								ByteArrayOutputStream bos = new ByteArrayOutputStream();
								byte[] buf = new byte[1024];
								for (int readnum; (readnum = fis.read(buf)) != -1;) {
									bos.write(buf, 0, readnum);
								}
								fis.close();
								String update = "update loging set schoolName='" + sInfo[0].getText()
										+ "',HeadteacherName='" + sInfo[1].getText() + "',secretaryName='"
										+ sInfo[2].getText() + "',EmailOrPhone='" + sInfo[3].getText() + "',Username='"
										+ username.getText() + "',PasswordKey='" + pass[0].getText()
										+ "',ReEnterPasswrd='" + pass[0].getText() + "',logo=?, classrooms ='" + no
										+ "'";

								PreparedStatement pr = db.con.prepareStatement(update);
								pr.setBytes(1, bos.toByteArray());
								pr.executeUpdate();
								System.exit(0);
							} else {
								String update = "update loging set schoolName='" + sInfo[0].getText()
										+ "',HeadteacherName='" + sInfo[1].getText() + "',secretaryName='"
										+ sInfo[2].getText() + "',EmailOrPhone='" + sInfo[3].getText() + "',Username='"
										+ username.getText() + "',PasswordKey='" + pass[0].getText()
										+ "',ReEnterPasswrd='" + pass[0].getText() + "', classrooms ='" + no + "'";
								db.st.executeUpdate(update);
								System.exit(0);
							}

						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							try {
								db.con.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}

					} else {
						ck.setSelected(false);
						text.setEditable(false);
						text.setText("  Enter-Code");
					}

				} else {

				}

			}

		});
		container.add(btn);

		username = new JTextField("  Enter username");
		username.setBounds(490, 465, 505, 35);
		username.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		username.setFont(new Font("Roman", Font.PLAIN, 17));
		username.setForeground(Color.gray);
		username.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent arg0) {

				if (arg0.getSource() == username) {
					if (username.getText().equals("  Enter username")) {

						username.setText("");
						username.setFont(new Font("Calibri", Font.PLAIN, 16));
						username.setForeground(Color.black);

					}
				}

			}

			public void focusLost(FocusEvent arg0) {

				if (arg0.getSource() == username) {
					if (username.getText().equals("  Enter username") || username.getText().length() == 0) {

						username.setText("  Enter username");
						username.setForeground(Color.gray);
						username.setFont(new Font("ARIAL", Font.PLAIN, 14));

					}
				}

			}
		});
		container.add(username);

		int down = 510;

		pass = new JPasswordField[2];
		for (int i = 0; i < pass.length; i++) {
			pass[i] = new JPasswordField(pas[i]);
			pass[i].setBounds(490, down, 480, 35);
			pass[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.LIGHT_GRAY));
			pass[i].setFont(new Font("Roman", Font.PLAIN, 17));
			pass[i].setForeground(Color.gray);
			pass[i].addFocusListener(focus);
			pass[i].setEchoChar((char) 0);
			if(i == 1)
			pass[1].addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent e) {
					if (pass[1].getText().equals(pass[0].getText())) {
						pass[1].setForeground(Color.gray);
					} else {
						pass[1].setForeground(Color.red);
					}
				}
			});
			pass[i].addFocusListener(new FocusListener() {

				public void focusGained(FocusEvent arg0) {
					for (int i = 0; i < pass.length; i++) {
						if (arg0.getSource() == pass[i]) {
							if (pass[i].getText().equals(pas[i])) {

								pass[i].setText("");
								pass[i].setEchoChar('.');
								pass[i].setFont(new Font("Calibri", Font.PLAIN, 30));
								pass[i].setForeground(Color.black);

							}
						}
					}
				}

				public void focusLost(FocusEvent arg0) {
					for (int i = 0; i < pass.length; i++) {
						if (arg0.getSource() == pass[i]) {
							if (pass[i].getText().equals(pas[i]) || pass[i].getText().length() == 0) {
								pass[i].setEchoChar((char) 0);
								pass[i].setText(pas[i]);
								pass[i].setForeground(Color.gray);
								pass[i].setFont(new Font("ARIAL", Font.PLAIN, 14));

							}
						}
					}
				}
			});
			container.add(pass[i]);

			pT = new JToggleButton();
			pT.setBounds(970, 510, 25, 35);
			BufferedImage tog2 = null;
			try {
				tog2 = ImageIO.read(getClass().getResource("/image/hide.png"));

			} catch (Exception e) {

			}
			pT.setIcon(new ImageIcon(
					new ImageIcon(tog2).getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));

			pT.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.LIGHT_GRAY));
			pT.setBackground(Color.white);
			pT.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent itemEvent) {
					int state = itemEvent.getStateChange();

					if (state == ItemEvent.SELECTED) {
						BufferedImage tog = null;
						try {
							tog = ImageIO.read(getClass().getResource("/image/hide.png"));

						} catch (Exception e) {

						}

						pT.setIcon(new ImageIcon(
								new ImageIcon(tog).getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));
						pass[0].setFont(new Font("ARIAL", Font.PLAIN, 14));
						pass[0].setEchoChar((char) 0);

					} else {
						BufferedImage tog = null;
						try {
							tog = ImageIO.read(getClass().getResource("/image/view.png"));

						} catch (Exception e) {

						}

						pT.setIcon(new ImageIcon(
								new ImageIcon(tog).getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));
						pass[0].setFont(new Font("Roman", Font.BOLD, 25));
						pass[0].setEchoChar('.');
					}
				}
			});

			container.add(pT);

			rT = new JToggleButton();
			rT.setBounds(970, 555, 25, 35);
			BufferedImage tog1 = null;
			try {
				tog1 = ImageIO.read(getClass().getResource("/image/hide.png"));

			} catch (Exception e) {

			}
			rT.setIcon(new ImageIcon(
					new ImageIcon(tog1).getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));

			rT.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.LIGHT_GRAY));
			rT.setBackground(Color.white);
			rT.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent itemEvent) {
					int state = itemEvent.getStateChange();

					if (state == 1) {
						BufferedImage tog = null;
						try {
							tog = ImageIO.read(getClass().getResource("/image/hide.png"));

						} catch (Exception e) {

						}

						rT.setIcon(new ImageIcon(
								new ImageIcon(tog).getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));
						pass[1].setFont(new Font("ARIAL", Font.PLAIN, 14));
						pass[1].setEchoChar((char) 0);

					} else {
						BufferedImage tog = null;
						try {
							tog = ImageIO.read(getClass().getResource("/image/view.png"));

						} catch (Exception e) {

						}
						rT.setIcon(new ImageIcon(
								new ImageIcon(tog).getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));

						pass[1].setFont(new Font("Roman", Font.BOLD, 25));
						pass[1].setEchoChar('.');
					}
				}
			});

			container.add(rT);

			down += 45;
		}

	}

	private void SchoolInformations() {
		lb = new JLabel("School informations");
		lb.setBounds(490, 70, 200, 35);
		lb.setFont(new Font("Roman", Font.PLAIN, 16));
		lb.setForeground(Color.gray);
		container.add(lb);

		sem1 = new JSeparator();
		sem1.setBounds(490, 110, 150, 1);
		sem1.setForeground(Color.LIGHT_GRAY);
		container.add(sem1);

		int down = 130;
		sInfo = new JTextField[4];
		for (int i = 0; i < sInfo.length; i++) {
			sInfo[i] = new JTextField(str[i]);
			sInfo[i].setBounds(490, down, 500, 35);
			sInfo[i].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
			sInfo[i].setFont(new Font("Roman", Font.PLAIN, 17));
			sInfo[i].setForeground(Color.gray);
			sInfo[i].addFocusListener(focus);
			container.add(sInfo[i]);
			down += 55;
		}

		ck = new JCheckBox("  Get verification code");
		ck.setBounds(490, down - 15, 350, 35);
		ck.setFont(new Font("Roman", Font.PLAIN, 16));
		ck.setOpaque(false);
		ck.setBorder(BorderFactory.createSoftBevelBorder(10));
		ck.setForeground(Color.gray);
		ck.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {

				if (ck.isSelected()) {
					text.setEditable(true);
					codeSent(sInfo[3].getText());

				} else
					text.setEditable(false);
			}

			private void codeSent(String mail) {

				if (!(mail == null || mail.equals("  Enter your gmail account"))) {
					try {

						Random code = new Random();
						int insert = code.nextInt(10_000);
						int nex = code.nextInt(10_000);
						String nn = "" + insert + "_" + nex;
						OTP = nn;
						String ran = nn;

						Properties props = new Properties();
						props.put("mail.smtp.host", "smtp.gmail.com");
						props.put("mail.smtp.soceketFactory.port", "465");
						props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
						props.put("mail.smtp.auth", "true");
						props.put("mail.smtp.port", "465");

						Session sesn = Session.getDefaultInstance(props, new javax.mail.Authenticator() {

							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication("schmasys@gmail.com", "SAARAQUENSCHMASYS");
							}

						});

						try {
							Message mm = new MimeMessage(sesn);
							mm.setFrom(new InternetAddress("schmasys@gmail.com"));
							mm.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
							mm.setSubject("schmasys confirmation code");
							mm.setText("the confirmation code " + ran + " this code is one time usage");

							Transport.send(mm);

						} catch (Exception e) {
							JOptionPane.showMessageDialog(null,
									"There is no internet connection\nPlease connect to the internert",
									"internet warring", JOptionPane.WARNING_MESSAGE);

						}

					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {

					JOptionPane.showMessageDialog(null, "Please Enter your gmail account to send verification code",
							"schmasys warring", JOptionPane.WARNING_MESSAGE);
					ck.setSelected(false);
					text.setEditable(false);
				}

			}

		});
		container.add(ck);

		text = new JTextField("  Enter-Code");
		text.setBounds(490, down + 20, 200, 36);
		text.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		text.setFont(new Font("Roman", Font.PLAIN, 16));
		text.setForeground(Color.gray);
		text.setEditable(false);
		text.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent arg0) {

				if (arg0.getSource() == text) {
					if (text.getText().equals("  Enter-Code")) {

						text.setText("");
						text.setFont(new Font("Calibri", Font.PLAIN, 16));
						text.setForeground(Color.black);

					}
				}

			}

			public void focusLost(FocusEvent arg0) {

				if (arg0.getSource() == text) {
					if (text.getText().equals("  Enter-Code") || text.getText().length() == 0) {

						text.setText("  Enter-Code");
						text.setForeground(Color.gray);
						text.setFont(new Font("ARIAL", Font.PLAIN, 14));

					}
				}

			}
		});
		container.add(text);

		lb1 = new JLabel("Security details");
		lb1.setBounds(490, down + 60, 200, 35);
		lb1.setFont(new Font("Roman", Font.PLAIN, 16));
		lb1.setForeground(Color.gray);
		container.add(lb1);

		sem2 = new JSeparator();
		sem2.setBounds(490, down + 100, 130, 1);
		sem2.setForeground(Color.LIGHT_GRAY);
		container.add(sem2);
	}

	private void logo() {
		BufferedImage log = null;
		try {
			log = ImageIO.read(getClass().getResource("/image/UJUHU-01.png"));

		} catch (Exception e) {

		}

		logo = new JLabel();
		logo.setBounds(20, 70, 200, 200);
		logo.setIcon(new ImageIcon(new ImageIcon(log).getImage().getScaledInstance(logo.getWidth(), logo.getHeight(),
				Image.SCALE_AREA_AVERAGING)));
		container.add(logo);

		browse = new JButton("Browse logo");
		browse.setBounds(45, 265, 150, 20);
		browse.setBackground(Color.gray);
		browse.setFont(new Font("Roman", Font.PLAIN, 15));
		browse.setForeground(Color.white);
		browse.setBorder(BorderFactory.createSoftBevelBorder(10));
		browse.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					JFileChooser ch = new JFileChooser();
					FileNameExtensionFilter fil = new FileNameExtensionFilter("Image File", "PNG", "JPG");
					ch.setFileFilter(fil);
					ch.showDialog(null, "add image");
					File file = ch.getSelectedFile();
					s = file.getAbsolutePath();
					logo.setIcon(new ImageIcon(new ImageIcon(s).getImage().getScaledInstance(logo.getWidth(),
							logo.getHeight(), Image.SCALE_AREA_AVERAGING)));
				} catch (Exception e) {

				}
			}

		});
		container.add(browse);

	}

	private void containers() {
		container = new JPanel();
		container.setBounds(0, 0, 1045, 690);
		container.setLayout(null);
		container.setBackground(Color.white);
		add(container);

		header = new JLabel("Create an account");
		header.setBounds(20, 10, 200, 35);
		header.setFont(new Font("Roman", Font.PLAIN, 17));
		header.setForeground(Color.gray);
		container.add(header);

		sem = new JSeparator();
		sem.setBounds(20, 45, 1000, 1);
		sem.setForeground(Color.LIGHT_GRAY);
		container.add(sem);
	}

	private void pane() {
		setBounds(280, 30, 1045, 690);
		setBackground(Color.white);
		setVisible(false);
		setLayout(null);

	}

	private class focusListener implements FocusListener {

		public void focusGained(FocusEvent arg0) {
			for (int i = 0; i < sInfo.length; i++) {
				if (arg0.getSource() == sInfo[i]) {
					if (sInfo[i].getText().equals(str[i])) {

						sInfo[i].setText("");
						sInfo[i].setFont(new Font("Calibri", Font.PLAIN, 16));
						sInfo[i].setForeground(Color.black);
						if (i == 5 || i == 6) {
							sInfo[i].setForeground(Color.white);
						}
					}
				}
			}
		}

		public void focusLost(FocusEvent arg0) {
			for (int i = 0; i < sInfo.length; i++) {
				if (arg0.getSource() == sInfo[i]) {
					if (sInfo[i].getText().equals(str[i]) || sInfo[i].getText().length() == 0) {

						sInfo[i].setText(str[i]);
						sInfo[i].setForeground(Color.gray);
						sInfo[i].setFont(new Font("ARIAL", Font.PLAIN, 14));

					}
				}
			}
		}
	}

}
