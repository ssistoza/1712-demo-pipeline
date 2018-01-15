package com.revature.model;

/**
 * Purpose:
 * - Object relational mapping of ers_reimbursement table in database.
 * @author Shane Avery Sistoza
 *
 */
public class ErsReimbursement {
	// states
	private int id;
	private double amount;
	private String description;
	private byte[] receipt;
	private String submitted;
	private String resolved;
	private int author;
	private int resolver;
	private int type;
	private int status;

	// no args constructor
	public ErsReimbursement() { }

	// overloaded constructor
	public ErsReimbursement(int id, double amount, String description, byte[] receipt, String submitted, String resolved,
			int author, int resolver, int type, int status) {
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.receipt = receipt;
		this.submitted = submitted;
		this.resolved = resolved;
		this.author = author;
		this.resolver = resolver;
		this.type = type;
		this.status = status;
	}

	// accessors and mutators
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public double getAmount() { return amount; }
	public void setAmount(double amount) { this.amount = amount; }

	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }

	public byte[] getReceipt() { return receipt; }
	public void setReceipt(byte[] receipt) { this.receipt = receipt; }

	public String getSubmitted() { return submitted; }
	public void setSubmitted(String submitted) { this.submitted = submitted; }

	public String getResolved() { return resolved; }
	public void setResolved(String resolved) { this.resolved = resolved; }

	public int getAuthor() { return author; }
	public void setAuthor(int author) { this.author = author; }

	public int getResolver() { return resolver; }
	public void setResolver(int resolver) { this.resolver = resolver; }

	public int getType() { return type; }
	public void setType(int type) { this.type = type; }

	public int getStatus() { return status; }
	public void setStatus(int status) { this.status = status; }

	@Override
	public String toString() {
		return "ErsReimbursement [id=" + id + ", amount=" + amount + ", description=" + description + ", receipt="
				+ receipt + ", submitted=" + submitted + ", resolved=" + resolved + ", author=" + author + ", resolver="
				+ resolver + ", type=" + type + ", status=" + status + "]";
	} // toString()
}
