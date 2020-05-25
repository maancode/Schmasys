package schoolManegementSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class StudentProfile extends JPanel {
	private static final long serialVersionUID = 1L;
	public JButton back;
	private JPanel container;
	private JSeparator sep;
	private JLabel detail;
	private JLabel picture;
	private JLabel Schmasys;
	public JLabel SN, SM, SE, SP, PP, SCH, SC, SG, SB, SL, SA;
	private JLabel lb[];
	private String list[] = { "Mother Name :", "Email :", "Student Phone :", "Parent Phone :", "School :", "Class :",
			"Gender :", "Date Of Birth :", "Location Of Birth :", "Address :" };
	private JButton edit, print;
	public JButton change;
	public JTextField text[];
	public JButton save;
	private JScrollPane MainContainer;
	private String filePath;
	public JTextField Cn;
	private String sname, sclass;

	Databases data = new Databases();
	actionListener action = new actionListener();

	public StudentProfile() {
		design();
		container();
		options();
		change();
	}

	public void getInfo(String name, String Class, String school) {
		sname = name;
		sclass = Class;
		Cn.setText(name);
		detail.setText(name.toUpperCase().charAt(0) + name.substring(1, name.indexOf(" ")) + " Details");
		SN.setText(name.toUpperCase());
		try {
			data.classForname();
			data.con = DriverManager.getConnection("jdbc:sqlite:students.db");
			data.st = data.con.createStatement();
			ResultSet rs = data.st.executeQuery(
					"SELECT Mother_name, Student_Email, Student_phone , Parent_phone, Student_class,Student_gender,Birth_Date,"
							+ "Location_Birth,Student_address,Student_image from" + " student where Student_name='"
							+ name + "'" + " AND Student_class='" + Class + "'");
			rs.next();
			SM.setText(rs.getString("Mother_name"));
			SE.setText(rs.getString("Student_Email"));
			SP.setText(rs.getString("Student_phone"));
			PP.setText(rs.getString("Parent_phone"));
			SCH.setText(school);
			SC.setText(Class);
			SG.setText(rs.getString("Student_gender"));
			SB.setText(rs.getString("Birth_Date"));
			SL.setText(rs.getString("Location_Birth"));
			SA.setText(rs.getString("Student_address"));
			byte image[] = rs.getBytes("Student_image");
			String gender = rs.getString("Student_gender");
			text[0].setText(rs.getString("Mother_name"));
			text[1].setText(rs.getString("Student_Email"));
			text[2].setText(rs.getString("Student_phone"));
			text[3].setText(rs.getString("Parent_phone"));
			text[6].setText(rs.getString("Student_gender"));
			text[7].setText(rs.getString("Birth_Date"));
			text[8].setText(rs.getString("Location_Birth"));
			text[9].setText(rs.getString("Student_address"));

			if (image == null) {
				if (gender.equalsIgnoreCase("female")) {
					BufferedImage im = null;
					try {
						im = ImageIO.read(getClass().getResource("/image/fle.png"));
					} catch (Exception e) {
					}
					ImageIcon imn = new ImageIcon(new ImageIcon(im).getImage().getScaledInstance(picture.getWidth(),
							picture.getHeight(), Image.SCALE_AREA_AVERAGING));
					picture.setIcon(imn);
				} else {
					BufferedImage im = null;
					try {
						im = ImageIO.read(getClass().getResource("/image/mle.png"));
					} catch (Exception e) {
					}
					ImageIcon imn = new ImageIcon(new ImageIcon(im).getImage().getScaledInstance(picture.getWidth(),
							picture.getHeight(), Image.SCALE_AREA_AVERAGING));
					picture.setIcon(imn);
				}
			} else {
				picture.setIcon(new ImageIcon(new ImageIcon(rs.getBytes("Student_image")).getImage()
						.getScaledInstance(picture.getWidth(), picture.getHeight(), Image.SCALE_AREA_AVERAGING)));

			}

			data.con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void change() {
		change = new JButton("Change picture");
		change.setBounds(30, 350, 230, 30);
		change.setBackground(Color.DARK_GRAY);
		change.setBorder(BorderFactory.createSoftBevelBorder(10));
		change.setForeground(Color.WHITE);
		change.setFont(new Font("Roman", Font.PLAIN, 16));
		change.setVisible(false);
		change.addActionListener(action);
		container.add(change);

		Cn = new JTextField();
		Cn.setBounds(300, 110, 500, 35);
		Cn.setBackground(Color.white);
		Cn.setForeground(new Color(69, 69, 72));
		Cn.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		Cn.setFont(new Font("Roman", Font.PLAIN, 15));
		Cn.setVisible(false);
		container.add(Cn);

		int bl = 150;
		text = new JTextField[10];
		for (int i = 0; i < text.length; i++) {
			if (!((i == 5) || (i == 4))) {
				text[i] = new JTextField();
				text[i].setBounds(430, bl, 300, 35);
				text[i].setBackground(Color.white);
				text[i].setForeground(new Color(69, 69, 72));
				text[i].setBorder(BorderFactory.createLineBorder(Color.GRAY));
				text[i].setFont(new Font("Roman", Font.PLAIN, 15));
				text[i].setVisible(false);
				container.add(text[i]);
			}
			bl += 50;
		}

		save = new JButton("Save");
		save.setBounds(460, 650, 230, 40);
		save.setBackground(Color.DARK_GRAY);
		save.setBorder(BorderFactory.createSoftBevelBorder(10));
		save.setForeground(Color.WHITE);
		save.setFont(new Font("Roman", Font.PLAIN, 16));
		save.addActionListener(action);
		save.setVisible(false);
		container.add(save);

	}

	private void options() {
		BufferedImage icon = null;
		try {
			icon = ImageIO.read(getClass().getResource("/image/closer2.png"));

		} catch (Exception e) {

		}
		back = new JButton(new ImageIcon(icon));
		back.setBounds(1000, 5, 32, 32);
		back.setOpaque(false);
		back.setBorder(BorderFactory.createSoftBevelBorder(10));
		container.add(back);

		sep = new JSeparator();
		sep.setBounds(0, 50, 1045, 1);
		sep.setForeground(new Color(240, 240, 240));
		container.add(sep);

		detail = new JLabel();
		detail.setBounds(10, 5, 250, 45);
		detail.setForeground(Color.DARK_GRAY);
		detail.setFont(new Font("Roman", Font.BOLD, 15));
		container.add(detail);

		picture = new JLabel();
		picture.setBounds(30, 80, 230, 260);
		picture.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		container.add(picture);

		Schmasys = new JLabel("SCHMASYS PRO");
		Schmasys.setBounds(290, 55, 150, 35);
		Schmasys.setForeground(Color.DARK_GRAY);
		Schmasys.setFont(new Font("Roman", Font.BOLD, 17));
		container.add(Schmasys);

		BufferedImage edi = null;
		try {
			edi = ImageIO.read(getClass().getResource("/image/edit1.png"));

		} catch (Exception e) {

		}

		edit = new JButton(new ImageIcon(edi));
		edit.setBounds(950, 55, 32, 32);
		edit.setBorder(BorderFactory.createSoftBevelBorder(10));
		edit.setOpaque(false);
		edit.addActionListener(action);
		container.add(edit);

		BufferedImage prints = null;
		try {
			prints = ImageIO.read(getClass().getResource("/image/print1.png"));

		} catch (Exception e) {

		}
		print = new JButton(new ImageIcon(prints));
		print.setBounds(1000, 55, 32, 32);
		print.setBorder(BorderFactory.createSoftBevelBorder(10));
		print.setOpaque(false);
		print.addActionListener(action);
		container.add(print);

		JPanel k = new JPanel();
		k.setBounds(290, 90, 50, 5);
		k.setBackground(new Color(0, 27, 61));
		container.add(k);

		int bl = 150;
		lb = new JLabel[10];
		for (int i = 0; i < lb.length; i++) {
			lb[i] = new JLabel(list[i]);
			lb[i].setBounds(290, bl, 121, 35);
			lb[i].setForeground(new Color(69, 69, 72));
			lb[i].setFont(new Font("Roman", Font.PLAIN, 15));
			container.add(lb[i]);
			bl += 50;
		}

		SN = new JLabel();
		SN.setBounds(300, 110, 500, 35);
		SN.setForeground(Color.DARK_GRAY);
		SN.setFont(new Font("Roman", Font.BOLD, 18));
		container.add(SN);

		SM = new JLabel();
		SM.setBounds(430, 150, 300, 35);
		SM.setForeground(new Color(69, 69, 72));
		SM.setFont(new Font("Roman", Font.BOLD, 16));
		container.add(SM);

		SE = new JLabel();
		SE.setBounds(430, 200, 300, 35);
		SE.setForeground(new Color(69, 69, 72));
		SE.setFont(new Font("Roman", Font.BOLD, 16));
		container.add(SE);

		SP = new JLabel();
		SP.setBounds(430, 250, 300, 35);
		SP.setForeground(new Color(69, 69, 72));
		SP.setFont(new Font("Roman", Font.BOLD, 16));
		container.add(SP);

		PP = new JLabel();
		PP.setBounds(430, 300, 300, 35);
		PP.setForeground(new Color(69, 69, 72));
		PP.setFont(new Font("Roman", Font.BOLD, 16));
		container.add(PP);

		SCH = new JLabel();
		SCH.setBounds(430, 350, 500, 35);
		SCH.setForeground(new Color(69, 69, 72));
		SCH.setFont(new Font("Roman", Font.BOLD, 16));
		container.add(SCH);

		SC = new JLabel();
		SC.setBounds(430, 400, 300, 35);
		SC.setForeground(new Color(69, 69, 72));
		SC.setFont(new Font("Roman", Font.BOLD, 16));
		container.add(SC);

		SG = new JLabel();
		SG.setBounds(430, 450, 300, 35);
		SG.setForeground(new Color(69, 69, 72));
		SG.setFont(new Font("Roman", Font.BOLD, 16));
		container.add(SG);

		SB = new JLabel();
		SB.setBounds(430, 500, 300, 35);
		SB.setForeground(new Color(69, 69, 72));
		SB.setFont(new Font("Roman", Font.BOLD, 16));
		container.add(SB);

		SL = new JLabel();
		SL.setBounds(430, 550, 300, 35);
		SL.setForeground(new Color(69, 69, 72));
		SL.setFont(new Font("Roman", Font.BOLD, 16));
		container.add(SL);

		SA = new JLabel();
		SA.setBounds(430, 600, 300, 35);
		SA.setForeground(new Color(69, 69, 72));
		SA.setFont(new Font("Roman", Font.BOLD, 16));
		container.add(SA);

	}

	private void container() {
		container = new JPanel();
		container.setPreferredSize(new Dimension(0, 710));
		container.setLayout(null);
		container.setBackground(Color.white);
		MainContainer = new JScrollPane(container);
		MainContainer.setBounds(0, 0, 1050, 670);
		MainContainer.setBorder(BorderFactory.createSoftBevelBorder(10));
		add(MainContainer);
	}

	private void design() {

		setVisible(false);
		setLayout(null);
		setBackground(Color.white);
		setBounds(0, 0, 1045, 670);
	}

	private class actionListener implements ActionListener {
		public void actionPerformed(ActionEvent ar) {

			if (ar.getSource() == print) {

				print(container);

			}

			else if (ar.getSource() == edit) {

				SM.setVisible(false);
				SE.setVisible(false);
				SP.setVisible(false);
				PP.setVisible(false);
				SG.setVisible(false);
				SB.setVisible(false);
				SL.setVisible(false);
				SA.setVisible(false);
				SN.setVisible(false);
				Cn.setVisible(true);
				change.setVisible(true);
				save.setVisible(true);
				for (int i = 0; i < text.length; i++) {
					if (!((i == 5) || (i == 4)))
						text[i].setVisible(true);
				}
			} else if (ar.getSource() == change) {

				JFileChooser file = new JFileChooser();
				FileNameExtensionFilter fil = new FileNameExtensionFilter("Image File", "PNG", "JPG");
				file.setFileFilter(fil);
				file.showDialog(null, "change picture");
				if (!(file.getSelectedFile() == null)) {
					File f = file.getSelectedFile();
					filePath = f.getAbsolutePath();

					picture.setIcon(new ImageIcon(new ImageIcon(filePath).getImage()
							.getScaledInstance(picture.getWidth(), picture.getHeight(), Image.SCALE_AREA_AVERAGING)));
				}
			} else if (ar.getSource() == save) {

				try {
					data.classForname();
					data.con = DriverManager.getConnection("jdbc:sqlite:students.db");
					data.st = data.con.createStatement();
					if (!(filePath == null)) {
						FileInputStream fis = new FileInputStream(filePath);
						ByteArrayOutputStream bos = new ByteArrayOutputStream();
						byte[] buf = new byte[1024];
						for (int readnum; (readnum = fis.read(buf)) != -1;) {
							bos.write(buf, 0, readnum);
						}
						fis.close();

						String update = "update student set Student_name='" + Cn.getText() + "', Mother_name ='"
								+ text[0].getText() + "' ,Student_Email ='" + text[1].getText() + "',"
								+ " Student_phone ='" + text[2].getText() + "',Parent_phone='" + text[3].getText()
								+ "',  Student_gender='" + text[6].getText() + "',Birth_Date='" + text[7].getText()
								+ "', " + "Location_Birth='" + text[8].getText() + "',Student_address='"
								+ text[9].getText() + "', Student_image=?   where Student_name='" + sname
								+ "' AND Student_class='" + sclass + "'";
						PreparedStatement pr = data.con.prepareStatement(update);
						pr.setBytes(1, bos.toByteArray());
						pr.executeUpdate();
						filePath = null;
					} else {

						String update = "update student set Student_name='" + Cn.getText() + "', Mother_name ='"
								+ text[0].getText() + "' ,Student_Email ='" + text[1].getText() + "',"
								+ " Student_phone ='" + text[2].getText() + "',Parent_phone='" + text[3].getText()
								+ "',  Student_gender='" + text[6].getText() + "',Birth_Date='" + text[7].getText()
								+ "', " + "Location_Birth='" + text[8].getText() + "',Student_address='"
								+ text[9].getText() + "' where Student_name='" + sname + "' AND Student_class='"
								+ sclass + "'";
						data.st = data.con.createStatement();
						data.st.executeUpdate(update);

					}
					SN.setText(Cn.getText());
					SM.setText(text[0].getText());
					SE.setText(text[1].getText());
					SP.setText(text[2].getText());
					PP.setText(text[3].getText());
					SG.setText(text[6].getText());
					SB.setText(text[7].getText());
					SL.setText(text[8].getText());
					SA.setText(text[9].getText());
					detail.setText(Cn.getText().toUpperCase().charAt(0)
							+ Cn.getText().substring(1, Cn.getText().indexOf(" ")) + " Details");
					String up = "update records set Student_name='" + Cn.getText() + "' where Student_name='" + sname
							+ "';";
					data.st.executeUpdate(up);
					data.con.close();
				} catch (Exception e) {
					System.out.println(e);
				}

				{
					SM.setVisible(true);
					SE.setVisible(true);
					SP.setVisible(true);
					PP.setVisible(true);
					SG.setVisible(true);
					SB.setVisible(true);
					SL.setVisible(true);
					SA.setVisible(true);
					SN.setVisible(true);
					change.setVisible(false);
					save.setVisible(false);
					Cn.setVisible(false);
					for (int i = 0; i < text.length; i++) {
						if (!((i == 5) || (i == 4)))
							text[i].setVisible(false);
					}

				}
			}

		}

		private void print(JPanel container) {

			SM.setVisible(true);
			SE.setVisible(true);
			SP.setVisible(true);
			PP.setVisible(true);
			SG.setVisible(true);
			SB.setVisible(true);
			SL.setVisible(true);
			SA.setVisible(true);
			SN.setVisible(true);
			change.setVisible(false);
			save.setVisible(false);
			Cn.setVisible(false);
			for (int i = 0; i < text.length; i++) {
				if (!((i == 5) || (i == 4)))
					text[i].setVisible(false);
			}

			PrinterJob prin = PrinterJob.getPrinterJob();
			prin.setJobName("test");
			prin.setPrintable(new Printable() {
				public int print(Graphics graph, PageFormat pf, int pageIndex) throws PrinterException {
					if (pageIndex > 0) {
						return Printable.NO_SUCH_PAGE;
					}
					Graphics2D gr2d = (Graphics2D) graph;
					gr2d.translate(pf.getImageableX() * 2, pf.getImageableY() * 2);
					gr2d.scale(0.7, 0.7);
					container.paint(gr2d);

					return Printable.PAGE_EXISTS;
				}
			});
			boolean rtrslt = prin.printDialog();
			if (rtrslt) {
				try {
					prin.print();
					JOptionPane.showMessageDialog(null, "Print Successfully", "print", JOptionPane.INFORMATION_MESSAGE);
				} catch (PrinterException e) {
					System.out.println(e);
				}
			}
		}

	}
}
