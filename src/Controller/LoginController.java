package Controller;


import Model.Employee.Barista;
import Model.Employee.Employee;
import Model.Employee.HRD;
import Model.Employee.Manager;
import Session.LoginSession;
import Views.LoginPage;


public class LoginController {
	private static LoginPage loginPage=null;

	public LoginController() {
		
	}
	public static LoginPage displayLoginView() {
		if(loginPage == null)
			loginPage = new LoginPage();
		return loginPage;
	}
	public static void Login(String username, String password) {
		Employee emp = Employee.getEmployee(username, password);

		if(emp != null) {
			new LoginSession(emp);
			if(emp instanceof Barista) {
				System.out.println("asa");
			}else if(emp instanceof HRD) {
				System.out.println("asa");
			}else if(emp instanceof Manager) {
				System.out.println("asa");
			}else{
				System.out.println("asa");
			}
		};
	}
}
