package com.github.jotask.groupproject.connection;

import com.github.jotask.groupproject.model.Task;
import com.github.jotask.groupproject.model.User;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Jose Vives on 25/01/2016.
 *
 * @author Jose Vives.
 * @since 25/01/2016
 */
public class Offline {

    private User user;
    private ArrayList<Task> tasks;

    public Offline(User user) {
        this.user = user;
    }

    public boolean loadFromFile(String forename){
        File userData = new File(forename + ".user");

        tasks = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(userData)))
        {

            String sCurrentLine;
            int id = Integer.parseInt(br.readLine());
            String mail = br.readLine();
            String surname = br.readLine();

            this.user = new User(id, surname, forename, mail);

            while ((sCurrentLine = br.readLine()) != null) {
                Task toAdd = Task.stringToTask(sCurrentLine);
                if (toAdd != null)
                    tasks.add(toAdd);
            }
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void saveToFile(){

        File userData = new File(user.getFirstName()+".user");
        if (!userData.exists()) {
            try {
                userData.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileWriter fw = new FileWriter(userData);
            fw.write(user.getId() + "\n");
            fw.write(user.getMail() + "\n");
            fw.write(user.getSurname() + "\n");
            if (tasks.size() > 0) {
                for (Task task : tasks){
                    fw.write(task.toString() + "\n");
                }
            }
            fw.flush();
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void updateTask(DataBase dataBase){
        this.tasks = dataBase.getTaskDAO().getAllTasks(user);
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public User getUser() {
        return user;
    }
}