package TestUnitaire;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Metier.User;

public class UserTest {

	
	public 	int IDToDeleteAfterTest=0;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception 
	{
		
		
	}

	@Test
	public void testLogin() {
		//fail("Not yet implemented");
		User userTest = new User();
		byte ID=-1;
		
		userTest.setUsername("cyril");
		userTest.setPassword("123m");
		
		ID=userTest.login();
		assertTrue("ID positif",ID>0);
	}
	
	@Test
	public void testAddUser()
	{
		User user = new User();
		int stateAdding =0;
		
		user.setUsername("toto");
		user.setPassword("tata");
		user.setEmail("titi@gmail.com");
		user.setAdminAccess((byte)0);
		user.setMailReceived((byte)1);
		user.setAccountEnable((byte)0);
		
		user.setService("Finance");
		
		stateAdding = user.add();
		
		if(stateAdding != 0)
		{
			fail("No user added");			
		}
		else
		{		
	
	/*		DeleteData deleteData = new DeleteData();	
			// this url, with method equals to delete, will delete the last user created 
			String url="http://STA6101856:3001/users/";
					
			deleteData.Start(url); 
			System.out.println("Last user \"test\" deleted");*/
		}
	}
	
	@Test
	public void testGetAllUsers()
	{
		User tempUser = new User();
		ArrayList<User> listUsers = new ArrayList<User>();
		tempUser.getAllUsers(listUsers);
		
		assertTrue("Liste utilisateur initailisé correctement",listUsers.size()>0);
		
	}
	@Test
	public void sendActivationMail()
	{
		User user = new User();
		user.setUsername("cyril");
		user.setPassword("MyMonitor");
		user.setEmail("c.mathieu.30470@gmail.com");
		
		boolean mailSent = user.sendActivationMail();

		assertTrue("Mail sent successfully",mailSent == true);
		
		if(mailSent == false)
		{
			fail("Mail transmission error");		
		}
	}
	
	@Test
	public void updateAccount()
	{
		User user = new User();
		
		user.setID((byte)45);
		user.setUsername("toto");
		user.setPassword("titi");
		user.setEmail("titi@gmail.com");
		user.setAdminAccess((byte)0);
		user.setMailReceived((byte)1);
		user.setAccountEnable((byte)0);
		
		int state = user.updateAccount();
		
		assertTrue("account updated successfully",state == 0);
		
		if(state == 1)
		{
			fail("Account not updated");		
		}
	}
	
}

