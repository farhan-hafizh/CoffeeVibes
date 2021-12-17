package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;

import Controller.HomeController;
import Model.Employee.Barista;
import Model.Employee.HRD;
import Model.Employee.Manager;
import Model.Employee.ProductAdmin;
import Session.LoginSession;

import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home {

	private JFrame frame;
	
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
					Home window = new Home();
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
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 850, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(99, 32, 629, 44);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Welcome to CoffeeVibes,");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(99, 141, 629, 399);
		frame.getContentPane().add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{148, 341, 0};
		gbl_panel_1.rowHeights = new int[]{34, 60, 60, 60, 60, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(99, 75, 629, 44);
		frame.getContentPane().add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel(LoginSession.getEmployee().getName());
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 28));
		panel_2.add(lblNewLabel_1);
		//set y on grid
		int y=0;
		
		if(LoginSession.getEmployee() instanceof ProductAdmin || LoginSession.getEmployee() instanceof Barista) {
			y++;
			JButton btnNewButton = new JButton("View Products");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					HomeController.viewProductManagementForm();
				}
			});
			GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
			gbc_btnNewButton.fill = GridBagConstraints.BOTH;
			gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
			gbc_btnNewButton.gridx = 1;
			gbc_btnNewButton.gridy = y;
			panel_1.add(btnNewButton, gbc_btnNewButton);
		}
		if(LoginSession.getEmployee() instanceof ProductAdmin) {
			y++;
			JButton btnNewButton_1 = new JButton("View Voucher");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
			gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
			gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
			gbc_btnNewButton_1.gridx = 1;
			gbc_btnNewButton_1.gridy = y;
			panel_1.add(btnNewButton_1, gbc_btnNewButton_1);
		}
		if(LoginSession.getEmployee() instanceof Barista) {
			y++;
			JButton btnNewButton_2 = new JButton("View Cart");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
			gbc_btnNewButton_2.fill = GridBagConstraints.BOTH;
			gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 0);
			gbc_btnNewButton_2.gridx = 1;
			gbc_btnNewButton_2.gridy = y;
			panel_1.add(btnNewButton_2, gbc_btnNewButton_2);
		}
		if(LoginSession.getEmployee() instanceof HRD || LoginSession.getEmployee() instanceof Manager) {
			y++;
			JButton btnNewButton_3 = new JButton("View Employees");
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					HomeController.viewEmployeeManagementForm();
				}
			});
			GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
			gbc_btnNewButton_3.fill = GridBagConstraints.BOTH;
			gbc_btnNewButton_3.gridx = 1;
			gbc_btnNewButton_3.gridy = y;
			panel_1.add(btnNewButton_3, gbc_btnNewButton_3);
		}
		frame.setVisible(true);
	}
}
