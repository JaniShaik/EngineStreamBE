package com.ste.inventorymanagement.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Rights")
@JsonIgnoreProperties(allowGetters = true)
public class Rights extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	@Column(name = "Name")
	private String name;
	
	@Column(name = "Code")
	private String code;
	
	@Column(name = "Url")
	private String url;
	
	@Column(name = "OtherUrl")
	private String otherUrl;
	
	@Column(name = "Parent")
	private Long parent;
	
	@Column(name = "Type")
	private Long type;
	
	@Column(name = "Position")
	private Long position;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOtherUrl() {
		return otherUrl;
	}

	public void setOtherUrl(String otherUrl) {
		this.otherUrl = otherUrl;
	}

	public Long getParent() {
		return parent;
	}

	public void setParent(Long parent) {
		this.parent = parent;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Long getPosition() {
		return position;
	}

	public void setPosition(Long position) {
		this.position = position;
	}
}
