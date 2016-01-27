package com.github.jotask.groupproject.gui;

import com.github.jotask.groupproject.model.Task;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * This class is the model for the JTable for format
 * all the information that is show up on the JTable
 *
 * @author Jose Vives
 *
 * @version 1.0 - Initial version
 *
 */
public class TaskTableModel extends AbstractTableModel{

    /** The serial version for this object */
    private static final long serialVersionUID = 1429691555122236840L;

    /** The id for the all the column */
    public static final int OBJECT_COL = -1;

    /** The id for the id column */
    public static final int ID_COL = 0;

    /** The id for the name column */
    public static final int NAME_COL = 1;

    /** The id for the team column */
    public static final int TEAM_COL = 2;

    /** The id for the start date column */
    public static final int START_COL = 3;

    /** The id for the end state column */
    public static final int END_COL = 4;

    /** The id for the status column */
    public static final int STATUS_COL = 5;

    /** The names for all the columns for the table */
    private final String[] columnsNames = {"Id", "Name", "Team", "Start Date", "End Date", "Status"};

    /** An instance of all tasks */
    private List<Task> tasks;

    /**
     * Constructor for this class
     *
     * @param tasks
     *      The task for populate the JTable
     */
    public TaskTableModel(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Get how many rows we have
     *
     * @return
     *      How many rows we have
     */
    @Override
    public int getRowCount() { return tasks.size(); }

    /**
     * Get how many columns we have
     *
     * @return
     *      How many columns we have
     */
    @Override
    public int getColumnCount() { return columnsNames.length; }

    /** Get one the name of one column name
     *
     * @param column
     *      The id for that column
     *
     * @return
     *      The name for that column
     */
    @Override
    public String getColumnName(int column) { return columnsNames[column]; }

    /**
     * Get the name from the value we want
     *
     * @param rowIndex
     *      The row index for that column
     *
     * @param columnIndex
     *      The column index for that column
     *
     * @return
     *      The object that hat column have
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Task tmp = tasks.get(rowIndex);
        switch (columnIndex) {
            case ID_COL:
                return tmp.getId();
            case NAME_COL:
                return tmp.getName();
            case TEAM_COL:
                return tmp.getTeam_id();
            case START_COL:
                return tmp.getStartDate();
            case END_COL:
                return tmp.getEndDate();
            case STATUS_COL:
                return tmp.getStatus();
            default:
                return tmp.getName();
        }
    }

    /** Get the class that one column belows
     *
     * @param columnIndex
     *      The index for the column we want
     *
     * @return
     *      The class for the selected column
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

}