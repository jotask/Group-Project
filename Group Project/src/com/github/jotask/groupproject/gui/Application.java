package com.github.jotask.groupproject.gui;

import com.github.jotask.groupproject.connection.Connection;
import com.github.jotask.groupproject.model.Task;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Main class for the application program
 *
 * @author Jose Vives
 * @author Jack Thomson
 *
 * @version 1.3 - new GUI
 *
 */
public class Application extends JFrame{

    /** Table that hold all tasks */
    private JTable table;

    /** Instance for this istance */
    private Application instance;

    /** Connection instance for retrieve all properties */
    private Connection connection;

    /**
     * Constructor for this class
     * Initialize everything
     *
     * @param connection
     *      The connection instance
     */
    public Application(Connection connection) {
    	setBackground(new Color(240, 240, 240));
        this.connection = connection;
        instance = this;
        initialize();

        // refresh the table with all task
        this.refreshTaskView(connection.getAllTasks());
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        this.setTitle("Task Manager");
        this.setBounds(100, 100, 800, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ImageIcon img = new ImageIcon("resources/icon.png");
        this.setIconImage(img.getImage());
        JScrollPane scrollPane = new JScrollPane();
        this.getContentPane().add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        scrollPane.setViewportView(table);

        {
            JPanel panel = new JPanel();
            panel.setBackground(Color.WHITE);
            table.setBackground(Color.white);
            table.setSelectionBackground(new Color(192,192,192));
            getContentPane().add(panel, BorderLayout.WEST);
            JLabel lblUsername = new JLabel(connection.getUser().getSurname());
            lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
            lblUsername.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
            JButton btnUpdate = new JButton("Update Task");

            JLabel lblWelcome = new JLabel("WELCOME");
            lblWelcome.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
            GroupLayout gl_panel = new GroupLayout(panel);
            gl_panel.setHorizontalGroup(
                    gl_panel.createParallelGroup(Alignment.LEADING)
                            .addComponent(lblUsername, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                            .addGroup(gl_panel.createSequentialGroup()
                                    .addGap(43)
                                    .addComponent(btnUpdate)
                                    .addContainerGap(42, Short.MAX_VALUE))
                            .addGroup(gl_panel.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(lblWelcome)
                                    .addContainerGap(77, Short.MAX_VALUE))
            );
            gl_panel.setVerticalGroup(
                    gl_panel.createParallelGroup(Alignment.LEADING)
                            .addGroup(gl_panel.createSequentialGroup()
                                    .addGap(77)
                                    .addComponent(lblWelcome)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblUsername)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                                    .addComponent(btnUpdate)
                                    .addGap(38))
            );
            btnUpdate.setBackground(new Color(192,192,192));
            btnUpdate.setBorder(BorderFactory.createLineBorder(new Color(192,192,192),6));
            panel.setLayout(gl_panel);
            panel.setBorder(BorderFactory.createLineBorder(new Color(192,192,192),2));
            btnUpdate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateTask();
                }
            });
        }

        this.setVisible(true);
    }

    /** Refresh the table with new tasks
     *
     * @param tasks
     *      The tasks for populate the table
     */
    public void refreshTaskView(ArrayList<Task> tasks) {
        TaskTableModel model = new TaskTableModel(tasks);
        table.setModel(model);
    }

    /**
     * This is called when the update button is clicked
     * Show a dialog for update a selected task
     */
    private void updateTask(){

        // get row selected
        int row = table.getSelectedRow();
        // check if a row is selected
        if(row < 0){
            return;
        }
        // Get the Employee selected
        int t = Integer.parseInt(table.getValueAt(row, TaskTableModel.ID_COL).toString());

        Task tmp = connection.getTask(t);

        // Create the dialog and make it visible
        TaskDialog dialog = new TaskDialog(instance, connection, tmp);
        dialog.setVisible(true);
    }

    /**
     * Dispose the application and close all the connection
     */
    @Override
    public void dispose() {
        super.dispose();
        connection.close();
        System.exit(0);
    }

}
