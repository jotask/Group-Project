package com.github.jotask.groupproject;

import com.github.jotask.groupproject.database.DataBase;
import com.github.jotask.groupproject.gui.Login;

import javax.swing.*;

/**
 * Start class where all start
 * @author Jose Vives
 *
 */
public class Application {
	
	private DataBase db;
	
	/**
	 * Constructor for initialise variables
	 */
	public Application() {
		
		db = new DataBase();

		try {
			Login dialog = new Login(db);
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