package com.github.jotask.groupproject.util;

import com.github.jotask.groupproject.database.DataBase;
import com.github.jotask.groupproject.model.User;

import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Jose Vives on 23/01/2016.
 *
 * @author Jose Vives.
 * @since 23/01/2016
 */
public class UpdateThread{

    private final String taskName;
    private long delay;
    private LoopTask task;
    private Timer timer;

    private boolean isFinish;

    private Properties properties;
    private DataBase database;

    public UpdateThread(Properties properties, String taskName, long seconds) {
        this.properties = properties;
        this.taskName = taskName;

        this.isFinish = true;

        this.delay = seconds * 1000;
        this.task = new LoopTask();
        this.timer = new Timer(this.taskName);

        this.database = new DataBase(this.properties);

    }

    public void start(){
        timer = new Timer(this.taskName);
        Date executionDate = new Date();
        timer.scheduleAtFixedRate(task, executionDate, delay);
    }

    private class LoopTask extends TimerTask{

        @Override
        public void run() {



            System.out.println("This is printed each " + delay / 1000 + " seconds.");
        }

    }

    public boolean isFinish(){
        return this.isFinish;
    }

    public User getUser(){
        return null;
    }

}
