package Session;

import Model.Employee.Employee;

public class LoginSession {
	private static Employee employee;
	
	public LoginSession(Employee emp) {
		employee=emp;
	}

	public static void setEmployee(Employee employee) {
		LoginSession.employee = employee;
	}

	public static Employee getEmployee() {
		return employee;
	}
	
//		
}
