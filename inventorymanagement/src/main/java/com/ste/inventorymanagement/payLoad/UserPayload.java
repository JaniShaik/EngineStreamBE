package com.ste.inventorymanagement.payLoad;

import com.ste.inventorymanagement.model.BaseEntity;

/**
 * IssuePayload payload class
 * 
 * @author hemalatha.sekar
 *
 */
public class UserPayload extends BaseEntity {

	private static final long serialVersionUID = 1L;
	 
	private Long roleId;
	 
	private String password;
	 
	private String name;
	
	private String emailAddress;
	
	private String userid;
	
	private String phoneno;	 

	public Long getRoleId() {
		return roleId;
	}


	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getPhoneno() {
		return phoneno;
	}


	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

}
