package Presentation;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import Metier.Equipment;
import Metier.Service;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

/**
 * 
 * @author Cyril or Will30 (GitHub) or Will15 (GitLab)
 * @version 1.00
 * Class for adding a new strategic point
 *
 */
public class AddElement extends JPanel {
	private JTextField txtSPName;
	private JTextField txtDescription;
	private JComboBox comboBoxService;

	private JTextField txtIpAddress1;
	private JTextField txtIpAddress2;
	
	public JRadioButton radioButtonEquipment ;	
	public JRadioButton radioButtonNetwork;
	public JRadioButton radioButtonDataStorage;
	public ButtonGroup bg;

	private JComboBox comboBoxType;

	public JButton btnValidate;
	public JButton btnReturn;	
	private JTextField txtDataStorage;
	

	/**
	 * Create the panel.
	 */
	public AddElement() 
	{
		Service service = new Service();
		ArrayList<Service> listServices = new ArrayList<Service>();
		service.getAll(listServices);
		
		Equipment equipment = new Equipment();
		ArrayList<Equipment> listTypeAndModel = new ArrayList<Equipment>();
		equipment.getListTypeAndModel(listTypeAndModel);
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorth = new JPanel();
		panel.add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panelNorth.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JLabel labelLogo = new JLabel(new ImageIcon(MainWindow.class.getResource("/images/logo.png")));
		panel_1.add(labelLogo);
		
		JPanel panel_2 = new JPanel();
		panelNorth.add(panel_2);
		
		JLabel label = new JLabel("MonitorYourLAN");
		label.setVerticalAlignment(SwingConstants.TOP);
		panel_2.add(label);
		label.setForeground(new Color(30, 144, 255));
		label.setFont(new Font("Snap ITC", Font.PLAIN, 32));
		
		JPanel panel_3 = new JPanel();
		panelNorth.add(panel_3);
		
		JPanel panelCenter = new JPanel();
		panel.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(null);
		
		txtSPName = new JTextField();
		txtSPName.setFont(new Font("Tahoma", Font.ITALIC, 11));
		txtSPName.setText("Nom du point strat\u00E9gique");
		txtSPName.setBounds(350, 78, 250, 50);
		panelCenter.add(txtSPName);
		txtSPName.setColumns(10);
		
		txtIpAddress1 = new JTextField();
		txtIpAddress1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		txtIpAddress1.setText("Adresse IP");
		txtIpAddress1.setBounds(350, 150, 250, 50);
		panelCenter.add(txtIpAddress1);
		txtIpAddress1.setColumns(10);
		
		comboBoxType = new JComboBox();
		comboBoxType.setToolTipText("");
		comboBoxType.setBounds(350, 250, 250, 50);
		
		for(int i=0;i<listTypeAndModel.size();i++)
		{
			comboBoxType.addItem(listTypeAndModel.get(i).getType());
		}
		
		panelCenter.add(comboBoxType);
		
		txtIpAddress2 = new JTextField();
		txtIpAddress2.setFont(new Font("Tahoma", Font.ITALIC, 11));
		txtIpAddress2.setText("Adresse IP 2");
		txtIpAddress2.setBounds(350, 250, 250, 50);
		panelCenter.add(txtIpAddress2);
		txtIpAddress2.setColumns(10);
		
		comboBoxService = new JComboBox();
		comboBoxService.setBounds(350, 350, 250, 50);

		for(int i=0;i<listServices.size();i++)
		{
			comboBoxService.addItem(listServices.get(i).getName());
		}
		/*comboBoxService.addItem("IT Service");
		comboBoxService.addItem("Finance");
		comboBoxService.addItem("Marketing");*/
		
		txtDataStorage = new JTextField();
		txtDataStorage.setText("Stockage de donn\u00E9e");
		txtDataStorage.setFont(new Font("Tahoma", Font.ITALIC, 11));
		txtDataStorage.setColumns(10);
		txtDataStorage.setBounds(350, 250, 250, 50);
		panelCenter.add(txtDataStorage);
		panelCenter.add(comboBoxService);
		
		txtDescription = new JTextField();
		txtDescription.setFont(new Font("Tahoma", Font.ITALIC, 11));
		txtDescription.setText("Description");
		txtDescription.setBounds(320, 450, 310, 200);
		panelCenter.add(txtDescription);
		txtDescription.setColumns(10);
		
		JPanel panelEast = new JPanel();
		panel.add(panelEast, BorderLayout.EAST);
		panelEast.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_10 = new JPanel();
		panelEast.add(panel_10);
		panel_10.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panelWest = new JPanel();
		panel.add(panelWest, BorderLayout.WEST);
		panelWest.setLayout(new GridLayout(5, 1, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panelWest.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		
		JPanel panel_5 = new JPanel();
		panelWest.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));
		
		JLabel label_1 = new JLabel("Choisir le type :");
		label_1.setVerticalAlignment(SwingConstants.TOP);
		label_1.setFont(new Font("Snap ITC", Font.PLAIN, 20));
		panel_5.add(label_1);
		
		radioButtonEquipment = new JRadioButton("Equipement");
		radioButtonEquipment.setFont(new Font("Snap ITC", Font.PLAIN, 16));
		panel_5.add(radioButtonEquipment);
		
		radioButtonNetwork = new JRadioButton("Flux r\u00E9seau");
		radioButtonNetwork.setFont(new Font("Snap ITC", Font.PLAIN, 16));
		panel_5.add(radioButtonNetwork);
		
		radioButtonDataStorage = new JRadioButton("Stockage de donn\u00E9e");
		radioButtonDataStorage.setFont(new Font("Snap ITC", Font.PLAIN, 16));
		panel_5.add(radioButtonDataStorage);
		
		bg = new ButtonGroup();
		bg.add(radioButtonEquipment);
		bg.add(radioButtonNetwork);
		bg.add(radioButtonDataStorage);		
			
		JPanel panel_6 = new JPanel();
		panelWest.add(panel_6);
		
		JPanel panelSouth = new JPanel();
		panel.add(panelSouth, BorderLayout.SOUTH);
		panelSouth.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_11 = new JPanel();
		panelSouth.add(panel_11);
		
		btnReturn = new JButton(new ImageIcon(AddElement.class.getResource("/images/buttonReturn.jpg")));
		panel_11.add(btnReturn);
		
		JPanel panel_12 = new JPanel();
		panelSouth.add(panel_12);
		
		btnValidate = new JButton(new ImageIcon(MainWindow.class.getResource("/images/buttonOK.jpg")));
		panel_12.add(btnValidate);

	}
	
