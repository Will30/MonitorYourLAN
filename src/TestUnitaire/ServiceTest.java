package TestUnitaire;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Metier.Network;
import Metier.Service;
import Metier.StrategicPoint;

public class ServiceTest 
{

	// test OK if no JSONException
	@Test
	public void testgetAll()
	{
		System.out.println("\n   testgetAll()");
		Service TempService = new Service ();
		
		ArrayList<Service> listService = new ArrayList<Service> ();
		TempService.getAll(listService);	
	}
	
	
	@Test
	public void testgetID()
	{
		System.out.println("\n   testgetID()");
		Service TempService = new Service ();
		
		TempService.setID((byte) 0);
		TempService.setName("IT service");
	
		TempService.setID(TempService.getID());
		
		if(TempService.getID() == 0)
		{
			fail("ID service is still equals to 0");
		}
	}

}
