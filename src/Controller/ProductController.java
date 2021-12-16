package Controller;

import Model.Product;

public class ProductController {
	public static Product getProduct(int productId) {
		Product product = Product.getProduct(productId);
		return product;
	}
}
