package com.github.jotask.groupproject.database.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import com.github.jotask.groupproject.database.DataBase;

/**
 * Class for get and update information from the table user from our connection between the
 * DataBase and our connection we established
 * 
 * @author Jose Vives
 * @version 0.1
 *
 */
public class UserDAO extends DAO {

	/**
	 * Constructor for the class
	 * @param db
	 * 			The DataBase instance
	 * @param conn
	 * 			The Connection instance
	 */
	public UserDAO(DataBase db, Connection conn) {
		super(db, conn);
	}
	
	public boolean login(String username, char[] password) {
		
		Statement stm = null;
		ResultSet rs = null;
		
		try{
			stm = conn.createStatement();
			String sql = "select password from users where username=" + username;
			rs = stm.executeQuery(sql);
			
			while(rs.next()){
				char[] passwd = rs.getString("password").toCharArray();
				if(Arrays.equals(passwd, password)){
					return true;
				}
			}
		}catch(SQLException sqlex){
			// TODO SQLException
		}finally{
			try {
				close(stm);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}
		}
		return false;
	}

}
