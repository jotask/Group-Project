package com.github.jotask.groupproject.database;

import com.github.jotask.groupproject.model.Task;
import com.github.jotask.groupproject.model.User;
import com.github.jotask.groupproject.util.Offline;
import com.github.jotask.groupproject.util.UpdateThread;

import java.sql.SQLException;
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

        // TODO update the file
//        this.thread = new UpdateThread(dataBase, "Database Update", 300);
//        this.thread.start();

        this.isOnline = true;
        return isOnline;

    }

    public boolean offline(String username, char[] password){

        System.out.println("offline");

        this.user = new User(1, username, "f", "m", "p");

        Offline offlineHandler = new Offline(user);



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

    public Task getTask(int taskID) {
        Task task = null;
        if(this.isOnline){
            task = dataBase.getTaskDAO().getTask(taskID);
        }else{
            // TODO offline get task by id
        }
        return task;
    }

    public ArrayList<Task> getTasks() {
        // TODO
        ArrayList<Task> tasks = null;
        if(this.isOnline){
            tasks = dataBase.getTasks(user);
        }else{
            // TODO offline get all task offline
        }
        return tasks;
    }


    public boolean updateTask(Task task) {
        if(this.isOnline){
            try {
                dataBase.getTaskDAO().updateTask(task);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            return true;

        }else{
            // TODO offline update a task
        }
        return false;
    }
}
