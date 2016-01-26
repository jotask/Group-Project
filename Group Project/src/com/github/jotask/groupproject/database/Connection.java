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
        this.properties = properties;

        this.offline = new Offline(user);
    }

    public boolean online(String username, char[] password){

        this.dataBase = new DataBase(this.properties);
        user = this.dataBase.getMemberDao().login(username, password);

        if(user == null){
            // TODO user not found pop up a dialog for choose between offline o try login again or something

            return false;
        }

//        this.thread = new UpdateThread(dataBase, "Database Update", 300);
//        this.thread.start();

        this.isOnline = true;
        return isOnline;

    }

    public boolean offline(String username, char[] password){

        System.out.println("offline");
        // TODO

        this.user = new User(1, username, "f", "m", "p");

        this.isOnline = false;
        return isOnline;

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
        // TODO
        return null;
    }


    public boolean updateTask(Task task) {
        // TODO update task
        return false;
    }
}
