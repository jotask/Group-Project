package com.github.jotask.groupproject.database.dao;

import com.github.jotask.groupproject.database.DataBase;
import com.github.jotask.groupproject.model.User;
import com.github.jotask.groupproject.util.MD5;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class for get and update information from the table user from our connection between the
 * DataBase and our connection we established
 * 
 * @author Jose Vives
 * @version 0.1
 *
 */
public class MemberDAO extends DAO {

	/**
	 * Constructor for the class
	 * @param db
	 * 			The DataBase instance
	 * @param conn
	 * 			The Connection instance
	 */
	public MemberDAO(DataBase db, Connection conn) {
		super(db, conn);
	}
	
	/**
	 * 
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
			String sql = "SELECT * FROM member WHERE surname=\"" + username + "\"";
			rs = stm.executeQuery(sql);

			// TODO implement password login
			// TODO implement a a encryption for the password
			password = MD5.encrypt(password.toString()).toCharArray();
			
			while(rs.next()){
//				char[] passwd = rs.getString("password").toCharArray();
//				if(Arrays.equals(passwd, password)){
//					return true;
//				}
				if(rs.getString("surname").equals(username)){
					User user = converToUser(rs);
					return user;
				}
			}
		}catch(SQLException sqlex){
			// TODO SQLException
			sqlex.printStackTrace();
		}finally{
			try {
				close(stm);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	private User converToUser(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String username = rs.getString("username");
		String firstname = rs.getString("firstname");
		String mail = rs.getString("mail");
		return new User(id, username, firstname, mail);
	}

}
