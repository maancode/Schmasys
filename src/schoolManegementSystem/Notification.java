package schoolManegementSystem;

import java.awt.Color;
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
import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class Notification extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel container;
	private JPanel listClasses, serch;
	private JPanel tiLiPar, chatpart, chatbottom;
	private JTextField ser;
	private JScrollPane spane;
	private JLabel logo, name, image, names, email, classes, cap;
	private DefaultListModel<ImgNtext> m;
	private JList<ImgNtext> li;
	private JToggleButton ch;
	private JScrollPane tPane;
	private JTextArea tArea;
	private JButton files, send;
	private JScrollPane chPane;
	private JEditorPane jEditorPane;
	private JTextArea taker;
	private JPanel placeHolder;
	private JLabel icon, lb, lb1;
	private String Student;
	private String mail;
	private String path;
	private boolean checker;
	private JPanel covers;
	StudentProfile sp = new StudentProfile();

	public Notification() {
		design();
		try {
			Databases dc1 = new Databases();
			dc1.classForname();
			dc1.con = DriverManager.getConnection("jdbc:sqlite:students.db");
			dc1.st = dc1.con.createStatement();
			String query = "select Student_name from student";
			ResultSet rs = dc1.st.executeQuery(query);
			checker = rs.next();

			dc1.con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		if(checker) {
			add(container);

			add(sp);
		panels();
		components();
		lists();
		sending();
		chattingField();
		}
		else
		{
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
		lb.setBounds(400,120,256,256);
		
		JLabel lb2 = new JLabel();
		lb2.setBounds(325,390,500,45);
		lb2.setText("Student notification is an empty");
		lb2.setFont(new Font("Roman",Font.PLAIN,30));
		lb2.setForeground(Color.gray);
		
		
		
		
		covers = new JPanel();
		covers.setBounds(0, 0, 1045, 690);
		covers.setBackground(Color.white);
		covers.setLayout(null);
		covers.add(lb);
		covers.add(lb2);
		add(covers);
		
	}

	private void chattingField() {

		jEditorPane = new JEditorPane();
		jEditorPane.setEditable(false);

		jEditorPane.setContentType("text/html");

		chPane = new JScrollPane(jEditorPane);

		chPane.setBounds(305, 55, 735, 560);
		chPane.setBorder(BorderFactory.createSoftBevelBorder(10));
		chPane.setVisible(false);
		container.add(chPane);

		placeHolder = new JPanel();
		placeHolder.setBounds(305, 55, 735, 643);
		placeHolder.setBorder(BorderFactory.createSoftBevelBorder(10));
		placeHolder.setVisible(true);
		placeHolder.setLayout(null);
		placeHolder.setBackground(new Color(0, 27, 61, 165));
		container.add(placeHolder);

		BufferedImage tog = null;

		try {
			tog = ImageIO.read(getClass().getResource("/image/senf.png"));

		} catch (Exception e) {

		}
		icon = new JLabel();
		icon.setBounds(255, 90, 256, 256);
		icon.setIcon(new ImageIcon(new ImageIcon(tog).getImage().getScaledInstance(icon.getWidth(), icon.getHeight(),
				Image.SCALE_AREA_AVERAGING)));
		placeHolder.add(icon);

		lb = new JLabel("Schmasys notification");
		lb.setBounds(205, 370, 600, 60);
		lb.setFont(new Font("Arial", Font.PLAIN, 35));
		lb.setForeground(Color.LIGHT_GRAY);
		placeHolder.add(lb);

		lb1 = new JLabel("You can send any notifications here via student Email account");
		lb1.setBounds(15, 420, 700, 50);
		lb1.setFont(new Font("Roman", Font.PLAIN, 25));
		lb1.setForeground(Color.LIGHT_GRAY);
		placeHolder.add(lb1);

	}

	private void sending() {

		tArea = new JTextArea();
		tArea.setFont(new Font("Roman", Font.PLAIN, 16));
		tArea.setForeground(Color.LIGHT_GRAY);
		tArea.setWrapStyleWord(true);
		tArea.setBackground(new Color(97, 146, 194));
		tArea.setLineWrap(true);
		tArea.setText("Type a message");
		tArea.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent a) {
				if (a.getSource() == tArea) {
					if (tArea.getText().equals("Type a message") || tArea.getText().length() == 0) {
						tArea.setText("");
						tArea.setForeground(Color.white);

					}
				}

			}

			public void focusLost(FocusEvent a) {
				if (a.getSource() == tArea) {
					if (tArea.getText().equals("Type a message") || tArea.getText().length() == 0) {
						tArea.setText("Type a message");
						tArea.setForeground(Color.LIGHT_GRAY);

					}
				}

			}

		});
		tArea.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {

				if (tArea.getText().length() + 1 >= 134) {
					tPane.setBounds(60, 5, 625, 73);
					chatbottom.setBounds(300, 590, 745, 100);
					send.setBounds(635 + 60, 42, 33, 33);
					files.setBounds(15, 42, 33, 33);
				} else {
					chatbottom.setBounds(300, 620, 745, 70);
					tPane.setBounds(60, 5, 625, 43);
					send.setBounds(635 + 60, 10, 33, 33);
					files.setBounds(15, 10, 33, 33);
				}

			}

		});
		BufferedImage tog = null;

		try {
			tog = ImageIO.read(getClass().getResource("/image/send2.png"));

		} catch (Exception e) {

		}

		send = new JButton();
		send.setBounds(635 + 60, 10, 33, 33);
		send.setIcon(new ImageIcon(new ImageIcon(tog).getImage().getScaledInstance(send.getWidth(), send.getHeight(),
				Image.SCALE_AREA_AVERAGING)));
		send.setBorder(BorderFactory.createSoftBevelBorder(10));
		send.setOpaque(false);

		send.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {

				Thread t = new Thread() {
					@Override
					public void run() { // override the run() to specify the running behavior

						if (!(tArea.getText().equals("Type a message") || tArea.getText() == null)) {
							int size = 30;
							if (tArea.getText().length() >= 45) {
								mail = email.getText();
								String text = tArea.getText();
								taker.append("<div style=\"  width:" + 430
										+ "px;color: white; height:auto; margin:5px; background:blue;\"><p style=\"padding:5px; margin:5px; font-family:Arial; font-size: 16px; left:auto; top:auto;position: relative;\">"
										+ tArea.getText() + "</p></div>");
								jEditorPane.setText("<html><body style=\"padding:0; margin:0;\">" + taker.getText()
										+ "</body></html>");
								email(text);
							} else {
								mail = email.getText();
								String text = tArea.getText();
								taker.append("<div style=\"  width:" + ((tArea.getText().length() * 9) + size)
										+ "px;color: white; height:auto; margin:5px; background:blue;\"><p style=\"padding:5px; margin:5px; font-family:Arial; font-size: 16px; left:auto; top:auto;position: relative;\">"
										+ tArea.getText() + "</p></div>");
								jEditorPane.setText("<html><body style=\"padding:0; margin:0;\">" + taker.getText()
										+ "</body></html>");
								email(text);
							}

						}

						tArea.setText("Type a message");
						tArea.setForeground(Color.LIGHT_GRAY);

					}
				};
				t.start();

			}
		});

		chatbottom.add(send);

		taker = new JTextArea();

		try {
			tog = ImageIO.read(getClass().getResource("/image/cll.png"));

		} catch (Exception e) {

		}

		files = new JButton();
		files.setBounds(15, 10, 33, 33);
		files.setIcon(new ImageIcon(new ImageIcon(tog).getImage().getScaledInstance(files.getWidth(), files.getHeight(),
				Image.SCALE_AREA_AVERAGING)));
		files.setBorder(BorderFactory.createSoftBevelBorder(10));
		files.setOpaque(false);
		files.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					JFileChooser ch = new JFileChooser();
					ch.showDialog(null, "Send");
					File file = ch.getSelectedFile();
					path = file.getAbsolutePath();
					

					Thread t = new Thread() {
						public void run() {
							mail = email.getText();
							taker.append("<div style=\"  width:" + 430
									+ "px;color: white; height:auto; margin:5px; background:GRAY;\"><p style=\"padding:5px; margin:5px; font-family:Arial; font-size: 16px; left:auto; top:auto;position: relative;\">"
									+ "File path: " + path + "</p></div>");
							jEditorPane.setText(
									"<html><body style=\"padding:0; margin:0;\">" + taker.getText() + "</body></html>");
							
							file_sender(path);
						}
					};
					t.start();
				} catch (Exception e) {
				}
			}

		});
		files.setToolTipText("send files");
		chatbottom.add(files);

		tPane = new JScrollPane(tArea);
		tPane.setBounds(60, 5, 625, 43);
		tPane.setOpaque(false);

		tPane.setBorder(BorderFactory.createSoftBevelBorder(10));
		chatbottom.add(tPane);
	}

	private void file_sender(String path) {

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
			mm.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
			mm.setSubject("schmasys attachment");

			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setText("This is message body");

			MimeBodyPart messageBodyPart2 = new MimeBodyPart();

			String filename = path;// change accordingly
			DataSource source = new FileDataSource(filename);
			messageBodyPart2.setDataHandler(new DataHandler(source));
			messageBodyPart2.setFileName("file from your school");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart1);
			multipart.addBodyPart(messageBodyPart2);
			mm.setContent(multipart);

			Transport.send(mm);

			try {
				Databases db = new Databases();
				String url = "jdbc:sqlite:messages.db";
				db.classForname();
				db.con = DriverManager.getConnection(url);
				db.st = db.con.createStatement();

				db.st.executeUpdate("update save set chatting_save='" + taker.getText() + "'  WHERE Student_name ='"
						+ Student + "'");

				db.con.close();
			} catch (Exception w) {
				System.out.println(w);
			}

			JOptionPane.showMessageDialog(null, "sent Successfully", "Email", JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "There is no internet connection\nPlease connect to the internert",
					"internet warring", JOptionPane.WARNING_MESSAGE);
		}

	}

	private void email(String text) {

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
			mm.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
			mm.setSubject("schmasys notification from your school");
			mm.setText(text);

			Transport.send(mm);
			try {
				Databases db = new Databases();
				String url = "jdbc:sqlite:messages.db";
				db.classForname();
				db.con = DriverManager.getConnection(url);
				db.st = db.con.createStatement();

				db.st.executeUpdate("update save set chatting_save='" + taker.getText() + "'  WHERE Student_name ='"
						+ Student + "'");

				db.con.close();
			} catch (Exception w) {
				System.out.println(w);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "There is no internet connection\nPlease connect to the internert",
					"internet warring", JOptionPane.WARNING_MESSAGE);

		}

	}

	private void lists() {
		m = new DefaultListModel<ImgNtext>();

		li = new JList<ImgNtext>();
		li.setFont(new Font("Roman", Font.BOLD, 12));
		li.setBounds(0, 5, 200, 25);
		li.setForeground(Color.DARK_GRAY);
		li.setCellRenderer(new Randerer());

		openchat();

		li.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				
				call_Student();
				tArea.setText("Type a message");
				placeHolder.setVisible(false);
				tArea.setForeground(Color.LIGHT_GRAY);
				chPane.setVisible(true);
				chatbottom.setVisible(true);

			}

		});

		li.setModel(m);

		spane = new JScrollPane(li);
		spane.setBounds(0, 0, 300, 576);
		spane.setBackground(Color.gray);
		spane.setBorder(BorderFactory.createSoftBevelBorder(10));
		listClasses.add(spane);

	}

	private void call_Student() {
		String name = ((ImgNtext) li.getSelectedValue()).getName();
		Student = name;
		try {
			Databases db = new Databases();
			db.classForname();
			db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
			db.st = db.con.createStatement();
			ResultSet rs = db.st.executeQuery("select leadername from classrooms where leadername='" + name + "'");
			cap.setVisible(rs.next());
			db.con.close();
		} catch (Exception ew) {
			System.out.print(ew);
		}

		names.setText(name);
		try {
			Databases db = new Databases();
			String url = "jdbc:sqlite:students.db";
			db.classForname();
			db.con = DriverManager.getConnection(url);
			db.st = db.con.createStatement();
			ResultSet rss = db.st.executeQuery(
					"SELECT Student_Email,Student_class, Student_image , Student_gender from student WHERE Student_name ='"
							+ name + "'");
			rss.next();
			classes.setText(rss.getString("Student_class"));
			email.setText(rss.getString("Student_Email"));
			String gender = rss.getString("Student_gender");
			byte[] imageByte = rss.getBytes("Student_image");
			
		
				if (imageByte == null) {

					if (gender.equalsIgnoreCase("female")) {
						BufferedImage im = null;
						try {
							im = ImageIO.read(getClass().getResource("/image/female2.png"));
						} catch (Exception e) {
						}
						ImageIcon imn = new ImageIcon(
								new ImageIcon(im).getImage().getScaledInstance(image.getWidth(),image.getHeight(), Image.SCALE_AREA_AVERAGING));
						image.setIcon(imn);
					} else {
						BufferedImage im = null;
						try {
							im = ImageIO.read(getClass().getResource("/image/male1.png"));
						} catch (Exception e) {
						}
						ImageIcon imn = new ImageIcon(
								new ImageIcon(im).getImage().getScaledInstance(image.getWidth(),image.getHeight(), Image.SCALE_AREA_AVERAGING));
						image.setIcon(imn);
						
					}

				} else {
					image.setIcon(new ImageIcon(new ImageIcon(rss.getBytes("Student_image")).getImage()
							.getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_AREA_AVERAGING)));
				}
			
			self_messages(name);
			db.con.close();
		} catch (Exception w) {
			System.out.println(w);
		}
	}

	private void self_messages(String name2) {
		try {
			Databases db = new Databases();
			String url = "jdbc:sqlite:messages.db";
			db.classForname();
			db.con = DriverManager.getConnection(url);
			db.st = db.con.createStatement();
			ResultSet rss1 = db.st.executeQuery("SELECT chatting_save from save WHERE Student_name ='" + name2 + "'");
			rss1.next();
			taker.setText(rss1.getString("chatting_save"));
			db.con.close();
		} catch (Exception w) {
			System.out.println(w);
		}
		jEditorPane.setText("<html><body style=\"padding:0; margin:0;\">" + taker.getText() + "</body></html>");

	}

	private void components() {
		ser = new JTextField("   Search student here");
		ser.setBounds(30, 5, 296 - 30, 36);
		ser.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.DARK_GRAY));
		ser.setForeground(Color.lightGray);
		ser.setFont(new Font("Roman", Font.ROMAN_BASELINE, 14));
		ser.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent arg0) {
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

		serch.add(ser);

		BufferedImage tog = null;
		try {
			tog = ImageIO.read(getClass().getResource("/image/search1.png"));

		} catch (Exception e) {

		}

		ch = new JToggleButton();
		ch.setBounds(0, 5, 30, 36);
		ch.setIcon(new ImageIcon(new ImageIcon(tog).getImage().getScaledInstance(tog.getWidth(), tog.getHeight(),
				Image.SCALE_AREA_AVERAGING)));
		ch.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.DARK_GRAY));
		ch.setBackground(Color.white);
		ch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ser.setText("   Search student here");
				ser.setForeground(Color.LIGHT_GRAY);
				BufferedImage tog = null;
				try {
					tog = ImageIO.read(getClass().getResource("/image/search1.png"));

				} catch (Exception e) {

				}
				ch.setIcon(new ImageIcon(new ImageIcon(tog).getImage().getScaledInstance(tog.getWidth(),
						tog.getHeight(), Image.SCALE_AREA_AVERAGING)));
				openchat();
			}

		});

		serch.add(ch);

		BufferedImage log = null;
		try {
			log = ImageIO.read(getClass().getResource("/image/logada.png"));

		} catch (Exception e) {

		}

		logo = new JLabel();
		logo.setBounds(5, 5, 40, 40);
		logo.setIcon(new ImageIcon(new ImageIcon(log).getImage().getScaledInstance(logo.getWidth(), logo.getHeight(),
				Image.SCALE_AREA_AVERAGING)));
		tiLiPar.add(logo);

		name = new JLabel("STUDENT NOTIFICATIONS");
		name.setBounds(50, 5, 250, 45);
		name.setForeground(new Color(216, 213, 213));
		name.setFont(new Font("Roman", Font.BOLD, 16));
		tiLiPar.add(name);

		image = new JLabel();
		image.setBounds(10, 5, 40, 40);
		image.setIcon(new ImageIcon(new ImageIcon(log).getImage().getScaledInstance(logo.getWidth(), logo.getHeight(),
				Image.SCALE_AREA_AVERAGING)));
		image.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				Databases database = new Databases();

				try {
					String url = "jdbc:sqlite:login.db";
					String data = "select schoolName from loging";
					database.classForname();
					database.con = DriverManager.getConnection(url);
					database.st = database.con.createStatement();
					ResultSet rs = database.st.executeQuery(data);
					rs.next();
					container.setVisible(false);
					sp.getInfo(names.getText(), classes.getText(), rs.getString("schoolName"));
					sp.setVisible(true);
					database.con.close();
				} catch (Exception r) {
					System.out.println(r);
				}

			}

		});
		chatpart.add(image);

		names = new JLabel();
		names.setBounds(60, 5, 300, 25);
		names.setForeground(new Color(216, 213, 213));
		names.setFont(new Font("Roman", Font.BOLD, 14));
		names.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				Databases database = new Databases();

				try {
					String url = "jdbc:sqlite:login.db";
					String data = "select schoolName from loging";
					database.classForname();
					database.con = DriverManager.getConnection(url);
					database.st = database.con.createStatement();
					ResultSet rs = database.st.executeQuery(data);
					rs.next();
					container.setVisible(false);
					sp.getInfo(names.getText(), classes.getText(), rs.getString("schoolName"));
					sp.setVisible(true);
					database.con.close();
				} catch (Exception r) {
					System.out.println(r);
				}

			}

		});

		chatpart.add(names);

		email = new JLabel();
		email.setBounds(60, 25, 350, 20);
		email.setForeground(new Color(216, 213, 213));
		email.setFont(new Font("Roman", Font.PLAIN, 14));
		chatpart.add(email);

		classes = new JLabel();
		classes.setBounds(620, 25, 150, 20);
		classes.setForeground(new Color(216, 213, 213));
		classes.setFont(new Font("Roman", Font.PLAIN, 14));
		chatpart.add(classes);

		BufferedImage cab = null;
		try {
			cab = ImageIO.read(getClass().getResource("/image/kab.png"));

		} catch (Exception e) {

		}

		cap = new JLabel();
		cap.setBounds(593, 20, 24, 24);
		cap.setForeground(new Color(216, 213, 213));
		cap.setFont(new Font("Roman", Font.PLAIN, 14));
		cap.setIcon(new ImageIcon(new ImageIcon(cab).getImage().getScaledInstance(cap.getWidth(), cap.getHeight(),
				Image.SCALE_AREA_AVERAGING)));
		cap.setVisible(false);
		cap.setToolTipText("class leader");
		chatpart.add(cap);

