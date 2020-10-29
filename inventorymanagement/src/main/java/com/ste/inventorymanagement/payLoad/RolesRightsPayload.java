package com.ste.inventorymanagement.payLoad;

import com.ste.inventorymanagement.model.BaseEntity;

public class RolesRightsPayload extends BaseEntity  {

	private static final long serialVersionUID = 1L;

	private Long roleId;
	
	private Long rightId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getRightId() {
		return rightId;
	}

	public void setRightId(Long rightId) {
		this.rightId = rightId;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}
