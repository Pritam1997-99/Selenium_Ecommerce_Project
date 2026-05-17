package databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

public class JDBCConnection {
	@Test
	public void getData() throws SQLException {
		String user = "root";
		String password = "root";
		String port = "3306";
		String host = "localhost";
		Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/testecommerce", user,
				password);
		//DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/demo", "root", "root");
		Statement path = con.createStatement();
		//ResultSet rs = path.executeQuery("select * from EmployeeData where id=38");
		ResultSet rs = path.executeQuery("select * from EmployeeData");
		while(rs.next()) {
		System.out.println(rs.getString("name"));
		System.out.println(rs.getString("location"));
		}
	}

}
