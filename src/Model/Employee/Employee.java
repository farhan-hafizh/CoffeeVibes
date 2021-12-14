package Model.Employee;

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
	public Employee insertEmployee(String name, int positionId, int salary, String username, String password) {
		String status = "active";
		String query = "INSERT INTO employees (employeeID, positionID, name, status, salary, username, password) VALUES "
				+ "(NULL, '"+positionId+"', '"+name+"', '"+status+"', '"+salary+"', '"+username+"', '"+password+"') ";
		ResultSet rs= Connect.getConnection().execute(query);
		
		try {
			rs.next();//move to data
//			employee_id=rs.getInt("employeeID");
			positionId=rs.getInt("positionID");
			name=rs.getString("name");
			status=rs.getString("status");
			salary=rs.getInt("salary");
			username=rs.getString("username");
			password=rs.getString("password");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Employee emp = new Employee(employeeId,positionId, salary, name, status, username, password);
		
		return emp;
	}
	public static List<Employee> getAllEmployees() {
		int employeeId,positionId,salary;
		String name,status,username,password;
		Employee emp;
		
		String query= "SELECT * FROM employees";
	    List<Employee> data = new ArrayList<>();
		ResultSet rs = Connect.getConnection().execute(query);
		try {
			while(rs.next()) {
//				employee_id=rs.getInt("employeeID");
				positionId=rs.getInt("positionID");
				name=rs.getString("name");
				status=rs.getString("status");
				salary=rs.getInt("salary");
				username=rs.getString("username");
				password=rs.getString("password");
				employeeId=rs.getInt("employeeID");
				emp=new Employee(employeeId,positionId, salary, name, status, username, password);
				data.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}
	public static Employee getEmployee(String username,String password) {
		int employeeId,positionId,salary;
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
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emp;
	}
}
