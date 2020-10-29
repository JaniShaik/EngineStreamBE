package com.ste.inventorymanagement.services;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

import com.ste.inventorymanagement.model.Material;


@Service
/*
 * @EnableAutoConfiguration
 * 
 * @Configuration
 */
public class MailService {
	
	
	  public void sendmail(Material material) throws Exception {

		  String contant = this.setMailcontent(material);
		  
		  Properties props = new Properties();
		  props.put("mail.smtp.auth", "true");
		  props.put("mail.smtp.starttls.enable", "true");
		  props.put("mail.smtp.host", "smtp.gmail.com");
		  props.put("mail.smtp.port", "587");

		  Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			  protected PasswordAuthentication getPasswordAuthentication() {
				  return new PasswordAuthentication("leekendrav@gmail.com", "*****");
			  }
		  });
		  Message msg = new MimeMessage(session);
		  msg.setFrom(new InternetAddress("leekendrav@gmail.com", false));

		  msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("leekendrav@gmail.com"));
		  msg.setSubject("Material Details for "+material.getMaterialNumber());
		  msg.setContent(contant, "text/html");
		  msg.setSentDate(new Date());

		  MimeBodyPart messageBodyPart = new MimeBodyPart();
		  messageBodyPart.setContent(contant.toString(), "text/html");

		  Multipart multipart = new MimeMultipart();
		  multipart.addBodyPart(messageBodyPart);
		  MimeBodyPart attachPart = new MimeBodyPart();

		  attachPart.attachFile("C:\\Users\\USER\\Downloads\\materials (3).xlsx");
		  multipart.addBodyPart(attachPart);
		  msg.setContent(multipart);
		  Transport.send(msg);   
	  }
	  
	  public String setMailcontent(Material material) {
		  StringBuilder contant = new StringBuilder();
		  String newLine = "<br>";
		  
		  contant.append("<html><body> <p>");
		  contant.append("Dear xxx ").append(newLine).append(newLine).append(newLine);
		  contant.append("Below are the details of Material: ").append(newLine)
		  .append("Material Number: "+material.getMaterialNumber()).append(newLine)
		  .append("Material Serial Number: "+material.getId()).append(newLine)
		  .append("Engine Type: "+material.getEngine().getEngineType()).append(newLine)
		  .append("Material Description: "+material.getMaterialDescription()).append(newLine)
		  ;
		  contant.append("</p></body></html>");
		  
		  return contant.toString();
	  }

	
}
