package com.github.jotask.groupproject.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.github.jotask.groupproject.database.DataBase;

import net.miginfocom.swing.MigLayout;

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
			JCheckBox chckbxNewCheckBox = new JCheckBox("Remember");
			contentPanel.add(chckbxNewCheckBox, "cell 1 2,alignx right");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						login();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void login(){
		String username = usernameField.getText();
		char[] password = passwordField.getPassword();
		
		if(db.getUser().login(username, password)){
			JOptionPane.showMessageDialog(this, "Login", "Succes", JOptionPane.INFORMATION_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(this, "Username or password not correct", "Error", JOptionPane.ERROR_MESSAGE);
		}		
	}

}
