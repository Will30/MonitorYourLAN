package TestUnitaire;

import static org.junit.Assert.*;

import org.junit.Test;

import Metier.ObjectIdentifier;

public class ObjectIdentifierTest {

	@Test
	public void testCheckValue() 
	{
		System.out.println("\n   testCheckValue()");
		
		ObjectIdentifier myOID = new ObjectIdentifier();
		myOID.setNumOID("1.3.6.1.2.1.25.2.2.0");    	// OID corresponds to Memory size value (to be multyplied by 4096 to get MegaByte value)
		myOID.checkValue("127.0.0.1","0");
		
		System.out.println(" Value returned : "+myOID.getValue());
	}
	
	
	@Test
	public void testCheckAllValue() 
	{
		System.out.println("\n   testCheckAllValue()");
		
		ObjectIdentifier myOID = new ObjectIdentifier();
		myOID.setNumOID("1.3.6.1.2.1.25.5.1.1.2"); 		//OID corresponds to total value of all processes running
		myOID.checkAllValue("127.0.0.1");
		
		System.out.println(" Value returned (total): "+myOID.getValue());
	}

}
