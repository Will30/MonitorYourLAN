package Metier;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Donnees.GetData;
import Donnees.UpdateData;

/**
 * @author Cyril or Will30 (GitHub) or Will15 (GitLab)
 * @version 1.00
 * Class for Bug: Each strategic point could have a bug
 */
public class Bug 
{
	private String date;  							// date which bug has been detected (defines into database or updated by application)
	private int ID;									// auto ID bug (defines into database)
	private String name;							// name (defines into database)
	private int thresholdValue;						// alert value (defines into database)
	private String detail;							// more info about bug encoutered (defines into database)
	private String category;						// category for bug name (it could be RAM, HDD, CPU or others)
	private byte IDcolor;							// each bug is already defines and associated at a color ID (defines into database)
	private String color;							// color name accordind to color ID
	private ArrayList<Solution> listSolution;		// see more details in constructor
	
	/**
	 * @category constructor
	 * When created, a bug is associated to solution list
	 * @since 1.00
	 */
	public Bug()
	{
		this.listSolution = new ArrayList<Solution>();
		this.listSolution.add(new Solution());
	}
	
	/**
	 * Get all bug for all strategic point
	 * @param listBug Strategic point 's list
	 * @since 1.00
	 */
	public void getAll(ArrayList<Bug> listBug)
	{	
		String url ="http://"+StrategicPoint.SERVER_NAME+":"+StrategicPoint.PORT_NUMBER+"/bugs";	
		
		GetData getSPs = new GetData();		
		JSONArray json = getSPs.Start(url);	
		int iBug = 0, iSolution=0, iSps;

		for(iSps=0;iSps<json.length();iSps++)
		{
			try 
			{	
				JSONObject jsonObject = json.getJSONObject(iSps);
		
				if(jsonObject.length()>0)
				{
					
										
					if(iSps>0)
					{
						if(jsonObject.getInt("id") == listBug.get(iBug-1).getID())
						{
							iBug--;		
							iSolution++;
							listBug.get(iBug).getListSolution().add(new Solution());
						}		
						else
						{
							iSolution=0;
							listBug.add(new Bug());	
							listBug.get(iBug).getListSolution().add(new Solution());
						}
					}
					else
					{
						
						listBug.add(new Bug());		
						listBug.get(iBug).getListSolution().add(new Solution());
				
					}
					
					
					listBug.get(iBug).setID( jsonObject.getInt("id"));
					listBug.get(iBug).setName(jsonObject.getString("name"));
					listBug.get(iBug).setThresholdValue(jsonObject.getInt("value"));
					listBug.get(iBug).setDetail(jsonObject.getString("errorDetail"));
					listBug.get(iBug).setCategory(jsonObject.getString("category"));
					listBug.get(iBug).setIDcolor((byte) jsonObject.getInt("IDColor"));	
					
					listBug.get(iBug).getListSolution().get(iSolution).setDescription(jsonObject.getString("solution"));

					iBug++;
				}								
			} 
			
			catch (JSONException e) 
			{				
				e.printStackTrace();
			}	
		}
		
		
	}

	/**
	 * Get bug for only one strategic point at a given period
	 * @param listBug Strategic point 's list
	 * @param ID strategic point id
	 * @param date1 start date
	 * @param date2 end date
	 * @since 1.00
	 */
	public void getBugDuringPeriod(ArrayList<Bug> listBug,int ID, String date1,String date2)
	{
		UpdateData updateData = new UpdateData();		
		String method ="POST";		
		JSONObject jsonSent = new JSONObject(); 
			
		String Server_Rest_Address = "http://"+StrategicPoint.SERVER_NAME+":"+StrategicPoint.PORT_NUMBER+"/bugs";
	
		String jsonReturned = "";
		JSONArray tempJsonArray = null;
		JSONObject jsonObject =null;	

		try 
		{
			jsonSent.put("id",ID);
			jsonSent.put("date1",date1);
			jsonSent.put("date2",date2);
		
		} 
		catch (JSONException e) 
		{			
			e.printStackTrace();
		}
		
		if(jsonSent.length()>0)
		{			
			
			jsonReturned = updateData.Start(jsonSent,Server_Rest_Address,method); 
			try 
			{
				tempJsonArray = new JSONArray(jsonReturned);
			} 
			catch (JSONException e) 
			{
				System.out.println("Bug.getBugDuringPeriod() unable to convert string to JSONArray");
				e.printStackTrace();
			}

			for(int i=0;i<tempJsonArray.length();i++)
			{
				try 
				{	
					jsonObject = tempJsonArray.optJSONObject(i);					
					if(jsonObject.length()>0)
					{
						listBug.add(new Bug());
						listBug.get(i).setDate(jsonObject.getString("dateCreation"));
						listBug.get(i).setDetail(jsonObject.getString("bug"));
						listBug.get(i).setColor(jsonObject.getString("color"));
						listBug.get(i).setIDcolor((byte) jsonObject.getInt("IDLed")); 											
					}
				}
				catch (JSONException e) 
				{				
					e.printStackTrace();
				}	
			}
			
			if(listBug.isEmpty() == false)
			{
				System.out.println("taille de listBug :"+listBug.size());
			}
		}				
	}
	
	
	/////////////************************    Getters and Setters *************************
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}

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

	public int getThresholdValue() {
		return thresholdValue;
	}

	public void setThresholdValue(int thresholdValue) {
		this.thresholdValue = thresholdValue;
	}

	

	public ArrayList<Solution> getListSolution() {
		return listSolution;
	}

	public void setListSolution() {
		this.listSolution = new ArrayList<Solution>();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setListSolution(ArrayList<Solution> listSolution) {
		this.listSolution = listSolution;
	}

	public byte getIDcolor() {
		return IDcolor;
	}

	public void setIDcolor(byte iDcolor) {
		IDcolor = iDcolor;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
