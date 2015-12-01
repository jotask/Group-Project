package com.github.jotask.groupproject.model;

/**
 * Created by Jose Vives on 30/11/2015.
 *
 * @author Jose Vives.
 * @since 30/11/2015
 */
public class Team {

    private int id;
    private String name;

    public Team(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
