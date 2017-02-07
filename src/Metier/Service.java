package Metier;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Donnees.GetData;
import Donnees.UpdateData;

/**
 * @author Cyril or Will30 (GitHub) or Will15 (GitLab)
 * @version 1.00
 * Class for Service. Each user and strategic point are associated at a service
 */
public class Service 
{
	private byte ID = 1;
	private String name = "IT Service";
	
	public Service(byte ID,String name)
	{
		this.ID = ID;
		this.name = name;
	}
	
	public Service()
	{
		
	}
	
	/**
	 * Get all service from database
	 * @param listServices this is the service list 
	 * @since 1.01
	 * @return 
	 */
	
	public void getAll(ArrayList<Service> listServices)
	{
		String Server_Rest_Address = "http://"+StrategicPoint.SERVER_NAME+":"+StrategicPoint.PORT_NUMBER+"/services";
		GetData getSPs = new GetData();
		
		JSONArray json = getSPs.Start(Server_Rest_Address);	
		
		for(int i=0;i<json.length();i++)
		{
			try 
			{				
				JSONObject jsonObject = json.getJSONObject(i);
				if(jsonObject.length()>0)
				{
					listServices.add(i, new Service());
								
					listServices.get(i).setID((byte) jsonObject.getInt("id"));
					listServices.get(i).setName(jsonObject.getString("libelle"));					
				}
			} 
			
			catch (JSONException e) 
			{				
				e.printStackTrace();
			}	
		}	
	}

	
	/////////////************************    Getters and Setters *************************
	/**
	 * Getter modified to get name also
	 * @return ID of the service
	 */
	public byte getID() 
	{
		UpdateData postData = new UpdateData();
		JSONObject json = new JSONObject(); 
		String jsonReturned = null;
		String Server_Rest_Address = "http://"+StrategicPoint.SERVER_NAME+":"+StrategicPoint.PORT_NUMBER+"/services/id";
		String method ="POST";
		JSONObject tempJson = null;
		int stateConnexion=-1;	
		
		try 
		{
			json.put("libelle",this.getName());			
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
				tempJson = new JSONObject(jsonReturned);
			} 
			catch (JSONException e) 
			{
				System.out.println("Service.getID() unable to convert string to JSON");
				e.printStackTrace();
			}
		}
			
		
		try 
		{
			stateConnexion = tempJson.getInt("status");		
		} 
		catch (JSONException e1) 
		{
			System.out.println("Service.getID()  unable to convert json to string ");
			e1.printStackTrace();
		}		
		
		if(stateConnexion == 0)
		{
			try 
			{
				ID = (byte) tempJson.getInt("id");
			} 
			catch (JSONException e) 
			{
				System.out.println("Service.getID()  unable to convert json to int cast to byte");
				e.printStackTrace();
			}			
		}		
		
		return ID;
	}

	public void setID(byte iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
