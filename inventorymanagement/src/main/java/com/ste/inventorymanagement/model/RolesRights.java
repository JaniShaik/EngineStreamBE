package com.ste.inventorymanagement.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "RolesRights")
@JsonIgnoreProperties(allowGetters = true)
public class RolesRights extends BaseEntity{

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "Role", referencedColumnName="id")
    private Roles role;
	
	@ManyToOne
	@JoinColumn(name = "Rights", referencedColumnName="id")
    private Rights right;

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public Rights getRight() {
		return right;
	}

	public void setRight(Rights right) {
		this.right = right;
	}
}
