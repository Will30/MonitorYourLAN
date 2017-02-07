package Presentation;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.xml.bind.DatatypeConverter;

import Metier.DataStorage;
import Metier.Equipment;
import Metier.Network;
import Metier.StrategicPoint;

import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.border.BevelBorder;
import java.awt.CardLayout;

/**
 * 
 * @author Cyril or Will30 (GitHub) or Will15 (GitLab)
 * @version 1.00
 * Main class. It consists of strategic points list with associated LED. LED switches on in different colors (green, orange, red or black) if bug is found.
 * Several Buttons are on the right side to manage users, add a new strategic point, or to see more details for a strageic point
 *
 */

public class MainWindow extends JPanel implements ActionListener
{
	public JPanel contentPane;
	
	private JCheckBox checkBox_1;
	private JCheckBox checkBox_2;
	private JCheckBox checkBox_3;
	private JCheckBox checkBox_4;
	private JCheckBox checkBox_5;
	private JCheckBox checkBox_6;
	private JCheckBox checkBox_7;
	
	private JLabel labelIcon1;
	private JLabel labelIcon2;
	private JLabel labelIcon3;
	private JLabel labelIcon4;
	private JLabel labelIcon5;
	private JLabel labelIcon6;
	private JLabel labelIcon7;
	
	private JLabel labelLed1;
	private JLabel labelLed2;
	private JLabel labelLed3;
	private JLabel labelLed4;
	private JLabel labelLed5;
	private JLabel labelLed6;
	private JLabel labelLed7;
	
	private JLabel labelName1;
	
	public JButton previousButton;
	public JButton nextButton;
	public JButton AddStrategicPointButton;
	public JButton manageUserButton;
	public JButton deleteButton;
	public JButton detailButton;
	
	private JPanel panel_11;	
	private JPanel panel_E1;
	private JPanel panel_E11;
	private JPanel panel_E12;
	private JPanel panel_E13;
	private JPanel panel_21;
	private JLabel labelName2;
	private JPanel panel_31;
	private JLabel labelName3;
	private JPanel panel_41;
	private JLabel labelName4;
	private JPanel panel_51;
	private JLabel labelName5;
	private JPanel panel_71;
	private JPanel panel_61;
	private JLabel labelName6;
	private JLabel labelName7;
	private JPanel panel_E14;
	
	
	private JCheckBox checkBoxEquipment;
	private JCheckBox checkBoxNetwork;
	private JCheckBox checkBoxDataStorage;
	
	private ArrayList<StrategicPoint> listSPs;
	private ArrayList<StrategicPoint> filteredList;
	private boolean filterActive = false;
	private int indexDisplay = 0;

