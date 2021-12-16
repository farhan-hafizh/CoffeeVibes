
import java.util.List;

import Controller.EmployeeController;
import Controller.LoginController;
import Model.Employee.Employee;
import Views.LoginPage;

public class Main {
	public Main() {
		LoginController.displayLoginView();
//		List<Employee> list =EmployeeController.getAllEmployee();
//		System.out.println(list.get(0).getName());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
