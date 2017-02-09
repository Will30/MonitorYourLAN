package Metier;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author Cyril or Will30 (GitHub) or Will15 (GitLab)
 * @version 1.00
 * Class for mail using SMTP protocol and javax.mail-1.4.5.jar library
 */
public class Mail 
{
	private String sender = "mailName@xxx.yyy"; // example --> john.smith@gmail.com
	private String recipient;
	private String username = "mailName@xxx.yyy"; // example --> john.smith@gmail.com
	private String password = "yourpassword";
	private String smtpServer = "smtp.gmail.com"; // if sender is a gmail address, alternatively use personal smtp's server
	private String emailBody;
	private String subject;
	
	protected boolean mailSent = false;
	
	
	public Mail()
	{
		
	}
	
	/**
	 * Sent an email using SMTP protocol
	 * @return mailSent boolean true if mail has been sent successfully
	 */
	public boolean start()
	{
		
		System.out.println("*********************************  Recipient "+this.recipient);
		
		Properties props = new Properties();
		
		//Using TLS
	//	props.put("mail.smtp.auth", "true");
	//	props.put("mail.smtp.starttls.enable", "true");
	//	props.put("mail.smtp.host", smtpServer);
	//	props.put("mail.smtp.port", "587"); 
		
		// using SSL
		props.put("mail.smtp.host", this.smtpServer);
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
	
	  
	  Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator()
	  {
	              @Override
	              protected PasswordAuthentication getPasswordAuthentication()
	              {
	                  return new PasswordAuthentication(username, password);
	              }
	  }
	  );
	  
	  try 
	  {
	      Message message = new MimeMessage(session);
	      message.setFrom(new InternetAddress(username));
	      message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(this.recipient));
	      message.setSubject(this.subject);
	      message.setText(this.emailBody);
	      Transport.send(message);
	
	      System.out.println("Mail.sendMail() Mail sent");
	      mailSent = true ;
	  
	  } 
	  catch (Exception e) 
	  {
		  System.out.println("Mail.sendMail() Unable to sent mail ");
		  e.printStackTrace();
		  mailSent = false ;
	  } 
	  
	  return(mailSent);		
	}
	

	/////////////************************    Getters and Setters *************************
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSmtpServer() {
		return smtpServer;
	}
	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}

	public String getEmailBody() {
		return emailBody;
	}

	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}
