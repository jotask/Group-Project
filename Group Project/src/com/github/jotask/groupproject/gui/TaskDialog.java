package com.github.jotask.groupproject.gui;

import com.github.jotask.groupproject.connection.Connection;
import com.github.jotask.groupproject.model.Element;
import com.github.jotask.groupproject.model.Task;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Dialog for update a task. They show us all information for one task
 *
 * @author Jose Vives
 *
 * @version 1.4 - Refactored
 *
 */
public class TaskDialog extends JDialog {

    /** The application that has create this dialog */
    private Application app;

    /** The connection for retrieve and update all information */
    private Connection conn;

    /** The JPanel for this dialog */
    private final JPanel contentPanel = new JPanel();

    /** The JTexField that holds the task id */
    private JTextField taskId;

    /** The JTexField that holds the task name */
    private JTextField taskName;

    /** The JTexField that holds the team id */
    private JTextField teamId;

    /** The JTexField that holds the member id */
    private JTextField memberId;

    /** The JTexField that holds the start date */
    private JTextField startDate;

    /** The JTexField that holds the end date */
    private JTextField endDate;

    /** The JComboBox that holds the task status */
    private JComboBox statusList;

    /** The JTexField that holds the element field */
    private JTextField elementField;

    /**
     * Constructor for a dialog
     *
     * @param app
     *      The instance of the application that has initialized this dialog
     *
     * @param conn
     *      The connection for retrieve all information
     *
     * @param task
     *      The task tat we want populate this dialog
     */
    public TaskDialog(Application app, Connection conn, final Task task) {
        this.app = app;
        this.conn = conn;

        setTitle("Update task");

        int counter = 0;

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        ImageIcon img = new ImageIcon("resources/icon.png");
        setIconImage(img.getImage());
        setModal(true);
        setBounds(100, 100, 450, 459);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][][][][][]"));
        {
            JLabel lblId = new JLabel("Id:");
            contentPanel.add(lblId, "cell 0 " + counter + ",alignx trailing");
        }
        {
            taskId = new JTextField();
            taskId.setEditable(false);
            contentPanel.add(taskId, "cell 1 " + counter +",growx");
            taskId.setColumns(10);
            taskId.setText(String.valueOf(task.getId()));
        }
        counter++;
        {
            JLabel lblName = new JLabel("Name");
            contentPanel.add(lblName, "cell 0 " + counter + ",alignx trailing");
        }
        {
            taskName = new JTextField();
            contentPanel.add(taskName, "cell 1 " + counter + ",growx");
            taskName.setColumns(10);
            taskName.setText(task.getName());
        }
        counter++;
        {
            JLabel lblTeamid = new JLabel("TeamID:");
            contentPanel.add(lblTeamid, "cell 0 " + counter + ",alignx trailing");
        }
        {
            teamId = new JTextField();
            teamId.setEditable(false);
            contentPanel.add(teamId, "cell 1 " + counter + ",growx");
            teamId.setColumns(10);
            teamId.setText(String.valueOf(task.getTeam_id()));
        }
        counter++;
        {
            JLabel lblMemberid = new JLabel("MemberID:");
            contentPanel.add(lblMemberid, "cell 0 " + counter + ",alignx trailing");
        }
        {
            memberId = new JTextField();
            memberId.setEditable(false);
            contentPanel.add(memberId, "cell 1 " + counter + ",growx");
            memberId.setColumns(10);
            memberId.setText(String.valueOf(task.getMember_id()));
        }
        counter++;
        {
            JLabel lblStartdate = new JLabel("StartDate");
            contentPanel.add(lblStartdate, "cell 0 " + counter + ",alignx trailing");
        }
        {
            startDate = new JTextField();
            contentPanel.add(startDate, "cell 1 " + counter + ",growx");
            startDate.setColumns(10);
            //TODO Delete this
            java.util.Date d = new java.util.Date();
            startDate.setText(new Timestamp(new java.util.Date().getTime()).toString());
        }
        counter++;
        {
            JLabel lblEnddate = new JLabel("EndDate:");
            contentPanel.add(lblEnddate, "cell 0 " + counter + ",alignx trailing");
        }
        {
            endDate = new JTextField();
            contentPanel.add(endDate, "cell 1 " + counter + ",growx");
            endDate.setColumns(10);
            // TODO Delete this
            endDate.setText(task.getEndDate().toString());
        }
        counter++;
        {
            JLabel lblStatus = new JLabel("Status:");
            contentPanel.add(lblStatus, "cell 0 " + counter + ",alignx trailing");
        }
        {
            String[] statusString = { "completed", "abandoned", "allocated" };
            statusList = new JComboBox(statusString);
            contentPanel.add(statusList, "cell 1 " + counter + ",growx");
            // FIXME set the default value to the actual value for tat task
        }
        counter++;
        {
            JLabel lblElement = new JLabel("Previous:");
            contentPanel.add(lblElement, "cell 0 " + counter + ",alignx trailing");
        }
        {
            // FIXME
            ArrayList<Element> elements = task.getElements();
            if(elements != null) {
                for (Element e : elements) {
                    counter++;
                    JLabel tmp = new JLabel(e.getDescription());
                    contentPanel.add(tmp, "cell 1 " + counter + ",alignx trailing");
                }
            }
        }
        counter++;
        {
            JLabel lblElement = new JLabel("addElement:");
            contentPanel.add(lblElement, "cell 0 " + counter + ",alignx trailing");
        }
        {
            elementField = new JTextField();
            contentPanel.add(elementField, "cell 1 " + counter + ",growx");
            elementField.setColumns(10);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                String text = "Update";
                JButton okButton = new JButton(text);
                okButton.setActionCommand(text);
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        updateTask();
                    }
                });
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cancelCliked();
                    }
                });
            }
        }
    }

    /**
     * Update a task.
     * First it check if the element field is empty.
     * Late get all the information for update the task on the database
     */
    private void updateTask(){

        // Check if the elementField if empty
        if(elementField.getText().isEmpty()){
            // TODO add a pop error. An elementField can't be empty i think
            JOptionPane.showMessageDialog(this, "If you update one task, you need to enter the element description", "Field Empty", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Retrieve all information from one task
        String taskID = taskId.getText();
        int id = Integer.parseInt(taskID);
        String name = taskName.getText();
        String teamID = this.teamId.getText();
        int team_id = Integer.parseInt(teamID);
        String memberID = memberId.getText();
        int member_id = Integer.parseInt(memberID);

        // FIXME Get actual values for this fields
        Date startDateTime = new Date();
        Date endDateTime = new Date();

        String status = (String) statusList.getSelectedItem();

        // Create a new task for update all iformation
        Task task = new Task(id, name, team_id, member_id, startDateTime, endDateTime, status);

        // Create an element for this update
        Element element = new Element(-1, task.getId(), elementField.getText());

        // Update the task
        conn.updateTask(task, element);

        // Refres the task view
        app.refreshTaskView(conn.getAllTasks());

        // Cancel and close the dialog
        cancelCliked();
    }

    /**
     * If the cancel button has clicked close the dialog
     */
    private void cancelCliked(){
        dispose();
        setVisible(false);
    }

}
