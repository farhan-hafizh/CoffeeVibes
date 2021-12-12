package Model;

public class Product {
	private int product_id,product_price,product_stock;
	private String product_name, product_description;
	
	public Product(int product_id, int product_price, int product_stock, String product_name,
			String product_description) {
		
		this.product_id = product_id;
		this.product_price = product_price;
		this.product_stock = product_stock;
		this.product_name = product_name;
		this.product_description = product_description;
	
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public int getProduct_stock() {
		return product_stock;
	}
	public void setProduct_stock(int product_stock) {
		this.product_stock = product_stock;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_description() {
		return product_description;
	}
	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}
	
}
