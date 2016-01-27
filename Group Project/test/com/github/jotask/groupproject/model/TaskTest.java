package com.github.jotask.groupproject.model;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Jack on 27/01/2016.
 */
public class TaskTest {

    private int testFreq = 1000;

    private Task testTask = new Task(0,null,0,0,null,null,null);
    private Random rnd = new Random();

    /*
     Should be 0 from init
     */
    @org.junit.Test
    public void testGetMember_id() throws Exception {
        assertTrue(0 == testTask.getMember_id());
    }

    /*
     Testing the getters and setters for the status
     */
    @org.junit.Test
    public void testSetStatus() throws Exception {
        for (int i = 0; i < testFreq; i++) {
            String testString = Long.toHexString(Double.doubleToLongBits(Math.random()));
            testTask.setStatus(testString);
            assertEquals(testString,testTask.getStatus());
        }
    }


    /*
     Creating a random arraylist and assigning data to it
     */
    @org.junit.Test
    public void testSetElements() throws Exception {
        for (int i = 0 ; i < testFreq; i++) {
            int loopFreq = rnd.nextInt(20);
            ArrayList<Element> testList = new ArrayList<Element>();
            for (int x = 0; x < loopFreq; x++) {
                int team_id = rnd.nextInt();
                int task_id = rnd.nextInt();
                String testString = Long.toHexString(Double.doubleToLongBits(Math.random()));
                Element tmp = new Element(team_id,task_id,testString);
                testList.add(tmp);
            }
            testTask.setElements(testList);
            assertEquals(testList,testTask.getElements());
        }
    }

}