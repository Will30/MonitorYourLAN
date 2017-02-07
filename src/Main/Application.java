package Main;
import java.awt.HeadlessException;
import java.text.ParseException;


import Presentation.Controller;



/**
 * 
 * @author Cyril as Will30 (GitHub) or Will15 (GitLab)
 * @version 1.00
 * Main Class for start MonitorYourLAN
 *
 */
public class Application 
{
	
	
	
	public Application() throws HeadlessException, ParseException 
	{
		Controller myController = new Controller();			
	}

	public static void main(String[] args) throws HeadlessException, ParseException 
	{
		
		System.out.println( "MonitorYourLAN starting...");
		new Application();

	}

}