//	

	}

	private void openchat() {
		m.removeAllElements();
		try {
			String url = "jdbc:sqlite:students.db";
			Databases db = new Databases();
			db.classForname();
			db.con = DriverManager.getConnection(url);
			db.st = db.con.createStatement();
			String data = "SELECT Student_name ,Student_Email, Student_image ,Student_gender from student ORDER by Student_name asc";
			ResultSet rs = db.st.executeQuery(data);

			while (rs.next()) {
				String get = rs.getString("Student_name");
				String gender = rs.getString("Student_gender");

				byte[] imageByte = rs.getBytes("Student_image");
				String mai = rs.getString("Student_Email");
				if (!mai.equals("null")) {
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

				}
			}

			db.con.close();

		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	private void liststudents() {

		m.removeAllElements();
		try {
			String url = "jdbc:sqlite:students.db";
			Databases db = new Databases();
			db.classForname();
			db.con = DriverManager.getConnection(url);
			db.st = db.con.createStatement();
			String data = "SELECT Student_name ,Student_Email, Student_image ,Student_gender from student where Student_name like '%"
					+ ser.getText() + "%'";
			ResultSet rs = db.st.executeQuery(data);

			while (rs.next()) {
				String get = rs.getString("Student_name");
				String gender = rs.getString("Student_gender");

				byte[] imageByte = rs.getBytes("Student_image");

				String mai = rs.getString("Student_Email");
				if (!mai.equals("null")) {

					if (!(ser.getText().length() == 0)) {
						if (imageByte == null) {

							if (gender.equalsIgnoreCase("female")) {
								BufferedImage im = null;
								try {
									im = ImageIO.read(getClass().getResource("/image/female2.png"));
								} catch (Exception e) {
								}
								ImageIcon imn = new ImageIcon(new ImageIcon(im).getImage().getScaledInstance(64, 64,
										Image.SCALE_AREA_AVERAGING));
								m.addElement(new ImgNtext(get, imn));
							} else {
								BufferedImage im = null;
								try {
									im = ImageIO.read(getClass().getResource("/image/male1.png"));
								} catch (Exception e) {
								}
								ImageIcon imn = new ImageIcon(new ImageIcon(im).getImage().getScaledInstance(64, 64,
										Image.SCALE_AREA_AVERAGING));
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
			}

			db.con.close();

		} catch (SQLException e) {
			System.out.println("hey");
		}

	}

	private void panels() {
		listClasses = new JPanel();
		listClasses.setBackground(Color.white);
		listClasses.setBounds(0, 100, 300, 590);
		listClasses.setLayout(null);
		container.add(listClasses);

		serch = new JPanel();
		serch.setBackground(new Color(240, 240, 240));
		serch.setBounds(0, 55, 300, 45);
		serch.setLayout(null);
		container.add(serch);

		tiLiPar = new JPanel();
		tiLiPar.setBackground(new Color(0, 50, 72));
		tiLiPar.setLayout(null);
		tiLiPar.setBounds(0, 0, 300, 50);
		container.add(tiLiPar);

		chatpart = new JPanel();
		chatpart.setBackground(new Color(0, 50, 72));
		chatpart.setBounds(300, 0, 745, 50);
		chatpart.setLayout(null);
		chatpart.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.lightGray));
		container.add(chatpart);

		chatbottom = new JPanel();
		chatbottom.setBackground(new Color(27, 59, 113));
		chatbottom.setLayout(null);
		chatbottom.setBounds(300, 620, 745, 70);
		chatbottom.setVisible(false);
		container.add(chatbottom);
	}

	private void design() {
		container = new JPanel();
		container.setBounds(0, 0, 1045, 690);
		container.setBackground(Color.LIGHT_GRAY);
		container.setLayout(null);
		sp.back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				container.setVisible(true);
				sp.setVisible(false);

				sp.SM.setVisible(true);
				sp.SE.setVisible(true);
				sp.SP.setVisible(true);
				sp.PP.setVisible(true);
				sp.SG.setVisible(true);
				sp.SB.setVisible(true);
				sp.SL.setVisible(true);
				sp.SA.setVisible(true);
				sp.SN.setVisible(true);
				sp.Cn.setVisible(false);
				sp.change.setVisible(false);
				sp.save.setVisible(false);
				for (int i = 0; i < sp.text.length; i++) {
					if (!((i == 5) || (i == 4)))
						sp.text[i].setVisible(false);
				}

			}

		});

	

		setVisible(false);
		setLayout(null);
		setBackground(Color.white);
		setBounds(280, 30, 1045, 690);
	}

}
