package Controller;

import java.util.List;

import Model.Employee.Employee;

public class EmployeeController {
	
	public EmployeeController() {
		
	}
	
	public void viewEmployeeManagementForm() {
		
	}
	public Employee insertEmployee() {
		Employee emp=null;
		return emp;
	}
	public List<Employee> getAllEmployee(){
		List<Employee> data= Employee.getAllEmployees();
		return data;
	}
	public Employee getEmployee(String username) {
		Employee emp = Employee.getEmployee(username, null);
		return emp;
	}
	public Employee updateEmployee() {
		Employee emp=null;
		return emp;
	}
}
