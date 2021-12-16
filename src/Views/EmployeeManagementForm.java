package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.swing.JTextField;

import Controller.EmployeeController;
import Controller.PositionController;
import Model.Position;
import Model.Employee.Employee;
import Views.Dialogs.EmployeeInsert;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.border.BevelBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TabExpander;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class EmployeeManagementForm {

	private JFrame frame;
	private JTextField name;
	private JTextField salary;
	private JTextField username;
	private JTextField password;
	private JComboBox<Position> position;
	private JTable table_1;
	
	private HashMap<Integer, String> map = new HashMap<Integer, String>();
	private boolean insertStatus;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeManagementForm window = new EmployeeManagementForm();
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
	public EmployeeManagementForm() {
		initialize();
	}
	/*
	 * Create table
	 * */
	private JTable getJTable() {

	    String[] colName = {
				"No", "Name", "Position","Salary", "Status", "Username", "Password"
			};
	    if (table_1 == null) {
	        table_1 = new JTable();
	    }
	    DefaultTableModel contactTableModel = (DefaultTableModel) table_1.getModel();
	    contactTableModel.setColumnIdentifiers(colName);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    return table_1;
	}
	private void setJTable() {
		List<Employee> list1 = EmployeeController.getAllEmployee();
		DefaultTableModel model =(DefaultTableModel) table_1.getModel();
		for (int i = 0; i < list1.size(); i++) {
			String data[]= new String[7];
			data[0]=String.valueOf(i+1) ;
			data[1]=list1.get(i).getName();
			data[2]=map.get(list1.get(i).getPositionId());
			data[3]=String.valueOf(list1.get(i).getSalary());
			data[4]=list1.get(i).getStatus();
			data[5]=list1.get(i).getUsername();
			data[6]=list1.get(i).getPassword();
			model.addRow(data);
		}
		table_1.setModel(model);
	}
	private void showItem(Employee emp) {
		name.setText(emp.getName());
		salary.setText(Integer.toString(emp.getSalary()));
		position.setSelectedItem(map.get(emp.getPositionId()));
		password.setText(emp.getPassword());
		username.setText(emp.getUsername());
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1150, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(12, 13, 729, 385);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel.setBounds(47, 61, 62, 23);
		panel.add(lblNewLabel);
		
		JLabel lblPosition = new JLabel("Position");
		lblPosition.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblPosition.setBounds(47, 110, 91, 23);
		panel.add(lblPosition);
		
		JLabel lblSalary = new JLabel("Salary");
		lblSalary.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblSalary.setBounds(47, 159, 62, 23);
		panel.add(lblSalary);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblUsername.setBounds(47, 209, 109, 25);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblPassword.setBounds(47, 269, 91, 23);
		panel.add(lblPassword);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(205, 64, 423, 22);
		panel.add(name);
		
		salary = new JTextField();
		salary.setColumns(10);
		salary.setBounds(205, 162, 423, 22);
		panel.add(salary);
		
		username = new JTextField();
		username.setColumns(10);
		username.setBounds(205, 213, 423, 22);
		panel.add(username);
		
		password = new JTextField();
		password.setColumns(10);
		password.setBounds(205, 272, 423, 22);
		panel.add(password);
		
	    List<Position> list = PositionController.getAllPosition();
	    String array[] = new String[list.size()];
	    for (int i = 0; i < list.size(); i++) {
			array[i]=list.get(i).getName();
			map.put(list.get(i).getPositionId(), list.get(i).getName());
		}
		position = new JComboBox();
		position.setModel(new DefaultComboBoxModel(array));
		position.setBounds(205, 113, 423, 22);
		panel.add(position);
		
		JButton btnNewButton = new JButton("Insert");
		btnNewButton.setIcon(new ImageIcon(EmployeeManagementForm.class.getResource("/org/eclipse/jface/text/source/projection/images/collapsed.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertStatus=EmployeeController.insertEmployee(list,name, position, salary, username, password);
				EmployeeInsert dialog = new EmployeeInsert(insertStatus);
				if(insertStatus)
					setJTable();
				dialog.setVisible(true);
			}
		});
		btnNewButton.setBounds(150, 327, 97, 45);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.setIcon(new ImageIcon(EmployeeManagementForm.class.getResource("/icons/full/message_info.png")));
		btnNewButton_1.setBounds(329, 327, 97, 45);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.setIcon(new ImageIcon(EmployeeManagementForm.class.getResource("/icons/full/message_warning.png")));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int employeeId=0;
				EmployeeController.deleteEmployee(employeeId);
			}
		});
		btnNewButton_2.setBounds(504, 327, 97, 45);
		panel.add(btnNewButton_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(753, 13, 367, 567);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 343, 541);
		panel_1.add(scrollPane);
		
		//get table
		getJTable();
		//set table
		setJTable();
		
		scrollPane.setViewportView(table_1);
		
		frame.setVisible(true);
	}
}
