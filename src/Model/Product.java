package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

import Database.Connect;

public class Product {
	private int productId,productPrice,productStock;
	private String productName, productDescription;
	
	public Product(int productId, int productPrice, int productStock, String productName, String productDescription) {
		super();
		this.productId = productId;
		this.productPrice = productPrice;
		this.productStock = productStock;
		this.productName = productName;
		this.productDescription = productDescription;
	}
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductStock() {
		return productStock;
	}
	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	public static Product getProduct(int productId) {
		ResultSet rs;
		Product product = null;
		String query="SELECT * FROM products WHERE products.productID="+productId;
		rs=Connect.getConnection().execute(query);
		try {
			while(rs.next()) {
				product = new Product(productId, rs.getInt("price"), rs.getInt("stock")
						, rs.getString("name"), rs.getString("description"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}	
}
