package Controller;

import java.util.ArrayList;
import java.util.List;

import Model.CartItem;
import Model.Product;

public class CartController {
	private static List<CartItem> listItem= new ArrayList<CartItem>();
	private static int totalPrice;
	
	public static List<CartItem> getListItem() {
		return listItem;
	}

	public static void setListItem(List<CartItem> listItem) {
		CartController.listItem = listItem;
	}

	public static int getTotalPrice() {
		return totalPrice;
	}

	public static void setTotalPrice(int totalPrice) {
		CartController.totalPrice = totalPrice;
	}
	
	private static CartItem updateCartProductQuantity(int productId, int quantity) {
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
	
	public static CartItem addToCart(int productId,int quantity) {
		CartItem item = null;
		Product product = ProductController.getProduct(productId);
		if(product != null) {
			item = new CartItem(product, quantity);
			if(listItem.contains(item)) {
				item=updateCartProductQuantity(productId, quantity);
				
			}else {
				listItem.add(item);
			}
			calculateTotalPrice();
		}
		int stock=product.getProductStock()-quantity;
		ProductController.updateProductStock(productId, stock);
		
		return item;
	}
	private static int calculateTotalPrice() {
		for (int i = 0; i < listItem.size(); i++) {
			totalPrice+=(listItem.get(i).getProduct().getProductPrice()*listItem.get(i).getQuantity());
		}
		return totalPrice;
	}
}
