package TestUnitaire;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Metier.Bug;

public class BugTest {

	@Test
	public void testgetAll() 
	{
		Bug tempBug = new Bug();
		ArrayList<Bug> listBug = new ArrayList<Bug>();
		
		tempBug.getAll(listBug);
		
		if(listBug.size()==0)
		{
			fail("No bug found");
		}	
		assertTrue("15 bugs found", listBug.size()==15);
		
	}
	
	@Test
	public void testgetBugDuringPeriod() 
	{
		Bug tempBug = new Bug();
		ArrayList<Bug> listBug = new ArrayList<Bug>();
		
		int IDStrategicPoint = 1;
		String date1="2017-01-15";
		String date2="2017-01-29";
		
		tempBug.getBugDuringPeriod(listBug,IDStrategicPoint,date1,date2);
		
		if(listBug.size()==0)
		{
			fail("No StrategicPoint founded");
		}		
	}

}
