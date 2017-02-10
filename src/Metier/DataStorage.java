package Metier;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
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
 * Class for DataStorage. It often represented by a folder/repository into a computer. In our project, data storage receives files at each time 
   interval.
 */

public class DataStorage extends StrategicPoint
{
	private String UNC; 		// it can be just a folder name or a way to a folder (example --> c:\temp\myFolder)
	private Date lastDate;		// the last time when the last file has been received

	
	/**
	 * @category constructor
	 * When created, data storage is associated at a LED and service
	 * @since 1.00
	 */
	public DataStorage() 
	{
		super.led = new Led();
		super.service = new Service(); 
		super.listBug = new ArrayList<Bug>();
	}

	@Override
	public void getAll(ArrayList<StrategicPoint> listdataStorage) 
	{
		String url ="http://"+SERVER_NAME+":"+PORT_NUMBER+"/dataStorages/";
		int j=0;

		int listLenght = listdataStorage.size();
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
					listdataStorage.add(new DataStorage());	
					
					listdataStorage.get(i).setID( jsonObject.getInt("id"));
					listdataStorage.get(i).setName(jsonObject.getString("name"));
					listdataStorage.get(i).setDescription(jsonObject.getString("description"));
					listdataStorage.get(i).setLocation(jsonObject.getString("location"));
					listdataStorage.get(i).setIcon(jsonObject.getString("icon"));
					listdataStorage.get(i).setIPAddress(jsonObject.getString("IPaddress"));	

					listdataStorage.get(i).getService().setName(jsonObject.getString("service"));
					listdataStorage.get(i).getService().setID((byte)jsonObject.getInt("id_Service"));

					listdataStorage.get(i).getLed().setID((byte) jsonObject.getInt("id_LED")); 
					listdataStorage.get(i).getLed().setColor(jsonObject.getString("color"));
					listdataStorage.get(i).getLed().setUNC(jsonObject.getString("UNCLed"));				

					((DataStorage) listdataStorage.get(i)).setUNC(jsonObject.getString("UNCFolder"));	
					
					((DataStorage) listdataStorage.get(i)).setLastDate(jsonObject.getString("lastfile"));				
					
			//		listdataStorage.get(i).setListBug();
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
		String Server_Rest_Address = "http://"+SERVER_NAME+":"+PORT_NUMBER+"/dataStorages";
		JSONObject tempJson = null;
		int stateConnexion=-1;		
		
		System.out.println("DataStorage.add() - service récupéré --> "+this.getService().getName());
		
		try 
		{
			json.put("name",this.getName());
			json.put("description",this.getDescription());
			json.put("location",this.getLocation());
			json.put("icon",this.getIcon());
			json.put("IPaddress",this.getIPAddress());
			json.put("id_Service",this.getService().getID()); 
			json.put("UNC",this.getUNC());
			
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
				System.out.println("DataStorage.Add() unable to convert string to JSON");
				e.printStackTrace();
			}
		}
		
		System.out.println("retour du JSON "+ String.valueOf(tempJson));
		
