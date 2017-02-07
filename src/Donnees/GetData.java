package Donnees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * 
 * @author Cyril or Will30 (GitHub) or Will15 (GitLab)
 * @version 1.00
 * Class using RESTful web services. This class in only for GET method, and returns JSONArray.
 *
 */

public class GetData 
{
	public Object myObjects;
    public JSONArray json;
	public String Recup=null;
	
	
	/**
	 * Get info from NodeJS server, through RestFul web services. 
	 * @param Url Special Uniform Ressource Locator to get specifically info
	 * @return json is an JSONArray type, witch contains info according to url
	 * @since 1.00
	 *  
	 */	
	
	public JSONArray Start(String Url)
	{
		HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();

        try
        {
            URL url = new URL(Url);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            urlConnection.connect();
            if(urlConnection.getResponseCode() == -1)
            {
            	 System.out.println("GetData.Start() - ResponseCode == null");            
            }

            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line = "";
            while ((line = in.readLine()) != null)
            {
                result.append(line);
            }
        }
        catch (Exception e)
        {
        	 System.out.println("GetData.Start() - Erreur " + e.getMessage());
        }
        if (urlConnection != null) urlConnection.disconnect();
        try
        {
            if (in != null) in.close();
        }
        catch (IOException e)
        {            
            e.printStackTrace();
        }


       try
        {    	   
            json = new JSONArray(result.toString());
        }
        catch (JSONException e)
        {
        	System.out.println("GetData.Start() - Unable to convert string Result to Json Object)");
            e.printStackTrace();            
        }

		return (json);
	}
}
