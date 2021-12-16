package Controller;


import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Model.Employee.Barista;
import Model.Employee.Employee;
import Model.Employee.HRD;
import Model.Employee.Manager;
import Session.LoginSession;
import Views.EmployeeManagementForm;
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
			if(emp instanceof Barista) {
				System.out.println(LoginSession.getName());
			}else if(emp instanceof HRD) {
				loginPage.getFrame().dispose();
				new EmployeeManagementForm();
			}else if(emp instanceof Manager) {
				System.out.println("asa");
			}else{
				System.out.println("asa");
			}
		}else {
			LoginFailed dialog = new LoginFailed();
			dialog.setVisible(true);
		}
	}
}
