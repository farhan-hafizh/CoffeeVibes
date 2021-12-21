package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.CartController;
import Controller.VoucherController;
import Model.CartItem;
import Model.Voucher;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class CartManagementForm {

	private JFrame frame;
	private JTable table;
	
	private DefaultTableModel model;
	private HashMap<Integer, String> map;
	private JComboBox voucherId;
	/*
	 * Create table
	 * */
	private JTable getTable() {

	    String[] colName = {
				"Product Id", "Product Name", "Product Price", "Qty", "Total Price"
			};
	    if (table == null) {
	        table = new JTable();
	    }
	    model = (DefaultTableModel) table.getModel();
	    model.setColumnIdentifiers(colName);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		return table;
	}
//	set table
	private void setTable() {
		List<CartItem> list1 = CartController.getListItem();
		int price,qty;
		model.getDataVector().removeAllElements();
		model =(DefaultTableModel) table.getModel();
		for (int i = 0; i < list1.size(); i++) {
			qty=list1.get(i).getQuantity();
			price=list1.get(i).getProduct().getProductPrice();
			String data[]= new String[5];
			data[0]=String.valueOf(list1.get(i).getProduct().getProductId()) ;
			data[1]=list1.get(i).getProduct().getProductName();
			data[2]=String.valueOf(price);
			data[3]=String.valueOf(qty);
			data[4]=String.valueOf(price*qty);
			model.addRow(data);
		}
		table.setModel(model);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CartManagementForm window = new CartManagementForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CartManagementForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 500, 860);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(11, 10, 460, 433);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 436, 407);
		panel.add(scrollPane);
		
		getTable();
		setTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Total Price :  Rp. ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(21, 491, 155, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel totalPrice = new JLabel(String.valueOf(CartController.getTotalPrice()));
		totalPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		totalPrice.setBounds(183, 491, 114, 25);
		frame.getContentPane().add(totalPrice);
		
		JButton btnNewButton = new JButton("Check Out");
		btnNewButton.setBounds(353, 593, 97, 45);
		frame.getContentPane().add(btnNewButton);
		
		List<Voucher> list = VoucherController.getAllVoucher();
		String[] vouchers= new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			vouchers[i]=list.get(i).getVoucherId();
		}
		voucherId = new JComboBox();
		voucherId.setModel(new DefaultComboBoxModel(vouchers));
		voucherId.setBounds(205, 113, 423, 22);
		panel.add(voucherId);
		
		JLabel lblVoucher = new JLabel("Voucher    : ");
		lblVoucher.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVoucher.setBounds(21, 453, 114, 25);
		frame.getContentPane().add(lblVoucher);
		System.out.println("sadasd");
		frame.setVisible(true);
	}
}
