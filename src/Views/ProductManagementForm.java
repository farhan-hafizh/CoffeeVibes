package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import Model.CartItem;
import Model.Product;
import Model.Employee.Employee;
import Model.Employee.ProductAdmin;
import Session.LoginSession;

import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import Controller.CartController;
import Controller.EmployeeController;
import Controller.HomeController;
import Controller.ProductController;
import javax.swing.JScrollPane;

public class ProductManagementForm {

	private JFrame frame;
	
	private int productId=0;
	private JTextField name;
	private JTextField description;
	private JTextField price;
	private JTextField stock;
	
	private DefaultTableModel model;
	private JTable table=null;
	private int selected_idx;//selected idx
	
	public JFrame getFrame() {
		return frame;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductManagementForm window = new ProductManagementForm();
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
	public ProductManagementForm() {
		initialize();
	}
	//reset fields
	private void resetFields() {
		name.setText("");
		description.setText("");
		price.setText("");
		stock.setText("");
	}
	//create table
	private void getTable() {
		
	    String[] colName = {
				"ID", "Name", "Description", "Price","Stock"
			};
	    if (table == null) {
	        table = new JTable();
	    }
	    model = (DefaultTableModel) table.getModel();
	    model.setColumnIdentifiers(colName);
	    table.setBounds(12, 13, 420, 701);
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    
	}
	private void setTable() {
		model.getDataVector().removeAllElements();
		List<Product> list1 = ProductController.getAllProducts();
		model =(DefaultTableModel) table.getModel();
		for (int i = 0; i < list1.size(); i++) {
			String data[]= new String[5];
			data[0]=String.valueOf(list1.get(i).getProductId()) ;
			data[1]=list1.get(i).getProductName();
			data[2]=list1.get(i).getProductDescription();
			data[3]=String.valueOf(list1.get(i).getProductPrice());
			data[4]=String.valueOf(list1.get(i).getProductStock());
			model.addRow(data);
		}
		table.setModel(model);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1150, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(0, 13, 653, 386);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(37, 35, 93, 31);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Description");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(37, 79, 124, 31);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Price");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1.setBounds(37, 123, 93, 31);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Stock");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1_1.setBounds(37, 167, 93, 31);
		panel.add(lblNewLabel_1_1_1);
		
		name = new JTextField();
		name.setBounds(181, 31, 385, 31);
		panel.add(name);
		name.setColumns(10);
		
		description = new JTextField();
		description.setColumns(10);
		description.setBounds(181, 79, 385, 31);
		panel.add(description);
		
		price = new JTextField();
		price.setColumns(10);
		price.setBounds(181, 123, 385, 31);
		panel.add(price);
		
		stock = new JTextField();
		stock.setColumns(10);
		stock.setBounds(181, 167, 385, 31);
		panel.add(stock);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(62, 242, 545, 115);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		if(LoginSession.getEmployee() instanceof ProductAdmin) {
			GridBagLayout gbl_panel_2 = new GridBagLayout();
			gbl_panel_2.columnWidths = new int[]{40, 97, 89, 97, 84, 97, 0};
			gbl_panel_2.rowHeights = new int[]{31, 53, 0};
			gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel_2.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			panel_2.setLayout(gbl_panel_2);
			
			JButton btnNewButton = new JButton("Insert");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Product prod;
					String message = "Product Failed to Add!";
					prod=ProductController.insertProduct(name, description, price, stock);
					if(prod != null) {
						message="Product "+prod.getProductName()+" Successfully Added!";
						resetFields();
						setTable();
					}
					JOptionPane.showMessageDialog(null, message);
				}
			});
			GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
			gbc_btnNewButton.fill = GridBagConstraints.BOTH;
			gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
			gbc_btnNewButton.gridx = 1;
			gbc_btnNewButton.gridy = 1;
			panel_2.add(btnNewButton, gbc_btnNewButton);
			
			JButton btnUpdate = new JButton("Update");
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Product prod;
					String message = "Product Failed to Update!";
					
					prod = ProductController.updateProduct(productId, name, description, price, stock);
					if(prod != null) {
						message="Product "+prod.getProductName()+" Successfully Updated!";
						resetFields();
						setTable();
					}
					JOptionPane.showMessageDialog(null, message);
				}
			});
			GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
			gbc_btnUpdate.fill = GridBagConstraints.BOTH;
			gbc_btnUpdate.insets = new Insets(0, 0, 0, 5);
			gbc_btnUpdate.gridx = 3;
			gbc_btnUpdate.gridy = 1;
			panel_2.add(btnUpdate, gbc_btnUpdate);
			
			JButton btnNewButton_1_1 = new JButton("Delete");
			btnNewButton_1_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int confirm = JOptionPane.showConfirmDialog (null, "Would You Like to Delete This Product?","Warning",JOptionPane.YES_NO_OPTION);
					if(confirm == JOptionPane.YES_OPTION) {
						boolean status=ProductController.deleteProduct(productId);
						String message="Product Failed To Delete!";
						if(status) {
							message="Product Successfully Deleted!";
							model.removeRow(selected_idx);
							resetFields();
							setTable();
						}
						JOptionPane.showMessageDialog(null, message);
					}else {
						
					}
				}
			});
			GridBagConstraints gbc_btnNewButton_1_1 = new GridBagConstraints();
			gbc_btnNewButton_1_1.fill = GridBagConstraints.BOTH;
			gbc_btnNewButton_1_1.gridx = 5;
			gbc_btnNewButton_1_1.gridy = 1;
			panel_2.add(btnNewButton_1_1, gbc_btnNewButton_1_1);
		}else {
			JButton btnNewButton_1 = new JButton("Add To Cart");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(productId!=0) {
						int quantity=Integer.parseInt(JOptionPane.showInputDialog("How much do you want to add?"));
						CartItem item =CartController.addToCart(productId, quantity);
						String message="Item Failed to add to Cart";
						if(item != null) {
							message="Item Successfuly Added To Cart";
							resetFields();
							setTable();
						}
						JOptionPane.showMessageDialog(null, message);
					}else {
						JOptionPane.showMessageDialog(null, "Select Item First");
					}
				}
			});
			btnNewButton_1.setBounds(34, 34, 110, 45);
			panel_2.add(btnNewButton_1);
		}
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(677, 13, 444, 727);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		//Create table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 420, 701);
		panel_1.add(scrollPane);
		
		getTable();
		setTable();
		
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_2 = new JButton("Back");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductController.viewHome();
			}
		});
		btnNewButton_2.setBounds(44, 659, 97, 54);
		frame.getContentPane().add(btnNewButton_2);
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
				selected_idx=table.getSelectedRow();
				showItem();
			}
			private void showItem() {
				// TODO Auto-generated method stub
				productId=Integer.parseInt((String) model.getValueAt(selected_idx, 0));
				name.setText((String) model.getValueAt(selected_idx, 1));
				description.setText((String) model.getValueAt(selected_idx, 2));
				price.setText((String) model.getValueAt(selected_idx, 3));
				stock.setText((String) model.getValueAt(selected_idx, 4));
			}
		});
		frame.setVisible(true);
	}
}
