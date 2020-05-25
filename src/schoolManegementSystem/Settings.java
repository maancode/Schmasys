package schoolManegementSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import diu.swe.habib.JPanelSlider.JPanelSlider;
import net.proteanit.sql.DbUtils;

public class Settings extends JPanel {
	private static final long serialVersionUID = 1L;
	JPanel container;
	private JLabel icon;
	private JLabel titleName;
	private JButton menu[];
	private JLabel manag, secu, ex, sub, other;
	private JLabel mName, sName, eName, cName, oName;
	JPanel listPanel;
	private JLabel back;
	private JLabel Smenu[];
	JPanel pane;
	JPanel contants;
	private JLabel logo;
	private JTextField school[];
	private JLabel texts[];
	private JLabel icons[];
	private JButton edite[];
	private int no;
	private JButton submit;
	private JLabel logoLabel, schoolLabel, headLabel, subLabel;
	private JLabel secLogo, secname, secUser, Email, seclabel, secPH;
	private JPasswordField secPass;
	private JTextField paHolder;
	private JButton next, forget;
	private JPanel logPanel, log, chanPanel;
	private String secPassword;
	private JLabel lLabel;

//	security changer conponents
	private JLabel Sysname;
	private JTextField gmail, Username;
	private JPasswordField newPass, RePass;
	private JPanel holder;
	private JCheckBox ck;
	private JToggleButton pT, rT;
	private JLabel lb[], lbicons[], notes[], Code;
	private JButton submits;
	private String mails, checkPass;
	private String yesOrNo, userChek;

//	examination components

	private JPanel ExPanel;
	private JScrollPane spane;
	private JTable tb;
	private JButton save;
	private JLabel lbb;

//	curriculam settings

	private JPanel cuPanel;
	private JScrollPane spane1, spane2;
	private JTable tb1, tb2;
	private JButton save1, save2;
	private JTextField sec, pri;
	private String names;

//	others setting 

	private JPanel oPanel;
	private JLabel slides[];
	private JLabel tit;
	private JLabel lbs;
	private JButton bro;
	private int jj;
	private JToggleButton tog;
	private JLabel labels, about, abtext;

	actionListener action = new actionListener();
	mouseListener mouse = new mouseListener();

	public Settings() {
		design();
		container();
		titles();
		menus();
		ListHolder();
		contents();
	}

	private void contents() {
		contants = new JPanel();
		contants.setBounds(300, 50, 890, 640);
		contants.setLayout(null);
		contants.setBackground(Color.white);
		add(contants);
		managementPanel();
		securityPanel();
		examPanel();
		curreculamPanel();
		othersPanel();
	}

