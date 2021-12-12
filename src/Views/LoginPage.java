package Views;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;

public class LoginPage {

	protected Shell shell;
	private Text username;
	private Text password;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LoginPage window = new LoginPage();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(465, 348);
		shell.setText("CoffeeVibes");
		
		Label lblUsername = new Label(shell, SWT.NONE);
		lblUsername.setBounds(63, 67, 70, 20);
		lblUsername.setText("Username");
		
		Label lblPassword = new Label(shell, SWT.NONE);
		lblPassword.setBounds(63, 113, 70, 20);
		lblPassword.setText("Password");
		
		username = new Text(shell, SWT.BORDER);
		username.setBounds(175, 67, 154, 26);
		
		password = new Text(shell, SWT.BORDER);
		password.setBounds(175, 107, 154, 26);
		
		Button btnLogin = new Button(shell, SWT.NONE);
		btnLogin.setBounds(77, 176, 90, 30);
		btnLogin.setText("Login");
		
		Button btnReset = new Button(shell, SWT.NONE);
		btnReset.setBounds(239, 176, 90, 30);
		btnReset.setText("Reset");

	}
}
