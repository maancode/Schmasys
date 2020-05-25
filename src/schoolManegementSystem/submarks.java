package schoolManegementSystem;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.MailcapCommandMap;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.TableColumn;

import org.sqlite.core.DB;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import net.proteanit.sql.DbUtils;

public class submarks extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel pane, title;
	private JScrollPane spane, sr;
	private JLabel titles[];
	private String str[] = { "Examination", "Exam Date", "Class Name" };
	private JLabel Image1, name, no;
	private JComboBox<String> cl, date, ex;
	private JTextField se;
	private JList<ImgNtext> li;
	private DefaultListModel<ImgNtext> m;
	private JTable tb;
	private JButton btn;
	public String Class;
	private String year, exam = "Term one";
	public static String dates, Exams;
	private JPopupMenu pop;
	private JPanel Container;
	private String names, Examer, Dater, Classer;
	private int nn = 0;
	private JButton print, sendEmail, save;
	private JProgressBar bar;
	private JButton finals, tobTen;
	private String Emails;
	private int timer, j, subc, isd;

	Databases data = new Databases();
	AddMarks AM = new AddMarks();
	Profile pr = new Profile();
	PrintMarks pm = new PrintMarks();

	public submarks() {
		tabel();
		options();
		panels();
		desing();

	}

	private void tabel() {
		Databases database = new Databases();
		try {
			tb = new JTable();
			tb.setPreferredScrollableViewportSize(new Dimension(0, 0));
			tb.setFont(new Font("ARIAL", Font.PLAIN, 15));
			tb.getTableHeader().setBackground(new Color(34, 166, 242));
			tb.getTableHeader().setForeground(Color.white);
			tb.getTableHeader().setFont(new Font("Calibri", Font.PLAIN, 15));
			tb.getTableHeader().removeAll();
			tb.setBorder(BorderFactory.createSoftBevelBorder(10));
			tb.setSurrendersFocusOnKeystroke(false);
			tb.setRowHeight(30);
			tb.setSelectionForeground(Color.black);
			tb.setFont(new Font("Calibri", Font.PLAIN, 15));
			tb.setSelectionBackground(new Color(97, 146, 194, 100));

			BufferedImage buf = null;
			try {
				buf = ImageIO.read(getClass().getResource("/image/prof.png"));
			} catch (Exception e) {
				System.out.println(e);
			}

			pop = new JPopupMenu();

			JMenuItem c = new JMenuItem(
					new ImageIcon(new ImageIcon(buf).getImage().getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING)));
			c.setText("Open profile");
			c.setBackground(Color.white);
			c.setFont(new Font("ARIAL", Font.PLAIN, 15));
			c.setBorder(BorderFactory.createSoftBevelBorder(10));
			pop.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(244, 244, 244)));
			pop.setFont(new Font("Roman", Font.PLAIN, 15));

			pop.add(c);
			tb.add(pop);
			c.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					Databases db = new Databases();
					try {

						String url = "jdbc:sqlite:students.db";
						db.classForname();
						db.con = DriverManager.getConnection(url);
						db.st = db.con.createStatement();
						ResultSet rss = db.st
								.executeQuery("SELECT classname from records WHERE Student_name ='" + names + "'");
						if (rss.next()) {
							pr.setVisible(true);
							Container.setVisible(false);
							pop.setVisible(false);

							ResultSet rss1 = db.st
									.executeQuery("SELECT classname from records WHERE Student_name ='" + names + "'");
							String classes = rss1.getString("classname");

							pr.getPeramiters(names, Examer, Dater, classes);

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

				}

			});

			tb.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					names = (String) tb.getValueAt(tb.getSelectedRow(), 1);
					if (e.isPopupTrigger()) {
						pop.show(tb, e.getX(), e.getY());

					}
				}
			});

			String urls = "jdbc:sqlite:class.db";
			database.classForname();
			database.con = DriverManager.getConnection(urls);
			database.st = database.con.createStatement();
			ResultSet rs2 = database.st.executeQuery("SELECT * from classrooms ORDER by classname ASC");
			rs2.next();
			String taker = rs2.getString(1);
			database.con.close();
			AM.classname(taker, null, null);
			String url = "jdbc:sqlite:students.db";
			Classer = taker;
			database.classForname();
			database.con = DriverManager.getConnection(url);
			database.st = database.con.createStatement();
			String tab = "SELECT Rowid as ID, Student_name ,Total,Average FROM records where classname='" + taker
					+ "' AND deleted = 0  ORDER by Average DESC";

			ResultSet rs = database.st.executeQuery(tab);
			boolean b = rs.next();
			if (b) {
				tb.setModel(DbUtils.resultSetToTableModel(rs));
				nn = 1;
				rowId(tb);
			}

			else {
				nn = 0;
				String tab1 = "SELECT Rowid as ID,Student_name ,Total,Average FROM student where Student_class='"
						+ taker + "' ORDER BY Student_name asc";
				ResultSet rs1 = database.st.executeQuery(tab1);
				tb.setModel(DbUtils.resultSetToTableModel(rs1));
			}
			rowId(tb);
			collw(tb);
			database.con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void rowId(JTable tb) {
		for (int row = 0; row < tb.getRowCount(); row++) {
			tb.setValueAt(row + 1, row, 0);
		}
	}

	private void collw(JTable tb2) {
		TableColumn col = null;
		for (int i = 0; i < tb2.getColumnCount(); i++) {
			col = tb2.getColumnModel().getColumn(i);
//			col.setPreferredWidth(100);
			if (i == 0) {
				col.setPreferredWidth(50);

			} else {
				col.setPreferredWidth(100);
			}

		}
	}

	private void options() {

		title = new JPanel();
		title.setBackground(Color.white);
		title.setBounds(5, 170, 1035, 30);
		title.setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, new Color(4, 18, 89)));
		title.setLayout(null);

		int ll[] = { 10, 400, 800 };
		titles = new JLabel[3];
		for (int i = 0; i < titles.length; i++) {
			titles[i] = new JLabel();
			titles[i].setBounds(ll[i], 5, 250, 30);
			titles[i].setFont(new Font("Calibri", Font.PLAIN, 15));
			titles[i].setForeground(Color.gray);

			title.add(titles[i]);

		}

		btn = new JButton("Add Marks");
		btn.setBackground(new Color(57, 162, 217));
		btn.setBorder(BorderFactory.createSoftBevelBorder(10));
		btn.setBounds(900, 10, 100, 30);
		btn.setForeground(Color.white);
		btn.setFont(new Font("Calibri", Font.PLAIN, 15));
		btn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				AM.setVisible(true);
				pane.setVisible(false);
				spane.setVisible(false);
				try {

					data.con.close();
				} catch (Exception ew) {
					System.out.println(ew);
				}

			}
		});
		pr.logo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				pr.setVisible(false);
				Container.setVisible(true);

			}
		});

		AM.back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				AM.setVisible(false);
				pane.setVisible(true);
				spane.setVisible(true);
			}
		});

		BufferedImage im = null;
		try {
			im = ImageIO.read(getClass().getResource("/image/UJUHU-01.png"));
		} catch (Exception e) {
		}

		Image1 = new JLabel();
		Image1.setBounds(400, 7, 200, 145);
		Image1.setIcon(new ImageIcon(new ImageIcon(im).getImage().getScaledInstance(Image1.getWidth(),
				Image1.getHeight(), Image.SCALE_AREA_AVERAGING)));

		name = new JLabel();
		name.setBounds(10, 15, 380, 45);
		name.setFont(new Font("Calibri", Font.BOLD, 16));
		name.setText("Schmasys school");

		Databases database = new Databases();
		try {
			String url = "jdbc:sqlite:login.db";
			String data = "select  logo,schoolName from loging";
			database.classForname();
			database.con = DriverManager.getConnection(url);
			database.st = database.con.createStatement();
			ResultSet rs = database.st.executeQuery(data);
			rs.next();
			byte[] imageByte = rs.getBytes("logo");
			String getter = rs.getString("schoolName");
			Image1.setIcon(new ImageIcon(new ImageIcon(imageByte).getImage().getScaledInstance(Image1.getWidth(),
					Image1.getHeight(), Image.SCALE_AREA_AVERAGING)));
			name.setText(getter);

			database.st.close();
			database.con.close();

		} catch (Exception e) {
			System.out.println();
		}

		no = new JLabel();
		no.setBounds(950, 120, 380, 35);
		no.setFont(new Font("Calibri", Font.BOLD, 16));

		BufferedImage prints = null;
		try {
			prints = ImageIO.read(getClass().getResource("/image/print1.png"));

		} catch (Exception e) {

		}

		BufferedImage saves = null;
		try {
			saves = ImageIO.read(getClass().getResource("/image/save.png"));

		} catch (Exception e) {

		}

		BufferedImage dd1 = null;
		try {
			dd1 = ImageIO.read(getClass().getResource("/image/top-10.png"));

		} catch (Exception e) {

		}
		tobTen = new JButton(
				new ImageIcon(new ImageIcon(dd1).getImage().getScaledInstance(28, 28, Image.SCALE_AREA_AVERAGING)));
		tobTen.setBounds(890, 122, 32, 32);
		tobTen.setBorder(BorderFactory.createSoftBevelBorder(10));
		tobTen.setOpaque(false);
		tobTen.setVisible(true);
		tobTen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isd = 1;
				titles[2].setText(str[2] + " : " + Classer.substring(0, Classer.indexOf("of")));
				Databases db = new Databases();
				try {
					String url = "jdbc:sqlite:students.db";
					db.classForname();
					db.con = DriverManager.getConnection(url);
					db.st = db.con.createStatement();
//					 Examer, Dater
					String tab = "SELECT Rowid as ID,Student_name ,Total,Average  FROM records where classname like'"
							+ Classer.substring(0, Classer.indexOf("of")) + "%' and Examination='" + Examer
							+ "' and ExamDate ='" + Dater + "' AND deleted = 0 ORDER by Average DESC LIMIT 10";

					ResultSet rs = db.st.executeQuery(tab);

					tb.setModel(DbUtils.resultSetToTableModel(rs));
					rowId(tb);
				} catch (Exception f) {
					f.printStackTrace();
				} finally {
					try {
						db.con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		});

		BufferedImage dd = null;
		try {
			dd = ImageIO.read(getClass().getResource("/image/final.png"));

		} catch (Exception e) {

		}
		finals = new JButton(
				new ImageIcon(new ImageIcon(dd).getImage().getScaledInstance(28, 28, Image.SCALE_AREA_AVERAGING)));
		finals.setBounds(830, 122, 32, 32);
		finals.setBorder(BorderFactory.createSoftBevelBorder(10));
		finals.setOpaque(false);
		finals.setToolTipText("show the total exam year");
		finals.setVisible(true);
		finals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getcountSubject();
				titles[0].setText("Examination : Total Of Exams ");
				j = 1;
				if (isd == 0) {
					Databases db = new Databases();
					try {
						String url = "jdbc:sqlite:students.db";
						db.classForname();
						db.con = DriverManager.getConnection(url);
						db.st = db.con.createStatement();

						String tab = "SELECT  ROWID as ID ,Student_name, SUM(Total) as Total,((SUM(Total)/"
								+ (subc * 100) + ")*100) as Average FROM records WHERE classname='" + Classer
								+ "' and ExamDate ='" + Dater
								+ "'  AND deleted = 0 GROUP BY Student_name ORDER by ((SUM(Total)/" + (subc * 100)
								+ ")*100) DESC";

						ResultSet rs = db.st.executeQuery(tab);

						tb.setModel(DbUtils.resultSetToTableModel(rs));
						rowId(tb);
					} catch (Exception f) {
						f.printStackTrace();
					} finally {
						try {
							db.con.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} else {
					Databases db = new Databases();
					try {
						String url = "jdbc:sqlite:students.db";
						db.classForname();
						db.con = DriverManager.getConnection(url);
						db.st = db.con.createStatement();

						String tab = "SELECT  ROWID as ID ,Student_name, SUM(Total) as Total,((SUM(Total)/"
								+ (subc * 100) + ")*100) as Average FROM records WHERE classname like'"
								+ Classer.substring(0, Classer.indexOf("of")) + "%' and ExamDate ='" + Dater
								+ "'  AND deleted = 0 GROUP BY Student_name ORDER by ((SUM(Total)/" + (subc * 100)
								+ ")*100) DESC";

						ResultSet rs = db.st.executeQuery(tab);

						tb.setModel(DbUtils.resultSetToTableModel(rs));
						rowId(tb);
					} catch (Exception f) {
						f.printStackTrace();
					} finally {
						try {
							db.con.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}

				subc = 0;

			}

			private void getcountSubject() {
				Databases data = new Databases();
				if (Classer.substring(0, 4).equalsIgnoreCase("form")) {

					try {
						data.classForname();
						data.con = DriverManager.getConnection("jdbc:sqlite:class.db");
						data.st = data.con.createStatement();
						ResultSet rs = data.st.executeQuery("select * from subjects");
						while (rs.next()) {
							subc++;
						}
						data.con.close();

					} catch (Exception e) {
						System.out.println(e);
					}

				}

				if (Classer.substring(0, 5).equalsIgnoreCase("grade")) {
					subc = 5;

					try {
						data.classForname();
						data.con = DriverManager.getConnection("jdbc:sqlite:class.db");
						data.st = data.con.createStatement();
						ResultSet rs = data.st.executeQuery("select * from Psubj");
						while (rs.next()) {

							subc++;
						}
						data.con.close();

					} catch (Exception e) {
						System.out.println(e);
					}

				}
			}
		});

		save = new JButton(new ImageIcon(saves));
		save.setBounds(770, 122, 32, 32);
		save.setBorder(BorderFactory.createSoftBevelBorder(10));
		save.setOpaque(false);
		save.setToolTipText("Save the student marks in your system.");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ar) {

				if (tb.getRowCount() != 0) {
					sendEmail.setVisible(false);
					print.setVisible(false);
					save.setVisible(false);
					finals.setVisible(false);
					tobTen.setVisible(false);
					bar.setVisible(true);
					bar.setMaximum(tb.getRowCount());
					saving sav = new saving();
					sav.start();

				} else {
					JOptionPane.showMessageDialog(null, "This classroom is empty", "schmasys info",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}

		});

		print = new JButton(new ImageIcon(prints));
		print.setBounds(710, 120, 32, 32);
		print.setBorder(BorderFactory.createSoftBevelBorder(10));
		print.setOpaque(false);
		print.setToolTipText("print the records or save as a pdf");
		print.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btn.setVisible(false);
				print.setVisible(false);
				save.setVisible(false);
				finals.setVisible(false);
				tobTen.setVisible(false);
				spane.setBounds(5, 200, 1035, 10000);
				print(Container);
				spane.setBounds(5, 200, 1035, 535);
				print.setVisible(true);
				save.setVisible(true);
				finals.setVisible(true);
				btn.setVisible(true);
				tobTen.setVisible(true);

			}

			private void print(JPanel container) {
				PrinterJob prin = PrinterJob.getPrinterJob();
				prin.setJobName("test");
				prin.setPrintable(new Printable() {
					public int print(Graphics graph, PageFormat pf, int pageIndex) throws PrinterException {
						if (pageIndex > 0) {
							return Printable.NO_SUCH_PAGE;
						}
						Graphics2D gr2d = (Graphics2D) graph;
						gr2d.translate(pf.getImageableX() * 2, pf.getImageableY() * 2);
						gr2d.scale(0.585, 0.585);
						container.paint(gr2d);

						return Printable.PAGE_EXISTS;
					}
				});
				boolean rtrslt = prin.printDialog();
				if (rtrslt) {
					try {
						prin.print();
						JOptionPane.showMessageDialog(null, "Print Successfully", "print",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (PrinterException e) {
						System.out.println(e);
					}
				}
			}
		});

		BufferedImage send = null;
		try {
			send = ImageIO.read(getClass().getResource("/image/send.png"));

		} catch (Exception e) {

		}

		sendEmail = new JButton();
		sendEmail.setBounds(650, 123, 30, 30);
		sendEmail.setIcon(new ImageIcon(new ImageIcon(send).getImage().getScaledInstance(sendEmail.getWidth(),
				sendEmail.getHeight(), Image.SCALE_AREA_AVERAGING)));
		sendEmail.setBorder(BorderFactory.createSoftBevelBorder(10));
		sendEmail.setOpaque(false);
		sendEmail.setToolTipText("send the records to the student EMAIL");
		sendEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tb.getRowCount() != 0) {
					sendEmail.setVisible(false);
					print.setVisible(false);
					save.setVisible(false);
					finals.setVisible(false);
					tobTen.setVisible(false);
					bar.setVisible(true);

					bar.setMaximum(tb.getRowCount());

					mails mail = new mails();
					mail.start();
				} else {
					JOptionPane.showMessageDialog(null, "This classroom is empty", "schmasys info",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}

		});

		try {

			data.classForname();
			data.con = DriverManager.getConnection("jdbc:sqlite:class.db");
			data.st = data.con.createStatement();
			String query = "SELECT * from classrooms ORDER by classname like 'form four of four%'";
			ResultSet rs = data.st.executeQuery(query);

			cl = new JComboBox<String>();
			cl.setBounds(10, 60, 350, 30);
			cl.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
			cl.setOpaque(false);
			cl.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					j = 0;
					isd = 0;
					Class = cl.getSelectedItem().toString();
					AM.classname(Class, null, null);
					Classer = Class;
					tobTen.setToolTipText(
							"show the tob 10 student of : " + Classer.substring(0, Classer.indexOf("of")) + "");
					titles[2].setText(str[2] + " : " + Class);

					Databases data = new Databases();
					try {
						String url = "jdbc:sqlite:students.db";

						data.classForname();
						data.con = DriverManager.getConnection(url);
						data.st = data.con.createStatement();

						Databases db = new Databases();

						String url1 = "jdbc:sqlite:class.db";
						db.classForname();
						db.con = DriverManager.getConnection(url1);
						db.st = db.con.createStatement();
						ResultSet rss = db.st.executeQuery("select exam , dates from laster ORDER by exam DESC");
						String ex = null;
						String da = null;
						if (rss.next()) {
							ResultSet rss1 = db.st.executeQuery("select exam , dates from laster");
							while (rss1.next()) {
								ex = rss1.getString("exam");
								da = rss1.getString("dates");
							}
						} else {
							ex = null;
							da = null;
						}
						String tab = "SELECT Rowid as ID,Student_name ,Total,Average  FROM records where classname='"
								+ Class + "' and Examination='" + ex + "' and ExamDate ='" + da
								+ "' AND deleted = 0 ORDER by Average DESC";
						ResultSet rrr = data.st.executeQuery(tab);
						int i = 0;
						while (rrr.next()) {
							i = rrr.getRow();
						}
						no.setText("NO: " + i);

						ResultSet rs = data.st.executeQuery(tab);

						if (rs.next()) {
							ResultSet r = data.st.executeQuery(tab);
							tb.setModel(DbUtils.resultSetToTableModel(r));
							rowId(tb);
							String tab1 = "SELECT Examination,ExamDate  FROM records where classname='" + Class
									+ "' and Examination='" + ex + "' and ExamDate ='" + da + "'AND deleted = 0 ";
							ResultSet rs1 = data.st.executeQuery(tab1);

							if (rs1.next()) {
								titles[0].setText(str[0] + " : " + rs1.getString("Examination"));
								titles[1].setText(str[1] + " : " + rs1.getString("ExamDate"));
								AM.lb[1].setText("Examination : " + rs1.getString("Examination"));
								AM.lb[2].setText("Exam Date : " + rs1.getString("ExamDate"));
								AM.classname(Class, rs1.getString("ExamDate"), rs1.getString("Examination"));
								Examer = rs1.getString("Examination");
								Dater = rs1.getString("ExamDate");

							} else {
								titles[0].setText(str[0] + " : " + null);
								titles[1].setText(str[1] + " : " + null);
								AM.lb[1].setText("Examination : " + null);
								AM.lb[2].setText("Exam Date : " + null);
								AM.classname(Class, null, null);
								Examer = null;
								Dater = null;
							}
							rs1.close();
						} else {
							String tab1 = "SELECT Rowid as ID, Student_name ,Total,Average FROM student where Student_class='"
									+ Class + "' ORDER by[Student_name]";
							ResultSet rs1 = data.st.executeQuery(tab1);
							tb.setModel(DbUtils.resultSetToTableModel(rs1));
							rowId(tb);
							String tab2 = "SELECT Examination,ExamDate FROM student where Student_class='" + Class
									+ "'";

							ResultSet rs2 = data.st.executeQuery(tab2);

							if (rs2.next()) {
								titles[0].setText(str[0] + " : " + rs1.getString("Examination"));
								titles[1].setText(str[1] + " : " + rs1.getString("ExamDate"));
								AM.lb[1].setText("Examination : " + rs1.getString("Examination"));
								AM.lb[2].setText("Exam Date : " + rs1.getString("ExamDate"));
								dates = rs1.getString("ExamDate");
								Exams = rs1.getString("Examination");
								AM.classname(Class, rs1.getString("ExamDate"), rs1.getString("Examination"));
								Examer = rs1.getString("Examination");
								Dater = rs1.getString("ExamDate");
							} else {
								titles[0].setText(str[0] + " : " + null);
								titles[1].setText(str[1] + " : " + null);
								AM.lb[1].setText("Examination : " + null);
								AM.lb[2].setText("Exam Date : " + null);
								dates = null;
								Exams = null;
								AM.classname(Class, null, null);
								Examer = null;
								Dater = null;
							}

						}
						db.con.close();
						data.con.close();
					} catch (Exception e) {
						System.out.println(e);
					}

				}

			});
			cl.setFont(new Font("Calibri", Font.PLAIN, 18));

			while (rs.next()) {

				String str = rs.getString("classname");
//	
				cl.addItem(str);

			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				data.st.close();
				data.con.close();
			} catch (Exception e) {

			}
		}

		try {
			Databases datas = new Databases();

			datas.classForname();
			datas.con = DriverManager.getConnection("jdbc:sqlite:class.db");
			datas.st = datas.con.createStatement();

			String query = "SELECT * from Exam";
			ResultSet rs = datas.st.executeQuery(query);

//			String query1 = "select exdate  from edate GROUP by exdate having count(exdate) > 0";
//			ResultSet rs1 = datas.st.executeQuery(query1);
			date = new JComboBox<String>();
			date.setBounds(650, 90, 350, 30);
			date.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
			date.setOpaque(false);
			date.addItem("Select exam_date");
//			rs1.next();
//				date.addItem(rs1.getString(1));
//			
//			
			date.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {

					year = date.getSelectedItem().toString();
					if (!(year.equalsIgnoreCase("Select exam_date"))) {
						isd = 0;
						AM.lb[1].setText("Examination : " + exam);
						AM.lb[2].setText("Exam Date : " + year);
						titles[0].setText("Examination : " + exam);
						titles[1].setText("Exam Date : " + year);
						Examer = exam;
						Dater = year;
						AM.classname(Class, year, exam);
						String query = "Select Rowid as ID, Student_name,Total,Average from records where classname='"
								+ Class + "' And Examination= '" + exam + "' and ExamDate='" + year
								+ "'AND deleted = 0 ORDER by Average DESC";
						try {
							String url = "jdbc:sqlite:students.db";

							data.classForname();
							data.con = DriverManager.getConnection(url);
							data.st = data.con.createStatement();

							ResultSet rs = data.st.executeQuery(query);
							tb.setModel(DbUtils.resultSetToTableModel(rs));
							rowId(tb);
							data.con.close();

						} catch (Exception e) {
							System.out.println(e);
						}
					}

				}

			});

			ex = new JComboBox<String>();
			ex.setBounds(650, 50, 350, 30);
			ex.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
			ex.setOpaque(false);
			ex.setFont(new Font("Calibri", Font.PLAIN, 18));
			while (rs.next()) {
				String get = rs.getString(1);
				ex.addItem(get);
			}
			datas.con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		ex.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				exam = ex.getSelectedItem().toString();
				isd = 0;
			}

		});

		try {
			Databases datas = new Databases();

			datas.classForname();
			datas.con = DriverManager.getConnection("jdbc:sqlite:class.db");
			datas.st = datas.con.createStatement();

			String q = "select exdate  from edate GROUP by exdate having count(exdate) > 0";
			ResultSet g = datas.st.executeQuery(q);
			while (g.next())
				date.addItem(g.getString(1));
			datas.con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		try {

			String url = "jdbc:sqlite:students.db";

			database.classForname();
			database.con = DriverManager.getConnection(url);
			database.st = database.con.createStatement();

			m = new DefaultListModel<ImgNtext>();

			li = new JList<ImgNtext>();
			li.setFont(new Font("ARIAL", Font.BOLD, 13));
			li.setBounds(0, 0, 350, 30);
			li.setCellRenderer(new Randerer());

			li.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent e) {
					String name = ((ImgNtext) li.getSelectedValue()).getName();

					if (nn == 1) {

						Databases db = new Databases();
						try {

							String url = "jdbc:sqlite:students.db";
							db.classForname();
							db.con = DriverManager.getConnection(url);
							db.st = db.con.createStatement();
							ResultSet rss = db.st
									.executeQuery("SELECT classname from records WHERE Student_name ='" + name + "'");
							if (rss.next()) {
								pr.setVisible(true);
								Container.setVisible(false);
								pop.setVisible(false);

								ResultSet rss1 = db.st.executeQuery(
										"SELECT classname from records WHERE Student_name ='" + name + "'");
								String classes = rss1.getString("classname");

								pr.getPeramiters(name, Examer, Dater, classes);

								se.setText("\tSearch student here");
								se.setForeground(Color.LIGHT_GRAY);
								sr.setVisible(false);
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
					} else
						JOptionPane.showMessageDialog(null, "there is no any records or marks", "Schmasys",
								JOptionPane.WARNING_MESSAGE);

				}
			});

			li.setModel(m);

			sr = new JScrollPane(li);
			sr.setBounds(10, 145, 350, 300);
			sr.setBorder(BorderFactory.createSoftBevelBorder(10));
			sr.setVisible(false);

			se = new JTextField();
			se.setBounds(10, 110, 350, 30);
			se.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.darkGray));
			se.setFont(new Font("ARIAL", Font.PLAIN, 15));
			se.setForeground(Color.lightGray);
			se.setText("\tSearch student here");
			se.addFocusListener(new FocusListener() {

				public void focusGained(FocusEvent a) {
					if (a.getSource() == se) {
						if (se.getText().equals("\tSearch student here") || se.getText().length() == 0) {
							se.setText("");
							se.setForeground(Color.black);

						}
					}

				}

				public void focusLost(FocusEvent a) {
					if (a.getSource() == se) {
						if (se.getText().equals("\tSearch student here") || se.getText().length() == 0) {
							se.setText("\tSearch student here");
							se.setForeground(Color.LIGHT_GRAY);
							sr.setVisible(false);
						}
					}

				}
			}

			);
			se.addKeyListener(new KeyListener() {

				public void keyPressed(KeyEvent arg0) {

				}

				public void keyReleased(KeyEvent arg0) {

					m.removeAllElements();
					try {
						String url = "jdbc:sqlite:students.db";
						Databases db = new Databases();
						db.classForname();
						db.con = DriverManager.getConnection(url);
						db.st = db.con.createStatement();
						String data = "SELECT Student_name , Student_image ,Student_gender from student where Student_name like '%"
								+ se.getText() + "%'";
						ResultSet rs = db.st.executeQuery(data);

						while (rs.next()) {
							String get = rs.getString("Student_name");
							String gender = rs.getString("Student_gender");

							byte[] imageByte = rs.getBytes("Student_image");
							if (!(se.getText().length() == 0)) {
								if (imageByte == null) {

									if (gender.equalsIgnoreCase("female")) {
										BufferedImage im = null;
										try {
											im = ImageIO.read(getClass().getResource("/image/female2.png"));
										} catch (Exception e) {
										}
										ImageIcon imn = new ImageIcon(new ImageIcon(im).getImage().getScaledInstance(64,
												64, Image.SCALE_AREA_AVERAGING));
										m.addElement(new ImgNtext(get, imn));
									} else {
										BufferedImage im = null;
										try {
											im = ImageIO.read(getClass().getResource("/image/male1.png"));
										} catch (Exception e) {
										}
										ImageIcon imn = new ImageIcon(new ImageIcon(im).getImage().getScaledInstance(64,
												64, Image.SCALE_AREA_AVERAGING));
										m.addElement(new ImgNtext(get, imn));
									}

								} else {
									ImageIcon im = new ImageIcon(new ImageIcon(imageByte).getImage()
											.getScaledInstance(64, 64, Image.SCALE_AREA_AVERAGING));
									m.addElement(new ImgNtext(get, im));
								}
								sr.setVisible(true);

							} else {
								sr.setVisible(false);
							}

						}

						db.con.close();

					} catch (SQLException e) {
						System.out.println("hey");
					}

				}

				public void keyTyped(KeyEvent arg0) {

				}

			});
			database.con.close();
		} catch (Exception e) {
			System.out.println("null");
		}

	}

	private void panels() {
		pane = new JPanel();
		pane.setBounds(5, 5, 1035, 150);
		pane.setBorder(BorderFactory.createMatteBorder(2, 1, 0, 1, new Color(230, 230, 230)));
		pane.setBackground(Color.white);
		pane.setLayout(null);
		pane.add(Image1);
		pane.add(name);
		pane.add(no);
		pane.add(cl);
		pane.add(se);
		pane.add(date);
		pane.add(ex);
		pane.add(btn);
		pane.add(print);
		pane.add(sendEmail);
		pane.add(save);
		pane.add(finals);
		pane.add(tobTen);

		bar = new JProgressBar();
		bar.setBounds(650, 125, 280, 25);
		bar.setBorder(BorderFactory.createSoftBevelBorder(10));
		bar.setStringPainted(true);

		bar.setVisible(false);
		pane.add(bar);

		spane = new JScrollPane(tb);
		spane.setBackground(Color.white);
		spane.setBounds(5, 200, 1035, 535);
		spane.setBorder(BorderFactory.createSoftBevelBorder(10));
	}

	private void desing() {
		Container = new JPanel();
		Container.setLayout(null);
		Container.setBackground(Color.white);
		Container.setBounds(0, 0, 1045, 690);

		Container.add(AM);
		Container.add(sr);
		Container.add(title);
		Container.add(spane);
		Container.add(pane);
		add(Container);
		add(pr);
		setVisible(false);
		setLayout(null);
		setBackground(Color.white);
		setBounds(0, 0, 1045, 690);
	}

	private class saving extends Thread {
		String ss = "\\Schmasys documents";

		public void run() {
			for (int i = 0; i <= bar.getMaximum() - 1; i++) {
				try {
					data.classForname();
					data.con = DriverManager.getConnection("jdbc:sqlite:students.db");
					data.st = data.con.createStatement();

					String Student_name_helder = (String) tb.getValueAt(i, 1);
					String query1 = "Select Student_name from records where Student_name='" + Student_name_helder
							+ "' AND classname='" + Classer + "';";

					ResultSet rs1 = data.st.executeQuery(query1);
					if (rs1.next()) {
						pm.getPeramiters(Student_name_helder, Examer, Dater, Classer, j);

						File fileStructure = new File(System.getProperty("user.home") + "\\Documents" + ss);
						String s = fileStructure.getAbsolutePath();
						fileStructure.mkdir();

						File subFiles = new File(s + "\\" + Classer + "");
						subFiles.mkdirs();

						File file = new File(subFiles.getAbsolutePath() + "\\" + Student_name_helder + " Marks.pdf");
						FileOutputStream fileout = new FileOutputStream(file);
						writepdf(fileout);

						fileout.close();
						bar.setValue(i + 1);

						if (i == bar.getMaximum() - 1)
							Desktop.getDesktop().open(new File(System.getProperty("user.home") + "\\Documents" + ss));
					} else {
						JOptionPane.showMessageDialog(null, "There no records for this Student", "Schmasys",
								JOptionPane.WARNING_MESSAGE);
					}
					if (i == bar.getMaximum() - 1) {

						bar.setVisible(!true);
						bar.setString(null);
						sendEmail.setVisible(!false);
						print.setVisible(!false);
						save.setVisible(!false);
						finals.setVisible(!false);
						tobTen.setVisible(!false);
						break;
					}

					data.con.close();
				} catch (Exception h) {
					System.out.println(h);
				} finally {
					try {
						data.con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

		}

		private void writepdf(FileOutputStream outstream) {
			try {
				Document dc = new Document();
				Rectangle r = new Rectangle(pm.getWidth(), pm.getHeight());
				dc.setPageSize(r);
				PdfWriter writer;

				writer = PdfWriter.getInstance(dc, outstream);

				dc.open();
				PdfContentByte contentByte = writer.getDirectContent();
				PdfTemplate template = contentByte.createTemplate(pm.getWidth(), pm.getHeight());
				Graphics2D g2 = template.createGraphics(pm.getWidth(), pm.getHeight());
				pm.print(g2);
				g2.dispose();
				contentByte.addTemplate(template, 0, 0);
				dc.close();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private class mails extends Thread {

		public void run() {

			for (int i = 0; i <= bar.getMaximum() - 1; i++) {

				try {
					data.classForname();
					data.con = DriverManager.getConnection("jdbc:sqlite:students.db");
					data.st = data.con.createStatement();

					String Student_name_helder = (String) tb.getValueAt(i, 1);
					String query1 = "Select Student_name from records where Student_name='" + Student_name_helder
							+ "' AND classname='" + Classer + "';";

					ResultSet rs1 = data.st.executeQuery(query1);
					if (rs1.next()) {
						String query = "Select Student_Email from student where Student_class='" + Classer
								+ "' AND Student_name='" + Student_name_helder + "';";
						pm.getPeramiters(Student_name_helder, Examer, Dater, Classer, 0);
						ResultSet rs = data.st.executeQuery(query);
						rs.next();
						Emails = rs.getString("Student_Email");

						if (!(Emails.equals("null"))) {
							EmailSender();
							bar.setString(Student_name_helder);
							bar.setValue(i + 1);
						} else {
							JOptionPane.showMessageDialog(null, "This student has no Email address", "Schmasys",
									JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "There no records for this Student", "Schmasys",
								JOptionPane.WARNING_MESSAGE);
					}
					if (i == bar.getMaximum() - 1) {
						JOptionPane.showMessageDialog(null, "Done", "Schmasys Email", JOptionPane.INFORMATION_MESSAGE);
						bar.setVisible(!true);
						bar.setString(null);
						sendEmail.setVisible(!false);
						print.setVisible(!false);
						save.setVisible(!false);
						finals.setVisible(!false);
						tobTen.setVisible(!false);
						break;
					}

				} catch (Exception h) {
					System.out.println(h);
				} finally {
					try {
						data.con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}

		private void EmailSender() {

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
				mm.setRecipients(Message.RecipientType.TO, InternetAddress.parse(Emails));
				mm.setSubject("schmasys marks");

				Multipart emc = new MimeMultipart();

				ByteArrayOutputStream outstream = new ByteArrayOutputStream();

				writepdf(outstream);

				byte[] by = outstream.toByteArray();
				DataSource data = new ByteArrayDataSource(by, "attachment/pdf");

				MimeBodyPart bod = new MimeBodyPart();
				bod.setDataHandler(new DataHandler(data));
				bod.setFileName("Exam :" + Examer + ", Year : " + Dater + ", Your class : " + Classer);
				emc.addBodyPart(bod);
				mm.setContent(emc);

				Transport.send(mm);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "There is no internet connection\nPlease connect to the internert",
						"internet warring", JOptionPane.WARNING_MESSAGE);

				bar.setVisible(!true);
				bar.setString(null);
				sendEmail.setVisible(!false);
				print.setVisible(!false);
				save.setVisible(!false);
				finals.setVisible(!false);
				tobTen.setVisible(!false);
			}

		}

		private void writepdf(ByteArrayOutputStream outstream) throws DocumentException {
			Document dc = new Document();
			Rectangle r = new Rectangle(pm.getWidth(), pm.getHeight());
			dc.setPageSize(r);
			PdfWriter writer = PdfWriter.getInstance(dc, outstream);
			dc.open();
			PdfContentByte contentByte = writer.getDirectContent();
			PdfTemplate template = contentByte.createTemplate(pm.getWidth(), pm.getHeight());
			Graphics2D g2 = template.createGraphics(pm.getWidth(), pm.getHeight());
			pm.print(g2);
			g2.dispose();
			contentByte.addTemplate(template, 0, 0);
			dc.close();

		}

	}

}
