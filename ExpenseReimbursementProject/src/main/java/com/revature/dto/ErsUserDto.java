package com.revature.dto;

import com.revature.model.ErsUser;

public class ErsUserDto extends ErsUser {
	private String roleName;
	
	public ErsUserDto() {
		super();
	}
	
	public ErsUserDto(ErsUser curr, String roleName) {
		super(curr.getId(), curr.getUsername(), 
			  null, curr.getFirstName(), curr.getLastName(), curr.getEmail(), curr.getRole());
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "ErsUserDto [roleName=" + roleName + "]";
	}
	
	
}
