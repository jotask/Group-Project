package com.github.jotask.groupproject.database.dao;

import com.github.jotask.groupproject.database.DataBase;
import com.github.jotask.groupproject.model.Task;
import com.github.jotask.groupproject.model.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Jose Vives on 01/12/2015.
 *
 * @author Jose Vives.
 * @since 01/12/2015
 */
public class TaskDao extends DAO{

    /**
     * @param db   The actual DataBase instance
     * @param conn
     */
    public TaskDao(DataBase db, Connection conn) {
        super(db, conn);
    }

    public ArrayList<Task> getAllTasks(User user){
        // TODO get all tasks from database
        ArrayList<Task> tasks = new ArrayList<>();

        Statement stm = null;
        ResultSet rs = null;

        try {

            String sql = "SELECT * FROM task WHERE member_id=\"" + user.getId() + "\"";
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);

            while(rs.next()){
                Task task = createTask(rs);
                tasks.add(task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                close(stm, rs);
            } catch (SQLException e) {
                // TODO nothing to do
                e.printStackTrace();
            }
        }

        return tasks;

    }

    private Task createTask(ResultSet rs) throws SQLException {

        System.out.println("created");

        int id = rs.getInt("task_id");
        String name = rs.getString("task_name");
        int team_id = rs.getInt("team_id");
        int member_id = rs.getInt("member_id");
        String startDate = rs.getString("start_date");
        String endDate = rs.getString("end_date");
        String status = rs.getString("task-status");

        return new Task(id, name, team_id, member_id, startDate, endDate, status);
    }

    public void insertTask(Task task) throws SQLException {
        PreparedStatement stm = null;
        try{
            stm = conn.prepareStatement("insert into task (name, team_id, member_id, start_date, end_date, status) values ( ?, ?, ?, ?, ?, ?)");
            stm.setString(1, task.getName());
            stm.setInt(2, task.getTeam_id());
            stm.setInt(3, task.getMember_id());
            stm.setString(4, task.getStartDate());
            stm.setString(5, task.getEndDate());
            stm.setString(6, task.getStatus());
            stm.executeUpdate();
        }finally{
            close(stm, null);
        }
    }
}