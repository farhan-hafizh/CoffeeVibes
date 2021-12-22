package Controller;

import java.util.List;

import Model.CartItem;
import Model.Transaction.Transaction;
import Session.LoginSession;
import Views.AllTransactionManagementForm;
import Views.TransactionManagementForm;

public class TransactionController {
	private static TransactionManagementForm view;
	private static AllTransactionManagementForm allView;
	
	public static void viewTransactionManagementForm() {
		// TODO Auto-generated method stub
		view= new TransactionManagementForm();
	}
	
	public static void viewAllTransactionManagementForm() {
		// TODO Auto-generated method stub
		allView= new AllTransactionManagementForm();
	}
	public static void viewCart() {
		// TODO Auto-generated method stub
		view.getFrame().dispose();
		CartController.viewCartMangament();
	}

	public static int calculateDiscount(int voucherDiscount, int price) {
		// TODO Auto-generated method stub
		float disc=voucherDiscount/100.0f;
		int discount=(int) (price*disc);
		return discount;
	}

	public static Transaction insertTransaction(List<CartItem> listItem, Object voucher, int price) {
		// TODO Auto-generated method stub
		Transaction trans=Transaction.insertTransaction(LoginSession.getSession().getName(),(String) voucher,price);
		return trans;
	}

	public static void viewHome() {
		// TODO Auto-generated method stub
		view.getFrame().dispose();
		HomeController.viewHomePage();
	}


}
