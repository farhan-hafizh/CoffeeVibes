package Session;

import Model.Employee.Employee;

public class LoginSession {
	private static int positionId,salary;
	private static String name,status,username,password;

	
	public LoginSession(Employee emp) {
		positionId=emp.getPositionId();
		name= emp.getName();
		status=emp.getStatus();
		salary=emp.getSalary();
		username=emp.getUsername();
		password=emp.getPassword();
	}
	
//	public static int getEmployeeId() {
//		return employeeId;
//	}
//	public static void setEmployeeId(int employeeId) {
//		LoginSession.employeeId = employeeId;
//	}
	public static int getPositionId() {
		return positionId;
	}
	public static void setPositionId(int positionId) {
		LoginSession.positionId = positionId;
	}
	public static String getName() {
		return name;
	}
	public static void setName(String name) {
		LoginSession.name = name;
	}
	public static String getStatus() {
		return status;
	}
	public static void setStatus(String status) {
		LoginSession.status = status;
	}
	public static int getSalary() {
		return salary;
	}
	public static void setSalary(int salary) {
		LoginSession.salary = salary;
	}
	public static String getUsername() {
		return username;
	}
	public static void setUsername(String username) {
		LoginSession.username = username;
	}
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String password) {
		LoginSession.password = password;
	}
	
}
