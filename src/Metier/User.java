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
 * Class for user
 */
public class User 
{
	protected byte ID;
	protected String username;
	protected String password;
	protected String email;
	protected String Service;
	protected byte IDservice;
	
	protected byte adminAccess = 0;
	protected byte mailReceived = 0;
	protected byte accountEnable = 0;	
	
	
	public User() 
	{
	}


	/**
	 * Checks if login exists into database
	 * @return ID of user connected or null 
	 */
	public byte login()
	{				
		UpdateData postData = new UpdateData();
		String jsonReturned = null;
		String Server_Rest_Address = "http://"+StrategicPoint.SERVER_NAME+":"+StrategicPoint.PORT_NUMBER+"/users/login";
		String method ="POST";
		JSONObject tempJson = null;
		byte idUser = 0;
		int stateConnexion=-1;
		
		JSONObject json = new JSONObject(); 
		try 
		{
			json.put("username",this.getUsername());
			json.put("password",this.getPassword());
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
				System.out.println("User.login() unable to convert string to JSON");
				e.printStackTrace();
			}
		}
			
		
		
		try 
		{
			stateConnexion = tempJson.getInt("status");
			System.out.println("User.login()  status -->"+stateConnexion); 
			
		} 
		catch (JSONException e1) 
		{
			System.out.println("User.login() unable to convert json to string ");
			e1.printStackTrace();
		}		
		
		if(stateConnexion == 0)
		{
			try 
			{
				idUser = (byte) tempJson.getInt("id");
			} 
			catch (JSONException e) 
			{
				System.out.println("User.login() unable to convert json to int cast to byte");
				e.printStackTrace();
			}			
		}		

