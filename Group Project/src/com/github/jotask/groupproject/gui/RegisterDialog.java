package com.github.jotask.groupproject.gui;

import com.github.jotask.groupproject.database.DataBase;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterDialog extends JDialog {

    private DataBase db;

    private final JPanel contentPanel = new JPanel();
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;

    /**
     * Launch the application.
     */
//    public static void main(String[] args) {
//        try {
//            RegisterDialog dialog = new RegisterDialog();
//            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//            dialog.setVisible(true);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * Create the dialog.
     */
    public RegisterDialog(DataBase db) {
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][]"));
        {
            JLabel lblSurname = new JLabel("Surname:");
            contentPanel.add(lblSurname, "cell 0 0,alignx trailing");
        }
        {
            textField = new JTextField();
            contentPanel.add(textField, "cell 1 0,growx");
            textField.setColumns(10);
        }
        {
            JLabel lblForename = new JLabel("Forename:");
            contentPanel.add(lblForename, "cell 0 1,alignx trailing");
        }
        {
            textField_1 = new JTextField();
            contentPanel.add(textField_1, "cell 1 1,growx");
            textField_1.setColumns(10);
        }
        {
            JLabel lblEmail = new JLabel("E-Mail:");
            contentPanel.add(lblEmail, "cell 0 2,alignx trailing");
        }
        {
            textField_2 = new JTextField();
            contentPanel.add(textField_2, "cell 1 2,growx");
            textField_2.setColumns(10);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        register();
                    }
                });
                okButton.setActionCommand("Register");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
        this.db = db;
    }

    private void register() {

        String surname = textField.getText();
        String forename = textField_1.getText();
        String mail = textField_2.getText();

        db.getMemberDao().register(surname, forename, mail);

    }

}