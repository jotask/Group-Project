package com.github.jotask.groupproject.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


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
    private static char seperator = '#';

    private ArrayList<Element> elements;

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
        output += seperator;
        output += name;
        output += seperator;
        output += team_id;
        output += seperator;
        output += member_id;
        output += seperator;
        output += startDate;
        output += seperator;
        output += endDate;
        output += seperator;
        output += status;
        output += seperator;
        output += elementListToString();
        output += seperator;
        return output;
    }

    private String elementListToString() {
        if (elements == null) {
            System.out.println("null");
            return "---";
        }
//        System.out.println(elements.size());
        String returnString = "";
        for (Element e: elements) {
            returnString += e.toString();
        }
        return returnString;
    }

    public static Task stringToTask(String str) {
        /**
         *
         * Splitting the string into multiple sub strings
         * for parsing and object creation
         *
         */
        String[] separate = str.split(Character.toString(seperator));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        /**
         *
         * Parsing the strings into the correct format
         *
         */

        if (separate[4].equals("null") || separate[5].equals("null")) {
            System.out.println("Error with dates");
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

            return returnTask;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
    }

    public ArrayList<Element> getElements() { return this.elements; }
}
