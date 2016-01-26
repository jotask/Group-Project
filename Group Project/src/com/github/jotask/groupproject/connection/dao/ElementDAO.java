package com.github.jotask.groupproject.connection.dao;

import com.github.jotask.groupproject.connection.DataBase;
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
    public ElementDAO(DataBase db, Connection conn) {
        super(db, conn);
    }

    public ArrayList<Element> getAllElementOnTask(Task task){
        ArrayList<Element> elements = new ArrayList<>();

        Statement stm = null;
        ResultSet rs = null;

        try {

            String sql = "SELECT * FROM TASK_ELEMENT WHERE TASK_ID = " + task.getId() + ";";
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);

            while(rs.next()){
                Element element = createElement(rs);
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

    private Element createElement(ResultSet rs) throws SQLException {

        int id = rs.getInt("ELEMENT_ID");
        int taskID = rs.getInt("TASK_ID");
        String name = rs.getString("ELEMENT_NAME");
        String comment = rs.getString("TASK_COMMENT");

        Element element = new Element(id, taskID, comment);
        return element;

    }

    public boolean addElement(Element element){

        boolean success;

        int taskId = element.getTaskID();
        String comment = element.getDescription().toString();
        String name = "name";
        String desc = "nothing";

        String sql = "INSERT INTO `TASK_ELEMENT` (`TASK_ID`, `TASK_COMMENT`, `ELEMENT_NAME`, `TASK_DESCRIPTION`) VALUES ('" + taskId + "', '" + comment + "', '" + name + "', '" + desc + "');";

        Statement stm = null;

        try {
            stm = conn.createStatement();
            stm.executeUpdate(sql);
            success = true;
        } catch (SQLException e) {
            // TODO not created handle
            e.printStackTrace();
            success = false;
        }finally {
            try {
                close(stm);
            } catch (SQLException e) {
                // TODO Nothing we can do
            }
        }
        return success;
    }
}
