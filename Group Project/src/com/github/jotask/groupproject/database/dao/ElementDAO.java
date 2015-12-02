package com.github.jotask.groupproject.database.dao;

import com.github.jotask.groupproject.database.DataBase;
import com.github.jotask.groupproject.model.Element;
import com.github.jotask.groupproject.model.Task;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Jose Vives on 02/12/2015.
 *
 * @author Jose Vives.
 * @since 02/12/2015
 */
public class ElementDAO extends DAO{

    /**
     * @param db   The actual DataBase instance
     * @param conn
     */
    protected ElementDAO(DataBase db, Connection conn) {
        super(db, conn);
    }

    public ArrayList<Element> getAllElemenOnTask(Task task){
        ArrayList<Element> elements = new ArrayList<>();
        // TODO get all elements from database

        Statement stm = null;
        ResultSet rs = null;

        try {

            String sql = "SELECT * FROM element WHERE member_id=\"" + task.getId() + "\"";
            rs = stm.executeQuery(sql);

            while(rs.next()){
                Element element = createElemnt(rs);
                elements.add(element);
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
        return elements;
    }

    private Element createElemnt(ResultSet rs) throws SQLException {

        int id = rs.getInt("element_id");
        int taskID = rs.getInt("task_id");
        String name = rs.getString("element_name");
        String description = rs.getString("task_description");
        String comment = rs.getString("task_commnet");

        Element element = new Element(id, taskID, name, description, comment);
        return element;

    }
}
