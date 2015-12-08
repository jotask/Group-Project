package com.github.jotask.groupproject.gui;

import com.github.jotask.groupproject.database.DataBase;
import com.github.jotask.groupproject.model.Task;
import com.github.jotask.groupproject.util.Util;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TaskDialog extends JDialog {

    private Application app;
    private DataBase db;

    private final JPanel contentPanel = new JPanel();
    private JTextField taskId;
    private JTextField taskName;
    private JTextField teamId;
    private JTextField memberId;
    private JTextField startDate;
    private JTextField endDate;
    private JTextField taskStatus;

    /**
     * Create the dialog.
     */
    public TaskDialog(Application app, DataBase db) {
        this.app = app;
        this.db = db;
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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
            taskId = new JTextField();
            taskId.setEditable(false);
            contentPanel.add(taskId, "cell 1 0,growx");
            taskId.setColumns(10);
        }
        {
            JLabel lblName = new JLabel("Name");
            contentPanel.add(lblName, "cell 0 1,alignx trailing");
        }
        {
            taskName = new JTextField();
            contentPanel.add(taskName, "cell 1 1,growx");
            taskName.setColumns(10);
        }
        {
            JLabel lblTeamid = new JLabel("TeamID:");
            contentPanel.add(lblTeamid, "cell 0 2,alignx trailing");
        }
        {
            teamId = new JTextField();
            teamId.setEditable(false);
            contentPanel.add(teamId, "cell 1 2,growx");
            teamId.setColumns(10);
        }
        {
            JLabel lblMemberid = new JLabel("MemberID:");
            contentPanel.add(lblMemberid, "cell 0 3,alignx trailing");
        }
        {
            memberId = new JTextField();
            memberId.setEditable(false);
            contentPanel.add(memberId, "cell 1 3,growx");
            memberId.setColumns(10);
            memberId.setText(Integer.toString(app.getUser().getId()));
        }
        {
            JLabel lblStartdate = new JLabel("StartDate");
            contentPanel.add(lblStartdate, "cell 0 4,alignx trailing");
        }
        {
            startDate = new JTextField();
            contentPanel.add(startDate, "cell 1 4,growx");
            startDate.setColumns(10);
            //TODO Delete this
            java.util.Date d = new java.util.Date();
            startDate.setText(new Timestamp(new java.util.Date().getTime()).toString());
        }
        {
            JLabel lblEnddate = new JLabel("EndDate:");
            contentPanel.add(lblEnddate, "cell 0 5,alignx trailing");
        }
        {
            endDate = new JTextField();
            contentPanel.add(endDate, "cell 1 5,growx");
            endDate.setColumns(10);
            // TODO Delete this
            endDate.setText(new Timestamp(new java.util.Date().getTime()).toString());
        }
        {
            JLabel lblStatus = new JLabel("Status:");
            contentPanel.add(lblStatus, "cell 0 6,alignx trailing");
        }
        {
            taskStatus = new JTextField();
            contentPanel.add(taskStatus, "cell 1 6,growx");
            taskStatus.setColumns(10);
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
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        okClicked();
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
        setVisible(true);
    }

    private void okClicked(){

        int id = -1;
        String name = taskName.getText();
        String teamID = this.teamId.getText();
        if(teamID.equals("")) teamID = "1";
        int team_id = Integer.parseInt(teamID);
        String memberID = memberId.getText();
        if(memberID.equals("")) memberID = "1";
        int member_id = Integer.parseInt(memberID);
        Timestamp startDateTime = Util.stringToTimetamp(startDate.getText());
        Timestamp endDateTime = Util.stringToTimetamp(endDate.getText());
        String status = taskStatus.getText();

        Task task = new Task(id, name, team_id, member_id, startDateTime, endDateTime, status);

        try {
            db.getTaskDAO().insertTask(task);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        app.refreshTaskView();
        cancelCliked();
    }

    private void cancelCliked(){
        dispose();
        setVisible(false);
    }

}
