package com.github.jotask.groupproject.connection.dao;

import com.github.jotask.groupproject.connection.DataBase;
import com.github.jotask.groupproject.model.User;
import com.github.jotask.groupproject.util.MD5;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class for get and update information from the table user from our connection between the
 * DataBase and our connection we established
 * 
 * @author Jose Vives
 *
 * @version 1.0
 *
 */
public class MemberDao extends Dao {

	/**
	 * Constructor for the class
	 * @param db
	 * 			The DataBase instance
	 * @param conn
	 * 			The Connection instance
	 */
	public MemberDao(DataBase db, Connection conn) {
		super(db, conn);
	}
	
	/**
	 * @param username
	 * 				The username 
	 * @param password
	 * 				The
	 * @return
	 * 			If the username and the password is correct
	 */
	public User login(String username, char[] password) {

		Statement stm = null;
		ResultSet rs;

		try{
			stm = conn.createStatement();
			String sql = "SELECT * FROM MEMBER WHERE FORENAME=\"" + username + "\"";
			rs = stm.executeQuery(sql);

			// Implement password login
			// Implement a a encryption for the password
			password = MD5.encrypt(password.toString()).toCharArray();
			
			while(rs.next()){
//				char[] passwd = rs.getString("password").toCharArray();
//				if(Arrays.equals(passwd, password)){
//					return true;
//				}
				if(rs.getString("forename").equals(username)){
                    User user = convertToUser(rs);
                    close(stm);
					return user;
				}
			}
		}catch(SQLException sqlex){
			// SQLException
			sqlex.printStackTrace();
		}finally{
			try {
				close(stm);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
        return null;
	}

	private User convertToUser(ResultSet rs) throws SQLException {
		int id = rs.getInt("MEMBER_ID");
		String surname = rs.getString("SURNAME");
		String forename = rs.getString("FORENAME");
		String mail = rs.getString("EMAIL_ADDRESS");
		return new User(id, surname, forename, mail);
	}

	public boolean register(String surname, String forename, String mail) {

		boolean success;

		String sql = "INSERT INTO `MEMBER` (`SURNAME`, `FORENAME`, `EMAIL_ADDRESS`) VALUES ('" + surname + "', '" + forename + "', '" + mail + "');";

		Statement stm = null;

		try {
			stm = conn.createStatement();
			stm.executeUpdate(sql);
			success = true;
		} catch (SQLException e) {
			// not created handle
			e.printStackTrace();
			success = false;
		}finally {
			try {
				close(stm);
			} catch (SQLException e) {
                // Nothing to do
			}
		}
		return success;
	}

	/**
	 * Used for unit testing to grab a list of registered users
	 *
	 * @return
	 *      A list of all the users in the database
	 */

	public ArrayList<String> getAllUser(){

		ArrayList<String> Users = new ArrayList<>();

		Statement stm = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT * FROM MEMBER";
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);

			while(rs.next()){
				Users.add(getName(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				close(stm,rs);
			} catch (SQLException e) {
				// Nothing to do
			}
		}

		return Users;

	}

	private String getName(ResultSet rs) throws SQLException{
		return rs.getString("FORENAME");
	}


}
