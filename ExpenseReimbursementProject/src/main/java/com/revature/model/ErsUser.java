
package com.revature.model;

/**
 * Purpose: 
 * Object Relational Mapping of ers_users table in database.
 * 
 * @author Shane Avery Sistoza
 *
 */
public class ErsUser {
	// States
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private int role;
		
	// No Args Constructor
	public ErsUser() { }

	// Overloaded Constructor
	public ErsUser(int id, String username, String password, String firstName, String lastName, String email, int role){
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
	}

	// Accessors and Mutators	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }

	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }

	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public int getRole() { return role; }
	public void setRole(int role) { this.role = role; }

	@Override
	public String toString() {
		return "ErsUsers [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", role=" + role + "]";
	} // toString()
}
