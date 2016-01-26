package com.github.jotask.groupproject.model;

import java.util.Date;

/**
 * Created by Jose Vives on 30/11/2015.
 *
 * @author Jose Vives.
 * @since 30/11/2015
 */
public class Task {

    private int id;
    private String name;
    private int team_id;
    private int member_id;
    private Date startDate;
    private Date endDate;
    private String status;
    private char separator = '|';

    public Task(int id, String name, int team_id, int member_id, Date startDate, Date endDate, String status) {
        this.id = id;
        this.name = name;
        this.team_id = team_id;
        this.member_id = member_id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
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

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        String output = Integer.toString(id);
        output += separator;
        return output;
    }
}
