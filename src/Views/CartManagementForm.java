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
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class CartManagementForm {

	private JFrame frame;
	private JTable table;
	
	private DefaultTableModel model;
	private HashMap<Integer, String> map;
	private int productId;
	
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
		model.setNumRows(0);
		List<CartItem> list1 = CartController.getListItem();
		int price,qty;
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
		panel.setBounds(12, 57, 460, 433);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 436, 407);
		panel.add(scrollPane);
		
		getTable();
		setTable();
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				productId=Integer.valueOf((String) model.getValueAt(table.getSelectedRow(), 0));
			}	
		});
		
		JButton btnNewButton = new JButton("Check Out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CartController.viewTransactionManagementForm();
			}
		});
		btnNewButton.setBounds(353, 593, 97, 45);
		frame.getContentPane().add(btnNewButton);
		
		List<Voucher> list = VoucherController.getAllVoucher();
		String[] vouchers= new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			vouchers[i]=list.get(i).getVoucherId();
		}
		
		JLabel lblNewLabel_1 = new JLabel("Cart Management Form");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1.setBounds(105, 13, 296, 31);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CartController.viewHome();
			}
		});
		btnBack.setBounds(22, 603, 97, 45);
		frame.getContentPane().add(btnBack);
		
		JButton btnNewButton_1 = new JButton("Delete From Cart");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result=JOptionPane.showConfirmDialog(null, "Delete product from cart?", "Delete", JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					boolean deleted=CartController.deleteItem(productId);
					if(deleted) { 
						setTable();
						JOptionPane.showMessageDialog(null, "Product deleted from cart");
					}else JOptionPane.showMessageDialog(null, "Product failed to delete from cart");
				}
			}
		});
		btnNewButton_1.setBounds(319, 500, 131, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		frame.setVisible(true);
	}
}
