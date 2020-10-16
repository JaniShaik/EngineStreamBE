package com.ste.inventorymanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Engine")
public class Engine {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column (nullable = false, name = "Id")
	private Long id;
	
	@Column(name = "EngineType")
    private String engineType;
	
	@Column(name = "EngineTypeDescription")
	private String engineTypeDescription;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEngineType() {
		return engineType;
	}

	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}

	public String getEngineTypeDescription() {
		return engineTypeDescription;
	}

	public void setEngineTypeDescription(String engineTypeDescription) {
		this.engineTypeDescription = engineTypeDescription;
	}
}
