package com.github.jotask.groupproject.gui;

import com.github.jotask.groupproject.database.Connection;
import com.github.jotask.groupproject.model.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Application extends JFrame{

    private JTable table;

    private Application instance;
    private Connection connection;

    /**
     * Create the application.
     */
    public Application(Connection connection) {
        instance = this;
        this.connection = connection;
        initialize();
        this.refreshTaskView(connection.getTasks());
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

        JPanel panel = new JPanel();
        this.getContentPane().add(panel, BorderLayout.WEST);
        panel.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel lblUsername = new JLabel(connection.getUser().getSurname());
        panel.add(lblUsername);

//        JButton btnNewButton = new JButton("Add Task");
//        btnNewButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                TaskDialog taskDialog = new TaskDialog(instance, connection);
//                taskDialog.setVisible(true);
//            }
//        });
//        panel.add(btnNewButton);

        {
            JButton btnUpdate = new JButton("Update Task");
            panel.add(btnUpdate);
            btnUpdate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateTask();
                }
            });
        }
        JScrollPane scrollPane = new JScrollPane();
        this.getContentPane().add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        scrollPane.setViewportView(table);

        this.setVisible(true);
    }

    public void refreshTaskView(ArrayList<Task> tasks) {
        TaskTableModel model = new TaskTableModel(tasks);
        table.setModel(model);
    }

    private void updateTask(){
        // get row selected
        int row = table.getSelectedRow();
        // check if a row is selected
        if(row < 0){
            return;
        }
        // Get the Employee selected
        int t = Integer.parseInt(table.getValueAt(row, TaskTableModel.ID_COL).toString());
//        Task tmp = db.getTaskDAO().getTask(t);
        Task tmp = connection.getTask(t);
//        Task tmp = (Task) table.getValueAt(row, TaskTableModel.OBJECT_COL);
        // Create the dialog and make it visible
        TaskDialog dialog = new TaskDialog(instance, connection, tmp);
        dialog.setVisible(true);
    }

    @Override
    public void dispose() {
        super.dispose();
        connection.close();
    }
}
