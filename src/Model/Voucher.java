package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Database.Connect;

public class Voucher {
	private int voucherId,discount;
	private String status;
	
	public Voucher(int voucherId, int discount, String status) {
		this.voucherId = voucherId;
		this.discount = discount;
		this.status = status;
	}
	
	public int getVoucherId() {
		return voucherId;
	}
	public void setVoucherId(int voucherId) {
		this.voucherId = voucherId;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public static List<Voucher> getAllVoucher() {
		// TODO Auto-generated method stub
		List<Voucher> list = null;
		Voucher voucher;
		String query="SELECT * FROM vouchers";
		ResultSet rs = Connect.getConnection().execute(query);
		try {
			while(rs.next()) {
				voucher=setVoucher(rs);
				list.add(voucher);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	private static Voucher setVoucher(ResultSet rs) {
		// TODO Auto-generated method stub
		Voucher voucher;
		int voucherId = 0,discount = 0;
		String status = null;
		try {
			voucherId=rs.getInt("voucherID");
			discount=rs.getInt("discount");
			status=rs.getString("status");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		voucher = new Voucher(voucherId, discount, status);
		return voucher;
	}
}
