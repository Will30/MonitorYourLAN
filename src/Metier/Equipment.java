package Metier;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Donnees.GetData;
import Donnees.UpdateData;


/**
 * @author Cyril or Will30 (GitHub) or Will15 (GitLab)
 * @version 1.00
 * Class for Equipment. It can be a computer, a printer, a switch,etc..
 */
public class Equipment extends StrategicPoint
{

	private String type;							// for this project, only these value: switch, desktop, printer or router
	private String model;							// Model for each type. For example "Dell Z230"
	private ArrayList<ObjectIdentifier> listOID;    // OID
	
	private float RAMLoad = 0;						// Random Access Memory load
	private float HDDLoad = 0;						// Hard Disk Drive load
	private float CPULoad = 0;						// Central Processing Unit load
	private float TonerRemaining = 100;				// for printer
	
	private float bandwidthLoad =0;					// for switch or/and router


	/**
	 * @category constructor
	 * When created, it is associated at a LED and service
	 * @since 1.00
	 */
	public Equipment() 
	{
		super.led = new Led();
		super.service = new Service(); 
		super.listBug = new ArrayList<Bug>();
	}
	
	@Override
	public void getAll(ArrayList<StrategicPoint> listEquipment)
	{	
		String url ="http://"+SERVER_NAME+":"+PORT_NUMBER+"/equipments";

		GetData getSPs = new GetData();
		
		JSONArray json = getSPs.Start(url);	
	
		
		for(int i=0;i<json.length();i++)
		{
			try 
			{	
				JSONObject jsonObject = json.getJSONObject(i);
		
				if(jsonObject.length()>0)
				{
					listEquipment.add(new Equipment());
					
					listEquipment.get(i).setID( jsonObject.getInt("id"));
					listEquipment.get(i).setName(jsonObject.getString("name"));
					listEquipment.get(i).setDescription(jsonObject.getString("description"));
					listEquipment.get(i).setLocation(jsonObject.getString("location"));
					listEquipment.get(i).setIcon(jsonObject.getString("icon"));
					listEquipment.get(i).setIPAddress(jsonObject.getString("IPaddress"));	

					listEquipment.get(i).getService().setName(jsonObject.getString("service"));
					listEquipment.get(i).getService().setID(listEquipment.get(i).getService().getID());

					listEquipment.get(i).getLed().setID((byte) jsonObject.getInt("id_LED")); 
					listEquipment.get(i).getLed().setColor(jsonObject.getString("color"));
					listEquipment.get(i).getLed().setUNC(jsonObject.getString("UNC"));						
									
					((Equipment) listEquipment.get(i)).setType(jsonObject.getString("type"));
					((Equipment) listEquipment.get(i)).setModel(jsonObject.getString("model"));
					
					
					//listEquipment.get(i).setListBug();
					
					url ="http://"+SERVER_NAME+":"+PORT_NUMBER+"/equipments/"+listEquipment.get(i).getID();
					
					((Equipment) listEquipment.get(i)).setListOID(); 
					
					JSONArray json2 = getSPs.Start(url);	
	
					for(int j=0;j<json2.length();j++)
					{
						try 
						{	
							JSONObject jsonObject2 = json2.getJSONObject(j);
					
							if(jsonObject2.length()>0)
							{

								((Equipment) listEquipment.get(i)).getListOID().add(new ObjectIdentifier());								
								
								((Equipment) listEquipment.get(i)).getListOID().get(j).setID(jsonObject2.getInt("id"));
								((Equipment) listEquipment.get(i)).getListOID().get(j).setNumOID(jsonObject2.getString("OID"));
								((Equipment) listEquipment.get(i)).getListOID().get(j).setName(jsonObject2.getString("name"));
								((Equipment) listEquipment.get(i)).getListOID().get(j).setDescription(jsonObject2.getString("description"));	
							
							}
						} 
						
						catch (JSONException e) 
						{				
							e.printStackTrace();
						}	
				
					}
				}
			} 
			
			catch (JSONException e) 
			{				
				e.printStackTrace();
			}	
		}
		
		
	}
	
