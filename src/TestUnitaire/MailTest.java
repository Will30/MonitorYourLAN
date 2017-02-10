package TestUnitaire;

import static org.junit.Assert.*;

import org.junit.Test;

import Metier.Mail;

public class MailTest {

	// test OK if no Exception
	@Test
	public void testStart() 
	{
		Mail email = new Mail();
		
		email.setUsername("***@gmail.com");     // enter a sender address mail
		email.setPassword("****");				// enter a sender password
		email.setRecipient("****@gmail.com");
		email.setSubject("Mail from JUnit");
		email.setEmailBody("This is a test");
		
		boolean mailSent = email.start();
		
		if(mailSent == false)
		{
			fail("Mail transmission error");		
		}
	}

}
