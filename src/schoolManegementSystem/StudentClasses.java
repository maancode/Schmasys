package schoolManegementSystem;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.TableColumn;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import net.proteanit.sql.DbUtils;

public class StudentClasses extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel pane[], container;
	private int LLocation[] = { 5, 260, 515, 770, 5, 260, 515, 770, 5, 260, 515, 770 };
	private int BLocation[] = { 18, 18, 18, 18, 240, 240, 240, 240, 465, 465, 465, 465 };
	private JLabel lb2[];
	public JScrollPane sc;
	private Color color[] = { new Color(57, 162, 217, 225), new Color(57, 223, 217, 225), new Color(57, 223, 205, 225),
			new Color(57, 223, 230, 225), new Color(0, 27, 70, 225), new Color(0, 27, 61, 225),
			new Color(0, 27, 60, 225), new Color(0, 27, 55, 225), new Color(28, 18, 41), new Color(43, 18, 68),
			new Color(43, 18, 79), new Color(43, 18, 100) };
	private JButton btn[];
	private String url[] = { "/image/chha.png", "/image/ch3.png", "/image/ch2.png", "/image/ch1.png", "/image/g8.png",
			"/image/g7.png", "/image/g6.png", "/image/g5.png", "/image/g4.png", "/image/g3.png", "/image/g2.png",
			"/image/g1.png" };
	private JPanel border[];
	private String[] getter = new String[72];
	private String[] dg = new String[72];
	private String[] lg = new String[72];
	private static String query;
	private JPopupMenu jp;
	private String nm;
	private int no;
	private byte[] image;
	private int numberss;
	private String subjects[];
	private int no1 = 0;
	private String Classname[] = new String[16];
	public String subj;
	private String sname[];
	private String sclass[];
	private byte[] images;

	actionListener action = new actionListener();
	actionListener1 action1 = new actionListener1();
	tables tb = new tables();
	Classes cl = new Classes();
	StudentProfile sp = new StudentProfile();

	public StudentClasses() {

		Dialog();
		panes();
		Borders();
		design();
		add(sp);

	}

	private void Borders() {
		int Llocation[] = { 0, 0 };
		int Blocation[] = { 0, 220 };
		int ll[] = { 223, 450 };
		String[] str = { "Secondary", "Primary" };
		border = new JPanel[2];
		for (int i = 0; i < border.length; i++) {

			border[i] = new JPanel();
			border[i].setBounds(Llocation[i], Blocation[i], 1030, ll[i]);
			border[i]
					.setBorder(BorderFactory.createTitledBorder(null, str[i], 0, 0, new Font("ARIAL", Font.PLAIN, 15)));
			border[i].setOpaque(false);
			container.add(border[i]);

		}

	}

	private void Dialog() {
		BufferedImage images = null;
		cl.close.addMouseListener(action);
		btn = new JButton[12];
		for (int i = 0; i < btn.length; i++) {

			btn[i] = new JButton();
			btn[i].setBounds(0, 0, 250, 200);
//			btn[i].setText("Button "+(4-i));
			btn[i].setBackground(null);
			btn[i].setBorder(BorderFactory.createSoftBevelBorder(10));

			try {
				images = ImageIO.read(getClass().getResource(url[i]));

			} catch (Exception e) {
				System.out.println(e);
			}

			btn[i].setIcon(new ImageIcon(
					new ImageIcon(images).getImage().getScaledInstance(245, 210, Image.SCALE_AREA_AVERAGING)));

//			btn[i].setIcon(new ImageIcon(images));
			btn[i].addMouseListener(action);

		}

	}

	private void panes() {

		container = new JPanel();
		container.setLayout(null);
		container.setBackground(Color.white);
		container.setOpaque(false);
		container.setPreferredSize(new Dimension(0, 670));

		String labels[] = { "create new class room" };

		lb2 = new JLabel[1];
		for (int i = 0; i < lb2.length; i++) {
			lb2[i] = new JLabel();
			lb2[i].setText(labels[i]);
			lb2[i].setFont(new Font("ARIAL", Font.BOLD, 15));
			lb2[i].setForeground(Color.blue);

		}

		pane = new JPanel[12];
		for (int i = 0; i < pane.length; i++) {
			pane[i] = new JPanel();
			pane[i].setBounds(LLocation[i], BLocation[i], 250, 200);
//			pane[i].setBackground(color[i]);
			pane[i].setLayout(null);
			pane[i].setOpaque(false);
			/*
			 * pane[i].setLayout(null);
			 *
			 */
//			pane[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 3, Color.cyan));

			if (i == 0) {
				pane[i].add(btn[i]);

			} else if (i == 1) {
				pane[i].add(btn[i]);
			} else if (i == 2) {
				pane[i].add(btn[i]);
			} else if (i == 3) {
				pane[i].add(btn[i]);
			} else if (i == 4) {
				pane[i].add(btn[i]);
			} else if (i == 5) {
				pane[i].add(btn[i]);
			} else if (i == 6) {
				pane[i].add(btn[i]);
			} else if (i == 7) {
				pane[i].add(btn[i]);
			} else if (i == 8) {
				pane[i].add(btn[i]);
			} else if (i == 9) {
				pane[i].add(btn[i]);
			} else if (i == 10) {
				pane[i].add(btn[i]);
			} else if (i == 11) {
				pane[i].add(btn[i]);
			}

			container.add(pane[i]);
		}

	}

	private void design() {

		BufferedImage buf = null;
		try {
			buf = ImageIO.read(getClass().getResource("/image/dele.png"));
		} catch (Exception e) {
			System.out.println(e);
		}

		jp = new JPopupMenu();
		jp.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(244, 244, 244)));

		BufferedImage saves = null;
		try {
			saves = ImageIO.read(getClass().getResource("/image/save.png"));

		} catch (Exception e) {

		}
		JMenuItem saver = new JMenuItem(new ImageIcon(saves));
		saver.setText("Save all profile to the pc");
		saver.setFont(new Font("ARIAL", Font.PLAIN, 15));
		saver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String classname = (String) nm.subSequence(0, nm.indexOf("           "));
				Databases db = new Databases();
				String schoolname = null;
				String ss = "\\Schmasys documents";

				try {
					String url = "jdbc:sqlite:login.db";
					String data = "select schoolName from loging";
					db.classForname();
					db.con = DriverManager.getConnection(url);
					db.st = db.con.createStatement();
					ResultSet rs = db.st.executeQuery(data);
					rs.next();
					schoolname = rs.getString("schoolName");

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

				try {
					db.classForname();
					db.con = DriverManager.getConnection("jdbc:sqlite:students.db");
					db.st = db.con.createStatement();
					ResultSet rs = db.st
							.executeQuery("Select Student_name from student where Student_class='" + classname + "'");
					while (rs.next()) {
						String Studentname = rs.getString("Student_name");
						File fileStructure = new File(System.getProperty("user.home") + "\\Documents" + ss);
						String s = fileStructure.getAbsolutePath();
						fileStructure.mkdir();
						sp.getInfo(Studentname, classname, schoolname);
						File subFiles = new File(s + "\\Student profile\\" + classname + " ");
						subFiles.mkdirs();

						File file = new File(subFiles.getAbsolutePath() + "\\" + Studentname + " Profile.pdf");
						FileOutputStream fileout = new FileOutputStream(file);
						writepdf(fileout);

						fileout.close();

					}
					Desktop.getDesktop().open(new File(System.getProperty("user.home") + "\\Documents" + ss));

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

			private void writepdf(FileOutputStream outstream) {
				try {
					Document dc = new Document();
					Rectangle r = new Rectangle(950, 1100);
					dc.setPageSize(r);
					PdfWriter writer;

					writer = PdfWriter.getInstance(dc, outstream);

					dc.open();
					PdfContentByte contentByte = writer.getDirectContent();
					PdfTemplate template = contentByte.createTemplate(sp.getWidth(), sp.getHeight());
					Graphics2D g2 = template.createGraphics(sp.getWidth(), sp.getHeight());
					sp.print(g2);
					g2.dispose();
					contentByte.addTemplate(template, 10, 400);
					dc.close();
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});
		jp.add(saver);

		JMenuItem c = new JMenuItem(
				new ImageIcon(new ImageIcon(buf).getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));
		c.setText("Delete");
		c.setBackground(Color.white);
		c.setFont(new Font("ARIAL", Font.PLAIN, 15));
		c.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.DARK_GRAY));
		jp.add(c);

		KeyStroke ctrl = KeyStroke.getKeyStroke(KeyEvent.VK_D, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
		c.setAccelerator(ctrl);
		c.setMnemonic(KeyEvent.VK_D);

		BufferedImage buf1 = null;
		try {
			buf1 = ImageIO.read(getClass().getResource("/image/dd.png"));
		} catch (Exception e) {
			System.out.println(e);
		}

		JMenuItem ch = new JMenuItem(
				new ImageIcon(new ImageIcon(buf1).getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));
		ch.setText("Change Deen name");
		ch.setFont(new Font("ARIAL", Font.PLAIN, 15));

		ch.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.DARK_GRAY));
		ch.setBackground(Color.white);
		ch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Databases data = new Databases();
				try {

					data.classForname();
					data.con = DriverManager.getConnection("jdbc:sqlite:class.db");
					data.st = data.con.createStatement();
					String query = "SELECT deanname from classrooms where classname ='"
							+ nm.subSequence(0, nm.indexOf("           ")) + "'";
					ResultSet rs = data.st.executeQuery(query);
					rs.next();
					try {
						String update = JOptionPane.showInputDialog(null,
								"Enter the deaner name instead\n :" + rs.getString("deanname") + "", "Schmasys",
								JOptionPane.QUESTION_MESSAGE);
						if (!update.equalsIgnoreCase("") && !(update == null)) {
							data.st.executeUpdate("update classrooms set deanname='" + update + "' where deanname='"
									+ rs.getString("deanname") + "'");

						}
					} catch (Exception e) {

					}

					rs.close();
					data.con.close();
				} catch (Exception e) {
					System.out.println(e);
				}

			}

		});
		jp.add(ch);

		KeyStroke ctrl1 = KeyStroke.getKeyStroke(KeyEvent.VK_T, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
		ch.setAccelerator(ctrl1);
		ch.setMnemonic(KeyEvent.VK_T);

		BufferedImage buf2 = null;
		try {
			buf2 = ImageIO.read(getClass().getResource("/image/lea.png"));
		} catch (Exception e) {
			System.out.println(e);
		}

		JMenuItem ch1 = new JMenuItem(
				new ImageIcon(new ImageIcon(buf2).getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));
		ch1.setText("Change leader name");
		ch1.setFont(new Font("ARIAL", Font.PLAIN, 15));
		ch1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.DARK_GRAY));
		ch1.setBackground(Color.white);
		ch1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Databases data = new Databases();
				try {

					data.classForname();
					data.con = DriverManager.getConnection("jdbc:sqlite:class.db");
					data.st = data.con.createStatement();
					String query = "SELECT leadername from classrooms where classname ='"
							+ nm.subSequence(0, nm.indexOf("           ")) + "'";
					ResultSet rs = data.st.executeQuery(query);
					rs.next();
					try {
						String update = JOptionPane.showInputDialog(null,
								"Enter the leader name instead\n :" + rs.getString("leadername") + "", "Schmasys",
								JOptionPane.QUESTION_MESSAGE);
						if (!update.equalsIgnoreCase("") && !(update == null)) {
							data.st.executeUpdate("update classrooms set leadername='" + update + "' where leadername='"
									+ rs.getString("leadername") + "'");

						}
					} catch (Exception e) {

					}
					rs.close();
					data.con.close();
				} catch (Exception e) {
					System.out.println(e);
				}

			}

		});
		jp.add(ch1);

		KeyStroke ctrl2 = KeyStroke.getKeyStroke(KeyEvent.VK_L, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
		ch1.setAccelerator(ctrl2);
		ch1.setMnemonic(KeyEvent.VK_L);

		c.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				int i = JOptionPane.showConfirmDialog(null, "keep your mind these class and it's Student will be lost",
						"Schmasys warring", JOptionPane.YES_NO_OPTION);

				if (i == 0) {
					String nn = null;
					Databases dc = new Databases();
					try {
						dc.classForname();
						dc.con = DriverManager.getConnection("jdbc:sqlite:class.db");
						dc.st = dc.con.createStatement();
						String classes = nm.substring(0, nm.indexOf("                      "));
						nn = classes;
						dc.st.executeUpdate("delete from classrooms where classname like'" + classes + "%'");

						dc.st.close();
					} catch (Exception e) {
						e.printStackTrace();
					}

					if (((String) nm.subSequence(0, 9)).equalsIgnoreCase("form four")) {
						if (no == 5) {
							cl.FormFour[no].setVisible(false);
						} else {
							cl.FormFour[no].setVisible(false);
						}
					}
					if (((String) nm.subSequence(0, 10)).equalsIgnoreCase("form three")) {
						cl.FormThree[no].setVisible(false);
					}
					if (((String) nm.subSequence(0, 8)).equalsIgnoreCase("form two")) {
						cl.FormTwo[no].setVisible(false);
					}
					if (((String) nm.subSequence(0, 8)).equalsIgnoreCase("form one")) {
						cl.FormOne[no].setVisible(false);
					}
					if (((String) nm.subSequence(0, 11)).equalsIgnoreCase("grade eight")) {
						cl.GradeEight[no].setVisible(false);
					}
					if (((String) nm.subSequence(0, 11)).equalsIgnoreCase("grade seven")) {
						cl.GradeSeven[no].setVisible(false);
					}
					if (((String) nm.subSequence(0, 9)).equalsIgnoreCase("grade six")) {
						cl.GradeSix[no].setVisible(false);
					}
					if (((String) nm.subSequence(0, 10)).equalsIgnoreCase("grade five")) {
						cl.GradeFive[no].setVisible(false);
					}
					if (((String) nm.subSequence(0, 10)).equalsIgnoreCase("grade four")) {
						cl.GradeFour[no].setVisible(false);
					}
					if (((String) nm.subSequence(0, 11)).equalsIgnoreCase("grade three")) {
						cl.GradeThree[no].setVisible(false);
					}
					if (((String) nm.subSequence(0, 9)).equalsIgnoreCase("grade two")) {
						cl.GradeTwo[no].setVisible(false);
					}
					if (((String) nm.subSequence(0, 9)).equalsIgnoreCase("grade one")) {
						cl.GradeOne[no].setVisible(false);
					}
					Databases dc1 = new Databases();
					try {

						String classes = nm.substring(0, nm.indexOf("                      "));
						dc1.classForname();
						dc1.con = DriverManager.getConnection("jdbc:sqlite:trash.db");
						dc1.st = dc1.con.createStatement();
						Date d = new Date();
						SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss a   dd_MMM_yyyy");
						String ss = s.format(d);

						dc1.st.executeUpdate(
								"insert into folder (folderName,dates) values('" + classes + "','" + ss + "')");
						executersThenInserters(classes);

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

			private void executersThenInserters(String classes) {
				String insertH[] = null;
				Databases dc2 = new Databases();
				try {

					dc2.classForname();
					dc2.con = DriverManager.getConnection("jdbc:sqlite:students.db");
					dc2.st = dc2.con.createStatement();
					String query = "select count(), Student_name,Mother_name,Student_phone,Parent_phone,Student_Email,Student_class,Student_address,Birth_Date,Location_Birth,Student_gender,Student_image from student where Student_class='"
							+ classes + "'";

					ResultSet rs;

					rs = dc2.st.executeQuery(query);
					if (rs.next()) {

						sname = new String[rs.getInt("count()")];

						sclass = new String[rs.getInt("count()")];
						insertH = new String[rs.getInt("count()")];
						images = new byte[rs.getInt("count()")];
						String name = null;
						Object ob[] = new Object[rs.getInt("count()")];
						ResultSet rs1 = dc2.st.executeQuery(
								"select Student_name,Mother_name,Student_phone,Parent_phone,Student_Email,Student_class,Student_address,Birth_Date,Location_Birth,Student_gender,Student_image from student where Student_class='"
										+ classes + "'");

						while (rs1.next()) {
							name = rs1.getString("Student_name");
							String mother = rs1.getString("Mother_name");
							String phone = rs1.getString("Student_phone");
							image = rs1.getBytes("Student_image");
							String Email = rs1.getString("Student_Email");
							String Location = rs1.getString("Location_Birth");
							String datebirth = rs1.getString("Birth_Date");
							String gender = rs1.getString("Student_gender");
							String Address = rs1.getString("Student_address");
							String Pphone = rs1.getString("Parent_phone");
							String classser = rs1.getString("Student_class");
							String insert = "insert into fromStudent (Student_name,Mother_name,Student_phone,Student_image,"
									+ "Student_Email,Location_Birth,Birth_Date,"
									+ "Student_gender,Student_address, Parent_phone,Student_class ) values ('" + name
									+ "','" + mother + "','" + phone + "',?,'" + Email + "','" + Location + "','"
									+ datebirth + "','" + gender + "','" + Address + "','" + Pphone + "','" + classser
									+ "')";
							insertH[rs1.getRow() - 1] = insert;
							sname[rs1.getRow() - 1] = name;
							sclass[rs1.getRow() - 1] = classes;
							ob[rs1.getRow() - 1] = image;

						}
						for (int i = 0; i < sname.length; i++) {
							insertTrash(insertH[i], sname[i], sclass[i], ob[i]);
							deleters(sname[i], sclass[i]);
							System.out.println();
						}
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

			private void insertTrash(String insert, String name, String classes, Object ob) {
				Databases dc3 = new Databases();
				image = (byte[]) ob;
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

			private void deleters(String name, String classes) {
				Databases dc2 = new Databases();

				try {

					dc2.classForname();
					dc2.con = DriverManager.getConnection("jdbc:sqlite:students.db");
					dc2.st = dc2.con.createStatement();

					String query = "delete from student where Student_name='" + name + "' AND Student_class like'"
							+ classes + "%'";
					dc2.st.executeUpdate(query);

					query = "update records set deleted = 1 where Student_name='" + name + "' AND classname like'"
							+ classes + "%' ";
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

		});

		add(cl);
		tb.btn.addActionListener(action1);
		add(tb);
		sc = new JScrollPane(container);
		sc.setBounds(5, 0, 1040, 670);
		sc.setForeground(Color.white);
		sc.setBorder(BorderFactory.createSoftBevelBorder(10));
		add(sc);

		setBounds(280, 30, 1045, 690);
		setBackground(Color.white);
		setVisible(false);
		setLayout(null);
	}

//	private class saving extends Thread {
//		String ss = "\\Schmasys documents";
//		Databases data = new Databases();
//
//		public void run() {
//			for (int i = 0; i <= bar.getMaximum() - 1; i++) {
//				try {
//					data.classForname();
//					data.con = DriverManager.getConnection("jdbc:sqlite:students.db");
//					data.st = data.con.createStatement();
//
//					String Student_name_helder = (String) tb.getValueAt(i, 1);
//					String query1 = "Select Student_name from records where Student_name='" + Student_name_helder
//							+ "' AND classname='" + Classer + "';";
//
//					ResultSet rs1 = data.st.executeQuery(query1);
//					if (rs1.next()) {
//						pm.getPeramiters(Student_name_helder, Examer, Dater, Classer);
//
//						File fileStructure = new File(
//								System.getProperty("user.home") + "\\Documents"+ss);
//						String s = fileStructure.getAbsolutePath();
//						fileStructure.mkdir();
//
//						File subFiles = new File(s + "\\" + Classer + "");
//						subFiles.mkdirs();
//
//						File file = new File(subFiles.getAbsolutePath() + "\\" + Student_name_helder + " Marks.pdf");
//						FileOutputStream fileout = new FileOutputStream(file);
//						writepdf(fileout);
//
//						fileout.close();
//						bar.setValue(i + 1);
//						
//						if(i == bar.getMaximum() - 1)
//							Desktop.getDesktop().open(new File(System.getProperty("user.home") + "\\Documents" + ss));
//					} else {
//						JOptionPane.showMessageDialog(null, "There no records for this Student", "Schmasys",
//								JOptionPane.WARNING_MESSAGE);
//					}
//					if (i == bar.getMaximum() - 1) {
//						
//						bar.setVisible(!true);
//						bar.setString(null);
//						sendEmail.setVisible(!false);
//						print.setVisible(!false);
//						save.setVisible(!false);
//						break;
//					}
//
//					data.con.close();
//				} catch (Exception h) {
//					System.out.println(h);
//				}finally {
//					try {
//						data.con.close();
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//
//			}
//
//		}
//
//	
//
//	}

	public class actionListener implements MouseListener {
		actionListener1 action = new actionListener1();

		String s = "hello i\'m working";
		int ns = 0;
		String Class[] = new String[6];

		public void mouseClicked(MouseEvent arg0) {
			for (int i = 0; i < cl.FormFour.length; i++) {
				cl.FormFour[i].setVisible(false);
				cl.FormThree[i].setVisible(false);
				cl.FormTwo[i].setVisible(false);
				cl.FormOne[i].setVisible(false);
				cl.GradeEight[i].setVisible(false);
				cl.GradeSeven[i].setVisible(false);
				cl.GradeSix[i].setVisible(false);
				cl.GradeFive[i].setVisible(false);
				cl.GradeFour[i].setVisible(false);
				cl.GradeThree[i].setVisible(false);
				cl.GradeTwo[i].setVisible(false);
				cl.GradeOne[i].setVisible(false);

			}

			Databases data = new Databases();
			try {
				data.classForname();
				data.con = DriverManager.getConnection("jdbc:sqlite:class.db");
				data.st = data.con.createStatement();

				if (arg0.getSource() == btn[0]) {

					cl.setVisible(true);

					cl.four.setVisible(true);
					cl.three.setVisible(false);
					cl.two.setVisible(false);
					cl.one.setVisible(false);
					cl.eight.setVisible(false);
					cl.seven.setVisible(false);
					cl.six.setVisible(false);
					cl.five.setVisible(false);
					cl.Four.setVisible(false);
					cl.Three.setVisible(false);
					cl.Two.setVisible(false);
					cl.One.setVisible(false);

					container.setVisible(false);

					try {

						String query = "select classname , deanname , leadername from classrooms where classname like '%form f%'";
						PreparedStatement ps = data.con.prepareStatement(query);
						ResultSet rs = ps.executeQuery();
						while (rs.next()) {
							int i = rs.getRow();
							String str = rs.getString(1);
							String str1 = rs.getString("deanname");
							String str2 = rs.getString("leadername");
							if (i == 1) {
								cl.FormFour[i - 1].setVisible(true);
								cl.FormFour[i - 1].setText(
										str + "                                                                ");
								cl.FormFour[i - 1].addActionListener(action1);
								Class[0] = cl.FormFour[0].getLabel().toString();
								cl.FormFour[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.FormFour[0].getLabel().toString();
											jp.show(cl.FormFour[0], e.getX(), e.getY());

										}

									}
								});
								ns = i;

								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 2) {
								cl.FormFour[i - 1].setVisible(true);
								cl.FormFour[i - 1].setText(
										str + "                                                                ");
								cl.FormFour[i - 1].addActionListener(action1);
								Class[1] = cl.FormFour[1].getLabel().toString();
								cl.FormFour[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {
										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.FormFour[1].getLabel().toString();
											jp.show(cl.FormFour[1], e.getX(), e.getY());

										}

									}
								});
								ns = i;
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 3) {
								cl.FormFour[i - 1].setVisible(true);
								cl.FormFour[i - 1].setText(
										str + "                                                                ");
								getter[i - 1] = str;
								cl.FormFour[i - 1].addActionListener(action1);
								Class[2] = cl.FormFour[2].getLabel().toString();
								cl.FormFour[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {
										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.FormFour[2].getLabel().toString();
											jp.show(cl.FormFour[2], e.getX(), e.getY());

										}

									}
								});

								ns = i;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 4) {
								cl.FormFour[i - 1].setVisible(true);
								cl.FormFour[i - 1].setText(
										str + "                                                                ");
								getter[i - 1] = str;
								cl.FormFour[i - 1].addActionListener(action1);
								Class[3] = cl.FormFour[3].getLabel().toString();
								cl.FormFour[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {
										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.FormFour[3].getLabel().toString();
											jp.show(cl.FormFour[3], e.getX(), e.getY());

										}

									}
								});
								ns = i;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 5) {
								cl.FormFour[i - 1].setVisible(true);
								cl.FormFour[i - 1].setText(
										str + "                                                                ");
								getter[i - 1] = str;
								cl.FormFour[i - 1].addActionListener(action);
								Class[4] = cl.FormFour[4].getLabel().toString();
								cl.FormFour[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {
										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.FormFour[4].getLabel().toString();
											jp.show(cl.FormFour[4], e.getX(), e.getY());

										}

									}
								});
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 6) {
								cl.FormFour[i - 1].setVisible(true);
								cl.FormFour[i - 1].setText(
										str + "                                                                ");
								getter[i - 1] = str;
								cl.FormFour[i - 1].addActionListener(action1);
								Class[5] = cl.FormFour[5].getLabel().toString();
								cl.FormFour[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {
										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.FormFour[5].getLabel().toString();
											jp.show(cl.FormFour[5], e.getX(), e.getY());

										}

									}
								});
								ns = i;
								cl.Fbtn.setEnabled(false);
								dg[i - 1] = str1;
								lg[i - 1] = str2;

							}

						}
