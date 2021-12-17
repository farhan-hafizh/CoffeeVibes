package Views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import Controller.EmployeeController;
import Controller.PositionController;
import Model.Position;
import Model.Employee.Employee;
import Views.Dialogs.EmployeeDelete;
import Views.Dialogs.EmployeeInsert;
import Views.Dialogs.EmployeeUpdate;

public class EmployeeManagementForm {

	private JFrame frame;
	private int employeeID;
	private JTextField name;
	private JTextField salary;
	private JTextField username;
	private JTextField password;
	private JComboBox<Position> position;
	private JTable table_1;
	
	private int selected_idx;//selected table index
	private DefaultTableModel model;
	private HashMap<Integer, String> map = new HashMap<Integer, String>();
	
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
	private JTable getTable() {

	    String[] colName = {
				"ID", "Name", "Position","Salary", "Status", "Username", "Password"
			};
	    if (table_1 == null) {
	        table_1 = new JTable();
	    }
	    model = (DefaultTableModel) table_1.getModel();
	    model.setColumnIdentifiers(colName);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    return table_1;
	}
//	set table
	private void setTable() {
		model.getDataVector().removeAllElements();
		List<Employee> list1 = EmployeeController.getAllEmployee();
		model =(DefaultTableModel) table_1.getModel();
		for (int i = 0; i < list1.size(); i++) {
			String data[]= new String[7];
			data[0]=String.valueOf(list1.get(i).getEmployeId()) ;
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
	//setting table
	
	private void resetFields() {
		name.setText("");
		password.setText("");
		position.setSelectedItem("");
		salary.setText("");
		username.setText("");
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
				Employee emp=null;
				emp=EmployeeController.insertEmployee(list,name, position, salary, username, password);
				EmployeeInsert dialog = new EmployeeInsert(emp);
				resetFields();
				if(emp!=null)
					setTable();
				dialog.setVisible(true);
			}
		});
		btnNewButton.setBounds(150, 327, 97, 45);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Employee emp=null;
				emp=EmployeeController.updateEmployee(employeeID,name,salary,username,password);
				EmployeeUpdate dialog = new EmployeeUpdate(emp);
				resetFields();
				if(emp!=null)
					setTable();
				dialog.setVisible(true);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(EmployeeManagementForm.class.getResource("/icons/full/message_info.png")));
		btnNewButton_1.setBounds(329, 327, 97, 45);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.setIcon(new ImageIcon(EmployeeManagementForm.class.getResource("/icons/full/message_warning.png")));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog (null, "Would You Like to Delete or Fire This Employee?","Warning",JOptionPane.YES_NO_OPTION);
				resetFields();
				if(confirm == JOptionPane.YES_OPTION){
					boolean status=EmployeeController.deleteEmployee(employeeID);
					EmployeeDelete dialog = new EmployeeDelete(status);
					if(status) {
						model.removeRow(selected_idx);
						setTable();
					}
					dialog.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "No Employee Deleted or Fired!");
				}				
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
		getTable();
		//set table
		setTable();
		table_1.addMouseListener(new MouseListener() {
			
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
				selected_idx = table_1.getSelectedRow();
				showItem();
			}
			private void showItem() {
				employeeID=Integer.parseInt((String) model.getValueAt(selected_idx, 0));
				name.setText((String) model.getValueAt(selected_idx, 1));
				position.setSelectedItem((String) model.getValueAt(selected_idx, 2));
				salary.setText((String) model.getValueAt(selected_idx, 3));
				username.setText((String) model.getValueAt(selected_idx, 5));
				password.setText((String) model.getValueAt(selected_idx, 6));
			}
		});
		
		scrollPane.setViewportView(table_1);
		
		JButton btnNewButton_3 = new JButton("Back");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeController.viewHome();
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_3.setBounds(41, 511, 97, 56);
		frame.getContentPane().add(btnNewButton_3);
		
		frame.setVisible(true);
	}
}
