package Controller;

import java.util.List;

import javax.swing.JTextField;

import Model.Product;
import Views.Home;
import Views.ProductManagementForm;

public class ProductController {
	private static ProductManagementForm productManagementPage;
	
	//constructor
	public ProductController() {
		
	}
	
	public static void viewProductManagementForm() {
		productManagementPage= new ProductManagementForm();
	}
	
	public static Product getProduct(int productId) {
		Product product = Product.getProduct(productId);
		return product;
	}

	public static List<Product> getAllProducts() {
		List<Product> list = Product.getAllProducts();
		return list;
	}
	public static Product insertProduct(JTextField name, JTextField description, JTextField price,JTextField stock) {
		Product prod=Product.insertProduct(name.getText(),description.getText(),Integer.parseInt(price.getText()),Integer.parseInt(stock.getText()));
		return prod;
	}
	public static boolean deleteProduct(int productId) {
		boolean status=false;
		status=Product.deleteProduct(productId);
		return status;
	}
	public static Product updateProduct(int productId, JTextField name, JTextField description, JTextField price, JTextField stock) {
		Product prod = Product.updateProduct(productId,name.getText(),description.getText(),Integer.parseInt(price.getText()),Integer.parseInt(stock.getText()));
		return prod;
	}
	public static void updateProductStock(int productId, int stock) {
		Product product = Product.getProduct(productId);
		Product.updateProduct(productId, product.getProductName(), product.getProductDescription(), product.getProductPrice(), stock);
	}
	public static void viewHome() {
		// TODO Auto-generated method stub
		productManagementPage.getFrame().dispose();
		HomeController.viewHomePage();
	}
}
