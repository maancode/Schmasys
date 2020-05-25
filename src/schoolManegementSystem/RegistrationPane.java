package schoolManegementSystem;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.wannawork.jcalendar.JCalendarPanel;

public class RegistrationPane extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel image;
	private JPanel pane, pane1, pane2, pic;
	private JTextField texts[];
	private JTextArea txt;
	private String textnames[] = { "Student name", "Mother name", "Student phone", "Parent phone", "Student Email",
			"Birth Date", "Location Birth" };
	private JRadioButton male, female;
	public JComboBox<String> box;
	private JButton btn;
	public JLabel pic1, reg;
	private JButton IH;
	public  JButton IH1;
	private String classes;
	private String gender[] = new String[1];
	private JButton date;
	private JPanel dates;
	public String s,sa;

	keyListener key = new keyListener();
	focusListener focus = new focusListener();
	itemListener item = new itemListener();
	actionListener action = new actionListener();

	public RegistrationPane() {

		RegistraBum();
		texts();
		panels();
		background();

		pane();

	}

	private void RegistraBum() {
		btn = new JButton("SAVE");
		btn.setBounds(520, 540, 300, 40);
		btn.setBorder(BorderFactory.createSoftBevelBorder(10));
		btn.setBackground(Color.WHITE);
		btn.setFont(new Font("Calibri", Font.BOLD, 25));
		btn.addActionListener(action);

	}

	private void texts() {
		pane2 = new JPanel();
		pane2.setOpaque(false);
		pane2.setLayout(null);
		pane2.setBounds(80, 40, 900, 600);
		JCalendarPanel dd = new JCalendarPanel();
		dd.setBounds(0, 0, 200, 200);
		dates = new JPanel();
		dates.setBounds(540 + 50 + 60 + 30, 340, 200, 235);
		dates.setBackground(Color.white);
		dates.setLayout(null);
		dates.setVisible(false);

		JButton ok = new JButton("OK");
		JButton cancel = new JButton("Cancel");

		cancel.setBackground(new Color(0, 162, 232));
		cancel.setBounds(105, 205, 85, 25);
		cancel.setBorder(BorderFactory.createSoftBevelBorder(10));
		cancel.setForeground(Color.white);
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dates.setVisible(false);

			}

		});
		dates.add(ok);
		dates.add(cancel);

		dates.add(dd);

		pane2.add(dates);

		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResource("/image/date.png"));

		} catch (Exception e) {
		}

		date = new JButton(new ImageIcon(image));
		date.setBorder(BorderFactory.createSoftBevelBorder(10));
		date.setOpaque(false);
		date.setBounds(470 + 350 + 10, 310, 32, 32);
		date.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				for (int i = 0; i < texts.length; i++) {

					texts[i].setEnabled(false);
					btn.setEnabled(false);
				}
				dates.setVisible(true);
			}
		});
		pane2.add(date);
		int Bl[] = { 230, 290, 350, 410, 470, 310, 390 };
		int Ll[] = { 30, 30, 30, 30, 30, 470, 470 };
		texts = new JTextField[7];
		for (int i = 0; i < texts.length; i++) {
			texts[i] = new JTextField();
			texts[i].setText(textnames[i]);
			texts[i].setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
			texts[i].setFont(new Font("ARIAL", Font.PLAIN, 14));
			texts[i].setBounds(Ll[i], Bl[i], 400, 30);
			texts[i].setForeground(Color.gray);
			texts[i].addFocusListener(focus);
			texts[i].addKeyListener(key);
			if (i == 0) {

			} else if (i == 1) {

			} else if (i == 2) {

			} else if (i == 3) {

			} else if (i == 5) {
				texts[i].setForeground(Color.white);
				texts[i].setBackground(new Color(0, 162, 232));
				texts[i].setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
				texts[i].setBounds(Ll[i], Bl[i], 350, 30);

			} else if (i == 6) {
				texts[i].setForeground(Color.white);
				texts[i].setBackground(new Color(0, 162, 232));
				texts[i].setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));

			}
			pane2.add(texts[i]);
		}

		ok.setBackground(new Color(0, 162, 232));
		ok.setBounds(10, 205, 85, 25);
		ok.setBorder(BorderFactory.createSoftBevelBorder(10));
		ok.setForeground(Color.white);
		ok.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String date = dd.toString();
				texts[5].setText(date);
				texts[5].setForeground(Color.white);
				dates.setVisible(false);
				for (int i = 0; i < texts.length; i++) {
					texts[i].setEnabled(true);
					btn.setEnabled(true);
				}
			}

		});

		box = new JComboBox<String>();
		box.setBounds(470, 150, 400, 30);
		box.setBorder(BorderFactory.createSoftBevelBorder(10));
		box.setBackground(new Color(0, 162, 232));
		box.setFont(new Font("ARIAL", Font.PLAIN, 15));
		box.setForeground(Color.white);

		box.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String ss = box.getSelectedItem().toString();
				classes = ss;

			}

		});

