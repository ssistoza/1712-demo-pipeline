package com.revature.model;

/**
 * Purpose:
 * - Object Relaional Mapping of the ers_reimbursement_status table in database.
 * 
 * @author Shane Avery Sistoza
 *
 */
public class ErsReimbursementStatus {

	// states
	private int id;
	private String status;
	
	// no args constructor
	public ErsReimbursementStatus() {}
	
	// overloaded constructor
	public ErsReimbursementStatus(int id, String status) {
		this.id = id;
		this.status = status;
	}

	// accessors and mutators
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getStatus() { return status; }
	public void setStatus(String status) { this.status = status; }

	@Override
	public String toString() {
		return "ErsReimbursementStatus [id=" + id + ", status=" + status + "]";
	}

}
