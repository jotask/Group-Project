package com.github.jotask.groupproject.model;

/**
 * Team model for the teams
 *
 * @author Jose Vives.
 *
 * @version 1.0
 */
public class Team {

    /** Id for this team */
    private final int id;

    /** The team name */
    private String name;

    /**
     * Constructor for the team
     *
     * @param id
     *          The id for this team
     * @param name
     *          The name for this team
     */
    public Team(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Get the Id for this team
     *
     * @return
     *      The id for this team
     */
    public int getId() {
        return id;
    }

    /**
     * The the name for this team
     *
     * @return
     *      The name from this team
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name for this team
     *
     * @param name
     *          The new name for this team
     */
    public void setName(String name) {
        this.name = name;
    }
}