//			

		male = new JRadioButton("Male");
		female = new JRadioButton("Female");

		male.setBounds(470, 460, 100, 30);
		female.setBounds(575, 460, 100, 30);
		male.setForeground(Color.white);
		female.setForeground(Color.white);
		male.setFont(new Font("Calibri", Font.BOLD, 16));
		female.setFont(new Font("Calibri", Font.BOLD, 16));
		male.setBackground(new Color(0, 162, 232));
		female.setBackground(new Color(0, 162, 232));
		male.setBorder(BorderFactory.createSoftBevelBorder(10));
		female.setBorder(BorderFactory.createSoftBevelBorder(10));
		female.addItemListener(item);

		male.addItemListener(item);

		BufferedImage tog = null;

		try {
			tog = ImageIO.read(getClass().getResource("/image/student.png"));

		} catch (Exception e) {

		}

		pic1 = new JLabel();
		pic1.setBounds(2, 2, 165, 195);
		pic1.setIcon(new ImageIcon(new ImageIcon(tog).getImage().getScaledInstance(pic1.getWidth(), pic1.getHeight(),
				Image.SCALE_AREA_AVERAGING)));

		pic = new JPanel();
		pic.setBounds(15, 15, 170, 200);
		pic.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY));

		reg = new JLabel("Registration");
		reg.setBounds(470, 40, 200, 50);
		reg.setForeground(Color.white);
		reg.setFont(new Font("Calibri", Font.BOLD, 35));
		pane2.add(box);
		pic.setLayout(null);
		pic.add(pic1);
		pane2.add(reg);
		pane2.add(pic);

		IH1 = new JButton("Take Image");
		IH1.setBounds(200, 130, 230, 30);
		IH1.setBackground(new Color(0, 162, 232));
		IH1.setBorder(BorderFactory.createSoftBevelBorder(10));
		IH1.setFont(new Font("Calibri", Font.PLAIN, 16));
		IH1.setForeground(Color.white);
		
		pane2.add(IH1);

		IH = new JButton("Upload Image");
		IH.setBounds(200, 170, 230, 30);
		IH.setBackground(new Color(0, 162, 232));
		IH.setBorder(BorderFactory.createSoftBevelBorder(10));
		IH.setFont(new Font("Calibri", Font.PLAIN, 16));
		IH.setForeground(Color.white);
		IH.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					JFileChooser ch = new JFileChooser();
					FileNameExtensionFilter fil = new FileNameExtensionFilter("Image File", "PNG", "JPG");
					ch.setFileFilter(fil);
					ch.showDialog(null, "add image");
					File file = ch.getSelectedFile();
					s = file.getAbsolutePath();
					System.out.println(s);
					pic1.setIcon(new ImageIcon(new ImageIcon(s).getImage().getScaledInstance(pic1.getWidth(),
							pic1.getHeight(), Image.SCALE_AREA_AVERAGING)));
				} catch (Exception e) {

				}

			}

		});

		pane2.add(IH);
		ButtonGroup group = new ButtonGroup();
		group.add(female);
		group.add(male);

		pane2.add(female);
		pane2.add(male);

		txt = new JTextArea();
		txt.setBounds(470, 200, 400, 70);
		txt.setBorder(BorderFactory.createMatteBorder(1, 1, 2, 1, Color.gray));
		txt.setBackground(new Color(240, 240, 240));
		txt.setText("Student Address");
		txt.setFont(new Font("ARIAL", Font.PLAIN, 15));
		txt.setForeground(Color.gray);
		txt.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent a) {
				if (txt.getText().equals("Student Address") || txt.getText().length() == 0) {
					txt.setText("");
					txt.setFont(new Font("ARIAL", Font.PLAIN, 15));
					txt.setForeground(Color.black);
				}

			}

			public void focusLost(FocusEvent a) {
				if (txt.getText().equals("Student Address") || txt.getText().length() == 0) {
					txt.setText("Student Address");
					txt.setFont(new Font("ARIAL", Font.PLAIN, 15));
					txt.setForeground(Color.gray);
				}
			}
		});

		pane2.add(txt);
		pane2.add(btn);

		add(pane2);
	}

	public void database() {
		Databases data1 = new Databases();
		String str = null;
		String strq[] = new String[72];
		String s[] = new String[strq.length];

		try {
			data1.classForname();
			data1.con = DriverManager.getConnection("jdbc:sqlite:class.db");
			data1.st = data1.con.createStatement();
			String query = "select * from classrooms";
			ResultSet rs = data1.st.executeQuery(query);

			while (rs.next()) {
				int i = rs.getRow();

				str = rs.getString("classname");
//		
				strq[i] = str;
				s[i - 1] = strq[i];
//				System.out.println(i);

			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				data1.st.close();
				data1.con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private void panels() {
		BufferedImage imge = null;

		try {
			imge = ImageIO.read(getClass().getResource("/image/Untitled2.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}

		image = new JLabel(new ImageIcon(imge));
		image.setBounds(0, 0, 900, 600);

		pane1 = new JPanel();
		pane1.setOpaque(true);
		pane1.setBackground(Color.black);
		pane1.setLayout(null);
		pane1.setBounds(80, 40, 900, 600);

		pane1.add(image);
		add(pane1);

		pane = new JPanel();
		pane.setBackground(new Color(240, 240, 240));
		pane.setBounds(0, 0, 1045, 690);
		add(pane);
	}

	private void background() {
//		BufferedImage image = null;
//		try {
//			image = ImageIO.read(getClass().getResource("/image/Untitled.png"));
//
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//
//		background = new JLabel(new ImageIcon(image));
//		background.setBounds(0, 0, 1045, 690);
//		add(background);

	}

	private void pane() {

		setBounds(280, 30, 1045, 690);
		setBackground(new Color(240, 240, 240));
		setVisible(false);
		setLayout(null);

	}

	private class keyListener implements KeyListener {

		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void keyTyped(KeyEvent arg0) {
			if (arg0.getSource() == texts[2] || arg0.getSource() == texts[3]) {
				char c = arg0.getKeyChar();
				if (!(Character.isDigit(c))) {
					getToolkit().beep();
					arg0.consume();

				}

			}

		}

	}

	private class focusListener implements FocusListener {

		public void focusGained(FocusEvent arg0) {
			for (int i = 0; i < texts.length; i++) {
				if (arg0.getSource() == texts[i]) {
					if (texts[i].getText().equals(textnames[i])) {

						texts[i].setText("");
						texts[i].setFont(new Font("Calibri", Font.PLAIN, 16));
						texts[i].setForeground(Color.black);
						if (i == 5 || i == 6) {
							texts[i].setForeground(Color.white);
						}
					}
				}
			}
		}

		public void focusLost(FocusEvent arg0) {
			for (int i = 0; i < texts.length; i++) {
				if (arg0.getSource() == texts[i]) {
					if (texts[i].getText().equals(textnames[i]) || texts[i].getText().length() == 0) {

						texts[i].setText(textnames[i]);
						texts[i].setForeground(Color.gray);
						texts[i].setFont(new Font("ARIAL", Font.PLAIN, 14));
						if (i == 5 || i == 6) {
							texts[i].setForeground(Color.white);
						}
					}
				}
			}
		}
	}

	private class itemListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			if (arg0.getItemSelectable() == male) {
				String ss = "male";
				gender[0] = ss;
			} else if (arg0.getItemSelectable() == female) {
				String ss = "female";
				gender[0] = ss;
			}
		}

	}

	private class actionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {

			if (!((texts[0].getText().equals(textnames[0]) || texts[0] == null)
					| (texts[1].getText().equals(textnames[1]) || texts[1] == null)
					| (texts[3].getText().equals(textnames[3]) || texts[3] == null)
					| (txt.getText().equals("Student Address"))
					| (texts[5].getText().equals(textnames[5]) || texts[5] == null)
					| (texts[6].getText().equals(textnames[6]) || texts[5] == null) | (gender[0] == null)
					| (classes == null))) {

				if (texts[2].getText().equals(textnames[2]))
					texts[2].setText("null");
				if (texts[4].getText().equals(textnames[4]))
					texts[4].setText("null");

				Databases data1 = new Databases();

				try {
					data1.classForname();
					data1.con = DriverManager.getConnection("jdbc:sqlite:students.db");
					String ss = s;
					if (!(ss == null)) {

						FileInputStream fis = new FileInputStream(ss);
						ByteArrayOutputStream bos = new ByteArrayOutputStream();
						byte[] buf = new byte[1024];
						for (int readnum; (readnum = fis.read(buf)) != -1;) {
							bos.write(buf, 0, readnum);
						}
						fis.close();

						String insert = "insert into student(Student_name,Mother_name,Student_phone,Parent_phone,Student_Email,Student_class,Student_address,Birth_Date,Location_Birth,Student_gender,Student_image )values ('"
								+ texts[0].getText() + "','" + texts[1].getText() + "','" + texts[2].getText() + "','"
								+ texts[3].getText() + "','" + texts[4].getText() + "','" + classes + "','"
								+ txt.getText() + "','" + texts[5].getText() + "','" + texts[6].getText() + "','"
								+ gender[0] + "',?);";
						PreparedStatement pr = data1.con.prepareStatement(insert);
						pr.setBytes(1, bos.toByteArray());
						pr.executeUpdate();
						JOptionPane.showMessageDialog(null, "The Student is registrated successFully", "Registration",
								JOptionPane.PLAIN_MESSAGE);
						box.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
						if(sa != null) {
							File f = new File(sa);
							f.delete();
							sa = null;
						}

					} else {
						String insert = "insert into student(Student_name,Mother_name,Student_phone,Parent_phone,Student_Email,Student_class,Student_address,Birth_Date,Location_Birth,Student_gender,Student_image )values ('"
								+ texts[0].getText() + "','" + texts[1].getText() + "','" + texts[2].getText() + "','"
								+ texts[3].getText() + "','" + texts[4].getText() + "','" + classes + "','"
								+ txt.getText() + "','" + texts[5].getText() + "','" + texts[6].getText() + "','"
								+ gender[0] + "',?);";

						data1.st = data1.con.createStatement();
						data1.st.executeUpdate(insert);
						JOptionPane.showMessageDialog(null, "The Student is registrated successFully", "Registration",
								JOptionPane.PLAIN_MESSAGE);
						box.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));

					}

					try {
						Databases db = new Databases();
						String url = "jdbc:sqlite:messages.db";
						db.classForname();
						db.con = DriverManager.getConnection(url);
						db.st = db.con.createStatement();

						db.st.executeUpdate("insert into save (Student_name) values('" + texts[0].getText() + "')");

						db.con.close();
					} catch (Exception w) {
						System.out.println(w);
					}

					for (int i = 0; i < texts.length; i++) {

						texts[i].setText(textnames[i]);
						texts[i].setForeground(Color.gray);
						texts[i].addFocusListener(focus);
						if (i == 5 || i == 6)
							texts[i].setForeground(Color.white);

					}
					txt.setText("Student Address");
					txt.setForeground(Color.gray);
					classes = null;

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "checkout your details like ( ' ) ", "Schmasys",
							JOptionPane.WARNING_MESSAGE);
				} finally {
					try {

						data1.con.close();
					} catch (Exception e) {
					}
				}

			} else if (classes == null) {
				box.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
			} else
				JOptionPane.showMessageDialog(null, "please fill the student detials first", "Schmasys",
						JOptionPane.WARNING_MESSAGE);

		}

	}
}