		return idUser;
	}
	
	/**
	 * Add a new user into database
	 * @return stateConnexion 1 if user has been added successfully, otherwise 0
	 * @version 1.00
	 */
	public int add()
	{
		UpdateData postData = new UpdateData();
		Service service = new Service((byte) 0,this.Service);
		JSONObject json = new JSONObject(); 
		String method ="POST";
		String jsonReturned = null;
		String Server_Rest_Address = "http://"+StrategicPoint.SERVER_NAME+":"+StrategicPoint.PORT_NUMBER+"/users";
		JSONObject tempJson = null;
		int stateConnexion=-1;				
		
		try 
		{
			json.put("username",this.getUsername());
			json.put("password",this.getPassword());
			json.put("email",this.getEmail());
			json.put("adminAccess",0);
			json.put("emailReceived",0);
			json.put("accountEnable",0); 
			json.put("id_Service",service.getID());
			
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
				System.out.println("User.add() unable to convert string to JSON");
				e.printStackTrace();
			}
		}
		
		try 
		{
			stateConnexion = tempJson.getInt("status");
			
		} 
		catch (JSONException e1) 
		{
			System.out.println("User.add() unable to convert json to int ");
			e1.printStackTrace();
		}			
		
		return (stateConnexion);
	}
	
	/**
	 * Get all users from database
	 * @version 1.00
	 * @param listUsers instanced to null into Controller 
	 */
	
	public void getAllUsers(ArrayList<User> listUsers)
	{
		String url ="http://"+StrategicPoint.SERVER_NAME+":"+StrategicPoint.PORT_NUMBER+"/users/";

		GetData getSPs = new GetData();
		
		JSONArray json = getSPs.Start(url);	
		System.out.println("UsergetAllUsers() : Taille Json -->"+json.length());
		
		for(int i=0;i<json.length();i++)
		{
			try 
			{
				
				JSONObject jsonObject = json.getJSONObject(i);
				if(jsonObject.length()>0)
				{
					listUsers.add(i, new User());
								
					listUsers.get(i).setID( (byte) jsonObject.getInt("id"));
					listUsers.get(i).setUsername(jsonObject.getString("username"));
					listUsers.get(i).setPassword(jsonObject.getString("password"));
					listUsers.get(i).setEmail(jsonObject.getString("email"));
					listUsers.get(i).setAdminAccess((byte)jsonObject.getInt("adminAccess"));
					listUsers.get(i).setMailReceived((byte)jsonObject.getInt("emailReceived"));
					listUsers.get(i).setAccountEnable((byte)jsonObject.getInt("accountEnable"));
					listUsers.get(i).setIDservice( (byte) jsonObject.getInt("id_Service"));	
			
				}
			} 
			
			catch (JSONException e) 
			{				
				e.printStackTrace();
			}	
		}	
		
	}
	
	/**
	 * Send email about his account creation confirmation
	 * @return mailSent true or false which confirm if email has been sent successfully
	 */
	public boolean sendActivationMail()
	{
		Mail email = new Mail();
		String tempBody="Bonjour "+this.username+", \n \nL'accès à MonitorYourLAN vient d'être activé. \nPour rappel voici vos identifiants: \nVotre nom d'utilisateur : "+this.username+"\nVotre mot de passe: "+this.password+" \n \n Cordialement \n L'équipe MonitorYourLan";
		
		email.setUsername("***@gmail.com");     // enter a sender address mail
		email.setPassword("***");				// enter a sender password
		email.setRecipient(this.email);
		email.setSubject("Activation de votre compte MonitorYourLAN");
		email.setEmailBody(tempBody);
		
		System.out.println("User.sendMail()  Recipient:"+email.getRecipient());
		boolean mailSent = email.start();		
		
		return(mailSent);
	}
	
	/**
	 * Update user in the database
	 * @return stateConnexion success or error (0 or 1)
	 */
	public int updateAccount()
	{		
		String method ="PUT";
		UpdateData updateData = new UpdateData();
		JSONObject json = new JSONObject(); 		
		String jsonReturned = null;
		String Server_Rest_Address = "";
		JSONObject tempJson = null;
		int stateConnexion=-1;	
		
		try 
		{
			json.put("username",this.getUsername());
			json.put("password",this.getPassword());
			json.put("email",this.getEmail());
			json.put("adminAccess",this.isAdminAccess());
			json.put("emailReceived",this.isMailReceived());
			json.put("accountEnable",this.isAccountEnable());
			json.put("id_Service",this.getIDservice());
			
		} 
		catch (JSONException e) 
		{			
			e.printStackTrace();
		}
		
		if(json.length()>0)
		{	
			Server_Rest_Address = "http://"+StrategicPoint.SERVER_NAME+":"+StrategicPoint.PORT_NUMBER+"/users" + "/" + this.getID();
			jsonReturned = updateData.Start(json,Server_Rest_Address,method);  	
			try 
			{
				tempJson = new JSONObject(jsonReturned);
			} 
			catch (JSONException e) 
			{
				System.out.println("user.updateAccount()  unable to convert string to JSON");
				e.printStackTrace();
			}
			try 
			{
				stateConnexion = tempJson.getInt("status");
				System.out.println("user.updateAccount() status -->"+stateConnexion);
				
			} 
			catch (JSONException e1) 
			{
				System.out.println("user.updateAccount() unable to convert json to int ");
				e1.printStackTrace();
			}							
		}
		
		return (stateConnexion);
	}
	
	
	
		/////////////************************    Getters and Setters *************************
	public byte getID() {
		return ID;
	}

	public void setID(byte iD) {
		ID = iD;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getService() {
		return Service;
	}


	public void setService(String service) {
		Service = service;
	}


	public byte isAdminAccess() {
		return adminAccess;
	}


	public void setAdminAccess(byte adminAccess) {
		this.adminAccess = adminAccess;
	}


	public byte isMailReceived() {
		return mailReceived;
	}


	public void setMailReceived(byte mailReceived) {
		this.mailReceived = mailReceived;
	}


	public byte isAccountEnable() {
		return accountEnable;
	}


	public void setAccountEnable(byte accountEnable) {
		this.accountEnable = accountEnable;
	}


	public byte getIDservice() {
		return IDservice;
	}


	public void setIDservice(byte iDservice) {
		IDservice = iDservice;
	}
}
