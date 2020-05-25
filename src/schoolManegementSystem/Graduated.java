package schoolManegementSystem;

import java.awt.Color;
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

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.Timer;

public class Graduated extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JScrollPane container;
	JButton back;
	private JComboBox<String> box;
	private JLabel Label;
	private JPanel[] ident;
	private JPanel[] iHeader;

	private JLabel sname[];
	private JLabel SI[], SN[], SC[], SNO[], Se[];
	private String StudentNames[];
	private JButton menus[];

	private JPanel[] menuH;
	private String dates;
	private int elements;
	private JButton items[];
	private String school;
	private JLabel Simage, Sname, Sclass, Sphone, SEmail, titles;
	private JButton close, iP, iM;
	private JToggleButton ch;
	private JTextField ser;
	private JPanel serPane;
	private String names;

	private DefaultListModel<ImgNtext> m;
	private JList<ImgNtext> li;
	private JScrollPane spane;

	private JPanel holder;

	GraduatedProfiles gp = new GraduatedProfiles();
	GraduatedOldMarks gom = new GraduatedOldMarks();
	menuListener menuses = new menuListener();
	itemListener itemses = new itemListener();

	public Graduated() {
		design();	
		containers();
	boolean checker = false;
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
		if(checker) {
		Thread tt = new Thread() {
			public void run() {
			
				lists();
				components();
				searching();
				getted_Student();

			}

		};
		tt.start();
		}
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
				Student_profile(studentName);

			}

			private void Student_profile(String studentName) {

				gp.getInfo(studentName, dates, school);
				serPane.setVisible(false);
				gp.setVisible(true);

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
												+ exnames[i] + "'AND deleted >0");
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
							ResultSet rss1 = db.st.executeQuery("SELECT classname from records WHERE Student_name ='"
									+ studentName + "' And deleted > 0");
							if (rss1.next()) {
								gom.setVisible(true);
								serPane.setVisible(false);

								ResultSet rss11 = db.st
										.executeQuery("SELECT classname from records WHERE Student_name ='"
												+ studentName + "' AND deleted >0");
								String classes = rss11.getString("classname");

								gom.getPeramiters(studentName, ex, da, classes);

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
				container.setVisible(false);
				ser.setText("   Search student here");
				ser.setVisible(true);
				serPane.setVisible(false);
				ser.setText("   Search student here");
				ser.setForeground(Color.LIGHT_GRAY);
				spane.setVisible(false);
				BufferedImage tog = null;
				try {
					tog = ImageIO.read(getClass().getResource("/image/search1.png"));

				} catch (Exception e1) {

				}
				ch.setIcon(new ImageIcon(new ImageIcon(tog).getImage().getScaledInstance(tog.getWidth(),
						tog.getHeight(), Image.SCALE_AREA_AVERAGING)));

				serPane.setVisible(true);
			}

			private void getStudentInformation(String name) {
				Databases db = new Databases();
				try {

					String url = "jdbc:sqlite:students.db";
					db.classForname();
					db.con = DriverManager.getConnection(url);
					db.st = db.con.createStatement();
					ResultSet rs = db.st.executeQuery(
							"SELECT  Student_Email, Student_phone , Student_gender ,grad_year, Student_image from graduate where Student_name='"
									+ name + "'");
					rs.next();
					String classes = rs.getString("grad_year");
					byte image[] = rs.getBytes("Student_image");
					String gender = rs.getString("Student_gender");
					Sname.setText(name.toUpperCase());
					Sclass.setText("Graduated: " + classes);
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
					w.printStackTrace();
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
		spane.setBounds(460, 106, 540, 400);
		spane.setBackground(Color.gray);
		spane.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		spane.setVisible(false);

		panel.add(spane);
	}

//
	private void enablerOrDisabler(boolean b) {
//
////		for (int i = 0; i < menu.length; i++)
////			menu[i].setEnabled(b);
////		for (int i = 0; i < menus.length; i++)
////			menus[i].setEnabled(b);
//		for (int i = 0; i < items.length; i++)
//			items[i].setEnabled(b);
//
//		box.setEnabled(b);
//		ch.setEnabled(b);
//		ser.setEnabled(b);
//		ser.setEditable(b);

	}

	private void searching() {
//		450, 100, 500, 50
		ser = new JTextField("   Search student here");
		ser.setBounds(500, 70, 500, 35);
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
		ch.setBounds(460, 70, 40, 35);
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
			String data = "SELECT Student_name ,Student_Email, Student_image ,Student_gender from graduate where Student_name like '%"
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

	private void components() {
		BufferedImage icon = null;
	

		try {
			icon = ImageIO.read(getClass().getResource("/image/hat.png"));

		} catch (Exception e) {

		}
		Label = new JLabel("<html>All graduated student list </html>");
		Label.setBounds(5, 0, 400, 60);
		Label.setForeground(Color.gray);
		Label.setFont(new Font("Roman", Font.PLAIN, 17));
		Label.setIcon(new ImageIcon(icon));
		panel.add(Label);

		String date[] = null;
		Databases data1 = new Databases();
		try {
			data1.classForname();
			data1.con = DriverManager.getConnection("jdbc:sqlite:class.db");
			data1.st = data1.con.createStatement();
			String query = "SELECT dates from laster GROUP by dates ";
			ResultSet rs = data1.st.executeQuery(query);
			int row = 0;
			while (rs.next())
				row = rs.getRow();
			date = new String[row];

			ResultSet rs1 = data1.st.executeQuery(query);
			while (rs1.next()) {
				date[row - rs1.getRow()] = rs1.getString("dates");
				dates = rs1.getString("dates");
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				data1.con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		box = new JComboBox<String>(date);
		box.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
		box.setBounds(15, 70, 270, 25);
		box.setForeground(Color.GRAY);
		box.setFont(new Font("Roman", Font.PLAIN, 16));
		box.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String cl = box.getSelectedItem().toString();

				int is = 0;
				;
				int left = 5;
				int bottom = 230;
				Databases db = new Databases();

				try {
					db.classForname();
					db.classForname();
					db.con = DriverManager.getConnection("jdbc:sqlite:students.db");
					db.st = db.con.createStatement();
					ResultSet rs = db.st.executeQuery("select Student_name from graduate where grad_year like'" + cl
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

					for (int i = 0; i < ident.length; i++) {
						ident[i].setVisible(false);
					}

					for (int i = 0; i < ident.length; i++) {

						ident[i].setVisible(true);
						if (i < StudentNames.length) {
							try {
								db.classForname();
								db.classForname();
								db.con = DriverManager.getConnection("jdbc:sqlite:students.db");
								db.st = db.con.createStatement();

								ResultSet rs2 = db.st.executeQuery(
										"select  Student_image ,Student_phone,grad_year, Student_gender from graduate where Student_name='"
												+ StudentNames[i] + "' ORDER by Student_name asc");
								SN[i].setText(StudentNames[i]);
								rs2.next();
								SC[i].setText("Phone : " + rs2.getString("Student_phone"));
								Se[i].setText("Graduated : " + rs2.getString("grad_year"));
								String gender = rs2.getString("Student_gender");

								if (rs.getBytes("Student_image") != null)
									SI[i].setIcon(new ImageIcon(
											new ImageIcon(rs2.getBytes("Student_image")).getImage().getScaledInstance(
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
							ident[i].setVisible(false);
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

			}

		});
		panel.add(box);

		BufferedImage icons = null;
		String urImage[] = { "/image/pr1.png", "/image/mars11.png" };
		String mrks[] = { " Student Profile", " Student Marks" };

		try {
			int down = 15;
			items = new JButton[2];
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

		getFromGraduated();

	}

	private void getFromGraduated() {

		Databases db = new Databases();
		try {
			db.classForname();
			db.classForname();
			db.con = DriverManager.getConnection("jdbc:sqlite:students.db");
			db.st = db.con.createStatement();
			ResultSet rs = db.st.executeQuery("select Student_name from graduate where grad_year='" + dates + "'");
			int rows = 0;
			while (rs.next()) {
				rows = rs.getRow();
			}

			StudentNames = new String[rows];

			ResultSet rs1 = db.st.executeQuery(
					"select Student_name from graduate where grad_year ='" + dates + "' ORDER by Student_name asc");

			while (rs.next()) {
				StudentNames[rs1.getRow() - 1] = rs1.getString("Student_name");
			}

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

	}

	private void listPanels(int rows) {
		String snames = null;
		String school = null;
		byte slogo[] = null;
		Databases database = new Databases();
		BufferedImage imm = null;
		try {
			imm = ImageIO.read(getClass().getResource("/image/mne2.png"));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
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
			this.school = school;
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
			iHeader[i].setBackground(new Color(57, 162, 217));
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
			sname[i].setBounds(15, 15, 300, 28);
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
				ident[i].setVisible(false);
			left += 345;
			panel.add(ident[i]);

		}

		panel.setPreferredSize(new Dimension(0, is + 300));

	}

	private void personalInfo(int i) {
		String sec = null;
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

		SC[i] = new JLabel();
		SC[i].setBounds(150, 115, 170, 35);
		SC[i].setFont(new Font("Roman", Font.PLAIN, 15));
		SC[i].setForeground(Color.gray);
		SC[i].setText("Phone : ");
		ident[i].add(SC[i]);

		Se[i] = new JLabel();
		Se[i].setBounds(150, 145, 170, 35);
		Se[i].setFont(new Font("Roman", Font.PLAIN, 15));
		Se[i].setForeground(Color.gray);
		Se[i].setText("SECTION: ");
		ident[i].add(Se[i]);

		SNO[i] = new JLabel();
		SNO[i].setBounds(150, 175, 170, 35);
		SNO[i].setFont(new Font("Roman", Font.PLAIN, 15));
		SNO[i].setForeground(Color.gray);
		SNO[i].setText("NO : " + (i + 1));
		ident[i].add(SNO[i]);

		if (i < StudentNames.length) {
			try {
				db.classForname();
				db.classForname();
				db.con = DriverManager.getConnection("jdbc:sqlite:students.db");
				db.st = db.con.createStatement();

				ResultSet rs = db.st.executeQuery(
						"select  Student_image ,Student_phone,grad_year, Student_gender from graduate where Student_name='"
								+ StudentNames[i] + "' ORDER by Student_name asc");
				SN[i].setText(StudentNames[i]);
				rs.next();
				SC[i].setText("Phone : " + rs.getString("Student_phone"));
				Se[i].setText("Graduated : " + rs.getString("grad_year"));
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
				e.printStackTrace();
			} finally {
				try {
					db.con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
//
		}

	}

	private void containers() {

		panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(0, 690));
		
		BufferedImage icon = null;
		try {
			icon = ImageIO.read(getClass().getResource("/image/closer2.png"));

		} catch (Exception e) {

		}

		back = new JButton(new ImageIcon(icon));
		back.setBounds(1000, 5, 32, 32);
		back.setOpaque(false);
		back.setBorder(BorderFactory.createSoftBevelBorder(10));

		panel.add(back);

		container = new JScrollPane(panel);
		container.setBounds(0, 0, 1049, 690);
		container.setBorder(BorderFactory.createSoftBevelBorder(10));
		add(container);
		gp.back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				gp.setVisible(false);
				container.setVisible(true);
			}
		});
		add(gp);
		gom.logo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				gom.setVisible(false);
				container.setVisible(true);
			}
		});
		add(gom);

		serPane = new JPanel();
		serPane.setBounds(0, 0, 1049, 690);
		serPane.setVisible(false);
		serPane.setBackground(new Color(0, 27, 61, 160));
		serPane.setLayout(null);
		add(serPane);
	}

	private void design() {
		setVisible(false);
		setLayout(null);
		setBackground(Color.white);
		setBounds(0, 0, 1045, 690);
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

			menuH[elements].setVisible(false);
			menus[elements].setVisible(true);
			if (a.getSource() == items[0]) {
				Student_profile(studentName);
			}
			if (a.getSource() == items[1]) {
				Student_marks(studentName);
			}
//			if (a.getSource() == items[2]) {
//				Student_delete(studentName);
//			}

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
									+ "'AND deleted >0");
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
						ResultSet rss1 = db.st.executeQuery("SELECT classname from records WHERE Student_name ='"
								+ studentName + "' And deleted > 0");
						if (rss1.next()) {
							gom.setVisible(true);
							container.setVisible(false);

							ResultSet rss11 = db.st.executeQuery("SELECT classname from records WHERE Student_name ='"
									+ studentName + "' AND deleted >0");
							String classes = rss11.getString("classname");

							gom.getPeramiters(studentName, ex, da, classes);

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

			gp.getInfo(studentName, dates, school);
			container.setVisible(false);
			gp.setVisible(true);

		}

	}

}
