package Controller;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import Model.Position;
import Model.Employee.Employee;
import Views.EmployeeManagementForm;

public class EmployeeController {
	
	EmployeeManagementForm view=null;
	
	public EmployeeController() {
		
	}
	
	
	public void viewEmployeeManagementForm() {
			view = new EmployeeManagementForm();
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
	public static Employee updateEmployee() {
		Employee emp=null;
		return emp;
	}

	public static boolean insertEmployee(List<Position> list,JTextField name, JComboBox position, JTextField salary, JTextField username,
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
		if(emp != null) {
			return true;
		}
		return false;
		
	}

}