	/**
	 * Function which get a list all type and model saved in the database
	 * @version 1.00
	 * 
	 */	
	public void getListTypeAndModel(ArrayList<Equipment> listTypeAndModel)
	{
		String url ="http://"+SERVER_NAME+":"+PORT_NUMBER+"/equipments/list";

		GetData getSPs = new GetData();		
		JSONArray json = getSPs.Start(url);	

		for(int i=0;i<json.length();i++)
		{
			try 
			{	
				JSONObject jsonObject = json.getJSONObject(i);
		
				if(jsonObject.length()>0)
				{
					listTypeAndModel.add(i,new Equipment());
					
					listTypeAndModel.get(i).setID( jsonObject.getInt("id"));
					listTypeAndModel.get(i).setType(jsonObject.getString("type"));
					listTypeAndModel.get(i).setModel(jsonObject.getString("model"));

				}
			} 
			
			catch (JSONException e) 
			{				
				e.printStackTrace();
			}	
		}	
	}
	
	
	@Override
	public int add()
	{
		UpdateData postData = new UpdateData();
		JSONObject json = new JSONObject(); 

		String method ="POST";
		String jsonReturned = null;
		String Server_Rest_Address = "http://"+SERVER_NAME+":"+PORT_NUMBER+"/equipments";
		JSONObject tempJson = null;
		int stateConnexion=-1;		
		
		try 
		{
			json.put("name",this.getName());
			json.put("description",this.getDescription());
			json.put("location",this.getLocation());
			json.put("icon",this.getIcon());
			json.put("IPaddress",this.getIPAddress());
			json.put("id_Service",this.getService().getID()); 
			json.put("type",this.getType());
			json.put("model",this.getModel());
	
			json.put("id_LED",this.getLed().getID()); 				
			
		} 
		catch (JSONException e) 
		{			
			e.printStackTrace();
		}
		
		if(json.length()>0)
		{			
			System.out.println("Ready to send JSON :"+String.valueOf(json));
			jsonReturned = postData.Start(json,Server_Rest_Address,method);  	
			try 
			{
				tempJson = new JSONObject(jsonReturned);
			} 
			catch (JSONException e) 
			{
				System.out.println("Equipment.Add() unable to convert string to JSON");
				e.printStackTrace();
			}
		}
				
		try 
		{
			stateConnexion = tempJson.getInt("status");
			super.setID(tempJson.getInt("id"));
			System.out.println("Equipment.Add()     Last ID --> "+tempJson.getInt("id"));
			System.out.println("Equipment.Add() 	status -->"+stateConnexion);
			
		} 
		catch (JSONException e1) 
		{
			System.out.println("Equipment.Add() unable to convert json to int ");
			e1.printStackTrace();
		}			
		
		return (stateConnexion);
	}
	
	
	/**
	 * Function which delete a strategic point
	 * @version 1.00
	 */
	public void delete()
	{
		super.delete();
	}
	
	/**
	 * Converts image (byte[]) to String base64
	 * @param this parameter for equipment or network, to assign an image (UNC) at this strategic point. Then, this image will be converting 
	 * into string base64 
	 * @return encodedImage Image encoded to string base64
	 */
	public String convertImageToBase64(String type)
	{
		File  file= null;
		String encodedImage = null;
		BufferedImage originalImage;
		
		switch(type)
		{
		case "Desktop": file= new File("src/images/icones/desktop.png");
						break;
		case "Laptop":	file= new File("src/images/icones/laptop.png");
						break;			
		case "Printer": file= new File("src/images/icones/printer.png");
						break;
		case "Switch":	file= new File("src/images/icones/switch.png");
						break;		

		 default:		file= new File("src/images/icones/no-image.png");
			 			break;					
		}
		
		try 
		{
			originalImage = ImageIO.read(file);
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			ImageIO.write(originalImage, "png", baos );
			encodedImage = DatatypeConverter.printBase64Binary(baos.toByteArray());
			
			
		} catch (IOException e) 
		{
			
			e.printStackTrace();
		}
	
		return(encodedImage);
	}
	
	/**
	 * After the detection, it will save new Strategic points's LED into database
	 * @param listSPs this is a list of all Strategic Points from database
	 * @return stateConnexion it's an integer with only two values (as a boolean) to know if strategic point has been updated successfully
	 * @ version 1.00
	 */
	public int update()
	{
		int state = super.update();
		return state;	
	}
	
