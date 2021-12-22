package Controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Model.CartItem;
import Model.Product;
import Views.CartManagementForm;

public class CartController {
	private static List<CartItem> listItem= new ArrayList<CartItem>();
	private static int totalPrice;
	private static CartManagementForm view;
	
	public static void viewCartMangament() {
		view=new CartManagementForm();
	}
	
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
		int flag=0;
		Product product = ProductController.getProduct(productId);
		CartItem item = new CartItem(product, quantity);
		if(product != null) {
			for (int i = 0; i < listItem.size(); i++) {
				if(listItem.get(i).getProduct().getProductId()==productId) {
					flag++;
				}				
			}
			if(flag!=0)
				item=updateCartProductQuantity(productId, quantity);
			else listItem.add(item);
			totalPrice=calculateTotalPrice();
		}
		int stock=product.getProductStock()-quantity;
		ProductController.updateProductStock(productId, stock);
		
		return item;
	}
	private static int calculateTotalPrice() {
		int totalPrice=0;
		for (int i = 0; i < listItem.size(); i++) {
			totalPrice+=(listItem.get(i).getProduct().getProductPrice()*listItem.get(i).getQuantity());
		}
		return totalPrice;
	}

	public static void viewHome() {
		// TODO Auto-generated method stub
		view.getFrame().dispose();
		HomeController.viewHomePage();
	}

	public static void viewTransactionManagementForm() {
		// TODO Auto-generated method stub
		view.getFrame().dispose();
		if(listItem.size()>0)
			TransactionController.viewTransactionManagementForm();
		else JOptionPane.showMessageDialog(null, "No item found in Cart!");
	}

	public static boolean deleteItem(int productId) {
		// TODO Auto-generated method stub
		int qty,stock;
		boolean status=false;
		Product product=ProductController.getProduct(productId);
		for (int i = 0; i < listItem.size(); i++) {
			if(listItem.get(i).getProduct().getProductId()==productId) {
				qty=listItem.get(i).getQuantity();
				stock=product.getProductStock()+qty;
				totalPrice=0;
				listItem.remove(i);
				status=ProductController.updateProductStock(productId, stock);
			}
		}
		return status;
	}
}
