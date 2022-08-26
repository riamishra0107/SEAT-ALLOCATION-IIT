package allocation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AllocationDatabase {

	private String dbUrl = "jdbc:mysql://localhost:3306/allocate";
	private String dbUname = "root";
	private String dbPassword = "1234";
	private String dbDriver = "com.mysql.jdbc.Driver";
	int a = 0;

	public void loadDriver(String dbDriver) {
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		Connection connection = null;
		try {

			connection = DriverManager.getConnection(dbUrl, dbUname, dbPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public int insert(AllocatingSeats allocatingseats) throws SQLException {
		loadDriver(dbDriver);
		Connection connection = getConnection();
		String sql = "insert into Allocation values(?,?,?,?,?)";
		PreparedStatement preparedStatementToVerify = connection.prepareStatement("sql");
        
		try {
			preparedStatementToVerify = connection.prepareStatement(sql);

			preparedStatementToVerify.setString(1, allocatingseats.getFull_name());
			preparedStatementToVerify.setString(2, allocatingseats.getEmp_id());
			preparedStatementToVerify.setString(3, allocatingseats.getEmail());
			preparedStatementToVerify.setString(4, allocatingseats.getFloors());
			preparedStatementToVerify.setString(5, allocatingseats.getSeat_num());
			a = preparedStatementToVerify.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return a;

	}
}
