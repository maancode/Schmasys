package schoolManegementSystem;

import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Informations extends JPanel {
	private static final long serialVersionUID = 1L;
	private JScrollPane container;
	private JPanel panel;
	private JPanel title;
	private JLabel tit;
	private JLabel titname;
	private JPanel[] ident;
	private JPanel[] iHeader;

	private JLabel  sname[];
	private JLabel SI[], SN[], SC[], SNO[], Se[];
	private String StudentNames[];
	private JButton menu[];
	private JLabel lb;
	private JComboBox<String> box;
	private JSeparator sem;

	private String d;
	private String snn, sec;

	private String cl = null;
	private boolean b = true;
	private String[] sm = { "ALL", "SECTION 1", "SECTION 2", "SECTION 3", "SECTION 4", "SECTION 5", "SECTION 6",
			"SECTION 7" };
	private JPanel Cover;
	private JPanel Underline;
	private JButton menus[];
	private Timer ttt;
	private JPanel[] menuH;
	private int elements;
	private JButton items[];
	private String Class, school;
	private JToggleButton ch;
	private JTextField ser;
	private DefaultListModel<ImgNtext> m;
	private JList<ImgNtext> li;
	private JScrollPane spane;
	private JPanel serPane;
	private JPanel holder;
	private JButton close, iP, iM, iD;
	private JLabel Simage, Sname, Sclass, Sphone, SEmail, titles;
	private String names, chs = "";
	private boolean checker;
	private JPanel covers;
	private byte[] image;
	private JButton grad;

	actionListener action = new actionListener();
	menuListener menuses = new menuListener();
	itemListener itemses = new itemListener();

	StudentProfile sp = new StudentProfile();
	Profile pr = new Profile();
	Graduated grads = new Graduated();

	public Informations() {
		design();
		Databases dc1 = new Databases();
		try {

			dc1.classForname();
			dc1.con = DriverManager.getConnection("jdbc:sqlite:students.db");
			dc1.st = dc1.con.createStatement();
			String query = "select Student_name from student";
			ResultSet rs = dc1.st.executeQuery(query);
			checker = rs.next();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				dc1.con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (checker) {

			container();
			titleOptions();
			Thread tt = new Thread() {
				public void run() {

					lists();
					Menues();
					Cover.setVisible(false);
					container.setVisible(true);
				}
			};
			tt.start();
		} else {

			covers();

		}
	}

	private void covers() {
		BufferedImage id = null;
		try {
			id = ImageIO.read(getClass().getResource("/image/mailbox.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel lb = new JLabel(new ImageIcon(id));
		lb.setBounds(400, 120, 256, 256);

		JLabel lb2 = new JLabel();
		lb2.setBounds(325, 390, 500, 45);
		lb2.setText("Student information is an empty");
		lb2.setFont(new Font("Roman", Font.PLAIN, 30));
		lb2.setForeground(Color.gray);

		covers = new JPanel();
		covers.setBounds(0, 0, 1045, 690);
		covers.setBackground(Color.white);
		covers.setLayout(null);
		covers.add(lb);
		covers.add(lb2);
		add(covers);

	}

	private void Menues() {
		Databases db = new Databases();
		String classnames[] = null;

		try {

			db.classForname();
			db.classForname();
			db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
			db.st = db.con.createStatement();
			ResultSet rs = db.st.executeQuery("select classname from classrooms");
			int rows = 0;
			String nns = "";
			while (rs.next()) {
				if (!nns.equals(rs.getString("classname").substring(0, rs.getString("classname").indexOf("of")))) {
					rows = rs.getRow();
					nns = rs.getString("classname").substring(0, rs.getString("classname").indexOf("of"));
				}
			}
			classnames = new String[rows];
			ResultSet rs1 = db.st.executeQuery("select classname from classrooms");
			while (rs1.next()) {
				Class = rs1.getString("classname");
				d = rs1.getString("classname").substring(0, rs1.getString("classname").indexOf("of"));
				if (!chs.equals(d)) {
					classnames[rs.getRow() - 1] = d;
					chs = d;
				}
				if (rs.getRow() == 1)
					cl = d;
//				System.out.println(rs1.getString("classname"));
			}
			db.con.close();

		} catch (Exception e) {
			try {
				db.con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
//		
//		for (int i = 0; i < classnames.length;i++) {
//			System.out.println(classnames[i]);
//		}

		lb = new JLabel("CLASS:");
		lb.setBounds(5, 70, 80, 35);
		lb.setForeground(Color.gray);
		lb.setFont(new Font("Calibri", Font.PLAIN, 20));
		panel.add(lb);

		BufferedImage saves = null;
		try {
			saves = ImageIO.read(getClass().getResource("/image/gst1.png"));

		} catch (Exception e) {

		}

		grad = new JButton(" Graduated       ");
		grad.setBackground(new Color(57, 162, 217));
		grad.setBorder(BorderFactory.createSoftBevelBorder(10));
		grad.setBounds(800, 60, 200, 32);
		grad.setForeground(Color.white);
		grad.setIcon(
				new ImageIcon(new ImageIcon(saves).getImage().getScaledInstance(24, 24, Image.SCALE_AREA_AVERAGING)));
		grad.setFont(new Font("Calibri", Font.PLAIN, 20));
		grad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Databases dc1 = new Databases();
				try {

					dc1.classForname();
					dc1.con = DriverManager.getConnection("jdbc:sqlite:students.db");
					dc1.st = dc1.con.createStatement();
					String query = "select Student_name from graduate";
					ResultSet rs = dc1.st.executeQuery(query);
					checker = rs.next();

				} catch (Exception e) {
					System.out.println(e);
				} finally {
					try {
						dc1.con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (checker) {
					container.setVisible(false);

					grads.setVisible(true);

				} else {
					JOptionPane.showMessageDialog(null, "There no graduated Students", "Schmasys",
							JOptionPane.WARNING_MESSAGE);
				}

			}

		});
		panel.add(grad);

		grads.back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				grads.setVisible(false);
				container.setVisible(true);
			}

		});

		box = new JComboBox<String>(classnames);
		box.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
		box.setBounds(5, 100, 350, 35);
		box.setForeground(Color.GRAY);
		box.setFont(new Font("Roman", Font.PLAIN, 16));
		box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl = box.getSelectedItem().toString();
				snn = cl;
				int is = 0;
				;
				int left = 5;
				int bottom = 230;

				try {
					db.classForname();
					db.classForname();
					db.con = DriverManager.getConnection("jdbc:sqlite:students.db");
					db.st = db.con.createStatement();
					ResultSet rs = db.st.executeQuery("select Student_name from student where Student_class like'" + cl
							+ "%' ORDER by Student_name asc");
					int rows = 0;
					while (rs.next()) {
						rows = rs.getRow();
					}

					StudentNames = new String[rows];

					ResultSet rs1 = db.st.executeQuery("select Student_name from student where Student_class like'" + cl
							+ "%' ORDER by Student_name asc");

					while (rs1.next()) {
						StudentNames[rs1.getRow() - 1] = rs1.getString("Student_name");
					}
					Underline.setBounds(5, 192, 100, 10);
					menu[1].setForeground(Color.LIGHT_GRAY);
					menu[1].setFont(new Font("ARIAL", Font.PLAIN, 12));
					menu[0].setForeground(Color.black);
					menu[0].setFont(new Font("ARIAL", Font.BOLD, 15));
					menu[2].setForeground(Color.LIGHT_GRAY);
					menu[2].setFont(new Font("ARIAL", Font.PLAIN, 12));
					menu[3].setForeground(Color.LIGHT_GRAY);
					menu[3].setFont(new Font("ARIAL", Font.PLAIN, 12));
					menu[4].setForeground(Color.LIGHT_GRAY);
					menu[4].setFont(new Font("ARIAL", Font.PLAIN, 12));
					menu[5].setForeground(Color.LIGHT_GRAY);
					menu[5].setFont(new Font("ARIAL", Font.PLAIN, 12));
					menu[6].setForeground(Color.LIGHT_GRAY);
					menu[6].setFont(new Font("ARIAL", Font.PLAIN, 12));
					for (int i = 0; i < ident.length; i++) {
						ident[i].setVisible(false);
					}

					for (int i = 0; i < ident.length; i++) {

						ident[i].setVisible(b);
						if (i < StudentNames.length) {
							try {
								db.classForname();
								db.classForname();
								db.con = DriverManager.getConnection("jdbc:sqlite:students.db");
								db.st = db.con.createStatement();

								ResultSet rss = db.st.executeQuery(
										"select  Student_image,Student_gender,Student_class from student where Student_name='"
												+ StudentNames[i] + "' AND Student_class like'" + cl
												+ "%' ORDER by Student_name asc");
								SN[i].setText(StudentNames[i]);
								SC[i].setText("Class: " + snn);
								rss.next();
								sec = rss.getString("Student_class")
										.substring(rss.getString("Student_class").indexOf("of")).substring(3);
								Se[i].setText("SECTION: " + sec);
								String gender = rss.getString("Student_gender");
								if (rss.getBytes("Student_image") != null)
									SI[i].setIcon(new ImageIcon(
											new ImageIcon(rss.getBytes("Student_image")).getImage().getScaledInstance(
													SI[i].getWidth(), SI[i].getHeight(), Image.SCALE_AREA_AVERAGING)));
								else {

									if (gender.equalsIgnoreCase("female")) {
										BufferedImage im = null;
										try {
											im = ImageIO.read(getClass().getResource("/image/female2.png"));
										} catch (Exception e) {
										}
										ImageIcon imn = new ImageIcon(new ImageIcon(im).getImage().getScaledInstance(
												SI[i].getWidth(), SI[i].getHeight(), Image.SCALE_AREA_AVERAGING));
										SI[i].setIcon(imn);
									} else {
										BufferedImage im = null;
										try {
											im = ImageIO.read(getClass().getResource("/image/male1.png"));
										} catch (Exception e) {
										}
										ImageIcon imn = new ImageIcon(new ImageIcon(im).getImage().getScaledInstance(
												SI[i].getWidth(), SI[i].getHeight(), Image.SCALE_AREA_AVERAGING));
										SI[i].setIcon(imn);
									}

								}

								db.con.close();
							} catch (Exception e) {
								try {
									db.con.close();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}

						}

						if (i % 3 == 0 && i != 0) {
							left = 5;
							bottom += 250;

						}
						ident[i].setBounds(left, bottom, 330, 235);
						if (i == rows) {
							is = bottom;
						}
						if (i >= rows)
							ident[i].setVisible(!b);
						left += 345;

					}

					panel.setPreferredSize(new Dimension(0, is + 300));

					db.con.close();

				} catch (Exception e) {

					try {
						db.con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				try {
					db.classForname();
					db.classForname();
					db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
					db.st = db.con.createStatement();
					ResultSet rs2 = db.st
							.executeQuery("select classname from classrooms where classname like'" + cl + "%'");

					for (int i = 0; i < menu.length; i++) {

						if (i != 0)
							menu[i].setVisible(false);

					}

					while (rs2.next()) {
						String t = rs2.getString("classname").substring(rs2.getString("classname").indexOf("of "));
						String test = t.substring(3);
						if (test.equals("one"))
							menu[1].setVisible(true);
						if (test.equals("two")) {
							menu[1].setVisible(true);
							menu[2].setVisible(true);
						}
						if (test.equals("three")) {
							menu[1].setVisible(true);
							menu[2].setVisible(true);
							menu[3].setVisible(true);
						}
						if (test.equals("four")) {
							menu[1].setVisible(true);
							menu[2].setVisible(true);
							menu[3].setVisible(true);
							menu[4].setVisible(true);
						}
						if (test.equals("five")) {
							menu[1].setVisible(true);
							menu[2].setVisible(true);
							menu[3].setVisible(true);
							menu[4].setVisible(true);
							menu[5].setVisible(true);
						}
						if (test.equals("six")) {
							menu[1].setVisible(true);
							menu[2].setVisible(true);
							menu[3].setVisible(true);
							menu[4].setVisible(true);
							menu[5].setVisible(true);
							menu[6].setVisible(true);
						}
					}

					db.con.close();
				} catch (Exception e) {
					e.printStackTrace();
					try {
						db.con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}

		});
		panel.add(box);
		BufferedImage icons = null;
		String urImage[] = { "/image/pr1.png", "/image/mars11.png", "/image/rb2.png" };
		String mrks[] = { " Student Profile", " Student Marks", " Delete Student" };

		try {
			int down = 15;
			items = new JButton[3];
			for (int i = 0; i < items.length; i++) {
				icons = ImageIO.read(getClass().getResource(urImage[i]));
				items[i] = new JButton(mrks[i]);
				items[i].setBounds(5, down, 125, 35);
				items[i].setFont(new Font("Roman", Font.PLAIN, 12));
				items[i].setForeground(Color.white);
				items[i].setIcon(new ImageIcon(
						new ImageIcon(icons).getImage().getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING)));
				items[i].setOpaque(false);
				items[i].addActionListener(itemses);
				items[i].setBorder(BorderFactory.createSoftBevelBorder(10));
				down += 50;
			}
		} catch (IOException e) {

		}

		int left = 5;

		Underline = new JPanel();
		Underline.setBounds(5, 192, 100, 10);
		Underline.setBackground(new Color(0, 27, 61));

		menu = new JButton[7];
		for (int i = 0; i < menu.length; i++) {
			menu[i] = new JButton(sm[i]);
			menu[i].setBounds(left, 160, 100, 35);
			menu[i].setBackground(Color.white);
			menu[i].setFont(new Font("ARIAL", Font.BOLD, 15));
			menu[i].setBorder(BorderFactory.createSoftBevelBorder(10));
			menu[i].addActionListener(action);
			if (i != 0) {
				menu[i].setForeground(Color.LIGHT_GRAY);
				menu[i].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[i].setVisible(true);
				menu[i].setBounds(left, 160, 150, 35);
			}
			left += 130;

			panel.add(menu[i]);
		}

		sem = new JSeparator();
		sem.setBounds(5, 200, 1000, 1);
		sem.setForeground(Color.LIGHT_GRAY);
		panel.add(Underline);
		panel.add(sem);

		try {
			db.classForname();
			db.classForname();
			db.con = DriverManager.getConnection("jdbc:sqlite:students.db");
			db.st = db.con.createStatement();
			ResultSet rs = db.st.executeQuery("select Student_name from student where Student_class like'" + cl + "%'");
			int rows = 0;
			while (rs.next()) {
				rows = rs.getRow();
			}
			snn = cl;
			StudentNames = new String[rows];

			ResultSet rs1 = db.st.executeQuery(
					"select Student_name from student where Student_class like'" + cl + "%' ORDER by Student_name asc");

			while (rs.next()) {
				StudentNames[rs1.getRow() - 1] = rs1.getString("Student_name");
			}
			menu[1].setForeground(Color.LIGHT_GRAY);
			menu[1].setFont(new Font("ARIAL", Font.PLAIN, 12));
			menu[0].setForeground(Color.black);
			menu[0].setFont(new Font("ARIAL", Font.BOLD, 15));
			menu[2].setForeground(Color.LIGHT_GRAY);
			menu[2].setFont(new Font("ARIAL", Font.PLAIN, 12));
			menu[3].setForeground(Color.LIGHT_GRAY);
			menu[3].setFont(new Font("ARIAL", Font.PLAIN, 12));
			menu[4].setForeground(Color.LIGHT_GRAY);
			menu[4].setFont(new Font("ARIAL", Font.PLAIN, 12));
			menu[5].setForeground(Color.LIGHT_GRAY);
			menu[5].setFont(new Font("ARIAL", Font.PLAIN, 12));
			menu[6].setForeground(Color.LIGHT_GRAY);
			menu[6].setFont(new Font("ARIAL", Font.PLAIN, 12));
			listPanels(rows);

			db.con.close();

		} catch (Exception e) {

			try {
				db.con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		try {
			db.classForname();
			db.classForname();
			db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
			db.st = db.con.createStatement();
			snn = cl;
			ResultSet rs4 = db.st.executeQuery("select classname from classrooms where classname like'" + cl + "%'");
			rs4.next();
			String t1 = rs4.getString("classname").substring(rs4.getString("classname").indexOf("of "));
			String test1 = t1.substring(3);
			sec = test1;

			ResultSet rs2 = db.st.executeQuery("select classname from classrooms where classname like'" + cl + "%'");

			for (int i = 0; i < menu.length; i++) {

				if (i != 0)
					menu[i].setVisible(false);

			}

			while (rs2.next()) {
				String t = rs2.getString("classname").substring(rs2.getString("classname").indexOf("of "));
				String test = t.substring(3);
				if (test.equals("one"))
					menu[1].setVisible(true);
				if (test.equals("two")) {
					menu[1].setVisible(true);
					menu[2].setVisible(true);
				}
				if (test.equals("three")) {
					menu[1].setVisible(true);
					menu[2].setVisible(true);
					menu[3].setVisible(true);
				}
				if (test.equals("four")) {
					menu[1].setVisible(true);
					menu[2].setVisible(true);
					menu[3].setVisible(true);
					menu[4].setVisible(true);
				}
				if (test.equals("five")) {
					menu[1].setVisible(true);
					menu[2].setVisible(true);
					menu[3].setVisible(true);
					menu[4].setVisible(true);
					menu[5].setVisible(true);
				}
				if (test.equals("six")) {
					menu[1].setVisible(true);
					menu[2].setVisible(true);
					menu[3].setVisible(true);
					menu[4].setVisible(true);
					menu[5].setVisible(true);
					menu[6].setVisible(true);
				}
			}

			db.con.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				db.con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		searching();
//		lists();
		getted_Student();

	}

	private void getted_Student() {

		JPanel panes = new JPanel();
		panes.setBounds(0, 0, 718, 548);
		panes.setOpaque(false);
		panes.setLayout(null);
		BufferedImage ims = null;
		try {
			ims = ImageIO.read(getClass().getResource("/image/clos.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		titles = new JLabel();
		titles.setBounds(30, 12, 300, 35);
		titles.setFont(new Font("Roman", Font.BOLD, 20));
		titles.setForeground(Color.white);
		titles.setText("Student information ");
		panes.add(titles);

		close = new JButton();
		close.setBounds(675, 20, 16, 16);
		close.setBorder(BorderFactory.createSoftBevelBorder(10));
		close.setIcon(new ImageIcon(new ImageIcon(ims).getImage().getScaledInstance(close.getWidth(), close.getHeight(),
				Image.SCALE_AREA_AVERAGING)));
		close.setOpaque(false);
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				enablerOrDisabler(true);
				ser.setText("   Search student here");
				ser.setVisible(true);
				serPane.setVisible(false);
				ser.setText("   Search student here");
				ser.setForeground(Color.LIGHT_GRAY);
				BufferedImage tog = null;
				try {
					tog = ImageIO.read(getClass().getResource("/image/search1.png"));

				} catch (Exception e) {

				}
				ch.setIcon(new ImageIcon(new ImageIcon(tog).getImage().getScaledInstance(tog.getWidth(),
						tog.getHeight(), Image.SCALE_AREA_AVERAGING)));

			}

		});
		panes.add(close);

		BufferedImage im = null;
		try {
			im = ImageIO.read(getClass().getResource("/image/immc.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel ll = new JLabel(
				new ImageIcon(new ImageIcon(im).getImage().getScaledInstance(718, 548, Image.SCALE_AREA_AVERAGING)));
		ll.setBounds(0, 0, 718, 548);

		BufferedImage ip = null;
		try {
			ip = ImageIO.read(getClass().getResource("/image/ac.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		iP = new JButton(" Student Profile");
		iP.setBounds(20, 500, 185, 35);
		iP.setBorder(BorderFactory.createSoftBevelBorder(10));
		iP.setOpaque(false);
		iP.setFont(new Font("Roman", Font.PLAIN, 15));
		iP.setForeground(Color.GRAY);
		iP.setIcon(new ImageIcon(new ImageIcon(ip).getImage().getScaledInstance(32, 32, Image.SCALE_AREA_AVERAGING)));
		iP.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String studentName = names;

				Databases db = new Databases();
				try {
					db.classForname();
					db.con = DriverManager.getConnection("jdbc:sqlite:students.db");
					db.st = db.con.createStatement();
					ResultSet rs = db.st.executeQuery(
							"select Student_class from student where  Student_name like'" + studentName + "%'");

					rs.next();
					Class = rs.getString("Student_class");

					db.con.close();

				} catch (Exception e) {
					e.getMessage();
				}
				Student_profile(studentName);

			}

			private void Student_profile(String studentName) {
				String snames = null;
				Databases database = new Databases();
				try {
					String url = "jdbc:sqlite:login.db";
					String data = "select schoolName , logo from loging";
					database.classForname();
					database.con = DriverManager.getConnection(url);
					database.st = database.con.createStatement();
					ResultSet rs = database.st.executeQuery(data);
					rs.next();
					snames = rs.getString("schoolName");

					database.con.close();
				} catch (Exception e) {
					try {
						database.con.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}

				sp.getInfo(studentName.toLowerCase(), Class, snames);
				container.setVisible(false);
				serPane.setVisible(false);
				sp.setVisible(true);

			}

		});
		panes.add(iP);

		BufferedImage iMs = null;
		try {
			iMs = ImageIO.read(getClass().getResource("/image/apl.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		iM = new JButton(" Student Marks");
		iM.setBounds(260, 500, 185, 35);
		iM.setBorder(BorderFactory.createSoftBevelBorder(10));
		iM.setOpaque(false);
		iM.setFont(new Font("Roman", Font.PLAIN, 15));
		iM.setForeground(Color.GRAY);
		iM.setIcon(new ImageIcon(new ImageIcon(iMs).getImage().getScaledInstance(32, 32, Image.SCALE_AREA_AVERAGING)));
		iM.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String studentName = names;

				Student_marks(studentName);

			}

			private void Student_marks(String studentName) {
				String exnames[] = null;
				String danames[] = null;
				Databases db1 = new Databases();
				db1.classForname();
				try {
					db1.con = DriverManager.getConnection("jdbc:sqlite:class.db");
					db1.st = db1.con.createStatement();
					ResultSet rss = db1.st
							.executeQuery("select count(exam),exam , dates from laster ORDER by exam DESC");
					String ex = null;
					String da = null;
					if (rss.next()) {
						exnames = new String[rss.getInt("count(exam)")];
						danames = new String[exnames.length];
						ResultSet rss1 = db1.st.executeQuery("select exam , dates from laster");
						while (rss1.next()) {
							exnames[rss1.getRow() - 1] = rss1.getString("exam");
							danames[rss1.getRow() - 1] = rss1.getString("dates");
						}
					}

					for (int i = exnames.length - 1; i >= 0; i--) {
						if (exnames[i] != null && danames[i] != null) {
							Databases db = new Databases();
							try {

								String url = "jdbc:sqlite:students.db";
								db.classForname();
								db.con = DriverManager.getConnection(url);
								db.st = db.con.createStatement();

								ResultSet rss1 = db.st
										.executeQuery("SELECT classname from records WHERE Student_name ='"
												+ studentName + "' AND ExamDate='" + danames[i] + "' AND Examination='"
												+ exnames[i] + "'");
								if (rss1.next()) {
									ex = exnames[i];
									da = danames[i];
									break;
								}

							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								db.con.close();
							}
						}
					}
					if (!(ex == null && da == null)) {
						Databases db = new Databases();
						try {

							String url = "jdbc:sqlite:students.db";
							db.classForname();
							db.con = DriverManager.getConnection(url);
							db.st = db.con.createStatement();
							ResultSet rss1 = db.st.executeQuery(
									"SELECT classname from records WHERE Student_name ='" + studentName + "'");
							if (rss1.next()) {
								container.setVisible(false);
								serPane.setVisible(false);
								pr.setVisible(true);

								ResultSet rss11 = db.st.executeQuery(
										"SELECT classname from records WHERE Student_name ='" + studentName + "'");
								String classes = rss11.getString("classname");

								pr.getPeramiters(studentName, ex, da, classes);

							} else {
								JOptionPane.showMessageDialog(null, "There no records for this Student", "Schmasys",
										JOptionPane.WARNING_MESSAGE);
							}
						} catch (Exception w) {
							System.out.println(w);
						} finally {
							try {
								db.con.close();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}

					} else {
						JOptionPane.showMessageDialog(null, "no recorded exam", "Schmasys warring",
								JOptionPane.WARNING_MESSAGE);
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
			}

		});

		panes.add(iM);

		BufferedImage id = null;
		try {
			id = ImageIO.read(getClass().getResource("/image/dle2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		iD = new JButton(" Delete Student");
		iD.setBounds(500, 500, 185, 35);
		iD.setBorder(BorderFactory.createSoftBevelBorder(10));
		iD.setOpaque(false);
		iD.setFont(new Font("Roman", Font.PLAIN, 15));
		iD.setForeground(Color.GRAY);
		iD.setIcon(new ImageIcon(new ImageIcon(id).getImage().getScaledInstance(32, 32, Image.SCALE_AREA_AVERAGING)));
		iD.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String studentName = names;
				Student_delete(studentName);
			}

			private void Student_delete(String studentName) {
				int no = JOptionPane.showConfirmDialog(null, "Are you sure to delete this Student", "Schmasys warring",
						JOptionPane.OK_CANCEL_OPTION);
				if (no == 0) {

					try {
						Databases dc1 = new Databases();
						dc1.classForname();
						dc1.con = DriverManager.getConnection("jdbc:sqlite:students.db");
						dc1.st = dc1.con.createStatement();
						String query = "delete from student where Student_name='" + studentName + "'";
						dc1.st.executeUpdate(query);

//						String execute1 = "SELECT  Student_name as NAME FROM student where Student_class='" + Class
//								+ "' ;";
//						ResultSet rs = dc1.st.executeQuery(execute1);

						dc1.con.close();
					} catch (Exception e) {
						System.out.println(e);
					}

				}
			}

		});
		panes.add(iD);

		Simage = new JLabel();
		Simage.setBounds(30, 110, 300, 350);
		Simage.setBorder(BorderFactory.createLineBorder(Color.gray));
		panes.add(Simage);

		Sname = new JLabel();
		Sname.setBounds(350, 155, 350, 35);
		Sname.setFont(new Font("Roman", Font.BOLD, 19));
		Sname.setForeground(Color.gray);
		panes.add(Sname);

		Sclass = new JLabel();
		Sclass.setBounds(345, 240, 350, 30);
		Sclass.setFont(new Font("Roman", Font.PLAIN, 17));
		Sclass.setForeground(Color.gray);
		Sclass.setText("");
		panes.add(Sclass);

		Sphone = new JLabel();
		Sphone.setBounds(345, 315, 350, 30);
		Sphone.setFont(new Font("Roman", Font.PLAIN, 17));
		Sphone.setForeground(Color.gray);
		Sphone.setText("");
		panes.add(Sphone);

		SEmail = new JLabel();
		SEmail.setBounds(345, 390, 350, 30);
		SEmail.setFont(new Font("Roman", Font.PLAIN, 17));
		SEmail.setForeground(Color.gray);
		SEmail.setText("");
		panes.add(SEmail);

		holder = new JPanel();
		holder.setBounds(150, 60, 718, 548);
		holder.setLayout(null);
		holder.setOpaque(false);
		holder.add(panes);
		holder.add(ll);
		serPane.add(holder);
	}

	private void lists() {
		m = new DefaultListModel<ImgNtext>();

		li = new JList<ImgNtext>();
		li.setFont(new Font("Roman", Font.BOLD, 12));
		li.setBounds(0, 5, 570, 25);
		li.setForeground(Color.DARK_GRAY);
		li.setCellRenderer(new Randerer());
		li.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				names = ((ImgNtext) li.getSelectedValue()).getName();
				getStudentInformation(names);
				enablerOrDisabler(false);
				serPane.setVisible(true);
				spane.setVisible(false);
				ser.setVisible(false);
			}

			private void getStudentInformation(String name) {
				Databases db = new Databases();
				try {

					String url = "jdbc:sqlite:students.db";
					db.classForname();
					db.con = DriverManager.getConnection(url);
					db.st = db.con.createStatement();
					ResultSet rs = db.st.executeQuery(
							"SELECT  Student_Email, Student_phone , Student_gender ,Student_class, Student_image from student where Student_name='"
									+ name + "'");
					rs.next();
					String classes = rs.getString("Student_class");
					byte image[] = rs.getBytes("Student_image");
					String gender = rs.getString("Student_gender");
					Sname.setText(name.toUpperCase());
					Sclass.setText("Class: " + classes);
					Sphone.setText("Phone: " + rs.getString("Student_phone"));
					SEmail.setText("Email: " + rs.getString("Student_Email"));

					if (image == null) {
						if (gender.equalsIgnoreCase("female")) {
							BufferedImage im = null;
							try {
								im = ImageIO.read(getClass().getResource("/image/fle.png"));
							} catch (Exception e) {
							}
							ImageIcon imn = new ImageIcon(new ImageIcon(im).getImage().getScaledInstance(
									Simage.getWidth(), Simage.getHeight(), Image.SCALE_AREA_AVERAGING));
							Simage.setIcon(imn);
						} else {
							BufferedImage im = null;
							try {
								im = ImageIO.read(getClass().getResource("/image/mle.png"));
							} catch (Exception e) {
							}
							ImageIcon imn = new ImageIcon(new ImageIcon(im).getImage().getScaledInstance(
									Simage.getWidth(), Simage.getHeight(), Image.SCALE_AREA_AVERAGING));
							Simage.setIcon(imn);
						}
					} else {
						Simage.setIcon(new ImageIcon(new ImageIcon(rs.getBytes("Student_image")).getImage()
								.getScaledInstance(Simage.getWidth(), Simage.getHeight(), Image.SCALE_AREA_AVERAGING)));

					}

				} catch (Exception w) {
					System.out.println(w);
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

		li.setModel(m);

		spane = new JScrollPane(li);
		spane.setBounds(460, 136, 540, 400);
		spane.setBackground(Color.gray);
		spane.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		spane.setVisible(false);

		panel.add(spane);
	}

	private void enablerOrDisabler(boolean b) {

		for (int i = 0; i < menu.length; i++)
			menu[i].setEnabled(b);
		for (int i = 0; i < menus.length; i++)
			menus[i].setEnabled(b);
		for (int i = 0; i < items.length; i++)
			items[i].setEnabled(b);

		box.setEnabled(b);
		ch.setEnabled(b);
		ser.setEnabled(b);
		ser.setEditable(b);

	}

	private void searching() {
//		450, 100, 500, 50
		ser = new JTextField("   Search student here");
		ser.setBounds(500, 100, 500, 35);
		ser.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.DARK_GRAY));
		ser.setForeground(Color.lightGray);
		ser.setFont(new Font("Roman", Font.ROMAN_BASELINE, 14));
		ser.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent arg0) {
				spane.setVisible(true);

				liststudents();

			}
		});

		ser.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent a) {
				if (a.getSource() == ser) {
					if (ser.getText().equals("   Search student here") || ser.getText().length() == 0) {
						ser.setText("");
						ser.setForeground(Color.black);

						BufferedImage tog = null;
						try {
							tog = ImageIO.read(getClass().getResource("/image/left.png"));
							ch.setIcon(new ImageIcon(new ImageIcon(tog).getImage().getScaledInstance(tog.getWidth(),
									tog.getHeight(), Image.SCALE_AREA_AVERAGING)));

						} catch (Exception e) {

						}

					}
				}

			}

			public void focusLost(FocusEvent a) {
				if (a.getSource() == ser) {
					if (ser.getText().equals("   Search student here") || ser.getText().length() == 0) {
						ser.setText("   Search student here");
						ser.setForeground(Color.LIGHT_GRAY);
						BufferedImage tog = null;
						try {
							tog = ImageIO.read(getClass().getResource("/image/search1.png"));

						} catch (Exception e) {

						}
						ch.setIcon(new ImageIcon(new ImageIcon(tog).getImage().getScaledInstance(tog.getWidth(),
								tog.getHeight(), Image.SCALE_AREA_AVERAGING)));

					}
				}

			}

		});

		panel.add(ser);

		BufferedImage tog = null;
		try {
			tog = ImageIO.read(getClass().getResource("/image/search1.png"));

		} catch (Exception e) {

		}
//30, 5, 296 - 30, 36
//		450, 100, 500, 40
		ch = new JToggleButton();
		ch.setBounds(460, 100, 40, 35);
		ch.setIcon(new ImageIcon(new ImageIcon(tog).getImage().getScaledInstance(tog.getWidth(), tog.getHeight(),
				Image.SCALE_AREA_AVERAGING)));
		ch.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.DARK_GRAY));
		ch.setBackground(Color.white);
		ch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ser.setText("   Search student here");
				ser.setForeground(Color.LIGHT_GRAY);
				spane.setVisible(false);

				BufferedImage tog = null;
				try {
					tog = ImageIO.read(getClass().getResource("/image/search1.png"));

				} catch (Exception e) {

				}
				ch.setIcon(new ImageIcon(new ImageIcon(tog).getImage().getScaledInstance(tog.getWidth(),
						tog.getHeight(), Image.SCALE_AREA_AVERAGING)));
			}

		});

		panel.add(ch);

	}

	private void liststudents() {

		m.removeAllElements();
		Databases db = new Databases();
		try {
			String url = "jdbc:sqlite:students.db";

			db.classForname();
			db.con = DriverManager.getConnection(url);
			db.st = db.con.createStatement();
			String data = "SELECT Student_name ,Student_Email, Student_image ,Student_gender from student where Student_name like '%"
					+ ser.getText() + "%' ORDER by Student_name asc";
			ResultSet rs = db.st.executeQuery(data);

			while (rs.next()) {
				String get = rs.getString("Student_name");
				String gender = rs.getString("Student_gender");

				byte[] imageByte = rs.getBytes("Student_image");

//				String mai = rs.getString("Student_Email");
//				if (!mai.equals("null")) {

				if (!(ser.getText().length() == 0)) {
					if (imageByte == null) {

						if (gender.equalsIgnoreCase("female")) {
							BufferedImage im = null;
							try {
								im = ImageIO.read(getClass().getResource("/image/female2.png"));
							} catch (Exception e) {
							}
							ImageIcon imn = new ImageIcon(
									new ImageIcon(im).getImage().getScaledInstance(64, 64, Image.SCALE_AREA_AVERAGING));
							m.addElement(new ImgNtext(get, imn));
						} else {
							BufferedImage im = null;
							try {
								im = ImageIO.read(getClass().getResource("/image/male1.png"));
							} catch (Exception e) {
							}
							ImageIcon imn = new ImageIcon(
									new ImageIcon(im).getImage().getScaledInstance(64, 64, Image.SCALE_AREA_AVERAGING));
							m.addElement(new ImgNtext(get, imn));
						}

					} else {
						ImageIcon im = new ImageIcon(new ImageIcon(imageByte).getImage().getScaledInstance(64, 64,
								Image.SCALE_AREA_AVERAGING));
						m.addElement(new ImgNtext(get, im));
					}

				} else {

				}

			}
//			}

		} catch (SQLException e) {
			System.out.println("hey");
		} finally {
			try {
				db.con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private void listPanels(int rows) {
		BufferedImage imm = null;
		try {
			imm = ImageIO.read(getClass().getResource("/image/mne2.png"));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Databases database = new Databases();
		String snames = null;
		byte[] slogo = null;
		try {
			String url = "jdbc:sqlite:login.db";
			String data = "select schoolName , logo from loging";
			database.classForname();
			database.con = DriverManager.getConnection(url);
			database.st = database.con.createStatement();
			ResultSet rs = database.st.executeQuery(data);
			rs.next();
			snames = rs.getString("schoolName");
			school = snames;
			slogo = rs.getBytes("logo");

			database.con.close();
		} catch (Exception e) {
			try {
				database.con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			snames = "Schmasys";

		}

		int is = 0;

		int left = 5;
		int bottom = 230;
		iHeader = new JPanel[300];
		sname = new JLabel[300];
		ident = new JPanel[300];
		SI = new JLabel[300];
		SN = new JLabel[300];
		SC = new JLabel[300];
		Se = new JLabel[300];
		SNO = new JLabel[300];
		menus = new JButton[300];
		menuH = new JPanel[300];

		for (int i = 0; i < ident.length; i++) {
			ident[i] = new JPanel();

			ident[i].setBackground(Color.white);
			ident[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
			ident[i].setLayout(null);
			ident[i].setVisible(b);

			menuH[i] = new JPanel();
			menuH[i].setBounds(180, 15, 148, 170);
			menuH[i].setBackground(new Color(38, 74, 131));
			menuH[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
			menuH[i].setVisible(false);
			menuH[i].setLayout(null);
			ident[i].addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent arg0) {
					for (int i = 0; i < menuH.length; i++) {
						menuH[i].setVisible(false);
						menus[i].setVisible(true);
					}
					menuH[elements].setVisible(false);
					menus[elements].setVisible(true);
				}

			});

			ident[i].add(menuH[i]);

			iHeader[i] = new JPanel();
			iHeader[i].setBounds(1, 1, 328, 49);
			iHeader[i].setBackground(new Color(0, 27, 61));
			iHeader[i].setLayout(null);

			menus[i] = new JButton();
			menus[i].setBounds(292, 7, 32, 32);
			menus[i].setOpaque(false);
			menus[i].setBorder(BorderFactory.createSoftBevelBorder(10));
			menus[i].setIcon(new ImageIcon(new ImageIcon(imm).getImage().getScaledInstance(menus[i].getWidth(),
					menus[i].getHeight(), Image.SCALE_AREA_AVERAGING)));
			menus[i].setText("" + i);
			menus[i].addActionListener(menuses);

			iHeader[i].add(menus[i]);

			sname[i] = new JLabel("SCHMASYS PRO...");
			sname[i].setBounds(15, 15, 330, 28);
			sname[i].setFont(new Font("Roman", Font.BOLD, 15));
			sname[i].setForeground(new Color(244, 240, 240));

			if (!(snames == null)) {
				String n = snames;
				String nt = n.substring(0, n.indexOf(" "));
				String ntt = n.substring(n.indexOf(" ")).substring(1);
				String ntts = ntt.substring(0, ntt.indexOf(" "));
				
				sname[i].setText((nt + " " + ntts).toUpperCase());
				
			}
			personalInfo(i);

			iHeader[i].add(sname[i]);

			ident[i].add(iHeader[i]);

			if (i % 3 == 0 && i != 0) {
				left = 5;
				bottom += 250;

			}
			ident[i].setBounds(left, bottom, 330, 235);
			if (i == rows) {
				is = bottom;
			}
			if (i >= rows)
				ident[i].setVisible(!b);
			left += 345;
			panel.add(ident[i]);

		}

		panel.setPreferredSize(new Dimension(0, is + 300));

	}

	private void personalInfo(int i) {
		Databases db = new Databases();
		SI[i] = new JLabel();
		SI[i].setBounds(5, 55, 140, 175);
		SI[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.lightGray));
		ident[i].add(SI[i]);
		SN[i] = new JLabel();
		SN[i].setBounds(150, 85, 170, 35);
		SN[i].setFont(new Font("Roman", Font.BOLD, 15));
		SN[i].setForeground(Color.gray);
		ident[i].add(SN[i]);

		if (i < StudentNames.length) {
			try {
				db.classForname();
				db.classForname();
				db.con = DriverManager.getConnection("jdbc:sqlite:students.db");
				db.st = db.con.createStatement();

				ResultSet rs = db.st.executeQuery(
						"select  Student_image,Student_class,Student_gender from student where Student_name='"
								+ StudentNames[i] + "' AND Student_class like'" + cl + "%' ORDER by Student_name asc");
				SN[i].setText(StudentNames[i]);
				rs.next();
				sec = rs.getString("Student_class").substring(rs.getString("Student_class").indexOf("of")).substring(3);
				String gender = rs.getString("Student_gender");
				if (rs.getBytes("Student_image") != null)
					SI[i].setIcon(new ImageIcon(new ImageIcon(rs.getBytes("Student_image")).getImage()
							.getScaledInstance(SI[i].getWidth(), SI[i].getHeight(), Image.SCALE_AREA_AVERAGING)));
				else {

					if (gender.equalsIgnoreCase("female")) {
						BufferedImage im = null;
						try {
							im = ImageIO.read(getClass().getResource("/image/female2.png"));
						} catch (Exception e) {
						}
						ImageIcon imn = new ImageIcon(new ImageIcon(im).getImage().getScaledInstance(SI[i].getWidth(),
								SI[i].getHeight(), Image.SCALE_AREA_AVERAGING));
						SI[i].setIcon(imn);
					} else {
						BufferedImage im = null;
						try {
							im = ImageIO.read(getClass().getResource("/image/male1.png"));
						} catch (Exception e) {
						}
						ImageIcon imn = new ImageIcon(new ImageIcon(im).getImage().getScaledInstance(SI[i].getWidth(),
								SI[i].getHeight(), Image.SCALE_AREA_AVERAGING));
						SI[i].setIcon(imn);
					}

				}

				db.con.close();
			} catch (Exception e) {
				try {
					db.con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}

		SC[i] = new JLabel();
		SC[i].setBounds(150, 115, 170, 35);
		SC[i].setFont(new Font("Roman", Font.PLAIN, 15));
		SC[i].setForeground(Color.gray);
		SC[i].setText("Class: " + snn);
		ident[i].add(SC[i]);

		Se[i] = new JLabel();
		Se[i].setBounds(150, 145, 170, 35);
		Se[i].setFont(new Font("Roman", Font.PLAIN, 15));
		Se[i].setForeground(Color.gray);
		Se[i].setText("SECTION: " + sec);
		ident[i].add(Se[i]);

		SNO[i] = new JLabel();
		SNO[i].setBounds(150, 175, 170, 35);
		SNO[i].setFont(new Font("Roman", Font.PLAIN, 15));
		SNO[i].setForeground(Color.gray);
		SNO[i].setText("NO : " + (i + 1));
		ident[i].add(SNO[i]);

	}

	private void titleOptions() {
		BufferedImage img = null;
		try {
			img = ImageIO.read(getClass().getResource("/image/ii.png"));
		} catch (Exception e) {
		}
		tit = new JLabel();
		tit.setBounds(15, 5, 35, 35);
		tit.setIcon(new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(tit.getWidth(), tit.getHeight(),
				Image.SCALE_AREA_AVERAGING)));
		title.add(tit);

		titname = new JLabel("INFORMATIONS");
		titname.setBounds(60, 13, 200, 30);
		titname.setFont(new Font("calibri", Font.BOLD, 20));
		titname.setForeground(Color.LIGHT_GRAY);
		title.add(titname);

	}

	private void container() {

		sp.back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				sp.setVisible(false);
				container.setVisible(true);
				enablerOrDisabler(true);
				ser.setText("   Search student here");
				ser.setVisible(true);
				serPane.setVisible(false);
				ser.setText("   Search student here");
				ser.setForeground(Color.LIGHT_GRAY);
				BufferedImage tog = null;
				try {
					tog = ImageIO.read(getClass().getResource("/image/search1.png"));

				} catch (Exception e) {

				}
				ch.setIcon(new ImageIcon(new ImageIcon(tog).getImage().getScaledInstance(tog.getWidth(),
						tog.getHeight(), Image.SCALE_AREA_AVERAGING)));

			}

		});

		pr.logo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				pr.setVisible(false);
				container.setVisible(true);

				enablerOrDisabler(true);
				ser.setText("   Search student here");
				ser.setVisible(true);
				serPane.setVisible(false);
				ser.setText("   Search student here");
				ser.setForeground(Color.LIGHT_GRAY);
				BufferedImage tog = null;
				try {
					tog = ImageIO.read(getClass().getResource("/image/search1.png"));

				} catch (Exception g) {

				}
				ch.setIcon(new ImageIcon(new ImageIcon(tog).getImage().getScaledInstance(tog.getWidth(),
						tog.getHeight(), Image.SCALE_AREA_AVERAGING)));

			}
		});

		title = new JPanel();
		title.setBackground(new Color(27, 102, 140));
		title.setBounds(5, 5, 1020, 45);
		title.setLayout(null);

		panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(0, 730));
		panel.add(title);

		Cover = new JPanel();
		Cover.setBackground(new Color(0, 27, 61));
		Cover.setBounds(0, 0, 1045, 690);
		Cover.setVisible(true);
		Cover.setLayout(null);
		add(Cover);

		JLabel lb = new JLabel();
		lb.setBounds(400, 250, 400, 200);
		lb.setForeground(Color.white);
		lb.setFont(new Font("Roman", Font.BOLD, 32));

		ttt = new Timer(500, new ActionListener() {
			int tss = 0;
			String[] cs = { " ", " . ", " . .", " . . ." };

			public void actionPerformed(ActionEvent arg0) {

				if (tss == cs.length)
					tss = 0;
				lb.setText("PLEASE WAIT" + cs[tss]);
				tss++;
			}
		});
		ttt.start();
		Cover.add(lb);

		serPane = new JPanel();
		serPane.setBounds(0, 0, 1049, 690);
		serPane.setVisible(false);
		serPane.setBackground(new Color(0, 27, 61, 160));
		serPane.setLayout(null);
		add(serPane);

		container = new JScrollPane(panel);
		container.setBounds(0, 0, 1049, 690);
		container.setBorder(BorderFactory.createSoftBevelBorder(10));
		container.setVisible(false);
		add(container);
		add(sp);
		add(grads);
		add(pr);

	}

	private void design() {
		setVisible(false);
		setLayout(null);
		setBackground(Color.white);
		setBounds(280, 30, 1045, 690);
	}

	private class actionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			cl = box.getSelectedItem().toString();
			if (arg0.getSource() == menu[0]) {
				Underline.setBounds(5, 192, 100, 10);
				menu[1].setForeground(Color.LIGHT_GRAY);
				menu[1].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[0].setForeground(Color.black);
				menu[0].setFont(new Font("ARIAL", Font.BOLD, 15));
				menu[2].setForeground(Color.LIGHT_GRAY);
				menu[2].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[3].setForeground(Color.LIGHT_GRAY);
				menu[3].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[4].setForeground(Color.LIGHT_GRAY);
				menu[4].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[5].setForeground(Color.LIGHT_GRAY);
				menu[5].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[6].setForeground(Color.LIGHT_GRAY);
				menu[6].setFont(new Font("ARIAL", Font.PLAIN, 12));
				prafed(cl);

			}

			else if (arg0.getSource() == menu[1]) {
				Underline.setBounds(165, 192, 100, 10);
				menu[0].setForeground(Color.LIGHT_GRAY);
				menu[0].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[1].setForeground(Color.black);
				menu[1].setFont(new Font("ARIAL", Font.BOLD, 15));
				menu[2].setForeground(Color.LIGHT_GRAY);
				menu[2].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[3].setForeground(Color.LIGHT_GRAY);
				menu[3].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[4].setForeground(Color.LIGHT_GRAY);
				menu[4].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[5].setForeground(Color.LIGHT_GRAY);
				menu[5].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[6].setForeground(Color.LIGHT_GRAY);
				menu[6].setFont(new Font("ARIAL", Font.PLAIN, 12));
				prafed(cl + "of one");

			} else if (arg0.getSource() == menu[2]) {
				Underline.setBounds(295, 192, 100, 10);
				menu[0].setForeground(Color.LIGHT_GRAY);
				menu[0].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[2].setForeground(Color.black);
				menu[2].setFont(new Font("ARIAL", Font.BOLD, 15));
				menu[1].setForeground(Color.LIGHT_GRAY);
				menu[1].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[3].setForeground(Color.LIGHT_GRAY);
				menu[3].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[4].setForeground(Color.LIGHT_GRAY);
				menu[4].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[5].setForeground(Color.LIGHT_GRAY);
				menu[5].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[6].setForeground(Color.LIGHT_GRAY);
				menu[6].setFont(new Font("ARIAL", Font.PLAIN, 12));
				prafed(cl + "of two");
			} else if (arg0.getSource() == menu[3]) {
				Underline.setBounds(425, 192, 100, 10);
				menu[0].setForeground(Color.LIGHT_GRAY);
				menu[0].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[3].setForeground(Color.black);
				menu[3].setFont(new Font("ARIAL", Font.BOLD, 15));
				menu[2].setForeground(Color.LIGHT_GRAY);
				menu[2].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[1].setForeground(Color.LIGHT_GRAY);
				menu[1].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[4].setForeground(Color.LIGHT_GRAY);
				menu[4].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[5].setForeground(Color.LIGHT_GRAY);
				menu[5].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[6].setForeground(Color.LIGHT_GRAY);
				menu[6].setFont(new Font("ARIAL", Font.PLAIN, 12));
				prafed(cl + "of three");
			} else if (arg0.getSource() == menu[4]) {
				Underline.setBounds(555, 192, 100, 10);
				menu[0].setForeground(Color.LIGHT_GRAY);
				menu[0].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[4].setForeground(Color.black);
				menu[4].setFont(new Font("ARIAL", Font.BOLD, 15));
				menu[2].setForeground(Color.LIGHT_GRAY);
				menu[2].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[3].setForeground(Color.LIGHT_GRAY);
				menu[3].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[1].setForeground(Color.LIGHT_GRAY);
				menu[1].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[5].setForeground(Color.LIGHT_GRAY);
				menu[5].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[6].setForeground(Color.LIGHT_GRAY);
				menu[6].setFont(new Font("ARIAL", Font.PLAIN, 12));
				prafed(cl + "of four");
			} else if (arg0.getSource() == menu[5]) {
				Underline.setBounds(685, 192, 100, 10);
				menu[0].setForeground(Color.LIGHT_GRAY);
				menu[0].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[5].setForeground(Color.black);
				menu[5].setFont(new Font("ARIAL", Font.BOLD, 15));
				menu[2].setForeground(Color.LIGHT_GRAY);
				menu[2].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[3].setForeground(Color.LIGHT_GRAY);
				menu[3].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[4].setForeground(Color.LIGHT_GRAY);
				menu[4].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[1].setForeground(Color.LIGHT_GRAY);
				menu[1].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[6].setForeground(Color.LIGHT_GRAY);
				menu[6].setFont(new Font("ARIAL", Font.PLAIN, 12));
				prafed(cl + "of five");
			} else if (arg0.getSource() == menu[6]) {
				Underline.setBounds(815, 192, 100, 10);
				menu[0].setForeground(Color.LIGHT_GRAY);
				menu[0].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[6].setForeground(Color.black);
				menu[6].setFont(new Font("ARIAL", Font.BOLD, 15));
				menu[2].setForeground(Color.LIGHT_GRAY);
				menu[2].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[3].setForeground(Color.LIGHT_GRAY);
				menu[3].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[4].setForeground(Color.LIGHT_GRAY);
				menu[4].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[5].setForeground(Color.LIGHT_GRAY);
				menu[5].setFont(new Font("ARIAL", Font.PLAIN, 12));
				menu[1].setForeground(Color.LIGHT_GRAY);
				menu[1].setFont(new Font("ARIAL", Font.PLAIN, 12));
				prafed(cl + "of six");
			}

		}

		private void prafed(String string) {

			Databases db = new Databases();
			String cla = string;

			int is = 0;
			;
			int left = 5;
			int bottom = 230;

			try {

				db.classForname();
				db.con = DriverManager.getConnection("jdbc:sqlite:students.db");
				db.st = db.con.createStatement();
				ResultSet rs = db.st.executeQuery("select Student_name from student where Student_class like'" + cla
						+ "%' ORDER by Student_name asc");
				int rows = 0;
				while (rs.next()) {
					rows = rs.getRow();
				}

				StudentNames = new String[rows];

				ResultSet rs1 = db.st.executeQuery("select Student_name from student where Student_class like'" + cla
						+ "%' ORDER by Student_name asc");

				while (rs1.next()) {
					StudentNames[rs1.getRow() - 1] = rs1.getString("Student_name");
				}
				menu[0].setForeground(Color.black);
				for (int i = 0; i < ident.length; i++) {
					ident[i].setVisible(false);
				}

				for (int i = 0; i < ident.length; i++) {

					ident[i].setVisible(b);

					if (i < StudentNames.length) {
						try {
							db.classForname();
							db.classForname();
							db.con = DriverManager.getConnection("jdbc:sqlite:students.db");
							db.st = db.con.createStatement();

							ResultSet rss = db.st.executeQuery(
									"select  Student_image,Student_gender from student where Student_name='"
											+ StudentNames[i] + "' AND Student_class like'" + cla
											+ "%' ORDER by Student_name asc");
							SN[i].setText(StudentNames[i]);
							rss.next();

							String gender = rss.getString("Student_gender");
							byte image[] = rss.getBytes("Student_image");

							if (image != null)
								SI[i].setIcon(new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(
										SI[i].getWidth(), SI[i].getHeight(), Image.SCALE_AREA_AVERAGING)));
							else {

								if (gender.equalsIgnoreCase("female")) {
									BufferedImage im = null;
									try {
										im = ImageIO.read(getClass().getResource("/image/female2.png"));
									} catch (Exception e) {
									}
									ImageIcon imn = new ImageIcon(new ImageIcon(im).getImage().getScaledInstance(
											SI[i].getWidth(), SI[i].getHeight(), Image.SCALE_AREA_AVERAGING));
									SI[i].setIcon(imn);
								} else {
									BufferedImage im = null;
									try {
										im = ImageIO.read(getClass().getResource("/image/male1.png"));
									} catch (Exception e) {
									}
									ImageIcon imn = new ImageIcon(new ImageIcon(im).getImage().getScaledInstance(
											SI[i].getWidth(), SI[i].getHeight(), Image.SCALE_AREA_AVERAGING));
									SI[i].setIcon(imn);
								}

							}

							db.con.close();
						} catch (Exception e) {
							try {
								db.con.close();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}

					}

					if (i % 3 == 0 && i != 0) {
						left = 5;
						bottom += 250;

					}
					ident[i].setBounds(left, bottom, 330, 235);
					if (i == rows) {
						is = bottom;
					}
					if (i >= rows)
						ident[i].setVisible(!b);
					left += 345;

				}

				panel.setPreferredSize(new Dimension(0, is + 300));

				db.con.close();

			} catch (Exception e) {

				try {
					db.con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			try {
				db.classForname();
				db.classForname();
				db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
				db.st = db.con.createStatement();

				ResultSet rs2 = db.st
						.executeQuery("select classname from classrooms where classname like'" + cl + "%'");

				for (int i = 0; i < menu.length; i++) {

					if (i != 0)
						menu[i].setVisible(false);

				}

				while (rs2.next()) {
					String t = rs2.getString("classname").substring(rs2.getString("classname").indexOf("of "));
					String test = t.substring(3);
					if (test.equals("one"))
						menu[1].setVisible(true);
					if (test.equals("two")) {
						menu[1].setVisible(true);
						menu[2].setVisible(true);
					}
					if (test.equals("three")) {
						menu[1].setVisible(true);
						menu[2].setVisible(true);
						menu[3].setVisible(true);
					}
					if (test.equals("four")) {
						menu[1].setVisible(true);
						menu[2].setVisible(true);
						menu[3].setVisible(true);
						menu[4].setVisible(true);
					}
					if (test.equals("five")) {
						menu[1].setVisible(true);
						menu[2].setVisible(true);
						menu[3].setVisible(true);
						menu[4].setVisible(true);
						menu[5].setVisible(true);
					}
					if (test.equals("six")) {
						menu[1].setVisible(true);
						menu[2].setVisible(true);
						menu[3].setVisible(true);
						menu[4].setVisible(true);
						menu[5].setVisible(true);
						menu[6].setVisible(true);
					}
				}

				db.con.close();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					db.con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}

	}

	private class menuListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			elements = Integer.parseInt(arg0.getActionCommand().intern());

			for (int i = 0; i < menuH.length; i++) {
				menuH[i].setVisible(false);
				menus[i].setVisible(true);
			}

			for (int i = 0; i < items.length; i++) {
				menuH[elements].add(items[i]);
			}
			menuH[elements].setVisible(true);
			menus[elements].setVisible(false);

		}

	}

	private class itemListener implements ActionListener {

		public void actionPerformed(ActionEvent a) {
			String studentName = SN[elements].getText();
			Databases db = new Databases();
			try {
				db.classForname();
				db.con = DriverManager.getConnection("jdbc:sqlite:students.db");
				db.st = db.con.createStatement();
				ResultSet rs = db.st.executeQuery("select Student_class from student where  Student_name like'"
						+ studentName + "%' ORDER by Student_name asc");

				rs.next();
				Class = rs.getString("Student_class");

				db.con.close();

			} catch (Exception e) {
				e.getMessage();
			}

			menuH[elements].setVisible(false);
			menus[elements].setVisible(true);
			if (a.getSource() == items[0]) {
				Student_profile(studentName);
			}
			if (a.getSource() == items[1]) {
				Student_marks(studentName);
			}
			if (a.getSource() == items[2]) {
				Student_delete(studentName);
			}

		}

		private void Student_delete(String studentName) {
			int no = JOptionPane.showConfirmDialog(null, "Are you sure to delete this Student", "Schmasys warring",
					JOptionPane.OK_CANCEL_OPTION);
			if (no == 0) {
				Databases dc1 = new Databases();
				try {

					dc1.classForname();
					dc1.con = DriverManager.getConnection("jdbc:sqlite:trash.db");
					dc1.st = dc1.con.createStatement();
					String names = studentName;
					Date d = new Date();
					SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss a   dd_MMM_yyyy");
					String ss = s.format(d);
					dc1.st.executeUpdate("insert into folder values('" + names + "',1,'" + ss + "')");
					executersThenInserters(names);

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						dc1.con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}

		private void executersThenInserters(String object) {
			Databases dc2 = new Databases();
			try {

				dc2.classForname();
				dc2.con = DriverManager.getConnection("jdbc:sqlite:students.db");
				dc2.st = dc2.con.createStatement();
				String query = "select Mother_name,Student_phone,Parent_phone,Student_Email,Student_class,Student_address,Birth_Date,Location_Birth,Student_gender,Student_image from student where Student_name='"
						+ object + "' AND Student_class='" + Class + "'";

				ResultSet rs;

				rs = dc2.st.executeQuery(query);
				if (rs.next()) {
					ResultSet rs1 = dc2.st.executeQuery(query);
					String name = null;
					while (rs1.next()) {
						name = object;
						String mother = rs1.getString("Mother_name");
						String phone = rs1.getString("Student_phone");
						image = rs1.getBytes("Student_image");
						String Email = rs1.getString("Student_Email");
						String Location = rs1.getString("Location_Birth");
						String datebirth = rs1.getString("Birth_Date");
						String gender = rs1.getString("Student_gender");
						String Address = rs1.getString("Student_address");
						String Pphone = rs1.getString("Parent_phone");
						String insert = "insert into fromStudent (Student_name,Mother_name,Student_phone,Student_image,"
								+ "Student_Email,Location_Birth,Birth_Date,"
								+ "Student_gender,Student_address, Parent_phone,Student_class ) values ('" + name
								+ "','" + mother + "','" + phone + "',?,'" + Email + "','" + Location + "','"
								+ datebirth + "','" + gender + "','" + Address + "','" + Pphone + "','"
								+ rs1.getString("Student_class") + "')";
						insertTrash(insert);
					}
					deleters(name, Class);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					dc2.con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		private void insertTrash(String insert) {
			Databases dc3 = new Databases();
			try {

				dc3.classForname();
				dc3.con = DriverManager.getConnection("jdbc:sqlite:trash.db");
				dc3.st = dc3.con.createStatement();

				PreparedStatement pr = dc3.con.prepareStatement(insert);
				pr.setBytes(1, image);
				pr.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					dc3.con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		private void deleters(String name, String object) {
			Databases dc2 = new Databases();
			try {

				dc2.classForname();
				dc2.con = DriverManager.getConnection("jdbc:sqlite:students.db");
				dc2.st = dc2.con.createStatement();

				String query = "delete from student where Student_name='" + name + "' AND Student_class like'" + object
						+ "%'";
				dc2.st.executeUpdate(query);

				query = "update records set deleted = 1 where Student_name='" + name + "' AND classname like'" + object
						+ "%' ";
				dc2.st.executeUpdate(query);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					dc2.con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		private void Student_marks(String studentName) {
			String exnames[] = null;
			String danames[] = null;
			Databases db1 = new Databases();
			db1.classForname();
			try {
				db1.con = DriverManager.getConnection("jdbc:sqlite:class.db");
				db1.st = db1.con.createStatement();
				ResultSet rss = db1.st.executeQuery("select count(exam),exam , dates from laster ORDER by exam DESC");
				String ex = null;
				String da = null;
				if (rss.next()) {
					exnames = new String[rss.getInt("count(exam)")];
					danames = new String[exnames.length];
					ResultSet rss1 = db1.st.executeQuery("select exam , dates from laster");
					while (rss1.next()) {
						exnames[rss1.getRow() - 1] = rss1.getString("exam");
						danames[rss1.getRow() - 1] = rss1.getString("dates");
					}
				}

				for (int i = exnames.length - 1; i >= 0; i--) {
					if (exnames[i] != null && danames[i] != null) {
						Databases db = new Databases();
						try {

							String url = "jdbc:sqlite:students.db";
							db.classForname();
							db.con = DriverManager.getConnection(url);
							db.st = db.con.createStatement();

							ResultSet rss1 = db.st.executeQuery("SELECT classname from records WHERE Student_name ='"
									+ studentName + "' AND ExamDate='" + danames[i] + "' AND Examination='" + exnames[i]
									+ "'");
							if (rss1.next()) {
								ex = exnames[i];
								da = danames[i];
								break;
							}

						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							db.con.close();
						}
					}
				}
				if (!(ex == null && da == null)) {
					Databases db = new Databases();
					try {

						String url = "jdbc:sqlite:students.db";
						db.classForname();
						db.con = DriverManager.getConnection(url);
						db.st = db.con.createStatement();
						ResultSet rss1 = db.st.executeQuery(
								"SELECT classname from records WHERE Student_name ='" + studentName + "'");
						if (rss1.next()) {
							pr.setVisible(true);
							container.setVisible(false);

							ResultSet rss11 = db.st.executeQuery(
									"SELECT classname from records WHERE Student_name ='" + studentName + "'");
							String classes = rss11.getString("classname");

							pr.getPeramiters(studentName, ex, da, classes);

						} else {
							JOptionPane.showMessageDialog(null, "There no records for this Student", "Schmasys",
									JOptionPane.WARNING_MESSAGE);
						}
					} catch (Exception w) {
						System.out.println(w);
					} finally {
						try {
							db.con.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				} else {
					JOptionPane.showMessageDialog(null, "no recorded exam", "Schmasys warring",
							JOptionPane.WARNING_MESSAGE);
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
		}

		private void Student_profile(String studentName) {

			sp.getInfo(studentName, Class, school);
			container.setVisible(false);
			sp.setVisible(true);

		}

	}
}
