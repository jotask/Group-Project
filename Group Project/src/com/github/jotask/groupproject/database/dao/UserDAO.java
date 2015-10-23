package com.github.jotask.groupproject.database.dao;

import java.sql.Connection;

import com.github.jotask.groupproject.database.DataBase;

/**
 * Class for get and update information from the table user from our connection between the
 * DataBase and our connection we established
 * 
 * @author Jose Vives
 *
 */
public class UserDAO extends DAO {

	/**
	 * Constructor fo the class
	 * @param db
	 * 			The DataBase instance
	 * @param conn
	 * 			The Connection instance
	 */
	public UserDAO(DataBase db, Connection conn) {
		super(db, conn);
	}

}
