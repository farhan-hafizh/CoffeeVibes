package Views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.CartController;
import Controller.TransactionController;
import Controller.VoucherController;
import Model.CartItem;
import Model.Voucher;
import Model.Transaction.Transaction;

public class TransactionManagementForm {

	private JFrame frame;
	private JTable table;
	private JLabel discount;
	private JLabel afterDiscount;
	
	private DefaultTableModel model;
	private JTextField voucherId;
	
	private int discounts=0;
	private int priceAfterDiscount;
	
	public JFrame getFrame() {
		return frame;
	}
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
					TransactionManagementForm window = new TransactionManagementForm();
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
	public TransactionManagementForm() {
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
		panel.setBounds(12, 57, 460, 433);
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
		lblNewLabel.setBounds(22, 538, 155, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel totalPrice = new JLabel(String.valueOf(CartController.getTotalPrice()));
		totalPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		totalPrice.setBounds(184, 538, 114, 25);
		frame.getContentPane().add(totalPrice);
		
		JButton btnNewButton = new JButton("Pay");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transaction trans=TransactionController.insertTransaction(CartController.getListItem(),voucherId.getText(),afterDiscount);
				if(trans!=null) {
					JOptionPane.showMessageDialog(null, "Transaction Added!");
					TransactionController.viewHome();
				}
			}
		});
		btnNewButton.setBounds(356, 669, 97, 45);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblVoucher = new JLabel("Voucher    : ");
		lblVoucher.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVoucher.setBounds(22, 500, 114, 25);
		frame.getContentPane().add(lblVoucher);
		voucherId=new JTextField();
		voucherId.setBounds(132, 503, 116, 22);
		frame.getContentPane().add(voucherId);
		voucherId.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Validate Voucher\r\n");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				discounts=TransactionController.getVoucher(voucherId.getText(),Integer.parseInt(totalPrice.getText()));
				if(discounts>0) {
					discount.setText(String.valueOf(discounts));
					priceAfterDiscount=CartController.getTotalPrice()-discounts;
					afterDiscount.setText(String.valueOf(priceAfterDiscount));
					JOptionPane.showMessageDialog(null, "Voucher is valid! Discount Applied!");
				}else JOptionPane.showMessageDialog(null, "Voucher is invalid! Or used!");
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Transaction Management Form");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_1.setBounds(74, 13, 348, 31);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransactionController.viewCart();
			}
		});
		btnBack.setBounds(25, 679, 97, 45);
		frame.getContentPane().add(btnBack);
		
		JLabel lblDiscountRp = new JLabel("Discount :  Rp. ");
		lblDiscountRp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDiscountRp.setBounds(22, 576, 155, 25);
		frame.getContentPane().add(lblDiscountRp);
		
		discount = new JLabel("0");
		discount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		discount.setBounds(162, 576, 114, 25);
		frame.getContentPane().add(discount);
		
		JLabel lblNewLabel_2_1 = new JLabel("Need to pay :  Rp. ");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(22, 615, 182, 25);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		afterDiscount = new JLabel(totalPrice.getText());
		afterDiscount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		afterDiscount.setBounds(197, 615, 114, 25);
		frame.getContentPane().add(afterDiscount);
		
		btnNewButton_1.setBounds(260, 503, 139, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		frame.setVisible(true);
	}
}
