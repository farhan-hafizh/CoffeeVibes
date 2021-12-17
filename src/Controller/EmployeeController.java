package Controller;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import Model.Position;
import Model.Employee.Employee;
import Views.EmployeeManagementForm;

public class EmployeeController {
	
	private static EmployeeManagementForm view=null;
	
	public EmployeeController() {
		
	}
	
	
	public static void viewEmployeeManagementForm() {
			view = new EmployeeManagementForm();
	}
	public static void viewHome() {
		view.getFrame().dispose();
		HomeController.viewHomePage();
	}
	public static boolean deleteEmployee(int employeeId) {
		boolean deleteStatus= false;
		deleteStatus=Employee.deleteEmployee(employeeId);
		return deleteStatus;
	}
	
	public static List<Employee> getAllEmployee(){
		List<Employee> data= Employee.getAllEmployees();
		return data;
	}
	public static Employee getEmployee(String username) {
		Employee emp = Employee.getEmployee(username, null);
		return emp;
	}
	public static Employee updateEmployee(int employeeId,JTextField name, JTextField salary, JTextField username, JTextField password) {
		Employee emp=null;
		emp=Employee.updateEmployee(employeeId,name.getText(),Integer.parseInt(salary.getText()),username.getText(),password.getText());
		return emp;
	}

	public static Employee insertEmployee(List<Position> list,JTextField name, JComboBox position, JTextField salary, JTextField username,
			JTextField password) {
		// TODO Auto-generated method stub
		String selectedPosition = (String) position.getSelectedItem();
		int positionId=0;
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getName().equals(selectedPosition)) {
				positionId=list.get(i).getPositionId();
				break;
			}
		}
		Employee emp = Employee.insertEmployee(name.getText(), positionId, Integer.parseInt(salary.getText()), username.getText(), password.getText());
		
		return emp;
	}

}
