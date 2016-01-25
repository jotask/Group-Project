package com.github.jotask.groupproject.gui;

import com.github.jotask.groupproject.database.DataBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

public class RegisterDialog extends JDialog {

    private DataBase db;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;

    /**
     * Create the dialog.
     */
    public RegisterDialog(Properties properties) {

        this.db = new DataBase(properties);

        getContentPane().setBackground(Color.WHITE);
        setBackground(Color.WHITE);
        this.db = db;
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
                    textField = new JTextField();
                    textField.setBounds(89, 64, 374, 20);
                    getContentPane().add(textField);
                    textField.setColumns(10);
                }
                {
                    JLabel lblForename = new JLabel("Forename:");
                    lblForename.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
                    lblForename.setBounds(10, 34, 67, 14);
                    getContentPane().add(lblForename);
                }
                {
                    textField_1 = new JTextField();
                    textField_1.setBounds(89, 29, 374, 20);
                    getContentPane().add(textField_1);
                    textField_1.setColumns(10);
                }
                {
                    JLabel lblEmail = new JLabel("E-Mail:");
                    lblEmail.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
                    lblEmail.setBounds(10, 105, 54, 14);
                    getContentPane().add(lblEmail);
                }
                {
                    textField_2 = new JTextField();
                    textField_2.setBounds(89, 103, 374, 20);
                    getContentPane().add(textField_2);
                    textField_2.setColumns(10);
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

    private void close(){
        dispose();
        setVisible(false);
    }

    private void register() {

        String surname = textField.getText();
        String forename = textField_1.getText();
        String mail = textField_2.getText();

        if(surname.equals("")){
            // TODO
            return;
        }else if(forename.equals("")){
            // TODO
            return;
        }else if(mail.equals("")){
            // TODO
            return;
        }

        if(db.getMemberDao().register(surname, forename, mail)) {
            close();
        }else{
            JOptionPane.showMessageDialog(this, "Error Creating user", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

}