//				
//				
						data.st.close();
						data.con.close();

					} catch (Exception e) {
						System.out.print(e);
					}
					cl.graduated.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent arg0) {
							
							boolean b = false;
							String date = null;
							Databases data1 = new Databases();
							try {
								data1.classForname();
								data1.con = DriverManager.getConnection("jdbc:sqlite:class.db");
								data1.st = data1.con.createStatement();
								String query ="SELECT dates from laster";
								ResultSet rs = data1.st.executeQuery(query);
								while(rs.next())
									date = rs.getString("dates");
								query ="SELECT classname from classrooms where classname like'form four%'";
								ResultSet rs1 = data1.st.executeQuery(query);
								b = rs1.next();
								
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
							
							if(b) {
								int dd = JOptionPane.showConfirmDialog(null, "this is classes would be graduated",
										"Schmays", JOptionPane.OK_CANCEL_OPTION);
								
								if(dd == 0) {
									inserteToGraduated(date);
									deletefromStudents();
								}
								
								
								
								
							}else{
								JOptionPane.showMessageDialog(null, "there no class rooms","schmasys info",JOptionPane.INFORMATION_MESSAGE);
							}
							
						}

						private void deletefromStudents() {
							Databases data = new Databases();
							try {
								data.classForname();
								data.con = DriverManager.getConnection("jdbc:sqlite:students.db");
								data.st = data.con.createStatement();
								data.st.executeUpdate("delete from student where Student_class like'form four%'");
							}catch(Exception e) {
								e.printStackTrace();
							}finally {
								try {
									data.con.close();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							
						}

					
						private void inserteToGraduated(String date) {
							Databases data = new Databases();
							try {
								data.classForname();
								data.con = DriverManager.getConnection("jdbc:sqlite:students.db");
								data.st = data.con.createStatement();
								
								String query = "select Student_name," + " Student_gender,Student_phone,"
										+ "Student_Email,Student_address," + "Student_image,Mother_name,"
										+ "Location_Birth,Birth_Date "
										+ "from student where Student_class like'form four%'";
								ResultSet rs = data.st.executeQuery(query);
								while (rs.next()) {
									
									String name = rs.getString("Student_name");
									String mother = rs.getString("Mother_name");
									String phone = rs.getString("Student_phone");
									byte[] image = rs.getBytes("Student_image");
									String Email = rs.getString("Student_Email");
									String Location = rs.getString("Location_Birth");
									String datebirth = rs.getString("Birth_Date");
									String gender = rs.getString("Student_gender");
									String Address = rs.getString("Student_address");
									String insert = "insert into graduate (Student_name,Mother_name,Student_phone,Student_image,"
											+ "Student_Email,Location_Birth,Birth_Date,"
											+ "Student_gender,Student_address,grad_year) values ('" + name + "','"
											+ mother + "','" + phone + "',?,'" + Email + "','" + Location
											+ "','" + datebirth + "','" + gender + "','" + Address + "','"+date+"')";
									PreparedStatement pr = data.con.prepareStatement(insert);
									pr.setBytes(1, image);
									pr.executeUpdate();
									pr.close();
									String query1 = "update records set deleted = 1 where Student_name='" + name + "'";
									PreparedStatement pr1 = data.con.prepareStatement(query1);
									pr1.executeUpdate();
									pr1.close();
								}
								
							}catch(Exception e) {
								e.printStackTrace();
							}
							finally {
								try {
									data.con.close();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
										
							
						}

					});

				} else if (arg0.getSource() == cl.close) {
					cl.setVisible(false);

					container.setVisible(true);

				} else if (arg0.getSource() == btn[1]) {
					cl.setVisible(true);
					cl.four.setVisible(false);
					cl.three.setVisible(true);
					cl.two.setVisible(false);
					cl.one.setVisible(false);
					cl.eight.setVisible(false);
					cl.seven.setVisible(false);
					cl.six.setVisible(false);
					cl.five.setVisible(false);
					cl.Four.setVisible(false);
					cl.Three.setVisible(false);
					cl.Two.setVisible(false);
					cl.One.setVisible(false);

					try {

						String query = "select classname , deanname , leadername from classrooms where classname like '%form th%'";
						PreparedStatement ps = data.con.prepareStatement(query);
						ResultSet rs = ps.executeQuery();
						while (rs.next()) {
							int i = rs.getRow();
							String str = rs.getString(1);
							String str1 = rs.getString("deanname");
							String str2 = rs.getString("leadername");
							if (i == 1) {
								cl.FormThree[i - 1].setVisible(true);
								cl.FormThree[i - 1].setText(
										str + "                                                                ");
								cl.FormThree[i - 1].addActionListener(action1);
								Class[0] = cl.FormThree[0].getLabel().toString();
								cl.FormThree[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {
										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.FormThree[0].getLabel().toString();
											jp.show(cl.FormThree[0], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 2) {
								cl.FormThree[i - 1].setVisible(true);
								cl.FormThree[i - 1].setText(
										str + "                                                                ");
								cl.FormThree[i - 1].addActionListener(action1);
								Class[i - 1] = cl.FormThree[i - 1].getLabel().toString();
								cl.FormThree[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {
										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.FormThree[1].getLabel().toString();
											jp.show(cl.FormThree[1], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 3) {
								cl.FormThree[i - 1].setVisible(true);
								cl.FormThree[i - 1].setText(
										str + "                                                                ");
								cl.FormThree[i - 1].addActionListener(action1);
								Class[i - 1] = cl.FormThree[i - 1].getLabel().toString();
								cl.FormThree[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {
										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.FormThree[2].getLabel().toString();
											jp.show(cl.FormThree[2], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 4) {
								cl.FormThree[i - 1].setVisible(true);
								cl.FormThree[i - 1].setText(
										str + "                                                                ");
								;
								cl.FormThree[i - 1].addActionListener(action1);
								Class[i - 1] = cl.FormThree[i - 1].getLabel().toString();
								cl.FormThree[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {
										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.FormThree[3].getLabel().toString();
											jp.show(cl.FormThree[3], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 5) {
								cl.FormThree[i - 1].setVisible(true);
								cl.FormThree[i - 1].setText(
										str + "                                                                ");
								cl.FormThree[i - 1].addActionListener(action1);
								Class[i - 1] = cl.FormThree[i - 1].getLabel().toString();
								cl.FormThree[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {
										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.FormThree[4].getLabel().toString();
											jp.show(cl.FormThree[4], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 6) {
								cl.FormThree[i - 1].setVisible(true);
								cl.FormThree[i - 1].setText(
										str + "                                                                ");
								cl.FormThree[i - 1].addActionListener(action1);
								Class[i - 1] = cl.FormThree[i - 1].getLabel().toString();
								cl.FormThree[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {
										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.FormThree[5].getLabel().toString();
											jp.show(cl.FormThree[5], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
								cl.Tbtn.setEnabled(false);
							}

						}
//					
//					
						data.st.close();
						data.con.close();

					} catch (Exception e) {
						System.out.print(e);
					}

					cl.toFf.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							int s = JOptionPane.showConfirmDialog(null,
									"Do you confirm jumping this class into form four ", "schmasys",
									JOptionPane.YES_NO_OPTION);
							if (s == 0) {
								String newClasses[] = { "form four of one", "form four of two", "form four of three",
										"form four of four", "form four of five", "form four of six" };
								Databases data = new Databases();

								try {
									data.classForname();
									data.con = DriverManager.getConnection("jdbc:sqlite:students.db");
									data.st = data.con.createStatement();

									Databases db = new Databases();
									try {
										db.classForname();
										db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
										db.st = db.con.createStatement();
										String query = "delete from classrooms where classname like 'form four%'";
										db.st.executeUpdate(query);
										db.con.close();
									} catch (Exception e) {
										System.out.println(e);
									}

									for (int i = 0; i < Class.length; i++) {
										if (!(Class[i] == null)) {
											String shorter = Class[i].substring(0,
													Class[i].indexOf("                    "));

											Databases db1 = new Databases();
											try {
												db1.classForname();
												db1.con = DriverManager.getConnection("jdbc:sqlite:class.db");
												db1.st = db1.con.createStatement();
												String classes = "insert into classrooms (classname) values('"
														+ newClasses[i] + "')";
												db1.st.executeUpdate(classes);
												db1.con.close();
											} catch (Exception e) {
												System.out.println(e);
											}
											String update = "update student set Student_class='" + newClasses[i]
													+ "' where Student_class ='" + shorter + "'";
											data.st.executeUpdate(update);

										}
									}
									data.con.close();
									JOptionPane.showMessageDialog(null, "all of form three class are jumped",
											"Schmaysy", JOptionPane.INFORMATION_MESSAGE);

								} catch (Exception e) {
									System.out.println(e);
								}
							}
						}
					});

					container.setVisible(false);

				} else if (arg0.getSource() == btn[2]) {
					cl.setVisible(true);
					cl.four.setVisible(false);
					cl.three.setVisible(false);
					cl.two.setVisible(true);
					cl.one.setVisible(false);
					cl.eight.setVisible(false);
					cl.seven.setVisible(false);
					cl.six.setVisible(false);
					cl.five.setVisible(false);
					cl.Four.setVisible(false);
					cl.Three.setVisible(false);
					cl.Two.setVisible(false);
					cl.One.setVisible(false);

					try {

						String query = "select classname , deanname , leadername from classrooms where classname like '%form tw%'";
						PreparedStatement ps = data.con.prepareStatement(query);
						ResultSet rs = ps.executeQuery();
						while (rs.next()) {
							int i = rs.getRow();
							String str = rs.getString(1);
							String str1 = rs.getString("deanname");
							String str2 = rs.getString("leadername");
							if (i == 1) {
								cl.FormTwo[i - 1].setVisible(true);
								cl.FormTwo[i - 1].setText(
										str + "                                                                ");
								cl.FormTwo[i - 1].addActionListener(action1);
								Class[i - 1] = cl.FormTwo[i - 1].getLabel().toString();
								cl.FormTwo[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {
										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.FormTwo[0].getLabel().toString();
											jp.show(cl.FormTwo[0], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 2) {
								cl.FormTwo[i - 1].setVisible(true);
								cl.FormTwo[i - 1].setText(
										str + "                                                                ");
								cl.FormTwo[i - 1].addActionListener(action1);
								Class[i - 1] = cl.FormTwo[i - 1].getLabel().toString();
								cl.FormTwo[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {
										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.FormTwo[1].getLabel().toString();
											jp.show(cl.FormTwo[1], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 3) {
								cl.FormTwo[i - 1].setVisible(true);
								cl.FormTwo[i - 1].setText(
										str + "                                                                ");
								cl.FormTwo[i - 1].addActionListener(action1);
								Class[i - 1] = cl.FormTwo[i - 1].getLabel().toString();
								cl.FormTwo[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {
										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.FormTwo[2].getLabel().toString();
											jp.show(cl.FormTwo[2], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 4) {
								cl.FormTwo[i - 1].setVisible(true);
								cl.FormTwo[i - 1].setText(
										str + "                                                                ");
								;
								cl.FormTwo[i - 1].addActionListener(action1);
								Class[i - 1] = cl.FormTwo[i - 1].getLabel().toString();
								cl.FormTwo[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {
										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.FormTwo[3].getLabel().toString();
											jp.show(cl.FormTwo[3], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 5) {
								cl.FormTwo[i - 1].setVisible(true);
								cl.FormTwo[i - 1].setText(
										str + "                                                                ");
								cl.FormTwo[i - 1].addActionListener(action1);
								Class[i - 1] = cl.FormTwo[i - 1].getLabel().toString();
								cl.FormTwo[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {
										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.FormTwo[4].getLabel().toString();
											jp.show(cl.FormTwo[4], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 6) {
								cl.FormTwo[i - 1].setVisible(true);
								cl.FormTwo[i - 1].setText(
										str + "                                                                ");
								cl.FormTwo[i - 1].addActionListener(action1);
								Class[i - 1] = cl.FormTwo[i - 1].getLabel().toString();
								cl.FormTwo[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {
										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.FormTwo[5].getLabel().toString();
											jp.show(cl.FormTwo[5], e.getX(), e.getY());

										}

									}
								});
								cl.TWbtn.setEnabled(false);
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;

							}

						}
//					
//					
						data.st.close();
						data.con.close();

					} catch (Exception e) {
						System.out.print(e);
					}

					cl.toFth.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							int s = JOptionPane.showConfirmDialog(null,
									"Do you confirm jumping this class into form three", "schmasys",
									JOptionPane.YES_NO_OPTION);
							if (s == 0) {
								String newClasses[] = { "form three of one", "form three of two", "form three of three",
										"form three of four", "form three of five", "form three of six" };
								Databases data = new Databases();

								try {
									data.classForname();
									data.con = DriverManager.getConnection("jdbc:sqlite:students.db");
									data.st = data.con.createStatement();

									Databases db = new Databases();
									try {
										db.classForname();
										db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
										db.st = db.con.createStatement();
										String query = "delete from classrooms where classname like 'form three%'";
										db.st.executeUpdate(query);
										db.con.close();
									} catch (Exception e) {
										System.out.println(e);
									}

									for (int i = 0; i < Class.length; i++) {
										if (!(Class[i] == null)) {
											String shorter = Class[i].substring(0,
													Class[i].indexOf("                    "));

											Databases db1 = new Databases();
											try {
												db1.classForname();
												db1.con = DriverManager.getConnection("jdbc:sqlite:class.db");
												db1.st = db1.con.createStatement();
												String classes = "insert into classrooms (classname) values('"
														+ newClasses[i] + "')";
												db1.st.executeUpdate(classes);
												db1.con.close();
											} catch (Exception e) {
												System.out.println(e);
											}
											String update = "update student set Student_class='" + newClasses[i]
													+ "' where Student_class ='" + shorter + "'";
											data.st.executeUpdate(update);

										}
									}
									data.con.close();
									JOptionPane.showMessageDialog(null, "all of form two class are jumped", "Schmaysy",
											JOptionPane.INFORMATION_MESSAGE);

								} catch (Exception e) {
									System.out.println(e);
								}
							}
						}
					});
					container.setVisible(false);

				} else if (arg0.getSource() == btn[3]) {
					cl.setVisible(true);
					cl.four.setVisible(false);
					cl.three.setVisible(false);
					cl.two.setVisible(false);
					cl.one.setVisible(true);
					cl.eight.setVisible(false);
					cl.seven.setVisible(false);
					cl.six.setVisible(false);
					cl.five.setVisible(false);
					cl.Four.setVisible(false);
					cl.Three.setVisible(false);
					cl.Two.setVisible(false);
					cl.One.setVisible(false);

					try {

						String query = "select classname , deanname , leadername from classrooms where classname like '%form on%'";
						PreparedStatement ps = data.con.prepareStatement(query);
						ResultSet rs = ps.executeQuery();
						while (rs.next()) {
							int i = rs.getRow();
							String str = rs.getString(1);
							String str1 = rs.getString("deanname");
							String str2 = rs.getString("leadername");
							if (i == 1) {
								cl.FormOne[i - 1].setVisible(true);
								cl.FormOne[i - 1].setText(
										str + "                                                                ");
								cl.FormOne[i - 1].addActionListener(action1);
								Class[i - 1] = cl.FormOne[i - 1].getLabel().toString();
								cl.FormOne[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {
										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.FormOne[0].getLabel().toString();
											jp.show(cl.FormOne[0], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 2) {
								cl.FormOne[i - 1].setVisible(true);
								cl.FormOne[i - 1].setText(
										str + "                                                                ");
								cl.FormOne[i - 1].addActionListener(action1);
								Class[i - 1] = cl.FormOne[i - 1].getLabel().toString();
								cl.FormOne[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {
										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.FormOne[1].getLabel().toString();
											jp.show(cl.FormOne[1], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 3) {
								cl.FormOne[i - 1].setVisible(true);
								cl.FormOne[i - 1].setText(
										str + "                                                                ");
								cl.FormOne[i - 1].addActionListener(action1);
								Class[i - 1] = cl.FormOne[i - 1].getLabel().toString();
								cl.FormOne[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {
										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.FormOne[2].getLabel().toString();
											jp.show(cl.FormOne[2], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 4) {
								cl.FormOne[i - 1].setVisible(true);
								cl.FormOne[i - 1].setText(
										str + "                                                                ");
								;
								cl.FormOne[i - 1].addActionListener(action1);
								Class[i - 1] = cl.FormOne[i - 1].getLabel().toString();
								cl.FormOne[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {
										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.FormOne[3].getLabel().toString();
											jp.show(cl.FormOne[3], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 5) {
								cl.FormOne[i - 1].setVisible(true);
								cl.FormOne[i - 1].setText(
										str + "                                                                ");
								cl.FormOne[i - 1].addActionListener(action1);
								Class[i - 1] = cl.FormOne[i - 1].getLabel().toString();
								cl.FormOne[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {
										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.FormOne[4].getLabel().toString();
											jp.show(cl.FormOne[4], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 6) {
								cl.FormOne[i - 1].setVisible(true);
								cl.FormOne[i - 1].setText(
										str + "                                                                ");
								cl.FormOne[i - 1].addActionListener(action1);
								Class[i - 1] = cl.FormOne[i - 1].getLabel().toString();
								cl.FormOne[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {
										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.FormOne[5].getLabel().toString();
											jp.show(cl.FormOne[5], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
								cl.Obtn.setEnabled(false);
							}

						}
//					
//					
						data.st.close();
						data.con.close();

					} catch (Exception e) {
						System.out.print(e);
					}
					cl.toFt.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							int s = JOptionPane.showConfirmDialog(null,
									"Do you confirm jumping this class into form two", "schmasys",
									JOptionPane.YES_NO_OPTION);
							if (s == 0) {
								String newClasses[] = { "form two of one", "form two of two", "form two of three",
										"form two of four", "form two of five", "form two of six" };
								Databases data = new Databases();

								try {
									data.classForname();
									data.con = DriverManager.getConnection("jdbc:sqlite:students.db");
									data.st = data.con.createStatement();

									Databases db = new Databases();
									try {
										db.classForname();
										db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
										db.st = db.con.createStatement();
										String query = "delete from classrooms where classname like 'form two%'";
										db.st.executeUpdate(query);
										db.con.close();
									} catch (Exception e) {
										System.out.println(e);
									}

									for (int i = 0; i < Class.length; i++) {
										if (!(Class[i] == null)) {
											String shorter = Class[i].substring(0,
													Class[i].indexOf("                    "));

											Databases db1 = new Databases();
											try {
												db1.classForname();
												db1.con = DriverManager.getConnection("jdbc:sqlite:class.db");
												db1.st = db1.con.createStatement();
												String classes = "insert into classrooms (classname) values('"
														+ newClasses[i] + "')";
												db1.st.executeUpdate(classes);
												db1.con.close();
											} catch (Exception e) {
												System.out.println(e);
											}
											String update = "update student set Student_class='" + newClasses[i]
													+ "' where Student_class ='" + shorter + "'";
											data.st.executeUpdate(update);

										}
									}
									data.con.close();
									JOptionPane.showMessageDialog(null, "all of form one class are jumped", "Schmaysy",
											JOptionPane.INFORMATION_MESSAGE);

								} catch (Exception e) {
									System.out.println(e);
								}
							}
						}
					});
					container.setVisible(false);

				} else if (arg0.getSource() == btn[4]) {
					cl.setVisible(true);
					cl.four.setVisible(false);
					cl.three.setVisible(false);
					cl.two.setVisible(false);
					cl.one.setVisible(false);
					cl.eight.setVisible(true);
					cl.seven.setVisible(false);
					cl.six.setVisible(false);
					cl.five.setVisible(false);
					cl.Four.setVisible(false);
					cl.Three.setVisible(false);
					cl.Two.setVisible(false);
					cl.One.setVisible(false);

					try {

						String query = "select classname , deanname , leadername from classrooms where classname like '%grade ei%'";
						PreparedStatement ps = data.con.prepareStatement(query);
						ResultSet rs = ps.executeQuery();
						while (rs.next()) {
							int i = rs.getRow();
							String str = rs.getString(1);
							String str1 = rs.getString("deanname");
							String str2 = rs.getString("leadername");
							if (i == 1) {
								cl.GradeEight[i - 1].setVisible(true);
								cl.GradeEight[i - 1].setText(
										str + "                                                                ");
								cl.GradeEight[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeEight[i - 1].getLabel().toString();
								cl.GradeEight[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {
										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeEight[0].getLabel().toString();
											jp.show(cl.GradeEight[0], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 2) {
								cl.GradeEight[i - 1].setVisible(true);
								cl.GradeEight[i - 1].setText(
										str + "                                                                ");
								cl.GradeEight[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeEight[i - 1].getLabel().toString();
								cl.GradeEight[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {
										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeEight[1].getLabel().toString();
											jp.show(cl.GradeEight[1], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 3) {
								cl.GradeEight[i - 1].setVisible(true);
								cl.GradeEight[i - 1].setText(
										str + "                                                                ");
								cl.GradeEight[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeEight[i - 1].getLabel().toString();
								cl.GradeEight[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {
										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeEight[2].getLabel().toString();
											jp.show(cl.GradeEight[2], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 4) {
								cl.GradeEight[i - 1].setVisible(true);
								cl.GradeEight[i - 1].setText(
										str + "                                                                ");
								;
								cl.GradeEight[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeEight[i - 1].getLabel().toString();
								cl.GradeEight[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {
										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeEight[3].getLabel().toString();
											jp.show(cl.GradeEight[3], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 5) {
								cl.GradeEight[i - 1].setVisible(true);
								cl.GradeEight[i - 1].setText(
										str + "                                                                ");
								cl.GradeEight[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeEight[i - 1].getLabel().toString();
								cl.GradeEight[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {
										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeEight[4].getLabel().toString();
											jp.show(cl.GradeEight[4], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 6) {
								cl.GradeEight[i - 1].setVisible(true);
								cl.GradeEight[i - 1].setText(
										str + "                                                                ");
								cl.GradeEight[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeEight[i - 1].getLabel().toString();
								cl.GradeEight[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {
										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeEight[5].getLabel().toString();
											jp.show(cl.GradeEight[5], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
								cl.eibtn.setEnabled(false);
							}

						}
//					
//					
						data.st.close();
						data.con.close();

					} catch (Exception e) {
						System.out.print(e);
					}

					cl.toFo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							int s = JOptionPane.showConfirmDialog(null,
									"Do you confirm jumping this class into form one ", "schmasys",
									JOptionPane.YES_NO_OPTION);
							if (s == 0) {
								String newClasses[] = { "form one of one", "form one of two", "form one of three",
										"form one of four", "form one of five", "form one of six" };
								Databases data = new Databases();

								try {
									data.classForname();
									data.con = DriverManager.getConnection("jdbc:sqlite:students.db");
									data.st = data.con.createStatement();

									Databases db = new Databases();
									try {
										db.classForname();
										db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
										db.st = db.con.createStatement();
										String query = "delete from classrooms where classname like 'form one%'";
										db.st.executeUpdate(query);
										db.con.close();
									} catch (Exception e) {
										System.out.println(e);
									}

									for (int i = 0; i < Class.length; i++) {
										if (!(Class[i] == null)) {
											String shorter = Class[i].substring(0,
													Class[i].indexOf("                    "));

											Databases db1 = new Databases();
											try {
												db1.classForname();
												db1.con = DriverManager.getConnection("jdbc:sqlite:class.db");
												db1.st = db1.con.createStatement();
												String classes = "insert into classrooms (classname) values('"
														+ newClasses[i] + "')";
												db1.st.executeUpdate(classes);
												db1.con.close();
											} catch (Exception e) {
												System.out.println(e);
											}
											String update = "update student set Student_class='" + newClasses[i]
													+ "' where Student_class ='" + shorter + "'";
											data.st.executeUpdate(update);

										}
									}
									data.con.close();
									JOptionPane.showMessageDialog(null, "all of grade eight class are jumped",
											"Schmaysy", JOptionPane.INFORMATION_MESSAGE);

								} catch (Exception e) {
									System.out.println(e);
								}
							}
						}
					});

					container.setVisible(false);

				} else if (arg0.getSource() == btn[5]) {
					cl.setVisible(true);
					cl.four.setVisible(false);
					cl.three.setVisible(false);
					cl.two.setVisible(false);
					cl.one.setVisible(false);
					cl.eight.setVisible(false);
					cl.seven.setVisible(true);
					cl.six.setVisible(false);
					cl.five.setVisible(false);
					cl.Four.setVisible(false);
					cl.Three.setVisible(false);
					cl.Two.setVisible(false);
					cl.One.setVisible(false);

					try {

						String query = "select classname , deanname , leadername from classrooms where classname like '%grade seven%'";
						PreparedStatement ps = data.con.prepareStatement(query);
						ResultSet rs = ps.executeQuery();
						while (rs.next()) {
							int i = rs.getRow();
							String str = rs.getString(1);
							String str1 = rs.getString("deanname");
							String str2 = rs.getString("leadername");
							if (i == 1) {
								cl.GradeSeven[i - 1].setVisible(true);
								cl.GradeSeven[i - 1].setText(
										str + "                                                                ");
								cl.GradeSeven[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeSeven[i - 1].getLabel().toString();
								cl.GradeSeven[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeSeven[0].getLabel().toString();
											jp.show(cl.GradeSeven[0], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 2) {
								cl.GradeSeven[i - 1].setVisible(true);
								cl.GradeSeven[i - 1].setText(
										str + "                                                                ");
								cl.GradeSeven[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeSeven[i - 1].getLabel().toString();
								cl.GradeSeven[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeSeven[1].getLabel().toString();
											jp.show(cl.GradeSeven[1], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 3) {
								cl.GradeSeven[i - 1].setVisible(true);
								cl.GradeSeven[i - 1].setText(
										str + "                                                                ");
								cl.GradeSeven[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeSeven[i - 1].getLabel().toString();

								cl.GradeSeven[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeSeven[2].getLabel().toString();
											jp.show(cl.GradeSeven[2], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 4) {
								cl.GradeSeven[i - 1].setVisible(true);
								cl.GradeSeven[i - 1].setText(
										str + "                                                                ");
								;
								cl.GradeSeven[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeSeven[i - 1].getLabel().toString();

								cl.GradeSeven[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeSeven[3].getLabel().toString();
											jp.show(cl.GradeSeven[3], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 5) {
								cl.GradeSeven[i - 1].setVisible(true);
								cl.GradeSeven[i - 1].setText(
										str + "                                                                ");
								cl.GradeSeven[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeSeven[i - 1].getLabel().toString();

								cl.GradeSeven[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeSeven[4].getLabel().toString();
											jp.show(cl.GradeSeven[4], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 6) {
								cl.GradeSeven[i - 1].setVisible(true);
								cl.GradeSeven[i - 1].setText(
										str + "                                                                ");
								cl.GradeSeven[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeSeven[i - 1].getLabel().toString();

								cl.GradeSeven[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeSeven[5].getLabel().toString();
											jp.show(cl.GradeSeven[5], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
								cl.sebtn.setEnabled(false);
							}

						}
//					
//					
						data.st.close();
						data.con.close();

					} catch (Exception e) {
						System.out.print(e);
					}
					cl.toGe.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							int s = JOptionPane.showConfirmDialog(null,
									"Do you confirm jumping this class into grade eight  ", "schmasys",
									JOptionPane.YES_NO_OPTION);
							if (s == 0) {
								String newClasses[] = { "grade eight of one", "grade eight of two",
										"grade eight of three", "grade eight of four", "grade eight of five",
										"grade eight of six" };
								Databases data = new Databases();

								try {
									data.classForname();
									data.con = DriverManager.getConnection("jdbc:sqlite:students.db");
									data.st = data.con.createStatement();

									Databases db = new Databases();
									try {
										db.classForname();
										db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
										db.st = db.con.createStatement();
										String query = "delete from classrooms where classname like 'grade eight%'";
										db.st.executeUpdate(query);
										db.con.close();
									} catch (Exception e) {
										System.out.println(e);
									}

									for (int i = 0; i < Class.length; i++) {
										if (!(Class[i] == null)) {
											String shorter = Class[i].substring(0,
													Class[i].indexOf("                    "));

											Databases db1 = new Databases();
											try {
												db1.classForname();
												db1.con = DriverManager.getConnection("jdbc:sqlite:class.db");
												db1.st = db1.con.createStatement();
												String classes = "insert into classrooms (classname) values('"
														+ newClasses[i] + "')";
												db1.st.executeUpdate(classes);
												db1.con.close();
											} catch (Exception e) {
												System.out.println(e);
											}
											String update = "update student set Student_class='" + newClasses[i]
													+ "' where Student_class ='" + shorter + "'";
											data.st.executeUpdate(update);

										}
									}
									data.con.close();
									JOptionPane.showMessageDialog(null, "all of grade seven class are jumped",
											"Schmaysy", JOptionPane.INFORMATION_MESSAGE);

								} catch (Exception e) {
									System.out.println(e);
								}
							}
						}
					});

					container.setVisible(false);

				} else if (arg0.getSource() == btn[6]) {
					cl.setVisible(true);
					cl.four.setVisible(false);
					cl.three.setVisible(false);
					cl.two.setVisible(false);
					cl.one.setVisible(false);
					cl.eight.setVisible(false);
					cl.seven.setVisible(false);
					cl.six.setVisible(true);
					cl.five.setVisible(false);
					cl.Four.setVisible(false);
					cl.Three.setVisible(false);
					cl.Two.setVisible(false);
					cl.One.setVisible(false);

					try {

						String query = "select classname , deanname , leadername from classrooms where classname like '%grade six%'";
						PreparedStatement ps = data.con.prepareStatement(query);
						ResultSet rs = ps.executeQuery();
						while (rs.next()) {
							int i = rs.getRow();
							String str = rs.getString(1);
							String str1 = rs.getString("deanname");
							String str2 = rs.getString("leadername");
							if (i == 1) {
								cl.GradeSix[i - 1].setVisible(true);
								cl.GradeSix[i - 1].setText(
										str + "                                                                ");
								cl.GradeSix[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeSix[i - 1].getLabel().toString();
								cl.GradeSix[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeSix[0].getLabel().toString();
											jp.show(cl.GradeSix[0], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 2) {
								cl.GradeSix[i - 1].setVisible(true);
								cl.GradeSix[i - 1].setText(
										str + "                                                                ");
								cl.GradeSix[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeSix[i - 1].getLabel().toString();
								cl.GradeSix[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeSix[1].getLabel().toString();
											jp.show(cl.GradeSix[1], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 3) {
								cl.GradeSix[i - 1].setVisible(true);
								cl.GradeSix[i - 1].setText(
										str + "                                                                ");
								cl.GradeSix[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeSix[i - 1].getLabel().toString();
								cl.GradeSix[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeSix[2].getLabel().toString();
											jp.show(cl.GradeSix[2], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 4) {
								cl.GradeSix[i - 1].setVisible(true);
								cl.GradeSix[i - 1].setText(
										str + "                                                                ");
								;
								cl.GradeSix[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeSix[i - 1].getLabel().toString();
								cl.GradeSix[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeSix[3].getLabel().toString();
											jp.show(cl.GradeSix[3], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 5) {
								cl.GradeSix[i - 1].setVisible(true);
								cl.GradeSix[i - 1].setText(
										str + "                                                                ");
								cl.GradeSix[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeSix[i - 1].getLabel().toString();
								cl.GradeSix[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeSix[4].getLabel().toString();
											jp.show(cl.GradeSix[4], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 6) {
								cl.GradeSix[i - 1].setVisible(true);
								cl.GradeSix[i - 1].setText(
										str + "                                                                ");
								cl.GradeSix[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeSix[i - 1].getLabel().toString();
								cl.GradeSix[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeSix[5].getLabel().toString();
											jp.show(cl.GradeSix[5], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
								cl.sibtn.setEnabled(false);
							}

						}
//					
//					
						data.st.close();
						data.con.close();

					} catch (Exception e) {
						System.out.print(e);
					}

					cl.toGs.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							int s = JOptionPane.showConfirmDialog(null,
									"Do you confirm jumping this class into grade seven   ", "schmasys",
									JOptionPane.YES_NO_OPTION);
							if (s == 0) {
								String newClasses[] = { "grade seven of one", "grade seven of two",
										"grade seven of three", "grade seven of four", "grade seven of five",
										"grade seven of six" };
								Databases data = new Databases();

								try {
									data.classForname();
									data.con = DriverManager.getConnection("jdbc:sqlite:students.db");
									data.st = data.con.createStatement();

									Databases db = new Databases();
									try {
										db.classForname();
										db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
										db.st = db.con.createStatement();
										String query = "delete from classrooms where classname like 'grade seven%'";
										db.st.executeUpdate(query);
										db.con.close();
									} catch (Exception e) {
										System.out.println(e);
									}

									for (int i = 0; i < Class.length; i++) {
										if (!(Class[i] == null)) {
											String shorter = Class[i].substring(0,
													Class[i].indexOf("                    "));

											Databases db1 = new Databases();
											try {
												db1.classForname();
												db1.con = DriverManager.getConnection("jdbc:sqlite:class.db");
												db1.st = db1.con.createStatement();
												String classes = "insert into classrooms (classname) values('"
														+ newClasses[i] + "')";
												db1.st.executeUpdate(classes);
												db1.con.close();
											} catch (Exception e) {
												System.out.println(e);
											}
											String update = "update student set Student_class='" + newClasses[i]
													+ "' where Student_class ='" + shorter + "'";
											data.st.executeUpdate(update);

										}
									}
									data.con.close();
									JOptionPane.showMessageDialog(null, "all of grade six class are jumped", "Schmaysy",
											JOptionPane.INFORMATION_MESSAGE);

								} catch (Exception e) {
									System.out.println(e);
								}
							}
						}
					});

					container.setVisible(false);

				} else if (arg0.getSource() == btn[7]) {
					cl.setVisible(true);
					cl.four.setVisible(false);
					cl.three.setVisible(false);
					cl.two.setVisible(false);
					cl.one.setVisible(false);
					cl.eight.setVisible(false);
					cl.seven.setVisible(false);
					cl.six.setVisible(false);
					cl.five.setVisible(true);
					cl.Four.setVisible(false);
					cl.Three.setVisible(false);
					cl.Two.setVisible(false);
					cl.One.setVisible(false);

					try {

						String query = "select classname , deanname , leadername from classrooms where classname like '%grade five%'";
						PreparedStatement ps = data.con.prepareStatement(query);
						ResultSet rs = ps.executeQuery();
						while (rs.next()) {
							int i = rs.getRow();
							String str = rs.getString(1);
							String str1 = rs.getString("deanname");
							String str2 = rs.getString("leadername");
							if (i == 1) {
								cl.GradeFive[i - 1].setVisible(true);
								cl.GradeFive[i - 1].setText(
										str + "                                                                ");
								cl.GradeFive[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeFive[i - 1].getLabel().toString();
								cl.GradeFive[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeFive[0].getLabel().toString();
											jp.show(cl.GradeFive[0], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 2) {
								cl.GradeFive[i - 1].setVisible(true);
								cl.GradeFive[i - 1].setText(
										str + "                                                                ");
								cl.GradeFive[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeFive[i - 1].getLabel().toString();
								cl.GradeFive[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeFive[1].getLabel().toString();
											jp.show(cl.GradeFive[1], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 3) {
								cl.GradeFive[i - 1].setVisible(true);
								cl.GradeFive[i - 1].setText(
										str + "                                                                ");
								cl.GradeFive[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeFive[i - 1].getLabel().toString();
								cl.GradeFive[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeFive[2].getLabel().toString();
											jp.show(cl.GradeFive[2], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 4) {
								cl.GradeFive[i - 1].setVisible(true);
								cl.GradeFive[i - 1].setText(
										str + "                                                                ");
								;
								cl.GradeFive[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeFive[i - 1].getLabel().toString();
								cl.GradeFive[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeFive[3].getLabel().toString();
											jp.show(cl.GradeFive[3], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 5) {
								cl.GradeFive[i - 1].setVisible(true);
								cl.GradeFive[i - 1].setText(
										str + "                                                                ");
								cl.GradeFive[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeFive[i - 1].getLabel().toString();
								cl.GradeFive[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeFive[4].getLabel().toString();
											jp.show(cl.GradeFive[4], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 6) {
								cl.GradeFive[i - 1].setVisible(true);
								cl.GradeFive[i - 1].setText(
										str + "                                                                ");
								cl.GradeFive[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeFive[i - 1].getLabel().toString();
								cl.GradeFive[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeFive[5].getLabel().toString();
											jp.show(cl.GradeFive[5], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
								cl.fibtn.setEnabled(false);
							}

						}
//					
//					
						data.st.close();
						data.con.close();

					} catch (Exception e) {
						System.out.print(e);
					}

					cl.toGsi.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							int s = JOptionPane.showConfirmDialog(null,
									"Do you confirm jumping this class into grade six ", "schmasys",
									JOptionPane.YES_NO_OPTION);
							if (s == 0) {
								String newClasses[] = { "grade six of one", "grade six of two", "grade six of three",
										"grade six of four", "grade six of five", "grade six of six" };
								Databases data = new Databases();

								try {
									data.classForname();
									data.con = DriverManager.getConnection("jdbc:sqlite:students.db");
									data.st = data.con.createStatement();

									Databases db = new Databases();
									try {
										db.classForname();
										db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
										db.st = db.con.createStatement();
										String query = "delete from classrooms where classname like 'grade six%'";
										db.st.executeUpdate(query);
										db.con.close();
									} catch (Exception e) {
										System.out.println(e);
									}

									for (int i = 0; i < Class.length; i++) {
										if (!(Class[i] == null)) {
											String shorter = Class[i].substring(0,
													Class[i].indexOf("                    "));

											Databases db1 = new Databases();
											try {
												db1.classForname();
												db1.con = DriverManager.getConnection("jdbc:sqlite:class.db");
												db1.st = db1.con.createStatement();
												String classes = "insert into classrooms (classname) values('"
														+ newClasses[i] + "')";
												db1.st.executeUpdate(classes);
												db1.con.close();
											} catch (Exception e) {
												System.out.println(e);
											}
											String update = "update student set Student_class='" + newClasses[i]
													+ "' where Student_class ='" + shorter + "'";
											data.st.executeUpdate(update);

										}
									}
									data.con.close();
									JOptionPane.showMessageDialog(null, "all of grade five class are jumped",
											"Schmaysy", JOptionPane.INFORMATION_MESSAGE);

								} catch (Exception e) {
									System.out.println(e);
								}
							}
						}
					});

					container.setVisible(false);

				} else if (arg0.getSource() == btn[8]) {
					cl.setVisible(true);
					cl.four.setVisible(false);
					cl.three.setVisible(false);
					cl.two.setVisible(false);
					cl.one.setVisible(false);
					cl.eight.setVisible(false);
					cl.seven.setVisible(false);
					cl.six.setVisible(false);
					cl.five.setVisible(false);
					cl.Four.setVisible(true);
					cl.Three.setVisible(false);
					cl.Two.setVisible(false);
					cl.One.setVisible(false);

					try {

						String query = "select classname , deanname , leadername from classrooms where classname like '%grade four%'";
						PreparedStatement ps = data.con.prepareStatement(query);
						ResultSet rs = ps.executeQuery();
						while (rs.next()) {
							int i = rs.getRow();
							String str = rs.getString(1);
							String str1 = rs.getString("deanname");
							String str2 = rs.getString("leadername");
							if (i == 1) {
								cl.GradeFour[i - 1].setVisible(true);
								cl.GradeFour[i - 1].setText(
										str + "                                                                ");
								cl.GradeFour[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeFour[i - 1].getLabel().toString();
								cl.GradeFour[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeFour[0].getLabel().toString();
											jp.show(cl.GradeFour[0], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 2) {
								cl.GradeFour[i - 1].setVisible(true);
								cl.GradeFour[i - 1].setText(
										str + "                                                                ");
								cl.GradeFour[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeFour[i - 1].getLabel().toString();
								cl.GradeFour[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeFour[1].getLabel().toString();
											jp.show(cl.GradeFour[1], e.getX(), e.getY());

										}

									}
								});

								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 3) {
								cl.GradeFour[i - 1].setVisible(true);
								cl.GradeFour[i - 1].setText(
										str + "                                                                ");
								cl.GradeFour[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeFour[i - 1].getLabel().toString();
								cl.GradeFour[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeFour[2].getLabel().toString();
											jp.show(cl.GradeFour[2], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 4) {
								cl.GradeFour[i - 1].setVisible(true);
								cl.GradeFour[i - 1].setText(
										str + "                                                                ");
								;
								cl.GradeFour[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeFour[i - 1].getLabel().toString();
								cl.GradeFour[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeFour[3].getLabel().toString();
											jp.show(cl.GradeFour[3], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 5) {
								cl.GradeFour[i - 1].setVisible(true);
								cl.GradeFour[i - 1].setText(
										str + "                                                                ");
								cl.GradeFour[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeFour[i - 1].getLabel().toString();
								cl.GradeFour[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeFour[4].getLabel().toString();
											jp.show(cl.GradeFour[4], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 6) {
								cl.GradeFour[i - 1].setVisible(true);
								cl.GradeFour[i - 1].setText(
										str + "                                                                ");
								cl.GradeFour[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeFour[i - 1].getLabel().toString();
								cl.GradeFour[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeFour[5].getLabel().toString();
											jp.show(cl.GradeFour[5], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
								cl.fobtn.setEnabled(false);
							}

						}
//					
//					
						data.st.close();
						data.con.close();

					} catch (Exception e) {
						System.out.print(e);
					}

					cl.toGfi.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							int s = JOptionPane.showConfirmDialog(null,
									"Do you confirm jumping this class into grade five ", "schmasys",
									JOptionPane.YES_NO_OPTION);
							if (s == 0) {
								String newClasses[] = { "grade five of one", "grade five of two", "grade five of three",
										"grade five of four", "grade five of five", "grade five of six" };
								Databases data = new Databases();

								try {
									data.classForname();
									data.con = DriverManager.getConnection("jdbc:sqlite:students.db");
									data.st = data.con.createStatement();

									Databases db = new Databases();
									try {
										db.classForname();
										db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
										db.st = db.con.createStatement();
										String query = "delete from classrooms where classname like 'grade five%'";
										db.st.executeUpdate(query);
										db.con.close();
									} catch (Exception e) {
										System.out.println(e);
									}

									for (int i = 0; i < Class.length; i++) {
										if (!(Class[i] == null)) {
											String shorter = Class[i].substring(0,
													Class[i].indexOf("                    "));

											Databases db1 = new Databases();
											try {
												db1.classForname();
												db1.con = DriverManager.getConnection("jdbc:sqlite:class.db");
												db1.st = db1.con.createStatement();
												String classes = "insert into classrooms (classname) values('"
														+ newClasses[i] + "')";
												db1.st.executeUpdate(classes);
												db1.con.close();
											} catch (Exception e) {
												System.out.println(e);
											}
											String update = "update student set Student_class='" + newClasses[i]
													+ "' where Student_class ='" + shorter + "'";
											data.st.executeUpdate(update);

										}
									}
									data.con.close();
									JOptionPane.showMessageDialog(null, "all of grade four class are jumped",
											"Schmaysy", JOptionPane.INFORMATION_MESSAGE);

								} catch (Exception e) {
									System.out.println(e);
								}
							}
						}
					});

					container.setVisible(false);

				} else if (arg0.getSource() == btn[9]) {
					cl.setVisible(true);
					cl.four.setVisible(false);
					cl.three.setVisible(false);
					cl.two.setVisible(false);
					cl.one.setVisible(false);
					cl.eight.setVisible(false);
					cl.seven.setVisible(false);
					cl.six.setVisible(false);
					cl.five.setVisible(false);
					cl.Four.setVisible(false);
					cl.Three.setVisible(true);
					cl.Two.setVisible(false);
					cl.One.setVisible(false);

					try {

						String query = "select classname , deanname , leadername from classrooms where classname like '%grade thre%'";
						PreparedStatement ps = data.con.prepareStatement(query);
						ResultSet rs = ps.executeQuery();
						while (rs.next()) {
							int i = rs.getRow();
							String str = rs.getString(1);
							String str1 = rs.getString("deanname");
							String str2 = rs.getString("leadername");
							if (i == 1) {
								cl.GradeThree[i - 1].setVisible(true);
								cl.GradeThree[i - 1].setText(
										str + "                                                                ");
								cl.GradeThree[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeThree[i - 1].getLabel().toString();
								cl.GradeThree[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeThree[0].getLabel().toString();
											jp.show(cl.GradeThree[0], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 2) {
								cl.GradeThree[i - 1].setVisible(true);
								cl.GradeThree[i - 1].setText(
										str + "                                                                ");
								cl.GradeThree[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeThree[i - 1].getLabel().toString();
								cl.GradeThree[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeThree[1].getLabel().toString();
											jp.show(cl.GradeThree[1], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 3) {
								cl.GradeThree[i - 1].setVisible(true);
								cl.GradeThree[i - 1].setText(
										str + "                                                                ");
								cl.GradeThree[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeThree[i - 1].getLabel().toString();
								cl.GradeThree[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeThree[2].getLabel().toString();
											jp.show(cl.GradeThree[2], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 4) {
								cl.GradeThree[i - 1].setVisible(true);
								cl.GradeThree[i - 1].setText(
										str + "                                                                ");
								;
								cl.GradeThree[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeThree[i - 1].getLabel().toString();
								cl.GradeThree[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeThree[3].getLabel().toString();
											jp.show(cl.GradeThree[3], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 5) {
								cl.GradeThree[i - 1].setVisible(true);
								cl.GradeThree[i - 1].setText(
										str + "                                                                ");
								cl.GradeThree[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeThree[i - 1].getLabel().toString();
								cl.GradeThree[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeThree[4].getLabel().toString();
											jp.show(cl.GradeThree[4], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 6) {
								cl.GradeThree[i - 1].setVisible(true);
								cl.GradeThree[i - 1].setText(
										str + "                                                                ");
								cl.GradeThree[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeThree[i - 1].getLabel().toString();
								cl.GradeThree[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeThree[5].getLabel().toString();
											jp.show(cl.GradeThree[5], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
								cl.thbtn.setEnabled(false);
							}

						}
//					
//					
						data.st.close();
						data.con.close();

					} catch (Exception e) {
						System.out.print(e);
					}

					cl.toGf.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							int s = JOptionPane.showConfirmDialog(null,
									"Do you confirm jumping this class into grade four", "schmasys",
									JOptionPane.YES_NO_OPTION);
							if (s == 0) {
								String newClasses[] = { "grade four of one", "grade four of two", "grade four of three",
										"grade four of four", "grade four of five", "grade four of six" };
								Databases data = new Databases();

								try {
									data.classForname();
									data.con = DriverManager.getConnection("jdbc:sqlite:students.db");
									data.st = data.con.createStatement();

									Databases db = new Databases();
									try {
										db.classForname();
										db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
										db.st = db.con.createStatement();
										String query = "delete from classrooms where classname like 'grade four%'";
										db.st.executeUpdate(query);
										db.con.close();
									} catch (Exception e) {
										System.out.println(e);
									}

									for (int i = 0; i < Class.length; i++) {
										if (!(Class[i] == null)) {
											String shorter = Class[i].substring(0,
													Class[i].indexOf("                    "));

											Databases db1 = new Databases();
											try {
												db1.classForname();
												db1.con = DriverManager.getConnection("jdbc:sqlite:class.db");
												db1.st = db1.con.createStatement();
												String classes = "insert into classrooms (classname) values('"
														+ newClasses[i] + "')";
												db1.st.executeUpdate(classes);
												db1.con.close();
											} catch (Exception e) {
												System.out.println(e);
											}
											String update = "update student set Student_class='" + newClasses[i]
													+ "' where Student_class ='" + shorter + "'";
											data.st.executeUpdate(update);

										}
									}
									data.con.close();
									JOptionPane.showMessageDialog(null, "all of grade three class are jumped",
											"Schmaysy", JOptionPane.INFORMATION_MESSAGE);

								} catch (Exception e) {
									System.out.println(e);
								}
							}
						}
					});
					container.setVisible(false);

				} else if (arg0.getSource() == btn[10]) {
					cl.setVisible(true);
					cl.four.setVisible(false);
					cl.three.setVisible(false);
					cl.two.setVisible(false);
					cl.one.setVisible(false);
					cl.eight.setVisible(false);
					cl.seven.setVisible(false);
					cl.six.setVisible(false);
					cl.five.setVisible(false);
					cl.Four.setVisible(false);
					cl.Three.setVisible(false);
					cl.Two.setVisible(true);
					cl.One.setVisible(false);

					try {

						String query = "select classname , deanname , leadername from classrooms where classname like '%grade two%'";
						PreparedStatement ps = data.con.prepareStatement(query);
						ResultSet rs = ps.executeQuery();
						while (rs.next()) {
							int i = rs.getRow();
							String str = rs.getString(1);
							String str1 = rs.getString("deanname");
							String str2 = rs.getString("leadername");
							if (i == 1) {
								cl.GradeTwo[i - 1].setVisible(true);
								cl.GradeTwo[i - 1].setText(
										str + "                                                                ");
								cl.GradeTwo[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeTwo[i - 1].getLabel().toString();
								cl.GradeTwo[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeTwo[0].getLabel().toString();
											jp.show(cl.GradeTwo[0], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 2) {
								cl.GradeTwo[i - 1].setVisible(true);
								cl.GradeTwo[i - 1].setText(
										str + "                                                                ");
								cl.GradeTwo[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeTwo[i - 1].getLabel().toString();
								cl.GradeTwo[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeTwo[1].getLabel().toString();
											jp.show(cl.GradeTwo[1], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 3) {
								cl.GradeTwo[i - 1].setVisible(true);
								cl.GradeTwo[i - 1].setText(
										str + "                                                                ");
								cl.GradeTwo[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeTwo[i - 1].getLabel().toString();
								cl.GradeTwo[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeTwo[2].getLabel().toString();
											jp.show(cl.GradeTwo[2], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 4) {
								cl.GradeTwo[i - 1].setVisible(true);
								cl.GradeTwo[i - 1].setText(
										str + "                                                                ");
								;
								cl.GradeTwo[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeTwo[i - 1].getLabel().toString();
								cl.GradeTwo[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeTwo[3].getLabel().toString();
											jp.show(cl.GradeTwo[3], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 5) {
								cl.GradeTwo[i - 1].setVisible(true);
								cl.GradeTwo[i - 1].setText(
										str + "                                                                ");
								cl.GradeTwo[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeTwo[i - 1].getLabel().toString();
								cl.GradeTwo[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeTwo[4].getLabel().toString();
											jp.show(cl.GradeTwo[4], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 6) {
								cl.GradeTwo[i - 1].setVisible(true);
								cl.GradeTwo[i - 1].setText(
										str + "                                                                ");
								cl.GradeTwo[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeTwo[i - 1].getLabel().toString();
								cl.GradeTwo[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeTwo[5].getLabel().toString();
											jp.show(cl.GradeTwo[5], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
								cl.twbtn.setEnabled(false);
							}

						}
//					
//					
						data.st.close();
						data.con.close();

					} catch (Exception e) {
						System.out.print(e);
					}

					cl.toGth.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							int s = JOptionPane.showConfirmDialog(null,
									"Do you confirm jumping this class into grade three ", "schmasys",
									JOptionPane.YES_NO_OPTION);
							if (s == 0) {
								String newClasses[] = { "grade three of one", "grade three of two",
										"grade three of three", "grade three of four", "grade three of five",
										"grade three of six" };
								Databases data = new Databases();

								try {
									data.classForname();
									data.con = DriverManager.getConnection("jdbc:sqlite:students.db");
									data.st = data.con.createStatement();

									Databases db = new Databases();
									try {
										db.classForname();
										db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
										db.st = db.con.createStatement();
										String query = "delete from classrooms where classname like 'grade three%'";
										db.st.executeUpdate(query);
										db.con.close();
									} catch (Exception e) {
										System.out.println(e);
									}

									for (int i = 0; i < Class.length; i++) {
										if (!(Class[i] == null)) {
											String shorter = Class[i].substring(0,
													Class[i].indexOf("                    "));

											Databases db1 = new Databases();
											try {
												db1.classForname();
												db1.con = DriverManager.getConnection("jdbc:sqlite:class.db");
												db1.st = db1.con.createStatement();
												String classes = "insert into classrooms (classname) values('"
														+ newClasses[i] + "')";
												db1.st.executeUpdate(classes);
												db1.con.close();
											} catch (Exception e) {
												System.out.println(e);
											}
											String update = "update student set Student_class='" + newClasses[i]
													+ "' where Student_class ='" + shorter + "'";
											data.st.executeUpdate(update);

										}
									}
									data.con.close();
									JOptionPane.showMessageDialog(null, "all of grade two class are jumped", "Schmaysy",
											JOptionPane.INFORMATION_MESSAGE);

								} catch (Exception e) {
									System.out.println(e);
								}
							}
						}
					});

					container.setVisible(false);

				} else if (arg0.getSource() == btn[11]) {
					cl.setVisible(true);
					cl.four.setVisible(false);
					cl.three.setVisible(false);
					cl.two.setVisible(false);
					cl.one.setVisible(false);
					cl.eight.setVisible(false);
					cl.seven.setVisible(false);
					cl.six.setVisible(false);
					cl.five.setVisible(false);
					cl.Four.setVisible(false);
					cl.Three.setVisible(false);
					cl.Two.setVisible(false);
					cl.One.setVisible(true);

					try {

						String query = "select classname , deanname , leadername from classrooms where classname like '%grade one%'";
						PreparedStatement ps = data.con.prepareStatement(query);
						ResultSet rs = ps.executeQuery();
						while (rs.next()) {
							int i = rs.getRow();
							String str = rs.getString(1);
							String str1 = rs.getString("deanname");
							String str2 = rs.getString("leadername");
							if (i == 1) {
								cl.GradeOne[i - 1].setVisible(true);
								cl.GradeOne[i - 1].setText(
										str + "                                                                ");
								cl.GradeOne[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeOne[i - 1].getLabel().toString();
								cl.GradeOne[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeOne[0].getLabel().toString();
											jp.show(cl.GradeOne[0], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 2) {
								cl.GradeOne[i - 1].setVisible(true);
								cl.GradeOne[i - 1].setText(
										str + "                                                                ");
								cl.GradeOne[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeOne[i - 1].getLabel().toString();
								cl.GradeOne[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeOne[1].getLabel().toString();
											jp.show(cl.GradeOne[1], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 3) {
								cl.GradeOne[i - 1].setVisible(true);
								cl.GradeOne[i - 1].setText(
										str + "                                                                ");
								cl.GradeOne[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeOne[i - 1].getLabel().toString();
								cl.GradeOne[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeOne[2].getLabel().toString();
											jp.show(cl.GradeOne[2], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 4) {
								cl.GradeOne[i - 1].setVisible(true);
								cl.GradeOne[i - 1].setText(
										str + "                                                                ");
								;
								cl.GradeOne[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeOne[i - 1].getLabel().toString();
								cl.GradeOne[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeOne[3].getLabel().toString();
											jp.show(cl.GradeOne[3], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;

							} else if (i == 5) {
								cl.GradeOne[i - 1].setVisible(true);
								cl.GradeOne[i - 1].setText(
										str + "                                                                ");
								cl.GradeOne[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeOne[i - 1].getLabel().toString();
								cl.GradeOne[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeOne[4].getLabel().toString();
											jp.show(cl.GradeOne[4], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
							} else if (i == 6) {
								cl.GradeOne[i - 1].setVisible(true);
								cl.GradeOne[i - 1].setText(
										str + "                                                                ");
								cl.GradeOne[i - 1].addActionListener(action1);
								Class[i - 1] = cl.GradeOne[i - 1].getLabel().toString();
								cl.GradeOne[i - 1].addMouseListener(new MouseAdapter() {
									public void mouseReleased(MouseEvent e) {

										if (e.isPopupTrigger()) {
											no = (i - 1);
											nm = cl.GradeOne[5].getLabel().toString();
											jp.show(cl.GradeOne[5], e.getX(), e.getY());

										}

									}
								});
								getter[i - 1] = str;
								dg[i - 1] = str1;
								lg[i - 1] = str2;
								cl.obtn.setEnabled(false);
							}

						}
//					
//					
						data.st.close();
						data.con.close();

					} catch (Exception e) {
						System.out.print(e);
					}
					cl.toGt.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							int s = JOptionPane.showConfirmDialog(null,
									"Do you confirm jumping this class into grade two ", "schmasys",
									JOptionPane.YES_NO_OPTION);
							if (s == 0) {
								String newClasses[] = { "grade two of one", "grade two of two", "grade two of three",
										"grade two of four", "grade two of five", "grade two of six" };
								Databases data = new Databases();

								try {
									data.classForname();
									data.con = DriverManager.getConnection("jdbc:sqlite:students.db");
									data.st = data.con.createStatement();

									Databases db = new Databases();
									try {
										db.classForname();
										db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
										db.st = db.con.createStatement();
										String query = "delete from classrooms where classname like 'grade two%'";
										db.st.executeUpdate(query);
										db.con.close();
									} catch (Exception e) {
										System.out.println(e);
									}

									for (int i = 0; i < Class.length; i++) {
										if (!(Class[i] == null)) {
											String shorter = Class[i].substring(0,
													Class[i].indexOf("                    "));

											Databases db1 = new Databases();
											try {
												db1.classForname();
												db1.con = DriverManager.getConnection("jdbc:sqlite:class.db");
												db1.st = db1.con.createStatement();
												String classes = "insert into classrooms (classname) values('"
														+ newClasses[i] + "')";
												db1.st.executeUpdate(classes);
												db1.con.close();
											} catch (Exception e) {
												System.out.println(e);
											}
											String update = "update student set Student_class='" + newClasses[i]
													+ "' where Student_class ='" + shorter + "'";
											data.st.executeUpdate(update);

										}
									}
									data.con.close();
									JOptionPane.showMessageDialog(null, "all of grade one class are jumped", "Schmaysy",
											JOptionPane.INFORMATION_MESSAGE);

								} catch (Exception e) {
									System.out.println(e);
								}
							}
						}
					});

					container.setVisible(false);

				}

				data.con.close();
			} catch (Exception e) {
				System.out.println(e);

			}

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

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

		}

	}

	public void getQuery(String query) {
		this.query = query;

	}

	public static String setQuery() {
		return query;
	}

	private class actionListener1 implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			Databases data = new Databases();
			try {
				data.classForname();
				data.con = DriverManager.getConnection("jdbc:sqlite:students.db");
				data.st = data.con.createStatement();

				for (int i = 0; i < cl.FormFour.length; i++) {
					if (arg0.getSource() == cl.FormFour[i]) {

						String execute1 = "SELECT RowId as ID, Student_name  as NAME FROM student where Student_class='"
								+ getter[i] + "' ORDER by Student_name asc;";
						ResultSet rs1 = data.st.executeQuery(execute1);

						tb.tb.setModel(DbUtils.resultSetToTableModel(rs1));
						rowId(tb.tb);
						ResultSet rs2 = data.st.executeQuery(execute1);
						int ns = 0;
						while (rs2.next()) {
							ns++;
						}

						tb.tb.setAutoResizeMode(0);
						collw(tb.tb);
						tb.Class = getter[i];
						tb.covers[3].setText(getter[i]);
						tb.covers[4].setText(dg[i]);
						tb.covers[5].setText(lg[i]);
						cl.setVisible(false);
						tb.setVisible(true);

					} else if (arg0.getSource() == cl.FormThree[i]) {
						String execute1 = "SELECT Rowid as ID, Student_name as NAME FROM student where Student_class='"
								+ getter[i] + "' ORDER by Student_name asc;";

						ResultSet rs1 = data.st.executeQuery(execute1);

						tb.tb.setModel(DbUtils.resultSetToTableModel(rs1));
						tb.tb.setAutoResizeMode(0);
						collw(tb.tb);
						rowId(tb.tb);
						ResultSet rs2 = data.st.executeQuery(execute1);
						int ns = 0;
						while (rs2.next()) {
							ns++;
						}
						tb.no.setText("" + ns + "");
						tb.Class = getter[i];

						tb.covers[3].setText(getter[i]);
						tb.covers[4].setText(dg[i]);
						tb.covers[5].setText(lg[i]);
						cl.setVisible(false);
						tb.setVisible(true);
					} else if (arg0.getSource() == cl.FormTwo[i]) {
						String execute1 = "SELECT Rowid as ID, Student_name as NAME FROM student where Student_class='"
								+ getter[i] + "' ORDER by Student_name asc ;";

						ResultSet rs1 = data.st.executeQuery(execute1);

						tb.tb.setModel(DbUtils.resultSetToTableModel(rs1));
						tb.tb.setAutoResizeMode(0);
						collw(tb.tb);
						rowId(tb.tb);
						ResultSet rs2 = data.st.executeQuery(execute1);
						int ns = 0;
						while (rs2.next()) {
							ns++;
						}
						tb.no.setText("" + ns + "");
						tb.Class = getter[i];
						tb.covers[3].setText(getter[i]);
						tb.covers[4].setText(dg[i]);
						tb.covers[5].setText(lg[i]);
						cl.setVisible(false);
						tb.setVisible(true);
					} else if (arg0.getSource() == cl.FormOne[i]) {
						String execute1 = "SELECT Rowid as ID, Student_name as NAME FROM student where Student_class='"
								+ getter[i] + "' ORDER by Student_name asc ;";

						ResultSet rs1 = data.st.executeQuery(execute1);

						tb.tb.setModel(DbUtils.resultSetToTableModel(rs1));
						tb.tb.setAutoResizeMode(0);
						collw(tb.tb);
						rowId(tb.tb);
						ResultSet rs2 = data.st.executeQuery(execute1);
						int ns = 0;
						while (rs2.next()) {
							ns++;
						}
						tb.no.setText("" + ns + "");
						tb.Class = getter[i];
						tb.covers[3].setText(getter[i]);
						tb.covers[4].setText(dg[i]);
						tb.covers[5].setText(lg[i]);
						cl.setVisible(false);
						tb.setVisible(true);
					} else if (arg0.getSource() == cl.GradeEight[i]) {
						String execute1 = "SELECT Rowid as ID, Student_name as NAME FROM student where Student_class='"
								+ getter[i] + "' ORDER by Student_name asc ;";

						ResultSet rs1 = data.st.executeQuery(execute1);

						tb.tb.setModel(DbUtils.resultSetToTableModel(rs1));
						tb.tb.setAutoResizeMode(0);
						collw(tb.tb);
						rowId(tb.tb);
						ResultSet rs2 = data.st.executeQuery(execute1);
						int ns = 0;
						while (rs2.next()) {
							ns++;
						}
						tb.no.setText("" + ns + "");
						tb.Class = getter[i];
						tb.covers[3].setText(getter[i]);
						tb.covers[4].setText(dg[i]);
						tb.covers[5].setText(lg[i]);
						cl.setVisible(false);
						tb.setVisible(true);
					} else if (arg0.getSource() == cl.GradeSeven[i]) {
						String execute1 = "SELECT  Rowid as ID,Student_name as NAME FROM student where Student_class='"
								+ getter[i] + "' ORDER by Student_name asc ;";

						ResultSet rs1 = data.st.executeQuery(execute1);

						tb.tb.setModel(DbUtils.resultSetToTableModel(rs1));
						tb.tb.setAutoResizeMode(0);
						collw(tb.tb);
						rowId(tb.tb);
						ResultSet rs2 = data.st.executeQuery(execute1);
						int ns = 0;
						while (rs2.next()) {
							ns++;
						}
						tb.no.setText("" + ns + "");
						tb.Class = getter[i];
						tb.covers[3].setText(getter[i]);
						tb.covers[4].setText(dg[i]);
						tb.covers[5].setText(lg[i]);
						cl.setVisible(false);
						tb.setVisible(true);
					} else if (arg0.getSource() == cl.GradeSix[i]) {
						String execute1 = "SELECT Rowid as ID, Student_name as NAME FROM student where Student_class='"
								+ getter[i] + "' ORDER by Student_name asc ;";

						ResultSet rs1 = data.st.executeQuery(execute1);

						tb.tb.setModel(DbUtils.resultSetToTableModel(rs1));
						tb.tb.setAutoResizeMode(0);
						collw(tb.tb);
						rowId(tb.tb);
						ResultSet rs2 = data.st.executeQuery(execute1);
						int ns = 0;
						while (rs2.next()) {
							ns++;
						}
						tb.no.setText("" + ns + "");
						tb.Class = getter[i];
						tb.covers[3].setText(getter[i]);
						tb.covers[4].setText(dg[i]);
						tb.covers[5].setText(lg[i]);
						cl.setVisible(false);
						tb.setVisible(true);
					} else if (arg0.getSource() == cl.GradeFive[i]) {
						String execute1 = "SELECT Rowid as ID, Student_name as NAME FROM student where Student_class='"
								+ getter[i] + "' ORDER by Student_name asc ;";

						ResultSet rs1 = data.st.executeQuery(execute1);

						tb.tb.setModel(DbUtils.resultSetToTableModel(rs1));
						tb.tb.setAutoResizeMode(0);
						collw(tb.tb);

						ResultSet rs2 = data.st.executeQuery(execute1);
						int ns = 0;
						while (rs2.next()) {
							ns++;
						}
						tb.no.setText("" + ns + "");
						tb.Class = getter[i];
						tb.covers[3].setText(getter[i]);
						tb.covers[4].setText(dg[i]);
						tb.covers[5].setText(lg[i]);
						cl.setVisible(false);
						tb.setVisible(true);
					} else if (arg0.getSource() == cl.GradeFour[i]) {
						String execute1 = "SELECT Rowid as ID , Student_name as NAME FROM student where Student_class='"
								+ getter[i] + "' ORDER by Student_name asc ;";

						ResultSet rs1 = data.st.executeQuery(execute1);

						tb.tb.setModel(DbUtils.resultSetToTableModel(rs1));
						tb.tb.setAutoResizeMode(0);
						collw(tb.tb);

						ResultSet rs2 = data.st.executeQuery(execute1);
						int ns = 0;
						while (rs2.next()) {
							ns++;
						}
						tb.no.setText("" + ns + "");
						tb.Class = getter[i];
						tb.covers[3].setText(getter[i]);
						tb.covers[4].setText(dg[i]);
						tb.covers[5].setText(lg[i]);
						cl.setVisible(false);
						tb.setVisible(true);
					} else if (arg0.getSource() == cl.GradeThree[i]) {
						String execute1 = "SELECT Rowid as ID, Student_name as NAME FROM student where Student_class='"
								+ getter[i] + "' ORDER by Student_name asc ;";

						ResultSet rs1 = data.st.executeQuery(execute1);

						tb.tb.setModel(DbUtils.resultSetToTableModel(rs1));
						tb.tb.setAutoResizeMode(0);
						collw(tb.tb);

						ResultSet rs2 = data.st.executeQuery(execute1);
						int ns = 0;
						while (rs2.next()) {
							ns++;
						}
						tb.no.setText("" + ns + "");
						tb.Class = getter[i];
						tb.covers[3].setText(getter[i]);
						tb.covers[4].setText(dg[i]);
						tb.covers[5].setText(lg[i]);
						cl.setVisible(false);
						tb.setVisible(true);
					} else if (arg0.getSource() == cl.GradeTwo[i]) {
						String execute1 = "SELECT Rowid as ID, Student_name as NAME FROM student where Student_class='"
								+ getter[i] + "' ORDER by Student_name asc ;";

						ResultSet rs1 = data.st.executeQuery(execute1);

						tb.tb.setModel(DbUtils.resultSetToTableModel(rs1));
						tb.tb.setAutoResizeMode(0);
						collw(tb.tb);
						rowId(tb.tb);
						ResultSet rs2 = data.st.executeQuery(execute1);
						int ns = 0;
						while (rs2.next()) {
							ns++;
						}
						tb.no.setText("" + ns + "");
						tb.Class = getter[i];
						tb.covers[3].setText(getter[i]);
						tb.covers[4].setText(dg[i]);
						tb.covers[5].setText(lg[i]);
						cl.setVisible(false);
						tb.setVisible(true);
					} else if (arg0.getSource() == cl.GradeOne[i]) {
						String execute1 = "SELECT Rowid as ID, Student_name as NAME FROM student where Student_class='"
								+ getter[i] + "' ORDER by Student_name asc ;";

						ResultSet rs1 = data.st.executeQuery(execute1);

						tb.tb.setModel(DbUtils.resultSetToTableModel(rs1));
						tb.tb.setAutoResizeMode(0);
						collw(tb.tb);
						rowId(tb.tb);
						ResultSet rs2 = data.st.executeQuery(execute1);
						int ns = 0;
						while (rs2.next()) {
							ns++;
						}
						tb.no.setText("" + ns + "");
						tb.Class = getter[i];
						tb.covers[3].setText(getter[i]);
						tb.covers[4].setText(dg[i]);
						tb.covers[5].setText(lg[i]);
						cl.setVisible(false);
						tb.setVisible(true);
					}

				}
				if (arg0.getSource() == tb.btn) {
					tb.setVisible(false);
					cl.setVisible(true);
				}
				data.st.close();
				data.con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		private void collw(JTable tb2) {
			TableColumn col = null;
			for (int i = 0; i < tb2.getColumnCount(); i++) {
				col = tb2.getColumnModel().getColumn(i);
				if (i == 0) {
					col.setPreferredWidth(50);

				} else {
					col.setPreferredWidth(1040 - 50);
				}

			}
		}

		private void rowId(JTable tb) {
			for (int row = 0; row < tb.getRowCount(); row++) {
				tb.setValueAt(row + 1, row, 0);
			}
		}

	}

}

class Classes extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel backgound, lb;
	private String urlImage[] = { "/image/change.png", "/image/ab.png" };
	private JPanel pane, uderLine, underline;
	public JButton close;
	public JPanel four, three, two, one, eight, seven, six, five, Four, Three, Two, One, CloseH;
	private JLabel lb2, lb1, lb3, lb4, lb5, lb6;
	private JTextField Ftxt[], txt, Ttxt[], Thtxt, Twtxt[], TWtxt, Otxt[], otxt, eitxt[], Eitxt, setxt[], Setxt,
			sitxt[], Sitxt, fitxt[], Fitxt, fotxt[], Fotxt, TRtxt[], Trtxt, Twotxt[], twotxt, Ontxt[], ontxt;
	public JButton Fbtn, Tbtn, TWbtn, Obtn, eibtn, sebtn, sibtn, fibtn, fobtn, thbtn, twbtn, obtn;
	private String placeH[] = { "Enter dean name", "Enter leader name" };
//	private String tplaceH[] = { "Enter classroom name", "Enter dean name", "Enter leader name" };

	public JButton FormFour[], FormThree[], FormTwo[], FormOne[], GradeEight[], GradeSeven[], GradeSix[], GradeFive[],
			GradeFour[], GradeThree[], GradeTwo[], GradeOne[];
	private JLabel classroom, welcome, somtech;
	public JButton graduated, toFf, toFth, toFt, toFo, toGe, toGs, toGsi, toGfi, toGf, toGth, toGt;
	public JComboBox[] comp = new JComboBox[12];
	private String subjects = null;

	focusListener focus = new focusListener();
	actionListener action = new actionListener();
	keyListener key = new keyListener();

	public Classes() {
		Compounents();
		background();
		panels();
		container();
		fuctions();
	}

	private void Compounents() {

		BufferedImage img1 = null;
		try {

			img1 = ImageIO.read(getClass().getResource("/image/back1.png"));

		} catch (Exception e) {
			System.out.println(e);
		}
		close = new JButton(new ImageIcon(img1));
		close.setBounds(13, 13, 30, 30);
		close.setBackground(null);
		close.setOpaque(false);
		close.setBorder(BorderFactory.createSoftBevelBorder(10));

		classroom = new JLabel("Create a classroom");
		classroom.setBounds(500, 60, 300, 35);
		classroom.setForeground(Color.black);
		classroom.setFont(new Font("Calibri", Font.BOLD, 30));

		uderLine = new JPanel();
		uderLine.setBackground(new Color(43, 55, 149));
		uderLine.setBounds(500, 100, 50, 5);

		welcome = new JLabel("Welcome to");
		welcome.setBounds(35, 90, 300, 55);
		welcome.setForeground(Color.white);
		welcome.setFont(new Font("Calibri", Font.BOLD, 30));

		somtech = new JLabel("Somtech Schmasys");
		somtech.setBounds(35, 130, 300, 55);
		somtech.setForeground(Color.white);
		somtech.setFont(new Font("Calibri", Font.BOLD, 30));

		underline = new JPanel();
		underline.setBackground(Color.white);
		underline.setBounds(35, 190, 40, 5);

		lb2 = new JLabel("Check first your class rooms before  ");
		lb2.setForeground(Color.gray);
		lb2.setBounds(500, 130, 400, 20);
		;
		lb2.setFont(new Font("Calibri", Font.PLAIN, 15));

		lb1 = new JLabel("creating new class room!!  because this system ");
		lb1.setForeground(Color.gray);
		lb1.setBounds(500, 150, 400, 20);
		;
		lb1.setFont(new Font("Calibri", Font.PLAIN, 15));

		lb3 = new JLabel("don't permit two class rooms in same name.");
		lb3.setForeground(Color.gray);
		lb3.setBounds(500, 170, 400, 20);
		;
		lb3.setFont(new Font("Calibri", Font.PLAIN, 15));

		lb4 = new JLabel("When  you writing name of classroom  follow ");
		lb4.setForeground(Color.gray);
		lb4.setBounds(500, 190, 400, 20);
		;
		lb4.setFont(new Font("Calibri", Font.PLAIN, 15));

		lb5 = new JLabel("this Example (class room one) keep there to ");
		lb5.setForeground(Color.gray);
		lb5.setBounds(500, 210, 400, 20);
		;
		lb5.setFont(new Font("Calibri", Font.PLAIN, 15));

		lb6 = new JLabel("give numbers thank you for reading .");
		lb6.setForeground(Color.gray);
		lb6.setBounds(500, 230, 400, 20);
		;
		lb6.setFont(new Font("Calibri", Font.PLAIN, 15));

	}

	private void background() {
		BufferedImage img = null;
		try {

			img = ImageIO.read(getClass().getResource(urlImage[0]));

		} catch (Exception e) {
			System.out.println(e);
		}

		lb = new JLabel();
		lb.setBounds(0, 0, 930, 600);
		lb.setIcon(new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(lb.getWidth(), lb.getHeight(),
				Image.SCALE_AREA_AVERAGING)));

		BufferedImage img1 = null;
		try {

			img1 = ImageIO.read(getClass().getResource(urlImage[1]));

		} catch (Exception e) {
			System.out.println(e);
		}
		backgound = new JLabel();
		backgound.setBounds(0, 0, 1045, 670);
		backgound.setIcon(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(backgound.getWidth(),
				backgound.getHeight(), Image.SCALE_AREA_AVERAGING)));

	}

	private void panels() {
		Formfour();
		Formthree();
		Formtwo();
		Formone();
		eight();
		seven();
		six();
		five();
		four();
		three();
		two();
		one();

	}

	private void one() {
		// TODO Auto-generated method stub
		One = new JPanel();
		One.setBounds(60, 40, 930, 600);
		One.setLayout(null);
		One.setBackground(Color.black);
		One.setOpaque(false);
		One.setVisible(false);

		ontxt = new JTextField();
//	
		toGt = new JButton("upgrade");
		toGt.setBounds(23, 275, 350, 25);
		toGt.setBorder(BorderFactory.createSoftBevelBorder(10));
		toGt.setBackground(new Color(97, 146, 194));
		toGt.setForeground(Color.white);
		toGt.setFont(new Font("Calibri", Font.PLAIN, 15));
		One.add(toGt);
		One.add(ontxt);

		String subj[] = { "grade one of one", "grade one of two", "grade one of three", "grade one of four",
				"grade one of five", "grade one of six" };
		Databases db = new Databases();
		try {
			db.classForname();
			db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
			db.st = db.con.createStatement();
			ResultSet rs = db.st.executeQuery(
					"select classname from classrooms where classname like 'grade one%' order by classname like'one%'");
			String rr[] = new String[6];

			while (rs.next()) {
				rr[rs.getRow() - 1] = rs.getString("classname");
			}
			int g = 0;
			for (int i = 0; i < rr.length; i++) {
				if (rr[i] == null || !subj[i].equalsIgnoreCase(rr[i])) {
					g += i - i;

					g++;
				}
			}
			String in[] = new String[g];
			int c = 0;
			for (int i = 0; i < rr.length; i++) {
				if (rr[i] == null || !subj[i].equalsIgnoreCase(rr[i])) {
					c += i - i;
					in[c] = subj[i];
					c++;
				}
			}

			comp[11] = new JComboBox(in);
			db.con.close();
		} catch (Exception e) {
			System.out.print(e);
		}
		comp[11].setBounds(500, 270, 330, 45);
		comp[11].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(44, 50, 142)));
		comp[11].setForeground(Color.black);
		comp[11].setFont(new Font("Calibri", Font.PLAIN, 20));
		comp[11].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (comp[11].getSelectedIndex() == 0) {
					subjects = comp[11].getSelectedItem().toString();
					comp[11].removeItem(subjects);
				} else {

					JOptionPane.showMessageDialog(null, "keep the order follow the Staps", "Schmasys warring",
							JOptionPane.WARNING_MESSAGE);
				}
			}

		});
		One.add(comp[11]);

		obtn = new JButton("CREATE CLASSROOM");
		obtn.setBackground(new Color(44, 50, 142));
		obtn.setBounds(500, 500, 330, 55);
		obtn.setForeground(Color.white);
		obtn.setFont(new Font("Calibri", Font.PLAIN, 25));
		obtn.setBorder(BorderFactory.createSoftBevelBorder(10));
		obtn.addActionListener(action);
//		Tbtn.addFocusListener(focus);
		One.add(obtn);
		int Blocation[] = { 345, 420 };
		Ontxt = new JTextField[2];
		for (int i = 0; i < Ontxt.length; i++) {
			Ontxt[i] = new JTextField();
			Ontxt[i].setBounds(500, Blocation[i], 330, 45);
			Ontxt[i].addKeyListener(key);
			Ontxt[i].setFont(new Font("Calibri", Font.PLAIN, 20));
			Ontxt[i].setForeground(Color.LIGHT_GRAY);
			Ontxt[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(44, 50, 142)));
			if (i == 0) {

				Ontxt[i].addFocusListener(focus);
				Ontxt[i].setText(placeH[i]);
			} else if (i == 1) {
				Ontxt[i].addFocusListener(focus);
				Ontxt[i].setText(placeH[i]);

			} else if (i == 2) {
				Ontxt[i].addFocusListener(focus);
				Ontxt[i].setText(placeH[i]);

			}
			One.add(Ontxt[i]);

		}

		int BLocation[] = { 310, 355, 400, 445, 490, 535 };
		GradeOne = new JButton[6];
		for (int i = 0; i < GradeOne.length; i++) {
			GradeOne[i] = new JButton();
			GradeOne[i].setBounds(23, BLocation[i], 350, 30);
			GradeOne[i].setText("Class room " + (i + 1));
			GradeOne[i].setBorder(BorderFactory.createSoftBevelBorder(10));
			GradeOne[i].setBackground(new Color(50, 112, 200));
			GradeOne[i].setForeground(Color.white);
			GradeOne[i].setFont(new Font("Calibri", Font.PLAIN, 15));
			GradeOne[i].addActionListener(action);
			GradeOne[i].setVisible(false);
			One.add(GradeOne[i]);

		}
	}

	private void two() {
		// TODO Auto-generated method stub
		Two = new JPanel();
		Two.setBounds(60, 40, 930, 600);
		Two.setLayout(null);
		Two.setBackground(Color.black);
		Two.setOpaque(false);
		Two.setVisible(false);

		twotxt = new JTextField();
//		
		toGth = new JButton("upgrade");
		toGth.setBounds(23, 275, 350, 25);
		toGth.setBorder(BorderFactory.createSoftBevelBorder(10));
		toGth.setBackground(new Color(97, 146, 194));
		toGth.setForeground(Color.white);
		toGth.setFont(new Font("Calibri", Font.PLAIN, 15));
		Two.add(toGth);
		Two.add(twotxt);

		String subj[] = { "grade two of one", "grade two of two", "grade two of three", "grade two of four",
				"grade two of five", "grade two of six" };
		Databases db = new Databases();
		try {
			db.classForname();
			db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
			db.st = db.con.createStatement();
			ResultSet rs = db.st.executeQuery(
					"select classname from classrooms where classname like 'grade two%' order by classname like'one%'");
			String rr[] = new String[6];

			while (rs.next()) {
				rr[rs.getRow() - 1] = rs.getString("classname");
			}
			int g = 0;
			for (int i = 0; i < rr.length; i++) {
				if (rr[i] == null || !subj[i].equalsIgnoreCase(rr[i])) {
					g += i - i;

					g++;
				}
			}
			String in[] = new String[g];
			int c = 0;
			for (int i = 0; i < rr.length; i++) {
				if (rr[i] == null || !subj[i].equalsIgnoreCase(rr[i])) {
					c += i - i;
					in[c] = subj[i];
					c++;
				}
			}

			comp[10] = new JComboBox(in);
			db.con.close();
		} catch (Exception e) {
			System.out.print(e);
		}
		comp[10].setBounds(500, 270, 330, 45);
		comp[10].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(44, 50, 142)));
		comp[10].setForeground(Color.black);
		comp[10].setFont(new Font("Calibri", Font.PLAIN, 20));
		comp[10].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (comp[10].getSelectedIndex() == 0) {
					subjects = comp[10].getSelectedItem().toString();
					comp[10].removeItem(subjects);
				} else {

					JOptionPane.showMessageDialog(null, "keep the order follow the Staps", "Schmasys warring",
							JOptionPane.WARNING_MESSAGE);
				}
			}

		});
		Two.add(comp[10]);

		twbtn = new JButton("CREATE CLASSROOM");
		twbtn.setBackground(new Color(44, 50, 142));
		twbtn.setBounds(500, 500, 330, 55);
		twbtn.setForeground(Color.white);
		twbtn.setFont(new Font("Calibri", Font.PLAIN, 25));
		twbtn.setBorder(BorderFactory.createSoftBevelBorder(10));
//		Tbtn.addFocusListener(focus);
		twbtn.addActionListener(action);
		Two.add(twbtn);
		int Blocation[] = { 345, 420 };
		Twotxt = new JTextField[2];
		for (int i = 0; i < TRtxt.length; i++) {
			Twotxt[i] = new JTextField();
			Twotxt[i].setBounds(500, Blocation[i], 330, 45);
			Twotxt[i].addKeyListener(key);
			Twotxt[i].setFont(new Font("Calibri", Font.PLAIN, 20));
			Twotxt[i].setForeground(Color.LIGHT_GRAY);
			Twotxt[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(44, 50, 142)));
			if (i == 0) {

				Twotxt[i].addFocusListener(focus);
				Twotxt[i].setText(placeH[i]);
			} else if (i == 1) {
				Twotxt[i].addFocusListener(focus);
				Twotxt[i].setText(placeH[i]);

			} else if (i == 2) {
				Twotxt[i].addFocusListener(focus);
				Twotxt[i].setText(placeH[i]);

			}
			Two.add(Twotxt[i]);

		}

		int BLocation[] = { 310, 355, 400, 445, 490, 535 };
		GradeTwo = new JButton[6];
		for (int i = 0; i < GradeTwo.length; i++) {
			GradeTwo[i] = new JButton();
			GradeTwo[i].setBounds(23, BLocation[i], 350, 30);
			GradeTwo[i].setText("Class room " + (i + 1));
			GradeTwo[i].setBorder(BorderFactory.createSoftBevelBorder(10));
			GradeTwo[i].setBackground(new Color(50, 112, 200));
			GradeTwo[i].setForeground(Color.white);
			GradeTwo[i].setFont(new Font("Calibri", Font.PLAIN, 15));
			GradeTwo[i].addActionListener(action);
			GradeTwo[i].setVisible(false);
			Two.add(GradeTwo[i]);

		}

	}

	private void three() {
		// TODO Auto-generated method stub
		Three = new JPanel();
		Three.setBounds(60, 40, 930, 600);
		Three.setLayout(null);
		Three.setBackground(Color.black);
		Three.setOpaque(false);
		Three.setVisible(false);

		Trtxt = new JTextField();
//		
		toGf = new JButton("upgrade");
		toGf.setBounds(23, 275, 350, 25);
		toGf.setBorder(BorderFactory.createSoftBevelBorder(10));
		toGf.setBackground(new Color(97, 146, 194));
		toGf.setForeground(Color.white);
		toGf.setFont(new Font("Calibri", Font.PLAIN, 15));
		Three.add(toGf);
		Three.add(Trtxt);

		String subj[] = { "grade three of one", "grade three of two", "grade three of three", "grade three of four",
				"grade three of five", "grade three of six" };
		Databases db = new Databases();
		try {
			db.classForname();
			db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
			db.st = db.con.createStatement();
			ResultSet rs = db.st.executeQuery(
					"select classname from classrooms where classname like 'grade three%' order by classname like'one%'");
			String rr[] = new String[6];

			while (rs.next()) {
				rr[rs.getRow() - 1] = rs.getString("classname");
			}
			int g = 0;
			for (int i = 0; i < rr.length; i++) {
				if (rr[i] == null || !subj[i].equalsIgnoreCase(rr[i])) {
					g += i - i;

					g++;
				}
			}
			String in[] = new String[g];
			int c = 0;
			for (int i = 0; i < rr.length; i++) {
				if (rr[i] == null || !subj[i].equalsIgnoreCase(rr[i])) {
					c += i - i;
					in[c] = subj[i];
					c++;
				}
			}

			comp[9] = new JComboBox(in);
			db.con.close();
		} catch (Exception e) {
			System.out.print(e);
		}
		comp[9].setBounds(500, 270, 330, 45);
		comp[9].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(44, 50, 142)));
		comp[9].setForeground(Color.black);
		comp[9].setFont(new Font("Calibri", Font.PLAIN, 20));
		comp[9].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (comp[9].getSelectedIndex() == 0) {
					subjects = comp[9].getSelectedItem().toString();
					comp[9].removeItem(subjects);
				} else {

					JOptionPane.showMessageDialog(null, "keep the order follow the Staps", "Schmasys warring",
							JOptionPane.WARNING_MESSAGE);
				}
			}

		});
		Three.add(comp[9]);

		thbtn = new JButton("CREATE CLASSROOM");
		thbtn.setBackground(new Color(44, 50, 142));
		thbtn.setBounds(500, 500, 330, 55);
		thbtn.setForeground(Color.white);
		thbtn.setFont(new Font("Calibri", Font.PLAIN, 25));
		thbtn.setBorder(BorderFactory.createSoftBevelBorder(10));
		thbtn.addActionListener(action);
//		Tbtn.addFocusListener(focus);
		Three.add(thbtn);
		int Blocation[] = { 345, 420 };
		TRtxt = new JTextField[2];
		for (int i = 0; i < TRtxt.length; i++) {
			TRtxt[i] = new JTextField();
			TRtxt[i].setBounds(500, Blocation[i], 330, 45);
			TRtxt[i].addKeyListener(key);
			TRtxt[i].setFont(new Font("Calibri", Font.PLAIN, 20));
			TRtxt[i].setForeground(Color.LIGHT_GRAY);
			TRtxt[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(44, 50, 142)));
			if (i == 0) {

				TRtxt[i].addFocusListener(focus);
				TRtxt[i].setText(placeH[i]);
			} else if (i == 1) {
				TRtxt[i].addFocusListener(focus);
				TRtxt[i].setText(placeH[i]);

			} else if (i == 2) {
				TRtxt[i].addFocusListener(focus);
				TRtxt[i].setText(placeH[i]);

			}
			Three.add(TRtxt[i]);

		}

		int BLocation[] = { 310, 355, 400, 445, 490, 535 };
		GradeThree = new JButton[6];
		for (int i = 0; i < GradeThree.length; i++) {
			GradeThree[i] = new JButton();
			GradeThree[i].setBounds(23, BLocation[i], 350, 30);
			GradeThree[i].setText("Class room " + (i + 1));
			GradeThree[i].setBorder(BorderFactory.createSoftBevelBorder(10));
			GradeThree[i].setBackground(new Color(50, 112, 200));
			GradeThree[i].setForeground(Color.white);
			GradeThree[i].setFont(new Font("Calibri", Font.PLAIN, 15));
			GradeThree[i].addActionListener(action);
			GradeThree[i].setVisible(false);
			Three.add(GradeThree[i]);

		}
	}

	private void four() {
		// TODO Auto-generated method stub
		Four = new JPanel();
		Four.setBounds(60, 40, 930, 600);
		Four.setLayout(null);
		Four.setBackground(Color.black);
		Four.setOpaque(false);
		Four.setVisible(false);

		Fotxt = new JTextField();
//		
		toGfi = new JButton("upgrade");
		toGfi.setBounds(23, 275, 350, 25);
		toGfi.setBorder(BorderFactory.createSoftBevelBorder(10));
		toGfi.setBackground(new Color(97, 146, 194));
		toGfi.setForeground(Color.white);
		toGfi.setFont(new Font("Calibri", Font.PLAIN, 15));
		Four.add(toGfi);
		Four.add(Fotxt);

		String subj[] = { "grade four of one", "grade four of two", "grade four of three", "grade four of four",
				"grade four of five", "grade four of six" };
		Databases db = new Databases();
		try {
			db.classForname();
			db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
			db.st = db.con.createStatement();
			ResultSet rs = db.st.executeQuery(
					"select classname from classrooms where classname like 'grade four%' order by classname like'one%'");
			String rr[] = new String[6];

			while (rs.next()) {
				rr[rs.getRow() - 1] = rs.getString("classname");
			}
			int g = 0;
			for (int i = 0; i < rr.length; i++) {
				if (rr[i] == null || !subj[i].equalsIgnoreCase(rr[i])) {
					g += i - i;

					g++;
				}
			}
			String in[] = new String[g];
			int c = 0;
			for (int i = 0; i < rr.length; i++) {
				if (rr[i] == null || !subj[i].equalsIgnoreCase(rr[i])) {
					c += i - i;
					in[c] = subj[i];
					c++;
				}
			}

			comp[8] = new JComboBox(in);
			db.con.close();
		} catch (Exception e) {
			System.out.print(e);
		}
		comp[8].setBounds(500, 270, 330, 45);
		comp[8].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(44, 50, 142)));
		comp[8].setForeground(Color.black);
		comp[8].setFont(new Font("Calibri", Font.PLAIN, 20));
		comp[8].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (comp[8].getSelectedIndex() == 0) {
					subjects = comp[8].getSelectedItem().toString();
					comp[8].removeItem(subjects);
				} else {

					JOptionPane.showMessageDialog(null, "keep the order follow the Staps", "Schmasys warring",
							JOptionPane.WARNING_MESSAGE);
				}
			}

		});
		Four.add(comp[8]);

		fobtn = new JButton("CREATE CLASSROOM");
		fobtn.setBackground(new Color(44, 50, 142));
		fobtn.setBounds(500, 500, 330, 55);
		fobtn.setForeground(Color.white);
		fobtn.setFont(new Font("Calibri", Font.PLAIN, 25));
		fobtn.setBorder(BorderFactory.createSoftBevelBorder(10));
//		Tbtn.addFocusListener(focus);
		fobtn.addActionListener(action);
		Four.add(fobtn);
		int Blocation[] = { 345, 420 };
		fotxt = new JTextField[2];
		for (int i = 0; i < fotxt.length; i++) {
			fotxt[i] = new JTextField();
			fotxt[i].setBounds(500, Blocation[i], 330, 45);
			fotxt[i].addKeyListener(key);
			fotxt[i].setFont(new Font("Calibri", Font.PLAIN, 20));
			fotxt[i].setForeground(Color.LIGHT_GRAY);
			fotxt[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(44, 50, 142)));
			if (i == 0) {

				fotxt[i].addFocusListener(focus);
				fotxt[i].setText(placeH[i]);
			} else if (i == 1) {
				fotxt[i].addFocusListener(focus);
				fotxt[i].setText(placeH[i]);

			} else if (i == 2) {
				fotxt[i].addFocusListener(focus);
				fotxt[i].setText(placeH[i]);

			}
			Four.add(fotxt[i]);

		}

		int BLocation[] = { 310, 355, 400, 445, 490, 535 };
		GradeFour = new JButton[6];
		for (int i = 0; i < GradeFour.length; i++) {
			GradeFour[i] = new JButton();
			GradeFour[i].setBounds(23, BLocation[i], 350, 30);
			GradeFour[i].setText("Class room " + (i + 1));
			GradeFour[i].setBorder(BorderFactory.createSoftBevelBorder(10));
			GradeFour[i].setBackground(new Color(50, 112, 200));
			GradeFour[i].setForeground(Color.white);
			GradeFour[i].setFont(new Font("Calibri", Font.PLAIN, 15));
			GradeFour[i].addActionListener(action);
			GradeFour[i].setVisible(false);
			Four.add(GradeFour[i]);

		}

	}

	private void five() {
		// TODO Auto-generated method stub
		five = new JPanel();
		five.setBounds(60, 40, 930, 600);
		five.setLayout(null);
		five.setBackground(Color.black);
		five.setOpaque(false);
		five.setVisible(false);

		Fitxt = new JTextField();
//		
		toGsi = new JButton("upgrade");
		toGsi.setBounds(23, 275, 350, 25);
		toGsi.setBorder(BorderFactory.createSoftBevelBorder(10));
		toGsi.setBackground(new Color(97, 146, 194));
		toGsi.setForeground(Color.white);
		toGsi.setFont(new Font("Calibri", Font.PLAIN, 15));
		five.add(toGsi);
		five.add(Fitxt);

		String subj[] = { "grade five of one", "grade five of two", "grade five of three", "grade five of four",
				"grade five of five", "grade five of six" };
		Databases db = new Databases();
		try {
			db.classForname();
			db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
			db.st = db.con.createStatement();
			ResultSet rs = db.st.executeQuery(
					"select classname from classrooms where classname like 'grade five%' order by classname like'one%'");
			String rr[] = new String[6];

			while (rs.next()) {
				rr[rs.getRow() - 1] = rs.getString("classname");
			}
			int g = 0;
			for (int i = 0; i < rr.length; i++) {
				if (rr[i] == null || !subj[i].equalsIgnoreCase(rr[i])) {
					g += i - i;

					g++;
				}
			}
			String in[] = new String[g];
			int c = 0;
			for (int i = 0; i < rr.length; i++) {
				if (rr[i] == null || !subj[i].equalsIgnoreCase(rr[i])) {
					c += i - i;
					in[c] = subj[i];
					c++;
				}
			}

			comp[7] = new JComboBox(in);
			db.con.close();
		} catch (Exception e) {
			System.out.print(e);
		}
		comp[7].setBounds(500, 270, 330, 45);
		comp[7].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(44, 50, 142)));
		comp[7].setForeground(Color.black);
		comp[7].setFont(new Font("Calibri", Font.PLAIN, 20));
		comp[7].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (comp[7].getSelectedIndex() == 0) {
					subjects = comp[7].getSelectedItem().toString();
					comp[7].removeItem(subjects);
				} else {

					JOptionPane.showMessageDialog(null, "keep the order follow the Staps", "Schmasys warring",
							JOptionPane.WARNING_MESSAGE);
				}
			}

		});
		five.add(comp[7]);

		fibtn = new JButton("CREATE CLASSROOM");
		fibtn.setBackground(new Color(44, 50, 142));
		fibtn.setBounds(500, 500, 330, 55);
		fibtn.setForeground(Color.white);
		fibtn.setFont(new Font("Calibri", Font.PLAIN, 25));
		fibtn.setBorder(BorderFactory.createSoftBevelBorder(10));
//		Tbtn.addFocusListener(focus);
		fibtn.addActionListener(action);
		five.add(fibtn);
		int Blocation[] = { 345, 420 };
		fitxt = new JTextField[2];
		for (int i = 0; i < fitxt.length; i++) {
			fitxt[i] = new JTextField();
			fitxt[i].setBounds(500, Blocation[i], 330, 45);
			fitxt[i].addKeyListener(key);
			fitxt[i].setFont(new Font("Calibri", Font.PLAIN, 20));
			fitxt[i].setForeground(Color.LIGHT_GRAY);
			fitxt[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(44, 50, 142)));
			if (i == 0) {

				fitxt[i].addFocusListener(focus);
				fitxt[i].setText(placeH[i]);
			} else if (i == 1) {
				fitxt[i].addFocusListener(focus);
				fitxt[i].setText(placeH[i]);

			} else if (i == 2) {
				fitxt[i].addFocusListener(focus);
				fitxt[i].setText(placeH[i]);

			}
			five.add(fitxt[i]);

		}

		int BLocation[] = { 310, 355, 400, 445, 490, 535 };
		GradeFive = new JButton[6];
		for (int i = 0; i < GradeFive.length; i++) {
			GradeFive[i] = new JButton();
			GradeFive[i].setBounds(23, BLocation[i], 350, 30);
			GradeFive[i].setText("Class room " + (i + 1));
			GradeFive[i].setBorder(BorderFactory.createSoftBevelBorder(10));
			GradeFive[i].setBackground(new Color(50, 112, 200));
			GradeFive[i].setForeground(Color.white);
			GradeFive[i].setFont(new Font("Calibri", Font.PLAIN, 15));
			GradeFive[i].addActionListener(action);
			GradeFive[i].setVisible(false);
			five.add(GradeFive[i]);

		}

	}

	private void six() {
		// TODO Auto-generated method stub
		six = new JPanel();
		six.setBounds(60, 40, 930, 600);
		six.setLayout(null);
		six.setBackground(Color.black);
		six.setOpaque(false);
		six.setVisible(false);

		Sitxt = new JTextField();
//		
		toGs = new JButton("upgrade");
		toGs.setBounds(23, 275, 350, 25);
		toGs.setBorder(BorderFactory.createSoftBevelBorder(10));
		toGs.setBackground(new Color(97, 146, 194));
		toGs.setForeground(Color.white);
		toGs.setFont(new Font("Calibri", Font.PLAIN, 15));
		six.add(toGs);

		six.add(Sitxt);

		String subj[] = { "grade six of one", "grade six of two", "grade six of three", "grade six of four",
				"grade six of five", "grade six of six" };
		Databases db = new Databases();
		try {
			db.classForname();
			db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
			db.st = db.con.createStatement();
			ResultSet rs = db.st.executeQuery(
					"select classname from classrooms where classname like 'grade six%' order by classname like'one%'");
			String rr[] = new String[6];

			while (rs.next()) {
				rr[rs.getRow() - 1] = rs.getString("classname");
			}
			int g = 0;
			for (int i = 0; i < rr.length; i++) {
				if (rr[i] == null || !subj[i].equalsIgnoreCase(rr[i])) {
					g += i - i;

					g++;
				}
			}
			String in[] = new String[g];
			int c = 0;
			for (int i = 0; i < rr.length; i++) {
				if (rr[i] == null || !subj[i].equalsIgnoreCase(rr[i])) {
					c += i - i;
					in[c] = subj[i];
					c++;
				}
			}

			comp[6] = new JComboBox(in);
			db.con.close();
		} catch (Exception e) {
			System.out.print(e);
		}
		comp[6].setBounds(500, 270, 330, 45);
		comp[6].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(44, 50, 142)));
		comp[6].setForeground(Color.black);
		comp[6].setFont(new Font("Calibri", Font.PLAIN, 20));
		comp[6].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (comp[6].getSelectedIndex() == 0) {
					subjects = comp[6].getSelectedItem().toString();
					comp[6].removeItem(subjects);
				} else {

					JOptionPane.showMessageDialog(null, "keep the order follow the Staps", "Schmasys warring",
							JOptionPane.WARNING_MESSAGE);
				}
			}

		});
		six.add(comp[6]);

		sibtn = new JButton("CREATE CLASSROOM");
		sibtn.setBackground(new Color(44, 50, 142));
		sibtn.setBounds(500, 500, 330, 55);
		sibtn.setForeground(Color.white);
		sibtn.setFont(new Font("Calibri", Font.PLAIN, 25));
		sibtn.setBorder(BorderFactory.createSoftBevelBorder(10));
		sibtn.addActionListener(action);
//		Tbtn.addFocusListener(focus);
		six.add(sibtn);
		int Blocation[] = { 345, 420 };
		sitxt = new JTextField[2];
		for (int i = 0; i < sitxt.length; i++) {
			sitxt[i] = new JTextField();
			sitxt[i].setBounds(500, Blocation[i], 330, 45);

			sitxt[i].setFont(new Font("Calibri", Font.PLAIN, 20));
			sitxt[i].setForeground(Color.LIGHT_GRAY);
			sitxt[i].addKeyListener(key);
			sitxt[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(44, 50, 142)));
			if (i == 0) {

				sitxt[i].addFocusListener(focus);
				sitxt[i].setText(placeH[i]);
			} else if (i == 1) {
				sitxt[i].addFocusListener(focus);
				sitxt[i].setText(placeH[i]);

			} else if (i == 2) {
				sitxt[i].addFocusListener(focus);
				sitxt[i].setText(placeH[i]);

			}
			six.add(sitxt[i]);

		}

		int BLocation[] = { 310, 355, 400, 445, 490, 535 };
		GradeSix = new JButton[6];
		for (int i = 0; i < GradeSix.length; i++) {
			GradeSix[i] = new JButton();
			GradeSix[i].setBounds(23, BLocation[i], 350, 30);
			GradeSix[i].setText("Class room " + (i + 1));
			GradeSix[i].setBorder(BorderFactory.createSoftBevelBorder(10));
			GradeSix[i].setBackground(new Color(50, 112, 200));
			GradeSix[i].setForeground(Color.white);
			GradeSix[i].setFont(new Font("Calibri", Font.PLAIN, 15));
			GradeSix[i].addActionListener(action);
			GradeSix[i].setVisible(false);
			six.add(GradeSix[i]);

		}
	}

	private void seven() {
		// TODO Auto-generated method stub
		seven = new JPanel();
		seven.setBounds(60, 40, 930, 600);
		seven.setLayout(null);
		seven.setBackground(Color.black);
		seven.setOpaque(false);
		seven.setVisible(false);

		Setxt = new JTextField();
//		

		toGe = new JButton("upgrade");
		toGe.setBounds(23, 275, 350, 25);
		toGe.setBorder(BorderFactory.createSoftBevelBorder(10));
		toGe.setBackground(new Color(97, 146, 194));
		toGe.setForeground(Color.white);
		toGe.setFont(new Font("Calibri", Font.PLAIN, 15));
		seven.add(toGe);
		seven.add(Setxt);

		String subj[] = { "grade seven of one", "grade seven of two", "grade seven of three", "grade seven of four",
				"grade seven of five", "grade seven of six" };
		Databases db = new Databases();
		try {
			db.classForname();
			db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
			db.st = db.con.createStatement();
			ResultSet rs = db.st.executeQuery(
					"select classname from classrooms where classname like 'grade seven%' order by classname like'one%'");
			String rr[] = new String[6];

			while (rs.next()) {
				rr[rs.getRow() - 1] = rs.getString("classname");
			}
			int g = 0;
			for (int i = 0; i < rr.length; i++) {
				if (rr[i] == null || !subj[i].equalsIgnoreCase(rr[i])) {
					g += i - i;

					g++;
				}
			}
			String in[] = new String[g];
			int c = 0;
			for (int i = 0; i < rr.length; i++) {
				if (rr[i] == null || !subj[i].equalsIgnoreCase(rr[i])) {
					c += i - i;
					in[c] = subj[i];
					c++;
				}
			}

			comp[5] = new JComboBox(in);
			db.con.close();
		} catch (Exception e) {
			System.out.print(e);
		}
		comp[5].setBounds(500, 270, 330, 45);
		comp[5].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(44, 50, 142)));
		comp[5].setForeground(Color.black);
		comp[5].setFont(new Font("Calibri", Font.PLAIN, 20));
		comp[5].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (comp[5].getSelectedIndex() == 0) {
					subjects = comp[5].getSelectedItem().toString();
					comp[5].removeItem(subjects);
				} else {

					JOptionPane.showMessageDialog(null, "keep the order follow the Staps", "Schmasys warring",
							JOptionPane.WARNING_MESSAGE);
				}
			}

		});
		seven.add(comp[5]);

		sebtn = new JButton("CREATE CLASSROOM");
		sebtn.setBackground(new Color(44, 50, 142));
		sebtn.setBounds(500, 500, 330, 55);
		sebtn.setForeground(Color.white);
		sebtn.setFont(new Font("Calibri", Font.PLAIN, 25));
		sebtn.addActionListener(action);
		sebtn.setBorder(BorderFactory.createSoftBevelBorder(10));
//		Tbtn.addFocusListener(focus);
		seven.add(sebtn);
		int Blocation[] = { 345, 420 };
		setxt = new JTextField[2];
		for (int i = 0; i < setxt.length; i++) {
			setxt[i] = new JTextField();
			setxt[i].setBounds(500, Blocation[i], 330, 45);

			setxt[i].setFont(new Font("Calibri", Font.PLAIN, 20));
			setxt[i].setForeground(Color.LIGHT_GRAY);
			setxt[i].addKeyListener(key);
			setxt[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(44, 50, 142)));
			if (i == 0) {

				setxt[i].addFocusListener(focus);
				setxt[i].setText(placeH[i]);
			} else if (i == 1) {
				setxt[i].addFocusListener(focus);
				setxt[i].setText(placeH[i]);

			} else if (i == 2) {
				setxt[i].addFocusListener(focus);
				setxt[i].setText(placeH[i]);

			}
			seven.add(setxt[i]);

		}

		int BLocation[] = { 310, 355, 400, 445, 490, 535 };
		GradeSeven = new JButton[6];
		for (int i = 0; i < GradeSeven.length; i++) {
			GradeSeven[i] = new JButton();
			GradeSeven[i].setBounds(23, BLocation[i], 350, 30);
			GradeSeven[i].setText("Class room " + (i + 1));
			GradeSeven[i].setBorder(BorderFactory.createSoftBevelBorder(10));
			GradeSeven[i].setBackground(new Color(50, 112, 200));
			GradeSeven[i].setForeground(Color.white);
			GradeSeven[i].setFont(new Font("Calibri", Font.PLAIN, 15));
			GradeSeven[i].addActionListener(action);
			GradeSeven[i].setVisible(false);
			seven.add(GradeSeven[i]);

		}

	}

	private void eight() {
		// TODO Auto-generated method stub
		eight = new JPanel();
		eight.setBounds(60, 40, 930, 600);
		eight.setLayout(null);
		eight.setBackground(Color.black);
		eight.setOpaque(false);
		eight.setVisible(false);

		Eitxt = new JTextField();
//		

		toFo = new JButton("upgrade");
		toFo.setBounds(23, 275, 350, 25);
		toFo.setBorder(BorderFactory.createSoftBevelBorder(10));
		toFo.setBackground(new Color(97, 146, 194));
		toFo.setForeground(Color.white);
		toFo.setFont(new Font("Calibri", Font.PLAIN, 15));
		eight.add(toFo);
		eight.add(Eitxt);

		String subj[] = { "grade eight of one", "grade eight of two", "grade eight of three", "grade eight of four",
				"grade eight of five", "grade eight of six" };
		Databases db = new Databases();
		try {
			db.classForname();
			db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
			db.st = db.con.createStatement();
			ResultSet rs = db.st.executeQuery(
					"select classname from classrooms where classname like 'grade eight%' order by classname like'one%'");
			String rr[] = new String[6];

			while (rs.next()) {
				rr[rs.getRow() - 1] = rs.getString("classname");
			}
			int g = 0;
			for (int i = 0; i < rr.length; i++) {
				if (rr[i] == null || !subj[i].equalsIgnoreCase(rr[i])) {
					g += i - i;

					g++;
				}
			}
			String in[] = new String[g];
			int c = 0;
			for (int i = 0; i < rr.length; i++) {
				if (rr[i] == null || !subj[i].equalsIgnoreCase(rr[i])) {
					c += i - i;
					in[c] = subj[i];
					c++;
				}
			}

			comp[4] = new JComboBox(in);
			db.con.close();
		} catch (Exception e) {
			System.out.print(e);
		}
		comp[4].setBounds(500, 270, 330, 45);
		comp[4].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(44, 50, 142)));
		comp[4].setForeground(Color.black);
		comp[4].setFont(new Font("Calibri", Font.PLAIN, 20));
		comp[4].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (comp[4].getSelectedIndex() == 0) {
					subjects = comp[4].getSelectedItem().toString();
					comp[4].removeItem(subjects);
				} else {

					JOptionPane.showMessageDialog(null, "keep the order follow the Staps", "Schmasys warring",
							JOptionPane.WARNING_MESSAGE);
				}
			}

		});
		eight.add(comp[4]);

		eibtn = new JButton("CREATE CLASSROOM");
		eibtn.setBackground(new Color(44, 50, 142));
		eibtn.setBounds(500, 500, 330, 55);
		eibtn.setForeground(Color.white);
		eibtn.setFont(new Font("Calibri", Font.PLAIN, 25));
		eibtn.setBorder(BorderFactory.createSoftBevelBorder(10));
//		Tbtn.addFocusListener(focus);
		eibtn.addActionListener(action);
		eight.add(eibtn);
		int Blocation[] = { 345, 420 };
		eitxt = new JTextField[2];
		for (int i = 0; i < eitxt.length; i++) {
			eitxt[i] = new JTextField();
			eitxt[i].setBounds(500, Blocation[i], 330, 45);

			eitxt[i].setFont(new Font("Calibri", Font.PLAIN, 20));
			eitxt[i].setForeground(Color.LIGHT_GRAY);
			eitxt[i].addKeyListener(key);
			eitxt[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(44, 50, 142)));
			if (i == 0) {

				eitxt[i].addFocusListener(focus);
				eitxt[i].setText(placeH[i]);
			} else if (i == 1) {
				eitxt[i].addFocusListener(focus);
				eitxt[i].setText(placeH[i]);

			} else if (i == 2) {
				eitxt[i].addFocusListener(focus);
				eitxt[i].setText(placeH[i]);

			}
			eight.add(eitxt[i]);

		}
		int BLocation[] = { 310, 355, 400, 445, 490, 535 };
		GradeEight = new JButton[6];
		for (int i = 0; i < GradeEight.length; i++) {
			GradeEight[i] = new JButton();
			GradeEight[i].setBounds(23, BLocation[i], 350, 30);
			GradeEight[i].setText("Class room " + (i + 1));
			GradeEight[i].setBorder(BorderFactory.createSoftBevelBorder(10));
			GradeEight[i].setBackground(new Color(50, 112, 200));
			GradeEight[i].setForeground(Color.white);
			GradeEight[i].setFont(new Font("Calibri", Font.PLAIN, 15));
			GradeEight[i].addActionListener(action);
			GradeEight[i].setVisible(false);
			eight.add(GradeEight[i]);

		}

	}

	private void Formone() {
		// TODO Auto-generated method stub
		one = new JPanel();
		one.setBounds(60, 40, 930, 600);
		one.setLayout(null);
		one.setBackground(Color.black);
		one.setOpaque(false);
		one.setVisible(false);

		otxt = new JTextField();
//		

		toFt = new JButton("upgrade");
		toFt.setBounds(23, 275, 350, 25);
		toFt.setBorder(BorderFactory.createSoftBevelBorder(10));
		toFt.setBackground(new Color(97, 146, 194));
		toFt.setForeground(Color.white);
		toFt.setFont(new Font("Calibri", Font.PLAIN, 15));
		one.add(toFt);

		one.add(otxt);

		String subj[] = { "form one of one", "form one of two", "form one of three", "form one of four",
				"form one of five", "form one of six" };
		Databases db = new Databases();
		try {
			db.classForname();
			db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
			db.st = db.con.createStatement();
			ResultSet rs = db.st.executeQuery(
					"select classname from classrooms where classname like 'form one%' order by classname like'one%'");
			String rr[] = new String[6];

			while (rs.next()) {
				rr[rs.getRow() - 1] = rs.getString("classname");
			}
			int g = 0;
			for (int i = 0; i < rr.length; i++) {
				if (rr[i] == null || !subj[i].equalsIgnoreCase(rr[i])) {
					g += i - i;

					g++;
				}
			}
			String in[] = new String[g];
			int c = 0;
			for (int i = 0; i < rr.length; i++) {
				if (rr[i] == null || !subj[i].equalsIgnoreCase(rr[i])) {
					c += i - i;
					in[c] = subj[i];
					c++;
				}
			}

			comp[3] = new JComboBox(in);
			db.con.close();
		} catch (Exception e) {
			System.out.print(e);
		}
		comp[3].setBounds(500, 270, 330, 45);
		comp[3].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(44, 50, 142)));
		comp[3].setForeground(Color.black);
		comp[3].setFont(new Font("Calibri", Font.PLAIN, 20));
		comp[3].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (comp[3].getSelectedIndex() == 0) {
					subjects = comp[3].getSelectedItem().toString();
					comp[3].removeItem(subjects);
				} else {

					JOptionPane.showMessageDialog(null, "keep the order follow the Staps", "Schmasys warring",
							JOptionPane.WARNING_MESSAGE);
				}
			}

		});
		one.add(comp[3]);

		Obtn = new JButton("CREATE CLASSROOM");
		Obtn.setBackground(new Color(44, 50, 142));
		Obtn.setBounds(500, 500, 330, 55);
		Obtn.setForeground(Color.white);
		Obtn.setFont(new Font("Calibri", Font.PLAIN, 25));
		Obtn.setBorder(BorderFactory.createSoftBevelBorder(10));
		Obtn.addActionListener(action);
//		Tbtn.addFocusListener(focus);
		one.add(Obtn);
		int Blocation[] = { 345, 420 };
		Otxt = new JTextField[2];
		for (int i = 0; i < Otxt.length; i++) {
			Otxt[i] = new JTextField();
			Otxt[i].setBounds(500, Blocation[i], 330, 45);

			Otxt[i].setFont(new Font("Calibri", Font.PLAIN, 20));
			Otxt[i].setForeground(Color.LIGHT_GRAY);
			Otxt[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(44, 50, 142)));
			Otxt[i].addKeyListener(key);
			if (i == 0) {

				Otxt[i].addFocusListener(focus);
				Otxt[i].setText(placeH[i]);
			} else if (i == 1) {
				Otxt[i].addFocusListener(focus);
				Otxt[i].setText(placeH[i]);

			} else if (i == 2) {
				Otxt[i].addFocusListener(focus);
				Otxt[i].setText(placeH[i]);

			}
			one.add(Otxt[i]);

		}

		int BLocation[] = { 310, 355, 400, 445, 490, 535 };
		FormOne = new JButton[6];
		for (int i = 0; i < FormOne.length; i++) {
			FormOne[i] = new JButton();
			FormOne[i].setBounds(23, BLocation[i], 350, 30);
			FormOne[i].setText("Class room " + (i + 1));
			FormOne[i].setBorder(BorderFactory.createSoftBevelBorder(10));
			FormOne[i].setBackground(new Color(50, 112, 200));
			FormOne[i].setForeground(Color.white);
			FormOne[i].setFont(new Font("Calibri", Font.PLAIN, 15));
			FormOne[i].addActionListener(action);
			FormOne[i].setVisible(false);

			one.add(FormOne[i]);

		}

	}

	private void Formtwo() {
		two = new JPanel();
		two.setBounds(60, 40, 930, 600);
		two.setLayout(null);
		two.setBackground(Color.black);
		two.setOpaque(false);
		two.setVisible(false);
		TWtxt = new JTextField();
//		

		toFth = new JButton("upgrade");
		toFth.setBounds(23, 275, 350, 25);
		toFth.setBorder(BorderFactory.createSoftBevelBorder(10));
		toFth.setBackground(new Color(97, 146, 194));
		toFth.setForeground(Color.white);
		toFth.setFont(new Font("Calibri", Font.PLAIN, 15));
		two.add(toFth);
		two.add(TWtxt);

		String subj[] = { "form two of one", "form two of two", "form two of three", "form two of four",
				"form two of five", "form two of six" };
		Databases db = new Databases();
		try {
			db.classForname();
			db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
			db.st = db.con.createStatement();
			ResultSet rs = db.st.executeQuery(
					"select classname from classrooms where classname like 'form two%' order by classname like'one%'");
			String rr[] = new String[6];

			while (rs.next()) {
				rr[rs.getRow() - 1] = rs.getString("classname");
			}
			int g = 0;
			for (int i = 0; i < rr.length; i++) {
				if (rr[i] == null || !subj[i].equalsIgnoreCase(rr[i])) {
					g += i - i;

					g++;
				}
			}
			String in[] = new String[g];
			int c = 0;
			for (int i = 0; i < rr.length; i++) {
				if (rr[i] == null || !subj[i].equalsIgnoreCase(rr[i])) {
					c += i - i;
					in[c] = subj[i];
					c++;
				}
			}

			comp[2] = new JComboBox(in);
			db.con.close();
		} catch (Exception e) {
			System.out.print(e);
		}
		comp[2].setBounds(500, 270, 330, 45);
		comp[2].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(44, 50, 142)));
		comp[2].setForeground(Color.black);
		comp[2].setFont(new Font("Calibri", Font.PLAIN, 20));
		comp[2].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (comp[2].getSelectedIndex() == 0) {
					subjects = comp[2].getSelectedItem().toString();
					comp[2].removeItem(subjects);
				} else {

					JOptionPane.showMessageDialog(null, "keep the order follow the Staps", "Schmasys warring",
							JOptionPane.WARNING_MESSAGE);
				}
			}

		});
		two.add(comp[2]);
		TWbtn = new JButton("CREATE CLASSROOM");
		TWbtn.setBackground(new Color(44, 50, 142));
		TWbtn.setBounds(500, 500, 330, 55);
		TWbtn.setForeground(Color.white);
		TWbtn.setFont(new Font("Calibri", Font.PLAIN, 25));
		TWbtn.setBorder(BorderFactory.createSoftBevelBorder(10));
//		Tbtn.addFocusListener(focus);
		TWbtn.addActionListener(action);
		two.add(TWbtn);
		int Blocation[] = { 345, 420 };
		Twtxt = new JTextField[2];
		for (int i = 0; i < Twtxt.length; i++) {
			Twtxt[i] = new JTextField();
			Twtxt[i].setBounds(500, Blocation[i], 330, 45);

			Twtxt[i].setFont(new Font("Calibri", Font.PLAIN, 20));
			Twtxt[i].setForeground(Color.LIGHT_GRAY);
			Twtxt[i].addKeyListener(key);
			Twtxt[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(44, 50, 142)));
			if (i == 0) {

				Twtxt[i].addFocusListener(focus);
				Twtxt[i].setText(placeH[i]);
			} else if (i == 1) {
				Twtxt[i].addFocusListener(focus);
				Twtxt[i].setText(placeH[i]);

			}
			two.add(Twtxt[i]);

		}

		int BLocation[] = { 310, 355, 400, 445, 490, 535 };
		FormTwo = new JButton[6];
		for (int i = 0; i < FormTwo.length; i++) {
			FormTwo[i] = new JButton();
			FormTwo[i].setBounds(23, BLocation[i], 350, 30);
			FormTwo[i].setText("Class room " + (i + 1));
			FormTwo[i].setBorder(BorderFactory.createSoftBevelBorder(10));
			FormTwo[i].setBackground(new Color(50, 112, 200));
			FormTwo[i].setForeground(Color.white);
			FormTwo[i].setFont(new Font("Calibri", Font.PLAIN, 15));
			FormTwo[i].addActionListener(action);
			FormTwo[i].setVisible(false);
			two.add(FormTwo[i]);

		}

	}

	private void Formthree() {

		three = new JPanel();
		three.setBounds(60, 40, 930, 600);
		three.setLayout(null);
		three.setBackground(Color.black);
		three.setOpaque(false);
		three.setVisible(false);

		Thtxt = new JTextField();

		toFf = new JButton("upgrade");
		toFf.setBounds(23, 275, 350, 25);
		toFf.setBorder(BorderFactory.createSoftBevelBorder(10));
		toFf.setBackground(new Color(97, 146, 194));
		toFf.setForeground(Color.white);
		toFf.setFont(new Font("Calibri", Font.PLAIN, 15));
		three.add(toFf);
		three.add(Thtxt);

		String subj[] = { "form three of one", "form three of two", "form three of three", "form three of four",
				"form three of five", "form three of six" };
		Databases db = new Databases();
		try {
			db.classForname();
			db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
			db.st = db.con.createStatement();
			ResultSet rs = db.st.executeQuery(
					"select classname from classrooms where classname like 'form three%' order by classname like'one%'");
			String rr[] = new String[6];

			while (rs.next()) {
				rr[rs.getRow() - 1] = rs.getString("classname");
			}
			int g = 0;
			for (int i = 0; i < rr.length; i++) {
				if (rr[i] == null || !subj[i].equalsIgnoreCase(rr[i])) {
					g += i - i;

					g++;
				}
			}
			String in[] = new String[g];
			int c = 0;
			for (int i = 0; i < rr.length; i++) {
				if (rr[i] == null || !subj[i].equalsIgnoreCase(rr[i])) {
					c += i - i;
					in[c] = subj[i];
					c++;
				}
			}

			comp[1] = new JComboBox(in);
			db.con.close();
		} catch (Exception e) {
			System.out.print(e);
		}
		comp[1].setBounds(500, 270, 330, 45);
		comp[1].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(44, 50, 142)));
		comp[1].setForeground(Color.black);
		comp[1].setFont(new Font("Calibri", Font.PLAIN, 20));
		comp[1].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (comp[1].getSelectedIndex() == 0) {
					subjects = comp[1].getSelectedItem().toString();
					comp[1].removeItem(subjects);
				} else {

					JOptionPane.showMessageDialog(null, "keep the order follow the Staps", "Schmasys warring",
							JOptionPane.WARNING_MESSAGE);
				}
			}

		});
		three.add(comp[1]);

		Tbtn = new JButton("CREATE CLASSROOM");
		Tbtn.setBackground(new Color(44, 50, 142));
		Tbtn.setBounds(500, 500, 330, 55);
		Tbtn.setForeground(Color.white);
		Tbtn.setFont(new Font("Calibri", Font.PLAIN, 25));
		Tbtn.setBorder(BorderFactory.createSoftBevelBorder(10));
//		Tbtn.addFocusListener(focus);
		Tbtn.addActionListener(action);
		three.add(Tbtn);
		int Blocation[] = { 345, 420 };
		Ttxt = new JTextField[2];
		for (int i = 0; i < Ttxt.length; i++) {
			Ttxt[i] = new JTextField();
			Ttxt[i].setBounds(500, Blocation[i], 330, 45);

			Ttxt[i].setFont(new Font("Calibri", Font.PLAIN, 20));
			Ttxt[i].setForeground(Color.LIGHT_GRAY);
			Ttxt[i].addKeyListener(key);
			Ttxt[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(44, 50, 142)));
			if (i == 0) {

				Ttxt[i].addFocusListener(focus);
				Ttxt[i].setText(placeH[i]);
			} else if (i == 1) {
				Ttxt[i].addFocusListener(focus);
				Ttxt[i].setText(placeH[i]);

			}
			three.add(Ttxt[i]);

		}

		int BLocation[] = { 310, 355, 400, 445, 490, 535 };
		FormThree = new JButton[6];
		for (int i = 0; i < FormThree.length; i++) {
			FormThree[i] = new JButton();
			FormThree[i].setBounds(23, BLocation[i], 350, 30);
			FormThree[i].setText("Class room " + (i + 1));
			FormThree[i].setBorder(BorderFactory.createSoftBevelBorder(10));
			FormThree[i].setBackground(new Color(50, 112, 200));
			FormThree[i].setForeground(Color.white);
			FormThree[i].setFont(new Font("Calibri", Font.PLAIN, 15));
			FormThree[i].addActionListener(action);
			FormThree[i].setVisible(false);
			three.add(FormThree[i]);

		}

	}

	private void Formfour() {

		graduated = new JButton("GRADUATE");
		graduated.setBackground(Color.white);
		graduated.setBorder(BorderFactory.createSoftBevelBorder(10));
		graduated.setFont(new Font("Roman", Font.PLAIN, 15));
		graduated.setBounds(23, 270, 350, 30);
		graduated.setForeground(Color.black);
		// TODO Auto-generated method stub
		four = new JPanel();
		four.setBounds(60, 40, 930, 600);
		four.setLayout(null);
		four.setBackground(Color.black);
		four.setOpaque(false);
		four.setVisible(false);
		txt = new JTextField();
//		
		four.add(graduated);
		four.add(txt);

		Fbtn = new JButton("CREATE CLASSROOM");
		Fbtn.setBackground(new Color(44, 50, 142));
		Fbtn.setBounds(500, 500, 330, 55);
		Fbtn.setForeground(Color.white);
		Fbtn.setFont(new Font("Calibri", Font.PLAIN, 25));
		Fbtn.setBorder(BorderFactory.createSoftBevelBorder(10));
		Fbtn.addFocusListener(focus);
		Fbtn.addActionListener(action);
		four.add(Fbtn);
		String subj[] = { "form four of one", "form four of two", "form four of three", "form four of four",
				"form four of five", "form four of six" };

		Databases db = new Databases();
		try {
			db.classForname();
			db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
			db.st = db.con.createStatement();
			ResultSet rs = db.st.executeQuery(
					"select classname from classrooms where classname like 'form four%' order by classname like'one%'");
			String rr[] = new String[6];

			while (rs.next()) {
				rr[rs.getRow() - 1] = rs.getString("classname");
			}
			int g = 0;
			for (int i = 0; i < rr.length; i++) {
				if (rr[i] == null || !subj[i].equalsIgnoreCase(rr[i])) {
					g += i - i;

					g++;
				}
			}
			String in[] = new String[g];
			int c = 0;
			for (int i = 0; i < rr.length; i++) {
				if (rr[i] == null || !subj[i].equalsIgnoreCase(rr[i])) {
					c += i - i;
					in[c] = subj[i];
					c++;
				}
			}

			comp[0] = new JComboBox(in);
			db.con.close();
		} catch (Exception e) {
			System.out.print(e);
		}
		comp[0].setBounds(500, 270, 330, 45);
		comp[0].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(44, 50, 142)));
		comp[0].setForeground(Color.LIGHT_GRAY);
		comp[0].setFont(new Font("Calibri", Font.PLAIN, 20));
		comp[0].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (comp[0].getSelectedIndex() == 0) {
					subjects = comp[0].getSelectedItem().toString();
					comp[0].removeItem(subjects);
				} else {

					JOptionPane.showMessageDialog(null, "keep the order follow the Staps", "Schmasys warring",
							JOptionPane.WARNING_MESSAGE);
				}
			}

		});
		four.add(comp[0]);
		int Blocation[] = { 345, 420 };
		Ftxt = new JTextField[2];
		for (int i = 0; i < Ftxt.length; i++) {
			Ftxt[i] = new JTextField();
			Ftxt[i].setBounds(500, Blocation[i], 330, 45);

			Ftxt[i].setFont(new Font("Calibri", Font.PLAIN, 20));
			Ftxt[i].setForeground(Color.LIGHT_GRAY);
			Ftxt[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(44, 50, 142)));
			Ftxt[i].addKeyListener(key);
			if (i == 0) {

				Ftxt[i].addFocusListener(focus);
				Ftxt[i].setText(placeH[i]);
			} else if (i == 1) {
				Ftxt[i].addFocusListener(focus);
				Ftxt[i].setText(placeH[i]);

			} else if (i == 2) {
				Ftxt[i].addFocusListener(focus);
				Ftxt[i].setText(placeH[i]);

			}
			four.add(Ftxt[i]);

		}
		int BLocation[] = { 310, 355, 400, 445, 490, 535 };
		FormFour = new JButton[6];
		for (int i = 0; i < FormFour.length; i++) {
			FormFour[i] = new JButton();
			FormFour[i].setBounds(23, BLocation[i], 350, 30);
			FormFour[i].setText("Class room " + (i + 1));
			FormFour[i].setBorder(BorderFactory.createSoftBevelBorder(10));
			FormFour[i].setBackground(new Color(50, 112, 200));
			FormFour[i].setForeground(Color.white);
			FormFour[i].setFont(new Font("Calibri", Font.PLAIN, 15));
			FormFour[i].addActionListener(action);
			FormFour[i].setVisible(false);
			four.add(FormFour[i]);

		}

	}

	private void container() {

		CloseH = new JPanel();
		CloseH.setBounds(60, 40, 930, 600);
		CloseH.setLayout(null);
		CloseH.setBackground(Color.black);
		CloseH.setOpaque(false);
		CloseH.setVisible(true);
		CloseH.add(close);
		CloseH.add(classroom);
		CloseH.add(uderLine);
		CloseH.add(welcome);
		CloseH.add(somtech);
		CloseH.add(underline);
		CloseH.add(lb2);
		CloseH.add(lb1);
		CloseH.add(lb3);
		CloseH.add(lb4);
		CloseH.add(lb5);
		CloseH.add(lb6);

		pane = new JPanel();
		pane.setBounds(60, 40, 930, 600);
		pane.setBackground(Color.black);
		pane.setLayout(null);

		pane.add(lb);

	}

	private void fuctions() {
		add(One);
		add(Two);
		add(Three);
		add(Four);
		add(five);
		add(six);
		add(seven);
		add(eight);
		add(one);
		add(two);
		add(three);
		add(four);
		add(CloseH);
		add(pane);
		add(backgound);
		setVisible(false);
		setLayout(null);
		setBounds(0, 0, 1045, 670);

	}

	private class focusListener implements FocusListener {

		public void focusGained(FocusEvent a) {
			// four four place holder
			for (int i = 0; i < 2; i++) {
				if (a.getSource() == Ftxt[i]) {
					if (Ftxt[i].getText().equals(placeH[i]) || Ftxt[i].getText().length() == 0) {
						Ftxt[i].setText("");
						Ftxt[i].setForeground(Color.black);

					}
				}

			}

			// four three place holder
			for (int i = 0; i < 2; i++) {
				if (a.getSource() == Ttxt[i]) {
					if (Ttxt[i].getText().equals(placeH[i]) || Ttxt[i].getText().length() == 0) {
						Ttxt[i].setText("");
						Ttxt[i].setForeground(Color.black);

					}
				}
			}
//	
			// for two placeHolder
			for (int i = 0; i < 2; i++) {
				if (a.getSource() == Twtxt[i]) {
					if (Twtxt[i].getText().equals(placeH[i]) || Twtxt[i].getText().length() == 0) {
						Twtxt[i].setText("");
						Twtxt[i].setForeground(Color.black);

					}
				}
			}
//		
			// form one placeHolder
			for (int i = 0; i < 2; i++) {
				if (a.getSource() == Otxt[i]) {
					if (Otxt[i].getText().equals(placeH[i]) || Otxt[i].getText().length() == 0) {
						Otxt[i].setText("");
						Otxt[i].setForeground(Color.black);

					}
				}
			}
			// form eight placeHolder
			for (int i = 0; i < 2; i++) {
				if (a.getSource() == eitxt[i]) {
					if (eitxt[i].getText().equals(placeH[i]) || eitxt[i].getText().length() == 0) {
						eitxt[i].setText("");
						eitxt[i].setForeground(Color.black);

					}
				}
			}

			// form seven placeHolder sitxt
			for (int i = 0; i < 2; i++) {
				if (a.getSource() == setxt[i]) {
					if (setxt[i].getText().equals(placeH[i]) || setxt[i].getText().length() == 0) {
						setxt[i].setText("");
						setxt[i].setForeground(Color.black);

					}
				}
			}

			// form seven placeHolder fitxt
			for (int i = 0; i < 2; i++) {
				if (a.getSource() == sitxt[i]) {
					if (sitxt[i].getText().equals(placeH[i]) || sitxt[i].getText().length() == 0) {
						sitxt[i].setText("");
						sitxt[i].setForeground(Color.black);

					}
				}
			}
			// form seven placeHolder fotxt
			for (int i = 0; i < 2; i++) {
				if (a.getSource() == fitxt[i]) {
					if (fitxt[i].getText().equals(placeH[i]) || fitxt[i].getText().length() == 0) {
						fitxt[i].setText("");
						fitxt[i].setForeground(Color.black);

					}
				}
			}

			// form seven placeHolder fotxt
			for (int i = 0; i < 2; i++) {
				if (a.getSource() == fotxt[i]) {
					if (fotxt[i].getText().equals(placeH[i]) || fotxt[i].getText().length() == 0) {
						fotxt[i].setText("");
						fotxt[i].setForeground(Color.black);

					}
				}
			}

			// form seven placeHolder fotxt TRtxt
			for (int i = 0; i < 2; i++) {
				if (a.getSource() == TRtxt[i]) {
					if (TRtxt[i].getText().equals(placeH[i]) || TRtxt[i].getText().length() == 0) {
						TRtxt[i].setText("");
						TRtxt[i].setForeground(Color.black);

					}
				}
			}

			// form seven placeHolder fotxt TRtxt Ontxt
			for (int i = 0; i < 2; i++) {
				if (a.getSource() == Twotxt[i]) {
					if (Twotxt[i].getText().equals(placeH[i]) || Twotxt[i].getText().length() == 0) {
						Twotxt[i].setText("");
						Twotxt[i].setForeground(Color.black);

					}
				}
			}

			// form seven placeHolder fotxt TRtxt Ontxt
			for (int i = 0; i < 2; i++) {
				if (a.getSource() == Ontxt[i]) {
					if (Ontxt[i].getText().equals(placeH[i]) || Ontxt[i].getText().length() == 0) {
						Ontxt[i].setText("");
						Ontxt[i].setForeground(Color.black);

					}
				}
			}

		}

		public void focusLost(FocusEvent a) {
			for (int i = 0; i < 2; i++) {
				if (a.getSource() == Ftxt[i]) {
					if (Ftxt[i].getText().equals(placeH[i]) || Ftxt[i].getText().length() == 0) {
						Ftxt[i].setText(placeH[i]);
						Ftxt[i].setForeground(Color.LIGHT_GRAY);

					}
				}
			}

			// form three placeHolder

			for (int i = 0; i < 2; i++) {
				if (a.getSource() == Ttxt[i]) {
					if (Ttxt[i].getText().equals(placeH[i]) || Ttxt[i].getText().length() == 0) {
						Ttxt[i].setText(placeH[i]);
						Ttxt[i].setForeground(Color.LIGHT_GRAY);

					}
				}
			}

			// form two placeHolder
			for (int i = 0; i < 2; i++) {
				if (a.getSource() == Twtxt[i]) {
					if (Twtxt[i].getText().equals(placeH[i]) || Twtxt[i].getText().length() == 0) {
						Twtxt[i].setText(placeH[i]);
						Twtxt[i].setForeground(Color.LIGHT_GRAY);

					}
				}
			}

			// form one placeHolder
			for (int i = 0; i < 2; i++) {
				if (a.getSource() == Otxt[i]) {
					if (Otxt[i].getText().equals(placeH[i]) || Otxt[i].getText().length() == 0) {
						Otxt[i].setText(placeH[i]);
						Otxt[i].setForeground(Color.LIGHT_GRAY);

					}
				}
			}
			// form one placeHolder setxt
			for (int i = 0; i < 2; i++) {
				if (a.getSource() == eitxt[i]) {
					if (eitxt[i].getText().equals(placeH[i]) || eitxt[i].getText().length() == 0) {
						eitxt[i].setText(placeH[i]);
						eitxt[i].setForeground(Color.LIGHT_GRAY);

					}
				}
			}

			// form one placeHolder fitxt
			for (int i = 0; i < 2; i++) {
				if (a.getSource() == setxt[i]) {
					if (setxt[i].getText().equals(placeH[i]) || setxt[i].getText().length() == 0) {
						setxt[i].setText(placeH[i]);
						setxt[i].setForeground(Color.LIGHT_GRAY);

					}
				}
			}

			// form one placeHolder fotxt
			for (int i = 0; i < 2; i++) {
				if (a.getSource() == sitxt[i]) {
					if (sitxt[i].getText().equals(placeH[i]) || sitxt[i].getText().length() == 0) {
						sitxt[i].setText(placeH[i]);
						sitxt[i].setForeground(Color.LIGHT_GRAY);

					}
				}
			}
			// form one placeHolder fotxt
			for (int i = 0; i < 2; i++) {
				if (a.getSource() == fitxt[i]) {
					if (fitxt[i].getText().equals(placeH[i]) || fitxt[i].getText().length() == 0) {
						fitxt[i].setText(placeH[i]);
						fitxt[i].setForeground(Color.LIGHT_GRAY);

					}
				}
			}
			// form one placeHolder fotxt
			for (int i = 0; i < 2; i++) {
				if (a.getSource() == fotxt[i]) {
					if (fotxt[i].getText().equals(placeH[i]) || fotxt[i].getText().length() == 0) {
						fotxt[i].setText(placeH[i]);
						fotxt[i].setForeground(Color.LIGHT_GRAY);

					}
				}
			}
			// form one placeHolder fotxt Twotxt
			for (int i = 0; i < 2; i++) {
				if (a.getSource() == TRtxt[i]) {
					if (TRtxt[i].getText().equals(placeH[i]) || TRtxt[i].getText().length() == 0) {
						TRtxt[i].setText(placeH[i]);
						TRtxt[i].setForeground(Color.LIGHT_GRAY);

					}
				}
			}

			// form one placeHolder fotxt Twotxt Ontxt
			for (int i = 0; i < 2; i++) {
				if (a.getSource() == Twotxt[i]) {
					if (Twotxt[i].getText().equals(placeH[i]) || Twotxt[i].getText().length() == 0) {
						Twotxt[i].setText(placeH[i]);
						Twotxt[i].setForeground(Color.LIGHT_GRAY);

					}
				}
			}
			// form one placeHolder fotxt Twotxt Ontxt
			for (int i = 0; i < 2; i++) {
				if (a.getSource() == Ontxt[i]) {
					if (Ontxt[i].getText().equals(placeH[i]) || Ontxt[i].getText().length() == 0) {
						Ontxt[i].setText(placeH[i]);
						Ontxt[i].setForeground(Color.LIGHT_GRAY);

					}
				}
			}

		}

	}

	private class actionListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			Databases data = new Databases();
			try {
				data.classForname();
				data.con = DriverManager.getConnection("jdbc:sqlite:class.db");
				data.st = data.con.createStatement();
			} catch (Exception e) {
				System.out.print(e);
			}
			if (a.getSource() == Fbtn) {
				for (int i = 0; i < 2; i++) {
					if (!(subjects == null || Ftxt[i].getText().equals(placeH[i]) || Ftxt[i].getText().length() == 0)) {
						try {
							String str = "insert into  classrooms values('" + subjects + "','" + Ftxt[0].getText()
									+ "','" + Ftxt[1].getText() + "');";
							PreparedStatement pr = data.con.prepareStatement(str);
							pr.executeUpdate();

							String query = "select classname from classrooms where classname like '%form f%'";
							PreparedStatement ps = data.con.prepareStatement(query);
							ResultSet rs = ps.executeQuery();
							while (rs.next()) {
								int k = rs.getRow();
								String str1 = rs.getString(1);
								if (k == 1) {
									FormFour[k - 1].setVisible(true);
									FormFour[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 2) {
									FormFour[k - 1].setVisible(true);
									FormFour[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 3) {
									FormFour[k - 1].setVisible(true);
									FormFour[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 4) {
									FormFour[k - 1].setVisible(true);
									FormFour[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 5) {
									FormFour[k - 1].setVisible(true);
									FormFour[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 6) {
									FormFour[k - 1].setVisible(true);
									FormFour[k - 1].setText(
											str1 + "                                                                ");
									Fbtn.setEnabled(false);
								}
							}
							Ftxt[0].setText(placeH[0]);
							Ftxt[1].setText(placeH[1]);

							Ftxt[0].setForeground(Color.LIGHT_GRAY);
							Ftxt[1].setForeground(Color.LIGHT_GRAY);

							pr.close();

							break;
						} catch (Exception e) {
							System.out.println(e);
						}
					} else {
						JOptionPane.showMessageDialog(null, "please fill the class room \n     details first",
								"Schmasys worrying", JOptionPane.WARNING_MESSAGE);
						break;
					}
				}
			} else if (a.getSource() == Tbtn) {
				for (int i = 0; i < 2; i++) {
					if (!(subjects == null || Ttxt[i].getText().equals(placeH[i]) || Ttxt[i].getText().length() == 0)) {
						try {
							String str = "insert into  classrooms values('" + subjects + "','" + Ttxt[0].getText()
									+ "','" + Ttxt[1].getText() + "');";
							PreparedStatement pr = data.con.prepareStatement(str);
							pr.executeUpdate();

							String query = "select classname from classrooms where classname like '%form th%'";
							PreparedStatement ps = data.con.prepareStatement(query);
							ResultSet rs = ps.executeQuery();
							while (rs.next()) {
								int k = rs.getRow();
								String str1 = rs.getString(1);
								if (k == 1) {
									FormThree[k - 1].setVisible(true);
									FormThree[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 2) {
									FormThree[k - 1].setVisible(true);
									FormThree[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 3) {
									FormThree[k - 1].setVisible(true);
									FormThree[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 4) {
									FormThree[k - 1].setVisible(true);
									FormThree[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 5) {
									FormThree[k - 1].setVisible(true);
									FormThree[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 6) {
									FormThree[k - 1].setVisible(true);
									FormThree[k - 1].setText(
											str1 + "                                                                ");
									Tbtn.setEnabled(false);
								}
							}
							Ttxt[0].setText(placeH[0]);
							Ttxt[1].setText(placeH[1]);

							Ttxt[0].setForeground(Color.LIGHT_GRAY);
							Ttxt[1].setForeground(Color.LIGHT_GRAY);

							pr.close();

							break;
						} catch (Exception e) {
							System.out.println(e);
						}
					} else {
						JOptionPane.showMessageDialog(null, "please fill the class room \n            details first",
								"Schmasys worrying", JOptionPane.WARNING_MESSAGE);
						break;
					}
				}
			} else if (a.getSource() == TWbtn) {
				for (int i = 0; i < 2; i++) {
					if (!(subjects == null || Twtxt[i].getText().equals(placeH[i])
							|| Twtxt[i].getText().length() == 0)) {
						try {
							String str = "insert into  classrooms values('" + subjects + "','" + Twtxt[0].getText()
									+ "','" + Twtxt[1].getText() + "');";
							PreparedStatement pr = data.con.prepareStatement(str);
							int j = pr.executeUpdate();
							System.out.println(j + " row/s effected");
							String query = "select classname from classrooms where classname like '%form tw%'";
							PreparedStatement ps = data.con.prepareStatement(query);
							ResultSet rs = ps.executeQuery();
							while (rs.next()) {
								int k = rs.getRow();
								String str1 = rs.getString(1);
								if (k == 1) {
									FormTwo[k - 1].setVisible(true);
									FormTwo[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 2) {
									FormTwo[k - 1].setVisible(true);
									FormTwo[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 3) {
									FormTwo[k - 1].setVisible(true);
									FormTwo[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 4) {
									FormTwo[k - 1].setVisible(true);
									FormTwo[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 5) {
									FormTwo[k - 1].setVisible(true);
									FormTwo[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 6) {
									FormTwo[k - 1].setVisible(true);
									FormTwo[k - 1].setText(
											str1 + "                                                                ");
									TWbtn.setEnabled(false);
								}
							}
							Twtxt[0].setText(placeH[0]);
							Twtxt[1].setText(placeH[1]);

							Twtxt[0].setForeground(Color.LIGHT_GRAY);
							Twtxt[1].setForeground(Color.LIGHT_GRAY);

							pr.close();

							break;
						} catch (Exception e) {
							System.out.println(e);
						}
					} else {
						JOptionPane.showMessageDialog(null, "please fill the class room \n     details first",
								"Schmasys worrying", JOptionPane.WARNING_MESSAGE);
						break;
					}
				}
			} else if (a.getSource() == Obtn) {
				for (int i = 0; i < 2; i++) {
					if (!(subjects == null || Otxt[i].getText().equals(placeH[i]) || Otxt[i].getText().length() == 0)) {
						try {
							String str = "insert into  classrooms values('" + subjects + "','" + Otxt[0].getText()
									+ "','" + Otxt[1].getText() + "');";
							PreparedStatement pr = data.con.prepareStatement(str);
							int j = pr.executeUpdate();
							System.out.println(j + " row/s effected");
							String query = "select classname from classrooms where classname like '%form o%'";
							PreparedStatement ps = data.con.prepareStatement(query);
							ResultSet rs = ps.executeQuery();
							while (rs.next()) {
								int k = rs.getRow();
								String str1 = rs.getString(1);
								if (k == 1) {
									FormOne[k - 1].setVisible(true);
									FormOne[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 2) {
									FormOne[k - 1].setVisible(true);
									FormOne[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 3) {
									FormOne[k - 1].setVisible(true);
									FormOne[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 4) {
									FormOne[k - 1].setVisible(true);
									FormOne[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 5) {
									FormOne[k - 1].setVisible(true);
									FormOne[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 6) {
									FormOne[k - 1].setVisible(true);
									FormOne[k - 1].setText(
											str1 + "                                                                ");
									Obtn.setEnabled(false);
								}
							}
							Otxt[0].setText(placeH[0]);
							Otxt[1].setText(placeH[1]);

							Otxt[0].setForeground(Color.LIGHT_GRAY);
							Otxt[1].setForeground(Color.LIGHT_GRAY);

							pr.close();

							break;
						} catch (Exception e) {
							System.out.println(e);
						}
					} else {
						JOptionPane.showMessageDialog(null, "please fill the class room \n     details first",
								"Schmasys worrying", JOptionPane.WARNING_MESSAGE);
						break;
					}
				}
			} else if (a.getSource() == eibtn) {
				for (int i = 0; i < 2; i++) {
					if (!(subjects == null || eitxt[i].getText().equals(placeH[i])
							|| eitxt[i].getText().length() == 0)) {
						try {
							String str = "insert into  classrooms values('" + subjects + "','" + eitxt[0].getText()
									+ "','" + eitxt[1].getText() + "');";
							PreparedStatement pr = data.con.prepareStatement(str);
							int j = pr.executeUpdate();
							System.out.println(j + " row/s effected");
							String query = "select classname from classrooms where classname like '%eight%'";
							PreparedStatement ps = data.con.prepareStatement(query);
							ResultSet rs = ps.executeQuery();
							while (rs.next()) {
								int k = rs.getRow();
								String str1 = rs.getString(1);
								if (k == 1) {
									GradeEight[k - 1].setVisible(true);
									GradeEight[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 2) {
									GradeEight[k - 1].setVisible(true);
									GradeEight[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 3) {
									GradeEight[k - 1].setVisible(true);
									GradeEight[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 4) {
									GradeEight[k - 1].setVisible(true);
									GradeEight[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 5) {
									GradeEight[k - 1].setVisible(true);
									GradeEight[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 6) {
									GradeEight[k - 1].setVisible(true);
									GradeEight[k - 1].setText(
											str1 + "                                                                ");
									eibtn.setEnabled(false);
								}
							}
							eitxt[0].setText(placeH[0]);
							eitxt[1].setText(placeH[1]);

							eitxt[0].setForeground(Color.LIGHT_GRAY);
							eitxt[1].setForeground(Color.LIGHT_GRAY);

							break;
						} catch (Exception e) {
							System.out.println(e);
						}
					} else {
						JOptionPane.showMessageDialog(null, "please fill the class room \n     details first",
								"Schmasys worrying", JOptionPane.WARNING_MESSAGE);
						break;
					}
				}
			} else if (a.getSource() == sebtn) {
				for (int i = 0; i < 3; i++) {
					if (!(subjects == null || setxt[i].getText().equals(placeH[i])
							|| setxt[i].getText().length() == 0)) {
						try {
							String str = "insert into  classrooms values('" + subjects + "','" + setxt[0].getText()
									+ "','" + setxt[1].getText() + "');";
							PreparedStatement pr = data.con.prepareStatement(str);
							int j = pr.executeUpdate();
							System.out.println(j + " row/s effected");
							String query = "select classname from classrooms where classname like '%seven%'";
							PreparedStatement ps = data.con.prepareStatement(query);
							ResultSet rs = ps.executeQuery();
							while (rs.next()) {
								int k = rs.getRow();
								String str1 = rs.getString(1);
								if (k == 1) {
									GradeSeven[k - 1].setVisible(true);
									GradeSeven[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 2) {
									GradeSeven[k - 1].setVisible(true);
									GradeSeven[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 3) {
									GradeSeven[k - 1].setVisible(true);
									GradeSeven[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 4) {
									GradeSeven[k - 1].setVisible(true);
									GradeSeven[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 5) {
									GradeSeven[k - 1].setVisible(true);
									GradeSeven[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 6) {
									GradeSeven[k - 1].setVisible(true);
									GradeSeven[k - 1].setText(
											str1 + "                                                                ");
									sebtn.setEnabled(false);
								}
							}
							setxt[0].setText(placeH[0]);
							setxt[1].setText(placeH[1]);

							setxt[0].setForeground(Color.LIGHT_GRAY);
							setxt[1].setForeground(Color.LIGHT_GRAY);

							pr.close();

							break;
						} catch (Exception e) {
							System.out.println(e);
						}
					} else {
						JOptionPane.showMessageDialog(null, "please fill the class room \n     details first",
								"Schmasys worrying", JOptionPane.WARNING_MESSAGE);
						break;
					}
				}
			} else if (a.getSource() == sibtn) {
				for (int i = 0; i < 2; i++) {
					if (!(subjects == null || sitxt[i].getText().equals(placeH[i])
							|| sitxt[i].getText().length() == 0)) {
						try {
							String str = "insert into  classrooms values('" + subjects + "','" + sitxt[0].getText()
									+ "','" + sitxt[1].getText() + "');";
							PreparedStatement pr = data.con.prepareStatement(str);
							int j = pr.executeUpdate();
							System.out.println(j + " row/s effected");
							String query = "select classname from classrooms where classname like '%grade six%'";
							PreparedStatement ps = data.con.prepareStatement(query);
							ResultSet rs = ps.executeQuery();
							while (rs.next()) {
								int k = rs.getRow();
								String str1 = rs.getString(1);
								if (k == 1) {
									GradeSix[k - 1].setVisible(true);
									GradeSix[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 2) {
									GradeSix[k - 1].setVisible(true);
									GradeSix[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 3) {
									GradeSix[k - 1].setVisible(true);
									GradeSix[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 4) {
									GradeSix[k - 1].setVisible(true);
									GradeSix[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 5) {
									GradeSix[k - 1].setVisible(true);
									GradeSix[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 6) {
									GradeSix[k - 1].setVisible(true);
									GradeSix[k - 1].setText(
											str1 + "                                                                ");
									sibtn.setEnabled(false);
								}
							}
							sitxt[0].setText(placeH[0]);
							sitxt[1].setText(placeH[1]);

							sitxt[0].setForeground(Color.LIGHT_GRAY);
							sitxt[1].setForeground(Color.LIGHT_GRAY);

							pr.close();

							break;
						} catch (Exception e) {
							System.out.println(e);
						}
					} else {
						JOptionPane.showMessageDialog(null, "please fill the class room \n     details first",
								"Schmasys worrying", JOptionPane.WARNING_MESSAGE);
						break;
					}
				}
			} else if (a.getSource() == fibtn) {
				for (int i = 0; i < 2; i++) {
					if (!(subjects == null || fitxt[i].getText().equals(placeH[i])
							|| fitxt[i].getText().length() == 0)) {
						try {
							String str = "insert into  classrooms values('" + subjects + "','" + fitxt[0].getText()
									+ "','" + fitxt[1].getText() + "');";
							PreparedStatement pr = data.con.prepareStatement(str);
							int j = pr.executeUpdate();
							System.out.println(j + " row/s effected");
							String query = "select classname from classrooms where classname like '%grade fi%'";
							PreparedStatement ps = data.con.prepareStatement(query);
							ResultSet rs = ps.executeQuery();
							while (rs.next()) {
								int k = rs.getRow();
								String str1 = rs.getString(1);
								if (k == 1) {
									GradeFive[k - 1].setVisible(true);
									GradeFive[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 2) {
									GradeFive[k - 1].setVisible(true);
									GradeFive[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 3) {
									GradeFive[k - 1].setVisible(true);
									GradeFive[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 4) {
									GradeFive[k - 1].setVisible(true);
									GradeFive[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 5) {
									GradeFive[k - 1].setVisible(true);
									GradeFive[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 6) {
									GradeFive[k - 1].setVisible(true);
									GradeFive[k - 1].setText(
											str1 + "                                                                ");
									fibtn.setEnabled(false);
								}
							}
							fitxt[0].setText(placeH[0]);
							fitxt[1].setText(placeH[1]);

							fitxt[0].setForeground(Color.LIGHT_GRAY);
							fitxt[1].setForeground(Color.LIGHT_GRAY);

							pr.close();

							break;
						} catch (Exception e) {
							System.out.println(e);
						}
					} else {
						JOptionPane.showMessageDialog(null, "please fill the class room \n     details first",
								"Schmasys worrying", JOptionPane.WARNING_MESSAGE);
						break;
					}
				}
			} else if (a.getSource() == fobtn) {
				for (int i = 0; i < 2; i++) {
					if (!(subjects == null || fotxt[i].getText().equals(placeH[i])
							|| fotxt[i].getText().length() == 0)) {
						try {
							String str = "insert into  classrooms values('" + subjects + "','" + fotxt[0].getText()
									+ "','" + fotxt[1].getText() + "');";
							PreparedStatement pr = data.con.prepareStatement(str);
							int j = pr.executeUpdate();
							System.out.println(j + " row/s effected");
							String query = "select classname from classrooms where classname like '%grade fo%'";
							PreparedStatement ps = data.con.prepareStatement(query);
							ResultSet rs = ps.executeQuery();
							while (rs.next()) {
								int k = rs.getRow();
								String str1 = rs.getString(1);
								if (k == 1) {
									GradeFour[k - 1].setVisible(true);
									GradeFour[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 2) {
									GradeFour[k - 1].setVisible(true);
									GradeFour[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 3) {
									GradeFour[k - 1].setVisible(true);
									GradeFour[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 4) {
									GradeFour[k - 1].setVisible(true);
									GradeFour[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 5) {
									GradeFour[k - 1].setVisible(true);
									GradeFour[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 6) {
									GradeFour[k - 1].setVisible(true);
									GradeFour[k - 1].setText(
											str1 + "                                                                ");
									fobtn.setEnabled(false);
								}
							}
							fotxt[0].setText(placeH[0]);
							fotxt[1].setText(placeH[1]);

							fotxt[0].setForeground(Color.LIGHT_GRAY);
							fotxt[1].setForeground(Color.LIGHT_GRAY);

							pr.close();

							break;
						} catch (Exception e) {
							System.out.println(e);
						}
					} else {
						JOptionPane.showMessageDialog(null, "please fill the class room \n     details first",
								"Schmasys worrying", JOptionPane.WARNING_MESSAGE);
						break;
					}
				}
			} else if (a.getSource() == thbtn) {
				for (int i = 0; i < 2; i++) {
					if (!(subjects == null || TRtxt[i].getText().equals(placeH[i])
							|| TRtxt[i].getText().length() == 0)) {
						try {
							String str = "insert into  classrooms values('" + subjects + "','" + TRtxt[0].getText()
									+ "','" + TRtxt[1].getText() + "');";
							PreparedStatement pr = data.con.prepareStatement(str);
							int j = pr.executeUpdate();
							System.out.println(j + " row/s effected");
							String query = "select classname from classrooms where classname like '%grade th%'";
							PreparedStatement ps = data.con.prepareStatement(query);
							ResultSet rs = ps.executeQuery();
							while (rs.next()) {
								int k = rs.getRow();
								String str1 = rs.getString(1);
								if (k == 1) {
									GradeThree[k - 1].setVisible(true);
									GradeThree[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 2) {
									GradeThree[k - 1].setVisible(true);
									GradeThree[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 3) {
									GradeThree[k - 1].setVisible(true);
									GradeThree[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 4) {
									GradeThree[k - 1].setVisible(true);
									GradeThree[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 5) {
									GradeThree[k - 1].setVisible(true);
									GradeThree[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 6) {
									GradeThree[k - 1].setVisible(true);
									GradeThree[k - 1].setText(
											str1 + "                                                                ");
									thbtn.setEnabled(false);
								}
							}
							TRtxt[0].setText(placeH[0]);
							TRtxt[1].setText(placeH[1]);

							TRtxt[0].setForeground(Color.LIGHT_GRAY);
							TRtxt[1].setForeground(Color.LIGHT_GRAY);

							pr.close();

							break;
						} catch (Exception e) {
							System.out.println(e);
						}
					} else {
						JOptionPane.showMessageDialog(null, "please fill the class room \n     details first",
								"Schmasys worrying", JOptionPane.WARNING_MESSAGE);
						break;
					}
				}
			} else if (a.getSource() == twbtn) {
				for (int i = 0; i < 2; i++) {
					if (!(subjects == null || Twotxt[i].getText().equals(placeH[i])
							|| Twotxt[i].getText().length() == 0)) {
						try {
							String str = "insert into  classrooms values('" + subjects + "','" + Twotxt[0].getText()
									+ "','" + Twotxt[1].getText() + "');";
							PreparedStatement pr = data.con.prepareStatement(str);
							int j = pr.executeUpdate();
							System.out.println(j + " row/s effected");
							String query = "select classname from classrooms where classname like '%grade tw%'";
							PreparedStatement ps = data.con.prepareStatement(query);
							ResultSet rs = ps.executeQuery();
							while (rs.next()) {
								int k = rs.getRow();
								String str1 = rs.getString(1);
								if (k == 1) {
									GradeTwo[k - 1].setVisible(true);
									GradeTwo[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 2) {
									GradeTwo[k - 1].setVisible(true);
									GradeTwo[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 3) {
									GradeTwo[k - 1].setVisible(true);
									GradeTwo[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 4) {
									GradeTwo[k - 1].setVisible(true);
									GradeTwo[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 5) {
									GradeTwo[k - 1].setVisible(true);
									GradeTwo[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 6) {
									GradeTwo[k - 1].setVisible(true);
									GradeTwo[k - 1].setText(
											str1 + "                                                                ");
									twbtn.setEnabled(false);
								}
							}
							Twotxt[0].setText(placeH[0]);
							Twotxt[1].setText(placeH[1]);

							Twotxt[0].setForeground(Color.LIGHT_GRAY);
							Twotxt[1].setForeground(Color.LIGHT_GRAY);

							pr.close();

							break;
						} catch (Exception e) {
							System.out.println(e);
						}
					} else {
						JOptionPane.showMessageDialog(null, "please fill the class room \n     details first",
								"Schmasys worrying", JOptionPane.WARNING_MESSAGE);
						break;
					}
				}
			} else if (a.getSource() == obtn) {
				for (int i = 0; i < 2; i++) {
					if (!(subjects == null || Ontxt[i].getText().equals(placeH[i])
							|| Ontxt[i].getText().length() == 0)) {
						try {
							String str = "insert into  classrooms values('" + subjects + "','" + Ontxt[0].getText()
									+ "','" + Ontxt[1].getText() + "');";
							PreparedStatement pr = data.con.prepareStatement(str);
							int j = pr.executeUpdate();
							System.out.println(j + " row/s effected");
							String query = "select classname from classrooms where classname like '%grade one%'";
							PreparedStatement ps = data.con.prepareStatement(query);
							ResultSet rs = ps.executeQuery();
							while (rs.next()) {
								int k = rs.getRow();
								String str1 = rs.getString(1);
								if (k == 1) {
									GradeOne[k - 1].setVisible(true);
									GradeOne[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 2) {
									GradeOne[k - 1].setVisible(true);
									GradeOne[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 3) {
									GradeOne[k - 1].setVisible(true);
									GradeOne[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 4) {
									GradeOne[k - 1].setVisible(true);
									GradeOne[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 5) {
									GradeOne[k - 1].setVisible(true);
									GradeOne[k - 1].setText(
											str1 + "                                                                ");

								} else if (k == 6) {
									GradeOne[k - 1].setVisible(true);
									GradeOne[k - 1].setText(
											str1 + "                                                                ");
									obtn.setEnabled(false);
								}
							}
							Ontxt[0].setText(placeH[0]);
							Ontxt[1].setText(placeH[1]);

							Ontxt[0].setForeground(Color.LIGHT_GRAY);
							Ontxt[1].setForeground(Color.LIGHT_GRAY);

							pr.close();
							data.st.close();
							data.con.close();

							break;
						} catch (Exception e) {
							System.out.println(e);
						}
					} else {
						JOptionPane.showMessageDialog(null, "please fill the class room \n     details first",
								"Schmasys worrying", JOptionPane.WARNING_MESSAGE);
						break;
					}
				}
			}

		}

	}

	private class keyListener implements KeyListener {

		public void keyPressed(KeyEvent arg0) {

		}

		public void keyReleased(KeyEvent arg0) {

		}

		public void keyTyped(KeyEvent arg0) {
			char c = arg0.getKeyChar();
			if ((Character.isDigit(c))) {
				getToolkit().beep();
				arg0.consume();
			}
		}

	}
}