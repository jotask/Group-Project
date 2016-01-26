package com.github.jotask.groupproject.database.dao;

import com.github.jotask.groupproject.database.DataBase;
import com.github.jotask.groupproject.model.Task;
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

        printAllMember();
		
		Statement stm = null;
		ResultSet rs;

		try{
			stm = conn.createStatement();
			String sql = "SELECT * FROM MEMBER WHERE SURNAME=\"" + username + "\"";
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
                    System.out.println("user find");
                    User user = converToUser(rs);
                    close(stm);
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
        System.out.println("not finded");
        return null;
	}

	private User converToUser(ResultSet rs) throws SQLException {
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
			// TODO not created handle
			e.printStackTrace();
			success = false;
		}finally {
			try {
				close(stm);
			} catch (SQLException e) {
				// TODO Nothing we can do
			}
		}
		return success;
	}

    public void printAllMember(){
        // TODO get all tasks from database
        ArrayList<User> users = new ArrayList<>();

        Statement stm = null;
        ResultSet rs = null;

        try {

            String sql = "SELECT * FROM MEMBER";
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);

            while(rs.next()){
                User task = converToUser(rs);
                users.add(task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                close(stm, rs);
            } catch (SQLException e) {
                // TODO nothing to do
                e.printStackTrace();
            }
        }

        for(User u: users){
            printUser(u);
        }

    }

    private void printUser(User u){
        int id = u.getId();
        String first = u.getFirstName();
        String last = u.getSurname();
        String mail = u.getMail();
        String p = u.getPassword();

        System.out.printf("ID: " + id + " F: " + first + " S: " + last + " M: " + mail + " P: " + p + "\n");
    }

}
