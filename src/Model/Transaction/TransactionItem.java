package Model.Transaction;

public class TransactionItem {
	private int transactionId,productId,quantity;

	public TransactionItem(int transactionId, int productId, int quantity) {
		this.transactionId = transactionId;
		this.productId = productId;
		this.quantity = quantity;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}	
}
