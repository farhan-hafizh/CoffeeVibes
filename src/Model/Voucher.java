package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.internal.C;

import Database.Connect;

public class Voucher {
	private int discount;
	private String voucherId,status;
	
	public Voucher(String voucherId, int discount, String status) {
		this.voucherId = voucherId;
		this.discount = discount;
		this.status = status;
	}
	
	public String getVoucherId() {
		return voucherId;
	}
	public void setVoucherId(String voucherId) {
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
		List<Voucher> list = new ArrayList<Voucher>();
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
		String voucherId = null;
		int discount = 0;
		String status = null;
		try {
			voucherId=rs.getString("voucherID");
			discount=rs.getInt("discount");
			status=rs.getString("status");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		voucher = new Voucher(voucherId, discount, status);
		return voucher;
	}

	public static Voucher generateVoucher(String voucherID, int discount) {
		// TODO Auto-generated method stub
		Voucher voucher = null;
		String status="Active";
		String query="INSERT INTO vouchers (voucherID, discount, status, created_at) VALUES (?, ?, ?, current_timestamp()) ";
		PreparedStatement prep= Connect.getConnection().preparedStatement(query);
		try {
			prep.setString(1, voucherID);
			prep.setInt(2, discount);
			prep.setString(3, status);
			if(prep.executeUpdate()!=0) {
				query="SELECT * FROM vouchers ORDER BY vouchers.created_at DESC LIMIT 1";
				ResultSet rs = Connect.getConnection().execute(query);
				while(rs.next()) {
					voucher=setVoucher(rs);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return voucher;
	}

	public static int deleteVoucher(String voucherId) {
		// TODO Auto-generated method stub
		int status=0;
		String query="DELETE FROM vouchers WHERE voucherID=?";
		PreparedStatement prep = Connect.getConnection().preparedStatement(query);
		
		try {
			prep.setString(1, voucherId);
			status=prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return status;
	}
	
	public static boolean updateStatus(String voucherId) {
		// TODO Auto-generated method stub
		int returns=0;
		String query="UPDATE vouchers SET status = 'Inactive' WHERE voucherID = ?";
		PreparedStatement prep = Connect.getConnection().preparedStatement(query);
		try {
			prep.setString(1, voucherId);
			returns=prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(returns!=0)
			return true;
		else return false;
	}

	public static Voucher getVoucher(String voucherID) {
		// TODO Auto-generated method stub
		Voucher voucher = null;
		String query="SELECT * FROM vouchers WHERE voucherID= ?";
		PreparedStatement prep = Connect.getConnection().preparedStatement(query);
		try {
			prep.setString(1, voucherID);
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				voucher=setVoucher(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return voucher;
	}
}
