package com.github.jotask.groupproject.model;

/**
 * Created by Jose Vives on 02/12/2015.
 *
 * @author Jose Vives.
 * @since 02/12/2015
 */
public class Element {

    // FIXME

    private int id;
    private int taskID;
    private String description;

    public Element(int id, int taskID, String comment) {

        this.id = id;
        this.taskID = taskID;
        this.description = comment;
    }

    public int getId() {
        return id;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
