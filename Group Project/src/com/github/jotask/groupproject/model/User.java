package com.github.jotask.groupproject.model;

public class User {
	
	/** The user ID */
	int id;
	/** The user username */
	String username;
	/** The name from the user */
	String name;
	/** The mail from the user */
	String mail;
	
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
	 */
	public User(int id, String username, String name, String mail) {
		this.id = id;
		this.username = username;
		this.name = name;
		this.mail = mail;
	}

}
