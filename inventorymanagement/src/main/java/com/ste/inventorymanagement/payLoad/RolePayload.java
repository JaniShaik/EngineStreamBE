package com.ste.inventorymanagement.payLoad;

import com.ste.inventorymanagement.model.BaseEntity;

public class RolePayload extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	public String roleName;

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
