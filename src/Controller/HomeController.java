package Controller;

import Views.Home;

public class HomeController {
	private static Home homePage;
	
	public static void viewHomePage() {
		homePage=new Home();
	}
	public static void viewEmployeeManagementForm() {
		homePage.getFrame().dispose();
		EmployeeController.viewEmployeeManagementForm();
	}
	public static void viewProductManagementForm() {
		homePage.getFrame().dispose();
		ProductController.viewProductManagementForm();;
	}
	public static void viewCartManagementForm() {
		homePage.getFrame().dispose();
		CartController.viewCartMangament();
	}
	public static void viewVoucherManagementForm() {
		homePage.getFrame().dispose();
		VoucherController.viewVoucherManagementForm();
	}
	public static void viewAllTransactionForm() {
		homePage.getFrame().dispose();
		TransactionController.viewTransactionManagementForm();
	}

}
