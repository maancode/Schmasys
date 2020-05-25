package schoolManegementSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

public class AddMarks extends JPanel {
	private static final long serialVersionUID = 1L;
	private JScrollPane spane;
	private JPanel pane;
	private JComboBox<String> stye;
	private JComboBox<String> examname;
	public JLabel lb[];
	private JButton cus;
	private JScrollPane cust;
	private JCheckBox sub[];
	private JPanel cont;
	private JButton apply;
	private JPanel ads;
	private JTextField addSub;
	private JButton save;
	private JButton close;
	private String cols;
	private String[] year = { "Select the Studing year", "2021 - 2022", "2022 - 2023", "2023 - 2024", "2024 - 2025",
			"2025 - 2026", "2026 - 2027", "2027 - 2028", "2028 - 2029", "2029 - 2030", "2030 - 2031", "2031 - 2032",
			"2032 - 2033", "2033 - 2034", "2034 - 2035", "2035 - 2036", "2036 - 2037", "2037 - 2038", "2038 - 2039",
			"2039 - 2040", "2040 - 2041", "2041 - 2042", "2042 - 2043", "2043 - 2044", "2044 - 2045", "2045 - 2046",
			"2046 - 2047", "2047 - 2048", "2048 - 2049", "2049 - 2050", "2050 - 2051", "2051 - 2052", "2052 - 2053",
			"2053 - 2054", "2054 - 2055", "2055 - 2056", "2056 - 2057", "2057 - 2058", "2058 - 2059", "2059 - 2060",
			"2060 - 2061", "2061 - 2062", "2062 - 2063", "2063 - 2064", "2064 - 2065", "2065 - 2066", "2066 - 2067",
			"2067 - 2068", "2068 - 2069", "2069 - 2070", "2070 - 2071", "2071 - 2072", "2072 - 2073", "2073 - 2074",
			"2074 - 2075", "2075 - 2076", "2076 - 2077", "2077 - 2078", "2078 - 2079", "2079 - 2080", "2080 - 2081",
			"2081 - 2082", "2082 - 2083", "2083 - 2084", "2084 - 2085", "2085 - 2086", "2086 - 2087", "2087 - 2088",
			"2088 - 2089", "2089 - 2090", "2090 - 2091", "2091 - 2092", "2092 - 2093", "2093 - 2094", "2094 - 2095",
			"2095 - 2096", "2096 - 2097", "2097 - 2098", "2098 - 2099", "2099 - 2100", "2100 - 2101", "2101 - 2102",
			"2102 - 2103", "2103 - 2104", "2104 - 2105", "2105 - 2106", "2106 - 2107", "2107 - 2108", "2108 - 2109",
			"2109 - 2110", "2110 - 2111", "2111 - 2112", "2112 - 2113", "2113 - 2114", "2114 - 2115", "2115 - 2116",
			"2116 - 2117", "2117 - 2118", "2118 - 2119", "2119 - 2120", "2120 - 2121", };
	public JButton back;
	private int no1 = 0;
	public String subj;
	private String subjects[];
	public JTable tb;
	private String classes;
	private JButton saver;
	private String Classname[] = new String[16];
	private String yestudy = "Select the Studing year", ename;
	private String csub[];
	private String clause;
	private String ClassClause;
	private String string;
	private String exames, dates;
	private int o;
	private int uint, uint1;
	private JLabel tick;
	private String updater;
	BufferedImage image1 = null;
	Databases data = new Databases();
	actionListener action = new actionListener();
	itemlistener item = new itemlistener();

	public AddMarks() {

		panels();
		options();
		container();
		design();

	}

