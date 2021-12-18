package Controller;

import Model.Employee.Employee;
import Session.LoginSession;
import Views.LoginPage;
import Views.Dialogs.LoginFailed;


public class LoginController {
	private static LoginPage loginPage;

	public LoginController() {
		
	}
	public static void displayLoginView() {
			loginPage = new LoginPage();
	}
	
	public static void Login() {
		Employee emp=null;
		if(loginPage!=null) 
			emp = Employee.getEmployee(loginPage.getUsername().getText(), loginPage.getPassword().getText());
		if(emp != null) {
			LoginSession.setSession(emp);
			LoginPage.getFrame().dispose();
			HomeController.viewHomePage();;
		}else {
			LoginFailed dialog = new LoginFailed();
			dialog.setVisible(true);
		}
	}
}
