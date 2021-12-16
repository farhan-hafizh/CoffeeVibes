package Model;

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
}