	public void classname(String s, String date, String Exam) {

		classes = s;
		lb[0].setText("Class : " + s);

		try {
			data.classForname();
			data.con = DriverManager.getConnection("jdbc:sqlite:class.db");
			data.st = data.con.createStatement();
			ResultSet rs = data.st.executeQuery("SELECT subject from subjects");
			String ss[] = new String[16];
			subjects = new String[ss.length];

			if (s.substring(0, 4).equalsIgnoreCase("form")) {

				while (rs.next()) {

					no1 = rs.getRow();
					ss[no1] = rs.getString(1);
					subjects[no1] = ss[no1];

//					System.out.println(ss[no]);
				}
				for (int i = 0; i < ss.length; i++) {
					Classname[i] = ss[i];
					if (!(ss[i] == null)) {
						if (i == 10) {
							subj = ss[1] + ", " + ss[2] + ", " + ss[3] + ", " + ss[4] + ", " + ss[5] + ", " + ss[6]
									+ ", " + ss[7] + ", " + ss[8] + ", " + ss[9] + ", " + ss[10];

							updater = ss[1] + " + " + ss[2] + " + " + ss[3] + " + " + ss[4] + " + " + ss[5] + " + "
									+ ss[6] + " + " + ss[7] + " + " + ss[8] + " + " + ss[9] + " + " + ss[10];
							uint = 10;
						} else if (i == 11) {
							subj = ss[1] + ", " + ss[2] + ", " + ss[3] + ", " + ss[4] + ", " + ss[5] + ", " + ss[6]
									+ ", " + ss[7] + ", " + ss[8] + ", " + ss[9] + ", " + ss[10] + ", " + ss[i];

							updater = ss[1] + " + " + ss[2] + " + " + ss[3] + " + " + ss[4] + " + " + ss[5] + " + "
									+ ss[6] + " + " + ss[7] + " + " + ss[8] + " + " + ss[9] + " + " + ss[10] + " + "
									+ ss[i];
							uint = 11;
						} else if (i == 12) {
							subj = ss[1] + ", " + ss[2] + ", " + ss[3] + ", " + ss[4] + ", " + ss[5] + ", " + ss[6]
									+ ", " + ss[7] + ", " + ss[8] + ", " + ss[9] + ", " + ss[10] + ", " + ss[11] + " , "
									+ ss[i];

							updater = ss[1] + " + " + ss[2] + " + " + ss[3] + " + " + ss[4] + " + " + ss[5] + " + "
									+ ss[6] + " + " + ss[7] + " + " + ss[8] + " + " + ss[9] + " + " + ss[10] + " + "
									+ ss[11] + " + " + ss[i];
							uint = i;
						} else if (i == 13) {
							subj = ss[1] + ", " + ss[2] + ", " + ss[3] + ", " + ss[4] + ", " + ss[5] + ", " + ss[6]
									+ ", " + ss[7] + ", " + ss[8] + ", " + ss[9] + ", " + ss[10] + ", " + ss[11] + " , "
									+ ss[12] + " , " + ss[i];
							updater = ss[1] + " + " + ss[2] + " + " + ss[3] + " + " + ss[4] + " + " + ss[5] + " + "
									+ ss[6] + " + " + ss[7] + " + " + ss[8] + " + " + ss[9] + " + " + ss[10] + " + "
									+ ss[11] + " + " + ss[12] + " + " + ss[i];
							uint = i;
						} else if (i == 14) {
							subj = ss[1] + ", " + ss[2] + ", " + ss[3] + ", " + ss[4] + ", " + ss[5] + ", " + ss[6]
									+ ", " + ss[7] + ", " + ss[8] + ", " + ss[9] + ", " + ss[10] + ", " + ss[11] + " , "
									+ ss[12] + " , " + ss[13] + ", " + ss[i];
							updater = ss[1] + " + " + ss[2] + " + " + ss[3] + " + " + ss[4] + " + " + ss[5] + " + "
									+ ss[6] + " + " + ss[7] + " + " + ss[8] + " + " + ss[9] + " + " + ss[10] + " + "
									+ ss[11] + " + " + ss[12] + " + " + ss[13] + " + " + ss[i];
							uint = i;
						} else if (i == 15) {
							subj = ss[1] + ", " + ss[2] + ", " + ss[3] + ", " + ss[4] + ", " + ss[5] + ", " + ss[6]
									+ ", " + ss[7] + ", " + ss[8] + ", " + ss[9] + ", " + ss[10] + ", " + ss[11] + " , "
									+ ss[12] + " , " + ss[13] + ", " + ss[14] + ", " + ss[i];
							updater = ss[1] + " + " + ss[2] + " + " + ss[3] + " + " + ss[4] + " + " + ss[5] + " + "
									+ ss[6] + " + " + ss[7] + " + " + ss[8] + " + " + ss[9] + " + " + ss[10] + " + "
									+ ss[11] + " + " + ss[12] + " + " + ss[13] + " + " + ss[14] + " + " + ss[i];
							uint = i;
						}
					}
				}

			} else {

				ResultSet rs1 = data.st.executeQuery("SELECT * FROM Psubj");
				String ss1[] = new String[5];
				while (rs1.next()) {
					int no = rs1.getRow();
					ss1[no - 1] = rs1.getString(1);
					subjects[no - 1] = ss1[no - 1];

				}
				for (int i = 0; i < Classname.length; i++)
					Classname[i] = null;
				for (int i = 0; i < ss1.length; i++) {

					Classname[i] = ss1[i];
//					System.out.println(Classname[i]);
					if (!(ss1[i] == null)) {
						if (i == 0) {
							subj = "Islamic , Arabic , Somali, English ,Mathematics ," + ss1[i];
							updater = "Islamic + Arabic + Somali + English + Mathematics + " + ss1[i];
							uint1 = 6;
						} else if (i == 1) {
							subj = "Islamic , Arabic , Somali, English ,Mathematics ," + ss1[0] + " , " + ss1[i];
							updater = "Islamic + Arabic + Somali + English + Mathematics + " + ss1[0] + " + " + ss1[i];
							uint1 = 7;
						} else if (i == 2) {
							subj = "Islamic , Arabic , Somali, English ,Mathematics ," + ss1[0] + " , " + ss1[1] + " , "
									+ ss1[i];
							updater = "Islamic + Arabic + Somali + English + Mathematics + " + ss1[0] + " + " + ss1[1]
									+ " + " + ss1[i];
							uint1 = 8;
						} else if (i == 3) {
							subj = "Islamic , Arabic , Somali, English ,Mathematics ," + ss1[0] + " , " + ss1[1] + " , "
									+ ss1[2] + " , " + ss1[i];
							updater = "Islamic + Arabic + Somali + English + Mathematics + " + ss1[0] + " + " + ss1[1]
									+ " + " + ss1[2] + " + " + ss1[i];
							uint1 = 9;
						} else if (i == 4) {
							subj = "Islamic , Arabic , Somali, English ,Mathematics ," + ss1[0] + " , " + ss1[1] + " , "
									+ ss1[2] + " , " + ss1[3] + " , " + ss1[i];
							updater = "Islamic + Arabic + Somali + English + Mathematics + " + ss1[0] + " + " + ss1[1]
									+ " + " + ss1[2] + " + " + ss1[3] + " + " + ss1[i];
							uint1 = 10;
						}
					}
				}

			}
			for (int i = 0; i < sub.length; i++) {
				sub[i].setVisible(false);
				sub[i].setText(null);
			}

			int no = 0;
			String ss1[] = { "Islamic", "Arabic", "Somali", "English", "Mathematics" };
			for (int i = 0; i < Classname.length; i++) {
				no = no + i;
				if (!(Classname[i] == null)) {
					if (s.substring(0, 4).equalsIgnoreCase("form")) {

						sub[i - 1].setText(Classname[i]);
						sub[i - 1].setVisible(true);

					}

					else if (s.substring(0, 5).equalsIgnoreCase("grade")) {
						for (int j = 0; j < 5; j++) {
							if (i == 0) {
								sub[j].setText(ss1[j]);
								sub[j].setVisible(true);
								no = j;

							}
						}

						sub[no + 1].setText(Classname[i]);
						sub[no + 1].setVisible(true);

					}

				}

			}

			admarks(subj, date, Exam);
			data.con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void admarks(String subj2, String date, String exam) {

		try {
			String url = "jdbc:sqlite:students.db";

			data.classForname();
			data.con = DriverManager.getConnection(url);
			data.st = data.con.createStatement();
			ResultSet rssss = data.st.executeQuery("Select * from records");
			if (rssss.next()) {

				String Dname = "records";
				clause = Dname;
				String Cname = "classname";
				ClassClause = Cname;
				exames = exam;
				dates = date;

				string = "SELECT  RowId ID, Student_name ," + subj + " FROM " + clause + " where " + ClassClause + "='" + classes
						+ "'AND Examination='" + exam + "' AND ExamDate='" + date + "' AND deleted = 0 ORDER by[Student_name]";
				ResultSet rs = data.st.executeQuery(string);
				if (rs.next()) {
					o = 0;
					string = "SELECT RowId ID, Student_name ," + subj + " FROM " + clause + " where " + ClassClause + "='"
							+ classes + "'AND Examination='" + exam + "' AND ExamDate='" + date
							+ "'AND deleted = 0 ORDER by[Student_name]";
					ResultSet rs1 = data.st.executeQuery(string);
					tb.setModel(DbUtils.resultSetToTableModel(rs1));

					image1 = ImageIO.read(getClass().getResource("/image/cag 48.png"));
					tick.setIcon(new ImageIcon(image1));
					rowId(tb);
				} else {
					o = 1;
					String Dname1 = "student";
					clause = Dname1;
					String Cname1 = "Student_class";
					ClassClause = Cname1;
					string = "SELECT RowId ID, Student_name ," + subj + " FROM " + clause + " where " + ClassClause + "='"
							+ classes + "' ORDER by[Student_name]";
					ResultSet rs1 = data.st.executeQuery(string);

					tb.setModel(DbUtils.resultSetToTableModel(rs1));
					image1 = ImageIO.read(getClass().getResource("/image/ma48.png"));
					tick.setIcon(new ImageIcon(image1));
					rowId(tb);
				}
			} else {
				String Dname = "student";
				clause = Dname;
				String Cname = "Student_class";
				ClassClause = Cname;
				string = "SELECT RowId ID, Student_name ," + subj + " FROM " + clause + " where " + ClassClause + "='" + classes
						+ "' ORDER by[Student_name]";
				ResultSet rs = data.st.executeQuery(string);

				tb.setModel(DbUtils.resultSetToTableModel(rs));
				image1 = ImageIO.read(getClass().getResource("/image/ma48.png"));
				tick.setIcon(new ImageIcon(image1));
				rowId(tb);

			}
			collw(tb);

			data.con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	private void rowId(JTable tb) {
		for (int row = 0; row <tb.getRowCount();row++ ) {
			tb.setValueAt(row+1,row,0);
			}
	}

	private void collw(JTable tb2) {
		TableColumn col = null;
		for (int i = 0; i < tb2.getColumnCount(); i++) {
			col = tb2.getColumnModel().getColumn(i);
			col.setPreferredWidth(100);
			if (i == 0) {
				col.setPreferredWidth(50);

			}
			if (i == 1) {
				col.setPreferredWidth(250);

			} else if (i == 5) {
				col.setPreferredWidth(130);
			}

		}
	}

	private void options() {

		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResource("/image/aw.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		back = new JButton(new ImageIcon(image));
		back.setBounds(5, 5, 32, 32);
		back.setBorder(BorderFactory.createSoftBevelBorder(10));
		back.setOpaque(false);
		pane.add(back);

		int bl[] = { 40, 60, 80, 100 };
		lb = new JLabel[3];
		for (int i = 0; i < lb.length; i++) {
			lb[i] = new JLabel("label " + (i + 1));
			lb[i].setBounds(10, bl[i], 300, 25);
			lb[i].setFont(new Font("Calibri", Font.PLAIN, 16));
			lb[i].setForeground(Color.white);
			if (i == 0)
				lb[i].setText("");
			pane.add(lb[i]);

		}

		tick = new JLabel();
		tick.setBounds(470, 50, 48, 48);
		pane.add(tick);

		saver = new JButton("SAVE THE RECORDS");
		saver.setBackground(new Color(59, 168, 245));
		saver.setBorder(BorderFactory.createSoftBevelBorder(10));
		saver.setBounds(340, 10, 300, 30);
		saver.setFont(new Font("ARIAL", Font.PLAIN, 18));
		saver.setForeground(Color.white);

		saver.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent a) {
				if (!(ename == null || ename.equalsIgnoreCase("Select the exam name"))
						&& !(yestudy == null || yestudy.equalsIgnoreCase("Select the Studing year"))) {
					int rows = tb.getRowCount();
					int col = tb.getColumnCount()-1;
					if (o == 0) {

						try {
							Databases d = new Databases();
							d.classForname();
							d.con = DriverManager.getConnection("jdbc:sqlite:class.db");
							d.st = d.con.createStatement();
							String query = "insert into edate values('" + yestudy + "')";
							d.st.executeUpdate(query);

							query = "insert into checker values('" + ename + " , " + yestudy + "')";
							d.st.executeUpdate(query);

							query = "insert into laster values('" + ename + "','" + yestudy + "')";
							d.st.executeUpdate(query);

							d.con.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					try {
						data.classForname();
						data.con = DriverManager.getConnection("jdbc:sqlite:students.db");
						data.st = data.con.createStatement();
						if (o == 0) {
							String inserter = null;
							if (classes.substring(0, 4).equalsIgnoreCase("form")) {
								for (int row = 0; row < rows; row++) {

									if (!(cols == null)) {
										if (col == 2) {
											inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
													+ cols + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes
													+ "','" + ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1)
													+ ")";
										}
										if (col == 3) {
											inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
													+ cols + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes
													+ "','" + ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1)
													+ "," + tb.getValueAt(row, 2+1) + ")";
										}
										if (col == 4) {
											inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
													+ cols + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes
													+ "','" + ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1)
													+ "," + tb.getValueAt(row, 2+1) + "," + tb.getValueAt(row, 3+1) + ")";
										}
										if (col == 5) {
											inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
													+ cols + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes
													+ "','" + ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1)
													+ "," + tb.getValueAt(row, 2+1) + "," + tb.getValueAt(row, 3+1) + ","
													+ tb.getValueAt(row, 4+1) + ")";
										}
										if (col == 6) {
											inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
													+ cols + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes
													+ "','" + ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1)
													+ "," + tb.getValueAt(row, 2+1) + "," + tb.getValueAt(row, 3+1) + ","
													+ tb.getValueAt(row, 4+1) + "," + tb.getValueAt(row, 5+1) + ")";
										}
										if (col == 7) {
											inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
													+ cols + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes
													+ "','" + ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1)
													+ "," + tb.getValueAt(row, 2+1) + "," + tb.getValueAt(row, 3+1) + ","
													+ tb.getValueAt(row, 4+1) + "," + tb.getValueAt(row, 5+1) + ","
													+ tb.getValueAt(row, 6+1) + ")";
										}
										if (col == 8) {
											inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
													+ cols + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes
													+ "','" + ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1)
													+ "," + tb.getValueAt(row, 2+1) + "," + tb.getValueAt(row, 3+1) + ","
													+ tb.getValueAt(row, 4+1) + "," + tb.getValueAt(row, 5+1) + ","
													+ tb.getValueAt(row, 6+1) + "," + tb.getValueAt(row, 7+1) + ")";
										}
										if (col == 8) {
											inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
													+ cols + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes
													+ "','" + ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1)
													+ "," + tb.getValueAt(row, 2+1) + "," + tb.getValueAt(row, 3+1) + ","
													+ tb.getValueAt(row, 4+1) + "," + tb.getValueAt(row, 5+1) + ","
													+ tb.getValueAt(row, 6+1) + "," + tb.getValueAt(row, 7+1) + ")";
										}
										if (col == 9) {
											inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
													+ cols + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes
													+ "','" + ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1)
													+ "," + tb.getValueAt(row, 2+1) + "," + tb.getValueAt(row, 3+1) + ","
													+ tb.getValueAt(row, 4+1) + "," + tb.getValueAt(row, 5+1) + ","
													+ tb.getValueAt(row, 6+1) + "," + tb.getValueAt(row, 7+1) + ","
													+ tb.getValueAt(row, 8+1) + ")";
										}
										if (col == 10) {
											inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
													+ cols + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes
													+ "','" + ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1)
													+ "," + tb.getValueAt(row, 2+1) + "," + tb.getValueAt(row, 3+1) + ","
													+ tb.getValueAt(row, 4+1) + "," + tb.getValueAt(row, 5+1) + ","
													+ tb.getValueAt(row, 6+1) + "," + tb.getValueAt(row, 7+1) + ","
													+ tb.getValueAt(row, 8+1) + "," + tb.getValueAt(row, 9+1) + ")";
										}
										if (col == 11) {
											inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
													+ cols + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes
													+ "','" + ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1)
													+ "," + tb.getValueAt(row, 2+1) + "," + tb.getValueAt(row, 3+1) + ","
													+ tb.getValueAt(row, 4+1) + "," + tb.getValueAt(row, 5+1) + ","
													+ tb.getValueAt(row, 6+1) + "," + tb.getValueAt(row, 7+1) + ","
													+ tb.getValueAt(row, 8+1) + "," + tb.getValueAt(row, 9+1) + ","
													+ tb.getValueAt(row, 10+1) + ")";
										}
										if (col == 12) {
											inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
													+ cols + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes
													+ "','" + ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1)
													+ "," + tb.getValueAt(row, 2+1) + "," + tb.getValueAt(row, 3+1) + ","
													+ tb.getValueAt(row, 4+1) + "," + tb.getValueAt(row, 5+1) + ","
													+ tb.getValueAt(row, 6+1) + "," + tb.getValueAt(row, 7+1) + ","
													+ tb.getValueAt(row, 8+1) + "," + tb.getValueAt(row, 9+1) + ","
													+ tb.getValueAt(row, 10+1) + "," + tb.getValueAt(row, 11+1) + ")";
										}
										if (col == 13) {
											inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
													+ cols + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes
													+ "','" + ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1)
													+ "," + tb.getValueAt(row, 2+1) + "," + tb.getValueAt(row, 3+1) + ","
													+ tb.getValueAt(row, 4+1) + "," + tb.getValueAt(row, 5+1) + ","
													+ tb.getValueAt(row, 6+1) + "," + tb.getValueAt(row, 7+1) + ","
													+ tb.getValueAt(row, 8+1) + "," + tb.getValueAt(row, 9+1) + ","
													+ tb.getValueAt(row, 10+1) + "," + tb.getValueAt(row, 11+1) + ","
													+ tb.getValueAt(row, 12+1) + ")";
										}
										if (col == 14) {
											inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
													+ cols + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes
													+ "','" + ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1)
													+ "," + tb.getValueAt(row, 2+1) + "," + tb.getValueAt(row, 3+1) + ","
													+ tb.getValueAt(row, 4+1) + "," + tb.getValueAt(row, 5+1) + ","
													+ tb.getValueAt(row, 6+1) + "," + tb.getValueAt(row, 7+1) + ","
													+ tb.getValueAt(row, 8+1) + "," + tb.getValueAt(row, 9+1) + ","
													+ tb.getValueAt(row, 10+1) + "," + tb.getValueAt(row, 11+1) + ","
													+ tb.getValueAt(row, 12+1) + "," + tb.getValueAt(row, 13+1) + ")";
										}
										if (col == 15) {
											inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
													+ cols + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes
													+ "','" + ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1)
													+ "," + tb.getValueAt(row, 2+1) + "," + tb.getValueAt(row, 3+1) + ","
													+ tb.getValueAt(row, 4+1) + "," + tb.getValueAt(row, 5+1) + ","
													+ tb.getValueAt(row, 6+1) + "," + tb.getValueAt(row, 7+1) + ","
													+ tb.getValueAt(row, 8+1) + "," + tb.getValueAt(row, 9+1) + ","
													+ tb.getValueAt(row, 10+1) + "," + tb.getValueAt(row, 11+1) + ","
													+ tb.getValueAt(row, 12+1) + "," + tb.getValueAt(row, 13+1) + ","
													+ tb.getValueAt(row, 14+1) + ")";
										}
										if (col == 16) {
											inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
													+ cols + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes
													+ "','" + ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1)
													+ "," + tb.getValueAt(row, 2+1) + "," + tb.getValueAt(row, 3+1) + ","
													+ tb.getValueAt(row, 4+1) + "," + tb.getValueAt(row, 5+1) + ","
													+ tb.getValueAt(row, 6+1) + "," + tb.getValueAt(row, 7+1) + ","
													+ tb.getValueAt(row, 8+1) + "," + tb.getValueAt(row, 9+1) + ","
													+ tb.getValueAt(row, 10+1) + "," + tb.getValueAt(row, 11+1) + ","
													+ tb.getValueAt(row, 12+1) + "," + tb.getValueAt(row, 13+1) + ","
													+ tb.getValueAt(row, 14+1) + "," + tb.getValueAt(row, 15+1) + ")";
										}
										if (col == 17) {
											inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
													+ cols + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes
													+ "','" + ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1)
													+ "," + tb.getValueAt(row, 2+1) + "," + tb.getValueAt(row, 3+1) + ","
													+ tb.getValueAt(row, 4+1) + "," + tb.getValueAt(row, 5+1) + ","
													+ tb.getValueAt(row, 6+1) + "," + tb.getValueAt(row, 7+1) + ","
													+ tb.getValueAt(row, 8+1) + "," + tb.getValueAt(row, 9+1) + ","
													+ tb.getValueAt(row, 10+1) + "," + tb.getValueAt(row, 11+1) + ","
													+ tb.getValueAt(row, 12+1) + "," + tb.getValueAt(row, 13+1) + ","
													+ tb.getValueAt(row, 14+1) + "," + tb.getValueAt(row, 15+1) + ","
													+ tb.getValueAt(row, 16+1) + ")";
										}
									} else {
										if (classes.substring(0, 4).equalsIgnoreCase("form")) {
											if (col == 11) {
												inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
														+ subj + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes
														+ "','" + ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1)
														+ "," + tb.getValueAt(row, 2+1) + "," + tb.getValueAt(row, 3+1)
														+ "," + tb.getValueAt(row, 4+1) + "," + tb.getValueAt(row, 5+1)
														+ "," + tb.getValueAt(row, 6+1) + "," + tb.getValueAt(row, 7+1)
														+ "," + tb.getValueAt(row, 8+1) + "," + tb.getValueAt(row, 9+1)
														+ "," + tb.getValueAt(row, 10+1) + ")";

											}
											if (col == 12) {
												inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
														+ subj + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes
														+ "','" + ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1)
														+ "," + tb.getValueAt(row, 2+1) + "," + tb.getValueAt(row, 3+1)
														+ "," + tb.getValueAt(row, 4+1) + "," + tb.getValueAt(row, 5+1)
														+ "," + tb.getValueAt(row, 6+1) + "," + tb.getValueAt(row, 7+1)
														+ "," + tb.getValueAt(row, 8+1) + "," + tb.getValueAt(row, 9+1)
														+ "," + tb.getValueAt(row, 10+1) + "," + tb.getValueAt(row, 11+1)
														+ ")";
											}

											if (col == 13) {
												inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
														+ subj + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes
														+ "','" + ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1)
														+ "," + tb.getValueAt(row, 2+1) + "," + tb.getValueAt(row, 3+1)
														+ "," + tb.getValueAt(row, 4+1) + "," + tb.getValueAt(row, 5+1)
														+ "," + tb.getValueAt(row, 6+1) + "," + tb.getValueAt(row, 7+1)
														+ "," + tb.getValueAt(row, 8+1) + "," + tb.getValueAt(row, 9+1)
														+ "," + tb.getValueAt(row, 10+1) + "," + tb.getValueAt(row, 11+1)
														+ "," + tb.getValueAt(row, 12+1) + ")";
											}
											if (col == 14) {
												inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
														+ subj + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes
														+ "','" + ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1)
														+ "," + tb.getValueAt(row, 2+1) + "," + tb.getValueAt(row, 3+1)
														+ "," + tb.getValueAt(row, 4+1) + "," + tb.getValueAt(row, 5+1)
														+ "," + tb.getValueAt(row, 6+1) + "," + tb.getValueAt(row, 7+1)
														+ "," + tb.getValueAt(row, 8+1) + "," + tb.getValueAt(row, 9+1)
														+ "," + tb.getValueAt(row, 10+1) + "," + tb.getValueAt(row, 11+1)
														+ "," + tb.getValueAt(row, 12+1) + "," + tb.getValueAt(row, 13+1)
														+ ")";
											}
											if (col == 15) {
												inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
														+ subj + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes
														+ "','" + ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1)
														+ "," + tb.getValueAt(row, 2+1) + "," + tb.getValueAt(row, 3+1)
														+ "," + tb.getValueAt(row, 4+1) + "," + tb.getValueAt(row, 5+1)
														+ "," + tb.getValueAt(row, 6+1) + "," + tb.getValueAt(row, 7+1)
														+ "," + tb.getValueAt(row, 8+1) + "," + tb.getValueAt(row, 9+1)
														+ "," + tb.getValueAt(row, 10+1) + "," + tb.getValueAt(row, 11+1)
														+ "," + tb.getValueAt(row, 12+1) + "," + tb.getValueAt(row, 13+1)
														+ "," + tb.getValueAt(row, 14+1) + ")";
											}
											if (col == 16) {
												inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
														+ subj + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes
														+ "','" + ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1)
														+ "," + tb.getValueAt(row, 2+1) + "," + tb.getValueAt(row, 3+1)
														+ "," + tb.getValueAt(row, 4+1) + "," + tb.getValueAt(row, 5+1)
														+ "," + tb.getValueAt(row, 6+1) + "," + tb.getValueAt(row, 7+1)
														+ "," + tb.getValueAt(row, 8+1) + "," + tb.getValueAt(row, 9+1)
														+ "," + tb.getValueAt(row, 10+1) + "," + tb.getValueAt(row, 11+1)
														+ "," + tb.getValueAt(row, 12+1) + "," + tb.getValueAt(row, 13+1)
														+ "," + tb.getValueAt(row, 14+1) + "," + tb.getValueAt(row, 15+1)
														+ ")";
											}
											if (col == 17) {
												inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
														+ subj + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes
														+ "','" + ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1)
														+ "," + tb.getValueAt(row, 2+1) + "," + tb.getValueAt(row, 3+1)
														+ "," + tb.getValueAt(row, 4+1) + "," + tb.getValueAt(row, 5+1)
														+ "," + tb.getValueAt(row, 6+1) + "," + tb.getValueAt(row, 7+1)
														+ "," + tb.getValueAt(row, 8+1) + "," + tb.getValueAt(row, 9+1)
														+ "," + tb.getValueAt(row, 10+1) + "," + tb.getValueAt(row, 11+1)
														+ "," + tb.getValueAt(row, 12+1) + "," + tb.getValueAt(row, 13+1)
														+ "," + tb.getValueAt(row, 14+1) + "," + tb.getValueAt(row, 15+1)
														+ "," + tb.getValueAt(row, 16+1) + ")";
											}

										}

									}

									data.st.executeUpdate(inserter);
									

								}
								try {
									Databases dd = new Databases();
									dd.classForname();
									dd.con = DriverManager.getConnection("jdbc:sqlite:class.db");
									dd.st = dd.con.createStatement();
									String query = "SELECT maximum from Exam where examname='" + ename + "'";
									ResultSet rrs = dd.st.executeQuery(query);
									rrs.next();
									int nn = rrs.getInt("maximum");
									if (nn == 0) {
										int op = Integer.parseInt(JOptionPane.showInputDialog(
												"Please the maximum marks of this Exam: " + ename + ""));
										dd.st.executeUpdate(
												"update Exam set maximum=" + op + " where examname='" + ename + "'");
										data.st.executeUpdate("update records set Total=(" + updater
												+ ")  where classname like 'form%'");

										data.st.executeUpdate("update records set Average=((Total /" + (uint * op)
												+ ")*100) where classname like 'form%'");

										ename = "Select the exam name";
										yestudy = "Select the Studing year";
										image1 = ImageIO.read(getClass().getResource("/image/cag 48.png"));
										tick.setIcon(new ImageIcon(image1));
									} else {
										data.st.executeUpdate("update records set Total=(" + updater
												+ ")  where classname like 'form%'");

										data.st.executeUpdate("update records set Average=((Total /" + (uint * nn)
												+ ")*100)  where Examination='" + ename + "' AND ExamDate='" + yestudy
												+ "' AND classname like 'form%'");

										ename = "Select the exam name";
										yestudy = "Select the Studing year";
										image1 = ImageIO.read(getClass().getResource("/image/cag 48.png"));
										tick.setIcon(new ImageIcon(image1));
									}
									dd.con.close();
								} catch (Exception e) {
									e.printStackTrace();
								}

							} else {

								for (int row = 0; row < rows; row++) {
									if (col == 2) {
										inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
												+ cols + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes + "','"
												+ ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1) + ")";
									}
									if (col == 3) {
										inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
												+ cols + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes + "','"
												+ ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1) + ","
												+ tb.getValueAt(row, 2+1) + ")";
									}
									if (col == 4) {
										inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
												+ cols + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes + "','"
												+ ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1) + ","
												+ tb.getValueAt(row, 2+1) + "," + tb.getValueAt(row, 3+1) + ")";
									}
									if (col == 5) {
										inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
												+ cols + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes + "','"
												+ ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1) + ","
												+ tb.getValueAt(row, 2+1) + "," + tb.getValueAt(row, 3+1) + ","
												+ tb.getValueAt(row, 4+1) + ")";
									}
									if (col == 6) {
										inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
												+ cols + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes + "','"
												+ ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1) + ","
												+ tb.getValueAt(row, 2+1) + "," + tb.getValueAt(row, 3+1) + ","
												+ tb.getValueAt(row, 4+1) + "," + tb.getValueAt(row, 5+1) + ")";
									}
									if (col == 7) {
										inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
												+ cols + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes + "','"
												+ ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1) + ","
												+ tb.getValueAt(row, 2+1) + "," + tb.getValueAt(row, 3+1) + ","
												+ tb.getValueAt(row, 4+1) + "," + tb.getValueAt(row, 5+1) + ","
												+ tb.getValueAt(row, 6+1) + ")";
									}
									if (col == 8) {
										inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
												+ subj + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes + "','"
												+ ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1) + ","
												+ tb.getValueAt(row, 2+1) + "," + tb.getValueAt(row, 3+1) + ","
												+ tb.getValueAt(row, 4+1) + "," + tb.getValueAt(row, 5+1) + ","
												+ tb.getValueAt(row, 6+1) + "," + tb.getValueAt(row, 7+1) + ")";
									}
									if (col == 9) {
										inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
												+ subj + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes + "','"
												+ ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1) + ","
												+ tb.getValueAt(row, 2+1) + "," + tb.getValueAt(row, 3+1) + ","
												+ tb.getValueAt(row, 4+1) + "," + tb.getValueAt(row, 5+1) + ","
												+ tb.getValueAt(row, 6+1) + "," + tb.getValueAt(row, 7+1) + ","
												+ tb.getValueAt(row, 8+1) + ")";
									}
									if (col == 10) {
										inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
												+ subj + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes + "','"
												+ ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1) + ","
												+ tb.getValueAt(row, 2+1) + "," + tb.getValueAt(row, 3+1) + ","
												+ tb.getValueAt(row, 4+1) + "," + tb.getValueAt(row, 5+1) + ","
												+ tb.getValueAt(row, 6+1) + "," + tb.getValueAt(row, 7+1) + ","
												+ tb.getValueAt(row, 8+1) + "," + tb.getValueAt(row, 9+1) + ")";
									}
									if (col == 11) {
										inserter = "insert into records (Student_name,classname,Examination,ExamDate,"
												+ subj + " ) values('" + tb.getValueAt(row, 0+1) + "','" + classes + "','"
												+ ename + "','" + yestudy + "'," + tb.getValueAt(row, 1+1) + ","
												+ tb.getValueAt(row, 2+1) + "," + tb.getValueAt(row, 3+1) + ","
												+ tb.getValueAt(row, 4+1) + "," + tb.getValueAt(row, 5+1) + ","
												+ tb.getValueAt(row, 6+1) + "," + tb.getValueAt(row, 7+1) + ","
												+ tb.getValueAt(row, 8+1) + "," + tb.getValueAt(row, 9+1) + ","
												+ tb.getValueAt(row, 10+1) + ")";
									}

									data.st.executeUpdate(inserter);

