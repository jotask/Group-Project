package com.github.jotask.groupproject.model;

public class User {
	
	/** The user ID */
	int id;
	/** The user username */
	String surname;
	/** The name from the user */
	String firstname;
	/** The mail from the user */
	String mail;
	/** The user password */
	String password;

	public int getId() {
		return id;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public User(int id, String surname, String firstname, String mail){
		this(id, surname, firstname, mail, "");
	}

	/**
	 * Constructor for the User, get all the parameters from one user
	 * @param id
	 * 			The user ID from the database
	 * @param username
	 * 			The user username
	 * @param name
	 * 			The Name from the user
	 * @param mail
	 * 			The mail from the user
	 * @param password
	 * 			The password from the user
	 */

	public User(int id, String surname, String firstname, String mail, String password) {
		this.id = id;
		this.surname = surname;
		this.firstname = firstname;
		this.mail = mail;
		this.password = password;
	}

}
