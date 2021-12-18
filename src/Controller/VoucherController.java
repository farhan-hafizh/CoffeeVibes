package Controller;

import java.util.List;

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

}
