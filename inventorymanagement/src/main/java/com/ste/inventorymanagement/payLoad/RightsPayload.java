package com.ste.inventorymanagement.payLoad;

import com.ste.inventorymanagement.model.BaseEntity;

public class RightsPayload extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	public String name;
	
	public String code;
	
	public String url;
	
	public String otherUrl;
	
	public Long parent;
	
	public Long type;
	
	public Long position;

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
	
	public void setId(Long id) {
		this.id = id;
	}

}
