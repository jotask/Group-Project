package com.github.jotask.groupproject.util;

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

    public UpdateThread(String taskName, long seconds) {
        this.taskName = taskName;
        this.delay = seconds * 1000;
        this.task = new LoopTask();
        this.timer = new Timer(this.taskName);
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

}
