package com.github.jotask.groupproject.database;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBase {
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	
	private Properties props;
	private Connection conn;
	
	public DataBase() {
		
		// Load properties from the configuration file
		String url, user, password;
		{
			try{
				props = new Properties();
				props.load(new FileReader("db.props"));
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
		
		{
			try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				// TODO Connection not created
			} catch (ClassNotFoundException e) {
				// TODO Handle the class not found exception
			}
		}
	}

}
