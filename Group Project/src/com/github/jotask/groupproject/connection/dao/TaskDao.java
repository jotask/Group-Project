package com.github.jotask.groupproject.connection.dao;

import com.github.jotask.groupproject.connection.DataBase;
import com.github.jotask.groupproject.model.Task;
import com.github.jotask.groupproject.model.User;
import com.github.jotask.groupproject.util.Util;

import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * TAsk DAO for retrieve information from the database for objects
 *
 * @author Jose Vives.
 *
 * @version 1.1
 */
public class TaskDao extends DAO{

    /**
     * Constructor for this class
     *
     * @param db
     *          The actual DataBase instance
     * @param conn
     *          The connection instance
     */
    public TaskDao(DataBase db, Connection conn) {
        super(db, conn);
    }

    /**
     * Get all task from one selected user
     *
     * @param user
     *      The user we want all his tasks
     *
     * @return
     *      An ArrayList of Task from the selected user
     */
    public ArrayList<Task> getAllTasks(User user){

        Statement stm = null;
        ResultSet rs = null;

        ArrayList<Task> tasks = new ArrayList<>();

        try {
            String sql = "SELECT * FROM TASK WHERE MEMBER_ID=\"" + user.getId() + "\"";
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);

            while(rs.next()){
                Task task = converTask(rs);
                tasks.add(task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                close(stm, rs);
            } catch (SQLException e) {
                // Nothing to do
            }
        }

        return tasks;

    }

    /**
     * Get a task by his ID
     *
     * @param id
     *      The id from the task we want
     *
     * @return
     *      The task requested
     */
    public Task getTask(int id){

        Task task = null;

        Statement stm = null;
        ResultSet rs = null;

        try {

            String sql = "SELECT * FROM TASK WHERE TASK_ID=\"" + id + "\"";
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);

            while (rs.next())
                task = converTask(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                close(stm, rs);
            } catch (SQLException e) {
                // Nothing to do
            }
        }

        return task;
    }

    /**
     * Convert one task from one resultset
     *
     * @param rs
     *      The result that we want the information
     *
     * @return
     *      The task created
     *
     * @throws SQLException
     *      The SQL exception that holds the error message
     */
    private Task converTask(ResultSet rs) throws SQLException {

        int id = rs.getInt("task_id");
        String name = rs.getString("task_name");
        int team_id = rs.getInt("team_id");
        int member_id = rs.getInt("member_id");
        Date startDate = rs.getDate("start_date");
        Date endDate = rs.getDate("EXPECTED_COMPLETION_DATE");
        String status = rs.getString("task_status");

        return new Task(id, name, team_id, member_id, startDate, endDate, status);
    }

    /**
     * Update one task on the database
     *
     * @param task
     *      The task we want update
     *
     * @throws SQLException
     *      The SQL exception that holds the error message
     */
    public void updateTask(Task task) throws SQLException {

        PreparedStatement stm = null;

        try{
            String sql = "UPDATE `TASK` SET " +
                    "`TASK_NAME` = ?," +
                    "`TEAM_ID` = ?," +
                    "`MEMBER_ID` = ?," +
                    "`START_DATE` = ?, " +
                    "`EXPECTED_COMPLETION_DATE` = ?, " +
                    "`TASK_STATUS` = ? " +
                    "WHERE `TASK_ID` = ?;";
            stm = conn.prepareStatement(sql);
            stm.setString(1, task.getName());
            stm.setInt(2, task.getTeam_id());
            stm.setInt(3, task.getMember_id());
            stm.setDate(4, Util.toSQLDate(task.getStartDate()));
            stm.setDate(5, Util.toSQLDate(task.getEndDate()));
            stm.setString(6, task.getStatus());
            stm.setInt(7, task.getId());
            stm.executeUpdate();
        }finally{
            close(stm, null);
        }
    }
}