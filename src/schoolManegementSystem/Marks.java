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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.*;

import javax.swing.*;

public class Marks extends JPanel {
	private static final long serialVersionUID = 1787400983411840238L;
	private JPanel pane, pane1;
	private JLabel cr, cr1;
	private JPanel container;
	private JCheckBox Scsub[], Pcsub[];
	private String Ssub[] = { "Islamic", "Arabic", "Somali", "English",
			"Mathematics", "Physics", "Biology", "Chemistry", "Geographic", "History", "Business", "Computer" };
	private String Psub[] = { "Islamic", "Arabic", "Somali", "English",
			"Mathematics ", "Science", "Social" };
	private JScrollPane spane, spane1, spane2;
	private JPanel sh, sh1, sh2;
	private JCheckBox ex[];
	private String exams[] = { "Term one", "Middle Term", "Term Two", "Final" };
	private JLabel lb1, lb2, lb3;
	private JButton submit;
	private String s[];
	private String ss[];
	private int no;
	private String exm[];
	private int pp;
	submarks m = new submarks();
	itemlistener item = new itemlistener();
	actionlistener action = new actionlistener();
	Databases data = new Databases();

	public Marks() {
		Subjects();
		panels();
		conatianer();
		desing();
		try {
			data.classForname();
			data.con = DriverManager.getConnection("jdbc:sqlite:class.db");
			data.st = data.con.createStatement();
			String query = "select * from allSub";
			ResultSet rs = data.st.executeQuery(query);
			rs.next();
			String ss = rs.getString(1);

			if (ss.equals("Islamic")) {
				m.setVisible(true);
				container.setVisible(false);
			} else {
				container.setVisible(true);
			}

			rs.close();
		} catch (Exception e) {

		} finally {

			try {
				data.st.close();
				data.con.close();
			} catch (Exception e) {
				System.out.println(e);
			}

		}
	}