		try 
		{
			stateConnexion = tempJson.getInt("status");
			super.setID(tempJson.getInt("id"));
			System.out.println("DataStorage.Add()   Last ID --> "+tempJson.getInt("id"));
			System.out.println("DataStorage.Add() 	Status -->"+stateConnexion);
			
		} 
		catch (JSONException e1) 
		{
			System.out.println("DataStorage.Add() unable to convert json to int ");
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

	@Override
	public String convertImageToBase64(String type) 
	{
		File  file= null;
		String encodedImage = null;
		BufferedImage originalImage;
		file= new File("src/images/icones/dataStorage.png");						
		
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
	 * After the detection, this function will update Strategic points's LED into database
	 * @return stateConnexion it's an integer with only two values (as a boolean) to know if strategic point has been updated successfully
	 * @ version 1.00
	 */
	public int update()
	{
		int state = super.update();
		return state;		
	}




	/**
	 * Function which start command ping and detect if Strategic point is available
	 * @param listSPs this is a list of all Strategic Points from database
	 * @version 1.03 Modify for network
	 */
	@Override
	public void detectSP()
	{
		InetAddress address = null;			
		try 
		{												
			address = InetAddress.getByName(this.getIPAddress());		
		} 
		catch (UnknownHostException e) 
		{
			System.out.println("DataStorage.detectSP() - Bad IP address format");
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
				System.out.println("DataStorage.detectSP() - "+this.getName() + ": Unable to detect the StrategicPoint - Unable to communicate with ");
				this.getLed().setID((byte)1);		// id => 1 equals to black color
	
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Function verify activity for data storage. Normally a data storage is a folder which receives files each "x" seconds, minutes or hours 
	 * (as an archive storage). This function checks if files are still uploaded into.
	 * @ version 1.00
	 */
	
	public void diagnose(ArrayList<Bug> listBugIntoDatabase)
	{		
		Date lastCreation = null;
		int lastModifiedDay;
		int lastModifiedHours;
		int lastModifiedMinutes;
		int actualDay;
		int actualHours;
		int actualMinutes;
		int difference = 0;
		
		int numberFiles;		
		
		//For this project test, our file will be called "archive.txt"
		String tempUNC;
		File file;		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.FRANCE);
		Date date = new Date();
		System.out.println("DataStorage.diagnose()   - Date :"+dateFormat.format(date)); //2013/10/15 16:16:39
		
		// For counting existing files in folder
		numberFiles = new File("\\"+"\\"+this.getIPAddress()+"\\"+this.getUNC()+"\\").list().length;	
		System.out.println("DataStorage.diagnose()   - Numbers of files  --> "+numberFiles);
		
		tempUNC="\\"+"\\"+this.getIPAddress()+"\\"+this.getUNC()+"\\"+"archive"+numberFiles+".txt";
		file = new File(tempUNC);
		if(file.exists())
		{
			System.out.println("DataStorage.diagnose()   - Files exists");
			
			lastCreation = new Date (file.lastModified());		
			System.out.println("DataStorage.diagnose()   - Last modification "+dateFormat.format(lastCreation));
		}	
		else
		{
			System.out.println("DataStorage.diagnose()   - Files doesn't exists");
		}
		
	
		lastModifiedDay = Integer.parseInt(dateFormat.format(lastCreation).substring(8, 10));
		lastModifiedHours = Integer.parseInt(dateFormat.format(lastCreation).substring(11, 13));
		lastModifiedMinutes = Integer.parseInt(dateFormat.format(lastCreation).substring(14, 16));
		
		actualDay = Integer.parseInt(dateFormat.format(date).substring(8, 10));
		actualHours = Integer.parseInt(dateFormat.format(date).substring(11, 13));
		actualMinutes = Integer.parseInt(dateFormat.format(date).substring(14, 16));
		
	
		if(actualDay - lastModifiedDay >= 0)
		{
			difference = (actualDay - lastModifiedDay) * 1440;
		}
		else
		{
			difference = ((actualDay+lastModifiedDay) - lastModifiedDay - 1) * 1440;
		}
		
		if(actualHours - lastModifiedHours >= 0)
		{
			
				difference = difference + (actualHours - lastModifiedHours) * 60; // to get value in minutes	
		}
		else
		{
			difference = difference + (24 +(actualHours-lastModifiedHours)) * 60; // to get value in minutes
		}
		
	
		if(actualMinutes-lastModifiedMinutes >= 0)
		{
			difference = difference + (actualMinutes-lastModifiedMinutes);
		}
		else
		{
			difference = difference + (60 +(actualMinutes + lastModifiedMinutes));
		}
		
		System.out.println("DataStorage.diagnose()   - (TOTAL) Last update is --> "+difference+" minutes");
	
		
		if(difference > listBugIntoDatabase.get(9).getThresholdValue())  			// Corresponds to the minimal alert (5 minutes) ==> DataStorageMinimalAlert
		{
			if(difference > listBugIntoDatabase.get(10).getThresholdValue()) 		// Corresponds to the maximal alert (15 minutes) ==> DataStorageMaximalAlert
			{
				if(difference > listBugIntoDatabase.get(11).getThresholdValue())	// Indicates that date storage doesn't receive files (1 hour) ==> DataStorageDown
				{
					this.getLed().setID(listBugIntoDatabase.get(11).getIDcolor());				
					
					if(this.getListBug().isEmpty() == true)
					{
						this.getListBug().add(listBugIntoDatabase.get(11));
					}
					else
					{
						this.getListBug().set(0, listBugIntoDatabase.get(11));
					}
					
					this.getListBug().get(0).setDate(dateFormat.format(date));
				}
				else
				{
					this.getLed().setID(listBugIntoDatabase.get(10).getIDcolor());			
					
					
					if(this.getListBug().isEmpty() == true)
					{
						this.getListBug().add(listBugIntoDatabase.get(10));
					}
					else
					{
						this.getListBug().set(0, listBugIntoDatabase.get(10));
					}
					
					this.getListBug().get(0).setDate(dateFormat.format(date));
				}
			}
			else
			{
				this.getLed().setID(listBugIntoDatabase.get(9).getIDcolor());		
				
				
				if(this.getListBug().isEmpty() == true)
				{
					this.getListBug().add(listBugIntoDatabase.get(9));
				}
				else
				{
					this.getListBug().set(0, listBugIntoDatabase.get(9));
				}
				this.getListBug().get(0).setDate(dateFormat.format(date));
			}
		}
		else
		{
			this.getLed().setID((byte) 4);
			if(this.getListBug().isEmpty() == false)
			{
				this.getListBug().removeAll(this.getListBug());
			}
			
		}
				
	}


	/////////////************************    Getters and Setters *************************
	
	public String getUNC() {
		return UNC;
	}
	
	
	
	public void setUNC(String uNC) {
		UNC = uNC;
	}
	
	
	
	public Date getLastDate() {
		return lastDate;
	}
	
	
	
	public void setLastDate(String lastDate) 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD hh:mm:ss", Locale.FRANCE);
		
		try 
		{
			this.lastDate = dateFormat.parse(lastDate);
		} 
		catch (ParseException e) 
		{	
			e.printStackTrace();
		}
	}

}