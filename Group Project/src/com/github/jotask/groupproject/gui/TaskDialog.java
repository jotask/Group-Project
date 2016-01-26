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

public class TaskDialog extends JDialog {

    private Application app;
    private Connection conn;

    private final JPanel contentPanel = new JPanel();
    private JTextField taskId;
    private JTextField taskName;
    private JTextField teamId;
    private JTextField memberId;
    private JTextField startDate;
    private JTextField endDate;
    private JTextField taskStatus;
    private JTextField elementField;

    /**
     * Create the dialog.
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
        setBounds(100, 100, 450, 659);
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
            taskStatus = new JTextField();
            contentPanel.add(taskStatus, "cell 1 " + counter + ",growx");
            taskStatus.setColumns(10);
            taskStatus.setText(task.getStatus());
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

    private void updateTask(){

        if(elementField.getText().isEmpty()){
            // TODO add a pop error. An elementField can't be empty i think
            return;
        }

        String taskID = taskId.getText();
        int id = Integer.parseInt(taskID);
        String name = taskName.getText();
        String teamID = this.teamId.getText();
        int team_id = Integer.parseInt(teamID);
        String memberID = memberId.getText();
        int member_id = Integer.parseInt(memberID);

        // FIXME
        Date startDateTime = new Date();
        Date endDateTime = new Date();

        String status = taskStatus.getText();

        Task task = new Task(id, name, team_id, member_id, startDateTime, endDateTime, status);

        Element element = new Element(-1, task.getId(), elementField.getText());

        // TODO handle if the task is not updated
        conn.updateTask(task, element);

        app.refreshTaskView(conn.getAllTasks());
        cancelCliked();
    }

    private void cancelCliked(){
        dispose();
        setVisible(false);
    }

}
