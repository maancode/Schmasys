package schoolManegementSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.*;

public class TrashBackup extends JPanel {
	private static final long serialVersionUID = 1L;
	private JScrollPane spane;
	private JPanel container;
	private JPanel[] holders;
	private JLabel icon, lb, text;
	private JLabel folder[], namer[], fileName[], Date[], icons[];
	private JButton rest[], rem[];
	private JLabel wtch;
	private int rows;
	private byte[] image;

	public TrashBackup() {
		design();
		Container();
//		holders();
		title();

	}

	private void title() {
		BufferedImage id = null;
		try {
			id = ImageIO.read(getClass().getResource("/image/dle.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		icon = new JLabel(new ImageIcon(id));
		icon.setBounds(20, 15, 32, 32);
		container.add(icon);

		lb = new JLabel("Trash Backup");
		lb.setBounds(55, 15, 200, 40);
		lb.setFont(new Font("Roman", Font.BOLD, 25));
		lb.setForeground(Color.GRAY);
		container.add(lb);

		text = new JLabel("All deleted files and folder in this system, we kept here. You can take it back or remove.");
		text.setBounds(20, 60, 700, 30);
		text.setFont(new Font("Roman", Font.PLAIN, 16));
		text.setForeground(Color.gray);
		container.add(text);

	}

	public void operationCenter() {
		Databases dc1 = new Databases();
		try {
			
			dc1.classForname();
			dc1.con = DriverManager.getConnection("jdbc:sqlite:trash.db");
			dc1.st = dc1.con.createStatement();
			ResultSet rs1 = dc1.st.executeQuery("SELECT folderName from folder");
			if (rs1.next()) {
				ResultSet rs = dc1.st.executeQuery("SELECT folderName from folder");
				while (rs.next())
					rows = rs.getRow();
			} else {
				rows = 0;
			}
			dc1.con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dc1.con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		BufferedImage id = null;
		try {
			id = ImageIO.read(getClass().getResource("/image/bbb1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedImage ids = null;
		try {
			ids = ImageIO.read(getClass().getResource("/image/remm.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		int down = 120;
		holders = new JPanel[rows];
		folder = new JLabel[rows];
		rest = new JButton[rows];
		namer = new JLabel[rows];
		fileName = new JLabel[rows];
		Date = new JLabel[rows];
		rem = new JButton[rows];
		icons = new JLabel[rows];
		BufferedImage w = null;
		try {
			w = ImageIO.read(getClass().getResource("/image/wtch.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedImage file = null;
		String path[] = { "/image/folder (4).png", "/image/sheet.png" };

		for (int i = 0; i < holders.length; i++) {

			holders[i] = new JPanel();
			holders[i].setBounds(20, down, 1000, 100);
			holders[i].setBackground(Color.white);
			holders[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY));
			holders[i].setLayout(null);

			folder[i] = new JLabel();
			folder[i].setBounds(17, 0, 83, 70);
			folder[i].setBorder(BorderFactory.createMatteBorder(1, 0, 0, 1, Color.LIGHT_GRAY));
			holders[i].add(folder[i]);

			namer[i] = new JLabel();
			namer[i].setBounds(35, 70, 65, 30);
			namer[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.LIGHT_GRAY));
			namer[i].setFont(new Font("Roman", Font.BOLD, 15));
			namer[i].setForeground(Color.gray);
			holders[i].add(namer[i]);

			icons[i] = new JLabel();
			icons[i].setBounds(120, 10, 45, 50);
			holders[i].add(icons[i]);

			fileName[i] = new JLabel();
			fileName[i].setBounds(180, 20, 500, 30);
			fileName[i].setFont(new Font("Roman", Font.PLAIN, 18));
			fileName[i].setForeground(Color.GRAY);
			holders[i].add(fileName[i]);

			wtch = new JLabel();
			wtch.setBounds(120, 65, 24, 24);
			wtch.setIcon(new ImageIcon(new ImageIcon(w).getImage().getScaledInstance(wtch.getWidth(), wtch.getHeight(),
					Image.SCALE_AREA_AVERAGING)));

			Date[i] = new JLabel();
			Date[i].setBounds(155, 60, 500, 30);
			Date[i].setFont(new Font("Roman", Font.PLAIN, 15));
			Date[i].setForeground(Color.GRAY);

			holders[i].add(wtch);
			holders[i].add(Date[i]);

			rest[i] = new JButton(i + "");
			rest[i].setBorder(BorderFactory.createSoftBevelBorder(10));
			rest[i].setBounds(860, 15, 120, 30);
			rest[i].setIcon(new ImageIcon(new ImageIcon(id).getImage().getScaledInstance(rest[i].getWidth(),
					rest[i].getHeight(), Image.SCALE_AREA_AVERAGING)));
			rest[i].setOpaque(false);
			rest[i].setForeground(Color.white);
			rest[i].addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent ar) {

					int number = Integer.parseInt(ar.getActionCommand().intern());
					String checker = namer[number].getText();
					String restoringName = fileName[number].getText();
				

					if (checker.equals("File")) {
						
						
						executersThenInserters(restoringName);
						deleters(restoringName);
						
						String query = "update records set deleted = 0 where Student_name='" + restoringName + "'";
						update(query);
						deleterFolder(restoringName);
						
						container.remove(holders[number]);
						int down = 120;
						for(int i = number+1;i<holders.length;i++) {
							holders[i].setBounds(20, down, 1000, 100);
							down += 120;
						}
						container.setPreferredSize(new Dimension(0, down + 30));
						JOptionPane.showMessageDialog(null, "We'll get it back to it's old place, after few seconds","Schmasys",JOptionPane.INFORMATION_MESSAGE);
						
					} else {
						execuerThenInserterClass(restoringName);
						deletersClass(restoringName);
						String query = "update records set deleted = 0 where classname='" + restoringName + "'";
						update(query);
						deleterFolderClass(restoringName);
						inserterClass(restoringName);
						
						container.remove(holders[number]);
						int down = 120;
						for(int i = number+1;i<holders.length;i++) {
						
							holders[i].setBounds(20, down, 1000, 100);
							down += 120;
						}
						container.setPreferredSize(new Dimension(0, down + 30));
						JOptionPane.showMessageDialog(null, "We'll get it back to it's old place, after few seconds","Schmasys",JOptionPane.INFORMATION_MESSAGE);
						
						
					}

				}

				private void inserterClass(String restoringName) {
					Databases dc = new Databases();
					try {
						dc.classForname();
						dc.con = DriverManager.getConnection("jdbc:sqlite:class.db");
						dc.st = dc.con.createStatement();
						
						dc.st.executeUpdate("insert into classrooms  values('" + restoringName + "','Deen had removed','leader had removed')");

						
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
						try {
							dc.st.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				private void deletersClass(String restoringName) {
					Databases dc2 = new Databases();
					try {

						dc2.classForname();
						dc2.con = DriverManager.getConnection("jdbc:sqlite:trash.db");
						PreparedStatement pr;

						pr = dc2.con.prepareStatement("delete from fromStudent  where Student_class='" + restoringName + "'");
						pr.executeUpdate();

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

				private void deleterFolderClass(String restoringName) {
					Databases dc2 = new Databases();
					try {
						dc2.classForname();
						dc2.con = DriverManager.getConnection("jdbc:sqlite:trash.db");
						PreparedStatement pr;
						pr = dc2.con.prepareStatement("delete from  folder where folderName='" + restoringName + "'");
					pr.executeUpdate();
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

				private void execuerThenInserterClass(String restoringName) {
					Databases dc2 = new Databases();
					try {
						dc2.classForname();
						dc2.con = DriverManager.getConnection("jdbc:sqlite:trash.db");
						dc2.st = dc2.con.createStatement();
						String query = "select Student_name,Mother_name,Student_phone,Parent_phone,Student_Email,Student_class,Student_address,Birth_Date,Location_Birth,Student_gender,Student_image from fromStudent where Student_class='"
								+ restoringName + "'";

						ResultSet rs;

						rs = dc2.st.executeQuery(query);
						if (rs.next()) {
							ResultSet rs1 = dc2.st.executeQuery(query);
							String name = null;
							String classeser = null;
							while(rs1.next()) {
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
							classeser = rs1.getString("Student_class");
							String insert = "insert into student (Student_name,Mother_name,Student_phone,Student_image,"
									+ "Student_Email,Location_Birth,Birth_Date,"
									+ "Student_gender,Student_address, Parent_phone,Student_class ) values ('" + name
									+ "','" + mother + "','" + phone + "',?,'" + Email + "','" + Location + "','"
									+ datebirth + "','" + gender + "','" + Address + "','" + Pphone + "','" + classeser
									+ "')";
							insertStudent(insert);
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

				private void deleterFolder(String restoringName) {
					Databases dc2 = new Databases();
					try {
						dc2.classForname();
						dc2.con = DriverManager.getConnection("jdbc:sqlite:trash.db");
						PreparedStatement pr;
						pr = dc2.con.prepareStatement("delete from  folder where folderName='" + restoringName + "'");
					pr.executeUpdate();
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

				private void executersThenInserters(String classes) {
					Databases dc2 = new Databases();
					try {
						dc2.classForname();
						dc2.con = DriverManager.getConnection("jdbc:sqlite:trash.db");
						dc2.st = dc2.con.createStatement();
						String query = "select Student_name,Mother_name,Student_phone,Parent_phone,Student_Email,Student_class,Student_address,Birth_Date,Location_Birth,Student_gender,Student_image from fromStudent where Student_name='"
								+ classes + "'";

						ResultSet rs;

						rs = dc2.st.executeQuery(query);
						if (rs.next()) {
							ResultSet rs1 = dc2.st.executeQuery(query);
							String name = null;
							String classeser = null;
							rs1.next();
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
							classeser = rs1.getString("Student_class");
							String insert = "insert into student (Student_name,Mother_name,Student_phone,Student_image,"
									+ "Student_Email,Location_Birth,Birth_Date,"
									+ "Student_gender,Student_address, Parent_phone,Student_class ) values ('" + name
									+ "','" + mother + "','" + phone + "',?,'" + Email + "','" + Location + "','"
									+ datebirth + "','" + gender + "','" + Address + "','" + Pphone + "','" + classeser
									+ "')";
							insertStudent(insert);

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

				private void update(String query) {
					Databases dc2 = new Databases();
					try {

						dc2.classForname();
						dc2.con = DriverManager.getConnection("jdbc:sqlite:students.db");
						dc2.st = dc2.con.createStatement();
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

				private void insertStudent(String insert) {
					Databases dc3 = new Databases();
					try {

						dc3.classForname();
						dc3.con = DriverManager.getConnection("jdbc:sqlite:students.db");
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

			});

			holders[i].add(rest[i]);

			rem[i] = new JButton(i + "");
			rem[i].setBorder(BorderFactory.createSoftBevelBorder(10));
			rem[i].setBounds(860, 57, 120, 30);
			rem[i].setIcon(new ImageIcon(new ImageIcon(ids).getImage().getScaledInstance(rest[i].getWidth(),
					rem[i].getHeight(), Image.SCALE_AREA_AVERAGING)));
			rem[i].setOpaque(false);
			rem[i].setForeground(Color.white);
			rem[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ar) {

					int number = Integer.parseInt(ar.getActionCommand().intern());

					String restoringName = fileName[number].getText();
					int no = JOptionPane.showConfirmDialog(null, "Are you sure to remove out of the System?", "Schmasys warring",
							JOptionPane.OK_CANCEL_OPTION);
					if (no == 0) {
						deleters(restoringName);						
						deleterFolder(restoringName);
					container.remove(holders[number]);
					int down = 120;
					for(int i = number+1;i<holders.length;i++) {
						holders[i].setBounds(20, down, 1000, 100);
						down += 120;
					}
					container.setPreferredSize(new Dimension(0, down + 30));
					JOptionPane.showMessageDialog(null, "We'll Remove it from the system, after few seconds","Schmasys",JOptionPane.INFORMATION_MESSAGE);
					}
				}
				private void deleterFolder(String restoringName) {
					Databases dc2 = new Databases();
					try {
						dc2.classForname();
						dc2.con = DriverManager.getConnection("jdbc:sqlite:trash.db");
						PreparedStatement pr;
						pr = dc2.con.prepareStatement("delete from  folder where folderName='" + restoringName + "'");
					pr.executeUpdate();
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
			holders[i].add(rem[i]);

			down += 120;
			container.add(holders[i]);

		}

		container.setPreferredSize(new Dimension(0, down + 30));
		
//		System.out.println(Arrays.toString(holders));
	
		try {

			dc1.classForname();
			dc1.con = DriverManager.getConnection("jdbc:sqlite:trash.db");
			dc1.st = dc1.con.createStatement();
			ResultSet rs = dc1.st.executeQuery("SELECT folderName , notes,dates from folder");
			while (rs.next()) {
				if (rs.getInt("notes") == 1) {
					file = ImageIO.read(getClass().getResource(path[1]));

					folder[rs.getRow() - 1].setIcon(new ImageIcon(
							new ImageIcon(file).getImage().getScaledInstance(64, 64, Image.SCALE_AREA_AVERAGING)));
					namer[rs.getRow() - 1].setText("File");
					Date[rs.getRow() - 1].setText(rs.getString("dates"));
					fileName[rs.getRow() - 1].setText(rs.getString("folderName"));
					name(rs.getString("folderName"), (rs.getRow() - 1));
				} else {
					file = ImageIO.read(getClass().getResource(path[0]));
					folder[rs.getRow() - 1].setIcon(new ImageIcon(
							new ImageIcon(file).getImage().getScaledInstance(64, 64, Image.SCALE_AREA_AVERAGING)));
					namer[rs.getRow() - 1].setText("folder");
					Date[rs.getRow() - 1].setText(rs.getString("dates"));
					fileName[rs.getRow() - 1].setText(rs.getString("folderName"));
					file = ImageIO.read(getClass().getResource("/image/board.png"));
					icons[rs.getRow() - 1].setIcon(new ImageIcon(
							new ImageIcon(file).getImage().getScaledInstance(icons[rs.getRow() - 1].getWidth(),
									icons[rs.getRow() - 1].getHeight(), Image.SCALE_AREA_AVERAGING)));

				}
			}
			dc1.con.close();
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

	private void deleters(String name) {
		Databases dc2 = new Databases();
		try {

			dc2.classForname();
			dc2.con = DriverManager.getConnection("jdbc:sqlite:trash.db");
			PreparedStatement pr;

			pr = dc2.con.prepareStatement("delete from fromStudent  where Student_name='" + name + "'");
			pr.executeUpdate();

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

	private void name(String name, int i) {
		Databases dc1 = new Databases();
		try {
			
			dc1.classForname();
			dc1.con = DriverManager.getConnection("jdbc:sqlite:trash.db");
			dc1.st = dc1.con.createStatement();
			String query = "SELECT Student_image,Student_gender from fromStudent where Student_name='" + name + "'";
			ResultSet rs1 = dc1.st.executeQuery(query);
			if (rs1.next()) {
				ResultSet rs = dc1.st.executeQuery(query);
				rs.next();
				String gender = rs.getString("Student_gender");
				byte[] image = rs.getBytes("Student_image");
				if (image == null) {
					if (gender.equalsIgnoreCase("female")) {
						BufferedImage im = null;
						try {
							im = ImageIO.read(getClass().getResource("/image/tF.png"));
						} catch (Exception e) {
						}
						ImageIcon imn = new ImageIcon(new ImageIcon(im).getImage().getScaledInstance(
								icons[i].getWidth(), icons[i].getHeight(), Image.SCALE_AREA_AVERAGING));
						icons[i].setIcon(imn);
					} else {
						BufferedImage im = null;
						try {
							im = ImageIO.read(getClass().getResource("/image/tM.png"));
						} catch (Exception e) {
						}
						ImageIcon imn = new ImageIcon(new ImageIcon(im).getImage().getScaledInstance(
								icons[i].getWidth(), icons[i].getHeight(), Image.SCALE_AREA_AVERAGING));
						icons[i].setIcon(imn);
					}
				} else {
					ImageIcon imn = new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(icons[i].getWidth(),
							icons[i].getHeight(), Image.SCALE_AREA_AVERAGING));
					icons[i].setIcon(imn);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dc1.con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void Container() {
		container = new JPanel();
		container.setBackground(Color.white);
		container.setPreferredSize(new Dimension(0, 690));
		container.setLayout(null);

		spane = new JScrollPane(container);
		spane.setBounds(0, 0, 1049, 675);
		spane.setBackground(Color.white);
		spane.setBorder(BorderFactory.createSoftBevelBorder(10));
		add(spane);
	}

	private void design() {
		setVisible(false);
		setLayout(null);
		setBackground(Color.white);
		setBounds(280, 30, 1045, 690);
	}

}
