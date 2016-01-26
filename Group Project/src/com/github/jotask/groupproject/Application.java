package com.github.jotask.groupproject;

import com.github.jotask.groupproject.gui.Login;

import javax.swing.*;
import java.io.*;
import java.util.Properties;

/**
 * Start class where all start
 * The properties file is loaded for know all the config parameters we need for
 * all program
 *
 * @author Jose Vives
 * @since 1.0 - Initial version of the file
 * @version 1.4 - Now just have the basics they need
 *
 */
public class Application {

	/** Path and the file for the properties */
	public static final String PROPERTIES_FILE = "resources/config.properties";

	/** Property variable */
	private Properties properties;
	
	/**
	 * Constructor for initialise variables
	 */
	public Application() {

		{
			// Init and load properties from the file
			try {
				this.properties = new Properties();
				this.properties.load(new FileInputStream(PROPERTIES_FILE));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// Start the dialog for the login
		try {
			Login dialog = new Login(properties);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Launch the application
	 * This method is the first methods called when the program start
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		new Application();

	}
	
}