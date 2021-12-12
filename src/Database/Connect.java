package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
	private final String USERNAME="root";
	private final String PASSWORD="";
	private final String HOST ="localhost:8080";
	private final String DBNAME="coffeevibes";
	
	private Statement st;
	private static Connect connection=null;
	
	private Connect(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://"+HOST+"/"+DBNAME,USERNAME,PASSWORD);
			
			st=con.createStatement();
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
}
