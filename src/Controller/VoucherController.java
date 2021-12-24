package Controller;

import java.util.List;

import javax.swing.JTextField;

import Model.Voucher;
import Views.VoucherManagementForm;

public class VoucherController {
	
	private static VoucherManagementForm view;
	
	public static void viewVoucherManagementForm() {
		view=new VoucherManagementForm();
	}
	
	public static List<Voucher> getAllVoucher() {
		// TODO Auto-generated method stub
		List<Voucher> list = Voucher.getAllVoucher();
		return list;
	}
	public static Voucher generateVoucher(JTextField voucherID, JTextField discount) {
		Voucher voucher = Voucher.generateVoucher(voucherID.getText(),Integer.valueOf(discount.getText()));
		return voucher;
	}
	public static void backHome() {
		view.getFrame().dispose();
		HomeController.viewHomePage();
	}

	public static boolean deleteVoucher(JTextField voucherID) {
		// TODO Auto-generated method stub
		int status=0;
		status=Voucher.deleteVoucher(voucherID.getText());
		if(status!=0)
			return true;
		else return false;
	}

	public static boolean updateStatus(String voucher) {
		// TODO Auto-generated method stub
		boolean status=Voucher.updateStatus(voucher);
		return status;
	}

	public static Voucher getVoucher(String voucherID) {
		// TODO Auto-generated method stub
		Voucher voucher = Voucher.getVoucher(voucherID);
		return voucher;
	}
}
