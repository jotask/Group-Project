package com.github.jotask.groupproject.connection.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.github.jotask.groupproject.connection.DataBase;

/**
 * Class for store the connection instance and the reference to the actual connection
 * Also have methods for close connections
 *
 * @author Jose Vives
 *
 * @version 1.0
 */
public abstract class Dao {
	
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
	protected Dao(DataBase db, Connection conn){
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
	 * 			The ResultSet for close
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
