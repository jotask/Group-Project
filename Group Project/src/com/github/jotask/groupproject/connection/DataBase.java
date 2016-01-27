package com.github.jotask.groupproject.connection;

import com.github.jotask.groupproject.connection.dao.ElementDAO;
import com.github.jotask.groupproject.connection.dao.MemberDAO;
import com.github.jotask.groupproject.connection.dao.TaskDao;
import com.github.jotask.groupproject.connection.dao.TeamDao;
import com.github.jotask.groupproject.model.Task;
import com.github.jotask.groupproject.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Main class for hold all we need for retrieve information and update
 * information for the connection
 *
 * @author Jose Vives
 * @version 0.1
 *
 */
public class DataBase {
	
	/**
	 * The JBDC we gonna use for the connection for an MySQL server
	 */
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	
	/**
	 * The properties for load all the configuration
	 */
	private Properties props;
	/**
	 * The connection between our code and the connection
	 */
	private Connection conn;
	
	/**
	 * Instance for the userDAO object
	 */
	private MemberDAO memberDao;
	private TaskDao taskDAO;
	private TeamDao teamDAO;
	private ElementDAO elementDAO;
	
	/**
	 * Constructor the main class, we instantiate and load our configuration from a file.
	 * And we create the connection between our code and the DataBase server
	 */
	public DataBase(Properties properties) {

		this.props = properties;
		
		// Load properties from the configuration file
		String url, user, password;
		{

			user = props.getProperty("db_user");

			password = props.getProperty("db_passwd");

			// Create the URL
			StringBuilder DBURL = new StringBuilder();
			DBURL.append(props.getProperty("db_url"));
			DBURL.append(props.getProperty("db_server") + ":");
			DBURL.append(props.getProperty("db_port") + "/");
			DBURL.append(props.getProperty("db_db"));
			
			url = DBURL.toString();

		}
		
		// Create the connection using the mySQL driver and connect to the DataBase server for
		// instantiate the connection
        {
            try {
                Class.forName(DRIVER);
                conn = DriverManager.getConnection(url, user, password);

                // Initialise all our DAO objects
                {
                    this.memberDao = new MemberDAO(this, conn);
                    this.elementDAO = new ElementDAO(this, conn);
                    this.taskDAO = new TaskDao(this, conn);
                    this.teamDAO = new TeamDao(this, conn);
                }

            } catch (SQLException se) {
                //Handle errors for JDBC
                se.printStackTrace();
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
     * Get the DAO object for member
     *
     * @return
     *      The memberDAO object
     */
	public MemberDAO getMemberDao() {
		return memberDao;
	}

    /**
     * Get the DAO object for Task
     *
     * @return
     *      The taskDAO Object
     */
	public TaskDao getTaskDAO() {
		return taskDAO;
	}

    /**
     * Get the DAO object for team
     *
     * @return
     *      The teamDAO Object
     */
	public TeamDao getTeamDAO() {
		return teamDAO;
	}

    /**
     * Get the DAO object for element
     *
     * @return
     *      The elementDAO object
     */
	public ElementDAO getElementDAO() {
		return elementDAO;
	}

    /**
     * Close the connection between the source code and the server
     */
	public void close(){
		try {
			this.conn.close();
		} catch (SQLException e) {
			// TODO I think is nothing to do here
			e.printStackTrace();
		}
	}

}
