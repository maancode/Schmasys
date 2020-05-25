package schoolManegementSystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class GraduatedPrintMarks extends JPanel {


	private static final long serialVersionUID = 1L;
	private JPanel Container;
	private JPanel TitlePanel;
	private JPanel header;
	private JLabel shname;
	public JLabel logo;
	private JLabel SI, SN, SM, SC, ST, SE;
	private JPanel seprater;
	private JPanel marks;
	private JSeparator sep;
	private JLabel mar, Ex, Exd, sub, ma, va, av;
	private JLabel lb[], lb1[], lb2[], lb3[];
	private JSeparator sem[];
	private String name, exam, date, classe;
	private String subj;
	private String ss1[] = new String[16];
	private String ss2[] = new String[5];
	private String pri[] = new String[10];
	private JLabel total,average,grade;
	private String finelConter;
	
	int is = 0;
	int subc;
	Databases data = new Databases();

	public GraduatedPrintMarks() {
		container();
		panels();
		profile();
		titles();
		marks();
		center();
		design();
	}
	public void getPeramiters(String name1, String Exam, String Date, String Class,int from) {

		name = name1;
		exam = Exam;
		date = Date;
		classe = Class;

		Ex.setText("Examination name : " + exam);
		Exd.setText("Examination date   : " + date);
		if (classe.substring(0, 4).equalsIgnoreCase("form")) {
			for (int i = 0; i < lb.length; i++) {
				lb[i].setText(null);
				lb[i].setVisible(true);
				lb1[i].setVisible(false);
				lb2[i].setVisible(false);
				lb3[i].setVisible(false);
				sem[i].setVisible(false);
			}

			try {
				data.classForname();
				data.con = DriverManager.getConnection("jdbc:sqlite:class.db");
				data.st = data.con.createStatement();
				ResultSet rs = data.st.executeQuery("select * from subjects");
				while (rs.next()) {
					ss1[rs.getRow()] = rs.getString("subject");
				}
				data.con.close();

			} catch (Exception e) {
				System.out.println(e);
			}

			for (int i = 0; i < ss1.length; i++) {
				if (!(ss1[i] == null)) {
					if (i == 10) {
						subj = ss1[1] + ", " + ss1[2] + ", " + ss1[3] + ", " + ss1[4] + ", " + ss1[5] + ", " + ss1[6]
								+ ", " + ss1[7] + ", " + ss1[8] + ", " + ss1[9] + ", " + ss1[i];
						finelConter = "sum(" + ss1[1] + "), sum(" + ss1[2] + "), sum(" + ss1[3] + "), sum(" + ss1[4]
								+ "), sum(" + ss1[5] + "), sum(" + ss1[6] + "), sum(" + ss1[7] + "),sum(" + ss1[8]
								+ "), sum(" + ss1[9] + "),sum(" + ss1[i] + ")";
						subc = i;
					} else if (i == 11) {
						subj = ss1[1] + ", " + ss1[2] + ", " + ss1[3] + ", " + ss1[4] + ", " + ss1[5] + ", " + ss1[6]
								+ ", " + ss1[7] + ", " + ss1[8] + ", " + ss1[9] + ", " + ss1[10] + ", " + ss1[i];
						subc = i;

						finelConter = "sum(" + ss1[1] + "), sum(" + ss1[2] + "), sum(" + ss1[3] + "), sum(" + ss1[4]
								+ "), sum(" + ss1[5] + "), sum(" + ss1[6] + "), sum(" + ss1[7] + "),sum(" + ss1[8]
								+ "), sum(" + ss1[9] + "),sum(" + ss1[10] + "),sum(" + ss1[i] + ")";

					} else if (i == 12) {
						subj = ss1[1] + ", " + ss1[2] + ", " + ss1[3] + ", " + ss1[4] + ", " + ss1[5] + ", " + ss1[6]
								+ ", " + ss1[7] + ", " + ss1[8] + ", " + ss1[9] + ", " + ss1[10] + ", " + ss1[11]
								+ " , " + ss1[i];
						subc = i;
						finelConter = "sum(" + ss1[1] + "), sum(" + ss1[2] + "), sum(" + ss1[3] + "), sum(" + ss1[4]
								+ "), sum(" + ss1[5] + "), sum(" + ss1[6] + "), sum(" + ss1[7] + "),sum(" + ss1[8]
								+ "), sum(" + ss1[9] + "),sum(" + ss1[10] + "),sum(" + ss1[11] + "),sum(" + ss1[i]
								+ ")";
					} else if (i == 13) {
						subj = ss1[1] + ", " + ss1[2] + ", " + ss1[3] + ", " + ss1[4] + ", " + ss1[5] + ", " + ss1[6]
								+ ", " + ss1[7] + ", " + ss1[8] + ", " + ss1[9] + ", " + ss1[10] + ", " + ss1[11]
								+ " , " + ss1[12] + " , " + ss1[i];
						subc = i;
						finelConter = "sum(" + ss1[1] + "), sum(" + ss1[2] + "), sum(" + ss1[3] + "), sum(" + ss1[4]
								+ "), sum(" + ss1[5] + "), sum(" + ss1[6] + "), sum(" + ss1[7] + "),sum(" + ss1[8]
								+ "), sum(" + ss1[9] + "),sum(" + ss1[10] + "),sum(" + ss1[11] + "),sum(" + ss1[12]
								+ "),sum(" + ss1[i] + ")";
					} else if (i == 14) {
						subj = ss1[1] + ", " + ss1[2] + ", " + ss1[3] + ", " + ss1[4] + ", " + ss1[5] + ", " + ss1[6]
								+ ", " + ss1[7] + ", " + ss1[8] + ", " + ss1[9] + ", " + ss1[10] + ", " + ss1[11]
								+ " , " + ss1[12] + " , " + ss1[13] + ", " + ss1[i];
						subc = i;
						finelConter = "sum(" + ss1[1] + "), sum(" + ss1[2] + "), sum(" + ss1[3] + "), sum(" + ss1[4]
								+ "), sum(" + ss1[5] + "), sum(" + ss1[6] + "), sum(" + ss1[7] + "),sum(" + ss1[8]
								+ "), sum(" + ss1[9] + "),sum(" + ss1[10] + "),sum(" + ss1[11] + "),sum(" + ss1[12]
								+ "),sum(" + ss1[13] + "),sum(" + ss1[i] + ")";
					} else if (i == 15) {
						subj = ss1[1] + ", " + ss1[2] + ", " + ss1[3] + ", " + ss1[4] + ", " + ss1[5] + ", " + ss1[6]
								+ ", " + ss1[7] + ", " + ss1[8] + ", " + ss1[9] + ", " + ss1[10] + ", " + ss1[11]
								+ " , " + ss1[12] + " , " + ss1[13] + ", " + ss1[14] + ", " + ss1[i];
						subc = i;

						finelConter = "sum(" + ss1[1] + "), sum(" + ss1[2] + "), sum(" + ss1[3] + "), sum(" + ss1[4]
								+ "), sum(" + ss1[5] + "), sum(" + ss1[6] + "), sum(" + ss1[7] + "),sum(" + ss1[8]
								+ "), sum(" + ss1[9] + "),sum(" + ss1[10] + "),sum(" + ss1[11] + "),sum(" + ss1[12]
								+ "),sum(" + ss1[13] + "),sum(" + ss1[14] + "),sum(" + ss1[i] + ")";
					}
				}

			}
			for (int s = 0; s < ss1.length; s++) {
				if (!(ss1[s] == null)) {
					lb1[s - 1].setVisible(true);
					lb[s - 1].setText(ss1[s].toUpperCase());
					lb2[s - 1].setVisible(true);
					lb3[s - 1].setVisible(true);
					sem[s - 1].setVisible(true);
				}
			}
		}
		String ed[] = { "Islamic", "Arabic", "Somali", "English", "Mathematics" };

		int no = 0;

		if (classe.substring(0, 5).equalsIgnoreCase("grade")) {

			for (int i = 0; i < lb.length; i++) {
				lb[i].setText(null);
				lb[i].setVisible(false);
				lb1[i].setVisible(false);
				lb2[i].setVisible(false);
				lb3[i].setVisible(false);
				sem[i].setVisible(false);
			}
			try {
				data.classForname();
				data.con = DriverManager.getConnection("jdbc:sqlite:class.db");
				data.st = data.con.createStatement();
				ResultSet rs = data.st.executeQuery("select * from Psubj");
				while (rs.next()) {

					ss2[rs.getRow()] = rs.getString(1);
				}
				data.con.close();

			} catch (Exception e) {
				System.out.println(e);
			}

			for (int i = 0; i < ss2.length; i++) {
				if (!(ss2[i] == null)) {
					if (i == 1) {
						subj = "Islamic , Arabic , Somali, English ,Mathematics ," + ss2[i];
						is = i;
						finelConter = "sum(Islamic),sum(Arabic) , sum(Somali), sum(English) ,sum(Mathematics),sum("
								+ ss2[i] + ")";
					} else if (i == 2) {
						subj = "Islamic , Arabic , Somali, English ,Mathematics ," + ss2[1] + " , " + ss2[i];
						is = i;
						finelConter = "sum(Islamic),sum(Arabic) , sum(Somali), sum(English) ,sum(Mathematics),sum("
								+ ss2[1] + "),sum(" + ss2[i] + ")";
					} else if (i == 3) {
						subj = "Islamic , Arabic , Somali, English ,Mathematics ," + ss2[1] + " , " + ss2[2] + " , "
								+ ss2[i];
						is = i;
						finelConter = "sum(Islamic),sum(Arabic) , sum(Somali), sum(English) ,sum(Mathematics),sum("
								+ ss2[1] + "),sum(" + ss2[2] + "),sum(" + ss2[i] + ")";

					} else if (i == 4) {
						subj = "Islamic , Arabic , Somali, English ,Mathematics ," + ss2[1] + " , " + ss2[2] + " , "
								+ ss2[4] + " , " + ss2[i];
						is = i;
						finelConter = "sum(Islamic),sum(Arabic) , sum(Somali), sum(English) ,sum(Mathematics),sum("
								+ ss2[1] + "),sum(" + ss2[2] + "),sum(" + ss2[3] + "),sum(" + ss2[i] + ")";
					} else if (i == 5) {
						subj = "Islamic , Arabic , Somali, English ,Mathematics ," + ss2[1] + " , " + ss2[2] + " , "
								+ ss2[3] + " , " + ss2[4] + " , " + ss2[i];
						is = i;
						finelConter = "sum(Islamic),sum(Arabic) , sum(Somali), sum(English) ,sum(Mathematics),sum("
								+ ss2[1] + "),sum(" + ss2[2] + "),sum(" + ss2[3] + "),sum(" + ss2[4] + "),sum(" + ss2[i]
								+ ")";
					}
				}
				no = i;
				for (int j = 0; j < 5; j++) {
					pri[j] = ed[j];
					no = no + 1;

				}
				pri[no - 1] = ss2[i];

			}

			for (int i = 0; i < pri.length; i++) {
				if (!(pri[i] == null)) {
					subc = i + 1;
					lb[i].setText(pri[i].toUpperCase());
					lb[i].setVisible(true);
					sem[i].setVisible(true);
					lb1[i].setVisible(true);
					lb2[i].setVisible(true);
					lb3[i].setVisible(true);
				}
			}
		}
		if(from == 0) {
		markss(name, exam, date, classe, subj);
		}else {
			marks(name, date, classe);
		}
		student(name);
	}

	private void marks(String name2, String date2, String classe2) {
		try {
			String url = "jdbc:sqlite:students.db";
			data.classForname();
			data.con = DriverManager.getConnection(url);
			data.st = data.con.createStatement();
			String query = "select sum(Total),sum(Average)," + finelConter + " from records where ExamDate='"
					+ date + "' AND classname='" + classe + "' AND Student_name='" + name + "'";

			ResultSet rs = data.st.executeQuery(query);

			int nn = 100;

			DecimalFormat df2 = new DecimalFormat("#.##");

			rs.next();

			if (classe2.substring(0, 4).equalsIgnoreCase("form")) {
				
				total.setText("Total : " + rs.getString("sum(Total)") + " / " + (nn * subc));
				float avg = (rs.getFloat("sum(Total)")/(nn*subc))*100;
				average.setText("AVERAGE : " +df2.format(avg) + " %");
				
				grade.setForeground(new Color(102, 102, 102));
				if (rs.getFloat("sum(Average)") >= 95.00f && rs.getFloat("sum(Average)") <= 100.00f) {
					grade.setText("( A+ )");
					grade.setFont(new Font("Roman", Font.BOLD, 15));
				} else if (rs.getFloat("sum(Average)") >= 90.00f && rs.getFloat("sum(Average)") < 95.00f) {
					grade.setText("( A )");
					grade.setFont(new Font("Roman", Font.BOLD, 15));
				} else if (rs.getFloat("sum(Average)") >= 85.00f && rs.getFloat("sum(Average)") < 90.00f) {
					grade.setText("( B+ )");
					grade.setFont(new Font("Roman", Font.BOLD, 15));
				} else if (rs.getFloat("sum(Average)") >= 80.00f && rs.getFloat("sum(Average)") < 85.00f) {
					grade.setText("( B )");
					grade.setFont(new Font("Roman", Font.BOLD, 15));
				} else if (rs.getFloat("sum(Average)") >= 75.00f && rs.getFloat("sum(Average)") < 80.00f) {
					grade.setText("( C+ )");
					grade.setFont(new Font("Roman", Font.BOLD, 15));
				} else if (rs.getFloat("sum(Average)") >= 70.00f && rs.getFloat("sum(Average)") < 75.00f) {
					grade.setText("( C )");
					grade.setFont(new Font("Roman", Font.BOLD, 15));
				} else if (rs.getFloat("sum(Average)") >= 65.00f && rs.getFloat("sum(Average)") < 70.00f) {
					grade.setText("( D+ )");
					grade.setFont(new Font("Roman", Font.BOLD, 15));
				} else if (rs.getFloat("sum(Average)") >= 50.00f && rs.getFloat("sum(Average)") < 65.00f) {
					grade.setText("( D )");
					grade.setFont(new Font("Roman", Font.BOLD, 15));
				} else if (rs.getFloat("sum(Average)") < 50.00f) {
					grade.setText("( F )");
					grade.setFont(new Font("Roman", Font.BOLD, 15));
					grade.setForeground(Color.RED);
				}
				for (int i = 0; i < ss1.length; i++) {
					if (!(ss1[i] == null)) {
						lb1[i-1].setText(rs.getString("sum(" + ss1[i] + ")"));
						float avg1 =((rs.getFloat("sum("+ss1[i]+")") / (nn * subc)) * 100);
						lb2[i-1].setText("" +df2.format(avg1)  + " %");
						
						
						float value = (nn / 2);
						float mark = rs.getFloat("sum(" + ss1[i] + ")");
						if (value > mark) {
							lb3[i - 1].setText("FAILED");
							lb3[i - 1].setFont(new Font("Roman", Font.BOLD, 15));
//							lb3[i-1].setForeground(Color.red);
						} else if (value == mark) {
							lb3[i - 1].setText("POOR");
							lb3[i - 1].setFont(new Font("Roman", Font.PLAIN, 15));
//							lb3[i-1].setForeground(Color.pink);
						} else if (((value / 3) + value) >= mark) {
							lb3[i - 1].setText("NICE!");
							lb3[i - 1].setFont(new Font("Roman", Font.PLAIN, 15));
//							lb3[i-1].setForeground(Color.BLUE);
						} else if (((value / 2) + value) >= mark || ((value / 2) + value) == mark) {
							lb3[i - 1].setText("GOOD");
							lb3[i - 1].setFont(new Font("Roman", Font.PLAIN, 15));
//							lb3[i-1].setForeground(Color.magenta);
						} else {
							lb3[i - 1].setText("PASSED");
							lb3[i - 1].setFont(new Font("Roman", Font.PLAIN, 15));
//							lb3[i-1].setForeground(Color.green);

						}
					}
				}
			}
			if (classe2.substring(0, 5).equalsIgnoreCase("grade")) {
				total.setText("Total : " + rs.getString("sum(Total)") + " / " + (nn * subc));
				float avg = (rs.getFloat("sum(Total)")/(nn*subc))*100;
				average.setText("AVERAGE : " +df2.format(avg) + " %");
				grade.setForeground(new Color(102, 102, 102));
				if (rs.getFloat("sum(Average)") >= 95.00f && rs.getFloat("sum(Average)") <= 100.00f) {
					grade.setText("( A+ )");
					grade.setFont(new Font("Roman", Font.BOLD, 15));
				} else if (rs.getFloat("sum(Average)") >= 90.00f && rs.getFloat("sum(Average)") < 95.00f) {
					grade.setText("( A )");
					grade.setFont(new Font("Roman", Font.BOLD, 15));
				} else if (rs.getFloat("sum(Average)") >= 85.00f && rs.getFloat("sum(Average)") < 90.00f) {
					grade.setText("( B+ )");
					grade.setFont(new Font("Roman", Font.BOLD, 15));
				} else if (rs.getFloat("sum(Average)") >= 80.00f && rs.getFloat("sum(Average)") < 85.00f) {
					grade.setText("( B )");
					grade.setFont(new Font("Roman", Font.BOLD, 15));
				} else if (rs.getFloat("sum(Average)") >= 75.00f && rs.getFloat("sum(Average)") < 80.00f) {
					grade.setText("( C+ )");
					grade.setFont(new Font("Roman", Font.BOLD, 15));
				} else if (rs.getFloat("sum(Average)") >= 70.00f && rs.getFloat("sum(Average)") < 75.00f) {
					grade.setText("( C )");
					grade.setFont(new Font("Roman", Font.BOLD, 15));
				} else if (rs.getFloat("sum(Average)") >= 65.00f && rs.getFloat("sum(Average)") < 70.00f) {
					grade.setText("( D+ )");
					grade.setFont(new Font("Roman", Font.BOLD, 15));
				} else if (rs.getFloat("sum(Average)") >= 50.00f && rs.getFloat("sum(Average)") < 65.00f) {
					grade.setText("( D )");
					grade.setFont(new Font("Roman", Font.BOLD, 15));
				} else if (rs.getFloat("sum(Average)") < 50.00f) {
					grade.setText("( F )");
					grade.setFont(new Font("Roman", Font.BOLD, 15));
					grade.setForeground(Color.RED);
				}
				for (int i = 0; i < pri.length; i++) {
					if (!(pri[i] == null)) {
						lb1[i].setText(rs.getString("sum(" + pri[i] + ")"));
						float avg1 =((rs.getFloat("sum("+pri[i]+")") / (nn * subc)) * 100);
						lb2[i].setText("" +df2.format(avg1)  + " %");

						float value = (nn / 2);
						float mark = rs.getFloat("sum(" + pri[i] + ")");
						if (value > mark) {
							lb3[i].setText("FAILED");
							lb3[i].setFont(new Font("Roman", Font.BOLD, 15));
//							lb3[i-1].setForeground(Color.red);
						} else if (value == mark) {
							lb3[i].setText("POOR");
							lb3[i].setFont(new Font("Roman", Font.PLAIN, 15));
//							lb3[i-1].setForeground(Color.pink);
						} else if (((value / 3) + value) >= mark) {
							lb3[i].setText("NICE!");
							lb3[i].setFont(new Font("Roman", Font.PLAIN, 15));
//							lb3[i-1].setForeground(Color.BLUE);
						} else if (((value / 2) + value) >= mark || ((value / 2) + value) == mark) {
							lb3[i].setText("GOOD");
							lb3[i].setFont(new Font("Roman", Font.PLAIN, 15));
//							lb3[i-1].setForeground(Color.magenta);
						} else {
							lb3[i].setText("PASSED");
							lb3[i].setFont(new Font("Roman", Font.PLAIN, 15));
//							lb3[i-1].setForeground(Color.green);

						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				data.con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void markss(String name2, String exam2, String date2, String classe2, String subj2) {
		try {
			String url = "jdbc:sqlite:students.db";
			data.classForname();
			data.con = DriverManager.getConnection(url);
			data.st = data.con.createStatement();
			String query = "select Total,Average," + subj2 + " from records where Student_name='" + name2
					+ "' AND Examination='" + exam2 + "' AND ExamDate='" + date2 + "' AND classname='" + classe2 + "' AND deleted = 1";
			ResultSet rs = data.st.executeQuery(query);
			SM.setText("Class : " +classe2);
			int nn = 0;
			try {
				Databases db = new Databases();
				String url1 = "jdbc:sqlite:class.db";
				db.classForname();
				db.con = DriverManager.getConnection(url1);
				db.st = db.con.createStatement();

				String query1 = "SELECT maximum from Exam where examname='" + exam2 + "'";
				ResultSet rrs = db.st.executeQuery(query1);
				rrs.next();
				nn = rrs.getInt("maximum");

				db.con.close();
			} catch (Exception e) {

			}
			DecimalFormat df2 = new DecimalFormat("#.##");

			rs.next();
			if (classe2.substring(0, 4).equalsIgnoreCase("form")) {
				average.setText("AVERAGE : "+df2.format(rs.getFloat("Average"))+" %");
				total.setText("TOTAL : "+rs.getString("Total")+" / "+ (nn * subc));
				grade.setForeground(new Color(102, 102, 102));
				if(rs.getFloat("Average") >= 95.00f && rs.getFloat("Average") <= 100.00f ) {
					grade.setText("( A+ )");
					grade.setFont(new Font("Roman",Font.BOLD,15));
				}
				else if(rs.getFloat("Average") >= 90.00f && rs.getFloat("Average") < 95.00f){
					grade.setText("( A )");
					grade.setFont(new Font("Roman",Font.BOLD,15));
				}
				else if(rs.getFloat("Average") >= 85.00f && rs.getFloat("Average") < 90.00f){
					grade.setText("( B+ )");
					grade.setFont(new Font("Roman",Font.BOLD,15));
				}
				else if(rs.getFloat("Average") >= 80.00f && rs.getFloat("Average") < 85.00f){
					grade.setText("( B )");
					grade.setFont(new Font("Roman",Font.BOLD,15));
				}
				else if(rs.getFloat("Average") >= 75.00f && rs.getFloat("Average") < 80.00f){
					grade.setText("( C+ )");
					grade.setFont(new Font("Roman",Font.BOLD,15));
				}
				else if(rs.getFloat("Average") >= 70.00f && rs.getFloat("Average") < 75.00f){
					grade.setText("( C )");
					grade.setFont(new Font("Roman",Font.BOLD,15));
				}
				else if(rs.getFloat("Average") >= 65.00f && rs.getFloat("Average") < 70.00f){
					grade.setText("( D+ )");
					grade.setFont(new Font("Roman",Font.BOLD,15));
				}
				else if(rs.getFloat("Average") >= 50.00f && rs.getFloat("Average") < 65.00f){
					grade.setText("( D )");
					grade.setFont(new Font("Roman",Font.BOLD,15));
				}
				else if(rs.getFloat("Average") < 50.00f){
					grade.setText("( F )");
					grade.setFont(new Font("Roman",Font.BOLD,15));
					grade.setForeground(Color.RED);
				}
				
				for (int i = 0; i < ss1.length; i++) {
					if (!(ss1[i] == null)) {
						lb1[i - 1].setText(rs.getString(ss1[i]));
						lb2[i - 1].setText("" + df2.format(((rs.getFloat(ss1[i]) / (nn * subc)) * 100)) + " %");
						float value = (nn / 2);
						float mark = rs.getFloat(ss1[i]);
						if (value > mark) {
							lb3[i - 1].setText("FAILED");
							lb3[i - 1].setFont(new Font("Roman", Font.BOLD, 15));
//							lb3[i-1].setForeground(Color.red);
						} else if (value == mark) {
							lb3[i - 1].setText("POOR");
							lb3[i - 1].setFont(new Font("Roman", Font.PLAIN, 15));
//							lb3[i-1].setForeground(Color.pink);
						} else if (((value / 3) + value) >= mark) {
							lb3[i - 1].setText("NICE!");
							lb3[i - 1].setFont(new Font("Roman", Font.PLAIN, 15));
//							lb3[i-1].setForeground(Color.BLUE);
						} else if (((value / 2) + value) >= mark || ((value / 2) + value) == mark) {
							lb3[i - 1].setText("GOOD");
							lb3[i - 1].setFont(new Font("Roman", Font.PLAIN, 15));
//							lb3[i-1].setForeground(Color.magenta);
						} else {
							lb3[i - 1].setText("PASSED");
							lb3[i - 1].setFont(new Font("Roman", Font.PLAIN, 15));
//							lb3[i-1].setForeground(Color.green);

						}
					}
				}
			}
			if (classe2.substring(0, 5).equalsIgnoreCase("grade")) {
				average.setText("AVERAGE : "+df2.format(rs.getFloat("Average"))+" %");
				total.setText("TOTAL : "+rs.getString("Total")+" / "+ (nn * subc));
				grade.setForeground(new Color(102, 102, 102));
				if(rs.getFloat("Average") >= 95.00f && rs.getFloat("Average") <= 100.00f ) {
					grade.setText("( A+ )");
					grade.setFont(new Font("Roman",Font.BOLD,15));
				}
				else if(rs.getFloat("Average") >= 90.00f && rs.getFloat("Average") < 95.00f){
					grade.setText("( A )");
					grade.setFont(new Font("Roman",Font.BOLD,15));
				}
				else if(rs.getFloat("Average") >= 85.00f && rs.getFloat("Average") < 90.00f){
					grade.setText("( B+ )");
					grade.setFont(new Font("Roman",Font.BOLD,15));
				}
				else if(rs.getFloat("Average") >= 80.00f && rs.getFloat("Average") < 85.00f){
					grade.setText("( B )");
					grade.setFont(new Font("Roman",Font.BOLD,15));
				}
				else if(rs.getFloat("Average") >= 75.00f && rs.getFloat("Average") < 80.00f){
					grade.setText("( C+ )");
					grade.setFont(new Font("Roman",Font.BOLD,15));
				}
				else if(rs.getFloat("Average") >= 70.00f && rs.getFloat("Average") < 75.00f){
					grade.setText("( C )");
					grade.setFont(new Font("Roman",Font.BOLD,15));
				}
				else if(rs.getFloat("Average") >= 65.00f && rs.getFloat("Average") < 70.00f){
					grade.setText("( D+ )");
					grade.setFont(new Font("Roman",Font.BOLD,15));
				}
				else if(rs.getFloat("Average") >= 50.00f && rs.getFloat("Average") < 65.00f){
					grade.setText("( D )");
					grade.setFont(new Font("Roman",Font.BOLD,15));
				}
				else if(rs.getFloat("Average") < 50.00f){
					grade.setText("( F )");
					grade.setFont(new Font("Roman",Font.BOLD,15));
					grade.setForeground(Color.RED);
				}
				for (int i = 0; i < pri.length; i++) {
					if (!(pri[i] == null)) {
						lb1[i].setText(rs.getString(pri[i]));

						lb2[i].setText("" + df2.format(((rs.getFloat(pri[i]) / (nn * subc)) * 100)) + " %");

						float value = (nn / 2);
						float mark = rs.getFloat(pri[i]);
						if (value > mark) {
							lb3[i].setText("FAILED");
							lb3[i].setFont(new Font("Roman", Font.BOLD, 15));
//							lb3[i-1].setForeground(Color.red);
						} else if (value == mark) {
							lb3[i].setText("POOR");
							lb3[i].setFont(new Font("Roman", Font.PLAIN, 15));
//							lb3[i-1].setForeground(Color.pink);
						} else if (((value / 3) + value) >= mark) {
							lb3[i].setText("NICE!");
							lb3[i].setFont(new Font("Roman", Font.PLAIN, 15));
//							lb3[i-1].setForeground(Color.BLUE);
						} else if (((value / 2) + value) >= mark || ((value / 2) + value) == mark) {
							lb3[i].setText("GOOD");
							lb3[i].setFont(new Font("Roman", Font.PLAIN, 15));
//							lb3[i-1].setForeground(Color.magenta);
						} else {
							lb3[i].setText("PASSED");
							lb3[i].setFont(new Font("Roman", Font.PLAIN, 15));
//							lb3[i-1].setForeground(Color.green);

						}
					}
				}
			}
			data.con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private void student(String name2) {
		SN.setText(name2);
	
		try {
			String url = "jdbc:sqlite:students.db";
			data.classForname();
			data.con = DriverManager.getConnection(url);
			data.st = data.con.createStatement();
			String query = "select Mother_name, grad_year,Student_phone, Student_image ,Student_Email, Student_gender from graduate where Student_name='"
					+ name2 + "'";
			ResultSet rs = data.st.executeQuery(query);
			if (rs.next()) {
				SC.setText("Graduated : " + rs.getString("grad_year"));
			
				ST.setText("Phone : " + rs.getString("Student_phone"));
				SE.setText("Email : " + rs.getString("Student_Email"));
				String gender = rs.getString("Student_gender");
				byte[] image = rs.getBytes("Student_image");
				if (image == null) {
					if (gender.equalsIgnoreCase("female")) {
						BufferedImage im = null;
						try {
							im = ImageIO.read(getClass().getResource("/image/female2.png"));
						} catch (Exception e) {
						}
						ImageIcon imn = new ImageIcon(new ImageIcon(im).getImage().getScaledInstance(SI.getWidth(),
								SI.getHeight(), Image.SCALE_AREA_AVERAGING));
						SI.setIcon(imn);
					} else {
						BufferedImage im = null;
						try {
							im = ImageIO.read(getClass().getResource("/image/male1.png"));
						} catch (Exception e) {
						}
						ImageIcon imn = new ImageIcon(new ImageIcon(im).getImage().getScaledInstance(SI.getWidth(),
								SI.getHeight(), Image.SCALE_AREA_AVERAGING));
						SI.setIcon(imn);
					}
				} else {
					ImageIcon imn = new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(SI.getWidth(),
							SI.getHeight(), Image.SCALE_AREA_AVERAGING));
					SI.setIcon(imn);
				}
			} else {
				String query1 = "select Mother_name, Student_phone, Student_image ,Student_Email, Student_gender from graduate where Student_name='"
						+ name2 + "'";
				ResultSet rs1 = data.st.executeQuery(query1);
				rs1.next();
				SM.setText("Mother : " + rs1.getString("Mother_name"));
				ST.setText("Phone : " + rs1.getString("Student_phone"));
				SE.setText("Email : " + rs1.getString("Student_Email"));
				String gender = rs1.getString("Student_gender");
				byte[] image = rs1.getBytes("Student_image");
				if (image == null) {
					if (gender.equalsIgnoreCase("female")) {
						BufferedImage im = null;
						try {
							im = ImageIO.read(getClass().getResource("/image/female2.png"));
						} catch (Exception e) {
						}
						ImageIcon imn = new ImageIcon(new ImageIcon(im).getImage().getScaledInstance(SI.getWidth(),
								SI.getHeight(), Image.SCALE_AREA_AVERAGING));
						SI.setIcon(imn);
					} else {
						BufferedImage im = null;
						try {
							im = ImageIO.read(getClass().getResource("/image/male1.png"));
						} catch (Exception e) {
						}
						ImageIcon imn = new ImageIcon(new ImageIcon(im).getImage().getScaledInstance(SI.getWidth(),
								SI.getHeight(), Image.SCALE_AREA_AVERAGING));
						SI.setIcon(imn);
					}
				} else {
					ImageIcon imn = new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(SI.getWidth(),
							SI.getHeight(), Image.SCALE_AREA_AVERAGING));
					SI.setIcon(imn);
				}

			}

			data.con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private void center() {

		lb = new JLabel[15];
		sem = new JSeparator[15];
		int BL[] = { 120, 155, 185, 220, 255, 285, 320, 355, 385, 420, 455, 485, 520, 555, 585, 620, 655, 685, 720, 755,
				785, 820, 855, 885, 920, 955 };
		for (int i = 0; i < lb.length; i++) {
			sem[i] = new JSeparator();
			sem[i].setBounds(20, BL[i] + 30, 965, 1);
			sem[i].setForeground(new Color(244, 244, 244));
			lb[i] = new JLabel();
			lb[i].setBounds(25, BL[i], 250, 30);
			lb[i].setFont(new Font("Roman", Font.PLAIN, 13));
			lb[i].setForeground(new Color(102, 102, 102));
			marks.add(lb[i]);
			marks.add(sem[i]);
		}
		lb1 = new JLabel[15];
		for (int i = 0; i < lb.length; i++) {
			lb1[i] = new JLabel();
			lb1[i].setBounds(320, BL[i], 250, 30);
			lb1[i].setFont(new Font("Roman", Font.PLAIN, 13));
			lb1[i].setForeground(new Color(102, 102, 102));
			marks.add(lb1[i]);
		}
		lb2 = new JLabel[15];
		for (int i = 0; i < lb.length; i++) {
			lb2[i] = new JLabel();
			lb2[i].setBounds(620, BL[i], 250, 30);
			lb2[i].setFont(new Font("Roman", Font.PLAIN, 13));
			lb2[i].setForeground(new Color(102, 102, 102));
			marks.add(lb2[i]);
		}
		lb3 = new JLabel[15];
		for (int i = 0; i < lb.length; i++) {
			lb3[i] = new JLabel();
			lb3[i].setBounds(950, BL[i], 250, 30);
			lb3[i].setFont(new Font("Roman", Font.PLAIN, 13));
			lb3[i].setForeground(new Color(102, 102, 102));
			marks.add(lb3[i]);
		}

	}

	private void marks() {
		sep = new JSeparator();
		sep.setBounds(15, 100, 1000, 1);
		marks.add(sep);

		mar = new JLabel("Marks");
		mar.setBounds(20, 5, 100, 30);
		mar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		marks.add(mar);

		Ex = new JLabel("Exam name");
		Ex.setBounds(20, 25, 300, 30);
		Ex.setFont(new Font("Roman", Font.PLAIN, 13));
		Ex.setForeground(new Color(102, 102, 102));

		Exd = new JLabel("Exam Date");
		Exd.setBounds(20, 45, 300, 30);
		Exd.setFont(new Font("Roman", Font.PLAIN, 13));
		Exd.setForeground(new Color(102, 102, 102));

		sub = new JLabel(("subject").toUpperCase());
		sub.setBounds(25, 72, 200, 30);
		sub.setFont(new Font("Roman", Font.PLAIN, 13));
		sub.setForeground(new Color(102, 102, 102));

		ma = new JLabel(("mark").toUpperCase());
		ma.setBounds(320, 72, 200, 30);
		ma.setFont(new Font("Roman", Font.PLAIN, 13));
		ma.setForeground(new Color(102, 102, 102));
		
		total = new JLabel();
		total.setBounds(320, 45, 200, 30);
		total.setFont(new Font("Roman", Font.PLAIN, 13));
		total.setForeground(new Color(102, 102, 102));

		average = new JLabel();
		average.setBounds(620, 45, 200, 30);
		average.setFont(new Font("Roman", Font.PLAIN, 13));
		average.setForeground(new Color(102, 102, 102));

		av = new JLabel(("average").toUpperCase());
		av.setBounds(620, 72, 200, 30);
		av.setFont(new Font("Roman", Font.PLAIN, 13));
		av.setForeground(new Color(102, 102, 102));

		va = new JLabel(("value").toUpperCase());
		va.setBounds(950, 72, 200, 30);
		va.setFont(new Font("Roman", Font.PLAIN, 13));
		va.setForeground(new Color(102, 102, 102));
		grade = new JLabel();
		grade.setBounds(950, 45, 200, 30);
		grade.setFont(new Font("Roman", Font.PLAIN, 13));
		grade.setForeground(new Color(102, 102, 102));

		marks.add(grade);
		marks.add(average);
		marks.add(total);
		marks.add(va);
		marks.add(av);
		marks.add(ma);
		marks.add(sub);
		marks.add(Exd);
		marks.add(Ex);

	}

	private void profile() {
		SI = new JLabel("ab");
		SI.setBounds(10, 54, 110, 120);
		SI.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.lightGray));

		SN = new JLabel("Student name");
		SN.setBounds(140, 50, 350, 30);
		SN.setFont(new Font("Roman", Font.BOLD, 15));
		SN.setForeground(new Color(102, 102, 102));

		SM = new JLabel("Mother name");
		SM.setBounds(140, 75, 350, 30);
		SM.setFont(new Font("Roman", Font.PLAIN, 15));
		SM.setForeground(new Color(102, 102, 102));

		SC = new JLabel("Class name");
		SC.setBounds(140, 100, 350, 30);
		SC.setFont(new Font("Roman", Font.PLAIN, 15));
		SC.setForeground(new Color(102, 102, 102));

		ST = new JLabel("Phone");
		ST.setBounds(140, 125, 350, 30);
		ST.setFont(new Font("Roman", Font.PLAIN, 15));
		ST.setForeground(new Color(102, 102, 102));

		SE = new JLabel("Email");
		SE.setBounds(140, 150, 350, 30);
		SE.setFont(new Font("Roman", Font.PLAIN, 15));
		SE.setForeground(new Color(102, 102, 102));

		TitlePanel.add(SE);
		TitlePanel.add(ST);
		TitlePanel.add(SC);
		TitlePanel.add(SM);
		TitlePanel.add(SN);
		TitlePanel.add(SI);
	}

	private void titles() {
		shname = new JLabel("SCHMASYS PRO...");
		shname.setBounds(50, 20, 300, 25);
		shname.setFont(new Font("Roman", Font.BOLD, 15));
		shname.setForeground(Color.GRAY);

		logo = new JLabel();
		logo.setBounds(3, 6, 46, 40);
		logo.setBorder(BorderFactory.createSoftBevelBorder(10));

		try {

			BufferedImage image = null;
			image = ImageIO.read(getClass().getResource("/image/UJUHU-01.png"));
			logo.setIcon(new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(logo.getWidth(),
					logo.getHeight(), Image.SCALE_AREA_AVERAGING)));

		} catch (Exception e) {
			System.out.println(e);
		}

		header.add(shname);
		header.add(logo);
	}

	private void panels() {
		TitlePanel = new JPanel();
		TitlePanel.setBounds(5, 5, 1035, 180);
		TitlePanel.setLayout(null);
		TitlePanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.lightGray));
		TitlePanel.setBackground(Color.white);

		seprater = new JPanel();
		seprater.setBounds(5, 190, 1035, 8);
		seprater.setBackground(new Color(0, 27, 61));

		marks = new JPanel();
		marks.setLayout(null);
		marks.setBackground(Color.white);
		marks.setBounds(5, 198, 1035, 800);

		header = new JPanel();
		header.setBounds(2, 2, 1031, 50);
		header.setBackground(new Color(244, 240, 240));
		header.setLayout(null);
		TitlePanel.add(header);

		Container.add(marks);
		Container.add(seprater);
		Container.add(TitlePanel);
	}

	private void container() {
		Container = new JPanel();
		Container.setLayout(null);
		Container.setBackground(Color.white);
		Container.setBounds(2, 2, 1040, 850);
		add(Container);

	}

	private void design() {
		setVisible(false);
		setLayout(null);
		setBackground(Color.white);
		setBounds(0, 0, 1045, 890);
	}


}
