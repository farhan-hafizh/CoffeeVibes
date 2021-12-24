package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import Controller.TransactionController;
import Controller.VoucherController;
import Model.Voucher;
import Model.Transaction.Transaction;
import Model.Transaction.TransactionItem;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;

public class AllTransactionManagementForm {

	private JFrame frame;
	private JTable table;
	
	private List<Transaction> list1;
	
	private DefaultTableModel model;
	private DefaultTableModel model_1;
	private JTable table_1;

	public JFrame getFrame() {
		return frame;
	}
	private void getTable() {
		 String[] colName = {
					"Transaction ID", "Transaction Date", "Voucher", "Employee Name", "Total Price"
				};
		    if (table == null) {
		        table = new JTable();
		    }
		    model = (DefaultTableModel) table.getModel();
		    model.setColumnIdentifiers(colName);
		    table.setBounds(12, 13, 420, 701);
		    table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
	}
	private void setTable() {
		model.getDataVector().removeAllElements();
		list1 = TransactionController.getAllTransaction();
		model =(DefaultTableModel) table.getModel();
		for (int i = 0; i < list1.size(); i++) {
			String data[]= new String[5];
			data[0]=String.valueOf(list1.get(i).getTransactionId());
			data[1]=String.valueOf(list1.get(i).getPurchaseDate());
			data[2]=list1.get(i).getVoucher();
			data[3]=list1.get(i).getEmployeeName();
			data[4]=String.valueOf(list1.get(i).getTotalPrice());
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
					AllTransactionManagementForm window = new AllTransactionManagementForm();
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
	public AllTransactionManagementForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 900, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(461, 72, 409, 526);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 13, 385, 500);
		panel_1.add(scrollPane_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1_1.setBounds(12, 72, 409, 526);
		frame.getContentPane().add(panel_1_1);
		panel_1_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 385, 500);
		panel_1_1.add(scrollPane);
		
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
				Transaction tr = null;
				int selected_id=Integer.parseInt((String) model.getValueAt(table.getSelectedRow(), 0));
				for (int i = 0; i < list1.size(); i++) {
					if(list1.get(i).getTransactionId()==selected_id) {
						tr=list1.get(i);
					}
				}
				getTable();
				setTable(tr);
				scrollPane_1.setViewportView(table_1);
			}

			private void getTable() {
				 String[] colName = {
							"Product Name", "Quantity", "Total Price"
					};
				    if (table_1 == null) {
				        table_1 = new JTable();
				    }
				    model_1 = (DefaultTableModel) table_1.getModel();
				    model_1.setColumnIdentifiers(colName);
				    table_1.setBounds(12, 13, 420, 701);
				    table_1.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
			}
			private void setTable(Transaction tr) {
				model_1.getDataVector().removeAllElements();
				model_1 =(DefaultTableModel) table_1.getModel();
				List<TransactionItem> list = new ArrayList<TransactionItem>();
						list=tr.getListTransactionItem();
				for (int i = 0; i < list.size(); i++) {
					String data[]= new String[3];
//					System.out.println();
					data[0]=list.get(i).getProductName();
					data[1]=String.valueOf(list.get(i).getQuantity());
					data[2]=String.valueOf(list.get(i).getTotalPrice());
					model_1.addRow(data);
				}
				table_1.setModel(model_1);
			}
		});
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransactionController.viewHome();
			}
		});
		btnNewButton.setBounds(40, 646, 97, 54);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("All Transaction List");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(92, 34, 249, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblDetailItems = new JLabel("Detail Items");
		lblDetailItems.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblDetailItems.setBounds(594, 34, 164, 25);
		frame.getContentPane().add(lblDetailItems);
		frame.setVisible(true);
	}
}