	/**
	 * After the detection and if bug is detected, it will create this bug in the database
	 * @return stateConnexion it's an integer with only two values (as a boolean) to know if bug has been created successfully
	 * @ version 1.00
	 */
	public int createBugIntoDatabase()
	{
		String method ="POST";
		UpdateData postData = new UpdateData();
		JSONObject json = new JSONObject(); 		
		String jsonReturned = null;
		String Server_Rest_Address = "";
		JSONObject tempJson = null;
		int stateConnexion=-1;		

		Server_Rest_Address = "";
		try 
		{
			json.put("dateBUG",this.getListBug().get(0).getDate());
			json.put("idSP",this.getID());
			json.put("idBug",this.getListBug().get(0).getID());

		} 
		catch (JSONException e) 
		{			
			e.printStackTrace();
		}
		
		if(json.length()>0)
		{	
			Server_Rest_Address = "http://"+SERVER_NAME+":"+PORT_NUMBER+"/SPs" + "/" + this.getID();
			
			jsonReturned = postData.Start(json,Server_Rest_Address,method);  	
			try 
			{
				tempJson = new JSONObject(jsonReturned);
			} 
			catch (JSONException e) 
			{
				System.out.println("SP.update() unable to convert string to JSON");
				e.printStackTrace();
			}
		}
		try 
		{
			stateConnexion = tempJson.getInt("status");
		} 
		catch (JSONException e1) 
		{
			System.out.println("SP.update() unable to convert json to int");
			e1.printStackTrace();
		}					
	
		return (stateConnexion);
		
	}
	
