package com.github.jotask.groupproject.connection;

import com.github.jotask.groupproject.model.Element;
import com.github.jotask.groupproject.model.Task;
import com.github.jotask.groupproject.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Class that hold all the connection and all the logic for retrieve information
 *
 * @author Jose Vives
 *
 * @since 1.6 - Offline works correctly
 */
public class Connection {

    /** Seconds delay */
    private static final long SECONDS = 300;

    /** Properties instance */
    private final Properties properties;

    /** Know if we are working online or offline */
    private boolean isOnline;

    /** User instance that we are login */
    private User user;

    /** DataBase instance*/
    private DataBase dataBase;

    /** Thread instance */
    private UpdateThread thread;

    /** Offline instance */
    private Offline offline;

    /**
     * Constructor for this class. Just it save the instance of properties
     *
     * @param properties
     *      The properties instance
     */
    public Connection(Properties properties) {
        this.properties = properties;
    }

    /**
     * For online connection
     * We create a connection to a DataBase
     * It create the thread for get all information each time
     *
     * @param username
     *      The user for the login
     * @param password
     *      The password for the login
     * @return
     *      If is successfully login
     */
    public boolean online(String username, char[] password){

        // Create the database connection
        this.dataBase = new DataBase(this.properties);
        user = this.dataBase.getMemberDao().login(username, password);

        // Check if the user is null, for know if we have connected to the database
        if(user == null){
            return false;
        }

        // TODO check if we need to update the database with all previous offline work

        // We set if we are online. At this point we have a connection between the database and the source code
        this.isOnline = true;

        // We initialize a offline object for store information on the file
        this.offline = new Offline(user);

        // Load previous data to the database
        this.offline.loadFromFile(user.getFirstName());
        ArrayList<Task> oldTasks = this.offline.getTasks();
        if(oldTasks != null){
            syncData(oldTasks);
        }

        // We get all task for this users and we save the tasks on the file
        ArrayList<Task> tasks = dataBase.getTasks(user);
        for(Task t: tasks){
            t.setElements(getElements(t));
        }
        this.offline.setTasks(tasks);
        this.offline.saveToFile();

        // Initialize the thread
        this.thread = new UpdateThread(this, "Database Update", SECONDS);
        this.thread.start();

        return isOnline;

    }

    /**
     * Set an offline connection
     *
     * @param username
     *      The user for the login
     * @param password
     *      The password for the login
     * @return
     *      If we have an offline connection
     */
    public boolean offline(String username, char[] password){

        // Initialize a offline connection
        this.offline= new Offline(null);
        if(!this.offline.loadFromFile(username)){
            return false;
        }

        // Get the user that we have login
        this.user = offline.getUser();

        return isOnline;

    }

    /**
     * Get a task by his ID
     *
     * @param taskID
     *      The id for the task we want
     *
     * @return
     *      the task that we requested
     */
    public Task getTask(int taskID) {
        Task task;
        if(this.isOnline){
            task = dataBase.getTaskDAO().getTask(taskID);
            if(task != null){
                task.setElements(getElements(task));
            }
        }else{
            task = offline.getTask(taskID);
        }
        return task;
    }

    /**
     * Get all tasks from the user that we have login
     *
     * @return
     *      An arrayList with all task from the selected user
     */
    private ArrayList<Task> getAllTasks() {
        ArrayList<Task> tasks;
        if(this.isOnline){
            tasks = dataBase.getTasks(user);
            if(tasks != null){
                for(Task t: tasks){
                    t.setElements(getElements(t));
                }
            }
        }else{
            tasks = this.offline.getTasks();
        }
        return tasks;
    }


    /**
     * Update one task
     *
     * @param task
     *      The task we want update
     * @param element
     *      The new element for this task
     *
     * @return
     *      if has been updated correctly
     */
    public boolean updateTask(Task task, Element element) {
        if(this.isOnline){
            try {
                dataBase.getTaskDAO().updateTask(task);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            dataBase.getElementDAO().addElement(element);
            return true;

        }else{
            offline.updateTask(task, element);
        }
        return false;
    }

    /**
     * Get all elements from one task
     *
     * @param task
     *      The task we want his elements
     *
     * @return
     *      An ArrayList of element for the selected task
     */
    private ArrayList<Element> getElements(Task task){

        ArrayList<Element> elements;

        if(isOnline){
            elements = dataBase.getElementDAO().getAllElementOnTask(task);
        }else{
            elements = offline.getAllElementOnTask(task);
        }
        return elements;
    }

    /**
     * Get all tasks with all his elements
     *
     * @return
     *      All the task from the logged user with all his elements
     */
    public ArrayList<Task> getAllTasksWithElements(){

        ArrayList<Task> tasks = getAllTasks();
        for(Task t: tasks){
            t.setElements(getElements(t));
        }
        return tasks;
    }

    private void syncData(ArrayList<Task> tasks){
        System.out.println("sync");
        for(Task t: tasks){
            try {
                dataBase.getTaskDAO().updateTask(t);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            for(Element e: t.getElements()){
                if(!dataBase.getElementDAO().existElement(e)) {
                    dataBase.getElementDAO().addElement(e);
                }
            }
        }
    }

    /**
     * Close all connection and close/stop the thread
     */
    public void close(){
        if(this.thread != null){
            thread.close();
        }
        if(this.dataBase != null){
            dataBase.close();
        }
    }

    /**
     * Get te offline instance
     *
     * @return
     *      The offline instance
     */
    public Offline getOffline() {
        return offline;
    }

    /**
     * Get the user that we have connected
     *
     * @return
     *      The user that we stabilised the connection
     */
    public User getUser(){
        return this.user;
    }

}