	/////////////************************    Getters and Setters *************************
	public JTextField getTxtAdresseIp2() {
		return txtIpAddress2;
	}

	public JComboBox getComboBoxType() {
		return comboBoxType;
	}

	public JTextField getTxtSPName() {
		return txtSPName;
	}

	public void setTxtSPName(JTextField txtSPName) {
		this.txtSPName = txtSPName;
	}

	public JTextField getTxtDescription() {
		return txtDescription;
	}

	public void setTxtDescription(JTextField txtDescription) {
		this.txtDescription = txtDescription;
	}

	public JTextField getTxtIpAddress1() {
		return txtIpAddress1;
	}

	public void setTxtIpAddress1(JTextField txtIpAddress1) {
		this.txtIpAddress1 = txtIpAddress1;
	}

	public JTextField getTxtIpAddress2() {
		return txtIpAddress2;
	}

	public void setTxtIpAddress2(JTextField txtIpAddress2) {
		this.txtIpAddress2 = txtIpAddress2;
	}

	public void setComboBoxType(JComboBox comboBoxType) {
		this.comboBoxType = comboBoxType;
	}

	public JComboBox getComboBoxService() {
		return comboBoxService;
	}

	public void setComboBoxService(JComboBox comboBoxService) {
		this.comboBoxService = comboBoxService;
	}

	public JTextField getTxtDataStorage() {
		return txtDataStorage;
	}

	public void setTxtDataStorage(JTextField txtDataStorage) {
		this.txtDataStorage = txtDataStorage;
	}
	
	
	
	

}
