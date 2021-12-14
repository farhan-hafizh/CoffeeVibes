package Controller;


import Model.Employee;
import Views.LoginPage;


public class LoginController {
	private static LoginPage loginPage;

	public LoginController() {
		
	}
	public static void displayLoginView() {
		loginPage = new LoginPage();
	}
	public static void Login(String username, String password) {
		System.out.println("b");
		Employee emp = Employee.getEmployee(username, password);

		if(emp != null) {
			System.out.println("a");
		};
	}
}
