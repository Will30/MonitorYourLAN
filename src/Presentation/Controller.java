package Presentation;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import org.apache.commons.validator.routines.EmailValidator;

import Metier.Bug;

import Metier.DataStorage;
import Metier.DetectionAndDiagnostic;
import Metier.Equipment;
import Metier.Network;
import Metier.Service;
import Metier.StrategicPoint;
import Metier.User;


public class Controller extends JFrame implements ActionListener
{	
	private Login LoginPanel;
	private CreateAccount createAccountPanel;
	private MainWindow MainPanel;
	private AddElement addElementPanel;
	private ManageAccount manageUserPanel;
	private Detail detailPanel;
	private Historic historicPanel;
	
	
	protected User myUser;
	
	private ArrayList<StrategicPoint> listSPs;
	
	private ArrayList<Bug> listBugsIntoDatabase;
	private Equipment tempEquipment;
	private Network tempNetwork;
	private DataStorage tempDataStorage;
	private Bug tempBug;	

	private ArrayList<User> listUsers;
	private ArrayList<Service> listServices;
	
	private byte idUser=0;
	private DetectionAndDiagnostic treatment;
	
	public Dimension screenSize;
	public int screenWidth;
	public int screenHeight;
	protected  Timer timer;
	
	
	public Controller()
	{			
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
		
		listSPs = new ArrayList<StrategicPoint>();
		listBugsIntoDatabase = new ArrayList<Bug>();
		tempEquipment = new Equipment();
		tempNetwork  = new Network();
		tempBug = new Bug();
		tempDataStorage = new DataStorage();
        	
		this.setTitle("MonitorYourLAN");
	
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/logo.png")));
	
		this.setResizable(false);
		this.setMinimumSize(new Dimension(800, 600));
		this.setLocationRelativeTo(null);
	
		this.LoginPanel = new Login();
		this.createAccountPanel = new CreateAccount();

		this.addElementPanel = new AddElement();
		this.manageUserPanel = new ManageAccount();
		this.detailPanel = new Detail();
		this.historicPanel = new Historic();
		
		LoginPanel.connectButton.addActionListener(this);
		LoginPanel.registerButton.addActionListener(this);	
		
		this.setContentPane(LoginPanel);		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);	
	}
	
	

	/**
	 * Several listeners managed by controller. Panel is displayed according to event generated
	 * @param e : Action event performed 
	 */	
	public void actionPerformed(ActionEvent e) 
	{	
		/**
		 * Event for CreateAccount panel
		 * @param e : Action when click on Login panel's registerButton 
		 */

		if (e.getSource() == this.LoginPanel.registerButton) 
		{
			System.out.println("Registering an account...");
			
			listServices = new ArrayList<Service> ();
			Service tempService = new Service();
			
			tempService.getAll(listServices);
			
			this.createAccountPanel.buttonReturn.addActionListener(this);
			this.createAccountPanel.buttonOK.addActionListener(this);
			
			for(int i=0; i<listServices.size();i++)
			{
				this.createAccountPanel.getComboBoxService().addItem(listServices.get(i).getName());				
			}
			
			this.getContentPane().removeAll();
			
			this.setBounds(100, 100, 450, 300);
			this.setMinimumSize(new Dimension(800, 600));		
			
			this.setContentPane(this.createAccountPanel);
			this.validate();					
		}
		
		/**
		 * Event for save a new user into database
		 * @param e : Action when click on CreateAccount panel's ValidateButton 
		 */
		if (e.getSource() == this.createAccountPanel.buttonOK) 
		{
			User newUser = new User();	
			boolean MailOK=false;
			
			newUser.setUsername(this.createAccountPanel.getTxtUser().getText());
			newUser.setPassword(this.createAccountPanel.getTxtPassword().getText());
			newUser.setEmail(this.createAccountPanel.getTextMail().getText());
			newUser.setService(this.createAccountPanel.getComboBoxService().getSelectedItem().toString());
			
			// Minimum 4 characters , letters and digits required
			Pattern p = Pattern.compile("(?=(.*[a-zA-Z]))(?=(.*[0-9])).{4,}");
			Matcher m = p.matcher(this.createAccountPanel.getTxtPassword().getText());
			
			// EmailValidator is a free Apache package to check email address and others strings as date,ISBN,CreditCard.
			EmailValidator emailValidator = EmailValidator.getInstance();
			
			if(emailValidator.isValid(this.createAccountPanel.getTextMail().getText()))
			{
				MailOK = true;				
			}
			else
			{
				JOptionPane.showMessageDialog(this.createAccountPanel,"Votre adresse mail est invalide. \n Merci de la saisir à nouveau.","Erreur de saisie",JOptionPane.ERROR_MESSAGE);
				this.createAccountPanel.getTextMail().setText("");
				
			}	
				
			if((this.createAccountPanel.getTxtPassword().getText().equals(this.createAccountPanel.getTxtPsswordConfirmed().getText().toString()) == true) && MailOK == true)
			{
				if(m.matches() == true)
				{
					int confirmation = newUser.add();					
					if(confirmation == 0)
					{
						JOptionPane.showMessageDialog(this.createAccountPanel,"Votre compte a été créé. \nAprès vérification de vos informations, une email de confirmation vous sera envoyé.","Enregistrement terminé",JOptionPane.INFORMATION_MESSAGE);	
					}					
				}
				else
				{
					JOptionPane.showMessageDialog(this.createAccountPanel,"Votre mode de passe est invalide ! \n(4 caractères minimum - chiffre(s) ET lettre(s) obligatoires)","Erreur de saisie",JOptionPane.ERROR_MESSAGE);
					this.createAccountPanel.getTxtPassword().setText("");
					this.createAccountPanel.getTxtPsswordConfirmed().setText("");
				}		
			}
			else
			{
				JOptionPane.showMessageDialog(this.createAccountPanel,"Votre mot de passe est différent. \n Merci de le saisir à nouveau.","Erreur de saisie",JOptionPane.ERROR_MESSAGE);
				this.createAccountPanel.getTxtPassword().setText("");
				this.createAccountPanel.getTxtPsswordConfirmed().setText("");
			}			
		}
		
		/**
		 * Event to return to LoginPanel
		 * @param e : Action when click on CreateAccount panel's ReturnButton 
		 */
		if (e.getSource() == this.createAccountPanel.buttonReturn) 
		{
			this.LoginPanel = new Login();
			
			this.LoginPanel.connectButton.addActionListener(this);
			this.LoginPanel.registerButton.addActionListener(this);		
			
			this.getContentPane().removeAll();		
		
			this.setContentPane(this.LoginPanel);
			this.validate();	
		}
		
		/**
		 * Event for MainWindow
		 * @param e : Action when click on Login panel's connectButton 
		 */
		
		if (e.getSource() == this.LoginPanel.connectButton) 
		{
			
			byte tempID=0;			
			
			myUser = new User();			
			myUser.setUsername(this.LoginPanel.getUsername().getText());
			myUser.setPassword(this.LoginPanel.getPassword().getText());				
			
			tempID = myUser.login();
			
			if(tempID != 0)			{		
				
				this.setIdUser(tempID);	
				
				this.getContentPane().removeAll();
	
				
				this.setBounds((screenWidth-1280)/2, (screenHeight-1024)/2, 1280, 1024);
					  
				tempEquipment.getAll(this.listSPs);										  
				tempNetwork.getAll(this.listSPs);
				tempDataStorage.getAll(this.listSPs);
				tempBug.getAll(this.listBugsIntoDatabase);			
				
				if(this.MainPanel == null)
				{
					System.out.println("Creating new MainWindow()");
					this.MainPanel = new MainWindow(this.listSPs);	
	
					System.out.println("Screen size width :"+screenWidth);
					System.out.println("Screen size height:"+screenHeight);					
				}
				
				this.MainPanel.AddStrategicPointButton.addActionListener(this);
				this.MainPanel.deleteButton.addActionListener(this);
				this.MainPanel.manageUserButton.addActionListener(this);
				this.MainPanel.previousButton.addActionListener(this);
				this.MainPanel.nextButton.addActionListener(this);
				this.MainPanel.detailButton.addActionListener(this);
	
				this.MainPanel.displaySPs(this.listSPs,this.MainPanel.getIndexDisplay());
				
				this.setContentPane(this.MainPanel);
				this.validate();
				
				// delay initiated to 60 seconds to launch  a new Strategic Points detection					
				this.timer = new Timer(60000, null);  
				this.timer .setRepeats(true);			
				
				this.timer.start();	
				this.timer.addActionListener(this);				
			}
			else
			{
				JOptionPane.showMessageDialog(this.LoginPanel,"Veuillez entrez un nom d'utilisateur et/ou mot de passe valide","Erreur de connexion",JOptionPane.ERROR_MESSAGE);			
			}
		}
		
		
		/**
		 * Event which displays AddPointStrategic window
		 * @param e : Action when click on  AddItem button from MainWindow
		 */
		if(e.getSource() == this.MainPanel.AddStrategicPointButton) 
		{
			
			this.addElementPanel = new AddElement();				

			addElementPanel.radioButtonEquipment.addActionListener(this);
			addElementPanel.radioButtonNetwork.addActionListener(this);
			addElementPanel.radioButtonDataStorage.addActionListener(this);
			addElementPanel.btnReturn.addActionListener(this);
			addElementPanel.btnValidate.addActionListener(this);
			
			addElementPanel.radioButtonEquipment.setSelected(true);			
			
			this.addElementPanel.getComboBoxType().setVisible(true);
			this.addElementPanel.getTxtIpAddress2().setVisible(false);
			this.addElementPanel.getTxtDataStorage().setVisible(false);

			this.getContentPane().removeAll();
			this.setContentPane(this.addElementPanel);
			this.validate();			
		} 
		
		/**
		 * Event which displays AddPointStrategic window
		 */
		if(e.getSource() == this.addElementPanel.radioButtonEquipment) 
		{			
			this.addElementPanel.getComboBoxType().setVisible(true);
			this.addElementPanel.getTxtIpAddress2().setVisible(false);
			this.addElementPanel.getTxtDataStorage().setVisible(false);

		} 
		
		if(e.getSource() == this.addElementPanel.radioButtonNetwork) 
		{				
			this.addElementPanel.getComboBoxType().setVisible(false);
			this.addElementPanel.getTxtIpAddress2().setVisible(true);
			this.addElementPanel.getTxtDataStorage().setVisible(false);
		}
		
		if(e.getSource() == this.addElementPanel.radioButtonDataStorage) 
		{				
			this.addElementPanel.getComboBoxType().setVisible(false);
			this.addElementPanel.getTxtIpAddress2().setVisible(false);
			this.addElementPanel.getTxtDataStorage().setVisible(true);
		}
		
		/**
		 * Event from ManageAccount panel or AddElement panel
		 * @param e : Action when click on each return button present on some panel
		 * @version 1.01 
		 * 
		 */
		
		if(e.getSource() == this.addElementPanel.btnReturn || e.getSource() == this.manageUserPanel.buttonReturn || e.getSource() == this.detailPanel.buttonReturn) 
		{	
			
			// In the ManageUser panel's case, an email is send automatically to new account activated
			if(e.getSource() == this.manageUserPanel.buttonReturn)
			{
				boolean mailSent = false;
		
				if(listUsers.size()>0)
				{
					for(int i=0;i<listUsers.size();i++)				
					{
						
						mailSent = false;
						if(listUsers.get(i).isAccountEnable() == 1 && listUsers.get(i).isMailReceived() == 0)
						{
							System.out.println("Before sendmail ()Name :"+listUsers.get(i).getUsername());
							mailSent = listUsers.get(i).sendActivationMail();							
							
							if(mailSent == true)
							{
								JOptionPane.showMessageDialog(this.manageUserPanel,"L'email de confirmation de création de compte a été envoyé à "+listUsers.get(i).getUsername(),"Mail envoyé",JOptionPane.INFORMATION_MESSAGE);
								listUsers.get(i).setMailReceived((byte)1);
								listUsers.get(i).updateAccount();	
							}
							else
							{
								JOptionPane.showMessageDialog(this.manageUserPanel,"Une erreur a été rencontrée lors de l'envoi du mail à "+listUsers.get(i).getUsername(),"Problème d'envoi du mail sur la création de compte",JOptionPane.ERROR_MESSAGE);	
							}
						}				
					}					
				}				
			}
			
			if(e.getSource() == this.detailPanel.buttonReturn)
			{
				if(this.detailPanel.getTxtFieldName().isVisible() == true)   // It means that all TextFields presents on DetailPanel are visible
				{
					System.out.println("ID du modifié : "+this.detailPanel.getLabelIDHidden().getText());
					for(int i=0;i<this.listSPs.size();i++)
					{
						if(this.detailPanel.getLabelIDHidden().getText().equals(String.valueOf(listSPs.get(i).getID())))
						{
							listSPs.get(i).setName(this.detailPanel.getTxtFieldName().getText());
							listSPs.get(i).setDescription(this.detailPanel.getTextFieldDescription().getText());
							listSPs.get(i).setIPAddress(this.detailPanel.getTextFieldIPAddress().getText());
							listSPs.get(i).getService().setName(this.detailPanel.getTxtFieldName().getText());
							
							if(listSPs.get(i) instanceof Equipment)
							{
								((Equipment) listSPs.get(i)).setType(this.detailPanel.getTextFieldDetail().getText());
								((Equipment) listSPs.get(i)).update();								
							}
							
							if(listSPs.get(i) instanceof Network)
							{
								((Network) listSPs.get(i)).setIPAddress2(this.detailPanel.getTextFieldDetail().getText());	
								((Network) listSPs.get(i)).update();
							}
							
							if(listSPs.get(i) instanceof DataStorage)
							{
								((DataStorage) listSPs.get(i)).setUNC(this.detailPanel.getTextFieldDetail().getText());	
								((DataStorage) listSPs.get(i)).update();
							}								
						}
					}
				}
				
			}
			
			this.MainPanel = new MainWindow(this.listSPs);
				
			this.MainPanel.AddStrategicPointButton.addActionListener(this);
			this.MainPanel.deleteButton.addActionListener(this);
			this.MainPanel.manageUserButton.addActionListener(this);
			this.MainPanel.previousButton.addActionListener(this);
			this.MainPanel.nextButton.addActionListener(this);
			this.MainPanel.detailButton.addActionListener(this);
			
			this.getContentPane().removeAll();		
			
			this.setBounds((screenWidth-1280)/2, (screenHeight-1024)/2, 1280, 1024);
	
			this.MainPanel.displaySPs(listSPs,this.MainPanel.getIndexDisplay());
			
			this.setContentPane(this.MainPanel);
			this.validate();
		}
		
		/**
		 * 
		 */
		if(e.getSource() == this.addElementPanel.btnValidate) 
		{	
			StrategicPoint newSP = null;			

			if(this.addElementPanel.radioButtonEquipment.isSelected())
			{
				newSP = new Equipment();
			}
			
			if(this.addElementPanel.radioButtonNetwork.isSelected())
			{
				newSP = new Network();
			}
			
			if(this.addElementPanel.radioButtonDataStorage.isSelected())
			{
				newSP = new DataStorage();
			}
						
			newSP.setName(this.addElementPanel.getTxtSPName().getText());
			newSP.setDescription(this.addElementPanel.getTxtDescription().getText());
			newSP.setLocation(null);
			
			newSP.setIPAddress(this.addElementPanel.getTxtIpAddress1().getText());

			newSP.getService().setName(this.addElementPanel.getComboBoxService().getSelectedItem().toString());

			System.out.println("***\n  Getting info for this new Strategic Point");
			System.out.println("name -->"+this.addElementPanel.getTxtSPName().getText());
			System.out.println("description -->"+this.addElementPanel.getTxtDescription().getText());
			System.out.println("location -->"+newSP.getLocation());
			System.out.println("icon -->"+newSP.getIcon());
			System.out.println("IPAddress -->"+this.addElementPanel.getTxtIpAddress1().getText());
			System.out.println("IDService -->"+newSP.getService().getID());
			
			System.out.println("Service:"+this.addElementPanel.getComboBoxService().getSelectedItem().toString());
	
			if(this.addElementPanel.radioButtonEquipment.isSelected() == true)
			{
				System.out.println("type -->"+this.addElementPanel.getComboBoxType().getSelectedItem().toString());
				((Equipment) newSP).setType(this.addElementPanel.getComboBoxType().getSelectedItem().toString());
				((Equipment) newSP).setIcon(newSP.convertImageToBase64(((Equipment) newSP).getType()));
			}
	
			if(this.addElementPanel.radioButtonNetwork.isSelected() == true)
			{
				System.out.println("IPAddress2 -->"+this.addElementPanel.getTxtIpAddress2().getText());	
				((Network) newSP).setIPAddress2(this.addElementPanel.getTxtIpAddress2().getText());
				((Network) newSP).setIcon(((Network) newSP).convertImageToBase64("Network"));	
			}

			if(this.addElementPanel.radioButtonDataStorage.isSelected() == true)
			{
				System.out.println("UNC -->"+this.addElementPanel.getTxtDataStorage().getText());
				((DataStorage) newSP).setUNC(this.addElementPanel.getTxtDataStorage().getText());
				((DataStorage) newSP).setIcon(((DataStorage) newSP).convertImageToBase64("dataStorage"));
			}
	
		
			int confirmation = newSP.add();
			
			if(confirmation == 0)
			{
				JOptionPane.showMessageDialog(this.addElementPanel,"Le nouveau point stratégique a été enregistré avec succès","Enregistrement terminé",JOptionPane.INFORMATION_MESSAGE);	
			}	
		}
		
		/**
		 * Event for deleting Strategic point
		 * @param e : Action when click on Main panel's deleteButton 
		 */
		
		if(e.getSource() == this.MainPanel.deleteButton) 
		{
			ArrayList<StrategicPoint> tempListSP = new ArrayList<StrategicPoint>();
			
			String messageDetails = "";
			boolean SPChecked = false;			
			
			
			if(this.MainPanel.getCheckBox_1().isSelected() == true)
			{
				tempListSP.add(listSPs.get(0+this.MainPanel.getIndexDisplay()));
				messageDetails=messageDetails+listSPs.get(0+this.MainPanel.getIndexDisplay()).getName()+"\n";
				SPChecked = true;
			}
			if(this.MainPanel.getCheckBox_2().isSelected() == true)
			{
				tempListSP.add(listSPs.get(1+this.MainPanel.getIndexDisplay()));
				messageDetails=messageDetails+listSPs.get(1+this.MainPanel.getIndexDisplay()).getName()+"\n";
				SPChecked = true;
			}
			if(this.MainPanel.getCheckBox_3().isSelected() == true)
			{
				tempListSP.add(listSPs.get(2+this.MainPanel.getIndexDisplay()));
				messageDetails=messageDetails+listSPs.get(2+this.MainPanel.getIndexDisplay()).getName()+"\n";
				SPChecked = true;
			}
			if(this.MainPanel.getCheckBox_4().isSelected() == true)
			{
				tempListSP.add(listSPs.get(3+this.MainPanel.getIndexDisplay()));
				messageDetails=messageDetails+listSPs.get(3+this.MainPanel.getIndexDisplay()).getName()+"\n";
				SPChecked = true;
			}
			if(this.MainPanel.getCheckBox_5().isSelected() == true)
			{
				tempListSP.add(listSPs.get(4+this.MainPanel.getIndexDisplay()));
				messageDetails=messageDetails+listSPs.get(4+this.MainPanel.getIndexDisplay()).getName()+"\n";
				SPChecked = true;
			}
			if(this.MainPanel.getCheckBox_6().isSelected() == true)
			{
				tempListSP.add(listSPs.get(5+this.MainPanel.getIndexDisplay()));
				messageDetails=messageDetails+listSPs.get(5+this.MainPanel.getIndexDisplay()).getName()+"\n";
				SPChecked = true;
			}
			if(this.MainPanel.getCheckBox_7().isSelected() == true)
			{
				tempListSP.add(listSPs.get(6+this.MainPanel.getIndexDisplay()));
				messageDetails=messageDetails+listSPs.get(6+this.MainPanel.getIndexDisplay()).getName()+"\n";
				SPChecked = true;
			}
			
			if(SPChecked == true)
			{
				int valueReturned = JOptionPane.showConfirmDialog(this,"Voulez-vous supprimer ce point stratégique ?\n"+messageDetails,"Confirmation de suppression",JOptionPane.YES_NO_OPTION);
				if(valueReturned == JOptionPane.OK_OPTION)
				{
					for(int i=0;i<tempListSP.size();i++)
					{
						System.out.println("Supression de l'élément id:"+tempListSP.get(i).getID());							
						
						for(int j=0;j<listSPs.size();j++)
						{
							if(tempListSP.get(i).getID() == listSPs.get(j+this.MainPanel.getIndexDisplay()).getID())
							{
								listSPs.remove(j+this.MainPanel.getIndexDisplay());
							}
						}
						
						tempListSP.get(i).delete();	
						this.MainPanel.getCheckBox_1().setSelected(false);
						this.MainPanel.getCheckBox_2().setSelected(false);
						this.MainPanel.getCheckBox_3().setSelected(false);
						this.MainPanel.getCheckBox_4().setSelected(false);
						this.MainPanel.getCheckBox_5().setSelected(false);
						this.MainPanel.getCheckBox_6().setSelected(false);
						this.MainPanel.getCheckBox_7().setSelected(false);		
					}
					
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Aucun point stratégique n'a été sélectionné","Choisir un point stratégique",JOptionPane.INFORMATION_MESSAGE);
			}

			this.MainPanel.displaySPs(listSPs,this.MainPanel.getIndexDisplay());
			
		}
		/**
		 * Event for ManageAccount panel
		 * @param e : Action when click on Main panel's connectButton 
		 */
		
		if(e.getSource() == this.MainPanel.manageUserButton) 
		{			
			this.manageUserPanel = new ManageAccount();
			User tempUser = new User();
			
			this.setBounds(800, 600, 450, 300);
			this.setLocationRelativeTo(null);
			
			this.manageUserPanel.buttonOK.addActionListener(this);
			this.manageUserPanel.buttonReturn.addActionListener(this);
			this.manageUserPanel.getcomboBoxUser().addActionListener(this);
			
			listUsers = new ArrayList<User>();	
			
			tempUser.getAllUsers(listUsers);

			if(listUsers.size()>0)
			{
				for(int i=0;i<listUsers.size();i++)				
				{
					this.manageUserPanel.getcomboBoxUser().addItem(listUsers.get(i).getUsername());						
				}
				
				if(listUsers.get(0).isAccountEnable() == 1)
				{
					this.manageUserPanel.setCheckboxAccountState(true);						
				}
				else
				{
					this.manageUserPanel.setCheckboxAccountState(false);	
				}
			}

			this.getContentPane().removeAll();					
			
			this.setContentPane(this.manageUserPanel);
			this.validate();			
		}
		
		
		if(e.getSource() == this.manageUserPanel.getcomboBoxUser()) 
		{
			
			for(int i=0;i<listUsers.size();i++)				
			{
				if(this.manageUserPanel.getcomboBoxUser().getSelectedItem().toString() == listUsers.get(i).getUsername())
				{
					if(listUsers.get(i).isAccountEnable() == 1)
					{
						this.manageUserPanel.setCheckboxAccountState(true);						
					}
					else
					{
						this.manageUserPanel.setCheckboxAccountState(false);	
					}					
				}										
			}							
		}
		
		
		if(e.getSource() == this.manageUserPanel.buttonOK) 
		{
			
			int stateUpdate = 1;
			int i = 0;
			
			for(i=0;i<listUsers.size();i++)				
			{
				if(this.manageUserPanel.getcomboBoxUser().getSelectedItem().toString() == listUsers.get(i).getUsername())
				{
					if(this.manageUserPanel.getCheckboxAccountState().isSelected() == false)
					{
						listUsers.get(i).setAccountEnable((byte) 0); 
					}
					else
					{
						listUsers.get(i).setAccountEnable((byte) 1); 
					}
					stateUpdate = listUsers.get(i).updateAccount();
					break;
				}										
			}
			
			if(stateUpdate == 0)
			{
				JOptionPane.showMessageDialog(this.manageUserPanel,"Le compte de "+listUsers.get(i).getUsername()+" a été mis à jour avec succès","Mise à jour terminée",JOptionPane.INFORMATION_MESSAGE);	
			}	
			else
			{
				JOptionPane.showMessageDialog(this.manageUserPanel,"Une erreur a été rencontrée lors de la mise à jour du compte","Erreur lors de la mise à jour",JOptionPane.ERROR_MESSAGE);		
			}
		}
		
		
		/**
		 * Event for Detail panel
		 * @param e : Action when click on Main panel's detailButton 
		 */
		if(e.getSource() == this.MainPanel.detailButton) 
		{
			this.detailPanel = new Detail();	
			boolean SPChecked = false;			
			
			if(this.MainPanel.getCheckBox_1().isSelected() == true)
			{		
				listSPs.get(0+this.MainPanel.getIndexDisplay()).getBugForThisSP();
				this.detailPanel.displayDetail(listSPs.get(0+this.MainPanel.getIndexDisplay()));				
				SPChecked = true;
			}
			if(this.MainPanel.getCheckBox_2().isSelected() == true)
			{
				listSPs.get(1+this.MainPanel.getIndexDisplay()).getBugForThisSP();
				this.detailPanel.displayDetail(listSPs.get(1+this.MainPanel.getIndexDisplay()));	
				SPChecked = true;
			}
			if(this.MainPanel.getCheckBox_3().isSelected() == true)
			{
				listSPs.get(2+this.MainPanel.getIndexDisplay()).getBugForThisSP();
				this.detailPanel.displayDetail(listSPs.get(2+this.MainPanel.getIndexDisplay()));	
				SPChecked = true;
			}
			if(this.MainPanel.getCheckBox_4().isSelected() == true)
			{
				listSPs.get(3+this.MainPanel.getIndexDisplay()).getBugForThisSP();
				this.detailPanel.displayDetail(listSPs.get(3+this.MainPanel.getIndexDisplay()));	
				SPChecked = true;
			}
			if(this.MainPanel.getCheckBox_5().isSelected() == true)
			{
				listSPs.get(4+this.MainPanel.getIndexDisplay()).getBugForThisSP();
				this.detailPanel.displayDetail(listSPs.get(4+this.MainPanel.getIndexDisplay()));	
				SPChecked = true;
			}
			if(this.MainPanel.getCheckBox_6().isSelected() == true)
			{
				listSPs.get(5+this.MainPanel.getIndexDisplay()).getBugForThisSP();
				this.detailPanel.displayDetail(listSPs.get(5+this.MainPanel.getIndexDisplay()));					
				SPChecked = true;
			}
			if(this.MainPanel.getCheckBox_7().isSelected() == true)
			{
				listSPs.get(6+this.MainPanel.getIndexDisplay()).getBugForThisSP();
				this.detailPanel.displayDetail(listSPs.get(6+this.MainPanel.getIndexDisplay()));	
				SPChecked = true;
			}
			
			if(SPChecked == false)
			{
				JOptionPane.showMessageDialog(this,"Aucun point stratégique n'a été sélectionné","Choisir un point stratégique",JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				this.detailPanel.buttonModify.addActionListener(this);
				this.detailPanel.buttonHistoric.addActionListener(this);
				this.detailPanel.buttonReturn.addActionListener(this);

				this.getContentPane().removeAll();
				this.setContentPane(this.detailPanel);
				this.validate();
			}	
		}
		
		/**
		 * Event for historic panel
		 * @param e historic button present on detail panel
		 */
	
		if(e.getSource() == this.detailPanel.buttonHistoric) 
		{
			this.historicPanel.returnButton.addActionListener(this);
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			Date dateLastWeek = new Date();			

			System.out.println("Date in milliseconds: "+date.getTime());
			System.out.println("Corresponds to : "+dateFormat.format(date));
			
			dateLastWeek.setTime((date.getTime()) - Long.parseLong("604800000")); 
			System.out.println("Same date with seven days less  : "+dateLastWeek.getTime());
			System.out.println("Corresponds to  : "+dateFormat2.format(dateLastWeek));
			
			for(int i=0;i <this.listSPs.size();i++)
			{
				if(Integer.parseInt(this.detailPanel.getLabelIDHidden().getText()) == this.listSPs.get(i).getID())
				{
					// By default, historic panel displays the chart of the last week.
					this.historicPanel.displaySPs(this.listSPs.get(i),dateFormat2.format(dateLastWeek), dateFormat.format(date));
				}
			}
			
			this.getContentPane().removeAll();
			this.setContentPane(this.historicPanel);
			this.validate();
		}
		
		if(e.getSource() == this.historicPanel.returnButton) 
		{
			this.detailPanel = new Detail();
			this.detailPanel.buttonModify.addActionListener(this);
			this.detailPanel.buttonHistoric.addActionListener(this);
			this.detailPanel.buttonReturn.addActionListener(this);

			this.getContentPane().removeAll();
			this.setContentPane(this.detailPanel);
			this.validate();			
		}
		
		
		/**
		 * Lister for timer - it will start Strategic point detection
		 */
		
		if (e.getSource() == this.timer && this.timer != null) 
		{
		
			treatment = new DetectionAndDiagnostic(this.listSPs,this.listBugsIntoDatabase,this.MainPanel);	

			
			if(treatment.getState().equals("STARTED") == false )
			{				
				treatment.execute();
			}
			else
			{
				System.out.println("********************* Treatment status "+treatment.getState());
			}
		}			
	}

	/////////////************************    Getters and Setters *************************
	public byte getIdUser() {
		return idUser;
	}

	public void setIdUser(byte idUser) {
		this.idUser = idUser;
	}



	public MainWindow getMainPanel() {
		return MainPanel;
	}



	public void setMainPanel(MainWindow mainPanel) {
		MainPanel = mainPanel;
	}



	public ArrayList<StrategicPoint> getListSPs() {
		return listSPs;
	}



	public void setListSPs(ArrayList<StrategicPoint> listSPs) {
		this.listSPs = listSPs;
	}



	public ArrayList<Bug> getListBugsIntoDatabase() {
		return listBugsIntoDatabase;
	}



	public void setListBugsIntoDatabase(ArrayList<Bug> listBugsIntoDatabase) {
		this.listBugsIntoDatabase = listBugsIntoDatabase;
	}




	

}
