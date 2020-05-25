package schoolManegementSystem;

import java.sql.*;


public class Databases {
	Connection con;
	Statement st;

	public Databases() {
		classForname();
	}

	public void classForname() {
		try {

			Class.forName("org.sqlite.JDBC");

		} catch (Exception e) {
		System.out.println(e);
		}
	}

}
