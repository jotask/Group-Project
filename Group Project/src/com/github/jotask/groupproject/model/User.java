package com.github.jotask.groupproject.model;

/**
 * User model class for create users
 *
 * @author Jose Vives
 * @version 1.0
 */
public class User {
	
	/** The user ID */
	int id;

	/** The user username */
	String surname;

	/** The name from the user */
	String firstName;

	/** The mail from the user */
	String mail;

	/** The user password */
	String password;

	public User(int id, String surname, String firstname, String mail){
		this(id, surname, firstname, mail, "");
	}

	/**
	 * Constructor for the User, get all the parameters from one user
	 *
	 * @param id
	 * 			The user ID from the database
	 * @param surname
	 * 			The user username
	 * @param firstName
	 * 			The Name from the user
	 * @param mail
	 * 			The mail from the user
	 * @param password
	 * 			The password from the user
	 */

	public User(int id, String surname, String firstName, String mail, String password) {
		this.id = id;
		this.surname = surname;
		this.firstName = firstName;
		this.mail = mail;
		this.password = password;
	}

	/**
	 * Get the ID for this user
	 *
	 * @return
	 * 		the ID from this user
     */
	public int getId() {
		return id;
	}

	/**
	 * Get the Surname for this user
	 *
	 * @return
	 * 		The user surname for this users
     */
	public String getSurname() {
		return surname;
	}

	/**
	 * Set Surname for this user
	 *
	 * @param surname
	 * 			The new surname for this user
     */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Get the first name for this user
	 *
	 * @return
	 * 		The first name from this user
     */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set the first name for this user
	 *
	 * @param firstName
	 * 			The new first name for this user
     */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get the mail for this user
	 *
	 * @return
	 * 		The the mail from this user
     */
	public String getMail() {
		return mail;
	}

	/**
	 * Set the mail for this user
	 *
	 * @param mail
	 * 			The new mail for this user
     */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * Set the id for this user
	 *
	 * @param id
	 * 			Allocated id for the user
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get the password for this user
	 *
	 * @return
	 * 		return the password for this user
     */
	public String getPassword() {
		return password;
	}

	/**
	 * Change the password for this user
	 *
	 * @param password
	 * 			The new password for this user
     */
	public void setPassword(String password) {
		this.password = password;
	}

    @Override
    public String toString() {
        return "id: " + id + " f:" + firstName + " s: " + surname + " M: " + mail;
    }
}
