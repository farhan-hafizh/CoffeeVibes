package Model.Transaction;

import java.util.Date;

public class Transaction {
	private int transactionId,voucherId,employeeId,totalPrice;	
	private Date purchaseDate;
	private TransactionItem listTransactionItem;
	
	public Transaction(int transactionId, int voucherId, int employeeId, int totalPrice, Date purchaseDate,
			TransactionItem listTransactionItem) {
		super();
		this.transactionId = transactionId;
		this.voucherId = voucherId;
		this.employeeId = employeeId;
		this.totalPrice = totalPrice;
		this.purchaseDate = purchaseDate;
		this.listTransactionItem = listTransactionItem;
	}
	
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getVoucherId() {
		return voucherId;
	}
	public void setVoucherId(int voucherId) {
		this.voucherId = voucherId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public TransactionItem getListTransactionItem() {
		return listTransactionItem;
	}
	
	public void setListTransactionItem(TransactionItem listTransactionItem) {
		this.listTransactionItem = listTransactionItem;
	}
}
