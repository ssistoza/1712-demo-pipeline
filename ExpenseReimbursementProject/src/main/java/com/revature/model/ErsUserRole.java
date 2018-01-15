package com.revature.model;

/**
 * Purpose:
 * - Object relational Mapping of ers_roles in the database.
 * 
 * @author Shane Avery Sistoza
 *
 */
public class ErsUserRole {
	// States
	private int id;
	private String role;
	
	// no args-constructor
	public ErsUserRole() { }	

	// overloaded constructor
	public ErsUserRole(int id, String role) {
		this.id = id;
		this.role = role;
	}

	// accessors and mutators
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getRole() { return role; }
	public void setRole(String role) { this.role = role; }

	@Override
	public String toString() {
		return "ErsRole [id=" + id + ", role=" + role + "]";
	} // toString()

}
