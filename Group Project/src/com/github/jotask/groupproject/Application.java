package com.github.jotask.groupproject;

import com.github.jotask.groupproject.gui.Login;

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
	
	/**
	 * Constructor for initialise variables
	 */
	public Application() {

		{
			// Load properties file
			try {
				this.properties = new Properties();
				this.properties.load(new FileReader(PROPERTIES_FILE));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

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
	 * @param args
	 */
	public static void main(String[] args) {
		 new Application();
	}
	
}