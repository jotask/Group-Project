package com.github.jotask.groupproject.gui;

import com.github.jotask.groupproject.connection.Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Properties;

import static com.github.jotask.groupproject.Application.PROPERTIES_FILE;

/**
 * Login dialog for the login to the connection
 *
 * @author Jose Vives
 *
 * @version 1.8 - New features added
 */
public class Login extends JDialog {

    /** serial version for this dialog. Is created randomly */
	private static final long serialVersionUID = -6203552975399889940L;

    /** Password field for the password */
	private final JPasswordField passwordField;

    /** Username field */
	private final JTextField usernameField;

    /** Check box for rember option */
	private final JCheckBox remember;

    /** Properties instance */
	private final Properties properties;

    /**
     * Constructor for initialize everything
     *
     * @param properties
     *          The properties for get all information
     */
	public Login(Properties properties) {
		this.properties = properties;

        // populate the dialog
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);
		setBackground(Color.WHITE);
		ImageIcon img = new ImageIcon("resources/icon.png");
		setIconImage(img.getImage());
		setTitle("Login");
		setBounds(100, 100, 492, 233);
		getContentPane().setLayout(null);
		{
			JLabel lblPassword = new JLabel("Password:");
			lblPassword.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
			lblPassword.setBounds(10, 92, 66, 20);
			getContentPane().add(lblPassword);
		}
		{
			usernameField = new JTextField();
			usernameField.setToolTipText("Surname");
			usernameField.setBounds(90, 51, 373, 20);
			getContentPane().add(usernameField);
			usernameField.setColumns(10);
		}
		{
			JLabel lblUsername = new JLabel("Username:");
			lblUsername.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
			lblUsername.setBounds(10, 53, 66, 14);
			getContentPane().add(lblUsername);
		}
		{
			passwordField = new JPasswordField();
			passwordField.setToolTipText("Enter password");
			passwordField.setBounds(90, 93, 373, 20);
			getContentPane().add(passwordField);
		}
		{
			remember = new JCheckBox("Remember");
			remember.setBackground(Color.WHITE);
			remember.setFont(new Font("Trebuchet MS", Font.PLAIN, 10));
			remember.setBounds(171, 164, 73, 30);
			getContentPane().add(remember);
		}
		{
			JButton offlineBtn = new JButton("Offline");
			offlineBtn.setFont(new Font("Trebuchet MS", Font.PLAIN, 10));
			offlineBtn.setBounds(398, 167, 65, 23);
			offlineBtn.setBorder(null);
			offlineBtn.setBackground(new Color(192,192,192));
			getContentPane().add(offlineBtn);
			offlineBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					offline();
				}
			});
		}
		{
			JButton loginBtn = new JButton("Login");
			loginBtn.setFont(new Font("Trebuchet MS", Font.PLAIN, 10));
			loginBtn.setBounds(254, 167, 57, 23);
			loginBtn.setBorder(null);
			loginBtn.setBackground(new Color(192,192,192));
			getContentPane().add(loginBtn);
			loginBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					login();
				}
			});
			loginBtn.setActionCommand("OK");
			getRootPane().setDefaultButton(loginBtn);
		}
		{
			JButton register = new JButton("Register");
			register.setFont(new Font("Trebuchet MS", Font.PLAIN, 10));
			register.setBounds(318, 167, 73, 23);
			register.setBorder(null);
			register.setBackground(new Color(192,192,192));
			getContentPane().add(register);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 10));
				cancelButton.setBounds(11, 167, 65, 23);
				cancelButton.setBorder(null);
				cancelButton.setBackground(new Color(192,192,192));
				getContentPane().add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
			}
			register.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					register();
				}
			});
		}

		// Load previous data if exist
		{
			String username = this.properties.getProperty("username");
			// String password = this.properties.getProperty("password");

			if(!username.isEmpty()){
				this.remember.setSelected(true);
				this.usernameField.setText(username);
			}

		}

	}

    /**
     * This method is call if the register button is clickec
     * It show a dialog for register a new user
     */
	private void register(){
		new RegisterDialog(properties);
	}

    /**
     * This method is called when the login button has clicked
     * Just prepare everything and create the connection for a online login
     */
	private void login(){

        // Get the information from the field
		String username = usernameField.getText();
		char[] password = passwordField.getPassword();

        // Check if the username is not empty
		if(!username.isEmpty()){

            // Create a connection instance
			Connection connection = new Connection(properties);
            if(!connection.online(username, password)){
                JOptionPane.showMessageDialog(this, "Username or password not correct", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(remember.isSelected())
                saveRemember();

			closeDialog();

			new ApplicationGUI(connection);

		}else{
			JOptionPane.showMessageDialog(this, "Username or password not correct", "Error", JOptionPane.ERROR_MESSAGE);
		}		
	}

    /**
     * This method is called when the offline button has been clicked
     */
	private void offline(){

        // Get the information
		String username = this.usernameField.getText();
		char[] password = this.passwordField.getPassword();

        // Validate if the fields are not empty
		if(username.isEmpty()){
			JOptionPane.showMessageDialog(this, "Username is empty. Please fill with one username", "Field Empty", JOptionPane.ERROR_MESSAGE);
			return;
		}

        if(remember.isSelected())
            saveRemember();

        // Create the connection
		Connection connection = new Connection(properties);
        connection.offline(username, password);

		closeDialog();

		new ApplicationGUI(connection);
	}

    /**
     * This method store this login for next quick loggins
     */
    private void saveRemember(){
        //Save options
        // If remember checkbox is selected and also on the config file the username
        // from the previous session and the actual session is different we save this
        // new options.
        String username = this.usernameField.getText();
        if(!this.properties.getProperty("username").equals(username)){
            this.properties.setProperty("username", username);

            // Save this values to the config file
            try {
                OutputStream fos = new FileOutputStream("resources/" + PROPERTIES_FILE);
                this.properties.store(fos, null);
            } catch (IOException e) {
                // Nothing we can do, because this has been checked before start the program
            }
        }
    }

    /** Close the dialog */
    private void closeDialog(){
        this.setVisible(false);
        dispose();
    }

}