	/**
	 * Create the frame.
	 */
	public MainWindow(ArrayList<StrategicPoint> TempListSPs) 
	{
		listSPs = TempListSPs;
		filteredList = new ArrayList<StrategicPoint>();
		
		this.setBorder(new EmptyBorder(5, 5, 5, 5));		
		this.setLayout(new BorderLayout(0, 0));
		
		JPanel northPanel = new JPanel();
		northPanel.setBackground(Color.WHITE);
		this.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel_N1 = new JPanel();
		panel_N1.setBackground(Color.WHITE);
		northPanel.add(panel_N1);
		panel_N1.setLayout(new BoxLayout(panel_N1, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel_111 = new JLabel(new ImageIcon(MainWindow.class.getResource("/images/logo.png")));
		lblNewLabel_111.setHorizontalAlignment(SwingConstants.CENTER);
		panel_N1.add(lblNewLabel_111);
		
		JPanel panel_N2 = new JPanel();
		panel_N2.setBackground(Color.WHITE);
		northPanel.add(panel_N2);
		
		JLabel lblNewLabel_121 = new JLabel(new ImageIcon(MainWindow.class.getResource("/images/title.png")));
		lblNewLabel_121.setForeground(new Color(30, 144, 255));
		lblNewLabel_121.setFont(new Font("Snap ITC", Font.PLAIN, 32));
		panel_N2.add(lblNewLabel_121);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.WHITE);
		this.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(9, 1, 200, 20));

		JPanel panel_0 = new JPanel();
		panel_0.setBackground(Color.WHITE);
		centerPanel.add(panel_0);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		centerPanel.add(panel_1);
		panel_1.setLayout(null);
		
		checkBox_1 = new JCheckBox("");
		checkBox_1.setBounds(150, 20, 20, 20);
		panel_1.add(checkBox_1);
		
		labelIcon1 = new JLabel("");
		labelIcon1.setBounds(200, 0, 100, 60);
		panel_1.add(labelIcon1);
		
		labelLed1 = new JLabel("");
		labelLed1.setBounds(310, 0, 60, 60);
		panel_1.add(labelLed1);
		
		panel_11 = new JPanel();
		panel_11.setBackground(Color.WHITE);
		panel_11.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)));
		panel_11.setBounds(380, 0, 400, 60);
		panel_1.add(panel_11);
		panel_11.setLayout(new CardLayout(0, 0));
		
		labelName1 = new JLabel("New label");
		labelName1.setHorizontalAlignment(SwingConstants.CENTER);
		labelName1.setFont(new Font("Arial", Font.PLAIN, 20));
		panel_11.add(labelName1, "name_438015413587024");
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		centerPanel.add(panel_2);
		panel_2.setLayout(null);
		
		checkBox_2 = new JCheckBox("");
		checkBox_2.setBounds(150, 20, 20, 20);
		panel_2.add(checkBox_2);
		
		labelIcon2 = new JLabel("");
		labelIcon2.setBounds(200, 0, 100, 60);
		panel_2.add(labelIcon2);
		
		labelLed2 = new JLabel("");
		labelLed2.setBounds(310, 0, 60, 60);
		panel_2.add(labelLed2);
		
		panel_21 = new JPanel();
		panel_21.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		panel_21.setBackground(Color.WHITE);
		panel_21.setBounds(380, 0, 400, 60);
		panel_2.add(panel_21);
		panel_21.setLayout(new CardLayout(0, 0));
		
		labelName2 = new JLabel("New label");
		labelName2.setHorizontalAlignment(SwingConstants.CENTER);
		labelName2.setFont(new Font("Arial", Font.PLAIN, 20));
		panel_21.add(labelName2, "name_438143066908768");
				
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		centerPanel.add(panel_3);
		panel_3.setLayout(null);
		
		checkBox_3 = new JCheckBox("");
		checkBox_3.setBounds(150, 20, 20, 20);
		panel_3.add(checkBox_3);
		
		labelIcon3 = new JLabel("");
		labelIcon3.setBounds(200, 0, 100, 60);
		panel_3.add(labelIcon3);
		
		labelLed3 = new JLabel("");
		labelLed3.setBounds(310, 0, 60, 60);
		panel_3.add(labelLed3);
		
		panel_31 = new JPanel();
		panel_31.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		panel_31.setBackground(Color.WHITE);
		panel_31.setBounds(380, 0, 400, 60);
		panel_3.add(panel_31);
		panel_31.setLayout(new CardLayout(0, 0));
		
		labelName3 = new JLabel("New label");
		labelName3.setHorizontalAlignment(SwingConstants.CENTER);
		labelName3.setFont(new Font("Arial", Font.PLAIN, 20));
		panel_31.add(labelName3, "name_438175360810829");

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		centerPanel.add(panel_4);
		panel_4.setLayout(null);
		
		checkBox_4 = new JCheckBox("");
		checkBox_4.setBounds(150, 20, 20, 20);
		panel_4.add(checkBox_4);
		
		labelIcon4 = new JLabel("");
		labelIcon4.setBounds(200, 0, 100, 60);
		panel_4.add(labelIcon4);
		
		labelLed4 = new JLabel("");
		labelLed4.setBounds(310, 0, 60, 60);
		panel_4.add(labelLed4);
		
		panel_41 = new JPanel();
		panel_41.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		panel_41.setBackground(Color.WHITE);
		panel_41.setBounds(380, 0, 400, 60);
		panel_4.add(panel_41);
		panel_41.setLayout(new CardLayout(0, 0));
		
		labelName4 = new JLabel("New label");
		labelName4.setHorizontalAlignment(SwingConstants.CENTER);
		labelName4.setFont(new Font("Arial", Font.PLAIN, 20));
		panel_41.add(labelName4, "name_438224168006789");
			
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		centerPanel.add(panel_5);
		panel_5.setLayout(null);
		
		checkBox_5 = new JCheckBox("");
		checkBox_5.setBounds(150, 20, 20, 20);
		panel_5.add(checkBox_5);
		
		labelIcon5 = new JLabel("");
		labelIcon5.setBounds(200, 0, 100, 60);
		panel_5.add(labelIcon5);
		
		labelLed5 = new JLabel("");
		labelLed5.setBounds(310, 0, 60, 60);
		panel_5.add(labelLed5);
		
		panel_51 = new JPanel();
		panel_51.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		panel_51.setBackground(Color.WHITE);
		panel_51.setBounds(380, 0, 400, 60);
		panel_5.add(panel_51);
		panel_51.setLayout(new CardLayout(0, 0));
		
		labelName5 = new JLabel("New label");
		labelName5.setHorizontalAlignment(SwingConstants.CENTER);
		labelName5.setFont(new Font("Arial", Font.PLAIN, 20));
		panel_51.add(labelName5, "name_438293104849484");
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		centerPanel.add(panel_6);
		panel_6.setLayout(null);
		
		checkBox_6 = new JCheckBox("");
		checkBox_6.setBounds(150, 20, 20, 20);
		panel_6.add(checkBox_6);
		
		labelIcon6 = new JLabel("");
		labelIcon6.setBounds(200, 0, 100, 60);
		panel_6.add(labelIcon6);
		
		labelLed6 = new JLabel("");
		labelLed6.setBounds(310, 0, 60, 60);
		panel_6.add(labelLed6);
		
		panel_61 = new JPanel();
		panel_61.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		panel_61.setBackground(Color.WHITE);
		panel_61.setBounds(380, 0, 400, 60);
		panel_6.add(panel_61);
		panel_61.setLayout(new CardLayout(0, 0));
		
		labelName6 = new JLabel("New label");
		panel_61.add(labelName6, "name_438380528452824");
		labelName6.setHorizontalAlignment(SwingConstants.CENTER);
		labelName6.setFont(new Font("Arial", Font.PLAIN, 20));
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.WHITE);
		centerPanel.add(panel_7);
		panel_7.setLayout(null);
		
		checkBox_7 = new JCheckBox("");
		checkBox_7.setBounds(150, 20, 20, 20);
		panel_7.add(checkBox_7);
		
		labelIcon7 = new JLabel("");
		labelIcon7.setBounds(200, 0, 100, 60);
		panel_7.add(labelIcon7);
		
		labelLed7 = new JLabel("");
		labelLed7.setBounds(310, 0, 60, 60);
		panel_7.add(labelLed7);
		
		panel_71 = new JPanel();
		panel_71.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		panel_71.setBackground(Color.WHITE);
		panel_71.setBounds(380, 0, 400, 60);
		panel_7.add(panel_71);
		panel_71.setLayout(new CardLayout(0, 0));
		
		labelName7 = new JLabel("New label");
		labelName7.setHorizontalAlignment(SwingConstants.CENTER);
		labelName7.setFont(new Font("Arial", Font.PLAIN, 20));
		panel_71.add(labelName7, "name_439394295713719");
		
		JPanel southPanel = new JPanel();
		this.add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panel_S1 = new JPanel();
		panel_S1.setBackground(Color.WHITE);
		FlowLayout fl_panel_S1 = (FlowLayout) panel_S1.getLayout();
		fl_panel_S1.setAlignment(FlowLayout.RIGHT);
		southPanel.add(panel_S1);
		
		previousButton = new JButton(new ImageIcon(MainWindow.class.getResource("/images/previousButton.jpg")));
		panel_S1.add(previousButton);
		previousButton.setVisible(false);

		JPanel panel_S2 = new JPanel();
		panel_S2.setBackground(Color.WHITE);
		FlowLayout fl_panel_S2 = (FlowLayout) panel_S2.getLayout();
		fl_panel_S2.setAlignment(FlowLayout.LEFT);
		southPanel.add(panel_S2);
		
		nextButton = new JButton(new ImageIcon(MainWindow.class.getResource("/images/nextButton.jpg")));
		panel_S2.add(nextButton);
		nextButton.setVisible(false);
		
		JPanel westPanel = new JPanel();
		westPanel.setBackground(Color.WHITE);
		this.add(westPanel, BorderLayout.WEST);
		westPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_W1 = new JPanel();
		panel_W1.setBackground(Color.WHITE);
		westPanel.add(panel_W1, BorderLayout.SOUTH);
		panel_W1.setLayout(new BoxLayout(panel_W1, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_W2 = new JLabel("Filtre: ");
		panel_W1.add(lblNewLabel_W2);
		lblNewLabel_W2.setFont(new Font("Snap ITC", Font.PLAIN, 18));
		
		checkBoxEquipment = new JCheckBox("Equipement");
		checkBoxEquipment.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_W1.add(checkBoxEquipment);
		
		checkBoxNetwork = new JCheckBox("Flux r\u00E9seau");
		checkBoxNetwork.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_W1.add(checkBoxNetwork);
		
		checkBoxDataStorage = new JCheckBox("Stockage de donn\u00E9es");
		checkBoxDataStorage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_W1.add(checkBoxDataStorage);
		
		JPanel eastPanel = new JPanel();
		this.add(eastPanel, BorderLayout.EAST);
		eastPanel.setLayout(new BorderLayout(0, 0));
		
		panel_E1 = new JPanel();
		eastPanel.add(panel_E1, BorderLayout.CENTER);
		panel_E1.setLayout(new GridLayout(4, 0, 0, 0));
		
		panel_E11 = new JPanel();
		panel_E11.setBackground(Color.WHITE);
		panel_E1.add(panel_E11);
		
		AddStrategicPointButton = new JButton(new ImageIcon(MainWindow.class.getResource("/images/addElementButton.png")));
		panel_E11.add(AddStrategicPointButton);
		
		panel_E12 = new JPanel();
		panel_E12.setBackground(Color.WHITE);
		panel_E1.add(panel_E12);
		
		detailButton = new JButton(new ImageIcon(MainWindow.class.getResource("/images/detailsButton.jpg")));
		panel_E12.add(detailButton);
		
		panel_E13 = new JPanel();
		panel_E13.setBackground(Color.WHITE);
		panel_E1.add(panel_E13);
		
		manageUserButton = new JButton(new ImageIcon(MainWindow.class.getResource("/images/addUserButton.png")));
		panel_E13.add(manageUserButton);
		
		panel_E14 = new JPanel();
		panel_E14.setBackground(Color.WHITE);
		panel_E1.add(panel_E14);
		
		
		deleteButton = new JButton(new ImageIcon(MainWindow.class.getResource("/images/deleteButton.png")));
		panel_E14.add(deleteButton);
		
		
		this.previousButton.addActionListener(this);
		this.nextButton.addActionListener(this);
		
		this.checkBoxEquipment.addActionListener(this);
		this.checkBoxNetwork.addActionListener(this);
		this.checkBoxDataStorage.addActionListener(this);
		
	}
	
	/**
	 * Displays all strategic point with associated LED
	 * @param TempListSPs is a strategic point list updated when application starts,
	 * @param compteur allows to see more strategic point (value possible are: 0,7,14,21,etc...). If TempListSPs size is more than seven "previous button" and "next button" appears
	 * 		 
	 * @since 1.00
	 *  
	 */
	public void displaySPs(ArrayList<StrategicPoint> TempListSPs, int compteur)
	{
	
		if(TempListSPs.size()>7)
		{
			this.previousButton.setVisible(true);
			this.nextButton.setVisible(true);
		}
		
		this.labelIcon1.setVisible(false);
		this.labelLed1.setVisible(false);
		this.labelName1.setVisible(false);
		this.checkBox_1.setVisible(false);
		this.panel_11.setVisible(false);
		
		this.labelIcon2.setVisible(false);
		this.labelLed2.setVisible(false);
		this.labelName2.setVisible(false);
		this.checkBox_2.setVisible(false);	
		this.panel_21.setVisible(false);
		
		this.labelIcon3.setVisible(false);
		this.labelLed3.setVisible(false);
		this.labelName3.setVisible(false);
		this.checkBox_3.setVisible(false);
		this.panel_31.setVisible(false);
		
		this.labelIcon4.setVisible(false);
		this.labelLed4.setVisible(false);
		this.labelName4.setVisible(false);
		this.checkBox_4.setVisible(false);
		this.panel_41.setVisible(false);
		
		this.labelIcon5.setVisible(false);
		this.labelLed5.setVisible(false);
		this.labelName5.setVisible(false);
		this.checkBox_5.setVisible(false);
		this.panel_51.setVisible(false);
		
		this.labelIcon6.setVisible(false);
		this.labelLed6.setVisible(false);
		this.labelName6.setVisible(false);
		this.checkBox_6.setVisible(false);
		this.panel_61.setVisible(false);
		
		this.labelIcon7.setVisible(false);
		this.labelLed7.setVisible(false);
		this.labelName7.setVisible(false);
		this.checkBox_7.setVisible(false);
		this.panel_71.setVisible(false);
		
		//Sort by urgency order
		boolean order = false;
		Equipment tempEquipment = new Equipment();
		Network tempNetwork = new Network();
		DataStorage tempDataStorage = new DataStorage();
		
		do
		{
			order = true;
			for(int i=(TempListSPs.size()-1);i>0;i--)
			{
				if(TempListSPs.get(i).getLed().getColor().equals("black"))
				{
					
					
					if(TempListSPs.get(i-1).getLed().getColor().equals("red") || TempListSPs.get(i-1).getLed().getColor().equals("orange") || TempListSPs.get(i-1).getLed().getColor().equals("green"))
					{
							
						if(TempListSPs.get(i) instanceof Equipment)
						{
							tempEquipment = (Equipment) TempListSPs.get(i);
							TempListSPs.set(i, TempListSPs.get(i-1));
							TempListSPs.set(i-1,tempEquipment);
							order = false;
							continue;
						}
						if(TempListSPs.get(i) instanceof Network)
						{
							tempNetwork = (Network) TempListSPs.get(i);
							TempListSPs.set(i, TempListSPs.get(i-1));
							TempListSPs.set(i-1,tempNetwork);
							order = false;
							continue;
						}
						if(TempListSPs.get(i) instanceof DataStorage)
						{
							tempDataStorage = (DataStorage) TempListSPs.get(i);
							TempListSPs.set(i, TempListSPs.get(i-1));
							TempListSPs.set(i-1,tempDataStorage);
							order = false;
							continue;
						}											
						
					}					
				}		
				
				if(TempListSPs.get(i).getLed().getColor().equals("red"))
				{								
					if(TempListSPs.get(i-1).getLed().getColor().equals("orange") || TempListSPs.get(i-1).getLed().getColor().equals("green"))
					{
							
						if(TempListSPs.get(i) instanceof Equipment)
						{
							tempEquipment = (Equipment) TempListSPs.get(i);
							TempListSPs.set(i, TempListSPs.get(i-1));
							TempListSPs.set(i-1,tempEquipment);
							order = false;
							continue;
						}
						if(TempListSPs.get(i) instanceof Network)
						{
							tempNetwork = (Network) TempListSPs.get(i);
							TempListSPs.set(i, TempListSPs.get(i-1));
							TempListSPs.set(i-1,tempNetwork);
							order = false;
							continue;
						}
						if(TempListSPs.get(i) instanceof DataStorage)
						{
							tempDataStorage = (DataStorage) TempListSPs.get(i);
							TempListSPs.set(i, TempListSPs.get(i-1));
							TempListSPs.set(i-1,tempDataStorage);
							order = false;
							continue;
						}
						
					}					
				}	
				
				if(TempListSPs.get(i).getLed().getColor().equals("orange"))
				{		
					if(TempListSPs.get(i-1).getLed().getColor().equals("green"))
					{
							
						if(TempListSPs.get(i) instanceof Equipment)
						{
							tempEquipment = (Equipment) TempListSPs.get(i);
							TempListSPs.set(i, TempListSPs.get(i-1));
							TempListSPs.set(i-1,tempEquipment);
							order = false;
							continue;
						}
						if(TempListSPs.get(i) instanceof Network)
						{
							tempNetwork = (Network) TempListSPs.get(i);
							TempListSPs.set(i, TempListSPs.get(i-1));
							TempListSPs.set(i-1,tempNetwork);
							order = false;
							continue;
						}
						
						if(TempListSPs.get(i) instanceof DataStorage)
						{
							tempDataStorage = (DataStorage) TempListSPs.get(i);
							TempListSPs.set(i, TempListSPs.get(i-1));
							TempListSPs.set(i-1,tempDataStorage);
							order = false;
							continue;
						}
						
					
					}					
				}
				
			}
			
		}while(order == false);
		
				
		if(TempListSPs.size()>0+indexDisplay)
		{			
			this.labelIcon1.setVisible(true);
			this.labelLed1.setVisible(true);
			this.labelName1.setVisible(true);
			this.checkBox_1.setVisible(true);
			this.panel_11.setVisible(true);
			
			this.setLabelIcon1(new ImageIcon(DatatypeConverter.parseBase64Binary(TempListSPs.get(0+indexDisplay).getIcon())));		
			this.setLabelName1(TempListSPs.get(0+indexDisplay).getName());
			this.setLabelLed1(new ImageIcon(MainWindow.class.getResource(TempListSPs.get(0+indexDisplay).getLed().getUNC())));			
		}
		
		if(TempListSPs.size()>1+indexDisplay)
		{
			this.labelIcon2.setVisible(true);
			this.labelLed2.setVisible(true);
			this.labelName2.setVisible(true);
			this.checkBox_2.setVisible(true);
			this.panel_21.setVisible(true);

			this.setLabelIcon2(new ImageIcon(DatatypeConverter.parseBase64Binary(TempListSPs.get(1+indexDisplay).getIcon())));
			this.setLabelLed2(new ImageIcon(MainWindow.class.getResource(TempListSPs.get(1+indexDisplay).getLed().getUNC())));
			this.setLabelName2(TempListSPs.get(1+indexDisplay).getName());
		}
		
		if(TempListSPs.size()>2+indexDisplay)
		{
			this.labelIcon3.setVisible(true);
			this.labelLed3.setVisible(true);
			this.labelName3.setVisible(true);
			this.checkBox_3.setVisible(true);
			this.panel_31.setVisible(true);
			
			this.setLabelIcon3(new ImageIcon(DatatypeConverter.parseBase64Binary(TempListSPs.get(2+indexDisplay).getIcon())));
			this.setLabelLed3(new ImageIcon(MainWindow.class.getResource(TempListSPs.get(2+indexDisplay).getLed().getUNC())));
			this.setLabelName3(TempListSPs.get(2+indexDisplay).getName());
		}
		
		if(TempListSPs.size()>3+indexDisplay)
		{
			this.labelIcon4.setVisible(true);
			this.labelLed4.setVisible(true);
			this.labelName4.setVisible(true);
			this.checkBox_4.setVisible(true);
			this.panel_41.setVisible(true);
			
			this.setLabelIcon4(new ImageIcon(DatatypeConverter.parseBase64Binary(TempListSPs.get(3+indexDisplay).getIcon())));
			this.setLabelLed4(new ImageIcon(MainWindow.class.getResource(TempListSPs.get(3+indexDisplay).getLed().getUNC())));
			this.setLabelName4(TempListSPs.get(3+indexDisplay).getName());
		}
		
		if(TempListSPs.size()>4+indexDisplay)
		{
			this.labelIcon5.setVisible(true);
			this.labelLed5.setVisible(true);
			this.labelName5.setVisible(true);
			this.checkBox_5.setVisible(true);
			this.panel_51.setVisible(true);
			
			this.setLabelIcon5(new ImageIcon(DatatypeConverter.parseBase64Binary(TempListSPs.get(4+indexDisplay).getIcon())));
			this.setLabelLed5(new ImageIcon(MainWindow.class.getResource(TempListSPs.get(4+indexDisplay).getLed().getUNC())));
			this.setLabelName5(TempListSPs.get(4+indexDisplay).getName());
		}
		
		if(TempListSPs.size()>5+indexDisplay)
		{
			this.labelIcon6.setVisible(true);
			this.labelLed6.setVisible(true);
			this.labelName6.setVisible(true);
			this.checkBox_6.setVisible(true);
			this.panel_61.setVisible(true);
			
			this.setLabelIcon6(new ImageIcon(DatatypeConverter.parseBase64Binary(TempListSPs.get(5+indexDisplay).getIcon())));
			this.setLabelLed6(new ImageIcon(MainWindow.class.getResource(TempListSPs.get(5+indexDisplay).getLed().getUNC())));
			this.setLabelName6(TempListSPs.get(5+indexDisplay).getName());
		}
		
		if(TempListSPs.size()>6+indexDisplay)
		{
			this.labelIcon7.setVisible(true);
			this.labelLed7.setVisible(true);
			this.labelName7.setVisible(true);
			this.checkBox_7.setVisible(true);
			this.panel_71.setVisible(true);
			
			this.setLabelIcon7(new ImageIcon(DatatypeConverter.parseBase64Binary(TempListSPs.get(6+indexDisplay).getIcon())));
			this.setLabelLed7(new ImageIcon(MainWindow.class.getResource(TempListSPs.get(6+indexDisplay).getLed().getUNC())));
			this.setLabelName7(TempListSPs.get(6+indexDisplay).getName());
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		if(e.getSource() == this.previousButton) 
		{	
			if(this.filterActive == false)
			{
				if(this.indexDisplay-7<=-6)
				{
					JOptionPane.showMessageDialog(this,"Début de liste atteinte","Information",JOptionPane.INFORMATION_MESSAGE);	
				}
				else
				{
					this.indexDisplay=this.indexDisplay-7;
					if(this.indexDisplay<=0)
					{
						this.indexDisplay=0;
					}
					this.displaySPs(this.listSPs,this.indexDisplay);
				}
			}
			else
			{
				if(this.indexDisplay-7<=-6)
				{
					JOptionPane.showMessageDialog(this,"Début de liste atteinte","Information",JOptionPane.INFORMATION_MESSAGE);	
				}
				else
				{
					this.indexDisplay=this.indexDisplay-7;
					if(this.indexDisplay<=0)
					{
						this.indexDisplay=0;
					}
					this.displaySPs(this.filteredList,this.indexDisplay);
				}
				
			}
		}
		
		/**
		 * Event for MainWindow
		 * @param e : Action when click on NextButton to modify list display
		 */
		if(e.getSource() == this.nextButton) 
		{	
			if(this.filterActive == false)
			{
				if(this.indexDisplay+7>listSPs.size())
				{
					JOptionPane.showMessageDialog(this,"Fin de la liste atteinte","Information",JOptionPane.INFORMATION_MESSAGE);	
				}
				else
				{
					this.indexDisplay=this.indexDisplay+7;
					this.displaySPs(this.listSPs,this.indexDisplay);
				}				
			}
			else
			{
				if(this.indexDisplay+7>this.filteredList.size())
				{
					JOptionPane.showMessageDialog(this,"Fin de la liste atteinte","Information",JOptionPane.INFORMATION_MESSAGE);	
				}
				else
				{
					this.indexDisplay=this.indexDisplay+7;
					this.displaySPs(this.filteredList,this.indexDisplay);
				}
				
			}
			
		}
		
		if(e.getSource() == this.checkBoxEquipment) 
		{
			
			if(this.checkBoxEquipment.isSelected() == true)
			{
				this.filterActive = true;
				if(this.checkBoxDataStorage.isSelected() == false && this.checkBoxNetwork.isSelected()  == false)
				{
					this.filteredList.removeAll(this.filteredList);
				}
				
				for(int i=0; i<this.listSPs.size();i++)
				{
					if(this.listSPs.get(i) instanceof Equipment)
					{
						this.filteredList.add(this.listSPs.get(i));
					}
				}	
				this.displaySPs(this.filteredList,this.indexDisplay);
			}
			else
			{
				if(this.checkBoxDataStorage.isSelected() == true || this.checkBoxNetwork.isSelected()  == true)
				{
					
						for(int i=0; i<this.filteredList.size();i++)
						{
							if(this.filteredList.get(i) instanceof Equipment)
							{
								this.filteredList.remove(i);
								i--;
							}						
						}
					
					if(this.filteredList.size()<7)
					{
						this.indexDisplay = 0;
					}
					this.displaySPs(this.filteredList,this.indexDisplay);
				}
				else
				{
					this.filterActive = false;
					this.displaySPs(this.listSPs,this.indexDisplay);	
				}							
			}
		}
		
		if(e.getSource() == this.checkBoxNetwork) 
		{
		
			if(this.checkBoxNetwork.isSelected() == true)
			{
				this.filterActive = true;
				if(this.checkBoxEquipment.isSelected() == false && this.checkBoxDataStorage.isSelected()  == false)
				{
					this.filteredList.removeAll(this.filteredList);
				}				
				
				for(int i=0; i<this.listSPs.size();i++)
				{
					if(this.listSPs.get(i) instanceof Network)
					{
						this.filteredList.add(this.listSPs.get(i));
					}
				}	
				this.displaySPs(this.filteredList,this.indexDisplay);
			}
			else
			{
				if(this.checkBoxEquipment.isSelected() == true || this.checkBoxDataStorage.isSelected()  == true)
				{
					for(int i=0; i<this.filteredList.size();i++)
						{
							if(this.filteredList.get(i) instanceof Network)
							{
								this.filteredList.remove(i);
								i--;
							}						
						}
					
					if(this.filteredList.size()<7)
					{
						this.indexDisplay = 0;
					}
					this.displaySPs(this.filteredList,this.indexDisplay);
				}
				else
				{
					this.filterActive = false;
					this.displaySPs(this.listSPs,this.indexDisplay);	
				}
			}
		}
		
		if(e.getSource() == this.checkBoxDataStorage) 
		{
	
			if(this.checkBoxDataStorage.isSelected() == true)
			{
				this.filterActive = true;
				if(this.checkBoxEquipment.isSelected() == false && this.checkBoxNetwork.isSelected()  == false)
				{
					this.filteredList.removeAll(this.filteredList);
				}
				
				for(int i=0; i<this.listSPs.size();i++)
				{
					if(this.listSPs.get(i) instanceof DataStorage)
					{
						this.filteredList.add(this.listSPs.get(i));
					}
				}	
				this.displaySPs(this.filteredList,this.indexDisplay);
			}
			else
			{
				if(this.checkBoxEquipment.isSelected() == true || this.checkBoxNetwork.isSelected()  == true)
				{
					
					for(int i=0; i<this.filteredList.size();i++)
					{
						if(this.filteredList.get(i) instanceof DataStorage)
						{
							this.filteredList.remove(i);
							i--;
						}						
					}
				
					if(this.filteredList.size()<7)
					{
						this.indexDisplay = 0;
					}
					this.displaySPs(this.filteredList,this.indexDisplay);
				}
				else
				{
					this.filterActive = false;
					this.displaySPs(this.listSPs,this.indexDisplay);	
				}
			}
		}
		
	}
	
	
	/////////////************************    Getters and Setters *************************
	
	public void setLabelIcon1(ImageIcon labelIcon1) {
		this.labelIcon1.setIcon(labelIcon1); 
	}

	public void setLabelIcon2(ImageIcon labelIcon2) {
		this.labelIcon2.setIcon(labelIcon2); 
	}

	public void setLabelIcon3(ImageIcon labelIcon3) {
		this.labelIcon3.setIcon(labelIcon3); 
	}

	public void setLabelIcon4(ImageIcon labelIcon4) {
		this.labelIcon4.setIcon(labelIcon4); 
	}

	public void setLabelIcon5(ImageIcon labelIcon5) {
		this.labelIcon5.setIcon(labelIcon5); 
	}

	public void setLabelIcon6(ImageIcon labelIcon6) {
		this.labelIcon6.setIcon(labelIcon6); 
	}

	public void setLabelIcon7(ImageIcon labelIcon7) {
		this.labelIcon7.setIcon(labelIcon7); 
	}

	public void setLabelLed1(ImageIcon labelLed1) {
		this.labelLed1.setIcon(labelLed1); 
	}

	public void setLabelLed2(ImageIcon labelLed2) {
		this.labelLed2.setIcon(labelLed2);
	}

	public void setLabelLed3(ImageIcon labelLed3) {
		this.labelLed3.setIcon(labelLed3);
	}

	public void setLabelLed4(ImageIcon labelLed4) {
		this.labelLed4.setIcon(labelLed4);
	}

	public void setLabelLed5(ImageIcon labelLed5) {
		this.labelLed5.setIcon(labelLed5);
	}

	public void setLabelLed6(ImageIcon labelLed6) {
		this.labelLed6.setIcon(labelLed6);
	}

	public void setLabelLed7(ImageIcon labelLed7) {
		this.labelLed7.setIcon(labelLed7);
	}

	public void setLabelName1(String labelName1) {
		this.labelName1.setText(labelName1);
	}

	public void setLabelName2(String labelName2) {
		this.labelName2.setText(labelName2);
	}

	public void setLabelName3(String labelName3) {
		this.labelName3.setText(labelName3);
	}

	public void setLabelName4(String labelName4) {
		this.labelName4.setText(labelName4);
	}

	public void setLabelName5(String labelName5) {
		this.labelName5.setText(labelName5);
	}

	public void setLabelName6(String labelName6) {
		this.labelName6.setText(labelName6);
	}

	public void setLabelName7(String labelName7) {
		this.labelName7.setText(labelName7);
	}

	public JCheckBox getCheckBox_1() {
		return checkBox_1;
	}

	public void setCheckBox_1(JCheckBox checkBox_1) {
		this.checkBox_1 = checkBox_1;
	}

	public JCheckBox getCheckBox_2() {
		return checkBox_2;
	}

	public void setCheckBox_2(JCheckBox checkBox_2) {
		this.checkBox_2 = checkBox_2;
	}

	public JCheckBox getCheckBox_3() {
		return checkBox_3;
	}

	public void setCheckBox_3(JCheckBox checkBox_3) {
		this.checkBox_3 = checkBox_3;
	}

	public JCheckBox getCheckBox_4() {
		return checkBox_4;
	}

	public void setCheckBox_4(JCheckBox checkBox_4) {
		this.checkBox_4 = checkBox_4;
	}

	public JCheckBox getCheckBox_5() {
		return checkBox_5;
	}

	public void setCheckBox_5(JCheckBox checkBox_5) {
		this.checkBox_5 = checkBox_5;
	}

	public JCheckBox getCheckBox_6() {
		return checkBox_6;
	}

	public void setCheckBox_6(JCheckBox checkBox_6) {
		this.checkBox_6 = checkBox_6;
	}

	public JCheckBox getCheckBox_7() {
		return checkBox_7;
	}

	public void setCheckBox_7(JCheckBox checkBox_7) {
		this.checkBox_7 = checkBox_7;
	}

	public int getIndexDisplay() {
		return indexDisplay;
	}

	public void setIndexDisplay(int indexDisplay) {
		this.indexDisplay = indexDisplay;
	}
}
