package Metier;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import Donnees.GetData;
import Donnees.UpdateData;

/**
 * 
 * @author Cyril as Will30 (GitHub) or Will15 (GitLab)
 * @version 1.06
 * Abstract class for Strategic Point. A strategic can be an equipment (computer, printer, switch,etc...), a network (this a communication between 
   two IP addresses) or a data storage.   
 *
 */

public abstract class StrategicPoint
{

	protected static final String SERVER_NAME = "localhost";	// NodeJS server name
	protected static final int PORT_NUMBER = 3001;				// NodeJS port number
	
	private int ID;									// auto strategic point ID  (defines into database)
	private String name;							// strategic point name
	private String description;						// strategic point description, more
	private String IPAddress;						// for example "10.111.60.30"
	private String location;	
	private String icon=null;
	protected Service service;
	protected Led led;
	
	private boolean statusChanged=false;	
	private  ArrayList<Bug> listBug;
	
	
	public StrategicPoint() 
	{
		
	}
	
	/**
	 * Get all Strategic points from database - this function calls GetData() class
	 * @param listSPs this is the StrategicPoint's list 
	 * @since 1.01
	 */
	public abstract void getAll(ArrayList<StrategicPoint> list);
	

	
	/**
	 * Get more info about a strategic point and defines if strategic point is an equipment, network or datastorage
	 * @since 1.01	 
	 */
	public void getMoreInfoforSP()
	{
		
		String url ="http://"+SERVER_NAME+":"+PORT_NUMBER+"/SPs/"+this.getID();

		GetData getSPs = new GetData();		
		JSONArray json = getSPs.Start(url);	
			
		for(int i=0;i<json.length();i++)
		{
			try 
			{	
				JSONObject jsonObject = json.getJSONObject(i);
		
				if(jsonObject.length()>0)
				{
					this.setName(jsonObject.getString("name"));
					this.setDescription(jsonObject.getString("Description")); 
					this.setLocation(jsonObject.getString("location"));
					this.setIcon(jsonObject.getString("icon")); 
					this.setIPAddress(jsonObject.getString("IPaddress")); 
					this.getService().setID((byte) jsonObject.getInt("id_Service")); 
				
					this.getLed().setID((byte)jsonObject.getInt("id_LED"));
					this.getLed().setColor(jsonObject.getString("color"));
					this.getLed().setUNC(jsonObject.getString("UNC"));
				}
			} 
			
			catch (JSONException e) 
			{				
				e.printStackTrace();
			}	
		}		
	}
	
	/**
	 * Get Bug and Solution for SP, at given date
	 * @since 1.01	 
	 */
	public void getBugForThisSP()
	{
		String method ="POST";
		UpdateData postData = new UpdateData();
		JSONObject json = new JSONObject(); 	
		JSONArray jsonArray;
		String jsonReturned = null;
		String Server_Rest_Address = "http://"+SERVER_NAME+":"+PORT_NUMBER+"/bugs/thisSP";
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		JSONObject tempJson = null;

		try 
		{			
			json.put("date",dateFormat.format(date));
			json.put("id",this.getID());
		} 
		catch (JSONException e) 
		{			
			e.printStackTrace();
		}
		
		if(json.length()>0)
		{				
			jsonReturned = postData.Start(json,Server_Rest_Address,method);  	
			try 
			{
				jsonArray = new JSONArray(jsonReturned);
				tempJson = jsonArray.optJSONObject(0);
			
				if(String.valueOf(tempJson).equals("null") == false)
				{
					this.setListBug();
					this.getListBug().add(new Bug());
					
					// Just get the first bug found
					this.listBug.get(0).setDetail(tempJson.getString("detail"));
					this.listBug.get(0).getListSolution().get(0).setDescription(tempJson.getString("description"));
				}
			} 
			catch (JSONException e1) 
			{			
				e1.printStackTrace();
			}					
		}
	}
	
	/**
	 * Add a new strategic point into database
	 * @since 1.01
	 * @return stateConnexion it's an integer with only two values (as a boolean) to know if strategic point has been added successfully
	 */
	
	public abstract int add();
	
	/**
	 * Start command ping and detect if Strategic point is available
	 * @since 1.03 Modify for network
	 */
	
	public abstract void detectSP();
	
	
	/**
	 * Converts image (byte[]) to String base64
	 * @param this parameter for equipment or network, to assign an image (UNC) at this strategic point. Then, this image will be converting into string base64 
	 * @return encodedImage Image encoded to string base64
	 */
	public abstract String convertImageToBase64(String type);
	
	
	/**
	 * After the detection, this function will save new Strategic points's LED into database
	 * @return stateConnexion it's an integer with only two values (as a boolean) to know if strategic point has been updated successfully
	 * @ version 1.00
	 */
	public int update()
	{
		String method ="PUT";
		UpdateData postData = new UpdateData();
		JSONObject json = new JSONObject(); 		
		String jsonReturned = null;
		String Server_Rest_Address = "";
		JSONObject tempJson = null;
		int stateConnexion=-1;		
		
		Server_Rest_Address = "";
		try 
		{			
			json.put("name",this.getName());
			json.put("description",this.getDescription());
			json.put("location",this.getLocation());
			json.put("icon",this.getIcon());
			json.put("IPaddress",this.getIPAddress());
			json.put("id_Service",this.getService().getID());
			json.put("id_LED",this.getLed().getID());
	
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
	 * Delete a strategic point
	 * @version 1.00
	 */
	public void delete()
	{		
		String method ="DELETE";
		UpdateData deleteData = new UpdateData();
		
		String url="http://"+SERVER_NAME+":"+PORT_NUMBER+"/SPs/"+this.getID();				
		deleteData.Start(null, url, method);
	}
	
	
	/////////////************************    Getters and Setters *************************

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
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

	public String getIPAddress() {
		return IPAddress;
	}

	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}


	public Led getLed() {
		return led;
	}

	public void setLed() {
		// Not used
	}

	public Service getService() {
		return service;
	}
	
	public void setService() 
	{
	//	Not used		
	}

	public boolean isStatusChanged() {
		return statusChanged;
	}

	public void setStatusChanged(boolean statusChanged) {
		this.statusChanged = statusChanged;
	}


	public ArrayList<Bug> getListBug() {
		return listBug;
	}

	public void setListBug() {
		this.listBug =  new ArrayList<Bug>();
	}

	
	
	
}
