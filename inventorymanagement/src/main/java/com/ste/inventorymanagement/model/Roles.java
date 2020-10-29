package com.ste.inventorymanagement.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Roles")
@JsonIgnoreProperties(allowGetters = true)
public class Roles extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	@NotBlank
	@Column(name = "Name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

