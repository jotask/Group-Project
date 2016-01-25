package com.github.jotask.groupproject;

import com.github.jotask.groupproject.database.DataBase;
import com.github.jotask.groupproject.gui.Login;
import com.github.jotask.groupproject.util.UpdateThread;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Start class where all start
 * @author Jose Vives
 *
 */
public class Application {

	public static final String PROPERTIES_FILE = "resources/config.properties";

	private Properties properties;
	private DataBase db;
	
	/**
	 * Constructor for initialise variables
	 */
	public Application() {

		{
			try {
				this.properties = new Properties();
				this.properties.load(new FileReader(PROPERTIES_FILE));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		db = new DataBase(this.properties);

		try {
			Login dialog = new Login(db, properties);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Launch the application
	 * This method is the first methods called when the program start
	 * @param args
	 */
	public static void main(String[] args) {

//		FIXME
		 new Application();

		UpdateThread ut = new UpdateThread("Update", 10);
		ut.start();

	}
	
}