package Model;

import java.util.Date;

public class Transaction {
	private int transaction_id,voucher_id,employee_id,totalPrice;
	private Date purchaseDate;
	
	public Transaction(int transaction_id, int voucher_id, int employee_id, int totalPrice, Date purchaseDate) {
		this.transaction_id = transaction_id;
		this.voucher_id = voucher_id;
		this.employee_id = employee_id;
		this.totalPrice = totalPrice;
		this.purchaseDate = purchaseDate;
	}
	
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	public int getVoucher_id() {
		return voucher_id;
	}
	public void setVoucher_id(int voucher_id) {
		this.voucher_id = voucher_id;
	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
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
}