	/**
	 * Start command ping and detect if Strategic point is available
	 * @version 1.03 Modify for network
	 */
	@Override
	public void detectSP()
	{
		InetAddress address = null;		

		try 
		{						
			// For our project, only desktop have got a hostname in our domains. Others equipments are represented only by their IP addresses, so it's why there is this condition
			if(this.getName().equals("swichTest")  || this.getName().equals("BelkinWifi") || this.getName().equals("Imprimante CDI"))
			{				
				address = InetAddress.getByName(this.getIPAddress());	
			}
			else
			{					
				address = InetAddress.getByName(this.getName());		
				
			
			}			
			
		} 
		catch (UnknownHostException e) 
		{
			System.out.println("detectSP() -- Unknown host, please check manually with PING command");
			e.printStackTrace();
		}

		try 
		{			
			if (address.isReachable(5000)) 
			{					
	
				this.getLed().setID((byte)4); // id => 4 equals to green color
				this.setIPAddress(address.getHostAddress());    // Force an IPAddress update because into domain area, equipment's IPaddress often change 
	
			} 
			else 
			{
				System.out.println(this.getName() + ": Unable to detect the StrategicPoint - Unable to communicate with ");
				
				this.getLed().setID((byte)1);		// id => 1 equals to black color
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Start a hard detection for equipment. Thought SNMP protocol, a request is sent to agent and return values (as RAM, CPU, Hard disk 
	 * capacity, bandwidth for switch,etc...)
	 * @ version 1.00
	 */
	public void diagnose()
	{
		int i=0;
		long tempifInOctetValue =0;
		long tempifOutOctetValue =0;
		float bandWidth =0;
		int timeSleep = 2000;
		
		for(i=0;i<this.getListOID().size();i++)
		{
			
			if(this.getModel().equals("HP 1910-48") == true)
			{	
				
				if(i == 0)
				{
					this.getListOID().get(i).checkValue(this.getIPAddress(),".47");                          // for port 47
					tempifInOctetValue = Long.parseLong(this.getListOID().get(i).getValue());
					this.getListOID().get(i+1).checkValue(this.getIPAddress(),".47");
					tempifOutOctetValue = Long.parseLong(this.getListOID().get(i+1).getValue());
					try 
					{
						Thread.sleep(timeSleep);
					} catch (InterruptedException e) 
					{
						
						e.printStackTrace();
					}
				}
				if(i == 1)
				{
					this.getListOID().get(i-1).checkValue(this.getIPAddress(),".47");
					this.getListOID().get(i).checkValue(this.getIPAddress(),".47");
				}
				
				if(i == 2)
				{
					this.getListOID().get(i).checkValue(this.getIPAddress(),".47");
				}
				continue;
			}
			
			if(this.getModel().equals("Dell Z230") == true)
			{
				
				if(this.getListOID().get(i).getNumOID().equals("1.3.6.1.2.1.25.3.3.1.2") || this.getListOID().get(i).getNumOID().equals("1.3.6.1.2.1.25.5.1.1.2"))
				{
					
					// Multi-Processor's OID, in our case there are 8 processors to check, by machine			
					this.getListOID().get(i).checkAllValue(this.getIPAddress());
					continue;					
				}
				
			}
		
			this.getListOID().get(i).checkValue(this.getIPAddress(),"");
			
			
		}
		
		System.out.println("  ********   SP Result  "+this.getName()+" *********");
		if(this.getModel().equals("HP 1910-48") == true)
		{
			
			bandWidth = (float) ((((Long.parseLong(this.getListOID().get(0).getValue()))-tempifInOctetValue)+((Long.parseLong(this.getListOID().get(1).getValue()))-tempifOutOctetValue))/(timeSleep/1000));   /// 2 values are necessary to defines the bandwidth
			System.out.println("delta T1 et T2 :"+bandWidth);
			bandWidth = (bandWidth / Float.parseFloat(this.getListOID().get(2).getValue())) * 100; // calculate definitively bandwidth percentage
			System.out.println("Utilisation de la bande passante sur 2 seconde : "+bandWidth+"%");

			this.bandwidthLoad = bandWidth;
		}
		
		if(this.getModel().equals("Dell Z230") == true && this.getListOID().get(0).getValue().equals("0") == false)
		{
		
			float tempValue1=0;	
			float tempValue2=0;	
			float tempValue3=0;	
			
			if(this.getListOID().get(4).getValue().equals("0") == false)
			{
				System.out.println("Total charge des 8 multi-processor (a diviser par 8):"+this.getListOID().get(0).getValue());
				tempValue1 = (float) (Integer.parseInt(this.getListOID().get(0).getValue()))/8;
			}
			else
			{
				System.out.println("Total charge des 8 multi-processor (a diviser par 8):"+this.getListOID().get(0).getValue());
				tempValue1 = 0;
			}			
		
			tempValue2 = (float) (Integer.parseInt(this.getListOID().get(2).getValue()))/(Integer.parseInt(this.getListOID().get(1).getValue())) * 100;  
			tempValue3 = (float) (Long.parseLong(this.getListOID().get(5).getValue()))/(Long.parseLong(this.getListOID().get(4).getValue())) * 100 ;   
				
			System.out.println("CPU load: "+tempValue1+"%");
			System.out.println("RAM load: "+tempValue2+"%");
			System.out.println("HDD used percentage : "+tempValue3+"%");
			
			this.CPULoad = tempValue1;
			this.RAMLoad = tempValue2;
			this.HDDLoad = tempValue3;		
		}
		
		if(this.getModel().equals("Lexmark E460dn") == true)
		{
			System.out.println("Capacity "+this.getListOID().get(1).getValue());
			System.out.println("Remaining "+this.getListOID().get(0).getValue());
			this.TonerRemaining = (float) (Integer.parseInt(this.getListOID().get(1).getValue())) /  (Integer.parseInt(this.getListOID().get(0).getValue())) * 100;
			System.out.println("Toner remaining: "+this.TonerRemaining+"%");
		}
	}
	
	/**
	 * Checks if value founded are more important than threshold value
	 * @param listBugIntoDatabase all bug list from database
	 * @ version 1.00
	 */
	public void checkAlert(ArrayList<Bug> listBugIntoDatabase)
	{	
		
	    boolean BugActive = false;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.FRANCE);
		Date dateOfDay = new Date();		
		
		int indexBug = 0;
		System.out.println("************************************** CHECK ALERT");
		System.out.println("Name :"+this.getName());
		
		for(int i=0;i<listBugIntoDatabase.size();i++)
		{
			
			
			switch(listBugIntoDatabase.get(i).getID())
			{
			case 1: if(this.RAMLoad >= (float)listBugIntoDatabase.get(i).getThresholdValue())
					{		
						BugActive = true;
						if(this.getListBug().isEmpty() == false)
						{
							
							if(this.getListBug().get(indexBug).getDetail() != listBugIntoDatabase.get(i).getDetail())
							{
								
								this.getLed().setID(listBugIntoDatabase.get(i).getIDcolor());						
								this.getListBug().set(indexBug, listBugIntoDatabase.get(i));	
								this.getListBug().get(indexBug).setDate(dateFormat.format(dateOfDay));								
							}
							else
							{
								this.getLed().setID(listBugIntoDatabase.get(i).getIDcolor());
							}
						}	
						else
						{
							this.getLed().setID(listBugIntoDatabase.get(i).getIDcolor());	
							this.getListBug().add(listBugIntoDatabase.get(i));	
							this.getListBug().get(indexBug).setDate(dateFormat.format(dateOfDay));								
						}
						
					}
					break;
					
			case 2: if(this.RAMLoad >= (float)listBugIntoDatabase.get(i).getThresholdValue())
					{		
						
						if(this.getListBug().get(indexBug).getDetail() != listBugIntoDatabase.get(i).getDetail())
						{
							this.getLed().setID(listBugIntoDatabase.get(i).getIDcolor());						
							this.getListBug().set(indexBug, listBugIntoDatabase.get(i));	
							this.getListBug().get(indexBug).setDate(dateFormat.format(dateOfDay));							
						}						
					}
					break;
					
			case 3: if(this.HDDLoad >= (float)listBugIntoDatabase.get(i).getThresholdValue())
					{
						if(BugActive == true)
						{
							indexBug++;
						}
						else
						{							
							BugActive = true;
						}
						
						if(this.getListBug().isEmpty() == false)
						{
							
							if(this.getListBug().get(this.getListBug().size()-1).getDetail() != listBugIntoDatabase.get(i).getDetail())
							{
								
								this.getLed().setID(listBugIntoDatabase.get(i).getIDcolor());	
								this.getListBug().set(indexBug,listBugIntoDatabase.get(i));									
								this.getListBug().get(indexBug).setDate(dateFormat.format(dateOfDay));												
							}
							else
							{
								this.getLed().setID(listBugIntoDatabase.get(i).getIDcolor());
							}
						}
						else
						{							
							this.getLed().setID(listBugIntoDatabase.get(i).getIDcolor());	
							this.getListBug().add(listBugIntoDatabase.get(i));	
							this.getListBug().get(indexBug).setDate(dateFormat.format(dateOfDay));							
						}
					}
					break;
					
			case 4: if(this.HDDLoad >= (float)listBugIntoDatabase.get(i).getThresholdValue())
					{					
						if(this.getListBug().get(indexBug).getDetail() != listBugIntoDatabase.get(i).getDetail())
						{
							this.getLed().setID(listBugIntoDatabase.get(i).getIDcolor());						
							this.getListBug().set(indexBug, listBugIntoDatabase.get(i));	
							this.getListBug().get(indexBug).setDate(dateFormat.format(dateOfDay));						
						}													
					}
					break;
					
			case 5: if(this.CPULoad >= (float)listBugIntoDatabase.get(i).getThresholdValue())
					{
						if(BugActive == true)
						{
							indexBug++;
						}
						else
						{							
							BugActive = true;
						}
						
						if(this.getListBug().isEmpty() == false)
						{
							if(this.getListBug().get(this.getListBug().size()-1).getDetail() != listBugIntoDatabase.get(i).getDetail())
							{
								this.getLed().setID(listBugIntoDatabase.get(i).getIDcolor());	
								this.getListBug().set(indexBug,listBugIntoDatabase.get(i));								
								this.getListBug().get(indexBug).setDate(dateFormat.format(dateOfDay));
							}
							else
							{
								this.getLed().setID(listBugIntoDatabase.get(i).getIDcolor());
							}
						}	
						else
						{
							this.getLed().setID(listBugIntoDatabase.get(i).getIDcolor());	
							this.getListBug().add(listBugIntoDatabase.get(i));	
							this.getListBug().get(indexBug).setDate(dateFormat.format(dateOfDay));							
						}
					}
					break;
					
			case 6: if(this.CPULoad >= (float)listBugIntoDatabase.get(i).getThresholdValue())
					{							
						
						if(this.getListBug().get(indexBug).getDetail() != listBugIntoDatabase.get(i).getDetail())
						{
							this.getLed().setID(listBugIntoDatabase.get(i).getIDcolor());						
							this.getListBug().set(indexBug, listBugIntoDatabase.get(i));
							this.getListBug().get(indexBug).setDate(dateFormat.format(dateOfDay));								
						}
									
					}
					break;
					
					// only for Equipment's model equals switch
			case 7: if(this.bandwidthLoad >= (float)listBugIntoDatabase.get(i).getThresholdValue())
					{
						BugActive = true;
						if(this.getListBug().isEmpty() == false)
						{
							if(this.getListBug().get(this.getListBug().size()-1).getDetail() != listBugIntoDatabase.get(i).getDetail())
							{
								this.getLed().setID(listBugIntoDatabase.get(i).getIDcolor());	
								this.getListBug().set(indexBug,listBugIntoDatabase.get(i));								
								this.getListBug().get(indexBug).setDate(dateFormat.format(dateOfDay));
							}
							else
							{
								this.getLed().setID(listBugIntoDatabase.get(i).getIDcolor());
							}
						}
						else
						{
							this.getLed().setID(listBugIntoDatabase.get(i).getIDcolor());	
							this.getListBug().add(listBugIntoDatabase.get(i));	
							this.getListBug().get(indexBug).setDate(dateFormat.format(dateOfDay));
						}
					}
					break;
					
			case 8: if(this.bandwidthLoad >= (float)listBugIntoDatabase.get(i).getThresholdValue())
					{
						
							if(this.getListBug().get(indexBug).getDetail() != listBugIntoDatabase.get(i).getDetail())
							{
								this.getLed().setID(listBugIntoDatabase.get(i).getIDcolor());							
								this.getListBug().set(indexBug, listBugIntoDatabase.get(i));
								this.getListBug().get(indexBug).setDate(dateFormat.format(dateOfDay));
								
							}
											
					}
					break;
					
			case 13: if(this.TonerRemaining <= (float)listBugIntoDatabase.get(i).getThresholdValue())
						{		
							BugActive = true;
							if(this.getListBug().isEmpty() == false)
							{
								
								if(this.getListBug().get(indexBug).getDetail() != listBugIntoDatabase.get(i).getDetail())
								{
									
									this.getLed().setID(listBugIntoDatabase.get(i).getIDcolor());						
									this.getListBug().set(indexBug, listBugIntoDatabase.get(i));	
									this.getListBug().get(indexBug).setDate(dateFormat.format(dateOfDay));								
								}
								else
								{
									this.getLed().setID(listBugIntoDatabase.get(i).getIDcolor());
								}
							}	
							else
							{
								this.getLed().setID(listBugIntoDatabase.get(i).getIDcolor());	
								this.getListBug().add(listBugIntoDatabase.get(i));	
								this.getListBug().get(indexBug).setDate(dateFormat.format(dateOfDay));								
							}
							
						}
			break;
			
			case 14: if(this.TonerRemaining <= (float)listBugIntoDatabase.get(i).getThresholdValue())
						{		
							
							if(this.getListBug().get(indexBug).getDetail() != listBugIntoDatabase.get(i).getDetail())
							{
								this.getLed().setID(listBugIntoDatabase.get(i).getIDcolor());						
								this.getListBug().set(indexBug, listBugIntoDatabase.get(i));	
								this.getListBug().get(indexBug).setDate(dateFormat.format(dateOfDay));							
							}						
						}
			break;
			case 15: if(this.TonerRemaining <= (float)listBugIntoDatabase.get(i).getThresholdValue())
						{		
							
							if(this.getListBug().get(indexBug).getDetail() != listBugIntoDatabase.get(i).getDetail())
							{
								this.getLed().setID(listBugIntoDatabase.get(i).getIDcolor());						
								this.getListBug().set(indexBug, listBugIntoDatabase.get(i));	
								this.getListBug().get(indexBug).setDate(dateFormat.format(dateOfDay));							
							}						
						}
					break;
					
			default: 	
				
					break;			
			}			

		}
		
		if(this.getListBug().isEmpty() == false)
		{
			for(int j =0;j<this.getListBug().size();j++)
			{
				
				System.out.println("StartegicPoint name---> "+this.getName());
				System.out.println("Problem detected ! ---> "+this.getListBug().get(j).getDetail());
				System.out.println("List bug size -->" + this.getListBug().size());
				System.out.println("********************************************************************");
			}
		}
		else
		{
			if(BugActive == false)
			{
				this.getLed().setID((byte) 4);
				if(this.getListBug().isEmpty() == false)
				{
					this.getListBug().removeAll(this.getListBug());
				}
			}
		}
	}
	
	
	/////////////************************    Getters and Setters *************************
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public ArrayList<ObjectIdentifier> getListOID() {
		return listOID;
	}
	public void setListOID() {
		this.listOID = new ArrayList<ObjectIdentifier>();	
	}
	public float getRAMLoad() {
		return RAMLoad;
	}
	public void setRAMLoad(float rAMLoad) {
		RAMLoad = rAMLoad;
	}
	public float getHDDLoad() {
		return HDDLoad;
	}
	public void setHDDLoad(float hDDLoad) {
		HDDLoad = hDDLoad;
	}
	public float getCPULoad() {
		return CPULoad;
	}
	public void setCPULoad(float cPULoad) {
		CPULoad = cPULoad;
	}
	public float getBandwidthLoad() {
		return bandwidthLoad;
	}
	public void setBandwidthLoad(float bandwidthLoad) {
		this.bandwidthLoad = bandwidthLoad;
	}

	public float getTonerRemaining() {
		return TonerRemaining;
	}

	public void setTonerRemaining(float tonerRemaining) {
		TonerRemaining = tonerRemaining;
	}
	
}
