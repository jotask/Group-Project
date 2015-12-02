package com.github.jotask.groupproject.gui;

import com.github.jotask.groupproject.database.DataBase;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	private DataBase db;

	/**
	 * Create the dialog for the login to the database.
	 */
	public Login(DataBase db) {
		this.db = db;
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
			JCheckBox remember = new JCheckBox("Remember");
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
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void login(){
		String username = usernameField.getText();
		char[] password = passwordField.getPassword();
		
		if(db.getMember().login(username, password)){
			JOptionPane.showMessageDialog(this, "Login", "Succes", JOptionPane.INFORMATION_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(this, "Username or password not correct", "Error", JOptionPane.ERROR_MESSAGE);
		}		
	}

}
