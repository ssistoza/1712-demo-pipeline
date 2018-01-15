package com.revature.dto;

import com.revature.model.ErsReimbursement;

public class ErsReimbursementDto extends ErsReimbursement {
	private String typeName;
	private String statusName;
	private String authorName;
	private String resolverName;
	
	public ErsReimbursementDto() { }
	
	public ErsReimbursementDto(ErsReimbursement curr, String typeName, String statusName, String authorName, String resolverName) { 
		super(curr.getId(), curr.getAmount(), curr.getDescription(), curr.getReceipt(), 
			  curr.getSubmitted(), curr.getResolved(), curr.getAuthor(), 
			  curr.getResolver(), curr.getType(), curr.getStatus());
		
		this.typeName = typeName;
		this.statusName = statusName;
		this.authorName = authorName;
		this.resolverName = resolverName;
	}

	public String getTypeName() { return typeName; }

	public void setTypeName(String typeName) { this.typeName = typeName; }

	public String getStatusName() { return statusName; }

	public void setStatusName(String statusName) { this.statusName = statusName; }

	public String getAuthorName() { return authorName; }

	public void setAuthorName(String authorName) { this.authorName = authorName; }

	public String getResolverName() { return resolverName; }

	public void setResolverName(String resolverName) { this.resolverName = resolverName; }
	
	
}