	private void othersPanel() {
		oPanel = new JPanel();
		oPanel.setBounds(0, 0, 890, 640);
		oPanel.setBackground(Color.white);
		oPanel.setLayout(null);
		contants.add(oPanel);

		tog = new JToggleButton();
		tog.setBounds(20, 435, 64, 40);
		tog.setBorder(BorderFactory.createSoftBevelBorder(10));

		labels = new JLabel();
		labels.setBounds(100, 440, 250, 35);
		labels.setForeground(Color.gray);
		labels.setFont(new Font("Roman", Font.BOLD, 17));
		oPanel.add(labels);

		about = new JLabel("about the system.");
		about.setBounds(20, 475, 250, 35);
		about.setForeground(Color.gray);
		about.setFont(new Font("Roman", Font.BOLD, 16));
		oPanel.add(about);

		String abouts = "<html>Name : Schmsys <br>Version : SM2020.1.0<br>(c) Copyright somtech contributors 2020.  developed by Abdirahman Ali Yusuf, and the Schmays logo designed by Salah Awil. \r\n" + 
				"This System created for schools to maintain student informations , you can save in this system big size of data almost (140TB  = 143360GB). \r\n" + 
				"The System uses local environment no out site access.</html>";
		abtext = new JLabel(abouts);
		abtext.setBounds(20, 500, 650, 120);
		abtext.setForeground(Color.gray);
		abtext.setFont(new Font("Roman", Font.PLAIN, 14));
		oPanel.add(abtext);

		tog.setBackground(Color.white);

		Databases db4 = new Databases();
		try {
			db4.classForname();
			db4.con = DriverManager.getConnection("jdbc:sqlite:class.db");
			db4.st = db4.con.createStatement();
			ResultSet rs = db4.st.executeQuery("select togOnOf,label from tog");
			rs.next();

			BufferedImage image = null;

			if (rs.getInt("togOnOf") == 0) {
				try {
					image = ImageIO.read(getClass().getResource("/image/toggOf.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				tog.setIcon(new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(tog.getWidth(),
						tog.getHeight(), Image.SCALE_AREA_AVERAGING)));
				labels.setText(rs.getString("label"));
			} else {
				try {
					image = ImageIO.read(getClass().getResource("/image/toggOn.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				tog.setIcon(new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(tog.getWidth(),
						tog.getHeight(), Image.SCALE_AREA_AVERAGING)));
				labels.setText(rs.getString("label"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				db4.con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		tog.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent itemEvent) {

				int state = itemEvent.getStateChange();

				if (state == ItemEvent.SELECTED) {
					BufferedImage tog1 = null;
					try {
						tog1 = ImageIO.read(getClass().getResource("/image/toggOn.png"));

					} catch (Exception e) {

					}

					tog.setIcon(new ImageIcon(new ImageIcon(tog1).getImage().getScaledInstance(tog.getWidth(),
							tog.getHeight(), Image.SCALE_AREA_AVERAGING)));

					Databases db4 = new Databases();
					try {
						db4.classForname();
						db4.con = DriverManager.getConnection("jdbc:sqlite:class.db");
						db4.st = db4.con.createStatement();

						db4.st.executeUpdate("update tog set togOnOf=1, label='Remaining days is visible'");

						labels.setText("Remaining days is visible");

					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							db4.con.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				} else {
					BufferedImage tog1 = null;
					try {
						tog1 = ImageIO.read(getClass().getResource("/image/toggOf.png"));

					} catch (Exception e) {

					}

					tog.setIcon(new ImageIcon(new ImageIcon(tog1).getImage().getScaledInstance(tog.getWidth(),
							tog.getHeight(), Image.SCALE_AREA_AVERAGING)));

					Databases db4 = new Databases();
					try {
						db4.classForname();
						db4.con = DriverManager.getConnection("jdbc:sqlite:class.db");
						db4.st = db4.con.createStatement();

						db4.st.executeUpdate("update tog set togOnOf=0, label='Remaining days is invisible'");

						labels.setText("Remaining days is invisible");
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							db4.con.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}

		});

		oPanel.add(tog);

		Databases db = new Databases();
		try {
			db.classForname();
			db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
			db.st = db.con.createStatement();

			String q = "select count(), slides from slideImages";
			ResultSet g = db.st.executeQuery(q);
			g.next();
			int dd = g.getInt("count()");
			jj = dd;
			int left = 20;
			if (dd != 0) {
				slides = new JLabel[dd];
				for (int i = 0; i < slides.length; i++) {
					slides[i] = new JLabel();
					slides[i].setBounds(left, 300, 70, 70);
					slides[i].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
					oPanel.add(slides[i]);
					left += 75;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				db.con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		tit = new JLabel("Preview");
		tit.setBounds(20, 30, 100, 35);
		tit.setForeground(Color.gray);
		tit.setFont(new Font("Roman", Font.BOLD, 17));
		oPanel.add(tit);

		lbs = new JLabel("choose your picture");
		lbs.setBounds(20, 250, 200, 35);
		lbs.setForeground(Color.gray);
		lbs.setFont(new Font("Roman", Font.BOLD, 17));
		oPanel.add(lbs);

		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResource("/image/browse.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		bro = new JButton();
		bro.setBounds(20, 385, 120, 35);
		bro.setIcon(new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(bro.getWidth(), bro.getHeight(),
				Image.SCALE_AREA_AVERAGING)));
		bro.setBorder(BorderFactory.createSoftBevelBorder(10));
		bro.setOpaque(false);
		bro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (jj == 5) {
					Databases database2 = new Databases();
					try {

						database2.classForname();
						database2.con = DriverManager.getConnection("jdbc:sqlite:class.db");
						database2.st = database2.con.createStatement();
						database2.st.executeUpdate("delete from slideImages where ROWID=1");

					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							database2.con.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				Databases database1 = new Databases();
				try {

					database1.classForname();
					database1.con = DriverManager.getConnection("jdbc:sqlite:class.db");
					database1.st = database1.con.createStatement();
					JFileChooser choose = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Image File", "PNG", "JPG");

					choose.setFileFilter(filter);
					choose.showDialog(null, "add image");
					if (!(choose.getSelectedFile() == null)) {

						File file = choose.getSelectedFile();
						FileInputStream fis;

						fis = new FileInputStream(file.getAbsoluteFile());
						ByteArrayOutputStream bos = new ByteArrayOutputStream();
						byte[] buf = new byte[1024];
						for (int readnum; (readnum = fis.read(buf)) != -1;) {
							bos.write(buf, 0, readnum);
						}
						fis.close();

						PreparedStatement pr = database1.con.prepareStatement("insert into slideImages values(?)");
						pr.setBytes(1, bos.toByteArray());
						pr.executeUpdate();
						pr.close();

						String q = "select count(), slides from slideImages";
						ResultSet g = database1.st.executeQuery(q);
						g.next();

						int left = 20;
						slides = new JLabel[g.getInt("count()")];
						for (int i = 0; i < slides.length; i++) {
							slides[i] = new JLabel();
							slides[i].setBounds(left, 300, 70, 70);
							slides[i].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
							oPanel.add(slides[i]);
							left += 75;
						}
						jj = slides.length;
						String q1 = "select  slides from slideImages order by slides";
						ResultSet g1 = database1.st.executeQuery(q1);
						while (g1.next()) {
							slides[g1.getRow() - 1].setIcon(new ImageIcon(g1.getBytes("slides")));

						}
					}

				} catch (Exception s) {
					System.out.println(s);
				} finally {
					try {
						database1.con.close();
					} catch (Exception s) {
						s.printStackTrace();
					}
				}

			}
		});

		if (jj != 0) {
			Databases db1 = new Databases();
			try {
				db1.classForname();
				db1.con = DriverManager.getConnection("jdbc:sqlite:class.db");
				db1.st = db1.con.createStatement();

				String q = "select  slides from slideImages";
				ResultSet g = db1.st.executeQuery(q);
				while (g.next()) {
					slides[g.getRow() - 1].setIcon(new ImageIcon(g.getBytes("slides")));

				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					db1.con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			jj = slides.length;
		}
		oPanel.add(bro);
		ImagesLoader();
		othersVisibilty(false);
	}

	private void ImagesLoader() {

		JPanelSlider sl = new JPanelSlider();
		JPanel p1 = new JPanel();
		sl.setBounds(20, 70, 370, 170);
		sl.setBackground(Color.cyan);
		sl.setVisible(true);
		JPanel p = new JPanel();
		p.setBounds(10, 10, 400, 250);
		p.setBackground(Color.yellow);
		p.setLayout(null);

		JLabel lb = new JLabel();
		lb.setBounds(0, 0, 370, 170);
		lb.setForeground(Color.white);

//		

		JLabel lb1 = new JLabel();
		lb1.setBounds(0, 0, 370, 170);
		lb.setForeground(Color.white);

		Timer time = new Timer(4000, new ActionListener() {
			int i = 0;
			int j = 0;
			BufferedImage image = null;

			public void actionPerformed(ActionEvent arg0) {
				if (jj != 0) {
					if (j == jj) {
						j = 0;
					}
					if (i % 2 == 0) {
						Icon image = slides[j].getIcon();

						ImageIcon image1 = (ImageIcon) image;

						lb.setIcon(new ImageIcon(image1.getImage().getScaledInstance(sl.getWidth(), sl.getHeight(),
								Image.SCALE_AREA_AVERAGING)));
						sl.nextPanel(10, p, JPanelSlider.left);
					} else {
						Icon image = slides[j].getIcon();

						ImageIcon image1 = (ImageIcon) image;

						lb1.setIcon(new ImageIcon(image1.getImage().getScaledInstance(sl.getWidth(), sl.getHeight(),
								Image.SCALE_AREA_AVERAGING)));
						sl.nextPanel(10, p1, JPanelSlider.left);
					}

					j++;
					i++;

				} else {
					if (i % 2 == 0) {
						try {
							image = ImageIO.read(getClass().getResource("/image/n.jpg"));

						} catch (Exception e) {

						}
						lb.setIcon(new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(lb.getWidth(),
								lb.getHeight(), Image.SCALE_AREA_AVERAGING)));
						sl.nextPanel(10, p, JPanelSlider.left);
					} else {
						try {
							image = ImageIO.read(getClass().getResource("/image/pi.png"));

						} catch (Exception e) {

						}

						lb1.setIcon(new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(lb1.getWidth(),
								lb1.getHeight(), Image.SCALE_AREA_AVERAGING)));
						sl.nextPanel(10, p1, JPanelSlider.left);
					}
					i++;
				}
			}

		});

		time.start();

		p1.setBounds(10, 10, 400, 250);
		p1.setBackground(Color.black);
		p1.setLayout(null);

		p.add(lb);
		p1.add(lb1);
		sl.add(p1);
		sl.add(p);
		oPanel.add(sl);

	}

	private void othersVisibilty(boolean b) {
		oPanel.setVisible(b);
	}

	private void curreculamPanel() {
		cuPanel = new JPanel();
		cuPanel.setBounds(0, 0, 890, 640);
		cuPanel.setBackground(Color.white);
		cuPanel.setLayout(null);
		contants.add(cuPanel);

//		sec,pri

		sec = new JTextField();
		sec.setBounds(150, 450, 150, 40);
		sec.setBorder(BorderFactory.createTitledBorder("add secondary subject"));
		sec.setFont(new Font("Calibri", Font.PLAIN, 15));
		cuPanel.add(sec);

		pri = new JTextField();
		pri.setBounds(430, 450, 150, 40);
		pri.setFont(new Font("Calibri", Font.PLAIN, 15));
		pri.setBorder(BorderFactory.createTitledBorder("add primery subject"));
		cuPanel.add(pri);

		tb2 = new JTable();
		tb2.setPreferredScrollableViewportSize(new Dimension(0, 0));
		tb2.setFont(new Font("ARIAL", Font.PLAIN, 15));
		tb2.getTableHeader().setBackground(new Color(34, 166, 242));
		tb2.getTableHeader().setForeground(Color.white);
		tb2.getTableHeader().setFont(new Font("Calibri", Font.PLAIN, 15));
		tb2.getTableHeader().removeAll();
		tb2.getTableHeader().setResizingAllowed(false);
		tb2.setBorder(BorderFactory.createSoftBevelBorder(10));
		tb2.setSurrendersFocusOnKeystroke(false);
		tb2.setRowHeight(35);
		tb2.setSelectionForeground(Color.black);
		tb2.setFont(new Font("Calibri", Font.PLAIN, 15));
		tb2.setSelectionBackground(new Color(97, 146, 194, 100));
		tb2.setShowHorizontalLines(false);

		Databases db1 = new Databases();
		try {
			db1.classForname();
			db1.con = DriverManager.getConnection("jdbc:sqlite:class.db");
			db1.st = db1.con.createStatement();

			String q = "SELECT psub as PRIMERY_SUBJECTS from Psubj";
			ResultSet g = db1.st.executeQuery(q);
			tb2.setModel(DbUtils.resultSetToTableModel(g));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				db1.con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		spane2 = new JScrollPane(tb2);
		spane2.setBounds(430, 50, 150, 380);
		spane2.setBorder(BorderFactory.createSoftBevelBorder(10));
		cuPanel.add(spane2);

//		
//		lbb1 = new JLabel(
//				"<html>To change the previous Exam names and maximum marks select the row .<br>&nbsp;&nbsp;&nbsp;Then change the exam name. <br>&nbsp;Unselect the column and save </html>");
//		lbb1.setBounds(150,-60, 530, 350);
//		lbb1.setForeground(Color.gray);
//		lbb1.setFont(new Font("Roman", Font.PLAIN, 14));
//		cuPanel.add(lbb1);

		tb1 = new JTable();
		tb1.setPreferredScrollableViewportSize(new Dimension(0, 0));
		tb1.setFont(new Font("ARIAL", Font.PLAIN, 15));
		tb1.getTableHeader().setBackground(new Color(34, 166, 242));
		tb1.getTableHeader().setForeground(Color.white);
		tb1.getTableHeader().setFont(new Font("Calibri", Font.PLAIN, 15));
		tb1.getTableHeader().removeAll();
		tb1.getTableHeader().setResizingAllowed(false);
		tb1.setBorder(BorderFactory.createSoftBevelBorder(10));
		tb1.setSurrendersFocusOnKeystroke(false);
		tb1.setRowHeight(35);
		tb1.setSelectionForeground(Color.black);
		tb1.setFont(new Font("Calibri", Font.PLAIN, 15));
		tb1.setSelectionBackground(new Color(97, 146, 194, 100));
		tb1.setShowHorizontalLines(false);

		Databases db = new Databases();
		try {
			db.classForname();
			db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
			db.st = db.con.createStatement();

			String q = "SELECT subject as SECODARY_SUBJECTS from subjects";
			ResultSet g = db.st.executeQuery(q);
			tb1.setModel(DbUtils.resultSetToTableModel(g));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				db.con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		spane1 = new JScrollPane(tb1);
		spane1.setBounds(150, 50, 150, 380);
		spane1.setBorder(BorderFactory.createSoftBevelBorder(10));
		cuPanel.add(spane1);

		BufferedImage imagea = null;
		try {
			imagea = ImageIO.read(getClass().getResource("/image/submits.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		save1 = new JButton();
		save1.setBounds(150, 500, 150, 40);
		save1.setOpaque(false);
		save1.setBorder(BorderFactory.createSoftBevelBorder(10));
		save1.setIcon(new ImageIcon(new ImageIcon(imagea).getImage().getScaledInstance(save1.getWidth(),
				save1.getHeight(), Image.SCALE_AREA_AVERAGING)));
		save1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (sec.getText().length() == 0) {
					Databases db = new Databases();
					try {
						db.classForname();
						db.con = DriverManager.getConnection("jdbc:sqlite:students.db");
						db.st = db.con.createStatement();

						String query = "alter table student add " + sec.getText() + " double not null default 0.0";
						db.st.executeUpdate(query);

						String query1 = "alter table records add " + sec.getText() + " double not null default 0.0";
						db.st.executeUpdate(query1);

					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							db.con.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					classs();
				}
			}

			private void classs() {
				Databases db = new Databases();
				try {
					db.classForname();
					db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
					db.st = db.con.createStatement();

					String query = "insert into allSub values('" + sec.getText() + "')";
					db.st.executeUpdate(query);
					String query1 = "insert into subjects values('" + sec.getText() + "')";
					db.st.executeUpdate(query1);

					String q = "SELECT subject as SECODARY_SUBJECTS from subjects";
					ResultSet g = db.st.executeQuery(q);
					tb1.setModel(DbUtils.resultSetToTableModel(g));

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						db.con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		});
		cuPanel.add(save1);

		save2 = new JButton();
		save2.setBounds(430, 500, 150, 40);
		save2.setOpaque(false);
		save2.setBorder(BorderFactory.createSoftBevelBorder(10));
		save2.setIcon(new ImageIcon(new ImageIcon(imagea).getImage().getScaledInstance(save2.getWidth(),
				save2.getHeight(), Image.SCALE_AREA_AVERAGING)));
		save2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (pri.getText().length() == 0) {
					Databases db = new Databases();
					try {
						db.classForname();
						db.con = DriverManager.getConnection("jdbc:sqlite:students.db");
						db.st = db.con.createStatement();

						String query = "alter table student add " + pri.getText() + " double not null default 0.0";
						db.st.executeUpdate(query);

						String query1 = "alter table records add " + pri.getText() + " double not null default 0.0";
						db.st.executeUpdate(query1);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							db.con.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					classs();
				}
			}

			private void classs() {
				Databases db = new Databases();
				try {
					db.classForname();
					db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
					db.st = db.con.createStatement();
					String query = "insert into Psubj values('" + pri.getText() + "')";
					db.st.executeUpdate(query);
					String query1 = "insert into allSub values('" + pri.getText() + "')";
					db.st.executeUpdate(query1);

					String q = "SELECT psub as PRIMERY_SUBJECTS from Psubj";
					ResultSet g = db.st.executeQuery(q);
					tb2.setModel(DbUtils.resultSetToTableModel(g));

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						db.con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

		});
		cuPanel.add(save2);

		BufferedImage buf = null;
		try {
			buf = ImageIO.read(getClass().getResource("/image/dele.png"));
		} catch (Exception e) {
			System.out.println(e);
		}

		JPopupMenu jp = new JPopupMenu();
		jp.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(244, 244, 244)));

		JMenuItem c = new JMenuItem(
				new ImageIcon(new ImageIcon(buf).getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));
		c.setText("Delete");
		c.setBackground(Color.white);
		c.setFont(new Font("ARIAL", Font.PLAIN, 15));
		c.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.DARK_GRAY));
		c.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Databases db = new Databases();
				try {
					db.classForname();
					db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
					db.st = db.con.createStatement();

					String query = "delete from allSub alsub='" + names + "'";
					db.st.executeUpdate(query);
					String query1 = "delete from subjects subject='" + names + "'";
					db.st.executeUpdate(query1);

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						db.con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		});

		jp.add(c);

		tb1.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				names = (String) tb1.getValueAt(tb1.getSelectedRow(), 0);
				if (e.isPopupTrigger()) {
					jp.show(tb1, e.getX(), e.getY());

				}
			}
		});

		JPopupMenu jp1 = new JPopupMenu();
		jp1.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(244, 244, 244)));

		JMenuItem c1 = new JMenuItem(
				new ImageIcon(new ImageIcon(buf).getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));
		c1.setText("Delete");
		c1.setBackground(Color.white);
		c1.setFont(new Font("ARIAL", Font.PLAIN, 15));
		c1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.DARK_GRAY));
		c1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Databases db = new Databases();
				try {
					db.classForname();
					db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
					db.st = db.con.createStatement();

					String query = "delete from allSub alsub='" + names + "'";
					db.st.executeUpdate(query);
					String query1 = "delete from Psubj psub='" + names + "'";
					db.st.executeUpdate(query1);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						db.con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		});

		jp1.add(c1);

		tb2.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				names = (String) tb2.getValueAt(tb2.getSelectedRow(), 0);
				if (e.isPopupTrigger()) {
					jp.show(tb2, e.getX(), e.getY());

				}
			}
		});

		curreculamVisiblity(false);
	}

	private void curreculamVisiblity(boolean b) {
		cuPanel.setVisible(b);
	}

	private void examPanel() {
		ExPanel = new JPanel();
		ExPanel.setBounds(0, 0, 890, 640);
		ExPanel.setBackground(Color.white);
		ExPanel.setLayout(null);
		contants.add(ExPanel);

		lbb = new JLabel(
				"<html>To change the previous Exam names and maximum marks select the row .<br>&nbsp;&nbsp;&nbsp;Then change the exam name. <br>&nbsp;Unselect the column and save </html>");
		lbb.setBounds(150, -60, 530, 350);
		lbb.setForeground(Color.gray);
		lbb.setFont(new Font("Roman", Font.PLAIN, 14));
		ExPanel.add(lbb);

		tb = new JTable();
		tb.setPreferredScrollableViewportSize(new Dimension(0, 0));
		tb.setFont(new Font("ARIAL", Font.PLAIN, 15));
		tb.getTableHeader().setBackground(new Color(34, 166, 242));
		tb.getTableHeader().setForeground(Color.white);
		tb.getTableHeader().setFont(new Font("Calibri", Font.PLAIN, 15));
		tb.getTableHeader().removeAll();
		tb.getTableHeader().setResizingAllowed(false);
		tb.setBorder(BorderFactory.createSoftBevelBorder(10));
		tb.setSurrendersFocusOnKeystroke(false);
		tb.setRowHeight(50);
		tb.setSelectionForeground(Color.black);
		tb.setFont(new Font("Calibri", Font.PLAIN, 15));
		tb.setSelectionBackground(new Color(97, 146, 194, 100));
		tb.setShowHorizontalLines(false);

		Databases db = new Databases();
		try {
			db.classForname();
			db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
			db.st = db.con.createStatement();

			String q = "select examname,maximum from Exam";
			ResultSet g = db.st.executeQuery(q);
			tb.setModel(DbUtils.resultSetToTableModel(g));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				db.con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		spane = new JScrollPane(tb);
		spane.setBounds(50, 200, 650, 230);
		spane.setBorder(BorderFactory.createSoftBevelBorder(10));

		ExPanel.add(spane);

		BufferedImage imagea = null;
		try {
			imagea = ImageIO.read(getClass().getResource("/image/submits.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		save = new JButton();
		save.setBounds(250, 470, 270, 40);
		save.setOpaque(false);
		save.setBorder(BorderFactory.createSoftBevelBorder(10));
		save.setIcon(new ImageIcon(new ImageIcon(imagea).getImage().getScaledInstance(save.getWidth(), save.getHeight(),
				Image.SCALE_AREA_AVERAGING)));
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Databases db = new Databases();
				try {
					db.classForname();
					db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
					db.st = db.con.createStatement();

					String columns[] = { "examname", "maximum" };
					for (int col = 0; col < tb.getColumnCount(); col++) {
						for (int row = 0; row < tb.getRowCount(); row++) {
							String update = "update Exam set " + columns[col] + "='" + tb.getValueAt(row, col)
									+ "' where rowid =" + (row + 1) + ";";
							db.st.executeUpdate(update);
						}

					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						db.con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

		});
		ExPanel.add(save);

		examVisibilty(false);
	}

	private void examVisibilty(boolean b) {
		ExPanel.setVisible(b);
	}

	private void securityPanel() {

//		 secLogo,secUser; secPass ,hiSc,Email;

		logPanel = new JPanel();
		logPanel.setBounds(0, 0, 890, 640);
		logPanel.setBackground(new Color(248, 246, 248));
		logPanel.setLayout(null);
		contants.add(logPanel);

		chanPanel = new JPanel();
		chanPanel.setBounds(0, 0, 890, 640);
		chanPanel.setVisible(false);
		chanPanel.setLayout(null);
		contants.add(chanPanel);

		log = new JPanel();
		log.setBounds(150, 30, 450, 500);
		log.setBackground(Color.white);
		log.setLayout(null);

		logPanel.add(log);

		secLogo = new JLabel("Schmasys pro");
		secLogo.setBounds(40, 40, 250, 32);
		secLogo.setForeground(new Color(64, 160, 131));
		secLogo.setFont(new Font("Calibri", Font.BOLD, 20));
		log.add(secLogo);

		Email = new JLabel();
		Email.setBounds(80, 120, 400, 45);
		Email.setForeground(Color.gray);
		Email.setFont(new Font("Roman", Font.PLAIN, 15));
//		Email.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
		log.add(Email);

		secname = new JLabel();
		secname.setBounds(40, 132, 25, 25);
		log.add(secname);

		secUser = new JLabel();
		secUser.setBounds(40, 80, 400, 45);
		secUser.setForeground(Color.black);
		secUser.setFont(new Font("Roman", Font.PLAIN, 18));
		log.add(secUser);

		seclabel = new JLabel("To continue, first verify it's you");
		seclabel.setBounds(40, 185, 400, 45);
		seclabel.setForeground(Color.gray);
		seclabel.setFont(new Font("Roman", Font.PLAIN, 15));
		log.add(seclabel);

		secPH = new JLabel("Enter your password");
		secPH.setBounds(40, 225, 400, 45);
		secPH.setForeground(new Color(64, 160, 131));
		secPH.setVisible(false);
		secPH.setFont(new Font("Roman", Font.PLAIN, 14));
		log.add(secPH);

//		paHolder secPass

		paHolder = new JTextField("Enter your password");
		paHolder.setBounds(40, 255, 360, 45);
		paHolder.setForeground(new Color(64, 160, 131));
		paHolder.setFont(new Font("Roman", Font.PLAIN, 15));
		paHolder.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(64, 160, 131)));
		paHolder.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {

				secPH.setVisible(true);
				try {
					Thread.sleep(150);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				paHolder.setVisible(false);
				try {
					Thread.sleep(150);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				secPass.setVisible(true);
			}
		});

		secPass = new JPasswordField();
		secPass.setBounds(40, 255, 360, 45);
		secPass.setForeground(new Color(64, 160, 131));
		secPass.setFont(new Font("Roman", Font.BOLD, 25));
		secPass.setEchoChar('.');
		secPass.setVisible(false);
		secPass.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(64, 160, 131)));
		secPass.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				if (secPass.getText().length() == 0) {
					secPH.setVisible(false);
					try {
						Thread.sleep(150);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					paHolder.setVisible(true);
					try {
						Thread.sleep(150);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					secPass.setVisible(false);
				}
			}
		});

		log.add(secPass);
		log.add(paHolder);

		BufferedImage imagea = null;
		try {
			imagea = ImageIO.read(getClass().getResource("/image/next.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		next = new JButton();
		next.setBounds(240, 325, 152, 40);
		next.setForeground(new Color(64, 160, 131));
		next.setIcon(new ImageIcon(new ImageIcon(imagea).getImage().getScaledInstance(next.getWidth(), next.getHeight(),
				Image.SCALE_AREA_AVERAGING)));
		next.setOpaque(false);
		next.setBorder(BorderFactory.createSoftBevelBorder(10));
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lLabel.setForeground(new Color(64, 160, 131));
				lLabel.setText("");
				if (secPass.getText().equals(secPassword)) {
					logPanel.setVisible(false);
					chanPanel.setVisible(true);
					secPass.setText("");
					if (secPass.getText().length() == 0) {
						secPH.setVisible(false);
						try {
							Thread.sleep(150);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						paHolder.setVisible(true);
						try {
							Thread.sleep(150);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						secPass.setVisible(false);
					}

				} else if (secPass.getText().length() == 0) {

				} else {
					lLabel.setText("Your password is incorrect.");
					lLabel.setForeground(Color.red);
				}

			}

		});

		forget = new JButton("Forgot password?");
		forget.setBounds(40, 325, 135, 35);
		forget.setForeground(new Color(64, 160, 131));
		forget.setOpaque(false);
		forget.setBorder(BorderFactory.createSoftBevelBorder(10));
		forget.setFont(new Font("Roman", Font.PLAIN, 16));
		forget.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Databases database2 = new Databases();
				try {
					database2.classForname();
					database2.con = DriverManager.getConnection("jdbc:sqlite:login.db");
					database2.st = database2.con.createStatement();

					Random code = new Random();
					int insert = code.nextInt(10_000);
					int nex = code.nextInt(10_000);
					String nn = "" + insert + "_" + nex;
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
						mm.setRecipients(Message.RecipientType.TO, InternetAddress.parse(Email.getText()));
						mm.setSubject("schmasys confirmation code");
						mm.setText("the confirmation code " + ran + " this code is one time usage");

						Transport.send(mm);

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,
								"There is no internet connection\nPlease connect to the internert", "internet warring",
								JOptionPane.WARNING_MESSAGE);
					}
					String op = JOptionPane.showInputDialog("Please Enter code have sent to your Email");
					if (ran.equalsIgnoreCase(op)) {
						logPanel.setVisible(false);
						chanPanel.setVisible(true);
						secPass.setText("");
						if (secPass.getText().length() == 0) {
							secPH.setVisible(false);
							try {
								Thread.sleep(150);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							paHolder.setVisible(true);
							try {
								Thread.sleep(150);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							secPass.setVisible(false);
						}
					} else if (op.length() == 0) {

					} else {
						lLabel.setText("incorrect code.");
						lLabel.setForeground(Color.red);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						database2.con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}

		);
		log.add(forget);
		log.add(next);

		lLabel = new JLabel();
		lLabel.setBounds(60, 395, 400, 45);
		lLabel.setForeground(new Color(64, 160, 131));
		lLabel.setFont(new Font("Roman", Font.PLAIN, 14));
		log.add(lLabel);
		changerSecurity();
		SecurityData();
		securityVisibilts(false);

	}

	private void changerSecurity() {

		holder = new JPanel();
		holder.setBounds(100, 0, 550, 620);
		holder.setBackground(Color.white);
		holder.setLayout(null);
		chanPanel.add(holder);

		ck = new JCheckBox("I accept terms and conditions");
		ck.setBounds(150, 420, 350, 35);
		ck.setFont(new Font("Roman", Font.PLAIN, 15));
		ck.setOpaque(false);
		ck.setBorder(BorderFactory.createSoftBevelBorder(10));
		ck.setForeground(Color.gray);
		ck.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (ck.isSelected()) {
					if (mails.equals(gmail.getText())) {
						yesOrNo = "Yes";
						Code.setVisible(true);
					} else {

						codeSent(gmail.getText());
					}
				}
			}

			private void codeSent(String mail) {
				int is = 0;
				Databases database2 = new Databases();
				try {
					database2.classForname();
					database2.con = DriverManager.getConnection("jdbc:sqlite:login.db");
					database2.st = database2.con.createStatement();

					Random code = new Random();
					int insert = code.nextInt(10_000);
					int nex = code.nextInt(10_000);
					String nn = "" + insert + "_" + nex;
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
								"There is no internet connection\nPlease connect to the internert", "internet warring",
								JOptionPane.WARNING_MESSAGE);
						is = 1;
					}
					if (is == 0) {
						String op = JOptionPane.showInputDialog("Please Enter code have sent to your Email");
						if (op != null) {
							if (op.equalsIgnoreCase(ran)) {
								yesOrNo = "Yes";
								Code.setVisible(true);
								ck.setSelected(true);
								Code.setText("we  accepted your details !!");
								Code.setForeground(Color.gray);
							} else {
								yesOrNo = "no";
								Code.setVisible(true);
								Code.setForeground(Color.red);
								Code.setText("we don't accepted your details !!");
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						database2.con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

		});
		holder.add(ck);

		Code = new JLabel("<html>We accepted your details</html>");
		Code.setBounds(160, 456, 530, 30);
		Code.setForeground(Color.gray);
		Code.setFont(new Font("Roman", Font.PLAIN, 14));
		Code.setVisible(false);
		holder.add(Code);

		BufferedImage imagea = null;
		try {
			imagea = ImageIO.read(getClass().getResource("/image/submits.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
//		yesOrNo,passChek,RepassChek,userChek;
		submits = new JButton();
		submits.setBounds(150, 520, 270, 40);
		submits.setIcon(new ImageIcon(new ImageIcon(imagea).getImage().getScaledInstance(submits.getWidth(),
				submits.getHeight(), Image.SCALE_AREA_AVERAGING)));
		submits.setOpaque(false);
		submits.setBorder(BorderFactory.createSoftBevelBorder(10));
		submits.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				boolean checker = newPass.getText().equals(RePass.getText());
				boolean User = userChek.length() >= 5;
				if (yesOrNo.equalsIgnoreCase("Yes") && checker && User) {

					userChek = Username.getText();

					Databases database1 = new Databases();

					try {
						database1.classForname();
						database1.con = DriverManager.getConnection("jdbc:sqlite:login.db");
						database1.st = database1.con.createStatement();
						String update = "update loging set EmailOrPhone='" + gmail.getText() + "',Username='" + userChek
								+ "',PasswordKey ='" + newPass.getText() + "',ReEnterPasswrd='" + RePass.getText()
								+ "';";
						database1.st.executeUpdate(update);

						String data = "select Username, PasswordKey,ReEnterPasswrd,EmailOrPhone,logo from loging";

						ResultSet rs = database1.st.executeQuery(data);
						rs.next();
						secUser.setText("Hi " + rs.getString("Username"));

						secname.setIcon(new ImageIcon(new ImageIcon(rs.getBytes("logo")).getImage().getScaledInstance(
								secname.getWidth(), secname.getHeight(), Image.SCALE_AREA_AVERAGING)));
						Email.setText(rs.getString("EmailOrPhone"));
						mails = rs.getString("EmailOrPhone");
						secPassword = rs.getString("PasswordKey");
						gmail.setText(rs.getString("EmailOrPhone"));

					} catch (Exception e) {
						System.out.println(e);
					} finally {
						try {
							database1.con.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					chanPanel.setVisible(false);
					logPanel.setVisible(true);

				} else {
					Code.setText("invalid details please check your informations");
					Code.setForeground(Color.RED);
					yesOrNo = "No";
					ck.setSelected(false);

				}

			}

		});
		holder.add(submits);

		Sysname = new JLabel("Schmasys pro");
		Sysname.setBounds(50, 40, 250, 32);
		Sysname.setForeground(new Color(64, 160, 131));
		Sysname.setFont(new Font("Calibri", Font.BOLD, 20));
		holder.add(Sysname);

		BufferedImage image = null;
		String images[] = { "/image/secEmail.png", "/image/secUser.png", "/image/secPasss.png", "/image/secReP.png", };

		int bottom = 110;
		lb = new JLabel[4];
		lbicons = new JLabel[4];
		String lbs[] = { "Email", "Username", "Password", "Re-Enter", "Hint Password" };
		for (int i = 0; i < lb.length; i++) {
			lb[i] = new JLabel(lbs[i]);
			lb[i].setBounds(55, bottom, 135, 35);
			lb[i].setFont(new Font("Roman", Font.BOLD, 16));
			lb[i].setForeground(new Color(64, 160, 131));
			holder.add(lb[i]);

			lbicons[i] = new JLabel(lbs[i]);
			lbicons[i].setBounds(20, bottom, 25, 25);
			try {
				image = ImageIO.read(getClass().getResource(images[i]));
			} catch (IOException e) {
				e.printStackTrace();
			}

			lbicons[i].setIcon(new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(lbicons[i].getWidth(),
					lbicons[i].getHeight(), Image.SCALE_AREA_AVERAGING)));

			holder.add(lbicons[i]);

			bottom += 70;
		}

		String lb[] = { "<html>Enter a valid email</html>", "<html>Username must be at least 5 characters long<html>",
				"<html>Password must be at least 8 characters long<html>", "<html>Confirm password<html>",
				"<html><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Others</b><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Add Images on the slider, <br>&nbsp;&nbsp;&nbsp;&nbsp;show remined days, Date, help<html>" };
		int bottom1 = 135;
		notes = new JLabel[4];
		for (int i = 0; i < notes.length; i++) {
			notes[i] = new JLabel(lb[i]);
			notes[i].setBounds(155, bottom1, 400, 35);
			notes[i].setForeground(Color.gray);
			notes[i].setOpaque(false);
			notes[i].setFont(new Font("Calibri", Font.PLAIN, 15));
			notes[i].setBorder(BorderFactory.createSoftBevelBorder(10));
			holder.add(notes[i]);
			bottom1 += 75;
		}

		gmail = new JTextField();
		gmail.setBounds(150, 110, 350, 30);
		gmail.setForeground(Color.gray);
		gmail.setFont(new Font("Roman", Font.PLAIN, 17));
		gmail.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
		gmail.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {

			}
		});
		holder.add(gmail);

//		newPassH,RepassH,hint; newPass,RePass;ck; newPass,RePass

		Username = new JTextField();
		Username.setBounds(150, 180, 350, 30);
		Username.setForeground(Color.gray);
		Username.setFont(new Font("Roman", Font.PLAIN, 15));
		Username.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
		Username.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {

				if (Username.getText().length() < 5) {
					notes[1].setText("The username is weak !!, and Less then 5 characters");
					notes[1].setForeground(Color.red);
				} else {
					notes[1].setText("The username is valid");
					notes[1].setForeground(Color.gray);
					userChek = Username.getText();
				}
			}
		});
		holder.add(Username);

