package com.revature.model;

/**
 * Purpose: 
 * - Object Relational Mapping of the ers_reimbursement_type table in database.
 * 
 * @author Shane Avery Sistoza
 *
 */
public class ErsReimbursementType {
	// states
	private int id;
	private String type;
	
	// no args constructor
	public ErsReimbursementType() { }

	// overloaded constructor
	public ErsReimbursementType (int id, String type) {
		this.id = id;
		this.type = type;
	}

	// accessors and mutators
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getType() { return type; }
	public void setType(String type) { this.type = type; }

	@Override
	public String toString() {
		return "ErsReimbursementType [id=" + id + ", type=" + type + "]";
	} // toString()
}
