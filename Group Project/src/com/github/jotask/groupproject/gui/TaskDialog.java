package com.github.jotask.groupproject.gui;

import com.github.jotask.groupproject.connection.Connection;
import com.github.jotask.groupproject.model.Element;
import com.github.jotask.groupproject.model.Task;
import net.miginfocom.swing.MigLayout;
import org.jdatepicker.DateModel;
import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
    private final ApplicationGUI app;

    /** The connection for retrieve and update all information */
    private final Connection conn;

    /** The JPanel for this dialog */
    private final JPanel contentPanel;

    /** The JTexField that holds the task id */
    private final JTextField taskId;

    /** The JTexField that holds the task name */
    private final JTextField taskName;

    /** The JTexField that holds the team id */
    private final JTextField teamId;

    /** The JTexField that holds the member id */
    private final JTextField memberId;

    /** The JDatePicker that holds the start date */
    private final JDatePicker startDate;

    /** The JDatePicker that holds the end date */
    private final JDatePicker endDate;

    /** The JComboBox that holds the task status */
    private final JComboBox statusList;

    /** The JTexField that holds the element field */
    private final JTextField elementField;

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
    public TaskDialog(ApplicationGUI app, Connection conn, final Task task) {
        this.app = app;
        this.conn = conn;

        contentPanel = new JPanel();

        setTitle("Update task");
        setResizable(false);

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
            JLabel lblMemberId = new JLabel("MemberID:");
            contentPanel.add(lblMemberId, "cell 0 " + counter + ",alignx trailing");
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
            Date dateT = task.getStartDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateT);
            startDate = new JDateComponentFactory().createJDatePicker();
            DateModel date = startDate.getModel();
            date.setDay(calendar.get(Calendar.DAY_OF_MONTH));
            date.setMonth(calendar.get(Calendar.MONTH));
            date.setYear(calendar.get(Calendar.YEAR));
            contentPanel.add((Component) startDate, "cell 1 " + counter + ",growx");
        }
        counter++;
        {
            JLabel lblEndDate = new JLabel("EndDate:");
            contentPanel.add(lblEndDate, "cell 0 " + counter + ",alignx trailing");
        }
        {
            Date dateT = task.getEndDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateT);
            endDate = new JDateComponentFactory().createJDatePicker();
            DateModel date = endDate.getModel();
            date.setDay(calendar.get(Calendar.DAY_OF_MONTH));
            date.setMonth(calendar.get(Calendar.MONTH));
            date.setYear(calendar.get(Calendar.YEAR));
            contentPanel.add((Component) endDate, "cell 1 " + counter + ",growx");
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
            statusList.setSelectedItem(task.getStatus());
        }
        counter++;
        {
            JLabel lblElement = new JLabel("Previous:");
            contentPanel.add(lblElement, "cell 0 " + counter + ",alignx trailing");
        }
        {
            ArrayList<String> list = new ArrayList<>();
            ArrayList<Element> elements = task.getElements();
            if(elements != null) {
                for (Element e : elements) {
                    list.add(e.getDescription() + "   ");
                }
            }
            JList l = new JList(list.toArray());
            l.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            l.setBackground(contentPanel.getBackground());

            JScrollPane scrollPane = new JScrollPane(l);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            scrollPane.setPreferredSize(new Dimension(350,400));
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            JScrollBar bar = scrollPane.getVerticalScrollBar();
            bar.setValue(bar.getMaximum());

            contentPanel.add(scrollPane, "cell 1 " + counter + ",alignx trailing");
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

        GregorianCalendar gcS = (GregorianCalendar) startDate.getModel().getValue();
        Date startDateTime = new Date(gcS.getTime().getTime());

        GregorianCalendar gcE = (GregorianCalendar) endDate.getModel().getValue();
        Date endDateTime = new Date(gcE.getTime().getTime());

        String status = (String) statusList.getSelectedItem();

        // Create a new task for update all information
        Task task = new Task(id, name, team_id, member_id, startDateTime, endDateTime, status);

        // Create an element for this update
        Element element = new Element(-1, task.getId(), elementField.getText());

        // Update the task
        conn.updateTask(task, element);

        // Refresh the task view
        app.refreshTaskView(conn.getAllTasksWithElements());

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
