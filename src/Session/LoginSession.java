package Session;

import Model.Employee.Employee;

public class LoginSession {
	private static Employee employee=null;
	
	private LoginSession(Employee emp) {
		employee=emp;
	}

	public static void setSession(Employee emp) {
		if(employee==null) {
			employee = emp;
		}
	}

	public static Employee getSession() {
		
		return employee;
	}
	
//		
}
