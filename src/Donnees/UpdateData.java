package Donnees;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

/**
 * 
 * @author Cyril or Will30 (GitHub) or Will15 (GitLab)
 * @version 1.00
 * Class using RESTful web services. This class in only for POST,PUT and DELETE method.
 *
 */

public class UpdateData 
{
	
	/**
	 * Update, create or delete data into database via NodeJS server, through RestFul web services. 
	 * @param json a special JSONObject accepted by the NodeJS server.
	 * @param Url Special Uniform Ressource Locator to get specifically info
	 * @param Method indicates what type of transaction to used (POST,PUT or DELETE)
	 * @return json is an JSONArray type, witch contains info according to url
	 * @since 1.00
	 *  
	 */
	public String Start(JSONObject json,String Url,String Method)
	{
		String JsonResponse = null;
        String JsonDATA =String.valueOf(json);
        
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
       
            URL url = new URL(Url);
            
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);            
            urlConnection.setRequestMethod(Method);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");            
    
			urlConnection.connect();
			if(Method.equals("DELETE"))
			{
				// For DELETE method
				OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
			}
			else
			{
				// For POST and PUT method
				 Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
		         writer.write(JsonDATA);           
		         writer.close();
			}
			
            InputStream inputStream = urlConnection.getInputStream();

            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {

            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String inputLine;
            while ((inputLine = reader.readLine()) != null)
                buffer.append(inputLine + "\n");
            if (buffer.length() == 0) 
            {

            }
            JsonResponse = buffer.toString();

        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            if (urlConnection != null) 
            {
                urlConnection.disconnect();
            }
            if (reader != null) 
            {
                try 
                {
                    reader.close();
                } 
                catch (final IOException e) 
                {
                	System.out.println("UpdateData.Start() -Error closing stream"+ e);
                }
            }
        }
        return JsonResponse;
	}

}
