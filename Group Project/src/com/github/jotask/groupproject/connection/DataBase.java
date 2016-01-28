package com.github.jotask.groupproject.connection;

import com.github.jotask.groupproject.connection.dao.ElementDao;
import com.github.jotask.groupproject.connection.dao.MemberDao;
import com.github.jotask.groupproject.connection.dao.TaskDao;
import com.github.jotask.groupproject.connection.dao.TeamDao;
import com.github.jotask.groupproject.model.Task;
import com.github.jotask.groupproject.model.User;

import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Main class for hold all we need for retrieve information and update
 * information for the connection
 *
 * @author Jose Vives
 *
 * @version 1.0
 *
 */
public class DataBase {
	
	/**
	 * The JBDC we gonna use for the connection for an MySQL server
	 */
	private static final String DRIVER = "com.mysql.jdbc.Driver";

    /**
	 * The connection between our code and the connection
	 */
	private Connection conn;
	
	/**
	 * Instance for the userDAO object
	 */
	private MemberDao memberDao;
	private TaskDao taskDAO;
	private TeamDao teamDAO;
	private ElementDao elementDAO;
	
	/**
	 * Constructor the main class, we instantiate and load our configuration from a file.
	 * And we create the connection between our code and the DataBase server
     *
     * @param properties
     *      The properties for load all the configuration
	 */
	public DataBase(Properties properties) {
		
		// Load properties from the configuration file
		String url, user, password;
		{

			user = properties.getProperty("db_user");

			password = properties.getProperty("db_passwd");

			// Create the URL
            url = properties.getProperty("db_url") +
                    properties.getProperty("db_server") + ":" +
                    properties.getProperty("db_port") + "/" +
                    properties.getProperty("db_db");

		}
		
		// Create the connection using the mySQL driver and connect to the DataBase server for
		// instantiate the connection
        {
            try {
                Class.forName(DRIVER);
                conn = DriverManager.getConnection(url, user, password);

                // Initialise all our Dao objects
                {
                    this.memberDao = new MemberDao(this, conn);
                    this.elementDAO = new ElementDao(this, conn);
                    this.taskDAO = new TaskDao(this, conn);
                    this.teamDAO = new TeamDao(this, conn);
                }

            } catch (Exception e) {
                //Handle errors for Class.forName
                e.printStackTrace();
            }
        }

		
	}

    /**
     * Get all tasks for a specified user
     *
     * @param user
     *      The user we want all his task
     * @return
     *      An ArrayList with all the tasks from this user
     */
	public ArrayList<Task> getTasks(User user) {
        return getTaskDAO().getAllTasks(user);
    }

    /**
     * Get the Dao object for member
     *
     * @return
     *      The memberDAO object
     */
	public MemberDao getMemberDao() {
		return memberDao;
	}

    /**
     * Get the Dao object for Task
     *
     * @return
     *      The taskDAO Object
     */
	public TaskDao getTaskDAO() {
		return taskDAO;
	}

    /**
     * Get the Dao object for team
     *
     * @return
     *      The teamDAO Object
     */
	public TeamDao getTeamDAO() {
		return teamDAO;
	}

    /**
     * Get the Dao object for element
     *
     * @return
     *      The elementDAO object
     */
	public ElementDao getElementDAO() {
		return elementDAO;
	}

    /**
     * Close the connection between the source code and the server
     */
	public void close(){
		try {
			this.conn.close();
		} catch (SQLException e) {
			// I think is nothing to do here
		}
	}


}
