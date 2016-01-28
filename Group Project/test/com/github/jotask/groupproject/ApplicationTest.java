//package com.github.jotask.groupproject;
//
//import com.github.jotask.groupproject.connection.Connection;
//import com.github.jotask.groupproject.connection.DataBase;
//import com.github.jotask.groupproject.model.User;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runners.model.Statement;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.lang.reflect.Array;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Properties;
//import java.util.Random;
//
//import static org.junit.Assert.*;
//
///**
// * @author Jose Vives Iznardo
// * @since 28/01/2016
// */
//public class ApplicationTest {
//
//    private static final int LIMIT = 10;
//
//    private Properties properties;
//    private DataBase db;
//
//    private Random random;
//
//    @Before
//    public void setUp() throws Exception {
//
//        {
//            // Init and load properties from the file
//            try {
//                this.properties = new Properties();
//                this.properties.load(new FileInputStream("resources/" + ApplicationGUI.PROPERTIES_FILE));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        db = new DataBase(properties);
//
//        random = new Random();
//
//    }
//
//    @Test
//    public void testLogin(){
//
//    }
//
//    @Test
//    public void registerUser(){
//        // Creating random users
//        ArrayList<User> users = new ArrayList<>();
//        for(int i = 0; i < LIMIT; i++){
//            User user = createRandomUser();
//            users.add(user):
//        }
//        // Register User
//        for(User user: users) {
//            db.getMemberDao().register(user.getFirstName(), user.getSurname(), user.getMail());
//        }
//        // Check if all members has been created correctly
//
//    }
//
//    private ArrayList<User> getAllUsers(){
//        Statement stm;
//        ResultSet rs;
//
//        String sql = "SELECT * FROM MEMBER";
//
//        stm =
//
//
//    }
//
//    private User createRandomUser(){
//        int id = -1;
//        String firstName = "name" + random.nextInt(100);
//        String surName = "surname" + random.nextInt(100);
//        String mail = "mail" + random.nextInt(100);
//        return new User(id, firstName, surName, mail);
//    }
//
//    @After
//    public void tearDown() throws Exception {
//
//    }
//}