package com.github.jotask.groupproject.util;

import com.github.jotask.groupproject.database.DataBase;
import com.github.jotask.groupproject.model.Task;
import com.github.jotask.groupproject.model.User;

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

    private void loadFromFile(){

    }

    private void saveToFile(){

    }

    public void updateTask(DataBase dataBase){
        this.tasks = dataBase.getTaskDAO().getAllTasks(user);
    }
}