//		passChek,RepassChek
		newPass = new JPasswordField();
		newPass.setBounds(150, 250, 335, 30);
		newPass.setForeground(Color.gray);
		newPass.setEchoChar('.');

		newPass.setFont(new Font("Roman", Font.BOLD, 25));
		newPass.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
		newPass.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				checkPass = newPass.getText();
				if (checkPass.length() < 5) {
					notes[2].setText("The password is weak !!, and Less then 5 characters");
					notes[2].setForeground(Color.red);
				} else {
					notes[2].setText("The password is Strong");
					notes[2].setForeground(Color.gray);
				}

			}
		});
		holder.add(newPass);

		pT = new JToggleButton();
		pT.setBounds(485, 260, 20, 20);
		BufferedImage tog = null;
		try {
			tog = ImageIO.read(getClass().getResource("/image/hide.png"));

		} catch (Exception e) {

		}
		pT.setIcon(new ImageIcon(new ImageIcon(tog).getImage().getScaledInstance(pT.getWidth(), pT.getHeight(),
				Image.SCALE_AREA_AVERAGING)));

		pT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
		pT.setBackground(Color.white);
		pT.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				int state = itemEvent.getStateChange();

				if (state == ItemEvent.SELECTED) {
					BufferedImage tog = null;
					try {
						tog = ImageIO.read(getClass().getResource("/image/view.png"));

					} catch (Exception e) {

					}

					pT.setIcon(new ImageIcon(new ImageIcon(tog).getImage().getScaledInstance(pT.getWidth(),
							pT.getHeight(), Image.SCALE_AREA_AVERAGING)));
					newPass.setFont(new Font("Roman", Font.PLAIN, 15));
					newPass.setEchoChar((char) 0);
				} else {
					BufferedImage tog = null;
					try {
						tog = ImageIO.read(getClass().getResource("/image/hide.png"));

					} catch (Exception e) {

					}
					pT.setIcon(new ImageIcon(new ImageIcon(tog).getImage().getScaledInstance(pT.getWidth(),
							pT.getHeight(), Image.SCALE_AREA_AVERAGING)));

					newPass.setFont(new Font("Roman", Font.BOLD, 25));
					newPass.setEchoChar('.');

				}
			}
		});

		holder.add(pT);

		RePass = new JPasswordField();
		RePass.setBounds(150, 325, 335, 30);
		RePass.setForeground(Color.gray);
		RePass.setFont(new Font("Roman", Font.BOLD, 25));
		RePass.setEchoChar('.');
		RePass.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
		RePass.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (RePass.getText().equals(newPass.getText())) {
					notes[3].setText("Password match");
					notes[3].setForeground(Color.gray);
				} else {
					notes[3].setText("Password mismatch");
					notes[3].setForeground(Color.red);
				}
			}
		});
		holder.add(RePass);

		rT = new JToggleButton();
		rT.setBounds(485, 335, 20, 20);
		BufferedImage tog1 = null;
		try {
			tog1 = ImageIO.read(getClass().getResource("/image/hide.png"));

		} catch (Exception e) {

		}
		rT.setIcon(new ImageIcon(new ImageIcon(tog1).getImage().getScaledInstance(pT.getWidth(), pT.getHeight(),
				Image.SCALE_AREA_AVERAGING)));

		rT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
		rT.setBackground(Color.white);
		rT.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				int state = itemEvent.getStateChange();

				if (state == ItemEvent.SELECTED) {
					BufferedImage tog = null;
					try {
						tog = ImageIO.read(getClass().getResource("/image/view.png"));

					} catch (Exception e) {

					}

					rT.setIcon(new ImageIcon(new ImageIcon(tog).getImage().getScaledInstance(rT.getWidth(),
							rT.getHeight(), Image.SCALE_AREA_AVERAGING)));
					RePass.setFont(new Font("Roman", Font.PLAIN, 15));
					RePass.setEchoChar((char) 0);

				} else {
					BufferedImage tog = null;
					try {
						tog = ImageIO.read(getClass().getResource("/image/hide.png"));

					} catch (Exception e) {

					}
					rT.setIcon(new ImageIcon(new ImageIcon(tog).getImage().getScaledInstance(rT.getWidth(),
							rT.getHeight(), Image.SCALE_AREA_AVERAGING)));
					RePass.setFont(new Font("Roman", Font.BOLD, 25));
					RePass.setEchoChar('.');
				}
			}
		});

		holder.add(rT);

	}

	private void securityVisibilts(boolean b) {
		logPanel.setVisible(b);
	}

	private void SecurityData() {
		Databases database = new Databases();
		ResultSet rs = null;
		try {
			String url = "jdbc:sqlite:login.db";
			String data = "select Username, PasswordKey,ReEnterPasswrd,EmailOrPhone,logo from loging";
			database.classForname();
			database.con = DriverManager.getConnection(url);
			database.st = database.con.createStatement();
			rs = database.st.executeQuery(data);
			rs.next();
			if(rs.getString("Username").length() != 0) {
			secUser.setText("Hi " + rs.getString("Username"));
//			Username.setText(rs.getString("Username"));

			if( rs.getBytes("logo") != null)
				secname.setIcon(new ImageIcon(new ImageIcon(rs.getBytes("logo")).getImage()
						.getScaledInstance(secname.getWidth(), secname.getHeight(), Image.SCALE_AREA_AVERAGING)));
				else {
					BufferedImage log = null;
					try {
						log = ImageIO.read(getClass().getResource("/image/UJUHU-01.png"));

					} catch (Exception e) {

					}
					secname.setIcon(new ImageIcon(new ImageIcon(log).getImage()
							.getScaledInstance(secname.getWidth(), secname.getHeight(), Image.SCALE_AREA_AVERAGING)));
				}
			
			Email.setText(rs.getString("EmailOrPhone"));
			mails = rs.getString("EmailOrPhone");
			secPassword = rs.getString("PasswordKey");
//			newPass.setText(rs.getString("PasswordKey"));
//			RePass.setText(rs.getString("PasswordKey"));
			gmail.setText(rs.getString("EmailOrPhone"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				database.con.close();
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}

	private void managementPanel() {
		logo = new JLabel();
		logo.setBounds(30, 30, 126, 126);
		logo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Databases database1 = new Databases();
				try {

					database1.classForname();
					database1.con = DriverManager.getConnection("jdbc:sqlite:login.db");
					database1.st = database1.con.createStatement();
					JFileChooser choose = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Image File", "PNG", "JPG");

					choose.setFileFilter(filter);
					choose.showDialog(null, "add image");
					if (!(choose.getSelectedFile() == null)) {

						Image image = ImageIO.read(choose.getSelectedFile());
						Image image1 = image.getScaledInstance(logo.getWidth(), logo.getHeight(),
								Image.SCALE_AREA_AVERAGING);
						logo.setIcon(new ImageIcon(image1));
						File file = choose.getSelectedFile();
						FileInputStream fis;

						fis = new FileInputStream(file.getAbsoluteFile());
						ByteArrayOutputStream bos = new ByteArrayOutputStream();
						byte[] buf = new byte[1024];
						for (int readnum; (readnum = fis.read(buf)) != -1;) {
							bos.write(buf, 0, readnum);
						}
						fis.close();

						PreparedStatement pr = database1.con.prepareStatement("update loging set logo=?");
						pr.setBytes(1, bos.toByteArray());
						pr.executeUpdate();
						pr.close();
					}

				} catch (Exception s) {
					System.out.println(s);
				} finally {
					try {
						database1.con.close();
					} catch (Exception s) {
						s.printStackTrace();
					}
				}
			}
		});
		contants.add(logo);

		BufferedImage image = null;
		String images[] = { "/image/Sch.png", "/image/hT.png", "/image/SCT.png" };

		school = new JTextField[3];
		texts = new JLabel[3];
		icons = new JLabel[3];
		edite = new JButton[3];

		int bottom = 200;
		for (int i = 0; i < school.length; i++) {
			school[i] = new JTextField();
			school[i].setBounds(65, bottom, 600, 35);
			school[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
			school[i].setForeground(Color.gray);
			school[i].setVisible(false);
			school[i].setFont(new Font("Roman", Font.PLAIN, 17));

			texts[i] = new JLabel();
			texts[i].setBounds(65, bottom + 3, 600, 35);
			texts[i].setForeground(Color.gray);
			texts[i].setVisible(false);
			texts[i].setFont(new Font("Roman", Font.BOLD, 17));
			texts[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));

			try {
				image = ImageIO.read(getClass().getResource(images[i]));
			} catch (IOException e) {
				e.printStackTrace();
			}

			icons[i] = new JLabel();
			icons[i].setBounds(20, bottom, 32, 32);
			icons[i].setIcon(new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(icons[i].getWidth(),
					icons[i].getHeight(), Image.SCALE_AREA_AVERAGING)));
			icons[i].setVisible(false);

			try {
				image = ImageIO.read(getClass().getResource("/image/chan.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			edite[i] = new JButton("" + i);
			edite[i].setBounds(670, bottom, 80, 30);
			edite[i].setIcon(new ImageIcon(
					new ImageIcon(image).getImage().getScaledInstance(25, 25, Image.SCALE_AREA_AVERAGING)));
			edite[i].setBorder(BorderFactory.createSoftBevelBorder(10));
			edite[i].setOpaque(false);
			edite[i].setVisible(false);
			edite[i].setForeground(Color.white);
			edite[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					for (int i = 0; i < texts.length; i++) {
						texts[i].setVisible(true);
						school[i].setVisible(false);

					}

					no = Integer.parseInt(arg0.getActionCommand().intern());
					school[no].setVisible(true);
					school[no].setText(texts[no].getText());
					school[no].addFocusListener(new FocusAdapter() {
						public void focusLost(FocusEvent e) {
							texts[no].setText(school[no].getText());
						}
					});
					texts[no].setVisible(false);
				}

			});

			contants.add(edite[i]);
			contants.add(icons[i]);
			contants.add(texts[i]);
			contants.add(school[i]);
			bottom += 120;
		}
		BufferedImage imagea = null;
		try {
			imagea = ImageIO.read(getClass().getResource("/image/submits.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		submit = new JButton();
		submit.setBounds(235, 540, 270, 40);
		submit.setIcon(new ImageIcon(new ImageIcon(imagea).getImage().getScaledInstance(submit.getWidth(),
				submit.getHeight(), Image.SCALE_AREA_AVERAGING)));
		submit.setOpaque(false);
		submit.setBorder(BorderFactory.createSoftBevelBorder(10));
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String update = "update loging set schoolName='" + texts[0].getText() + "',HeadteacherName='"
						+ texts[1].getText() + "',secretaryName='" + texts[2].getText() + "';";

				Databases database = new Databases();

				try {
					String url = "jdbc:sqlite:login.db";
					database.classForname();
					database.con = DriverManager.getConnection(url);
					database.st = database.con.createStatement();
					database.st.executeUpdate(update);

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						database.con.close();
					} catch (Exception e) {

						e.printStackTrace();
					}
				}

				for (int i = 0; i < school.length; i++) {
					school[i].setVisible(false);
					texts[i].setVisible(true);
				}

			}

		});

		contants.add(submit);

		logoLabel = new JLabel(
				"<html>To change the previous school logo click previously applied logo.<br>&nbsp;&nbsp;&nbsp;Then choose the directory were you stored the innovation logo. <br>&nbsp;<b>Note</b><i> \"this fileChooser shows you only (<b>png or jpg</b>) extentioned files\"</i></html>");
		logoLabel.setBounds(180, 30, 530, 170);
		logoLabel.setForeground(Color.gray);
		logoLabel.setFont(new Font("Roman", Font.PLAIN, 14));
		contants.add(logoLabel);

		schoolLabel = new JLabel(
				"<html>Click right corner icon or button to edit the school name or change completely</html>");
		schoolLabel.setBounds(70, 200, 530, 130);
		schoolLabel.setForeground(Color.gray);
		schoolLabel.setFont(new Font("Roman", Font.PLAIN, 14));
		contants.add(schoolLabel);

		headLabel = new JLabel(
				"<html>This name is head teacher name please keep there enter correct information</html>");
		headLabel.setBounds(70, 330, 530, 130);
		headLabel.setForeground(Color.gray);
		headLabel.setFont(new Font("Roman", Font.PLAIN, 14));
		contants.add(headLabel);

		subLabel = new JLabel("<html>Click submit button to store the information</html>");
		subLabel.setBounds(150, 440, 530, 130);
		subLabel.setForeground(Color.gray);
		subLabel.setFont(new Font("Roman", Font.PLAIN, 14));
		contants.add(subLabel);

		data();
	}

	private void data() {
		Databases database = new Databases();
		ResultSet rs = null;
		try {
			String url = "jdbc:sqlite:login.db";
			String data = "select schoolName,HeadteacherName,secretaryName,logo from loging";
			database.classForname();
			database.con = DriverManager.getConnection(url);
			database.st = database.con.createStatement();
			rs = database.st.executeQuery(data);
			rs.next();
			
			if(rs.getString("schoolName").length() != 0) {
			texts[0].setText(rs.getString("schoolName"));
			texts[1].setText(rs.getString("HeadteacherName"));
			texts[2].setText(rs.getString("secretaryName"));
			if( rs.getBytes("logo") != null)
			logo.setIcon(new ImageIcon(new ImageIcon(rs.getBytes("logo")).getImage().getScaledInstance(logo.getWidth(),
					logo.getHeight(), Image.SCALE_AREA_AVERAGING)));
			else {
				BufferedImage log = null;
				try {
					log = ImageIO.read(getClass().getResource("/image/UJUHU-01.png"));

				} catch (Exception e) {

				}
				logo.setIcon(new ImageIcon(new ImageIcon(log).getImage().getScaledInstance(logo.getWidth(),
						logo.getHeight(), Image.SCALE_AREA_AVERAGING)));
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				database.con.close();
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}

	private void managementVisibilts(boolean b) {
		logo.setVisible(b);
		submit.setVisible(b);
		logoLabel.setVisible(b);
		schoolLabel.setVisible(b);
		headLabel.setVisible(b);
		subLabel.setVisible(b);
		for (int i = 0; i < texts.length; i++) {
			texts[i].setVisible(b);
			icons[i].setVisible(b);
			edite[i].setVisible(b);
		}
		for (int i = 0; i < school.length; i++)
			school[i].setVisible(false);
	}

	private void ListHolder() {
		BufferedImage imagea = null;
		try {
			imagea = ImageIO.read(getClass().getResource("/image/ico.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		pane = new JPanel();
		pane.setBounds(299, 0, 890, 50);
		pane.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		pane.setBackground(new Color(242, 242, 242));

		add(pane);

		listPanel = new JPanel();
		listPanel.setBounds(0, 0, 300, 690);
		listPanel.setLayout(null);
		listPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		listPanel.setBackground(new Color(242, 242, 242));
		add(listPanel);

		back = new JLabel();
		back.setBounds(5, 5, 32, 32);
		back.setForeground(Color.black);

		back.setIcon(
				new ImageIcon(new ImageIcon(imagea).getImage().getScaledInstance(32, 32, Image.SCALE_AREA_AVERAGING)));
		back.setFont(new Font("Calibri", Font.PLAIN, 15));
		back.setBorder(BorderFactory.createSoftBevelBorder(10));
		back.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				listPanel.setVisible(false);
				container.setVisible(true);
				pane.setVisible(false);
				contants.setVisible(false);
			}

		});
		listPanel.add(back);

		JLabel titleName = new JLabel("Settings");
		titleName.setBounds(45, 12, 200, 30);
		titleName.setForeground(Color.gray);
		titleName.setFont(new Font("Calibri", Font.BOLD, 20));
		listPanel.add(titleName);

		menuses();

	}

	private void menuses() {
//		Curriculum or subjects
		BufferedImage image = null;
		String images[] = { "/image/manag.png", "/image/secu.png", "/image/ex.png", "/image/sub.png",
				"/image/other.png" };
		String lb[] = {
				"<html><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Management</b><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Head teacher, secretory,<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;school name, logo </html>",
				"<html><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Security</b><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Email account, username,<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;password<html>",
				"<html><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Examination</b><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Maximum marks on the exams,<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;examinations<html>",
				"<html><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Curriculum</b><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Curriculum or subjects<html>",
				"<html><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Others</b><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Add Images on the slider, <br>&nbsp;&nbsp;&nbsp;&nbsp;show remined days, about <html>" };
		int bottom = 55;
		Smenu = new JLabel[5];
		for (int i = 0; i < Smenu.length; i++) {
			Smenu[i] = new JLabel(lb[i]);
			Smenu[i].setBounds(20, bottom, 300, 130);
			Smenu[i].setForeground(Color.gray);
			Smenu[i].setOpaque(false);
			try {
				image = ImageIO.read(getClass().getResource(images[i]));
			} catch (IOException e) {
				e.printStackTrace();
			}
			Smenu[i].setIcon(new ImageIcon(
					new ImageIcon(image).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
			Smenu[i].setFont(new Font("Calibri", Font.PLAIN, 15));
			Smenu[i].setBorder(BorderFactory.createSoftBevelBorder(10));
			listPanel.add(Smenu[i]);
			Smenu[i].addMouseListener(mouse);
			bottom += 110;
		}

	}

	private void menus() {
		BufferedImage image = null;
		String images[] = { "/image/manag.png", "/image/secu.png", "/image/ex.png", "/image/sub.png",
				"/image/other.png" };
		int left = 180, bottom = 150;
		menu = new JButton[5];
		for (int i = 0; i < menu.length; i++) {
			menu[i] = new JButton();
			if (i == 3) {
				left = 350;
				bottom = 400;
			}
			menu[i].setBounds(left, bottom, 64, 64);
			menu[i].setOpaque(false);
			try {
				image = ImageIO.read(getClass().getResource(images[i]));
			} catch (IOException e) {
				e.printStackTrace();
			}
			menu[i].setIcon(new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(menu[i].getWidth(),
					menu[i].getHeight(), Image.SCALE_AREA_AVERAGING)));
			menu[i].setBorder(BorderFactory.createSoftBevelBorder(10));
			menu[i].addActionListener(action);

			left += 300;
			container.add(menu[i]);
		}

		titler();
		menuName();

	}

	private void menuName() {
		mName = new JLabel("<html> Head teacher, secretory,<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;school name, logo <html>");
		mName.setBounds(130, 220, 200, 100);
		mName.setForeground(Color.gray);
		mName.setFont(new Font("Roman", Font.PLAIN, 16));
		container.add(mName);

//		sName eName cName
		sName = new JLabel(
				"<html> Email account, username,<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;password<html>");
		sName.setBounds(425, 220, 200, 100);
		sName.setForeground(Color.gray);
		sName.setFont(new Font("Roman", Font.PLAIN, 16));
		container.add(sName);

		eName = new JLabel(
				"<html>Maximum marks on the exams,<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;examinations<html>");
		eName.setBounds(707, 220, 300, 100);
		eName.setForeground(Color.gray);
		eName.setFont(new Font("Roman", Font.PLAIN, 16));
		container.add(eName);

		cName = new JLabel("<html>Curriculum or subjects<html>");
		cName.setBounds(307, 465, 300, 100);
		cName.setForeground(Color.gray);
		cName.setFont(new Font("Roman", Font.PLAIN, 16));
		container.add(cName);

		oName = new JLabel(
				"<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Add Images on the slider, <br>&nbsp;&nbsp;&nbsp;&nbsp;show remined days, about <html>");
		oName.setBounds(555, 475, 350, 100);
		oName.setForeground(Color.gray);
		oName.setFont(new Font("Roman", Font.PLAIN, 16));
		container.add(oName);

	}

	private void titler() {
		String foot[] = { "Managemet", "Security", "Exam Settings", "Curriculum Setting", "Other Settings" };

		manag = new JLabel(foot[0]);
		manag.setBounds(175, 215, 200, 35);
		manag.setForeground(Color.black);
		manag.setFont(new Font("Roman", Font.PLAIN, 15));
		container.add(manag);

		secu = new JLabel(foot[1]);
		secu.setBounds(485, 215, 200, 35);
		secu.setForeground(Color.black);
		secu.setFont(new Font("Roman", Font.PLAIN, 15));
		container.add(secu);

		ex = new JLabel(foot[2]);
		ex.setBounds(763, 210, 200, 35);
		ex.setForeground(Color.black);
		ex.setFont(new Font("Roman", Font.PLAIN, 15));
		container.add(ex);

		sub = new JLabel(foot[3]);
		sub.setBounds(323, 470, 200, 35);
		sub.setForeground(Color.black);
		sub.setFont(new Font("Roman", Font.PLAIN, 15));
		container.add(sub);

		other = new JLabel(foot[4]);
		other.setBounds(630, 470, 200, 35);
		other.setForeground(Color.black);
		other.setFont(new Font("Roman", Font.PLAIN, 15));
		container.add(other);
	}

	private void titles() {

		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResource("/image/ico.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		icon = new JLabel();
		icon.setBounds(10, 5, 45, 45);
		icon.setIcon(
				new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(32, 32, Image.SCALE_AREA_AVERAGING)));
		container.add(icon);

		titleName = new JLabel("Settings");
		titleName.setBounds(50, 13, 200, 45);
		titleName.setForeground(Color.gray);
		titleName.setFont(new Font("Calibri", Font.BOLD, 25));
		container.add(titleName);

	}

	private void container() {
		container = new JPanel();
		container.setBounds(0, 0, 1045, 690);
		container.setLayout(null);
		container.setBackground(Color.white);
		add(container);
	}

	private void design() {
		setVisible(false);
		setLayout(null);
		setBackground(Color.white);
		setBounds(280, 30, 1045, 690);
	}

	private class actionListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			for (int i = 0; i < Smenu.length; i++) {
				Smenu[i].setFont(new Font("Calibri", Font.PLAIN, 15));
				Smenu[i].setForeground(Color.gray);
			}
			chanPanel.setVisible(false);
			managementVisibilts(false);
			securityVisibilts(false);
			examVisibilty(false);
			curreculamVisiblity(false);
			othersVisibilty(false);
			if (a.getSource() == menu[0]) {
				chacher(0);
				container.setVisible(false);
				listPanel.setVisible(true);
				pane.setVisible(true);
				contants.setVisible(true);
				managementVisibilts(true);
			} else if (a.getSource() == menu[1]) {
				chacher(1);
				container.setVisible(false);
				listPanel.setVisible(true);
				contants.setVisible(true);
				pane.setVisible(true);
				securityVisibilts(true);
			} else if (a.getSource() == menu[2]) {
				chacher(2);
				container.setVisible(false);
				listPanel.setVisible(true);
				contants.setVisible(true);
				pane.setVisible(true);
				examVisibilty(true);
			} else if (a.getSource() == menu[3]) {
				chacher(3);
				container.setVisible(false);
				listPanel.setVisible(true);
				contants.setVisible(true);
				pane.setVisible(true);
				curreculamVisiblity(true);
			} else if (a.getSource() == menu[4]) {
				chacher(4);
				container.setVisible(false);
				listPanel.setVisible(true);
				pane.setVisible(true);
				contants.setVisible(true);
				othersVisibilty(true);
			}
		}

		private void chacher(int i) {
			Smenu[i].setFont(new Font("Calibri", Font.BOLD, 15));
			Smenu[i].setForeground(Color.black);
		}

	}

	private class mouseListener implements MouseListener {
		public void mouseClicked(MouseEvent ar) {
			for (int i = 0; i < Smenu.length; i++) {
				Smenu[i].setFont(new Font("Calibri", Font.PLAIN, 15));
				Smenu[i].setForeground(Color.gray);
			}
			managementVisibilts(false);
			chanPanel.setVisible(false);
			securityVisibilts(false);
			examVisibilty(false);
			curreculamVisiblity(false);
			othersVisibilty(false);
			for (int i = 0; i < school.length; i++)
				school[i].setVisible(false);
			if (ar.getSource() == Smenu[0]) {
				Smenu[0].setFont(new Font("Calibri", Font.BOLD, 15));
				Smenu[0].setForeground(Color.black);
				managementVisibilts(true);
			}
			if (ar.getSource() == Smenu[1]) {
				Smenu[1].setFont(new Font("Calibri", Font.BOLD, 15));
				Smenu[1].setForeground(Color.black);
				securityVisibilts(true);
			}
			if (ar.getSource() == Smenu[2]) {
				Smenu[2].setFont(new Font("Calibri", Font.BOLD, 15));
				Smenu[2].setForeground(Color.black);
				examVisibilty(true);
			}
			if (ar.getSource() == Smenu[3]) {
				Smenu[3].setFont(new Font("Calibri", Font.BOLD, 15));
				Smenu[3].setForeground(Color.black);
				curreculamVisiblity(true);
			}
			if (ar.getSource() == Smenu[4]) {
				Smenu[4].setFont(new Font("Calibri", Font.BOLD, 15));
				Smenu[4].setForeground(Color.black);
				othersVisibilty(true);
			}
		}

		public void mouseEntered(MouseEvent arg0) {

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}
}
