package com.ste.inventorymanagement.model;

import org.springframework.lang.Nullable;

public class SmtpMail {

	@Nullable
	private String to;
	private String messageSubject;
	private String messageBody;
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getMessageSubject() {
		return messageSubject;
	}
	public void setMessageSubject(String messageSubject) {
		this.messageSubject = messageSubject;
	}
	public String getMessageBody() {
		return messageBody;
	}
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
	
	
}
