package TestUnitaire;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.xml.bind.DatatypeConverter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import Metier.Bug;
import Metier.Equipment;
import Metier.ObjectIdentifier;
import Metier.StrategicPoint;

public class EquipmentTest
{
	
	public int IDToDeleteAfterTest = 0;
	public int IDCreatedTest;
	
	@Before
	public void setUp() throws Exception 
	{
		
	}

	@After
	public void tearDown() throws Exception 
	{		
		Equipment TempEquipment = new Equipment ();	
		
		TempEquipment.setID(this.IDCreatedTest);
		
		TempEquipment.delete();
	}

	@Test
	public void testgetAll()
	{
		System.out.println("\n   testgetAll()");
		Equipment TempEquipment = new Equipment ();
		
		ArrayList<StrategicPoint> listEquipment = new ArrayList<StrategicPoint> ();
		
		TempEquipment.getAll(listEquipment);
	
		if(listEquipment.size()==0)
		{
			fail("No StrategicPoint founded");
		}	
	}
	
	@Test
	public void testgetListTypeAndModel()
	{
		System.out.println("\n   testgetListTypeAndModel()");
		
		Equipment TempEquipment = new Equipment ();		
		ArrayList<Equipment> listEquipment = new ArrayList<Equipment> ();
		
		TempEquipment.getListTypeAndModel(listEquipment);
	
		if(listEquipment.size()==0)
		{
			fail("No StrategicPoint founded");
		}	
		
	}
	
	@Test
	public void testAdd()
	{
		System.out.println("\n   testAdd()");
		
		Equipment TempEquipment = new Equipment ();		
		
		TempEquipment.setName("Test unitaire ");
		TempEquipment.setDescription("desription");
		TempEquipment.setIcon(null);
		TempEquipment.setIPAddress("192.168.2.99");		
		TempEquipment.getService().setID((byte) 1);
		TempEquipment.setType("Desktop");	
		TempEquipment.setModel("Dell Z230");		
		TempEquipment.getLed().setID((byte) 1);
		
		int confirmation = TempEquipment.add();
	
		if(confirmation == 1 )
		{
			fail("No StrategicPoint created");
		}	
		else
		{
			
			System.out.println("testAdd()   --- super.ID --> "+TempEquipment.getID());
			this.IDCreatedTest = TempEquipment.getID();
			
		}
	}


	@Test
	public void testconvertImageToBase64()
	{
		System.out.println("\n   testconvertImageToBase64()");
		
		String encodedImage = null;		
		Equipment TempEquipment = new Equipment ();	
		
		encodedImage = TempEquipment.convertImageToBase64("Desktop");
		try
		{
			DatatypeConverter.parseBase64Binary(encodedImage);
		}
		catch(IllegalArgumentException e)
		{
			fail("string parameter does not conform to lexical value --> Datatypes for xsd:base64Binary");
		}
		
	}
	
	
	@Test
	public void testUpdate()
	{
		System.out.println("\n   testUpdate()");		
	
		Equipment TempEquipment = new Equipment ();	
		
		TempEquipment.setID(IDCreatedTest); // For test, we need equipment's ID.
		TempEquipment.setName("Test unitaire 2");  // Here's the change
		TempEquipment.setDescription("desription");
		TempEquipment.setIcon(null);
		TempEquipment.setIPAddress("192.168.2.99");		
		TempEquipment.getService().setID((byte) 1);
		TempEquipment.setType("Desktop");	
		TempEquipment.setModel("Dell Z230");
		TempEquipment.getLed().setID((byte) 1);
		
		int confirmation = TempEquipment.update();	
		
		
		if(confirmation == 1 )
		{
			fail("No StrategicPoint updated");
		}	
	}
	
	@Test
	public void testCreateBugIntoDatabase()
	{
		System.out.println("\n   createBugIntoDatabase()");		
		
		Equipment TempEquipment = new Equipment ();	
		TempEquipment.setListBug();
		TempEquipment.getListBug().add(new Bug());
		TempEquipment.getListBug().get(0).setDate("2017-01-17");
		TempEquipment.getListBug().get(0).setID(12);
		TempEquipment.setID(1);
		
		int confirmation = TempEquipment.createBugIntoDatabase();		

		if(confirmation == 1 )
		{
			fail("No bug created");
		}
	
	}
	
	
	@Test
	public void testDetectSP()
	{
		System.out.println("\n   testDetectSP()");	
		
		// For test, enter a real hostname present into our domain otherwise test failed (UnknownHostException)		
		Equipment TempEquipment = new Equipment ();	
		TempEquipment.setName("STA6101856");
		TempEquipment.detectSP();		
		
		System.out.println("ID du led --> "+TempEquipment.getLed().getID());			
	}
	
	/*@Test
	public void testDiagnose()
	{
		System.out.println("\n   testDiagnose()");			
				
		Equipment TempEquipment = new Equipment ();	
		
		TempEquipment.setIPAddress("127.0.0.1");		
		TempEquipment.setListOID();
		TempEquipment.getListOID().add(new ObjectIdentifier());
		TempEquipment.getListOID().get(0).setNumOID("1.3.6.1.2.1.25.2.3.1.6.1"); // ONLY for Dell Z230 --> The amount of the storage represented by this entry 		
		TempEquipment.setModel("Dell Z230");
		
		TempEquipment.diagnose();
		
		assertTrue("Diagnose done successfully",TempEquipment.getHDDLoad()>0);
		
	}**/


}
