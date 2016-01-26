package com.github.jotask.groupproject.util;

import com.github.jotask.groupproject.database.DataBase;
import com.github.jotask.groupproject.model.Task;
import com.github.jotask.groupproject.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

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

    public void loadFromFile(){
        File userData = new File(user.getFirstName()+".user");

        try (BufferedReader br = new BufferedReader(new FileReader(userData)))
        {

            String sCurrentLine;
            user.setId(Integer.parseInt(br.readLine()));
            user.setMail(br.readLine());
            user.setSurname(br.readLine());

            while ((sCurrentLine = br.readLine()) != null) {
                Task toAdd = Task.stringToTask(sCurrentLine);
                if (toAdd != null)
                    tasks.add(toAdd);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Task getTaskRandom(Random r){
        return new Task(r.nextInt(), "test", r.nextInt(), r.nextInt(), null, null, "test");
    }

    private ArrayList<Task> getRandom(){
        Random random = new Random();
        ArrayList<Task> tasks = new ArrayList<>();
        for(int i = 0 ; i < 5; i++){
            tasks.add(getTaskRandom(random));
        }
        return  tasks;
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

        ArrayList<Task> tasks = this.getRandom();

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

}