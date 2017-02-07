package Metier;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

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
 * Class for Network. This is a connection between two peripherals which have an IP address.
 */
public class Network extends StrategicPoint 
{
	private String IPAddress2;
	
	/**
	 * @category constructor
	 * When created, it is associated at a LED and service
	 * @since 1.00
	 */
	public Network() 
	{
		super.led = new Led();
		super.service = new Service(); 
	}


	@Override
	public void getAll(ArrayList<StrategicPoint> listNetwork)
	{	

		String url ="http://"+SERVER_NAME+":"+PORT_NUMBER+"/networks/";
		int j=0;
		int listLenght = listNetwork.size();
		GetData getSPs = new GetData();

		JSONArray json = getSPs.Start(url);	

		for(int i=listLenght;i<json.length()+listLenght;i++)
		{
			try 
			{	
				JSONObject jsonObject = json.getJSONObject(j);
				j++;
				if(jsonObject.length()>0)
				{
					listNetwork.add(new Network());	
					
					listNetwork.get(i).setID( jsonObject.getInt("id"));
					listNetwork.get(i).setName(jsonObject.getString("name"));
					listNetwork.get(i).setDescription(jsonObject.getString("description"));
					listNetwork.get(i).setLocation(jsonObject.getString("location"));
					listNetwork.get(i).setIcon(jsonObject.getString("icon"));
					listNetwork.get(i).setIPAddress(jsonObject.getString("IPaddress"));	

					listNetwork.get(i).getService().setName(jsonObject.getString("service"));
					listNetwork.get(i).getService().setID(listNetwork.get(i).getService().getID());

					listNetwork.get(i).getLed().setID((byte) jsonObject.getInt("id_LED")); 
					listNetwork.get(i).getLed().setColor(jsonObject.getString("color"));
					listNetwork.get(i).getLed().setUNC(jsonObject.getString("UNC"));				

					((Network) listNetwork.get(i)).setIPAddress2(jsonObject.getString("IPaddress2"));	
					
					listNetwork.get(i).setListBug();
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
		String Server_Rest_Address = "http://"+SERVER_NAME+":"+PORT_NUMBER+"/networks";
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
			json.put("IPaddress2",this.getIPAddress2());
											
			json.put("id_LED",this.getLed().getID()); 			
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
				System.out.println("Network.Add() unable to convert string to JSON");
				e.printStackTrace();
			}
		}
		
		
		try 
		{
			stateConnexion = tempJson.getInt("status");
			System.out.println("status -->"+stateConnexion);
			
		} 
		catch (JSONException e1) 
		{
			System.out.println("Network.Add() unable to convert json to int ");
			e1.printStackTrace();
		}			
		
		return (stateConnexion);
	}

	
	

	/**
	 * After the detection, it will save new Strategic points's LED into database
	 * @return stateConnexion it's an integer with only two values (as a boolean) to know if strategic point has been updated successfully
	 * @ version 1.00
	 */
	public int update()
	{
		int state = super.update();
		return state;
	}
	

	@Override
	public void detectSP()
	{

		InetAddress address = null;	
		InetAddress address2 = null;		

		try 
		{			
			address = InetAddress.getByName(this.getIPAddress());	
			address2 = InetAddress.getByName(this.getIPAddress2());				
		} 
		catch (UnknownHostException e) 
		{
			System.out.println("Unknown host, please check manually with PING command");
			e.printStackTrace();
		}
						
		try 
		{
			
			if (address.isReachable(5000)) 
			{
				
				// For StrategicPoint with 2 IPaddresses, as network
				if(address.isReachable(5000) && address2.isReachable(5000))
				{
					this.getLed().setID((byte)4);				
				}				
			} 
			else 
			{
				System.out.println(this.getName() + ": Unable to detect the StrategicPoint - Unable to communicate with ");
				
				this.getLed().setID((byte)1);
			}
			} 
			catch (IOException e) 
			{

				e.printStackTrace();
			}
		
	
	}
	
	@Override
	public String convertImageToBase64(String type) 
	{
		File  file= null;
		String encodedImage = null;
		BufferedImage originalImage;
		file= new File("src/images/icones/network.png");			
		
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
	
/////////////************************    Getters and Setters *************************
	
	public String getIPAddress2() {
		return IPAddress2;
	}

	public void setIPAddress2(String iPAddress2) {
		IPAddress2 = iPAddress2;
	}
}
