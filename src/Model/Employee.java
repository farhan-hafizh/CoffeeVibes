package Model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Database.Connect;

public class Employee {
	private int position_id,salary;
	private String name,status,username,password;
	
	
	private Employee( int position_id, int salary, String name, String status, String username,
			String password) {
//		this.employee_id = employee_id;
		this.position_id = position_id;
		this.salary = salary;
		this.name = name;
		this.status = status;
		this.username = username;
		this.password = password;
	}
//	public int getEmployee_id() {
//		return employee_id;
//	}
//	public void setEmployee_id(int employee_id) {
//		this.employee_id = employee_id;
//	}
	public int getPosition_id() {
		return position_id;
	}
	public void setPosition_id(int position_id) {
		this.position_id = position_id;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Employee insertEmployee(String name, int position_id, int salary, String username, String password) {
		String status = "active";
		String query = "INSERT INTO employees (employeeID, positionID, name, status, salary, username, password) VALUES "
				+ "(NULL, '"+position_id+"', '"+name+"', '"+status+"', '"+salary+"', '"+username+"', '"+password+"') ";
		ResultSet rs= Connect.getConnection().execute(query);
		
		try {
			rs.next();//move to data
//			employee_id=rs.getInt("employeeID");
			position_id=rs.getInt("positionID");
			name=rs.getString("name");
			status=rs.getString("status");
			salary=rs.getInt("salary");
			username=rs.getString("username");
			password=rs.getString("password");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Employee emp = new Employee(position_id, salary, name, status, username, password);
		
		return emp;
	}
	public static List<Employee> getAllEmployees() {
		int position_id,salary;
		String name,status,username,password;
		Employee emp;
		
		String query= "SELECT * FROM employees";
	    List<Employee> data = new ArrayList<>();
		ResultSet rs = Connect.getConnection().execute(query);
		try {
			while(rs.next()) {
//				employee_id=rs.getInt("employeeID");
				position_id=rs.getInt("positionID");
				name=rs.getString("name");
				status=rs.getString("status");
				salary=rs.getInt("salary");
				username=rs.getString("username");
				password=rs.getString("password");
				emp=new Employee(position_id, salary, name, status, username, password);
				data.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}
	public static Employee getEmployee(String username,String password) {
		int position_id,salary;
		String name,status;
		Employee emp=null;
		String query;
		
		if(password != null) {
			query = "SELECT * FROM employees emp WHERE emp.username='"+username+"' AND emp.password='"+password+"'";
		}else {
			query = "SELECT * FROM employees emp WHERE emp.username='"+username+"'";
		}
		ResultSet rs = Connect.getConnection().execute(query);
		try {
			while(rs.next()) {
				position_id=rs.getInt("positionID");
				name=rs.getString("name");
				status=rs.getString("status");
				salary=rs.getInt("salary");
				username=rs.getString("username");
				password=rs.getString("password");
				emp=new Employee(position_id, salary, name, status, username, password);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emp;
	}
}
