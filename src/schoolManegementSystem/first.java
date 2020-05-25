package schoolManegementSystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

public class first {

	JFrame frame = new JFrame();
	public JButton btn[];
	private JPanel pane;
	private JLabel lb;
	public JTextField user, Email, Username, hintpass;
	public JPasswordField pass, newPass, Rpass;
	private String passUser[] = { "Username ", "Password", "Login", "Cancel", "Forget account", "Check The Email",
			"Generated" };
	public String get;
	private JLabel username[], hint, newUsername, newPassword, RPassword, Hintpassword;
	private JProgressBar bar;
	private Timer time;
	private int timer = 0;
	private JLabel down;
	Timer t;
	Thread ts;
	BufferedImage img = null;
	actionListener action = new actionListener();
	actionListener1 action1 = new actionListener1();
//

	public first() {
		frame();
		
		TextPass();
		button();
		Panel();
		label();

		
	}

	private void TextPass() {
		bar = new JProgressBar(0, 30);

		bar.setBounds(0, 325, 500, 10);
		bar.setVisible(false);

		down = new JLabel();
		down.setBounds(5, 300, 300, 15);
		down.setVisible(false);
		down.setForeground(Color.cyan);
		
		ts = new Thread() {
			public void run() {
		t = new Timer(500,new ActionListener() {
			int tss = 0;
			String [] cs = {" "," . "," . ."," . . ."};
			public void actionPerformed(ActionEvent arg0) {
				
				
				if(tss == cs.length)
					tss = 0;
				down.setText("System is starting"+cs[tss]);
				down.setVisible(true);
				tss++;
		}
		
	}); t.start();
	
			}};
		
			

		time = new Timer(100, action1);

		frame.add(down);

		frame.add(bar);

		user = new JTextField();
		user.setBounds(120, 105, 200, 30);
		user.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 181, 192)));
		user.setOpaque(false);
		user.setBackground(new Color(0, 27, 61, 160));
		user.setFont(new Font("ARIAL", Font.BOLD, 15));
		user.setForeground(new Color(0, 181, 192));

		frame.add(user);
		pass = new JPasswordField();
		pass.setBounds(120, 155, 200, 30);
		pass.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 181, 192)));
		pass.setOpaque(false);
		pass.setEchoChar('.');
		pass.setBackground(new Color(0, 27, 61, 160));
		pass.setFont(new Font("ARIAL", Font.BOLD, 30));
		pass.setForeground(new Color(0, 181, 192));
		frame.add(pass);

		Email = new JTextField();
		Email.setBounds(100, 130, 300, 30);
		Email.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 181, 192)));
		Email.setOpaque(false);
		Email.setVisible(false);
		Email.setBackground(new Color(0, 27, 61, 160));
		Email.setFont(new Font("ARIAL", Font.BOLD, 15));
		Email.setForeground(new Color(0, 181, 192));
		frame.add(Email);

		Username = new JTextField();
		Username.setBounds(180 - 20, 80 - 10, 230, 30);
		Username.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 181, 192)));
		Username.setOpaque(false);
		Username.setBackground(new Color(0, 27, 61, 160));
		Username.setFont(new Font("ARIAL", Font.BOLD, 15));
		Username.setForeground(new Color(0, 181, 192));
		Username.setVisible(false);

		frame.add(Username);

		hintpass = new JTextField();
		hintpass.setBounds(190, 220, 200, 30);
		hintpass.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 181, 192)));
		hintpass.setOpaque(false);
		hintpass.setBackground(new Color(0, 27, 61, 160));
		hintpass.setFont(new Font("ARIAL", Font.BOLD, 15));
		hintpass.setForeground(new Color(0, 181, 192));
		hintpass.setVisible(false);
		frame.add(hintpass);

		newPass = new JPasswordField();
		newPass.setBounds(180 - 20, 120, 230, 30);
		newPass.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 181, 192)));
		newPass.setOpaque(false);
		newPass.setEchoChar('.');
		newPass.setBackground(new Color(0, 27, 61, 160));
		newPass.setFont(new Font("ARIAL", Font.BOLD, 30));
		newPass.setForeground(new Color(0, 181, 192));
		newPass.setVisible(false);
		frame.add(newPass);

		Rpass = new JPasswordField();
		Rpass.setBounds(190, 170, 200, 30);
		Rpass.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 181, 192)));
		Rpass.setOpaque(false);
		Rpass.setEchoChar('.');
		Rpass.setBackground(new Color(0, 27, 61, 160));
		Rpass.setFont(new Font("ARIAL", Font.BOLD, 30));
		Rpass.setForeground(new Color(0, 181, 192));
		Rpass.setVisible(false);
		frame.add(Rpass);

		username = new JLabel[3];
		int BLocation[] = { 120, 170, 140 };
		for (int i = 0; i < username.length; i++) {
			username[i] = new JLabel();
			username[i].setText(passUser[i]);
			username[i].setBounds(30, BLocation[i], 95, 15);
			username[i].setFont(new Font("ARIAL", Font.BOLD, 15));
			username[i].setForeground(new Color(0, 181, 192));
			if (i == 2) {
				username[i].setText("Email :");
				username[i].setVisible(false);
			}

			frame.add(username[i]);
		}
		hint = new JLabel();
		hint.setBounds(80, 230, 200, 25);
		hint.setFont(new Font("ARIAL", Font.PLAIN, 15));
		hint.setForeground(Color.cyan);
		hint.setVisible(false);

		frame.add(hint);

		newUsername = new JLabel();
		newUsername.setBounds(80, 100 - 20, 200, 25);
		newUsername.setFont(new Font("ARIAL", Font.PLAIN, 15));
		newUsername.setForeground(Color.cyan);
		newUsername.setText("Username:");
		newUsername.setVisible(false);

		frame.add(newUsername);
		newPassword = new JLabel();
		newPassword.setBounds(80, 180 - 30 - 20, 200, 25);
		newPassword.setFont(new Font("ARIAL", Font.PLAIN, 15));
		newPassword.setForeground(Color.cyan);
		newPassword.setText("Password:");
		newPassword.setVisible(false);

		frame.add(newPassword);
		RPassword = new JLabel();
		RPassword.setBounds(80, 230 - 30 - 20, 200, 25);
		RPassword.setFont(new Font("ARIAL", Font.PLAIN, 15));
		RPassword.setForeground(Color.cyan);
		RPassword.setText("Re-Password:");
		RPassword.setVisible(false);

		frame.add(RPassword);
		Hintpassword = new JLabel();
		Hintpassword.setBounds(80, 230, 200, 25);
		Hintpassword.setFont(new Font("ARIAL", Font.PLAIN, 15));
		Hintpassword.setForeground(Color.cyan);
		Hintpassword.setText("Hint Password:");
		Hintpassword.setVisible(false);

		frame.add(Hintpassword);

	}

	private void label() {
		BufferedImage img = null;
		try {
			img = ImageIO.read(getClass().getResource("/image/background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		lb = new JLabel(new ImageIcon(img));
		lb.setSize(500, 335);
		frame.add(lb);

	}

	private void Panel() {

		pane = new JPanel();
		pane.setSize(500, 335);
		pane.setBackground(new Color(0, 27, 61, 160));

//		pane1 = new JPanel();
//		pane1.setBounds(5,90,350,150);
//		pane1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(48,106,126)));
//		pane1.setOpaque(false);
//		frame.add(pane1);
		frame.add(pane);
	}

	private void button() {

		int LLocation[] = { 240, 150, 110, 110, 110 };
		int BLocation[] = { 200, 200, 260, 260, 260 };
		btn = new JButton[5];
		for (int i = 0; i < btn.length; i++) {
			btn[i] = new JButton();
			btn[i].setBounds(LLocation[i], BLocation[i], 63, 20);
			btn[i].setOpaque(false);
			btn[i].setBackground(new Color(0, 27, 61, 160));
			if (i == 0) {

				btn[i].setBounds(LLocation[i], 200, 80, 32);
				btn[i].setText(passUser[i + 2]);
				btn[i].setBorder(BorderFactory.createSoftBevelBorder(10));
				btn[i].setFont(new Font("ARIAL", Font.BOLD, 15));
				btn[i].setForeground(Color.cyan);

				btn[i].addActionListener(action);

			}
			if (i == 1) {

				btn[i].setBounds(LLocation[i], 200, 80, 32);
				btn[i].setText(passUser[i + 2]);
				btn[i].setBorder(BorderFactory.createSoftBevelBorder(10));
				btn[i].setFont(new Font("ARIAL", Font.BOLD, 15));
				btn[i].setForeground(Color.yellow);

				btn[i].addActionListener(action);

			}
			if (i == 2) {

				btn[i].setBounds(LLocation[i], BLocation[i], 250, 32);
				btn[i].setText(passUser[i + 2]);
				btn[i].setBorder(BorderFactory.createSoftBevelBorder(10));
				btn[i].setFont(new Font("ARIAL", Font.BOLD, 15));
				btn[i].setForeground(Color.cyan);
				btn[i].setVisible(false);

				btn[i].addActionListener(action);

			}
			if (i == 3) {

				btn[i].setBounds(LLocation[i], BLocation[i], 250, 32);
				btn[i].setText(passUser[i + 2]);
				btn[i].setBorder(BorderFactory.createSoftBevelBorder(10));
				btn[i].setFont(new Font("ARIAL", Font.BOLD, 15));
				btn[i].setForeground(Color.cyan);
				btn[i].setVisible(false);

				btn[i].addActionListener(action);

			}
			if (i == 4) {

				btn[i].setBounds(LLocation[i], BLocation[i], 250, 32);
				btn[i].setText(passUser[i + 2]);
				btn[i].setBorder(BorderFactory.createSoftBevelBorder(10));
				btn[i].setFont(new Font("ARIAL", Font.BOLD, 15));
				btn[i].setForeground(Color.cyan);
				btn[i].setVisible(false);

				btn[i].addActionListener(action);

			}
			frame.add(btn[i]);
		}

	}

	private void frame() {
		BufferedImage image = null;
		try {

			image = ImageIO.read(getClass().getResource("/image/UJUHU-01.png"));

		} catch (Exception e) {
			System.out.println(e);
		}
		ImageIcon image1 = new ImageIcon(image);
		frame.setSize(500, 335);

		frame.setLayout(null);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setIconImage(image1.getImage());

	}

	private class actionListener implements ActionListener {

		public void actionPerformed(ActionEvent a) {
			Databases database1 = new Databases();
			try {
				database1.classForname();
				database1.con = DriverManager.getConnection("jdbc:sqlite:login.db");
				database1.st = database1.con.createStatement();
			} catch (Exception e) {
				System.out.println(e);
			}
			if (a.getSource() == btn[1]) {

				System.exit(0);
			} else if (a.getSource() == btn[0]) {
				if (user.getText().length() > 0 && pass.getText().length() > 0) {

					try {

						String query = "select Username, PasswordKey ,HintPassword from loging";
						ResultSet rs = database1.st.executeQuery(query);
						rs.next();
						String uname = rs.getString("Username");
						String password = rs.getString("PasswordKey");
						if (user.getText().equals(uname) && pass.getText().equals(password)) {
							bar.setVisible(true);
							ts.start();	
							time.start();

						} else if (user.getText().equals(uname)) {
							JOptionPane.showMessageDialog(null, "your password is incorrect");
							pass.setForeground(Color.red);
							btn[2].setVisible(true);
							hint.setVisible(true);
							String hintpassword = rs.getString("HintPassword");
							hint.setText("Hint password: " + hintpassword);

						} else {
							pass.setForeground(Color.red);
							user.setForeground(Color.red);
							JOptionPane.showMessageDialog(null, "your username and password are incorrect\ntry again");
							btn[2].setVisible(true);

							user.setText("");
							pass.setText("");
						}
						database1.st.close();
						database1.con.close();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "there an Error please check First");
					}
				} else if (user.getText().length() > 0) {

					JOptionPane.showMessageDialog(null, "please enter your password");
				} else {

					JOptionPane.showMessageDialog(null, "please enter your username and password");
				}
//				}finally {
//					try {
//						
//						database1.st.close();
//						database1.con.close();
//						
//					}catch(Exception e) {
//						System.out.println(e);
//					}
//				}

			} else if (a.getSource() == btn[2]) {

				user.setVisible(false);
				pass.setVisible(false);
				username[0].setVisible(false);
				;
				username[1].setVisible(false);
				user.setVisible(false);
				btn[0].setVisible(false);
				btn[1].setVisible(false);
				Email.setVisible(true);
				username[2].setVisible(true);
				btn[2].setVisible(false);
				btn[3].setVisible(true);
				btn[3].setEnabled(false);
				Email.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						btn[3].setEnabled(true);
					}

				});

			} else if (a.getSource() == btn[3]) {

				try {
//					database2.classForname();
//					database2.con = DriverManager.getConnection("jdbc:sqlite:login.db");
//					database2.st = database2.con.createStatement();
					String query = "select EmailOrPhone from loging";

					ResultSet rs = database1.st.executeQuery(query);
					rs.next();
					String geter = rs.getString("EmailOrPhone");
					String checker2 = new String();
					checker2 = geter;
					if (Email.getText().equals(checker2)) {
						Random code = new Random();
						int insert = code.nextInt(10_000);
						String ran = Integer.toString(insert);
						PreparedStatement pr = database1.con
								.prepareStatement("update loging set classrooms='" + insert + "'");
						int i = pr.executeUpdate();

						ResultSet rrs = database1.st.executeQuery("select classrooms from loging");
						rrs.next();
						String getter = rrs.getString("classrooms");
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
							mm.setRecipients(Message.RecipientType.TO, InternetAddress.parse(geter));
							mm.setSubject("schmasys confirmation code");
							mm.setText("the confirmation code " + ran + " this code is one time usage");

							Transport.send(mm);

						} catch (Exception e) {
							JOptionPane.showMessageDialog(null,
									"There is no internet connection\nPlease connect to the internert",
									"internet warring", JOptionPane.WARNING_MESSAGE);
						}
						String op = JOptionPane.showInputDialog("Please Enter code have sent to your Email");
						if (op.equals(getter)) {
							username[2].setVisible(false);
							Email.setVisible(false);
							newUsername.setVisible(true);
							newPassword.setVisible(true);
							RPassword.setVisible(true);
							btn[4].setVisible(true);
							btn[4].setEnabled(false);
							btn[3].setVisible(false);
							Hintpassword.setVisible(true);
							Username.setVisible(true);
							hintpass.setVisible(true);
							newPass.setVisible(true);
							newPass.addMouseListener(new MouseAdapter() {
								public void mouseClicked(MouseEvent e) {
									btn[4].setEnabled(true);
								}
							});
							Rpass.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(null, "code is not correct");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Your Email is incorrect");
					}
					database1.st.close();
					database1.con.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "This is not recorded email please check again");
				}
//				finally {
//					try {
//					
//						database1.st.close();
//							database1.con.close();
//						
//					}catch(Exception e) {
//						System.out.println(e);
//					}
//				}

			} else if (a.getSource() == btn[4]) {

				try {

					String update = "update loging set Username='" + Username.getText() + "',PasswordKey ='"
							+ newPass.getText() + "',ReEnterPasswrd='" + Rpass.getText() + "',HintPassword='"
							+ hintpass.getText() + "';";
					PreparedStatement sst = database1.con.prepareStatement(update);

					sst.executeUpdate();
					JOptionPane.showMessageDialog(null,
							"your username and password hass been changed successfully\nUsername: " + Username.getText()
									+ "\nPassword: " + newPass.getText() + "\n  Run this again thank you");
					System.exit(0);
					sst.close();

					database1.st.close();
					database1.con.close();
				} catch (Exception e) {

					System.out.println(e);
				}

			}

		}
	}

	private class actionListener1 implements ActionListener {
		DesktopApp obj = null;

		public void actionPerformed(ActionEvent arg0) {

			if (timer == 5) {
//		Thread ts = new Thread() {
//			public void run() {
				obj = new DesktopApp();
//			}};ts.start();

			}
			if(timer >= 10 && timer <=15 ) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(timer >= 16 && timer <=25 ) {
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(timer >= 17 && timer <=29 ) {
				try {
					Thread.sleep(700);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(timer ==30 ) {
				obj.setVisible(true);
				obj.create.setVisible(false);
				
						frame.setVisible(false);
						
						user.setText("");
						pass.setText("");
						t.stop();
						time.stop();
			}

			timer++;
			bar.setValue(timer);

		}

	}

}
