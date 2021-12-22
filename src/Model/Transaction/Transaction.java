package Model.Transaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Database.Connect;

public class Transaction {
	private int transactionId,totalPrice;	
	private String employeeName,voucher;
	private Date purchaseDate;
	private List<TransactionItem> listTransactionItem;
	
	public Transaction(int transactionId, int totalPrice, String employeeName, String voucher, Date purchaseDate,
			List<TransactionItem> items) {
		super();
		this.transactionId = transactionId;
		this.totalPrice = totalPrice;
		this.employeeName = employeeName;
		this.voucher = voucher;
		this.purchaseDate = purchaseDate;
		this.listTransactionItem = items;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getVoucher() {
		return voucher;
	}
	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public List<TransactionItem> getListTransactionItem() {
		return listTransactionItem;
	}
	public void setListTransactionItem(List<TransactionItem> listTransactionItem) {
		this.listTransactionItem = listTransactionItem;
	}
	private static Transaction setTransaction(ResultSet rs) {
		Transaction trans= null;
		String employeeName,voucher;
		int transactionId,totalPrice;
		Date purchaseDate = null;
		List<TransactionItem> items = new ArrayList<TransactionItem>();
		try {
			employeeName=rs.getString("employeeName");
			voucher=rs.getString("voucher");
			transactionId=rs.getInt("transactionID");
			totalPrice=rs.getInt("transactionTotalPrice");
			items=TransactionItem.getTransItem(transactionId);
			purchaseDate=rs.getDate("transactionDate");
			trans=new Transaction(transactionId, totalPrice, employeeName, voucher, purchaseDate, items);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return trans;
	}
	public static int getLastId() {
		int id=0;
		String query="SELECT trans.transactionID FROM transactions trans ORDER BY trans.transactionID DESC LIMIT 1";
		ResultSet rs= Connect.getConnection().execute(query);
		try {
			while(rs.next()) {
				id=rs.getInt("transactionID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	public static Transaction insertTransaction(String name, String voucher, int price) {
		// TODO Auto-generated method stub
		Transaction trans = null;
		String query="INSERT INTO transactions (transactionID, transactionDate, voucher, employeeName, transactionTotalPrice) "
				+ "VALUES (NULL, current_timestamp(), ?, ?, ?)";
		PreparedStatement prep = Connect.getConnection().preparedStatement(query);
		try {
			prep.setString(1, voucher);
			prep.setString(2,name);
			prep.setInt(3, price);
			if(prep.executeUpdate()!=0) {
				query="SELECT * FROM transactions ORDER BY transactions.transactionID DESC LIMIT 1";
				ResultSet rs = Connect.getConnection().execute(query);
				while(rs.next()) {
					//inserting transaction item
					TransactionItem.insertTransactionItem(rs.getInt("transactionID"));
					trans=setTransaction(rs);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return trans;
	}
}
