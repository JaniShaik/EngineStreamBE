package com.ste.inventorymanagement.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Material")
public class Material {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column (nullable = false, name = "Id")
	private Long id;
	
	@Column(name = "MaterialNumber")
	private String materialNumber;
	
	@Column(name = "MaterialDescription")
	private String materialDescription;
	
	@Column(name = "LastPOUnitPrice")
	private BigDecimal lastPOUnitPrice;
	
	@Column(name = "Last1stYearIssueQuantity")
	private Long last1stYearIssueQuantity;
	
	@Column(name = "Last2ndYearIssueQuantity")
	private Long last2ndYearIssueQuantity;
	
	@Column(name = "Last3rdYearIssueQuantity")
	private Long last3rdYearIssueQuantity;
	
	@ManyToOne
	@JoinColumn(name="Engine", referencedColumnName="id")
	private Engine engine;
	
	@ManyToOne
	@JoinColumn(name = "Plant", referencedColumnName="id")
	private Plant plant;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMaterialNumber() {
		return materialNumber;
	}

	public void setMaterialNumber(String materialNumber) {
		this.materialNumber = materialNumber;
	}

	public String getMaterialDescription() {
		return materialDescription;
	}

	public void setMaterialDescription(String materialDescription) {
		this.materialDescription = materialDescription;
	}

	public BigDecimal getLastPOUnitPrice() {
		return lastPOUnitPrice;
	}

	public void setLastPOUnitPrice(BigDecimal lastPOUnitPrice) {
		this.lastPOUnitPrice = lastPOUnitPrice;
	}

	public Long getLast1stYearIssueQuantity() {
		return last1stYearIssueQuantity;
	}

	public void setLast1stYearIssueQuantity(Long last1stYearIssueQuantity) {
		this.last1stYearIssueQuantity = last1stYearIssueQuantity;
	}

	public Long getLast2ndYearIssueQuantity() {
		return last2ndYearIssueQuantity;
	}

	public void setLast2ndYearIssueQuantity(Long last2ndYearIssueQuantity) {
		this.last2ndYearIssueQuantity = last2ndYearIssueQuantity;
	}

	public Long getLast3rdYearIssueQuantity() {
		return last3rdYearIssueQuantity;
	}

	public void setLast3rdYearIssueQuantity(Long last3rdYearIssueQuantity) {
		this.last3rdYearIssueQuantity = last3rdYearIssueQuantity;
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}
}
