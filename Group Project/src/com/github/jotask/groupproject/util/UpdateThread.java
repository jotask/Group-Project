package com.github.jotask.groupproject.util;

import com.github.jotask.groupproject.connection.Connection;
import com.github.jotask.groupproject.connection.Offline;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class holds the thread for update the file every x seconds.
 * They update the file for the application
 *
 * @author Jose Vives.
 * @author Jack Thomson.
 *
 * @since 1.3 - Now close the thread when we exit the application
 */
public class UpdateThread{

    /** The name for this task thread */
    private final String taskName;

    /** The delay for update the thread */
    private final long delay;

    /** The task that is call every time */
    private LoopTask task;

    /** The timer for count how many seconds have been passed */
    private Timer timer;

    /** The connection instance */
    private Connection connection;

    /**
     * Constructor for this class that set up the update threads
     *
     * @param connection
     *      The connection instance
     * @param taskName
     *      The task name for this object
     * @param seconds
     *      Seconds for update the task we want
     */
    public UpdateThread(Connection connection, String taskName, long seconds) {
        this.taskName = taskName;

        this.delay = seconds * 1000;
        this.task = new LoopTask();
        this.timer = new Timer(this.taskName);

        this.connection = connection;

    }

    /**
     * Initialize everything needed for the tread and start the thread.
     */
    public void start(){
        timer = new Timer(this.taskName);
        Date executionDate = new Date();
        timer.scheduleAtFixedRate(task, executionDate, delay);
    }

    /**
     * Close all the thread
     */
    public void close(){
        task.cancel();
        timer.cancel();
        timer.purge();
    }

    /**
     * Inner class that holds the update methods that is call every X seconds
     */
    private class LoopTask extends TimerTask{

        @Override
        public void run() {
            Offline offline = connection.getOffline();
            offline.setTasks(connection.getAllTasksWithElements());
            offline.saveToFile();
        }

    }
}