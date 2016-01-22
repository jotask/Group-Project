package com.github.jotask.groupproject.gui;

import com.github.jotask.groupproject.database.DataBase;
import com.github.jotask.groupproject.model.User;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import static com.github.jotask.groupproject.Application.PROPERTIES_FILE;

/**
 * Login dialog for the login to the database
 * @author Jose Vives
 * @version 0.1
 */
public class Login extends JDialog {

	private static final long serialVersionUID = -6203552975399889940L;
	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private JTextField usernameField;
	private JCheckBox remember;
	
	private DataBase db;
	private Properties properties;

	/**
	 * Create the dialog for the login to the database.
	 */
	public Login(DataBase db, Properties properties) {
		this.db = db;
		this.properties = properties;
		ImageIcon img = new ImageIcon("resources/icon.png");
		setIconImage(img.getImage());
		setResizable(false);
		setTitle("Login to TaskManager");
		setBounds(100, 100, 450, 165);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][]"));
		{
			JLabel lblUsername = new JLabel("Username:");
			contentPanel.add(lblUsername, "cell 0 0,alignx trailing");
		}
		{
			usernameField = new JTextField();
			contentPanel.add(usernameField, "cell 1 0,growx");
			usernameField.setColumns(10);
		}
		{
			JLabel lblPassword = new JLabel("Password:");
			contentPanel.add(lblPassword, "cell 0 1,alignx trailing");
		}
		{
			passwordField = new JPasswordField();
			contentPanel.add(passwordField, "cell 1 1,growx");
		}
		{
			remember = new JCheckBox("Remember");
			contentPanel.add(remember, "cell 1 2,alignx right");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton loginBtn = new JButton("Login");
				loginBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						login();
					}
				});
				loginBtn.setActionCommand("OK");
				buttonPane.add(loginBtn);
				getRootPane().setDefaultButton(loginBtn);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				});
				{
					JButton offlineBtn = new JButton("Offline");
					buttonPane.add(offlineBtn);
				}
				{
					JButton register = new JButton("Register");
					register.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							register();
						}
					});
					buttonPane.add(register);
				}
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		// Load previous data if exist
		{
			String username = this.properties.getProperty("username");
			String password = this.properties.getProperty("password");

			if(!username.isEmpty()){
				this.remember.setSelected(true);
				this.usernameField.setText(username);
				// FIXME the password is not stored because later we can't or i don't know
				// how load and decrypt a MD5 encryption
			}

		}

	}

	private void register(){
		RegisterDialog registerDialog = new RegisterDialog(db);
	}
	
	private void login(){
		String username = usernameField.getText();
		char[] password = passwordField.getPassword();

		User user = db.getMemberDao().login(username, password);

		if(user != null){

			//Save options
			// If remember checkbox is selected and also on the config file the username
			// from the previous session and the actual session is different we save this
			// new options.
			if(this.remember.isSelected() &&
					!this.properties.getProperty("username").equals(username)){
				this.properties.setProperty("username", username);

//				// Encrypt password and store the encrypted password
//				String pEncrypted = MD5.encrypt(new String(password));
//				this.properties.setProperty("password", pEncrypted);

				// Save this values to the config file
				try {
					OutputStream fos = new FileOutputStream(PROPERTIES_FILE);
					this.properties.store(fos, null);
				} catch (FileNotFoundException e) {
					// Nothing we can do, because this has been checked before start the program
				} catch (IOException e) {
					// Nothing we can do, because this has been checked before start the program
				}

			}

			this.setVisible(false);
			dispose();
//			JOptionPane.showMessageDialog(this, "Login", "Success", JOptionPane.INFORMATION_MESSAGE);
			Application app = new Application(db, user);
		}else{
			JOptionPane.showMessageDialog(this, "Username or password not correct", "Error", JOptionPane.ERROR_MESSAGE);
		}		
	}

}
