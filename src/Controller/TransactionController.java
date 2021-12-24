package Controller;

import java.util.List;

import Model.CartItem;
import Model.Voucher;
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
	
	private static int calculateDiscount(int voucherDiscount, int price) {
		// TODO Auto-generated method stub
		float disc=voucherDiscount/100.0f;
		int discount=(int) (price*disc);
		return discount;
	}

	public static Transaction insertTransaction(List<CartItem> listItem, Object voucher, int price) {
		// TODO Auto-generated method stub
		Transaction trans=Transaction.insertTransaction(LoginSession.getSession().getName(),(String) voucher,price);
		if(trans!=null) {
			boolean status=VoucherController.updateStatus((String) voucher);
		}
		return trans;
	}

	public static void viewHome() {
		// TODO Auto-generated method stub
		view.getFrame().dispose();
		HomeController.viewHomePage();
	}

	public static List<Transaction> getAllTransaction() {
		// TODO Auto-generated method stub
		List<Transaction> list = Transaction.getAllTransaction();
		return list;
	}

	public static int getVoucher(String voucherID,int price) {
		// TODO Auto-generated method stub
		int discounts=0;
		Voucher voucher = VoucherController.getVoucher(voucherID);
		if(voucher== null)
			return 0;
		else {
			System.out.println(voucher.getStatus());
			if(voucher.getStatus().equals("Inactive"))
				return 0;
			else {
				discounts=calculateDiscount(voucher.getDiscount(), price);
				return discounts;
			}
		}
	}


}
