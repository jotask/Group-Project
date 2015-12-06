package com.github.jotask.groupproject.gui;

import com.github.jotask.groupproject.model.Task;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TaskTableModel extends AbstractTableModel{

    private static final long serialVersionUID = 1429691555122236840L;
    public static final int OBJECT_COL = -1;
    private static final int ID_COL = 0;
    private static final int NAME_COL = 1;
    private static final int TEAM_COL = 2;
    private static final int START_COL = 3;
    private static final int END_COL = 4;
    private static final int STATUS_COL = 5;

    private String[] columnsNames = {"Id", "Name", "Team", "Start Date", "End Date", "Status"};

    private List<Task> tasks;

    public TaskTableModel(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public int getRowCount() { return tasks.size(); }

    @Override
    public int getColumnCount() { return columnsNames.length; }

    @Override
    public String getColumnName(int column) { return columnsNames[column]; }

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

    @Override
    public Class<? extends Object> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

}