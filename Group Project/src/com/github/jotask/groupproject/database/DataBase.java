package com.github.jotask.groupproject.database;

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

	private static final String SERVER = "localhost";
	private static final String PORT = "3306";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	private static final String DATABASE = "project";
	private static final String URL = "jdbc:mysql://";
	
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
	private TeamDao teamDao;
	
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
				props.load(new FileReader("database.properties"));
			} catch (FileNotFoundException e) {
				// TODO Handle exception
			} catch (IOException e) {
				// TODO Handle exception
			}
//			user = props.getProperty("user");
			user = USER;
//			password = props.getProperty("passw");
			password = PASSWORD;
			
			// Create the URL
			StringBuilder DBURL = new StringBuilder();
			DBURL.append(props.getProperty("url"));
			DBURL.append(props.getProperty("server") + ":");
			DBURL.append(props.getProperty("port") + "/");
			DBURL.append(props.getProperty("db"));
			
//			url = DBURL.toString();
			url = URL + SERVER + ":" + PORT + "/" + DATABASE;
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
			memberDao = new MemberDAO(this, conn);
		}
		
	}
	
	/**
	 * Get the userDAO object
	 * @return
	 * 		Our userDAO instance
	 */
	public MemberDAO getMember(){
		return memberDao;
	}

}
