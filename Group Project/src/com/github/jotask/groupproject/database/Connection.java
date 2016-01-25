package com.github.jotask.groupproject.database;

import com.github.jotask.groupproject.model.Task;
import com.github.jotask.groupproject.model.User;
import com.github.jotask.groupproject.util.Offline;
import com.github.jotask.groupproject.util.UpdateThread;

import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by Jose Vives on 25/01/2016.
 *
 * @author Jose Vives.
 * @since 25/01/2016
 */
public class Connection {

    private Properties properties;
    private boolean isOnline;

    private User user;

    // Online
    private DataBase dataBase;
    private UpdateThread thread;

    private Offline offline;

    // For online connection
    public Connection(Properties properties) {

        this.isOnline = true;
        this.properties = properties;

        this.dataBase = new DataBase(this.properties);
        this.thread = new UpdateThread(dataBase, "Database Update", 300);
        this.thread.start();

    }

    // For offline connection
    public Connection(String forename){
        this.isOnline = false;
    }

    public DataBase getDataBase() {
        return dataBase;
    }

    public UpdateThread getThread() {
        return thread;
    }

    public void close(){
        if(this.thread != null){
            thread.close();
        }
        if(this.dataBase != null){
            dataBase.close();
        }
    }

    public User getUser(){
        return this.user;
    }

    public Task getTask(int t) {
        // TODO
        return null;
    }

    public ArrayList<Task> getTasks() {
        return null;
    }
}
