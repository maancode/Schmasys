package schoolManegementSystem;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import diu.swe.habib.JPanelSlider.JPanelSlider;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DesktopApp extends JFrame {
	private static final long serialVersionUID = 1L;
	private String str[] = { "Student Registration", "Student Information", "Student Marks" };
	private JPanel pane[];
	private String URLImage[] = { "/image/pi.png", "/image/UJUHU-01.png", "/image/home.png", "/image/st.png",
			"/image/invf.png", "/image/m.png", "/image/user.png" };
	private JLabel lb1[];
	private int l[] = { 280, 332, 384, 436, 488, 540, 592, 644 };
	JLabel lb[];
	static JLabel ll, schoolname;
	public String up, ull;
	public JButton Home[], inDash[], create;
	private static JLabel lo;
	private JPanelSlider sl;
	private JSeparator sem[];
	private JLabel slides[];
	private int jj;
	private Timer time;
	
	ImageIcon imagese = null;
	Container cp = getContentPane();

	mouseListener mouse = new mouseListener();
	actionListener action = new actionListener();

	first f = new first();
	newAccount c = new newAccount();
	RegistrationPane p = new RegistrationPane();
	StudentClasses student = new StudentClasses();
	Marks m = new Marks();
	Notification noti = new Notification();
	Informations info = new Informations();
	TrashBackup trash = new TrashBackup();
	Settings set = new Settings();

	public static Databases database = new Databases();

	public DesktopApp() {
		ImagesLoader();
		DashButtons();
		inDashButtons();
		LetterLoader();
		cp.add(c);
		cp.add(p);
		cp.add(student);
		cp.add(m);
		cp.add(noti);
		cp.add(info);
		cp.add(trash);
		cp.add(set);
		Panel();
		window();

	}
	
	public static void connect() {

		lo = new JLabel();
		lo.setBounds(20, 30, 250, 200);
		lo.setVisible(false);

		ResultSet rs;
		try {
			String url = "jdbc:sqlite:login.db";
			String data = "select schoolName from loging";
			database.classForname();
			database.con = DriverManager.getConnection(url);
			database.st = database.con.createStatement();
			rs = database.st.executeQuery(data);
			rs.next();
			String getter = rs.getString("schoolName");

			if (getter.length() == 0) {
				new DesktopApp().setVisible(true);
				;
				ll.setVisible(false);
				lo.setVisible(true);

			} else {
				new first().frame.setVisible(true);
				lo.setVisible(false);

			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {

			try {
				database.st.close();
				database.con.close();

			} catch (Exception e) {
				System.out.print(e);
			}

		}
	}

	private void LetterLoader() {
		// location
		/*
		 * 330, 490, 250, 38 650, 490, 240, 38 1020, 490, 200, 38
		 */

		int Llocation[] = { 330, 650, 1010 };
		int Blocation[] = { 490, 490, 490 };
		int width[] = { 250, 240, 200 };
		int height[] = { 38, 38, 38 };

		lb1 = new JLabel[3];
		for (int i = 0; i < lb1.length; i++) {
			lb1[i] = new JLabel();
			lb1[i].setBounds(Llocation[i], Blocation[i], width[i], height[i]);
			lb1[i].setFont(new Font("ARIAL", Font.BOLD, 25));
			lb1[i].setVisible(true);
			if (i == 0) {
				lb1[i].setText(str[i]);
				lb1[i].setForeground(new Color(34, 81, 176));

			} else if (i == 1) {
				lb1[i].setText(str[i]);
				lb1[i].setForeground(new Color(34, 81, 176));

			} else if (i == 2) {
				lb1[i].setText(str[i]);
				lb1[i].setForeground(Color.cyan);

			}

			cp.add(lb1[i]);

		}

	}

	private void inDashButtons() {

		BufferedImage log = null;
		try {
			log = ImageIO.read(getClass().getResource("/image/UJUHU-01.png"));

		} catch (Exception e) {

		}
		lo.setIcon(new ImageIcon(new ImageIcon(log).getImage().getScaledInstance(lo.getWidth(), lo.getHeight(),
				Image.SCALE_AREA_AVERAGING)));
		add(lo);

		BufferedImage image = null;
		// Location
		/*
		 * 1020, 360, 128, 128 385, 360, 128, 128 700, 360, 128, 128
		 */
		int Llocation[] = { 385, 700, 1020 };
		int Blocation[] = { 360, 360, 360 };
		int width[] = { 128, 128, 128 };
		int height[] = { 128, 128, 128 };
		// Color
		/*
		 * yellow 169, 255, 0 green 77, 255, 0 blue 4, 5, 89
		 * 
		 */
		int c1[] = { 169, 77, 4 };
		int c2[] = { 255, 255, 5 };
		int c3[] = { 0, 0, 89 };

		inDash = new JButton[3];

		for (int i = 0; i < inDash.length; i++) {

			inDash[i] = new JButton();
			inDash[i].setBounds(Llocation[i], Blocation[i], width[i], height[i]);
			inDash[i].setBackground(new Color(c1[i], c2[i], c3[i]));
			inDash[i].setBorder(BorderFactory.createSoftBevelBorder(10));
			inDash[i].setVisible(true);
			if (i == 0) {
				try {
					image = ImageIO.read(getClass().getResource(URLImage[3 + i]));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ImageIcon img = new ImageIcon(image);

				inDash[i].setIcon(img);
				inDash[i].addActionListener(action);
			} else if (i == 1) {
				try {
					image = ImageIO.read(getClass().getResource(URLImage[3 + i]));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ImageIcon img = new ImageIcon(image);
				inDash[i].setIcon(img);
				inDash[i].addActionListener(action);
			} else if (i == 2) {

				try {
					image = ImageIO.read(getClass().getResource(URLImage[3 + i]));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ImageIcon img = new ImageIcon(image);
				inDash[i].setIcon(img);
				inDash[i].addActionListener(action);
			}

			cp.add(inDash[i]);

		}

	}

	private void DashButtons() {

		int iy = 330;
		sem = new JSeparator[8];
		for (int s = 0; s < sem.length; s++) {
			sem[s] = new JSeparator();
			sem[s].setBounds(26, iy, 248, 1);
			sem[s].setForeground(new Color(0, 30, 80));
			iy += 52;
			cp.add(sem[s]);
		}

		String nameButtons[] = { "   Dashboard", "   Registration", "  Class Rooms", "  Student Marks",
				"  Notifications", "  Informations", "  Trash Backup", "  Settings       " };
		Home = new JButton[8];
		for (int i = 0; i < Home.length; i++) {

			Home[i] = new JButton(nameButtons[i]);
			Home[i].setBounds(40, l[i], 200 - 30, 40);
			Home[i].setBackground(new Color(0, 27, 61));
			Home[i].setFont(new Font("ARIAL", Font.PLAIN, 20));
			Home[i].setForeground(Color.cyan);
			Home[i].setBorder(BorderFactory.createSoftBevelBorder(10));
			if (i == 0) {
				BufferedImage image = null;
				try {
					image = ImageIO.read(getClass().getResource(URLImage[2]));
				} catch (IOException e) {
					e.printStackTrace();
				}

				Home[i].setBounds(10, l[i], 275, 40);
				Home[i].setBackground(new Color(57, 162, 217));
				Home[i].setIcon(new ImageIcon(image));
				Home[i].addMouseListener(mouse);
				Home[i].addActionListener(action);
			} else if (i == 1) {
				BufferedImage image = null;
				try {
					image = ImageIO.read(getClass().getResource(URLImage[6]));
				} catch (IOException e) {
					e.printStackTrace();
				}
				Home[i].setIcon(new ImageIcon(image));

				Home[i].addMouseListener(mouse);
				Home[i].addActionListener(action);

			} else if (i == 2) {
				BufferedImage image = null;
				try {
					image = ImageIO.read(getClass().getResource("/image/classes1.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				Home[i].setIcon(new ImageIcon(image));
				Home[i].addMouseListener(mouse);
				Home[i].addActionListener(action);
			} else if (i == 3) {
				BufferedImage image = null;
				try {
					image = ImageIO.read(getClass().getResource("/image/exam.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				Home[3].setBounds(40, l[3], 200 - 10, 40);
				Home[i].setIcon(new ImageIcon(
						new ImageIcon(image).getImage().getScaledInstance(24, 24, Image.SCALE_AREA_AVERAGING)));
				Home[i].addMouseListener(mouse);
				Home[i].addActionListener(action);
			} else if (i == 4) {
				BufferedImage image = null;
				try {
					image = ImageIO.read(getClass().getResource("/image/mess.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				Home[i].setIcon(new ImageIcon(image));
				Home[i].addMouseListener(mouse);
				Home[i].addActionListener(action);
			} else if (i == 5) {
				BufferedImage image = null;
				try {
					image = ImageIO.read(getClass().getResource("/image/informa.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				Home[i].setIcon(new ImageIcon(image));
				Home[i].addMouseListener(mouse);
				Home[i].addActionListener(action);
			} else if (i == 6) {
				BufferedImage image = null;
				try {
					image = ImageIO.read(getClass().getResource("/image/trash.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				Home[i].setIcon(new ImageIcon(
						new ImageIcon(image).getImage().getScaledInstance(24, 24, Image.SCALE_AREA_AVERAGING)));

				Home[i].addMouseListener(mouse);
				Home[i].addActionListener(action);
			} else if (i == 7) {
				BufferedImage image = null;
				try {
					image = ImageIO.read(getClass().getResource("/image/gear.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				Home[i].setIcon(new ImageIcon(
						new ImageIcon(image).getImage().getScaledInstance(24, 24, Image.SCALE_AREA_AVERAGING)));

				Home[i].addMouseListener(mouse);
				Home[i].addActionListener(action);
			}

			cp.add(Home[i]);
		}
		schoolname = new JLabel();
		schoolname.setBounds(95, 245, 300, 30);
		schoolname.setFont(new Font("ARIAL", Font.BOLD, 13));
		schoolname.setForeground(Color.cyan);

		ll = new JLabel();
		ll.setBounds(70, 75, 160, 160);
		// ll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.cyan));

		try {
			String url = "jdbc:sqlite:login.db";
			String data = "select  logo,schoolName from loging";
			database.classForname();
			database.con = DriverManager.getConnection(url);
			database.st = database.con.createStatement();
			ResultSet rs = database.st.executeQuery(data);
			rs.next();
			byte[] imageByte = rs.getBytes("logo");

			String n = rs.getString("schoolName");
			String nt = n.substring(0, n.indexOf(" "));
			String ntt = n.substring(n.indexOf(" ")).substring(1);
			String ntts = ntt.substring(0, ntt.indexOf(" "));

			schoolname.setText(nt + " " + ntts);
			if (imageByte != null)
				ll.setIcon(new ImageIcon(new ImageIcon(imageByte).getImage().getScaledInstance(ll.getWidth(),
						ll.getHeight(), Image.SCALE_AREA_AVERAGING)));
			else {
				BufferedImage log = null;
				try {
					log = ImageIO.read(getClass().getResource("/image/UJUHU-01.png"));

				} catch (Exception e) {

				}
				ll.setIcon(new ImageIcon(new ImageIcon(log).getImage().getScaledInstance(ll.getWidth(), ll.getHeight(),
						Image.SCALE_AREA_AVERAGING)));

			}
			database.st.close();
			database.con.close();

		} catch (Exception e) {
			System.out.println();
		}

		cp.add(ll);

		cp.add(schoolname);

		create = new JButton("Create an Account");
		create.setBounds(40, 245, 200, 30);
		create.setFont(new Font("ARIAL", Font.BOLD, 13));
		create.setBackground(new Color(0, 27, 61));
		create.setForeground(Color.cyan);
		create.setBorder(BorderFactory.createSoftBevelBorder(10));
		create.addActionListener(action);

		cp.add(create);
	}

	private void ImagesLoader() {

		Databases db = new Databases();
		try {
			db.classForname();
			db.con = DriverManager.getConnection("jdbc:sqlite:class.db");
			db.st = db.con.createStatement();

			String q = "select count(), slides from slideImages";
			ResultSet g = db.st.executeQuery(q);
			g.next();
			int dd = g.getInt("count()");
			jj = dd;
			int left = 20;
			if (dd != 0) {
				slides = new JLabel[dd];
				for (int i = 0; i < slides.length; i++) {
					slides[i] = new JLabel();
					slides[i].setBounds(left, 300, 70, 70);
					slides[i].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

					left += 75;
				}
			}

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

		if (jj != 0) {
			Databases db1 = new Databases();
			try {
				db1.classForname();
				db1.con = DriverManager.getConnection("jdbc:sqlite:class.db");
				db1.st = db1.con.createStatement();

				String q = "select  slides from slideImages";
				ResultSet g = db1.st.executeQuery(q);
				while (g.next()) {
					slides[g.getRow() - 1].setIcon(new ImageIcon(g.getBytes("slides")));

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

			jj = slides.length;
		}

		sl = new JPanelSlider();
		JPanel p1 = new JPanel();
		sl.setBounds(290, 35, 1010, 250);
		sl.setBackground(Color.cyan);
		sl.setVisible(true);
		JPanel p = new JPanel();
		p.setBounds(10, 10, 400, 250);
		p.setBackground(Color.yellow);
		p.setLayout(null);

		JLabel lb = new JLabel();
		lb.setBounds(0, 0, 1010, 250);
		lb.setForeground(Color.white);

		JLabel lb1 = new JLabel();
		lb1.setBounds(0, 0, 1010, 250);
		lb.setForeground(Color.white);

		time = new Timer(4000, new ActionListener() {
			int i = 0;
			int j = 0;
			BufferedImage image = null;

			public void actionPerformed(ActionEvent arg0) {
				if (jj != 0) {
					if (j == jj) {
						j = 0;
					}
					if (i % 2 == 0) {
						Icon image = slides[j].getIcon();

						ImageIcon image1 = (ImageIcon) image;

						lb.setIcon(new ImageIcon(image1.getImage().getScaledInstance(sl.getWidth(), sl.getHeight(),
								Image.SCALE_AREA_AVERAGING)));
						sl.nextPanel(10, p, JPanelSlider.left);
					} else {
						Icon image = slides[j].getIcon();

						ImageIcon image1 = (ImageIcon) image;

						lb1.setIcon(new ImageIcon(image1.getImage().getScaledInstance(sl.getWidth(), sl.getHeight(),
								Image.SCALE_AREA_AVERAGING)));
						sl.nextPanel(10, p1, JPanelSlider.left);
					}
					if (i == 1) {
						time.stop();
						time.restart();
					}

					j++;
					i++;

				} else {
					if (i % 2 == 0) {
						try {
							image = ImageIO.read(getClass().getResource("/image/n.jpg"));

						} catch (Exception e) {

						}
						lb.setIcon(new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(lb.getWidth(),
								lb.getHeight(), Image.SCALE_AREA_AVERAGING)));
						sl.nextPanel(10, p, JPanelSlider.left);
					} else {
						try {
							image = ImageIO.read(getClass().getResource("/image/pi.png"));

						} catch (Exception e) {

						}

						lb1.setIcon(new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(lb1.getWidth(),
								lb1.getHeight(), Image.SCALE_AREA_AVERAGING)));
						sl.nextPanel(10, p1, JPanelSlider.left);
					}
					if (i == 1) {
						time.stop();
						time.restart();
					}
					i++;
				}
			}

		});

		time.start();

		p1.setBounds(10, 10, 400, 250);
		p1.setBackground(Color.black);
		p1.setLayout(null);

		p.add(lb);
		p1.add(lb1);
		sl.add(p1);
		sl.add(p);
		cp.add(sl);

	}

	private void Panel() {
		// Location Margin'
		/*
		 * , 25, 250, 712 25, 0, 1335, 25 300, 330, 300, 200 620, 330, 300, 200 940,
		 * 330, 300, 200 300, 495, 300, 40 620, 495, 300, 40 940, 495, 300, 40 300, 600,
		 * 970, 10
		 */

		// Colors
		/*
		 * blue mari (0, 27, 61) blue calan 57, 162, 217 cadaan 255,255,255 169, 255, 0
		 * 77, 255, 0 4, 5, 89 228, 255, 0 124, 208, 0 18, 7, 158 4, 18, 89
		 */
		pane = new JPanel[11];
		// location layouts
		int Llocation[] = { 25, 300, 300, 620, 620, 940, 940, 300, 290, 275, 0 };
		int Blocation[] = { 25, 495, 330, 495, 330, 495, 330, 600, 310, 25, 0 };
		int width[] = { 250, 300, 300, 300, 300, 300, 300, 970, 990, 1055, 1360 };
		int height[] = { 705, 40, 200, 40, 200, 40, 200, 10, 250, 705, 730 };

		// colors
		int c1[] = { 0, 228, 169, 124, 77, 18, 4, 4, 225, 255, 57 };
		int c2[] = { 27, 255, 255, 208, 255, 7, 5, 18, 225, 255, 162 };
		int c3[] = { 61, 0, 0, 0, 0, 158, 89, 89, 225, 255, 217 };

		for (int i = 0; i < pane.length; i++) {

			pane[i] = new JPanel();
			pane[i].setBackground(new Color(c1[i], c2[i], c3[i]));
			pane[i].setBounds(Llocation[i], Blocation[i], width[i], height[i]);

			if (i == 1) {
				pane[i].setVisible(true);
			} else if (i == 2) {
				pane[i].setVisible(true);
			} else if (i == 3) {
				pane[i].setVisible(true);
			} else if (i == 4) {
				pane[i].setVisible(true);
			} else if (i == 5) {
				pane[i].setVisible(true);
			} else if (i == 6) {
				pane[i].setVisible(true);
			} else if (i == 7) {
				pane[i].setVisible(true);
			} else if (i == 8) {
				pane[i].setBorder(BorderFactory.createMatteBorder(0, 5, 0, 5, new Color(153, 158, 165)));
			}

			cp.add(pane[i]);

		}

	}

	private void window() {

		BufferedImage image = null;

		try {
			image = ImageIO.read(getClass().getResource(URLImage[1]));
		} catch (IOException e) {

			e.printStackTrace();
		}

		ImageIcon imag = new ImageIcon(image);

		setTitle("School_Manegment_System");

		setSize(1360, 730);
		setVisible(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(imag.getImage());

		p.IH1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BufferedImage image = null;

				try {
					image = ImageIO.read(getClass().getResource("/image/UJUHU-01.png"));
				} catch (IOException e) {

					e.printStackTrace();
				}

				ImageIcon imag = new ImageIcon(image);
			
				Webcam	web = Webcam.getDefault();
					web.setViewSize(new Dimension(640,480));
					web.setViewSize(WebcamResolution.VGA.getSize());
					web.open();	
			
				WebcamPanel panels = new WebcamPanel(web);
				panels.setImageSizeDisplayed(true);
				panels.setFPSDisplayed(true);
				panels.setMirrored(true);
				
				JDialog dialog = new JDialog(DesktopApp.this, "Dialog", true);
				dialog.setTitle("Camera..");
				dialog.setSize(640,480);
			
				dialog.setResizable(false);
				dialog.setVisible(true);
				dialog.setIconImage(imag.getImage());
			    JPanel mainGui = new JPanel(new BorderLayout());
			    mainGui.setBorder(new EmptyBorder(20, 20, 20, 20));
			   
			    panels.setBorder(BorderFactory.createLineBorder(Color.darkGray));
			    
			    mainGui.add(panels);

			    JPanel buttonPanel = new JPanel(new FlowLayout());
			    mainGui.add(buttonPanel, BorderLayout.SOUTH);

			    JButton close = new JButton("capture");
			    close.addActionListener(new ActionListener() {

					
					public void actionPerformed(ActionEvent arg0) {
							
						p.pic1.setIcon(new ImageIcon(new ImageIcon(panels.getImage()).getImage().getScaledInstance(p.pic1.getWidth(), p.pic1.getHeight(),
								Image.SCALE_AREA_AVERAGING)));
						File f = new File("Image");
						try {
							ImageIO.write(panels.getImage(),"PNG",f);
							p.s = f.getAbsolutePath();
							p.sa = p.s;
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
							
						
						web.close();
						dialog.setVisible(false);
					
						
					}

				
			    	
			    });

			    buttonPanel.add(close);
			    dialog.setContentPane(mainGui);
			    dialog.setVisible(true);  
			   
				

			    
				
//			    
					

			}

		});
	}

	public class mouseListener implements MouseListener, MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			if (arg0.getSource() == Home[0]) {
				Home[0].setBounds(10, l[0], 275, 40);
				Home[0].setBackground(new Color(57, 162, 217));

				Home[1].setBounds(40, l[1], 200 - 30, 40);
				Home[1].setBackground(new Color(0, 27, 61));

				Home[2].setBounds(40, l[2], 200 - 30, 40);
				Home[2].setBackground(new Color(0, 27, 61));

				Home[3].setBounds(40, l[3], 200 - 10, 40);
				Home[3].setBackground(new Color(0, 27, 61));

				Home[4].setBounds(40, l[4], 200 - 30, 40);
				Home[4].setBackground(new Color(0, 27, 61));

				Home[5].setBounds(40, l[5], 200 - 30, 40);
				Home[5].setBackground(new Color(0, 27, 61));

				Home[6].setBounds(40, l[6], 200 - 30, 40);
				Home[6].setBackground(new Color(0, 27, 61));

				Home[7].setBounds(40, l[7], 200 - 30, 40);
				Home[7].setBackground(new Color(0, 27, 61));

			} else if (arg0.getSource() == Home[1]) {
				Home[1].setBounds(10, l[1], 275, 40);
				Home[1].setBackground(new Color(57, 162, 217));

				Home[0].setBounds(40, l[0], 200 - 30, 40);
				Home[0].setBackground(new Color(0, 27, 61));

				Home[2].setBounds(40, l[2], 200 - 30, 40);
				Home[2].setBackground(new Color(0, 27, 61));

				Home[3].setBounds(40, l[3], 200 - 10, 40);
				Home[3].setBackground(new Color(0, 27, 61));

				Home[4].setBounds(40, l[4], 200 - 30, 40);
				Home[4].setBackground(new Color(0, 27, 61));

				Home[5].setBounds(40, l[5], 200 - 30, 40);
				Home[5].setBackground(new Color(0, 27, 61));

				Home[6].setBounds(40, l[6], 200 - 30, 40);
				Home[6].setBackground(new Color(0, 27, 61));

				Home[7].setBounds(40, l[7], 200 - 30, 40);
				Home[7].setBackground(new Color(0, 27, 61));

			} else if (arg0.getSource() == Home[2]) {
				Home[2].setBounds(10, l[2], 275, 40);
				Home[2].setBackground(new Color(57, 162, 217));

				Home[0].setBounds(40, l[0], 200 - 30, 40);
				Home[0].setBackground(new Color(0, 27, 61));

				Home[1].setBounds(40, l[1], 200 - 30, 40);
				Home[1].setBackground(new Color(0, 27, 61));

				Home[3].setBounds(40, l[3], 200 - 10, 40);
				Home[3].setBackground(new Color(0, 27, 61));

				Home[4].setBounds(40, l[4], 200 - 30, 40);
				Home[4].setBackground(new Color(0, 27, 61));

				Home[5].setBounds(40, l[5], 200 - 30, 40);
				Home[5].setBackground(new Color(0, 27, 61));

				Home[6].setBounds(40, l[6], 200 - 30, 40);
				Home[6].setBackground(new Color(0, 27, 61));

				Home[7].setBounds(40, l[7], 200 - 30, 40);
				Home[7].setBackground(new Color(0, 27, 61));

			} else if (arg0.getSource() == Home[3]) {
				Home[3].setBounds(10, l[3], 275, 40);
				Home[3].setBackground(new Color(57, 162, 217));

				Home[0].setBounds(40, l[0], 200 - 30, 40);
				Home[0].setBackground(new Color(0, 27, 61));

				Home[1].setBounds(40, l[1], 200 - 30, 40);
				Home[1].setBackground(new Color(0, 27, 61));

				Home[2].setBounds(40, l[2], 200 - 30, 40);
				Home[2].setBackground(new Color(0, 27, 61));

				Home[4].setBounds(40, l[4], 200 - 30, 40);
				Home[4].setBackground(new Color(0, 27, 61));

				Home[5].setBounds(40, l[5], 200 - 30, 40);
				Home[5].setBackground(new Color(0, 27, 61));

				Home[6].setBounds(40, l[6], 200 - 30, 40);
				Home[6].setBackground(new Color(0, 27, 61));

				Home[7].setBounds(40, l[7], 200 - 30, 40);
				Home[7].setBackground(new Color(0, 27, 61));

			} else if (arg0.getSource() == Home[4]) {
				Home[4].setBounds(10, l[4], 275, 40);
				Home[4].setBackground(new Color(57, 162, 217));

				Home[0].setBounds(40, l[0], 200 - 30, 40);
				Home[0].setBackground(new Color(0, 27, 61));

				Home[1].setBounds(40, l[1], 200 - 30, 40);
				Home[1].setBackground(new Color(0, 27, 61));

				Home[2].setBounds(40, l[2], 200 - 30, 40);
				Home[2].setBackground(new Color(0, 27, 61));

				Home[3].setBounds(40, l[3], 200 - 10, 40);
				Home[3].setBackground(new Color(0, 27, 61));

				Home[5].setBounds(40, l[5], 200 - 30, 40);
				Home[5].setBackground(new Color(0, 27, 61));

				Home[6].setBounds(40, l[6], 200 - 30, 40);
				Home[6].setBackground(new Color(0, 27, 61));

				Home[7].setBounds(40, l[7], 200 - 30, 40);
				Home[7].setBackground(new Color(0, 27, 61));

			} else if (arg0.getSource() == Home[5]) {
				Home[5].setBounds(10, l[5], 275, 40);
				Home[5].setBackground(new Color(57, 162, 217));

				Home[0].setBounds(40, l[0], 200 - 30, 40);
				Home[0].setBackground(new Color(0, 27, 61));

				Home[1].setBounds(40, l[1], 200 - 30, 40);
				Home[1].setBackground(new Color(0, 27, 61));

				Home[2].setBounds(40, l[2], 200 - 30, 40);
				Home[2].setBackground(new Color(0, 27, 61));

				Home[3].setBounds(40, l[3], 200 - 10, 40);
				Home[3].setBackground(new Color(0, 27, 61));

				Home[4].setBounds(40, l[4], 200 - 30, 40);
				Home[4].setBackground(new Color(0, 27, 61));

				Home[6].setBounds(40, l[6], 200 - 30, 40);
				Home[6].setBackground(new Color(0, 27, 61));

				Home[7].setBounds(40, l[7], 200 - 30, 40);
				Home[7].setBackground(new Color(0, 27, 61));

			}

			else if (arg0.getSource() == Home[6]) {
				Home[6].setBounds(10, l[6], 275, 40);
				Home[6].setBackground(new Color(57, 162, 217));

				Home[0].setBounds(40, l[0], 200 - 30, 40);
				Home[0].setBackground(new Color(0, 27, 61));

				Home[1].setBounds(40, l[1], 200 - 30, 40);
				Home[1].setBackground(new Color(0, 27, 61));

				Home[2].setBounds(40, l[2], 200 - 30, 40);
				Home[2].setBackground(new Color(0, 27, 61));

				Home[3].setBounds(40, l[3], 200 - 10, 40);
				Home[3].setBackground(new Color(0, 27, 61));

				Home[4].setBounds(40, l[4], 200 - 30, 40);
				Home[4].setBackground(new Color(0, 27, 61));

				Home[5].setBounds(40, l[5], 200 - 30, 40);
				Home[5].setBackground(new Color(0, 27, 61));

				Home[7].setBounds(40, l[7], 200 - 30, 40);
				Home[7].setBackground(new Color(0, 27, 61));

			}

			else if (arg0.getSource() == Home[7]) {
				Home[7].setBounds(10, l[7], 275, 40);
				Home[7].setBackground(new Color(57, 162, 217));

				Home[0].setBounds(40, l[0], 200 - 30, 40);
				Home[0].setBackground(new Color(0, 27, 61));

				Home[1].setBounds(40, l[1], 200 - 30, 40);
				Home[1].setBackground(new Color(0, 27, 61));

				Home[2].setBounds(40, l[2], 200 - 30, 40);
				Home[2].setBackground(new Color(0, 27, 61));

				Home[3].setBounds(40, l[3], 200 - 10, 40);
				Home[3].setBackground(new Color(0, 27, 61));

				Home[4].setBounds(40, l[4], 200 - 30, 40);
				Home[4].setBackground(new Color(0, 27, 61));

				Home[6].setBounds(40, l[6], 200 - 30, 40);
				Home[6].setBackground(new Color(0, 27, 61));

				Home[5].setBounds(40, l[5], 200 - 30, 40);
				Home[5].setBackground(new Color(0, 27, 61));

			}

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	public class actionListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			noti.setVisible(false);
			info.setVisible(false);
			trash.setVisible(false);
			set.setVisible(false);
			if (arg0.getSource() == inDash[0]) {

				Home[1].setBounds(10, l[1], 275, 40);
				Home[1].setBackground(new Color(57, 162, 217));

				Home[0].setBounds(40, l[0], 200 - 30, 40);
				Home[0].setBackground(new Color(0, 27, 61));

				Home[2].setBounds(40, l[2], 200 - 30, 40);
				Home[2].setBackground(new Color(0, 27, 61));

				Home[3].setBounds(40, l[3], 200 - 10, 40);
				Home[3].setBackground(new Color(0, 27, 61));

				Home[4].setBounds(40, l[4], 200 - 30, 40);
				Home[4].setBackground(new Color(0, 27, 61));

				Home[5].setBounds(40, l[5], 200 - 30, 40);
				Home[5].setBackground(new Color(0, 27, 61));

				p.setVisible(true);
				m.setVisible(false);

				Databases data = new Databases();
				String str = null;

				try {
					data.classForname();
					data.con = DriverManager.getConnection("jdbc:sqlite:class.db");
					data.st = data.con.createStatement();
					String query = "select * from classrooms";
					ResultSet rs = data.st.executeQuery(query);
					p.box.removeAllItems();

					while (rs.next()) {

						str = rs.getString("classname");

					}

				} catch (Exception e) {
					System.out.println(e);
				} finally {
					try {
						data.st.close();
						data.con.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				try {
					data.classForname();
					data.con = DriverManager.getConnection("jdbc:sqlite:class.db");
					data.st = data.con.createStatement();
					String query = "select * from classrooms";
					ResultSet rs = data.st.executeQuery(query);

					while (rs.next()) {

						str = rs.getString("classname");

						p.box.addItem(str);

					}

				} catch (Exception e) {
					System.out.println(e);
				} finally {
					try {
						data.st.close();
						data.con.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				c.setVisible(false);
				student.setVisible(false);
				int visibled = 10;
				for (int i = 0; i < visibled; i++) {
					if (i == 0) {
						sl.setVisible(false);
						inDash[i].setVisible(false);
						lb1[i].setVisible(false);
					} else if (i == 1) {
						pane[i].setVisible(false);
						inDash[i].setVisible(false);
						lb1[i].setVisible(false);
					} else if (i == 2) {
						pane[i].setVisible(false);
						inDash[i].setVisible(false);
						lb1[i].setVisible(false);
					} else if (i == 3) {
						pane[i].setVisible(false);
					} else if (i == 4) {
						pane[i].setVisible(false);
					} else if (i == 5) {
						pane[i].setVisible(false);
					} else if (i == 6) {
						pane[i].setVisible(false);
					} else if (i == 7) {
						pane[i].setVisible(false);
					} else if (i == 8) {
						pane[i].setVisible(false);
					}
					///
				}

			} else if (arg0.getSource() == inDash[1]) {

				Home[5].setBounds(10, l[5], 275, 40);
				Home[5].setBackground(new Color(57, 162, 217));

				Home[0].setBounds(40, l[0], 200 - 30, 40);
				Home[0].setBackground(new Color(0, 27, 61));

				Home[1].setBounds(40, l[1], 200 - 30, 40);
				Home[1].setBackground(new Color(0, 27, 61));

				Home[2].setBounds(40, l[2], 200 - 30, 40);
				Home[2].setBackground(new Color(0, 27, 61));

				Home[3].setBounds(40, l[3], 200 - 10, 40);
				Home[3].setBackground(new Color(0, 27, 61));

				Home[4].setBounds(40, l[4], 200 - 30, 40);
				Home[4].setBackground(new Color(0, 27, 61));

				p.setVisible(false);
				c.setVisible(false);
				student.setVisible(false);
				m.setVisible(false);
				info.setVisible(true);

				int visibled = 10;
				for (int i = 0; i < visibled; i++) {
					if (i == 0) {
						sl.setVisible(false);
						inDash[i].setVisible(false);
						lb1[i].setVisible(false);
					} else if (i == 1) {
						pane[i].setVisible(false);
						inDash[i].setVisible(false);
						lb1[i].setVisible(false);
					} else if (i == 2) {
						pane[i].setVisible(false);
						inDash[i].setVisible(false);
						lb1[i].setVisible(false);
					} else if (i == 3) {
						pane[i].setVisible(false);
					} else if (i == 4) {
						pane[i].setVisible(false);
					} else if (i == 5) {
						pane[i].setVisible(false);
					} else if (i == 6) {
						pane[i].setVisible(false);
					} else if (i == 7) {
						pane[i].setVisible(false);
					}

					else if (i == 8) {
						pane[i].setVisible(false);

					}
				}

			}

			else if (arg0.getSource() == inDash[2]) {
				Home[3].setBounds(10, l[3], 275, 40);
				Home[3].setBackground(new Color(57, 162, 217));

				Home[0].setBounds(40, l[0], 200 - 30, 40);
				Home[0].setBackground(new Color(0, 27, 61));

				Home[1].setBounds(40, l[1], 200 - 30, 40);
				Home[1].setBackground(new Color(0, 27, 61));

				Home[2].setBounds(40, l[2], 200 - 30, 40);
				Home[2].setBackground(new Color(0, 27, 61));

				Home[4].setBounds(40, l[4], 200 - 30, 40);
				Home[4].setBackground(new Color(0, 27, 61));

				Home[5].setBounds(40, l[5], 200 - 30, 40);
				Home[5].setBackground(new Color(0, 27, 61));
				p.setVisible(false);
				c.setVisible(false);
				student.setVisible(false);
				m.setVisible(true);

				int visibled = 10;
				for (int i = 0; i < visibled; i++) {
					if (i == 0) {
						sl.setVisible(false);
						inDash[i].setVisible(false);
						lb1[i].setVisible(false);
					} else if (i == 1) {
						pane[i].setVisible(false);
						inDash[i].setVisible(false);
						lb1[i].setVisible(false);
					} else if (i == 2) {
						pane[i].setVisible(false);
						inDash[i].setVisible(false);
						lb1[i].setVisible(false);
					} else if (i == 3) {
						pane[i].setVisible(false);
					} else if (i == 4) {
						pane[i].setVisible(false);
					} else if (i == 5) {
						pane[i].setVisible(false);
					} else if (i == 6) {
						pane[i].setVisible(false);
					} else if (i == 7) {
						pane[i].setVisible(false);
					} else if (i == 8) {
						pane[i].setVisible(false);
					}
				}
			}

			if (arg0.getSource() == create) {
				p.setVisible(false);
				c.setVisible(true);
				student.setVisible(false);
				m.setVisible(false);

//				student.dia.setVisible(false);
				int visibled = 10;
				for (int i = 0; i < visibled; i++) {
					if (i == 0) {
						sl.setVisible(false);
						inDash[i].setVisible(false);
						lb1[i].setVisible(false);
					} else if (i == 1) {
						pane[i].setVisible(false);
						inDash[i].setVisible(false);
						lb1[i].setVisible(false);
					} else if (i == 2) {
						pane[i].setVisible(false);
						inDash[i].setVisible(false);
						lb1[i].setVisible(false);
					} else if (i == 3) {
						pane[i].setVisible(false);

					} else if (i == 4) {
						pane[i].setVisible(false);
					} else if (i == 5) {
						pane[i].setVisible(false);
					} else if (i == 6) {
						pane[i].setVisible(false);
					} else if (i == 7) {
						pane[i].setVisible(false);
					} else if (i == 8) {
						pane[i].setVisible(false);
					}
				}

			}

			if (arg0.getSource() == Home[0]) {
				p.setVisible(false);
				c.setVisible(false);
				student.setVisible(false);
				m.setVisible(false);
				data();
				int visibled = 10;
				for (int i = 0; i < visibled; i++) {
					if (i == 0) {
						sl.setVisible(true);
						inDash[i].setVisible(true);
						lb1[i].setVisible(true);
					} else if (i == 1) {
						pane[i].setVisible(true);
						inDash[i].setVisible(true);
						lb1[i].setVisible(true);
					} else if (i == 2) {
						pane[i].setVisible(true);
						inDash[i].setVisible(true);
						lb1[i].setVisible(true);
					} else if (i == 3) {
						pane[i].setVisible(true);
					} else if (i == 4) {
						pane[i].setVisible(true);
					} else if (i == 5) {
						pane[i].setVisible(true);
					} else if (i == 6) {
						pane[i].setVisible(true);
					} else if (i == 7) {
						pane[i].setVisible(true);
					} else if (i == 8) {
						pane[i].setVisible(true);
					}
				}

			} else if (arg0.getSource() == Home[1]) {
				p.setVisible(true);
				m.setVisible(false);

				Databases data = new Databases();
				String str = null;

				try {
					data.classForname();
					data.con = DriverManager.getConnection("jdbc:sqlite:class.db");
					data.st = data.con.createStatement();
					String query = "select * from classrooms";
					ResultSet rs = data.st.executeQuery(query);
					p.box.removeAllItems();

					while (rs.next()) {

						str = rs.getString("classname");

					}

				} catch (Exception e) {
					System.out.println(e);
				} finally {
					try {
						data.st.close();
						data.con.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				try {
					data.classForname();
					data.con = DriverManager.getConnection("jdbc:sqlite:class.db");
					data.st = data.con.createStatement();
					String query = "select * from classrooms";
					ResultSet rs = data.st.executeQuery(query);

					while (rs.next()) {

						str = rs.getString("classname");

						p.box.addItem(str);

					}

				} catch (Exception e) {
					System.out.println(e);
				} finally {
					try {
						data.st.close();
						data.con.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				c.setVisible(false);
				student.setVisible(false);
				int visibled = 10;
				for (int i = 0; i < visibled; i++) {
					if (i == 0) {
						sl.setVisible(false);
						inDash[i].setVisible(false);
						lb1[i].setVisible(false);
					} else if (i == 1) {
						pane[i].setVisible(false);
						inDash[i].setVisible(false);
						lb1[i].setVisible(false);
					} else if (i == 2) {
						pane[i].setVisible(false);
						inDash[i].setVisible(false);
						lb1[i].setVisible(false);
					} else if (i == 3) {
						pane[i].setVisible(false);
					} else if (i == 4) {
						pane[i].setVisible(false);
					} else if (i == 5) {
						pane[i].setVisible(false);
					} else if (i == 6) {
						pane[i].setVisible(false);
					} else if (i == 7) {
						pane[i].setVisible(false);
					} else if (i == 8) {
						pane[i].setVisible(false);
					}
					///
				}

			} else if (arg0.getSource() == Home[2]) {
				p.setVisible(false);
				c.setVisible(false);
				student.setVisible(true);
				m.setVisible(false);

//				student.dia.setVisible(true);
				int visibled = 10;
				for (int i = 0; i < visibled; i++) {
					if (i == 0) {
						sl.setVisible(false);
						inDash[i].setVisible(false);
						lb1[i].setVisible(false);
					} else if (i == 1) {
						pane[i].setVisible(false);
						inDash[i].setVisible(false);
						lb1[i].setVisible(false);
					} else if (i == 2) {
						pane[i].setVisible(false);
						inDash[i].setVisible(false);
						lb1[i].setVisible(false);
					} else if (i == 3) {
						pane[i].setVisible(false);

					} else if (i == 4) {
						pane[i].setVisible(false);
					} else if (i == 5) {
						pane[i].setVisible(false);
					} else if (i == 6) {
						pane[i].setVisible(false);
					} else if (i == 7) {
						pane[i].setVisible(false);
					} else if (i == 8) {
						pane[i].setVisible(false);
					}
				}

			} else if (arg0.getSource() == Home[3]) {
				p.setVisible(false);
				c.setVisible(false);
				student.setVisible(false);
				m.setVisible(true);

				int visibled = 10;
				for (int i = 0; i < visibled; i++) {
					if (i == 0) {
						sl.setVisible(false);
						inDash[i].setVisible(false);
						lb1[i].setVisible(false);
					} else if (i == 1) {
						pane[i].setVisible(false);
						inDash[i].setVisible(false);
						lb1[i].setVisible(false);
					} else if (i == 2) {
						pane[i].setVisible(false);
						inDash[i].setVisible(false);
						lb1[i].setVisible(false);
					} else if (i == 3) {
						pane[i].setVisible(false);
					} else if (i == 4) {
						pane[i].setVisible(false);
					} else if (i == 5) {
						pane[i].setVisible(false);
					} else if (i == 6) {
						pane[i].setVisible(false);
					} else if (i == 7) {
						pane[i].setVisible(false);
					} else if (i == 8) {
						pane[i].setVisible(false);
					}
				}

			} else if (arg0.getSource() == Home[4]) {
				p.setVisible(false);
				c.setVisible(false);
				student.setVisible(false);
				m.setVisible(false);
				noti.setVisible(true);
				int visibled = 10;
				for (int i = 0; i < visibled; i++) {
					if (i == 0) {
						sl.setVisible(false);
						inDash[i].setVisible(false);
						lb1[i].setVisible(false);
					} else if (i == 1) {
						pane[i].setVisible(false);
						inDash[i].setVisible(false);
						lb1[i].setVisible(false);
					} else if (i == 2) {
						pane[i].setVisible(false);
						inDash[i].setVisible(false);
						lb1[i].setVisible(false);
					} else if (i == 3) {
						pane[i].setVisible(false);
					} else if (i == 4) {
						pane[i].setVisible(false);
					} else if (i == 5) {
						pane[i].setVisible(false);
					} else if (i == 6) {
						pane[i].setVisible(false);
					} else if (i == 7) {
						pane[i].setVisible(false);
					}

					else if (i == 8) {
						pane[i].setVisible(false);
						c.setVisible(false);
					}
				}

			} else if (arg0.getSource() == Home[5]) {
				p.setVisible(false);
				c.setVisible(false);
				student.setVisible(false);
				m.setVisible(false);
				info.setVisible(true);

				int visibled = 10;
				for (int i = 0; i < visibled; i++) {
					if (i == 0) {
						sl.setVisible(false);
						inDash[i].setVisible(false);
						lb1[i].setVisible(false);
					} else if (i == 1) {
						pane[i].setVisible(false);
						inDash[i].setVisible(false);
						lb1[i].setVisible(false);
					} else if (i == 2) {
						pane[i].setVisible(false);
						inDash[i].setVisible(false);
						lb1[i].setVisible(false);
					} else if (i == 3) {
						pane[i].setVisible(false);
					} else if (i == 4) {
						pane[i].setVisible(false);
					} else if (i == 5) {
						pane[i].setVisible(false);
					} else if (i == 6) {
						pane[i].setVisible(false);
					} else if (i == 7) {
						pane[i].setVisible(false);
					}

					else if (i == 8) {
						pane[i].setVisible(false);

					}
				}

			} else if (arg0.getSource() == Home[6]) {
				p.setVisible(false);
				c.setVisible(false);
				student.setVisible(false);
				m.setVisible(false);
				trash.operationCenter();
				trash.setVisible(true);

				int visibled = 10;
				for (int i = 0; i < visibled; i++) {
					if (i == 0) {
						sl.setVisible(false);
						inDash[i].setVisible(false);
						lb1[i].setVisible(false);
					} else if (i == 1) {
						pane[i].setVisible(false);
						inDash[i].setVisible(false);
						lb1[i].setVisible(false);
					} else if (i == 2) {
						pane[i].setVisible(false);
						inDash[i].setVisible(false);
						lb1[i].setVisible(false);
					} else if (i == 3) {
						pane[i].setVisible(false);
					} else if (i == 4) {
						pane[i].setVisible(false);
					} else if (i == 5) {
						pane[i].setVisible(false);
					} else if (i == 6) {
						pane[i].setVisible(false);
					} else if (i == 7) {
						pane[i].setVisible(false);
					}

					else if (i == 8) {
						pane[i].setVisible(false);

					}
				}

			} else if (arg0.getSource() == Home[7]) {
				p.setVisible(false);
				c.setVisible(false);
				student.setVisible(false);
				m.setVisible(false);
				set.setVisible(true);

				set.listPanel.setVisible(false);
				set.container.setVisible(true);
				set.pane.setVisible(false);
				set.contants.setVisible(false);

				int visibled = 10;
				for (int i = 0; i < visibled; i++) {
					if (i == 0) {
						sl.setVisible(false);
						inDash[i].setVisible(false);
						lb1[i].setVisible(false);
					} else if (i == 1) {
						pane[i].setVisible(false);
						inDash[i].setVisible(false);
						lb1[i].setVisible(false);
					} else if (i == 2) {
						pane[i].setVisible(false);
						inDash[i].setVisible(false);
						lb1[i].setVisible(false);
					} else if (i == 3) {
						pane[i].setVisible(false);
					} else if (i == 4) {
						pane[i].setVisible(false);
					} else if (i == 5) {
						pane[i].setVisible(false);
					} else if (i == 6) {
						pane[i].setVisible(false);
					} else if (i == 7) {
						pane[i].setVisible(false);
					}

					else if (i == 8) {
						pane[i].setVisible(false);

					}
				}

			}

		}

		private void data() {
			Databases database = new Databases();
			ResultSet rs = null;
			try {
				String url = "jdbc:sqlite:login.db";
				String data = "select schoolName,logo from loging";
				database.classForname();
				database.con = DriverManager.getConnection(url);
				database.st = database.con.createStatement();
				rs = database.st.executeQuery(data);
				rs.next();
				if (rs.getString("schoolName").length() != 0) {
					String n = rs.getString("schoolName");
					String nt = n.substring(0, n.indexOf(" "));
					String ntt = n.substring(n.indexOf(" ")).substring(1);
					String ntts = ntt.substring(0, ntt.indexOf(" "));
					byte[] imageByte = rs.getBytes("logo");
					schoolname.setText(nt + " " + ntts);
					if (imageByte != null)
						ll.setIcon(new ImageIcon(new ImageIcon(imageByte).getImage().getScaledInstance(ll.getWidth(),
								ll.getHeight(), Image.SCALE_AREA_AVERAGING)));
					else {
						BufferedImage log = null;
						try {
							log = ImageIO.read(getClass().getResource("/image/UJUHU-01.png"));

						} catch (Exception e) {

						}
						ll.setIcon(new ImageIcon(new ImageIcon(log).getImage().getScaledInstance(ll.getWidth(),
								ll.getHeight(), Image.SCALE_AREA_AVERAGING)));

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					database.con.close();
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		}

	}

	public static void main(String[] args) {

		try {

			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		

		} catch (Exception e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				connect();

			}
		});

	}

}
