package Controller;


import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Model.Employee.Barista;
import Model.Employee.Employee;
import Model.Employee.HRD;
import Model.Employee.Manager;
import Session.LoginSession;
import Views.EmployeeManagementForm;
import Views.Home;
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
			new LoginSession(emp);
			loginPage.getFrame().dispose();
			HomeController.viewHomePage();;
		}else {
			LoginFailed dialog = new LoginFailed();
			dialog.setVisible(true);
		}
	}
}
