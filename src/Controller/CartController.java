package Controller;

import java.util.ArrayList;
import java.util.List;

import Model.CartItem;
import Model.Product;

public class CartController {
	private List<CartItem> listItem= new ArrayList<CartItem>();
	
	private CartItem updateCartProductQuantity(int productId, int quantity) {
		CartItem item = null;
		int productQty=0;
		for (int i = 0; i < listItem.size(); i++) {
			if(listItem.get(i).getProduct().getProductId()==productId) {
				//set quantity
				productQty=listItem.get(i).getQuantity();
				listItem.get(i).setQuantity(productQty+quantity);
				item=listItem.get(i);
				break;
			}
		}
		return item;
	}
	
	public CartItem addToCart(int productId,int quantity) {
		CartItem item = null;
		Product product = ProductController.getProduct(productId);
		if(product != null) {
			item = new CartItem(product, quantity);
			if(listItem.contains(item)) {
				item=updateCartProductQuantity(productId, quantity);
			}else {
				listItem.add(item);
			}
		}
		return item;
	}
	public int calculateTotalPrice() {
		int totalPrice=0;
		for (int i = 0; i < listItem.size(); i++) {
			totalPrice+=(listItem.get(i).getProduct().getProductPrice()*listItem.get(i).getQuantity());
		}
		return totalPrice;
	}
}
