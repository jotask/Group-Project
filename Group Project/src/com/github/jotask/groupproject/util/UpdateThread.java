package com.github.jotask.groupproject.util;

import com.github.jotask.groupproject.connection.DataBase;
import com.github.jotask.groupproject.model.User;

import java.util.Date;
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

    private DataBase database;

    public UpdateThread(DataBase db, String taskName, long seconds) {
        this.taskName = taskName;

        this.isFinish = true;

        this.delay = seconds * 1000;
        this.task = new LoopTask();
        this.timer = new Timer(this.taskName);

        this.database = db;

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

    public DataBase getDB() {
        return database;
    }

    public void close(){
        timer.cancel();
    }
}
