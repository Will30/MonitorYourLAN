package TestUnitaire;

import org.junit.After;
import org.junit.Before;

public class StrategicPointTest {
	

	public 	int IDToDeleteAfterTest=0;
	
	@Before
	public void setUp() throws Exception 
	{
		
	}

	@After
	public void tearDown() throws Exception 
	{
	/*	DeleteData deleteData = new DeleteData();	
		String url="http://STA6101856:3001/SPs/"+IDToDeleteAfterTest;
				
		deleteData.Start(url); 
		System.out.println("StrategicPoint "+IDToDeleteAfterTest+" deleted"); */
	}

/*	@Test
	public void testgetMoreInfoforSP()
	{
		
		StrategicPoint SP = new StrategicPoint();		
		ArrayList<StrategicPoint> listSPs = new ArrayList<StrategicPoint> ();	
		
		SP.getAll(listSPs);
	
		if(listSPs.size()==0)
		{
			fail("No StrategicPoint founded");
		}	
	}
	
	/*	@Test
	public void testAddSP() 
	{
		int StateConfirmation=0;
		
		StrategicPoint SP = new StrategicPoint();
		//SP.setService("Finance");
		SP.setLed();
		SP.getLed().setID((byte) 1); 
		
		
		SP.setName("PC_Test2");
		SP.setDescription("JUnitTest1");
		SP.setLocation("LocationJUnit");
		SP.setIcon(null);
		SP.setIPAddress("192.168.3.0");
	//	SP.setService("Finance");
		SP.setType("laptop");
	//	SP.setIDservice(1);
		
		StateConfirmation = SP.add();
		
		if(StateConfirmation != 0)
		{
			fail("No StrategicPoint added");			
		}		
	}
	
	@Test
	public void testDetectSP() 
	{
		// for project, this is BelkinRouter IPAddress
		String IPAddress="192.168.2.254";
		StrategicPoint SP = new StrategicPoint();
		
		ArrayList<StrategicPoint> listSPs = new ArrayList<StrategicPoint> ();
		
		listSPs.add(new StrategicPoint());
		listSPs.get(0).setLed();
		listSPs.get(0).setIPAddress(IPAddress);	
		
		//listSPs.detectSP();
		
		assertTrue("SP detected",listSPs.get(0).getLed().getColor() == "green");
		
	}
	
	@Test
	public void testUpdateSP()
	{
		StrategicPoint SP = new StrategicPoint();
		int StateConfirmation=0;
		ArrayList<StrategicPoint> listSPs = new ArrayList<StrategicPoint>();
		
		listSPs.add(new StrategicPoint());
		// Change variable with an existing name into database
		
		listSPs.get(0).setName("swichTest");
		listSPs.get(0).setDescription("Switch pour MonitorYourLAN - sur bureau Cyril");
		listSPs.get(0).setLocation("undefined");
		listSPs.get(0).setIcon(null);
		listSPs.get(0).setIPAddress("10.111.61.240");
	//	listSPs.get(0).setIDservice(2);
		listSPs.get(0).setLed();
		listSPs.get(0).getLed().setID((byte)1);
		
		StateConfirmation = SP.update(listSPs);
		if(StateConfirmation != 0)
		{
			fail("No StrategicPoint updated");			
		}	
		
	}
*/
}