	private void Subjects() {

		submit = new JButton("SUBMIT");
		submit.setBounds(50, 510, 350, 50);
		submit.setBorder(BorderFactory.createSoftBevelBorder(10));
		submit.setBackground(new Color(6, 104, 159));
		submit.setFont(new Font("Calibri", Font.PLAIN, 30));
		submit.setForeground(Color.white);
		submit.addActionListener(action);

		sh = new JPanel();
		sh.setOpaque(false);
		sh.setPreferredSize(new Dimension(0, 400));
		sh.setBorder(BorderFactory.createSoftBevelBorder(10));
		sh.setLayout(null);

		int l[] = { 40, 70, 100, 130, 160, 190, 220, 250, 280, 310, 340, 370 };
		Scsub = new JCheckBox[12];
		for (int i = 0; i < Scsub.length; i++) {
			Scsub[i] = new JCheckBox(Ssub[i]);
			Scsub[i].setBounds(10, l[i], 200, 40);
			Scsub[i].setBorder(BorderFactory.createSoftBevelBorder(10));
			Scsub[i].setFont(new Font("ARIAL", Font.PLAIN, 18));
			Scsub[i].setForeground(Color.black);
			Scsub[i].addItemListener(item);
			sh.add(Scsub[i]);
		}

		sh1 = new JPanel();
		sh1.setPreferredSize(new Dimension(0, 270));
		sh1.setLayout(null);

		int ll[] = { 40, 70, 100, 130, 160, 190, 220 };
		Pcsub = new JCheckBox[7];
		for (int i = 0; i < Pcsub.length; i++) {
			Pcsub[i] = new JCheckBox(Psub[i]);
			Pcsub[i].setBounds(10, ll[i], 200, 40);
			Pcsub[i].setBorder(BorderFactory.createSoftBevelBorder(10));
			Pcsub[i].setFont(new Font("ARIAL", Font.PLAIN, 18));
			Pcsub[i].setForeground(Color.black);
			Pcsub[i].addItemListener(item);
			sh1.add(Pcsub[i]);
		}

		sh2 = new JPanel();
		sh2.setBorder(BorderFactory.createSoftBevelBorder(10));
		sh2.setPreferredSize(new Dimension(0, 170));
		sh2.setLayout(null);

		int lll[] = { 40, 70, 100, 130 };
		ex = new JCheckBox[4];
		for (int i = 0; i < ex.length; i++) {
			ex[i] = new JCheckBox(exams[i]);
			ex[i].setBounds(10, lll[i], 200, 40);
			ex[i].setBorder(BorderFactory.createSoftBevelBorder(10));
			ex[i].setFont(new Font("ARIAL", Font.PLAIN, 18));
			ex[i].setForeground(Color.black);
			ex[i].addItemListener(item);
			sh2.add(ex[i]);
		}

		lb1 = new JLabel();
		lb1.setBounds(10, 20, 270, 20);
		lb1.setFont(new Font("ARIAL", Font.PLAIN, 14));
		lb1.setForeground(Color.gray);
		lb1.setText("Please select  the subjects of your school ");

		sh.add(lb1);

		lb2 = new JLabel();
		lb2.setBounds(10, 20, 270, 20);
		lb2.setFont(new Font("ARIAL", Font.PLAIN, 14));
		lb2.setForeground(Color.gray);
		lb2.setText("Please select  the subjects of your school ");

		sh1.add(lb2);

		lb3 = new JLabel();
		lb3.setBounds(10, 20, 300, 20);
		lb3.setFont(new Font("ARIAL", Font.PLAIN, 14));
		lb3.setForeground(Color.gray);
		lb3.setText("Please select  the Examinations of your school ");

		sh2.add(lb3);

		spane = new JScrollPane(sh);
		spane.setBounds(50, 100, 350, 400);
		spane.setOpaque(false);
		spane.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createSoftBevelBorder(10), "Check the subjects"));

		spane1 = new JScrollPane(sh1);
		spane1.setBounds(50, 100, 350, 250);
		spane1.setOpaque(false);
		spane1.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createSoftBevelBorder(10), "Check the subjects"));

		spane2 = new JScrollPane(sh2);
		spane2.setBounds(50, 365, 350, 200);
		spane2.setOpaque(false);
		spane2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createSoftBevelBorder(10), "Examinations"));

	}

	private void panels() {

		pane = new JPanel();
		pane.setBounds(30, 70, 450, 700);
		pane.setBackground(Color.white);
		pane.setLayout(null);
		pane.add(submit);

		pane1 = new JPanel();
		pane1.setBounds(560, 70, 450, 700);
		pane1.setBackground(Color.white);
		pane1.setLayout(null);

		cr = new JLabel("SECONDARY SUBJECTS");
		cr.setBounds(80, 50, 290, 30);
		cr.setFont(new Font("Calibri", Font.CENTER_BASELINE, 30));
		pane.add(cr);

		cr1 = new JLabel("PRIMARY SUBJECTS");
		cr1.setBounds(80, 50, 250, 30);
		cr1.setFont(new Font("Calibri", Font.CENTER_BASELINE, 30));
		pane1.add(cr1);

		pane.add(spane);
		pane1.add(spane1);
		pane1.add(spane2);

	}

	private void conatianer() {
		container = new JPanel();
		container.setBackground(new Color(6, 104, 159));
		container.setBounds(0, 0, 1045, 690);
		container.setLayout(null);
		container.add(pane);
		container.add(pane1);
		add(container);
		add(m);
	}

	private void desing() {

		setVisible(false);
		setLayout(null);
		setBackground(Color.white);
		setBounds(280, 30, 1045, 690);
	}

	private class actionlistener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				data.classForname();
				data.con = DriverManager.getConnection("jdbc:sqlite:class.db");
				data.st = data.con.createStatement();

				int x = 0;
				try {
					for (int i = 0; i < s.length; i++) {
						if (!(no < 9) && !(pp == 0)) {
							if (!(s[i] == null)) {
								String query = "insert into allSub values('" + s[i] + "')";
								data.st.executeUpdate(query);
								String query1 = "insert into subjects values('" + s[i] + "')";
								 data.st.executeUpdate(query1);
								x = no;
							}
						} else if (no < 9) {
							JOptionPane.showMessageDialog(null, "please select the first 10 Secondary subjects",
									"Schmasys Error", JOptionPane.ERROR_MESSAGE);
							break;

						} else {
							JOptionPane.showMessageDialog(null, "please select the Secondary subjects",
									"Schmasys Error", JOptionPane.ERROR_MESSAGE);
							break;
						}
					}
					for (int i = 0; i < exm.length; i++) {
						if (!(exm[i] == null)) {
							if (!(x == 0)) {
								String query = "insert into Exam (examname) values('" + exm[i] + "')";
							 data.st.executeUpdate(query);
							}
						}
					}
					for (int i = 5; i < ss.length; i++) {
						if (!(ss[i] == null)) {
							if (!(x == 0)) {
								String query = "insert into Psubj values('" + ss[i] + "')";
								 data.st.executeUpdate(query);
								String query1 = "insert into allSub values('" + ss[i] + "')";
							 data.st.executeUpdate(query1);
								container.setVisible(false);
							} else {
								JOptionPane.showMessageDialog(null, "please select the first 10 Secondary subjects",
										"Schmasys Error", JOptionPane.ERROR_MESSAGE);
								break;
							}

						} else {
							JOptionPane.showMessageDialog(null, "please select the last two primary subjects",
									"Schmasys Error", JOptionPane.ERROR_MESSAGE);
							break;
						}
					}

				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null, "please select subjects before clicking me", "Schmasys Error",
							JOptionPane.ERROR_MESSAGE);
				}

				String queryy = "select * from allSub";
				ResultSet rs1 = data.st.executeQuery(queryy);
				while (rs1.next()) {
					save(rs1.getString(1));
				
				}
				rs1.close();
				m.setVisible(true);
				container.setVisible(false);
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				try {
					data.st.close();
					data.con.close();
				} catch (Exception e) {

				}

				
			}
		}

		private void save(String string) {
			try {
				data.classForname();
				data.con = DriverManager.getConnection("jdbc:sqlite:students.db");
				data.st = data.con.createStatement();
				String query = "alter table student add " + string + " double not null default 0.0";
				 data.st.executeUpdate(query);

				String query1 = "alter table records add " + string + " double not null default 0.0";
				 data.st.executeUpdate(query1);

				data.con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
			

		}
	}

	private class itemlistener implements ItemListener {
		public void itemStateChanged(ItemEvent arg0) {
			s = new String[Scsub.length];
			for (int i = 0; i < Scsub.length; i++) {
				if (Scsub[i].isSelected()) {
					no = i;
					s[i] = Scsub[i].getText();
				}
			}
			ss = new String[Pcsub.length];
			for (int i = 0; i < Pcsub.length; i++) {
				if (Pcsub[i].isSelected()) {
					pp = 2;
					ss[i] = Pcsub[i].getText();
				}
			}
			exm = new String[ex.length];
			for (int i = 0; i < ex.length; i++) {
				if (ex[i].isSelected()) {

					exm[i] = ex[i].getText();
				}
			}

		}
	}
}