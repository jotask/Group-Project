package com.github.jotask.groupproject.database.dao;

import com.github.jotask.groupproject.database.DataBase;
import com.github.jotask.groupproject.model.Task;
import com.github.jotask.groupproject.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

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
        int id = rs.getInt("task_id");
        String name = rs.getString("task_name");
        int team_id = rs.getInt("team_id");
        int member_id = rs.getInt("memer_id");
        Date startDate = rs.getDate("star_date");
        Date endDate = rs.getDate("end_date");
        String status = rs.getString("task-status");

        return new Task(id, name, team_id, member_id, startDate, endDate, status);
    }

}