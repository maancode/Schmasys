package schoolManegementSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.*;
import javax.swing.table.TableColumn;

import net.proteanit.sql.DbUtils;

public class tables extends JPanel {
	private static final long serialVersionUID = 1L;
	public JButton btn;
	public JLabel lb, background, logo;
	public JLabel[] covers;
	public JLabel HTN, SN, TE, CA, ST, no;
	private JPanel container, pane, header, cover;
	public JTable tb;
	private JScrollPane sp;
	private JPopupMenu pop;
	private JMenuItem Delete, Open;
	public String name, Class, school;
	private byte[] image;

	Databases data = new Databases();
	actionListener action = new actionListener();
	StudentProfile file = new StudentProfile();

	public tables() {
		covers();
		table();
		panels();
		contaianer();
	}

	private void covers() {
		cover = new JPanel();
		cover.setBounds(0, 0, 1045, 250);
		cover.setLayout(null);
		cover.setBackground(new Color(29, 41, 77, 80));

		BufferedImage im = null;
		try {

			im = ImageIO.read(getClass().getResource("/image/man3.png"));

		} catch (Exception e) {

		}

		HTN = new JLabel(new ImageIcon(im));
		HTN.setBounds(5, 110, 32, 32);
		HTN.setForeground(Color.white);
		HTN.setFont(new Font("Calibri", Font.PLAIN, 17));

		BufferedImage im1 = null;
		try {

			im1 = ImageIO.read(getClass().getResource("/image/sec.png"));

		} catch (Exception e) {

		}

		SN = new JLabel(new ImageIcon(im1));
		SN.setBounds(5, 160, 32, 32);
		SN.setForeground(Color.white);
		SN.setFont(new Font("Calibri", Font.PLAIN, 17));

		BufferedImage im2 = null;
		try {

			im2 = ImageIO.read(getClass().getResource("/image/te.png"));

		} catch (Exception e) {

		}

		TE = new JLabel(new ImageIcon(im2));
		TE.setBounds(630, 85, 32, 32);
		TE.setForeground(Color.white);
		TE.setFont(new Font("Calibri", Font.PLAIN, 17));

		BufferedImage im3 = null;
		try {

			im3 = ImageIO.read(getClass().getResource("/image/te.png"));

		} catch (Exception e) {

		}

		CA = new JLabel(new ImageIcon(im3));
		CA.setBounds(630, 130, 32, 32);
		CA.setForeground(Color.white);
		CA.setFont(new Font("Calibri", Font.PLAIN, 17));

		BufferedImage im4 = null;
		try {

			im4 = ImageIO.read(getClass().getResource("/image/stu.png"));

		} catch (Exception e) {

		}

		ST = new JLabel(new ImageIcon(im4));
		ST.setBounds(630, 170, 32, 32);
		ST.setForeground(Color.white);
		ST.setFont(new Font("Calibri", Font.PLAIN, 20));

		no = new JLabel();
		no.setBounds(680, 170, 100, 32);
		no.setForeground(Color.white);
		no.setFont(new Font("Calibri", Font.BOLD, 17));

		cover.add(CA);
		cover.add(TE);
		cover.add(SN);
		cover.add(HTN);
		cover.add(ST);
		cover.add(no);

		BufferedImage image1 = null;

		try {
			image1 = ImageIO.read(getClass().getResource("/image/UJUHU-01.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}

		logo = new JLabel(new ImageIcon(
				new ImageIcon(image1).getImage().getScaledInstance(200, 200, Image.SCALE_AREA_AVERAGING)));
		logo.setBounds(410, 10, 200, 200);

		try {
			String url = "jdbc:sqlite:login.db";
			data.classForname();
			data.con = DriverManager.getConnection(url);
			data.st = data.con.createStatement();
			ResultSet rs = data.st.executeQuery("select  logo from loging");
			rs.next();
			byte[] image = rs.getBytes("logo");
			logo.setIcon(new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(logo.getWidth(),
					logo.getHeight(), Image.SCALE_AREA_AVERAGING)));

		} catch (Exception e) {

		}

		cover.add(logo);

		int BLocation[] = { 45, 115, 165, 45, 90, 135 };
		covers = new JLabel[6];
		for (int i = 0; i < covers.length; i++) {
			covers[i] = new JLabel();
			covers[i].setBounds(20, BLocation[i], 400, 35);
			covers[i].setForeground(Color.white);
			covers[i].setFont(new Font("Calibri", Font.BOLD, 25));
			if (i == 0) {

				try {
					ResultSet rs = data.st.executeQuery("select schoolName from loging;");
					rs.next();
					String str = rs.getString("schoolName");
					String getter = str;
					school = str;
					covers[i].setText(getter);

				} catch (Exception e) {

				}
			} else if (i == 1) {
				covers[i].setFont(new Font("Calibri", Font.PLAIN, 18));
				covers[i].setBounds(43, BLocation[i], 400, 35);
				try {

					ResultSet rs = data.st.executeQuery("select HeadteacherName from loging;");
					rs.next();
					String str = rs.getString("HeadteacherName");
					String getter = str;
					covers[i].setFont(new Font("Calibri", Font.PLAIN, 25));
					covers[i].setText(getter);

				} catch (Exception e) {

				}

			} else if (i == 2) {
				covers[i].setFont(new Font("Calibri", Font.PLAIN, 18));
				covers[i].setBounds(43, BLocation[i], 400, 35);
				try {

					ResultSet rs = data.st.executeQuery("select secretaryName from loging;");
					rs.next();
					String str = rs.getString("secretaryName");
					String getter = str;
					covers[i].setFont(new Font("Calibri", Font.PLAIN, 25));
					covers[i].setText(getter);
					data.con.close();
				} catch (Exception e) {

				}

			} else if (i == 3) {

				covers[i].setBounds(680, BLocation[i], 400, 35);

			} else if (i == 4) {

				covers[i].setFont(new Font("Calibri", Font.PLAIN, 25));
				covers[i].setBounds(680, BLocation[i], 400, 35);
			} else if (i == 5) {
				covers[i].setFont(new Font("Calibri", Font.PLAIN, 25));
				covers[i].setBounds(680, BLocation[i], 400, 35);

			}
			cover.add(covers[i]);
		}

	}

	private void table() {

		tb = new JTable();
		tb.setPreferredScrollableViewportSize(new Dimension(0, 0));
		tb.setFont(new Font("ARIAL", Font.PLAIN, 15));
		tb.getTableHeader().setBackground(new Color(34, 166, 242));
		tb.getTableHeader().setForeground(Color.white);
		tb.getTableHeader().setFont(new Font("ARIAL", Font.PLAIN, 20));
		tb.setBorder(BorderFactory.createSoftBevelBorder(10));
		tb.setSurrendersFocusOnKeystroke(false);
		tb.setRowHeight(30);
		tb.setSelectionBackground(new Color(97, 146, 194, 100));
		tb.setSelectionForeground(Color.BLACK);
		tb.setEnabled(isEnabled());

		pops();
		sp = new JScrollPane(tb);
		sp.setBounds(0, 260, 1045, 410);
		sp.setBorder(BorderFactory.createSoftBevelBorder(10));

	}

	private void pops() {

		pop = new JPopupMenu();
		pop.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(244, 244, 244)));
		pop.setFont(new Font("Roman", Font.PLAIN, 15));

		BufferedImage del = null;
		try {
			del = ImageIO.read(getClass().getResource("/image/dele.png"));
		} catch (Exception e) {
			System.out.println(e);
		}

		KeyStroke ctrl = KeyStroke.getKeyStroke(KeyEvent.VK_D, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
		Delete = new JMenuItem(
				new ImageIcon(new ImageIcon(del).getImage().getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING)));
		Delete.setText("Delete the Student");
		Delete.setBackground(Color.white);
		Delete.setFont(new Font("ARIAL", Font.PLAIN, 15));
		Delete.setBorder(BorderFactory.createSoftBevelBorder(10));
		Delete.setAccelerator(ctrl);
		Delete.setMnemonic(KeyEvent.VK_D);
		Delete.addActionListener(action);

		BufferedImage open = null;
		try {
			open = ImageIO.read(getClass().getResource("/image/dele.png"));
		} catch (Exception e) {
			System.out.println(e);
		}

		KeyStroke ctrl1 = KeyStroke.getKeyStroke(KeyEvent.VK_O, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
		Open = new JMenuItem(
				new ImageIcon(new ImageIcon("").getImage().getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING)));
		Open.setText("Open the profile");
		Open.setBackground(Color.white);
		Open.setFont(new Font("ARIAL", Font.BOLD, 15));
		Open.setBorder(BorderFactory.createSoftBevelBorder(10));
		Open.setAccelerator(ctrl1);
		Open.setMnemonic(KeyEvent.VK_D);
		Open.addActionListener(action);

		tb.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (tb.isColumnSelected(tb.getSelectedColumn())) {
					name = (String) tb.getValueAt(tb.getSelectedRow(), 1);
					if (e.isPopupTrigger()) {
						pop.show(tb, e.getX(), e.getY());

					}
				}
			}
		});
		pop.add(Open);
		pop.add(Delete);
		tb.add(pop);
	}

	private void panels() {
		header = new JPanel();
		header.setBackground(Color.black);
		header.setBounds(0, 0, 1045, 250);
		header.setLayout(null);

		header.add(cover);
		String str = "/image/backgr1.jpg";
		BufferedImage image = null;
		try {

			image = ImageIO.read(getClass().getResource(str));

		} catch (Exception e) {

		}

		background = new JLabel();
		background.setBounds(0, 0, 1045, 250);
		background.setIcon(new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(background.getWidth(),
				background.getHeight(), Image.SCALE_AREA_AVERAGING)));
		header.add(background);

	}

	private void contaianer() {
		lb = new JLabel();
		lb.setBounds(50, 150, 300, 40);

		btn = new JButton("<");
		btn.setBounds(10, 10, 30, 30);
		btn.setBorder(BorderFactory.createSoftBevelBorder(10));
		btn.setBackground(new Color(34, 38, 126));
		btn.setOpaque(true);
		btn.setFont(new Font("Calibri", Font.BOLD, 30));
		btn.setForeground(Color.WHITE);

		pane = new JPanel();
		pane.setBounds(0, 0, 1045, 670);
		pane.setLayout(null);

		pane.add(btn);
		pane.add(header);
		container = new JPanel();
		container.setBounds(0, 0, 1045, 670);
		container.setLayout(null);
		pane.add(lb);

		container.add(sp);
		container.add(pane);
		add(container);
		add(file);
		file.back.addActionListener(action);
		setVisible(false);
		setLayout(null);
		setBackground(Color.white);
		setBounds(0, 0, 1045, 670);

	}

	private class actionListener implements ActionListener {

		public void actionPerformed(ActionEvent ar) {
			if (ar.getSource() == Delete) {
				int no = JOptionPane.showConfirmDialog(null, "Are you sure to delete this Student", "Schmasys warring",
						JOptionPane.OK_CANCEL_OPTION);
				if (no == 0) {

					try {
						Databases dc1 = new Databases();

						dc1.classForname();
						dc1.con = DriverManager.getConnection("jdbc:sqlite:trash.db");
						dc1.st = dc1.con.createStatement();
						String names = name;
						Date d = new Date();
						SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss a   dd_MMM_yyyy");
						String ss = s.format(d);
						dc1.st.executeUpdate("insert into folder values('" + names + "',1,'" + ss + "')");
						executersThenInserters(names);

						dc1.con.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else if (ar.getSource() == Open) {
				file.getInfo(name, Class, school);
				container.setVisible(false);
				file.setVisible(true);
			} else if (ar.getSource() == file.back) {
				try {
					data.classForname();
					data.con = DriverManager.getConnection("jdbc:sqlite:students.db");
					data.st = data.con.createStatement();
					String execute1 = "SELECT  Rowid as ID, Student_name as NAME FROM student where Student_class='"
							+ Class + "' ORDER BY Student_name asc ;";
					ResultSet rs = data.st.executeQuery(execute1);
					tb.setModel(DbUtils.resultSetToTableModel(rs));
					rowId(tb);
					collw(tb);
					data.con.close();
				} catch (Exception e) {
					System.out.println(e);
				}

				file.SM.setVisible(true);
				file.SE.setVisible(true);
				file.SP.setVisible(true);
				file.PP.setVisible(true);
				file.SG.setVisible(true);
				file.SB.setVisible(true);
				file.SL.setVisible(true);
				file.SA.setVisible(true);
				file.SN.setVisible(true);
				file.Cn.setVisible(false);
				file.change.setVisible(false);
				file.save.setVisible(false);
				for (int i = 0; i < file.text.length; i++) {
					if (!((i == 5) || (i == 4)))
						file.text[i].setVisible(false);
				}

				container.setVisible(true);
				file.setVisible(false);
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

				String execute1 = "SELECT RowID as ID, Student_name as NAME FROM student where Student_class='" + object
						+ "' ;";

				ResultSet rs = dc2.st.executeQuery(execute1);
				tb.setModel(DbUtils.resultSetToTableModel(rs));
				rowId(tb);
				collw(tb);

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
