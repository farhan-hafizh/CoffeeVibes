package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BaristaHome {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BaristaHome window = new BaristaHome();
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
	public BaristaHome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1450, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(12, 13, 378, 408);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("7");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btnNewButton.setBounds(22, 33, 90, 59);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("8");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btnNewButton_1.setBounds(139, 33, 90, 59);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("9");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btnNewButton_2.setBounds(264, 33, 90, 59);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_1_1 = new JButton("5");
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btnNewButton_1_1.setBounds(139, 123, 90, 59);
		panel.add(btnNewButton_1_1);
		
		JButton btnNewButton_2_1 = new JButton("6");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btnNewButton_2_1.setBounds(264, 123, 90, 59);
		panel.add(btnNewButton_2_1);
		
		JButton btnNewButton_3 = new JButton("4");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btnNewButton_3.setBounds(22, 123, 90, 59);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_1_2 = new JButton("2");
		btnNewButton_1_2.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btnNewButton_1_2.setBounds(139, 216, 90, 59);
		panel.add(btnNewButton_1_2);
		
		JButton btnNewButton_2_2 = new JButton("3");
		btnNewButton_2_2.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btnNewButton_2_2.setBounds(264, 216, 90, 59);
		panel.add(btnNewButton_2_2);
		
		JButton btnNewButton_4 = new JButton("1");
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btnNewButton_4.setBounds(22, 216, 90, 59);
		panel.add(btnNewButton_4);
		
		JButton btnNewButton_1_3 = new JButton(".");
		btnNewButton_1_3.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btnNewButton_1_3.setBounds(139, 318, 90, 59);
		panel.add(btnNewButton_1_3);
		
		JButton btnNewButton_2_3 = new JButton("C");
		btnNewButton_2_3.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btnNewButton_2_3.setBounds(264, 318, 90, 59);
		panel.add(btnNewButton_2_3);
		
		JButton btnNewButton_5 = new JButton("0");
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btnNewButton_5.setBounds(22, 318, 90, 59);
		panel.add(btnNewButton_5);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(802, 13, 618, 408);
		frame.getContentPane().add(panel_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1_1.setBounds(12, 434, 1408, 306);
		frame.getContentPane().add(panel_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(402, 13, 386, 408);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"Items", "Price", "Qty", "Total Amount"
			}
		));
		table.getColumnModel().getColumn(2).setPreferredWidth(55);
		
		frame.setVisible(true);
	}
}
