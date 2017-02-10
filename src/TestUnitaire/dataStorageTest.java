package TestUnitaire;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.xml.bind.DatatypeConverter;

import org.json.JSONException;
import org.junit.After;
import org.junit.Test;

import Metier.Bug;
import Metier.DataStorage;
import Metier.Equipment;
import Metier.StrategicPoint;

public class dataStorageTest {

	public int IDCreatedTest;
	
	@After
	public void tearDown() throws Exception 
	{		
		DataStorage TempDataStorage = new DataStorage ();
		
		TempDataStorage.setID(this.IDCreatedTest);
		
		TempDataStorage.delete();
	}
	
	// test OK if no JSONException
	@Test
	public void testgetAll()
	{
		System.out.println("\n   testgetAll()");
		DataStorage TempDataStorage = new DataStorage ();
		
		ArrayList<StrategicPoint> listDataStorage = new ArrayList<StrategicPoint> ();
		TempDataStorage.getAll(listDataStorage);	
	}
	
	@Test
	public void testAdd()
	{
		System.out.println("\n   testAdd()");
		
		DataStorage TempDataStorage = new DataStorage ();	
		
		TempDataStorage.setName("Test unitaire ");
		TempDataStorage.setDescription("desription");
		TempDataStorage.setLocation("test location");
		TempDataStorage.setIcon(null);
		TempDataStorage.setIPAddress("192.168.2.99");	
		TempDataStorage.setUNC("D:/MonitorYourLAN/dataStorage_test");
		TempDataStorage.setLastDate("2017-01-25 12:23:09");
		TempDataStorage.getService().setID((byte) 1);
				
		TempDataStorage.getLed().setID((byte) 1);
		
		int confirmation = TempDataStorage.add();
	
		if(confirmation == 1 )
		{
			fail("No DataStorage created");
		}	
		else
		{			
			// for tearDown()
			this.IDCreatedTest = TempDataStorage.getID();			
		}
	}
	
	// test OK if no IOException
	@Test
	public void testconvertImageToBase64()
	{
		System.out.println("\n   testconvertImageToBase64()");
		
		String encodedImage = null;		
		DataStorage TempDataStorage = new DataStorage ();	
		
		encodedImage = TempDataStorage.convertImageToBase64("");
		
		DatatypeConverter.parseBase64Binary(encodedImage);		
		
	}
	
	@Test
	public void testUpdate()
	{
		System.out.println("\n   testUpdate()");		
	
		DataStorage TempDataStorage = new DataStorage ();	
		
		TempDataStorage.setID(IDCreatedTest); 			// For test, we need equipment's ID.
		TempDataStorage.setName("Test unitaire 2");  	// Here's the change
		TempDataStorage.setDescription("desription");
		TempDataStorage.setIcon(null);
		TempDataStorage.setIPAddress("192.168.2.99");		
		TempDataStorage.getService().setID((byte) 1);
		
		TempDataStorage.getLed().setID((byte) 1);
		TempDataStorage.setUNC("D:/MonitorYourLAN/dataStorage_test");
		TempDataStorage.setLastDate("2017-01-25 18:01:09");		
		
		int confirmation = TempDataStorage.update();			
		
		if(confirmation == 1 )
		{
			fail("No DataStorage updated");
		}
		
	}
	
	// test OK if no IOException (check only IP address format)
	@Test
	public void testDetectSP()
	{
		System.out.println("\n   testDetectSP()");	
		
		// For test, enter a real IP address present into our domain		
		DataStorage TempDataStorage = new DataStorage ();	
			
		TempDataStorage.setIPAddress("192.168.2.99");	
		
		TempDataStorage.detectSP();			
	}
	
	@Test
	public void testDiagnose()
	{
		System.out.println("\n   testDiagnose()");			
				
		DataStorage TempDataStorage = new DataStorage ();			
		Bug tempBug = new Bug();
		ArrayList<Bug> listBug = new ArrayList<Bug>();
		
		tempBug.getAll(listBug);
		
		// for test, share a folder in your computer, create file named "archive1.txt" and enter both following values
		TempDataStorage.setIPAddress("127.0.0.1"); 			// Ip address where is located your folder
		TempDataStorage.setUNC("dataStorage_test");			// Folder name
		TempDataStorage.diagnose(listBug);		
		
	}

}
