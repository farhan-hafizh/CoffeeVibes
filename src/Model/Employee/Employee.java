package Model.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Database.Connect;

public class Employee {
	private int employeeId,positionId,salary;
	private String name,status,username,password;
	
	
	protected Employee( int employeeId,int positionId, int salary, String name, String status, String username,
			String password) {
		this.employeeId = employeeId;
		this.positionId = positionId;
		this.salary = salary;
		this.name = name;
		this.status = status;
		this.username = username;
		this.password = password;
	}
	public int getEmployeId() {
		return employeeId;
	}
	public void setEmployee_id(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getPositionId() {
		return positionId;
	}
	public void setPositionId(int positionId) {
		this.positionId = positionId;
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
	//delete Employee
	public static boolean deleteEmployee(int employeeId) {
		int deleteStatus;
		String query=String.format("DELETE FROM employees WHERE employeeID=%d", employeeId);
		deleteStatus=Connect.getConnection().executeUpdate(query);
		if(deleteStatus==1)
			return true;
		else return false;
	}
	
	//for setting employee or list employee
	private static Employee setEmployee(ResultSet rs) {
		int employeeId,positionId,salary;
		String name,status,username,password;
		Employee emp=null;
		try {
		employeeId=rs.getInt("employeeID");
		positionId=rs.getInt("positionID");
		name=rs.getString("name");
		status=rs.getString("status");
		salary=rs.getInt("salary");
		username=rs.getString("username");
		password=rs.getString("password");
		employeeId=rs.getInt("employeeID");
		if(positionId==1)
			emp=new Barista(employeeId,positionId, salary, name, status, username, password);
		else if(positionId==2)
			emp=new ProductAdmin(employeeId,positionId, salary, name, status, username, password);
		else if(positionId==3)
			emp=new Manager(employeeId,positionId, salary, name, status, username, password);
		else
			emp=new HRD(employeeId,positionId, salary, name, status, username, password);
		}catch(Exception e){
			e.printStackTrace();
		}
		return emp;
	}
	//insert employee
	public static Employee insertEmployee(String name, int positionId, int salary, String username, String password) {
		int insertStatus=0;
		Employee emp=null;
		String status = "active";
		String query = "INSERT INTO employees (employeeID, positionID, name, status, salary, username, password)"
				+ " VALUES (NULL, ?, ?, ?, ?, ?, ?)";
		PreparedStatement prep = Connect.getConnection().preparedStatement(query);
		try {
			prep.setInt(1, positionId);
			prep.setString(2, name);
			prep.setString(3, status);
			prep.setInt(4, salary);
			prep.setString(5, username);
			prep.setString(5, password);
			
			insertStatus=prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(insertStatus!=0)
			emp = getEmployee(username, password);
		
		return emp;
	}
	//get all employee
	public static List<Employee> getAllEmployees() {
		Employee emp;
		
		String query= "SELECT * FROM employees";
	    List<Employee> data = new ArrayList<>();
		ResultSet rs = Connect.getConnection().execute(query);
		try {
			while(rs.next()) {
				emp=setEmployee(rs);
				data.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}
	//get employee
	public static Employee getEmployee(String username,String password) {
		Employee emp=null;
		String query;
		ResultSet rs;
		int flag=0;
		if(password != null) {
			query = "SELECT * FROM employees WHERE username = ? AND password= ?";
			flag=1;
		}else {
			query = "SELECT * FROM employees WHERE username=?";
		}
		
		PreparedStatement prep= Connect.getConnection().preparedStatement(query);			
		try {
			prep.setString(1, username);
			if(flag==1) {
				prep.setString(2, password);
			}
			rs = prep.executeQuery();
			while(rs.next()) {
				emp=setEmployee(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emp;
	}
	//update employee
	public static Employee updateEmployee(int employeeId, String name, int salary, String username, String password) {
		Employee emp=null;
		int updateStatus=0;
		String query="UPDATE employees SET name = ?, salary = ?, username = ?, password = ? WHERE employeeID = ?";
		PreparedStatement prep = Connect.getConnection().preparedStatement(query);
		try {
			prep.setString(1, name);
			prep.setInt(2, salary);
			prep.setString(3, username);
			prep.setString(4, password);
			prep.setInt(5, employeeId);
			
			updateStatus=prep.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(updateStatus!=0)
			emp = getEmployee(username, password);
		return emp;
	}
}
