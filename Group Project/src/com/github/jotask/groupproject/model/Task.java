package com.github.jotask.groupproject.model;

import com.github.jotask.groupproject.util.Util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


/**
 * Task model for one task. This class holds all the parameters that one task needs, all
 * his information
 *
 * @author Jose Vives.
 * @author Jack Thomson
 *
 * @version 1.3 - Convert a task to a string for store it on a file
 */
public class Task {

    /** The task id */
    private int id;

    /** The task name */
    private String name;

    /** The team id that this task belows */
    private int team_id;

    /** The member id that this task belows */
    private int member_id;

    /** When this task starts */
    private Date startDate;

    /** When this task ends */
    private Date endDate;

    /** The status for this task */
    private String status;

    /** An array list of all elements that one task have */
    private ArrayList<Element> elements;

    /**
     * Constructor for a task
     *
     * @param id
     *      The task id
     * @param name
     *      The name for this task
     * @param team_id
     *      The team id for this task
     * @param member_id
     *      The member id for this task
     * @param startDate
     *      The start date for this task
     * @param endDate
     *      The end date for this task
     * @param status
     *      The status task for this task
     */
    public Task(int id, String name, int team_id, int member_id, Date startDate, Date endDate, String status) {
        this.id = id;
        this.name = name;
        this.team_id = team_id;
        this.member_id = member_id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    /**
     * Return a task with a string for store them on a file
     *
     * @return
     *      the task converted to a string
     *
     */
    @Override
    public String toString() {
        String output = Integer.toString(id);
        output += Util.SEPARATOR;
        output += name;
        output += Util.SEPARATOR;
        output += team_id;
        output += Util.SEPARATOR;
        output += member_id;
        output += Util.SEPARATOR;
        output += startDate;
        output += Util.SEPARATOR;
        output += endDate;
        output += Util.SEPARATOR;
        output += status;
        output += Util.SEPARATOR;
        output += elementListToString();
        output += Util.SEPARATOR;
        return output;
    }

    /**
     * Convert all elements to a string for store on a file
     *
     * @return
     *      The string wit all elements for this task
     */
    private String elementListToString() {

        String returnString = "";

        if (elements == null) {
            return returnString;
        }

        for (Element e: elements) {
            returnString += e.toString();
        }

        return returnString;
    }

    /**
     * Convert a string to a task
     *
     * @param str
     *      The string we want convert to a task
     *
     * @return
     *      The task has been created from the string information
     */
    public static Task stringToTask(String str) {
        /**
         * Splitting the string into multiple sub strings
         * for parsing and object creation
         */
        String[] separate = str.split(Character.toString(Util.SEPARATOR));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        /**
         * Parsing the strings into the correct format
         */

        if (separate[4].equals("null") || separate[5].equals("null")) {
            return null;
        }

        try {
            int id = Integer.parseInt(separate[0]);
            String name = separate[1];
            int team_id = Integer.parseInt(separate[2]);
            int member_id = Integer.parseInt(separate[3]);
            Date start_date = format.parse(separate[4]);
            Date end_date = format.parse(separate[5]);
            String status = separate[6];

            Task returnTask = new Task(id,name,team_id,member_id,start_date,end_date,status);

            returnTask.setElements(Element.stringToElements(separate[7]));

            System.out.println(returnTask.getElements().size());

            return returnTask;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Get the id for this task
     *
     * @return
     *      The id for this task
     */
    public int getId() {
        return id;
    }

    /**
     * Get the name for this task
     *
     * @return
     *      The name for this task
     */
    public String getName() {
        return name;
    }

    /**
     * Get the team id for this task
     *
     * @return
     *      The team id for this task
     */
    public int getTeam_id() {
        return team_id;
    }

    /** Get the member id for this task
     *
     * @return
     *      The member id for this task
     */
    public int getMember_id() {
        return member_id;
    }

    /** The the start date for this task
     *
     * @return
     *      The start date for this task
     */
    public Date getStartDate() {
        return startDate;
    }

    /** Get the end date for this task
     *
     * @return
     *      The end date for this task
     */
    public Date getEndDate() {
        return endDate;
    }

    /** Get the status for this task
     *
     * @return
     *      The status for this task
     */
    public String getStatus() {
        return status;
    }

    /** Set the status for this task
     *
     * @param status
     *      The status for this task
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /** Set all elements for this tasks
     *
     * @param elements
     *      The elements for this tak
     */
    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
    }

    /**
     * Get the elements for this task
     *
     * @return
     *      The elements for this task
     */
    public ArrayList<Element> getElements() {
        return elements;
    }
}
