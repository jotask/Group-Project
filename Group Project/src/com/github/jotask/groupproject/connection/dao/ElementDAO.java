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

            String sql = "SELECT * FROM TASK_ELEMENT WHERE TASK_ID=\"" + task.getId() + "\"";
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
        String description = rs.getString("TASK_DESCRIPTION");
        String comment = rs.getString("TASK_COMMENT");

        Element element = new Element(id, taskID, name, description, comment);
        return element;

    }
}
