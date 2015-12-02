package com.github.jotask.groupproject.model;

/**
 * Created by Jose Vives on 02/12/2015.
 *
 * @author Jose Vives.
 * @since 02/12/2015
 */
public class Element {

    private int id;
    private int taskID;
    private String name;
    private String description;
    private String comment;

    public Element(int id, int taskID, String name, String description, String comment) {

        this.id = id;
        this.taskID = taskID;
        this.name = name;
        this.description = description;
        this.comment = comment;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
