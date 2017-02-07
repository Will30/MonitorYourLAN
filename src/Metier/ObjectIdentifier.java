package Metier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TreeUtils;
import org.snmp4j.util.TreeEvent;


/**
 * @author Cyril or Will30 (GitHub) or Will15 (GitLab)
 * @version 1.00
 * Class for OID. This is an identifier mechanism standardized by the International Telecommunications Union (ITU) and ISO/IEC for naming 
 * any object. An OID corresponds to a node in the "OID tree" or hierarchy, which is formally defined using the ITU's ASN.1 standard, X.690
 * 
 * Class uses SNMP protocol and SNMP4J.jar library
 */

public class ObjectIdentifier 
{	
	private int ID;						// ID defines in the database
	private String numOID;				// for example 1.3.6.1.2.1.25.5.1.1.2
	private String name;				// Name associated at the numOID (define in the database)
	private String description;			// description associated at the name (define in the database)
	private String value = null;		// this value will be updated in checkValue() function
	
	
	public ObjectIdentifier()
	{
		
	}
	
	
	/**
	 * Get only one  value associated at an OID (function snmpGet)
	 * @param IPAddress representing the statregic point IP address to be detected
	 * @param updateOID optional value for switch and precise the port number (by default 0)
	 * @ version 1.00
	 */
	public void checkValue(String IPaddress,String updateOID)
	{
		
		String  ipAddress  = IPaddress;
		String  port    = "161";
		
		String  sysContactOid  = this.getNumOID()+updateOID;  // ends with 0 for scalar object
		String  sysContactValue  = "MonitorYourLAN";
		int    snmpVersion  = SnmpConstants.version1;
	
	    String  community  = "public";
	    ResponseEvent response = null;


	    // Create TransportMapping and Listen
	    TransportMapping transport = null;
		try
		{
			transport = new DefaultUdpTransportMapping();
			transport.listen();
		} catch (IOException e) 
		{
			System.out.println("ObjectIdentifier.checkValue() --  No SNMP agent active");			// here indicate no SNMP agent active
			e.printStackTrace();
		}
	    

	    // Create Target Address object
	    CommunityTarget comtarget = new CommunityTarget();
	    comtarget.setCommunity(new OctetString(community));
	    comtarget.setVersion(snmpVersion);
	    comtarget.setAddress(new UdpAddress(ipAddress + "/" + port));
	    comtarget.setRetries(2);
	    comtarget.setTimeout(2000);
	   
	    // Create the PDU object
	    PDU pdu = new PDU();
	   
	    // Setting the Oid and Value for sysContact variable
	    OID oid = new OID(sysContactOid);
	    Variable var = new OctetString(sysContactValue);
	    VariableBinding varBind = new VariableBinding(oid);
	    pdu.add(varBind);
	   
	    pdu.setType(PDU.GET);
	    pdu.setRequestID(new Integer32(1));

	    // Create Snmp object for sending data to Agent
	    Snmp snmp = new Snmp(transport);
	    
		try
		{
			response = snmp.send(pdu, comtarget);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

	    // Process Agent Response
	    if (response != null)
	    {
		    PDU responsePDU = response.getResponse();
		
		
		    if (responsePDU != null)
		    {
		    	int errorStatus = responsePDU.getErrorStatus();
		    	int errorIndex = responsePDU.getErrorIndex();
		    	String errorStatusText = responsePDU.getErrorStatusText();
		   
			    if (errorStatus == PDU.noError)
			    {
			    	int index = responsePDU.getVariableBindings().toString().indexOf("=")+2;
			    	String tempOIDValue = responsePDU.getVariableBindings().toString().substring(index);
			    	this.setValue(tempOIDValue.substring(0,tempOIDValue.length()-1));

			    }
				else
				{

					System.out.println("ObjectIdentifier.checkValue() - Unable to get value for this OID");
				}
		    }
		    else
		    {
		    	System.out.println("ObjectIdentifier.checkValue() - Error: Response PDU is null -- it seems IPaddress has been changed for this StrategicPoint");
		    }
	    }
	    else
	    {
	    	
	      System.out.println("ObjectIdentifier.checkValue() - Error: Agent Timeout... ");
	    }
	    
	    try 
	    {
			snmp.close();
		} 
	    catch (IOException e) 
	    {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Get severals value associated at an OID (function snmpWalk)
	 * @param IPAddress representing the statregic point IP address to be detected
	 * @ version 1.00
	 */
	
	public void checkAllValue(String IPaddress)
	{
		ArrayList<String> results = new ArrayList<String>();
		String port = "161";
		int snmpVersion = SnmpConstants.version1;
		String  community  = "public";
		Snmp snmp = null;
		int totalValue = 0;

	    TransportMapping transport = null;
		try 
		{
			transport = new DefaultUdpTransportMapping();
			snmp = new Snmp(transport);
		    transport.listen();
		} 
		catch (IOException e1) 
		{
		
			System.out.println("ObjectIdentifier.checkAllValue() --  No SNMP agent active");
			e1.printStackTrace();
		}
	    
	  
	    // setting up target
	    CommunityTarget comtarget = new CommunityTarget();
	    
	    comtarget.setCommunity(new OctetString(community));
	    comtarget.setVersion(snmpVersion);
	    comtarget.setAddress(new UdpAddress(IPaddress + "/" + port));
	    comtarget.setRetries(2);
	    comtarget.setTimeout(2000);
		  
	    OID oid = null;
	    try
	    {
	    	oid = new OID(this.getNumOID());
	    }
	    catch(RuntimeException ex)
	    {
	    	System.out.println("ObjectIdentifier.checkAllValue() --  OID is not specified correctly.");
	    	System.exit(1);
	    }
		  
	    TreeUtils treeUtils = new TreeUtils(snmp, new DefaultPDUFactory());      
	    List<TreeEvent> events = treeUtils.getSubtree(comtarget, oid);
	    if(events == null || events.size() == 0)
	    {
	      System.out.println("ObjectIdentifier.checkAllValue() -- No SNMP agent active");       
	    
	    }
		    
		// Get snmpwalk result.
		for (TreeEvent event : events) 
		{
			if(event != null){
				if (event.isError()) 
		        {
		            System.err.println("oid [" + oid + "] " + event.getErrorMessage());
		        }
		            
		        VariableBinding[] varBindings = event.getVariableBindings();
		        if(varBindings == null || varBindings.length == 0)
		        {
		        	// here put message indicating OID error  
		        }
		        else
		        {
		        	for (VariableBinding varBinding : varBindings) 
			        {
			        	results.add(varBinding.getVariable().toString());
			        }
		        }
		        
		      }
		    }
		    
		    //print results
		    for(String tempValue: results)
		    {		    	
		    	totalValue = totalValue + Integer.parseInt(tempValue);		    	
		    }
		    
		    this.setValue(String.valueOf(totalValue));
		    try 
		    {
				snmp.close();
			} 
		    catch (IOException e) 
		    {
	
				e.printStackTrace();
			} 	
	}
	
/////////////************************    Getters and Setters *************************
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNumOID() {
		return numOID;
	}

	public void setNumOID(String numOID) {
		this.numOID = numOID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