//								
								}
								try {
									Databases dd = new Databases();
									dd.classForname();
									dd.con = DriverManager.getConnection("jdbc:sqlite:class.db");
									dd.st = dd.con.createStatement();
									String query = "SELECT maximum from Exam where examname='" + ename + "'";
									ResultSet rrs = dd.st.executeQuery(query);
									rrs.next();
									int nn = rrs.getInt("maximum");
									if (nn == 0) {
										int op = Integer.parseInt(JOptionPane.showInputDialog(
												"Please the maximum marks of this Exam: " + ename + ""));
										dd.st.executeUpdate(
												"update Exam set maximum=" + op + " where examname='" + ename + "'");

										data.st.executeUpdate("update records set Total=(" + updater
												+ ") where classname like 'grade%'");

										data.st.executeUpdate("update records set Average=((Total /" + (uint1 * op)
												+ ")*100) where Examination='" + ename + "' AND ExamDate='" + yestudy
												+ "' AND classname like 'grade%'");

										ename = "Select the exam name";
										yestudy = "Select the Studing year";
										image1 = ImageIO.read(getClass().getResource("/image/cag 48.png"));
										tick.setIcon(new ImageIcon(image1));

									} else {

										data.st.executeUpdate("update records set Total=(" + updater
												+ ") where classname like 'grade%'");

										data.st.executeUpdate("update records set Average=((Total /" + (uint1 * nn)
												+ ")*100) where classname like 'grade%'");

										ename = "Select the exam name";
										yestudy = "Select the Studing year";
										image1 = ImageIO.read(getClass().getResource("/image/cag 48.png"));
										tick.setIcon(new ImageIcon(image1));

									}
									dd.con.close();
								} catch (Exception e) {
									e.printStackTrace();
									
								}
							}

						}
						// update the exam
						else {

							if (classes.substring(0, 4).equalsIgnoreCase("form")) {

								for (int col1 = 0; col1 < Classname.length; col1++) {

									if (!(Classname[col1] == null)) {
										for (int row = 0; row < tb.getRowCount(); row++) {
											String update = "update records set " + Classname[col1] + "="
													+ tb.getValueAt(row, col1) + " where Student_name='"
													+ tb.getValueAt(row, 0+1) + "'  AND Examination='" + ename
													+ "' AND ExamDate='" + yestudy + "'";
											data.st.executeUpdate(update);

										}
										image1 = ImageIO.read(getClass().getResource("/image/cag 48.png"));
										tick.setIcon(new ImageIcon(image1));

									}
								}
								JOptionPane.showMessageDialog(null, " the records updated", "Schmasys",
										JOptionPane.CANCEL_OPTION);
								try {
									Databases dd = new Databases();
									dd.classForname();
									dd.con = DriverManager.getConnection("jdbc:sqlite:class.db");
									dd.st = dd.con.createStatement();
									String query = "SELECT maximum from Exam where examname='" + ename + "'";
									ResultSet rrs = dd.st.executeQuery(query);
									rrs.next();
									int nn = rrs.getInt("maximum");
									if (nn == 0) {
										int op = Integer.parseInt(JOptionPane.showInputDialog(
												"Please the maximum marks of this Exam: " + ename + ""));
										dd.st.executeUpdate(
												"update Exam set maximum=" + op + " where examname='" + ename + "'");
										data.st.executeUpdate("update records set Total=(" + updater
												+ ")  where classname like 'form%'");

										data.st.executeUpdate("update records set Average=((Total /" + (uint * op)
												+ ")*100) where Examination='" + ename + "' AND ExamDate='" + yestudy
												+ "' AND classname like 'form%'");
										ename = "Select the exam name";
										yestudy = "Select the Studing year";
										image1 = ImageIO.read(getClass().getResource("/image/cag 48.png"));
										tick.setIcon(new ImageIcon(image1));
									} else {
										data.st.executeUpdate("update records set Total=(" + updater
												+ ")  where classname like 'form%'");

										data.st.executeUpdate("update records set Average=((Total /" + (uint * nn)
												+ ")*100) where Examination='" + ename + "' AND ExamDate='" + yestudy
												+ "' AND classname like 'form%'");
										ename = "Select the exam name";
										yestudy = "Select the Studing year";
										image1 = ImageIO.read(getClass().getResource("/image/cag 48.png"));
										tick.setIcon(new ImageIcon(image1));
									}
									dd.con.close();
								} catch (Exception e) {
									e.printStackTrace();
								}
							} else {
								String ss1[] = { "Islamic", "Arabic", "Somali", "English", "Mathematics" };
								String subjects[] = new String[10];
								int n = 0;
								for (int col1 = 0; col1 < Classname.length; col1++) {
									n = n + col1;
									if (!(Classname[col1] == null)) {
										for (int j = 0; j < 5; j++) {
											if (col1 == 0) {

												subjects[j] = ss1[j];
												n = j;
											}
											subjects[n + 1] = Classname[col1];
										}

									}

								}
								int cols = 1;
								for (int i = 0; i < subjects.length; i++) {
									if (!(subjects[i] == null)) {
										for (int row = 0; row < tb.getRowCount(); row++) {
											String update = null;

											update = "update records set " + subjects[i] + "="
													+ tb.getValueAt(row, cols) + " where Student_name='"
													+ tb.getValueAt(row, 0+1) + "' AND Examination='" + ename
													+ "' AND ExamDate='" + yestudy + "'";

											data.st.executeUpdate(update);

										}

										cols++;
									}

								}
								JOptionPane.showMessageDialog(null, " the records updated", "Schmasys",
										JOptionPane.CANCEL_OPTION);
								try {
									Databases dd = new Databases();
									dd.classForname();
									dd.con = DriverManager.getConnection("jdbc:sqlite:class.db");
									dd.st = dd.con.createStatement();
									String query = "SELECT maximum from Exam where examname='" + ename + "'";
									ResultSet rrs = dd.st.executeQuery(query);
									rrs.next();
									int nn = rrs.getInt("maximum");
									if (nn == 0) {
										int op = Integer.parseInt(JOptionPane.showInputDialog(
												"Please the maximum marks of this Exam: " + ename + ""));
										dd.st.executeUpdate(
												"update Exam set maximum=" + op + " where examname='" + ename + "'");

										data.st.executeUpdate("update records set Total=(" + updater
												+ ") where classname like 'grade%'");

										data.st.executeUpdate("update records set Average=((Total /" + (uint1 * op)
												+ ")*100) where Examination='" + ename + "' AND ExamDate='" + yestudy
												+ "' AND classname like 'grade%'");
										ename = "Select the exam name";
										yestudy = "Select the Studing year";
										image1 = ImageIO.read(getClass().getResource("/image/cag 48.png"));
										tick.setIcon(new ImageIcon(image1));

									} else {

										data.st.executeUpdate("update records set Total=(" + updater
												+ ") where classname like 'grade%'");

										data.st.executeUpdate("update records set Average=((Total /" + (uint1 * nn)
												+ ")*100) where Examination='" + ename + "' AND ExamDate='" + yestudy
												+ "' AND classname like 'grade%'");
										ename = "Select the exam name";
										yestudy = "Select the Studing year";
										image1 = ImageIO.read(getClass().getResource("/image/cag 48.png"));
										tick.setIcon(new ImageIcon(image1));

									}
									dd.con.close();
								} catch (Exception e) {
									e.printStackTrace();
								}

							}

						}

						data.con.close();
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {

					JOptionPane.showMessageDialog(null, "Please select exam name and exam date", "warring",
							JOptionPane.WARNING_MESSAGE);
					stye.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
					examname.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));

				}

			}

		});
		pane.add(saver);

		stye = new JComboBox<String>(year);
		stye.setBounds(730, 35, 300, 30);
		stye.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
		stye.setFont(new Font("Calibri", Font.PLAIN, 15));
		stye.setBackground(new Color(0, 27, 61));
		stye.setForeground(Color.white);

		stye.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				yestudy = stye.getSelectedItem().toString();
				if (yestudy.equals("Select the Studing year")) {
					lb[1].setText("Examination : " + null);
					lb[2].setText("Exam Date : " + null);
				}
			}

		});

		examname = new JComboBox<String>();
		examname.setBounds(730, 70, 300, 30);
		examname.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
		examname.setFont(new Font("Calibri", Font.PLAIN, 15));
		examname.setBackground(new Color(0, 27, 61));
		examname.setForeground(Color.white);
		examname.addItem("Select the exam name");
		examname.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				ename = examname.getSelectedItem().toString();
				if (!ename.equalsIgnoreCase("Select the exam name")
						&& !yestudy.equalsIgnoreCase("Select the Studing year")) {

					String check = null;
					String str = ename + " , " + yestudy;
					lb[1].setText("Examination : " + ename);
					lb[2].setText("Exam Date : " + yestudy);
					try {
						data.classForname();
						data.con = DriverManager.getConnection("jdbc:sqlite:class.db");
						data.st = data.con.createStatement();
						String q = "select * from checker ";
						ResultSet rs = data.st.executeQuery(q);
						while (rs.next()) {

							if ((ename + " , " + yestudy).equals(rs.getString(1))) {
								if (!yestudy.equalsIgnoreCase("Select the Studing year"))
									check = rs.getString(1);

							}
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						String url = "jdbc:sqlite:students.db";

						data.classForname();
						data.con = DriverManager.getConnection(url);
						data.st = data.con.createStatement();
//						System.out.println(str.equalsIgnoreCase(check));
						if (str.equalsIgnoreCase(check) || o == 0) {
							String Dname = "records";
							clause = Dname;
							String Cname = "classname";
							ClassClause = Cname;

							o = 1;
							string = "SELECT Rowid as ID,  Student_name ," + subj + " FROM " + clause + " where " + ClassClause
									+ "='" + classes + "'AND Examination='" + ename + "' AND ExamDate='" + yestudy
									+ "' ORDER by[Student_name]";
							ResultSet rs1 = data.st.executeQuery(string);
							if (rs1.next()) {
								ResultSet rs = data.st.executeQuery(string);

								tb.setModel(DbUtils.resultSetToTableModel(rs));

								image1 = ImageIO.read(getClass().getResource("/image/cag 48.png"));
								tick.setIcon(new ImageIcon(image1));
								rowId(tb);
							} else {

								String Dname1 = "student";
								clause = Dname1;
								String Cname1 = "Student_class";
								o = 0;
								ClassClause = Cname1;
								string = "SELECT  Rowid as ID, Student_name ," + subj + " FROM " + clause + " where " + ClassClause
										+ "='" + classes + "' ORDER by[Student_name]";
								ResultSet rs = data.st.executeQuery(string);

								tb.setModel(DbUtils.resultSetToTableModel(rs));

								image1 = ImageIO.read(getClass().getResource("/image/ma48.png"));
								tick.setIcon(new ImageIcon(image1));
								rowId(tb);

							}

						} else {

							String Dname = "student";
							clause = Dname;
							String Cname = "Student_class";
							o = 0;
							ClassClause = Cname;
							string = "SELECT Rowid as ID, Student_name ," + subj + " FROM " + clause + " where " + ClassClause
									+ "='" + classes + "' ORDER by[Student_name]";
							ResultSet rs = data.st.executeQuery(string);

							tb.setModel(DbUtils.resultSetToTableModel(rs));

							image1 = ImageIO.read(getClass().getResource("/image/ma48.png"));
							tick.setIcon(new ImageIcon(image1));
							rowId(tb);

						}
						collw(tb);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Select the exam name and year of study", "schmasys Error",
							JOptionPane.ERROR_MESSAGE);
					examname.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
					stye.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));

					lb[1].setText("Examination : " + null);
				}

			}

		});

		try {
			data.classForname();
			data.con = DriverManager.getConnection("jdbc:sqlite:class.db");
			data.st = data.con.createStatement();

			String query = "select * from Exam";
			ResultSet rs = data.st.executeQuery(query);
			while (rs.next()) {
				String str = rs.getString(1);
				examname.addItem(str);
			}

			data.con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		cust();

		cus = new JButton("costum");
		cus.setBounds(730, 5, 300, 25);
		cus.setBackground(new Color(59, 168, 245));
		cus.setForeground(Color.white);
		cus.setFont(new Font("Calibri", Font.PLAIN, 16));
		cus.setBorder(BorderFactory.createSoftBevelBorder(10));
		cus.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				cust.setVisible(true);
				cus.setVisible(false);
				examname.setVisible(false);
				stye.setVisible(false);
			}
		});
		pane.add(cus);
		pane.add(examname);
		pane.add(stye);

	}

	private void cust() {
		JPanel pane = new JPanel();
		pane.setPreferredSize(new Dimension(0, 495));
		pane.setLayout(null);
		pane.setBackground(Color.white);

		custOptions();
		adsubject();

		pane.add(cont);
		pane.add(ads);

		cust = new JScrollPane(pane);
		cust.setBounds(690, 5, 350, 500);
		cust.setVisible(false);
		cust.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));

	}

	private void adsubject() {
		ads = new JPanel();
		ads.setBounds(10, 330, 330, 200);
		ads.setLayout(null);
		ads.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.lightGray),
				"Add new Subject"));
		ads.setOpaque(false);

		addSub = new JTextField();
		addSub.setBounds(20, 30, 280, 40);
		addSub.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
		addSub.setText("Enter new subject name");
		addSub.setForeground(Color.LIGHT_GRAY);
		addSub.setFont(new Font("Calibri", Font.PLAIN, 18));
		addSub.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent arg0) {

				if (addSub.getText().equals("Enter new subject name")) {
					addSub.setText("");
					addSub.setForeground(Color.black);
				}

			}

			public void focusLost(FocusEvent arg0) {
				if (addSub.getText().equals("Enter new subject name") || addSub.getText().length() == 0) {
					addSub.setText("Enter new subject name");
					addSub.setForeground(Color.LIGHT_GRAY);
				}

			}

		});
		ads.add(addSub);

		save = new JButton("SAVE");
		save.setBorder(BorderFactory.createSoftBevelBorder(10));
		save.setBackground(new Color(0, 27, 61));
		save.setBounds(20, 80, 280, 40);
		save.setFont(new Font("Calibri", Font.PLAIN, 20));
		save.setForeground(Color.white);
		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (!(addSub.getText().equalsIgnoreCase("Enter new subject name") || addSub.getText().length() == 0)) {
					String st = null;
					st = addSub.getText().substring(0, addSub.getText().indexOf(" "));
					Databases db = new Databases();
					try {
						db.classForname();
						db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
						db.st = db.con.createStatement();
						String query = "insert into allSub values('" + st + "')";

						if (classes.substring(0, 4).equalsIgnoreCase("form")) {

							String sec = "insert into subjects values('" + st + "')";
							db.st.executeUpdate(sec);
							db.st.executeUpdate(query);

						} else if (classes.substring(0, 5).equalsIgnoreCase("grade")) {
							String sec = "insert into Psubj values('" + st + "')";
							db.st.executeUpdate(sec);
							db.st.executeUpdate(query);

						}
//				
//				
						db.con.close();
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						db.classForname();
						db.con = DriverManager.getConnection("jdbc:sqlite:students.db");
						db.st = db.con.createStatement();
						String query = "alter table student add " + st + " double not null default 0.0";
						db.st.executeUpdate(query);

						String query1 = "alter table records add " + st + " double not null default 0.0";
						db.st.executeUpdate(query1);

						db.con.close();
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(null, "please add subject ", "schmasys error",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		});

		close = new JButton("CLOSE");
		close.setBorder(BorderFactory.createSoftBevelBorder(10));
		close.setBackground(new Color(116, 185, 247));
		close.setBounds(90, 130, 150, 20);
		close.setFont(new Font("Calibri", Font.PLAIN, 15));
		close.setForeground(Color.white);
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				cust.setVisible(false);
				cus.setVisible(true);
				examname.setVisible(true);
				stye.setVisible(true);

			}

		});

		ads.add(close);
		ads.add(save);

	}

	private void custOptions() {
		cont = new JPanel();
		cont.setBounds(10, 20, 330, 300);
		cont.setLayout(null);
		cont.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.lightGray),
				"Custom subjects to edite"));
		cont.setOpaque(false);

		int[] bl = { 20, 50, 80, 110, 140, 170, 200, 20, 50, 80, 110, 140, 170, 200 };
		sub = new JCheckBox[14];
		for (int i = 0; i < sub.length; i++) {
			sub[i] = new JCheckBox();
			sub[i].setFont(new Font("Calibri", Font.PLAIN, 16));
			sub[i].setForeground(Color.BLUE);
			sub[i].setBounds(20, bl[i], 150, 30);
			sub[i].setBorder(BorderFactory.createSoftBevelBorder(10));
			sub[i].setOpaque(false);
			sub[i].setVisible(false);
			sub[i].addItemListener(item);
			if (i > 6) {
				sub[i].setBounds(180, bl[i], 150, 30);

			}

			cont.add(sub[i]);

		}
		apply = new JButton("Apply");
		apply.setBounds(20, 240, 280, 40);
		apply.setBackground(new Color(0, 27, 61));
		apply.setBorder(BorderFactory.createSoftBevelBorder(10));
		apply.setFont(new Font("Calibri", Font.PLAIN, 20));
		apply.setForeground(Color.white);
		apply.addActionListener(action);

		cont.add(apply);

	}

	private void panels() {

		pane = new JPanel();
		pane.setBounds(0, 0, 1045, 110);
		pane.setBackground(new Color(0, 27, 61));
		pane.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.LIGHT_GRAY));
		pane.setLayout(null);
	}

	private void container() {
		tb = new JTable();
		tb.setPreferredScrollableViewportSize(new Dimension(0, 0));
		tb.setFont(new Font("ARIAL", Font.PLAIN, 15));
		tb.getTableHeader().setBackground(new Color(34, 166, 242));
		tb.getTableHeader().setForeground(Color.white);
		tb.getTableHeader().setFont(new Font("Calibri", Font.PLAIN, 15));
		tb.getTableHeader().removeAll();
		tb.setBorder(BorderFactory.createSoftBevelBorder(10));
		tb.setSurrendersFocusOnKeystroke(false);
		tb.setRowHeight(20);
		tb.setSelectionForeground(Color.black);
		tb.setFont(new Font("Calibri", Font.PLAIN, 15));
		tb.setSelectionBackground(new Color(97, 146, 194, 100));

		spane = new JScrollPane(tb);
		spane.setBounds(0, 110, 1045, 690);
		spane.setBorder(BorderFactory.createSoftBevelBorder(10));

	}

	private void design() {
		add(cust);
		add(spane);
		add(pane);
		setVisible(false);
		setLayout(null);
		setBackground(Color.white);
		setBounds(0, 0, 1045, 690);
	}

	private class actionListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == apply) {

				String ss = null;
				for (int i = 0; i < csub.length; i++) {

					if (!(csub[i] == null)) {
						switch (i) {
						case 0:
							ss = csub[i];
							break;
						case 1:
							ss = csub[0] + " ," + csub[i];
							break;
						case 2:
							ss = csub[0] + " ," + csub[1] + " ," + csub[i];
							break;
						case 3:
							ss = csub[0] + " ," + csub[1] + " ," + csub[2] + " ," + csub[i];
							break;
						case 4:
							ss = csub[0] + " ," + csub[1] + " ," + csub[2] + " ," + csub[3] + " ," + csub[i];
							break;
						case 5:
							ss = csub[0] + " ," + csub[1] + " ," + csub[2] + " ," + csub[3] + " ," + csub[4] + " ,"
									+ csub[i];
							break;
						case 6:
							ss = csub[0] + " ," + csub[1] + " ," + csub[2] + " ," + csub[3] + " ," + csub[4] + " ,"
									+ csub[5] + " ," + csub[i];
							break;
						case 7:
							ss = csub[0] + " ," + csub[1] + " ," + csub[2] + " ," + csub[3] + " ," + csub[4] + " ,"
									+ csub[5] + " ," + csub[6] + " ," + csub[i];
							break;
						case 8:
							ss = csub[0] + " ," + csub[1] + " ," + csub[2] + " ," + csub[3] + " ," + csub[4] + " ,"
									+ csub[5] + " ," + csub[6] + " ," + csub[7] + " ," + csub[i];
							break;
						case 9:
							ss = csub[0] + " ," + csub[1] + " ," + csub[2] + " ," + csub[3] + " ," + csub[4] + " ,"
									+ csub[5] + " ," + csub[6] + " ," + csub[7] + " ," + csub[8] + " ," + csub[i];
							break;
						case 10:
							ss = csub[0] + " ," + csub[1] + " ," + csub[2] + " ," + csub[3] + " ," + csub[4] + " ,"
									+ csub[5] + " ," + csub[6] + " ," + csub[7] + " ," + csub[8] + " ," + csub[9] + " ,"
									+ csub[i];
							break;
						case 11:
							ss = csub[0] + " ," + csub[1] + " ," + csub[2] + " ," + csub[3] + " ," + csub[4] + " ,"
									+ csub[5] + " ," + csub[6] + " ," + csub[7] + " ," + csub[8] + " ," + csub[9] + " ,"
									+ csub[10] + " ," + csub[i];
							break;
						case 12:
							ss = csub[0] + " ," + csub[1] + " ," + csub[2] + " ," + csub[3] + " ," + csub[4] + " ,"
									+ csub[5] + " ," + csub[6] + " ," + csub[7] + " ," + csub[8] + " ," + csub[9] + " ,"
									+ csub[10] + " ," + csub[11] + " ," + csub[i];
							break;
						case 13:
							ss = csub[0] + " ," + csub[1] + " ," + csub[2] + " ," + csub[3] + " ," + csub[4] + " ,"
									+ csub[5] + " ," + csub[6] + " ," + csub[7] + " ," + csub[8] + " ," + csub[9] + " ,"
									+ csub[10] + " ," + csub[11] + " ," + csub[12] + " ," + csub[i];
							break;
						case 14:
							ss = csub[0] + " ," + csub[1] + " ," + csub[2] + " ," + csub[3] + " ," + csub[4] + " ,"
									+ csub[5] + " ," + csub[6] + " ," + csub[7] + " ," + csub[8] + " ," + csub[9] + " ,"
									+ csub[10] + " ," + csub[11] + " ," + csub[12] + " ," + csub[13] + " ," + csub[i];
							break;
						case 15:
							ss = csub[0] + " ," + csub[1] + " ," + csub[2] + " ," + csub[3] + " ," + csub[4] + " ,"
									+ csub[5] + " ," + csub[6] + " ," + csub[7] + " ," + csub[8] + " ," + csub[9] + " ,"
									+ csub[10] + " ," + csub[11] + " ," + csub[12] + " ," + csub[13] + " ," + csub[14]
									+ " ," + csub[i];
							break;
						default:
							ss = csub[0] + " ," + csub[1] + " ," + csub[2] + " ," + csub[3] + " ," + csub[4] + " ,"
									+ csub[5] + " ," + csub[6] + " ," + csub[7] + " ," + csub[8] + " ," + csub[9] + " ,"
									+ csub[10] + " ," + csub[11] + " ," + csub[12] + " ," + csub[13] + " ," + csub[14]
									+ " ," + csub[15] + " ," + csub[16];

						}
					}
				}

				cols = ss;
				if (!(ss == null)) {
					try {
						String url = "jdbc:sqlite:students.db";

						data.classForname();
						data.con = DriverManager.getConnection(url);
						data.st = data.con.createStatement();

						string = "SELECT  Student_name ," + ss + " FROM " + clause + " where " + ClassClause + "='"
								+ classes + "' order by Student_name";

						ResultSet rs = data.st.executeQuery(string);

						tb.setModel(DbUtils.resultSetToTableModel(rs));
					rowId(tb);
						collw(tb);
						cust.setVisible(false);
						cus.setVisible(true);
						examname.setVisible(true);
						stye.setVisible(true);
						data.con.close();

					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						String url = "jdbc:sqlite:students.db";

						data.classForname();
						data.con = DriverManager.getConnection(url);
						data.st = data.con.createStatement();

						if (ClassClause.equalsIgnoreCase("Student_Class")) {

							string = "select Student_name , " + subj + " from student where Student_class='" + classes
									+ "' order by Student_name ";
							ResultSet rs = data.st.executeQuery(string);

							tb.setModel(DbUtils.resultSetToTableModel(rs));
							rowId(tb);
						} else {

							string = "SELECT  Student_name ," + subj + " FROM records where " + ClassClause + "='"
									+ classes + "'AND Examination='" + exames + "' AND ExamDate='" + dates
									+ "'AND deleted = 0 ORDER by Student_name";

							ResultSet rs = data.st.executeQuery(string);

							tb.setModel(DbUtils.resultSetToTableModel(rs));
							rowId(tb);
						}

						collw(tb);
						cust.setVisible(false);
						cus.setVisible(true);
						examname.setVisible(true);
						stye.setVisible(true);
						data.con.close();

					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}

		}

	}

	private class itemlistener implements ItemListener {

		public void itemStateChanged(ItemEvent arg0) {
			csub = new String[sub.length];
			for (int i = 0; i < csub.length; i++) {
				if (sub[i].isSelected()) {

					csub[i] = sub[i].getText();
				}
				if (!(csub[i] == null)) {
				}
			}

		}

	}

}
