package com.github.jotask.groupproject.connection;

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

        // TODO load elements for tasks

        // Try to read a file
        File userData = new File(forename + ".user");
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
        File userData = new File(user.getFirstName()+".user");

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
            // Impossible exception because we check before if exisst
        } catch (IOException e) {
            e.printStackTrace();
        }

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