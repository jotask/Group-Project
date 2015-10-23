package com.github.jotask.groupproject.database.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.github.jotask.groupproject.database.DataBase;

/**
 * Class for store the database instance and the reference to the actual connection
 * Also have methods for close connections
 * @author Jose Vives
 * @version 1.0
 * @since 23 / October  / 2015
 */
public abstract class DAO {
	
	/**
	 * DataBase reference
	 */
	protected DataBase db;
	
	/**
	 * The actual connection
	 */
	protected Connection conn;
	
	/**
	 * 
	 * @param db
	 * 			The actual DataBase instance
	 * @param conn
	 * 			The Connection we have established between the code and the server
	 */
	protected DAO(DataBase db, Connection conn){
		this.db = db;
		this.conn = conn;
	}
	
	/**
	 * Method for call the actual method for close a statement
	 * @param stm
	 * 			The statement for close
	 * @throws SQLException
	 */
	protected void close(Statement stm) throws SQLException{
		close(stm, null);
	}
	
	/**
	 * Main method for close a statement and close a ResultSet
	 * @param stm
	 * 			The statement for close
	 * @param rs
	 * 			The ResulSet for close
	 * @throws SQLException
	 */
	protected void close(Statement stm , ResultSet rs) throws SQLException{
		if(stm != null && !stm.isClosed()){
			stm.close();
		}
		if(rs != null && !rs.isClosed()){
			rs.close();
		}
	}

}
