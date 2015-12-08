package com.github.jotask.groupproject.database;

import com.github.jotask.groupproject.database.dao.ElementDAO;
import com.github.jotask.groupproject.database.dao.MemberDAO;
import com.github.jotask.groupproject.database.dao.TaskDao;
import com.github.jotask.groupproject.database.dao.TeamDao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Main class for hold all we need for retrieve information and update
 * information for the database
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
	 * The connection between our code and the database
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
	public DataBase() {
		
		// Load properties from the configuration file
		String url, user, password;
		{
			try{
				props = new Properties();
				props.load(new FileReader("resources/database.properties"));
			} catch (FileNotFoundException e) {
				// TODO Handle exception
			} catch (IOException e) {
				// TODO Handle exception
			}
			user = props.getProperty("user");

			password = props.getProperty("passw");

			// Create the URL
			StringBuilder DBURL = new StringBuilder();
			DBURL.append(props.getProperty("url"));
			DBURL.append(props.getProperty("server") + ":");
			DBURL.append(props.getProperty("port") + "/");
			DBURL.append(props.getProperty("db"));
			
			url = DBURL.toString();

		}
		
		// Create the connection using the mySQL driver and connect to the DataBase server for
		// instantiate the connection
		{
			try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				// TODO Connection not created
				e.printStackTrace();
				System.exit(1);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				// TODO Handle the class not found exception
				System.exit(1);
			}
		}
		
		// Initialise all our DAO objects
		{
			this.memberDao = new MemberDAO(this, conn);
			this.elementDAO = new ElementDAO(this, conn);
			this.taskDAO = new TaskDao(this, conn);
			this.teamDAO = new TeamDao(this, conn);
		}
		
	}

	public MemberDAO getMemberDao() {
		return memberDao;
	}

	public TaskDao getTaskDAO() {
		return taskDAO;
	}

	public TeamDao getTeamDAO() {
		return teamDAO;
	}

	public ElementDAO getElementDAO() {
		return elementDAO;
	}
}
