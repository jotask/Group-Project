package com.github.jotask.groupproject.connection;

import com.github.jotask.groupproject.model.Element;
import com.github.jotask.groupproject.model.Task;
import com.github.jotask.groupproject.model.User;

import java.io.*;
import java.util.ArrayList;

/**
 * Offline mode for connection
 *
 * @author Jack Thomson
 * @author Jose Vives.
 *
 * @version 1.4 now load tasks
 */
public class Offline {

    private static final String PATH = "resources/data/";

    /** The user that we are logged */
    private User user;

    /** An instance of all tasks */
    private ArrayList<Task> tasks;

    /**
     * Constructor for this class
     * @param user
     *      The user for the login
     */
    public Offline(User user) {
        this.user = user;
    }

    /**
     * Load all the task from one file
     *
     * @param forename
     *      The username that we want find and load all his information
     *
     * @return
     *      If have been find it that user
     */
    public boolean loadFromFile(String forename){

        // Try to read a file
        File userData = new File(PATH + forename + ".user");
        try (BufferedReader br = new BufferedReader(new FileReader(userData))){

            String sCurrentLine;
            int id = Integer.parseInt(br.readLine());
            String mail = br.readLine();
            String surname = br.readLine();

            this.user = new User(id, surname, forename, mail);
            this.tasks = new ArrayList<>();

            while ((sCurrentLine = br.readLine()) != null) {
                Task toAdd = Task.stringToTask(sCurrentLine);
                if (toAdd != null)
                    tasks.add(toAdd);
            }
            return true;

    } catch (IOException e) {
            // The file doesn't exist so we can't do to much
        }
        return false;
    }

    /**
     * Save all the information to a file
     */
    public void saveToFile(){

        // Find the file if exist
        File userData = new File(PATH + user.getFirstName()+".user");

        // If not exist create a new one
        if (!userData.exists()) {
            try {
                userData.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Write everything on a file
        try {
            FileWriter fw = new FileWriter(userData);
            fw.write(user.getId() + "\n");
            fw.write(user.getMail() + "\n");
            fw.write(user.getSurname() + "\n");
            if (!tasks.isEmpty()) {
                for (Task task : tasks){
                    fw.write(task.toString() + "\n");
                }
            }
            fw.flush();
            fw.close();
        } catch (FileNotFoundException e) {
            // Impossible exception because we check before if exist
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Get all elements for a specified task
     *
     * @param task
     *      The task we want his elements
     *
     * @return
     *      All the elements for the selected task
     */
    public ArrayList<Element> getAllElementOnTask(Task task) {

        if(tasks != null){
            for (Task t: tasks){
                if(t.getId() == task.getId()){
                    return t.getElements();
                }
            }
        }
        return null;
    }

    /**
     * Update a task
     * We add the new element to the task it self
     * and we save the changes on the file
     *
     * @param task
     *      The task updated
     *
     * @param element
     *      The element to add
     */
    public void updateTask(Task task, Element element) {
            if(tasks != null){

                ArrayList<Element> elements = task.getElements();
                if(elements == null){
                    elements = new ArrayList<>();
                }

                task.setElements(elements);

                // Get task from array
                int index = 0;
                for(Task t: tasks){
                    if(t.getId() == task.getId()){
                        index = tasks.indexOf(t);
                    }
                }

                tasks.set(index, task);

                saveToFile();

            }
    }

    /**
     * Get a task by his ID
     *
     * @param taskID
     *      The id for the task we want search
     *
     * @return
     *      The task selected
     */
    public Task getTask(int taskID) {
        if(tasks != null){
            for(Task t: tasks){
                if(t.getId() == taskID){
                    return t;
                }
            }
        }
        return null;
    }

    /**
     * Set all tasks for this user
     *
     * @param tasks
     *      The new tasks
     */
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Get all task
     *
     * @return
     *      All the task for this user
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Get the user that we are logged
     *
     * @return
     *      The user that we are logged
     */
    public User getUser() {
        return user;
    }

}