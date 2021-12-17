package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.Connect;

public class Product {
	private int productId,productPrice,productStock;
	private String productName, productDescription;
	
	public Product(int productId, int productPrice, int productStock, String productName, String productDescription) {
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

	public static List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		List<Product> list = new ArrayList<Product>();
		ResultSet rs;
		Product prod;
		String query="SELECT * FROM products";
		rs=Connect.getConnection().execute(query);
		try {
			while(rs.next()) {
				prod=setProduct(rs);
				list.add(prod);
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	private static Product setProduct(ResultSet rs) {
		// TODO Auto-generated method stub
		Product prod=null;
		int productId,productStock,productPrice;
		String productDescription,productName;
		try {
			productId=rs.getInt("productID");
			productPrice=rs.getInt("price");
			productStock=rs.getInt("stock");
			productName=rs.getString("name");
			productDescription=rs.getString("description");
			prod= new Product(productId, productPrice, productStock, productName, productDescription);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return prod;
	}

	public static Product insertProduct(String name, String desc, int price, int stock) {
		// TODO Auto-generated method stub
		Product prod = null;
		String query=String.format("INSERT INTO products (productID, name, description, price, stock) VALUES (NULL, '%s','%s','%d','%d')",name,desc,price,stock);
		int status = Connect.getConnection().executeUpdate(query);
		if(status==1) {
			query="SELECT * FROM products ORDER BY products.productID DESC LIMIT 1";
			ResultSet rs = Connect.getConnection().execute(query);
			try {
				while(rs.next()) {
					prod=setProduct(rs);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return prod;
	}

	public static boolean deleteProduct(int productId) {
		// TODO Auto-generated method stub
		String query="DELETE FROM products WHERE productID=?";
		PreparedStatement prep =Connect.getConnection().preparedStatement(query);
		int status = 0;
		try {
			prep.setInt(1, productId);
			status = prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(status==1) return true;
		else return false;
	}

	public static Product updateProduct(int productId, String name, String desc, int price, int stock) {
		// TODO Auto-generated method stub
		int status=0;
		Product prod = null;
		String query="UPDATE products SET name = ?, description = ?, price = ?, stock = ? WHERE productID = ?";
		PreparedStatement prep = Connect.getConnection().preparedStatement(query);
		try {
			prep.setString(1, name);
			prep.setString(2, desc);
			prep.setInt(3, price);
			prep.setInt(4, stock);
			prep.setInt(5, productId);
			
			status = prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(status == 1) {
			prod=getProduct(productId);
		}
		return prod;
	}	
}
