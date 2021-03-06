package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
	private final String USERNAME="root";
	private final String PASSWORD="";
	private final String HOST ="localhost:3306";
	private final String DBNAME="coffeevibes";
	private final String DRIVER ="com.mysql.jdbc.Driver"; 
	
	private Statement st;
	private PreparedStatement ps;
	private Connection con;
	private static Connect connection=null;
	
	private Connect(){
		try {
			Class.forName(DRIVER);
			con = (Connection) DriverManager.getConnection("jdbc:mysql://"+HOST+"/"+DBNAME,USERNAME,PASSWORD);
			
			st=(Statement)con.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connect getConnection() {
		if( connection == null) {
			connection = new Connect();
		}
		
		return connection;
	}
	public ResultSet execute(String query) {
		ResultSet rs = null;
		
		try {
			rs = st.executeQuery(query);
//			st.close();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			 System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
		}
		return rs;
	}
	public int executeUpdate(String query) {
		int status=0;
		try {
			status = st.executeUpdate(query);
//			st.close();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
//			 System.out.println("SQLException: " + ex.getMessage());
//			    System.out.println("SQLState: " + ex.getSQLState());
//			    System.out.println("VendorError: " + ex.getErrorCode());
		}
		return status;
	}
	public PreparedStatement preparedStatement(String query) {
		ps = null;
		try {
			ps = con.prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	
}
