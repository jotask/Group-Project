package com.github.jotask.groupproject.gui;


import com.github.jotask.groupproject.connection.DataBase;
import com.github.jotask.groupproject.connection.dao.MemberDAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

/**
 * Created by Jack on 27/01/2016.
 */
public class LoginTest {

    /** Path and the file for the properties */
    public static final String PROPERTIES_FILE = "resources/config.properties";

    private int testFreq = 1000;

    private static final String DRIVER = "com.mysql.jdbc.Driver";

    private ArrayList<String> grabNames() {
        Properties properties = new Properties();
        ArrayList<String> names = new ArrayList<String>();

        try {
            properties.load(new FileInputStream(PROPERTIES_FILE));
            DataBase database = new DataBase(properties);
            assertEquals(database,database);
            Class.forName(DRIVER);

            String user = properties.getProperty("db_user");

            String password = properties.getProperty("db_passwd");

            // Create the URL
            StringBuilder DBURL = new StringBuilder();
            DBURL.append(properties.getProperty("db_url"));
            DBURL.append(properties.getProperty("db_server") + ":");
            DBURL.append(properties.getProperty("db_port") + "/");
            DBURL.append(properties.getProperty("db_db"));

            String url = DBURL.toString();

            Connection conn = (Connection) DriverManager.getConnection(url, user, password);

            MemberDAO memberDAO = new MemberDAO(database,conn);
            names = memberDAO.getAllUser();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return names;
    }



    @org.junit.Test
    public void incorrectLogin() {
        ArrayList<String> names = grabNames();
        for (int i = 0; i < testFreq; i ++) {
            String testString = Long.toHexString(Double.doubleToLongBits(Math.random()));

        }
    }

}