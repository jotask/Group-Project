package com.github.jotask.groupproject.gui;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TaskDialog extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            TaskDialog dialog = new TaskDialog();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public TaskDialog() {
        setBounds(100, 100, 450, 259);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][][][][][]"));
        {
            JLabel lblId = new JLabel("Id:");
            contentPanel.add(lblId, "cell 0 0,alignx trailing");
        }
        {
            textField = new JTextField();
            textField.setEditable(false);
            contentPanel.add(textField, "cell 1 0,growx");
            textField.setColumns(10);
        }
        {
            JLabel lblName = new JLabel("Name");
            contentPanel.add(lblName, "cell 0 1,alignx trailing");
        }
        {
            textField_1 = new JTextField();
            contentPanel.add(textField_1, "cell 1 1,growx");
            textField_1.setColumns(10);
        }
        {
            JLabel lblTeamid = new JLabel("TeamID:");
            contentPanel.add(lblTeamid, "cell 0 2,alignx trailing");
        }
        {
            textField_2 = new JTextField();
            textField_2.setEditable(false);
            contentPanel.add(textField_2, "cell 1 2,growx");
            textField_2.setColumns(10);
        }
        {
            JLabel lblMemberid = new JLabel("MemberID:");
            contentPanel.add(lblMemberid, "cell 0 3,alignx trailing");
        }
        {
            textField_3 = new JTextField();
            textField_3.setEditable(false);
            contentPanel.add(textField_3, "cell 1 3,growx");
            textField_3.setColumns(10);
        }
        {
            JLabel lblStartdate = new JLabel("StartDate");
            contentPanel.add(lblStartdate, "cell 0 4,alignx trailing");
        }
        {
            textField_4 = new JTextField();
            contentPanel.add(textField_4, "cell 1 4,growx");
            textField_4.setColumns(10);
        }
        {
            JLabel lblEnddate = new JLabel("EndDate:");
            contentPanel.add(lblEnddate, "cell 0 5,alignx trailing");
        }
        {
            textField_5 = new JTextField();
            contentPanel.add(textField_5, "cell 1 5,growx");
            textField_5.setColumns(10);
        }
        {
            JLabel lblStatus = new JLabel("Status:");
            contentPanel.add(lblStatus, "cell 0 6,alignx trailing");
        }
        {
            textField_6 = new JTextField();
            contentPanel.add(textField_6, "cell 1 6,growx");
            textField_6.setColumns(10);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }

}
