package Metier;

import java.util.ArrayList;

import javax.swing.SwingWorker;
import javax.swing.Timer;

import Presentation.MainWindow;

/**
 * @author Cyril or Will30 (GitHub) or Will15 (GitLab)
 * @version 1.04
 * Class witch launch a detection on each strategic point, and detect if bug is present. It is started in new thread, in background process.
 */
public class DetectionAndDiagnostic extends SwingWorker<Object ,String>
{
	//public Timer timer;
	//protected int milliseconds;
	private ArrayList<StrategicPoint> listSPs;	
	private ArrayList<Bug> listBugsIntoDatabase;	
	private MainWindow MainPanel;
	
	
	/**
	 * @category constructor
	 * @param listSPS strategic point list
	 * @param listBugsIntoDatabase List of all bugs in the database
	 * @param MainPanel panel display which needs to be updated
	 * @since 1.00
	 */
	public DetectionAndDiagnostic(ArrayList<StrategicPoint> listSPs, ArrayList<Bug> listBugsIntoDatabase,MainWindow MainPanel)
	{       
		this.listSPs = listSPs;
		this.listBugsIntoDatabase = listBugsIntoDatabase;
		this.MainPanel = MainPanel;
	}


	@Override
	public Object doInBackground() 
	{
		System.out.println("************************************************************* Executing Timer in asynctask***********************************************************");
	
		int oldLedColorID = 0;
		
		for (int i = 0; i < listSPs.size(); i++) 
		{
			System.out.println("************************************************************* Starting detection for "+this.listSPs.get(i).getName());
			oldLedColorID = listSPs.get(i).getLed().getID();
			
			if(listSPs.get(i) instanceof Equipment)
			{
				((Equipment) listSPs.get(i)).detectSP();
				
				// Launch a diagnostic to check more element inside an equipment
				if(listSPs.get(i).getLed().getID() != 1) 
				{	
					if(((Equipment) listSPs.get(i)).getListOID().size() > 0)
					{							
						((Equipment) listSPs.get(i)).diagnose();
						((Equipment) listSPs.get(i)).checkAlert(this.listBugsIntoDatabase);
					}						
				}	
			}
			
			
			if(listSPs.get(i) instanceof Network)
			{
				((Network) listSPs.get(i)).detectSP();
			}
			if(listSPs.get(i) instanceof DataStorage)
			{
				((DataStorage) listSPs.get(i)).detectSP();
				((DataStorage) listSPs.get(i)).diagnose(this.listBugsIntoDatabase);
			}

			// If after new detection, Led color has changed , an update of this will starting
			if(listSPs.get(i).getLed().getID() != oldLedColorID)
			{
			
				if(listSPs.get(i) instanceof Equipment)
				{
					((Equipment) listSPs.get(i)).update();
					
					// if led color switch on green color
					if(listSPs.get(i).getListBug().isEmpty() == false)
					{
						((Equipment) listSPs.get(i)).createBugIntoDatabase();
					}						
				}
				
				if(listSPs.get(i) instanceof Network)
				{
					((Network) listSPs.get(i)).update();
				}
				
				if(listSPs.get(i) instanceof DataStorage)
				{
					((DataStorage) listSPs.get(i)).update();
				}
				
				
				listSPs.get(i).getMoreInfoforSP();
			}
			
				
	}

	System.out.println("*************************************************                   Updating displays              ********************************************************************");
	this.MainPanel.displaySPs(this.listSPs,0);		
	return null;
		
	}
}


	
	
	
	
	
	
	
	
