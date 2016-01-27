package com.github.jotask.groupproject.gui;

import com.github.jotask.groupproject.connection.DataBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

/**
 * Dialog for register a new user
 *
 * @author Jose Vives
 *
 * @version 1.4 - Show a dialog if any field is empty before add the user
 */
public class RegisterDialog extends JDialog {

    /** Database instance */
    private DataBase db;

    /** JTextField for the forename */
    private JTextField forenameField;

    /** JTextField for the surename */
    private JTextField surenameField;

    /** JTextField for the mail */
    private JTextField mailField;

    /**
     * Constructor for this register a dialog. Initialize and
     * populate all the dialog
     *
     * @param properties
     *      The properties for know all information for create a connection
     */
    public RegisterDialog(Properties properties) {

        this.db = new DataBase(properties);

        getContentPane().setBackground(Color.WHITE);
        setBackground(Color.WHITE);
        setModal(true);
        setTitle("Register new member");
        ImageIcon img = new ImageIcon("resources/icon.png");
        setIconImage(img.getImage());
        setBounds(100, 100, 495, 226);
        getContentPane().setLayout(null);
        {
            {
                {
                    JLabel lblSurname = new JLabel("Surname:");
                    lblSurname.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
                    lblSurname.setBounds(10, 70, 63, 14);
                    getContentPane().add(lblSurname);
                }
                {
                    forenameField = new JTextField();
                    forenameField.setBounds(89, 64, 374, 20);
                    getContentPane().add(forenameField);
                    forenameField.setColumns(10);
                }
                {
                    JLabel lblForename = new JLabel("Forename:");
                    lblForename.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
                    lblForename.setBounds(10, 34, 67, 14);
                    getContentPane().add(lblForename);
                }
                {
                    surenameField = new JTextField();
                    surenameField.setBounds(89, 29, 374, 20);
                    getContentPane().add(surenameField);
                    surenameField.setColumns(10);
                }
                {
                    JLabel lblEmail = new JLabel("E-Mail:");
                    lblEmail.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
                    lblEmail.setBounds(10, 105, 54, 14);
                    getContentPane().add(lblEmail);
                }
                {
                    mailField = new JTextField();
                    mailField.setBounds(89, 103, 374, 20);
                    getContentPane().add(mailField);
                    mailField.setColumns(10);
                }
                {
                    JButton okButton = new JButton("OK");
                    okButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 10));
                    okButton.setBounds(89, 150, 47, 23);
                    okButton.setBorder(null);
                    okButton.setBackground(new Color(192,192,192));
                    getContentPane().add(okButton);
                    okButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            register();
                        }
                    });
                    okButton.setActionCommand("Register");
                    getRootPane().setDefaultButton(okButton);
                }
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 10));
                cancelButton.setBounds(389, 150, 75, 23);
                cancelButton.setBorder(null);
                cancelButton.setBackground(new Color(192,192,192));
                getContentPane().add(cancelButton);
                cancelButton.setActionCommand("Cancel");
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        close();
                    }
                });
            }
        }
        this.setVisible(true);
    }

    /**
     * This method is called whn the register button has been clicked.
     * It check if all the fields has information before continue
     * Can be improved by some type of type check improved
     */
    private void register() {

        // Get all information
        String surname = forenameField.getText();
        String forename = surenameField.getText();
        String mail = mailField.getText();

        // Check if any field is empty and show a dialog showing where is the error
        if(surname.equals("")){
            JOptionPane.showMessageDialog(this, "Surname can´t be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else if(forename.equals("")){
            JOptionPane.showMessageDialog(this, "Forename can´t be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else if(mail.equals("")){
            JOptionPane.showMessageDialog(this, "Mail can´t be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Show if the user has not been created
        if(!db.getMemberDao().register(surname, forename, mail)) {
            JOptionPane.showMessageDialog(this, "Error Creating user", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // close the dialog
        close();

    }

    /**
     * Method for close this dialog and close the connection to the database
     */
    private void close(){
        db.close();
        dispose();
        setVisible(false);
    }

}