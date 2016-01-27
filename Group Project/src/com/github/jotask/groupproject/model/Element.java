package com.github.jotask.groupproject.model;

/**
 * This class holds all the methods and parameters that one element needs
 *
 * @author Jose Vives.
 *
 * @version 1.2 Removed unused methods
 */
public class Element {

    /** The int for this element */
    private int id;

    /** The task id that this element belows */
    private int taskID;

    /** The description for this element */
    private String description;

    /** Constructor for element
     *
     * @param id
     *      The id for this element
     *
     * @param taskID
     *      The task id for this element
     *
     * @param comment
     *      The comment for this element
     */
    public Element(int id, int taskID, String comment) {
        this.id = id;
        this.taskID = taskID;
        this.description = comment;
    }

    /** Get the id for this element
     *
     * @return
     *      The ID for this element
     */
    public int getId() {
        return id;
    }

    /** Get the task id for this element
     *
     * @return
     *      The task id from this element
     */
    public int getTaskID() {
        return taskID;
    }

    /** Set the task id for this element
     *
     * @param taskID
     *      the task id for this element
     */
    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    /**
     * Get the description for this element
     *
     * @return
     *      The description for this element
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description for this element
     *
     * @param description
     *      The description for this element
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /** Convert a element to a string
     *
     * @return
     *      The string that hold all element information
     */
    @Override
    public String toString() {
        return "{"+id+":"+taskID+":"+description+"}";
    }

}
