package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//driver method to load our jdbc driver
public class loginDatabase {
	private String dbUrl = "jdbc:mysql://localhost:3306/authentication";
	private String dbUname = "root";
	private String dbPassword = "1234";
	private String dbDriver = "com.mysql.jdbc.Driver";

	public void loadDriver(String dbdriver) {
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(dbUrl, dbUname, dbPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	public boolean validate(LoginBean loginbean) {
		loadDriver(dbDriver);
		Connection con = getConnection();
		boolean status = false;
		String sql = "select * from login where username = ? and password =?";

		PreparedStatement preparedstatement;
		try {
			preparedstatement = con.prepareStatement(sql);
			preparedstatement.setString(1, loginbean.getUsername());
			preparedstatement.setString(2, loginbean.getPassword());

			ResultSet resultset = preparedstatement.executeQuery();
			status = resultset.next();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return status;
	}
}
	