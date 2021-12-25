package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.ProductController;
import Controller.VoucherController;
import Model.Product;
import Model.Voucher;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class VoucherManagementForm {

	private JFrame frame;
	private JTextField discount;
	private JTextField voucherID;
	private JTable table;
	
	private DefaultTableModel model;
	private int selected_idx;
	private JComboBox status = new JComboBox();
	
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
					VoucherManagementForm window = new VoucherManagementForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void resetField() {
		voucherID.setText("");
		discount.setText("");
		status.setSelectedItem("Active");
	}
	private void getTable() {
		 String[] colName = {
					"Voucher ID", "Voucher Discount", "Voucher Status"
				};
		    if (table == null) {
		        table = new JTable();
		    }
		    model = (DefaultTableModel) table.getModel();
		    model.setColumnIdentifiers(colName);
		    table.setBounds(12, 13, 420, 701);
	}
	private void setTable() {
		model.getDataVector().removeAllElements();
		List<Voucher> list1 = VoucherController.getAllVoucher();
		model =(DefaultTableModel) table.getModel();
		for (int i = 0; i < list1.size(); i++) {
			String data[]= new String[3];
			data[0]=list1.get(i).getVoucherId();
			data[1]=String.valueOf(list1.get(i).getDiscount());
			data[2]=list1.get(i).getStatus();
			model.addRow(data);
		}
		table.setModel(model);
	}
	
	/**
	 * Create the application.
	 */
	public VoucherManagementForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(584, 13, 386, 527);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 362, 501);
		panel.add(scrollPane);
		
		getTable();
		setTable();
		
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
				showData();
			}

			private void showData() {
				// TODO Auto-generated method stub
				voucherID.setText((String) model.getValueAt(selected_idx, 0));
				discount.setText((String) model.getValueAt(selected_idx, 1));
				status.setSelectedItem((String) model.getValueAt(selected_idx, 2));
			}
			
		});
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(12, 13, 556, 393);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Discount (%) :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(36, 148, 148, 60);
		panel_1.add(lblNewLabel);
		
		discount = new JTextField();
		discount.setBounds(182, 168, 209, 27);
		panel_1.add(discount);
		discount.setColumns(10);
		
		JLabel lblVoucherId = new JLabel("Voucher ID :");
		lblVoucherId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVoucherId.setBounds(36, 95, 148, 60);
		panel_1.add(lblVoucherId);
		
		voucherID = new JTextField();
		voucherID.setColumns(10);
		voucherID.setBounds(182, 115, 209, 27);
		panel_1.add(voucherID);
		
		JLabel lblStatus = new JLabel("Status :");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStatus.setBounds(36, 208, 148, 60);
		panel_1.add(lblStatus);
		
		
		status.setModel(new DefaultComboBoxModel(new String[] {"Active", "Inactive"}));
		status.setBounds(182, 228, 209, 27);
		panel_1.add(status);
		
		JLabel lblVoucherDetail = new JLabel("Voucher Detail");
		lblVoucherDetail.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblVoucherDetail.setBounds(174, 27, 172, 55);
		panel_1.add(lblVoucherDetail);
		
		JButton btnNewButton_1 = new JButton("Generate");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Voucher data = VoucherController.generateVoucher(voucherID,discount);
				if(data!=null) {
					JOptionPane.showMessageDialog(null, "Voucher generated!");
					resetField();
					setTable();
				}
			}
		});
		btnNewButton_1.setBounds(90, 318, 94, 39);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Delete");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog (null, "Would You Like to Delete This Voucher?","Warning",JOptionPane.YES_NO_OPTION);
				if(confirm== JOptionPane.YES_OPTION) {
					boolean deleted=VoucherController.deleteVoucher(voucherID);
					if(deleted) {
						JOptionPane.showMessageDialog(null, "Voucher deleted!");
						resetField();
						setTable();
					}
				}else JOptionPane.showMessageDialog(null, "No Voucher is deleted!");
			}
		});
		btnNewButton_1_1.setBounds(337, 318, 94, 39);
		panel_1.add(btnNewButton_1_1);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VoucherController.backHome();
			}
		});
		btnNewButton.setBounds(61, 443, 87, 61);
		frame.getContentPane().add(btnNewButton);
		frame.setVisible(true);
	}
}
