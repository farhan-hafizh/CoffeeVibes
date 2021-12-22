package Model.Transaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Controller.CartController;
import Database.Connect;
import Model.CartItem;

public class TransactionItem {
	private int transactionId,quantity,totalPrice;
	private String productName;

	public TransactionItem(int transactionId, int quantity, int totalPrice, String productName) {
		super();
		this.transactionId = transactionId;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.productName = productName;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	private static TransactionItem setTransactionItem(ResultSet rs) {
		TransactionItem items = null;
		int transactionId,quantity,totalPrice;
		String productName;
		
		try {
			while(rs.next()) {
				transactionId=rs.getInt("transactionID");
				quantity=rs.getInt("quantity");
				totalPrice=rs.getInt("totalPrice");
				productName=rs.getString("productName");
				items = new TransactionItem(transactionId, quantity, totalPrice, productName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return items;
	}
	public static List<TransactionItem> getTransItem(int transactionId) {
		// TODO Auto-generated method stub
		List<TransactionItem> trans = new ArrayList<TransactionItem>();
		TransactionItem trans1;
		String query="SELECT * FROM transactionItem WHERE transactionID= ?";
		PreparedStatement prep = Connect.getConnection().preparedStatement(query);
		
		try {
			prep.setInt(1, transactionId);
			ResultSet rs=prep.executeQuery();
			while(rs.next()) {
				trans1=setTransactionItem(rs);
				trans.add(trans1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return trans;
	}

	public static void insertTransactionItem(int transId) {
		// TODO Auto-generated method stub
		String query="INSERT INTO transactionitem (transactionID, productName, quantity, totalPrice) "
				+ "VALUES (?, ?, ?, ?)";
		PreparedStatement prep = Connect.getConnection().preparedStatement(query);
		try {
			prep.setInt(1, transId);
			List<CartItem> items = CartController.getListItem();
			for (int i = 0; i < items.size(); i++) {
				prep.setString(2, items.get(i).getProduct().getProductName());
				prep.setInt(3, items.get(i).getQuantity());
				prep.setInt(4, (items.get(i).getProduct().getProductPrice()*items.get(i).getQuantity()));
				prep.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
