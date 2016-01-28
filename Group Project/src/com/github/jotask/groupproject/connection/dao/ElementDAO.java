package com.github.jotask.groupproject.connection.dao;

import com.github.jotask.groupproject.connection.DataBase;
import com.github.jotask.groupproject.model.Element;
import com.github.jotask.groupproject.model.Task;
import com.github.jotask.groupproject.util.Util;

import java.sql.*;
import java.util.ArrayList;

/**
 * This class retrieve information from the database for the elements
 *
 * @author Jose Vives.
 *
 * @version 1.0
 */
public class ElementDAO extends DAO{

    /**
     * Constructor for this class
     *
     * @param db
     *          The actual DataBase instance
     * @param conn
     *          The connection instance
     */
    public ElementDAO(DataBase db, Connection conn) {
        super(db, conn);
    }

    /**
     * Get all elements on task from the database
     *
     * @param task
     *      The task that we want all his elements
     *
     * @return
     *      An arrayList of Elements for the selected task
     */
    public ArrayList<Element> getAllElementOnTask(Task task){

        // Init the instances
        ArrayList<Element> elements = new ArrayList<>();
        Statement stm = null;
        ResultSet rs = null;

        // Select all element from a selected task
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
                // Nothing we can do
            }
        }
        return elements;
    }

    /**
     * Create an element object from one resulset
     *
     * @param rs
     *      The resultset that have the information
     *
     * @return
     *      The element that has been created
     *
     * @throws SQLException
     *      And exception for some type of error
     */
    private Element createElement(ResultSet rs) throws SQLException {

        int id = rs.getInt("ELEMENT_ID");
        int taskID = rs.getInt("TASK_ID");
        String comment = rs.getString("TASK_DESCRIPTION");

        Element element = new Element(id, taskID, comment);
        return element;

    }

    /**
     * Add an element to the database
     *
     * @param element
     *      The element we want to add
     *
     * @return
     *      if is successfully added
     */
    public boolean addElement(Element element){

        Statement stm = null;

        boolean success;

        // Get the information
        int taskId = element.getTaskID();
        String desc = element.getDescription();

        // Create the statement
        String sql = "INSERT INTO `TASK_ELEMENT` (`TASK_ID`, `TASK_DESCRIPTION`) VALUES ('" + taskId + "', '" + desc + "');";

        // execute query
        try {
            stm = conn.createStatement();
            stm.executeUpdate(sql);
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        }finally {
            try {
                close(stm);
            } catch (SQLException e) {
                // Nothing we can do
            }
        }
        return success;
    }

    public boolean existElement(Element e){

        String sql = "SELECT EXISTS(SELECT 1 FROM TASK_ELEMENT WHERE ELEMENT_ID ='" + e.getId() + "' LIMIT 1) ";

        Statement stm = null;
        ResultSet rs = null;

        boolean exist = false;

        try {
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);

            while(rs.next()){
                exist = true;
                break;
            }

        }catch (SQLException eee){
            eee.printStackTrace();
        }finally {
            try {
                close(stm, rs);
            } catch (SQLException e1) {
                // Nothing to do
            }
        }

        return exist;

    }

}
