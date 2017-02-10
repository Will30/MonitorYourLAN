package TestUnitaire;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.xml.bind.DatatypeConverter;

import org.junit.After;
import org.junit.Test;

import Metier.DataStorage;
import Metier.Network;
import Metier.StrategicPoint;

public class NetworkTest 
{

	public int IDCreatedTest;
	
	@After
	public void tearDown() throws Exception 
	{		
		Network TempNetwork = new Network ();
		
		TempNetwork.setID(this.IDCreatedTest);
		
		TempNetwork.delete();
	}
	
	// test OK if no JSONException
	@Test
	public void testgetAll()
	{
		System.out.println("\n   testgetAll()");
		Network TempNetwork = new Network ();
		
		ArrayList<StrategicPoint> listNetwork = new ArrayList<StrategicPoint> ();
		TempNetwork.getAll(listNetwork);	
	}
	
	@Test
	public void testAdd()
	{
		System.out.println("\n   testAdd()");
		
		Network TempNetwork = new Network ();
		
		TempNetwork.setName("Test unitaire ");
		TempNetwork.setDescription("desription");
		TempNetwork.setLocation("test location");
		TempNetwork.setIcon(null);
		TempNetwork.setIPAddress("192.168.2.99");	
		TempNetwork.setIPAddress2("192.168.2.199");
		TempNetwork.getService().setID((byte) 1);
				
		TempNetwork.getLed().setID((byte) 1);
		
		int confirmation = TempNetwork.add();
	
		if(confirmation == 1 )
		{
			fail("No network created");
		}	
		else
		{			
			// for tearDown()
			this.IDCreatedTest = TempNetwork.getID();			
		}
	}
	
	@Test
	public void testUpdate()
	{
		System.out.println("\n   testUpdate()");		
	
		Network TempNetwork = new Network ();
		
		TempNetwork.setID(IDCreatedTest); 			// For test, we need equipment's ID.
		TempNetwork.setName("Test unitaire 2");  	// Here's the change
		TempNetwork.setDescription("desription");
		TempNetwork.setIcon(null);
		TempNetwork.setIPAddress("192.168.2.99");		
		TempNetwork.getService().setID((byte) 1);
		
		TempNetwork.getLed().setID((byte) 1);
		
		TempNetwork.setIPAddress2("192.168.2.199");	
		
		int confirmation = TempNetwork.update();			
		
		if(confirmation == 1 )
		{
			fail("No network updated");
		}		
	}
	
	// test OK if no IOException (check only IP address format)
	@Test
	public void testDetectSP()
	{
		System.out.println("\n   testDetectSP()");	
		
		// For test, enter real both IP address present into our domain		
		Network TempNetwork = new Network ();	
			
		TempNetwork.setIPAddress("192.168.2.99");	
		TempNetwork.setIPAddress2("192.168.3.99");	
		TempNetwork.detectSP();			
	}
	
	// test OK if no IOException
	@Test
	public void testconvertImageToBase64()
	{
		System.out.println("\n   testconvertImageToBase64()");
		
		String encodedImage = null;		
		Network TempNetwork = new Network ();
		
		encodedImage = TempNetwork.convertImageToBase64("");		
		DatatypeConverter.parseBase64Binary(encodedImage);	
		
	}
	

}
