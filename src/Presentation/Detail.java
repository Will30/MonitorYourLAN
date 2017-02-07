package Presentation;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;


import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

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
 * Class display detail for only one strategic point. It allows to see all details, it's possible to modify info if you need  
 *
 */
public class Detail extends JPanel implements ActionListener{


	public JPanel contentPane;
	
	private JLabel labelLed;
	private JLabel labelDescription;
	private JLabel labelDetail;
	private JLabel labelIPAddress;
	private JLabel labelName;
	private JLabel labelService;
	private JLabel labelBug;
	private JLabel labelSolution;
	private JLabel labelIDHidden;
	
	private JPanel panel_11;	
	private JPanel panel_E1;
	private JPanel panel_E11;
	private JPanel panel_E12;
	private JPanel panel_E13;
	private JPanel panel_21;
	private JPanel panel_31;
	private JPanel panel_41;
	private JPanel panel_51;
	private JPanel panel_71;
	private JPanel panel_61;

	
	private JPanel panel_W2;
	private JPanel panel_W3;

	private JPanel panel_W31;
	private JPanel panel_W32;
	private JPanel panel_E131;
	private JPanel panel_E132;

	public JButton buttonModify;
	public JButton buttonHistoric;
	public JButton buttonReturn;
	private JTextField txtFieldName;
	private JTextField textFieldDescription;
	private JTextField textFieldIPAddress;
	private JTextField textFieldDetail;
	private JTextField textFieldService;
	
	protected StrategicPoint tempSP;


	/**
	 * Create the frame.
	 */
	public Detail() 
	{
		
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
		
		labelIDHidden = new JLabel("");
		panel_0.add(labelIDHidden);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		centerPanel.add(panel_1);
		panel_1.setLayout(null);
		
		labelLed = new JLabel("New label");
		labelLed.setBounds(300, 0, 60, 60);
		panel_1.add(labelLed);
		
		panel_11 = new JPanel();
		panel_11.setBackground(Color.WHITE);
		panel_11.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		panel_11.setBounds(370, 0, 330, 60);
		panel_1.add(panel_11);
		panel_11.setLayout(new CardLayout(0, 0));
		
		labelName = new JLabel("New label");
		labelName.setHorizontalAlignment(SwingConstants.CENTER);
		labelName.setFont(new Font("Arial", Font.PLAIN, 20));
		panel_11.add(labelName, "name_438015413587024");
		
		txtFieldName = new JTextField();
		txtFieldName.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldName.setFont(new Font("Arial", Font.PLAIN, 20));
		txtFieldName.setText("test cach\u00E9");
		panel_11.add(txtFieldName, "name_2780041957168080");
		txtFieldName.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		centerPanel.add(panel_2);
		panel_2.setLayout(null);
		
		panel_21 = new JPanel();
		panel_21.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		panel_21.setBackground(Color.WHITE);
		panel_21.setBounds(300, 0, 400, 60);
		panel_2.add(panel_21);
		panel_21.setLayout(new CardLayout(0, 0));
		
		labelDescription = new JLabel("New label");
		labelDescription.setHorizontalAlignment(SwingConstants.CENTER);
		labelDescription.setFont(new Font("Arial", Font.PLAIN, 20));
		panel_21.add(labelDescription, "name_438143066908768");
		
		textFieldDescription = new JTextField();
		textFieldDescription.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldDescription.setFont(new Font("Arial", Font.PLAIN, 20));
		panel_21.add(textFieldDescription, "name_2780294721391796");
		textFieldDescription.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		centerPanel.add(panel_3);
		panel_3.setLayout(null);
		
		panel_41 = new JPanel();
		panel_41.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		panel_41.setBackground(Color.WHITE);
		panel_41.setBounds(300, 0, 400, 60);
		panel_3.add(panel_41);
		panel_41.setLayout(new CardLayout(0, 0));
		
		labelIPAddress = new JLabel("New label");
		labelIPAddress.setHorizontalAlignment(SwingConstants.CENTER);
		labelIPAddress.setFont(new Font("Arial", Font.PLAIN, 20));
		panel_41.add(labelIPAddress, "name_438224168006789");
		
		textFieldIPAddress = new JTextField();
		textFieldIPAddress.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldIPAddress.setFont(new Font("Arial", Font.PLAIN, 20));
		textFieldIPAddress.setColumns(10);
		panel_41.add(textFieldIPAddress, "name_2780333749232940");
				
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		centerPanel.add(panel_4);
		panel_4.setLayout(null);
		
		panel_31 = new JPanel();
		panel_31.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		panel_31.setBackground(Color.WHITE);
		panel_31.setBounds(300, 0, 400, 60);
		panel_4.add(panel_31);
		panel_31.setLayout(new CardLayout(0, 0));
		
		labelDetail = new JLabel("New label");
		labelDetail.setHorizontalAlignment(SwingConstants.CENTER);
		labelDetail.setFont(new Font("Arial", Font.PLAIN, 20));
		panel_31.add(labelDetail, "name_438175360810829");
		
		textFieldDetail = new JTextField();
		textFieldDetail.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldDetail.setFont(new Font("Arial", Font.PLAIN, 20));
		textFieldDetail.setColumns(10);
		panel_31.add(textFieldDetail, "name_2780358109036488");
			
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		centerPanel.add(panel_5);
		panel_5.setLayout(null);
		
		panel_51 = new JPanel();
		panel_51.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		panel_51.setBackground(Color.WHITE);
		panel_51.setBounds(300, 0, 400, 60);
		panel_5.add(panel_51);
		panel_51.setLayout(new CardLayout(0, 0));
		
		labelService = new JLabel("New label");
		labelService.setHorizontalAlignment(SwingConstants.CENTER);
		labelService.setFont(new Font("Arial", Font.PLAIN, 20));
		panel_51.add(labelService, "name_438293104849484");
		
		textFieldService = new JTextField();
		textFieldService.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldService.setFont(new Font("Arial", Font.PLAIN, 20));
		textFieldService.setColumns(10);
		panel_51.add(textFieldService, "name_2780401584346427");
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		centerPanel.add(panel_6);
		panel_6.setLayout(null);
		
		panel_61 = new JPanel();
		panel_61.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		panel_61.setBackground(Color.WHITE);
		panel_61.setBounds(300, 0, 400, 60);
		panel_6.add(panel_61);
		panel_61.setLayout(new CardLayout(0, 0));
		
		labelBug = new JLabel("New label");
		panel_61.add(labelBug, "name_438380528452824");
		labelBug.setHorizontalAlignment(SwingConstants.CENTER);
		labelBug.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.WHITE);
		centerPanel.add(panel_7);
		panel_7.setLayout(null);
		
		panel_71 = new JPanel();
		panel_71.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		panel_71.setBackground(Color.WHITE);
		panel_71.setBounds(300, 0, 400, 60);
		panel_7.add(panel_71);
		panel_71.setLayout(new CardLayout(0, 0));
		
		labelSolution = new JLabel("New label");
		labelSolution.setHorizontalAlignment(SwingConstants.CENTER);
		labelSolution.setFont(new Font("Arial", Font.PLAIN, 14));
		panel_71.add(labelSolution, "name_439394295713719");
		
		JPanel southPanel = new JPanel();
		this.add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panel_S1 = new JPanel();
		panel_S1.setBackground(Color.WHITE);
		FlowLayout fl_panel_S1 = (FlowLayout) panel_S1.getLayout();
		fl_panel_S1.setAlignment(FlowLayout.RIGHT);
		southPanel.add(panel_S1);
		
		
		JPanel panel_S2 = new JPanel();
		panel_S2.setBackground(Color.WHITE);
		FlowLayout fl_panel_S2 = (FlowLayout) panel_S2.getLayout();
		fl_panel_S2.setAlignment(FlowLayout.LEFT);
		southPanel.add(panel_S2);
		
		JPanel westPanel = new JPanel();
		westPanel.setBackground(Color.WHITE);
		this.add(westPanel, BorderLayout.WEST);
		westPanel.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_W1 = new JPanel();
		panel_W1.setBackground(Color.WHITE);
		westPanel.add(panel_W1);
		panel_W1.setLayout(new BoxLayout(panel_W1, BoxLayout.Y_AXIS));
		
		panel_W2 = new JPanel();
		panel_W2.setBackground(Color.WHITE);
		westPanel.add(panel_W2);
		
		panel_W3 = new JPanel();
		westPanel.add(panel_W3);
		panel_W3.setLayout(new GridLayout(0, 1, 0, 0));
		
		panel_W31 = new JPanel();
		panel_W31.setBackground(Color.WHITE);
		panel_W3.add(panel_W31);
		panel_W31.setLayout(new CardLayout(20, 50));
		
		buttonModify = new JButton("Modifier");
		panel_W31.add(buttonModify, "name_7789031362744");
		buttonModify.setAlignmentY(0.0f);
		
		panel_W32 = new JPanel();
		panel_W32.setBackground(Color.WHITE);
		panel_W3.add(panel_W32);
		panel_W32.setLayout(new CardLayout(20, 50));
		
		buttonHistoric = new JButton("Voir Historique");
		panel_W32.add(buttonHistoric, "name_7808695070550");
		buttonHistoric.setAlignmentY(150.0f);
		
		JPanel eastPanel = new JPanel();
		this.add(eastPanel, BorderLayout.EAST);
		eastPanel.setLayout(new BorderLayout(0, 0));
		
		panel_E1 = new JPanel();
		eastPanel.add(panel_E1, BorderLayout.CENTER);
		panel_E1.setLayout(new GridLayout(3, 0, 0, 0));
		
		panel_E11 = new JPanel();
		panel_E11.setBackground(Color.WHITE);
		panel_E1.add(panel_E11);
		
		panel_E12 = new JPanel();
		panel_E12.setBackground(Color.WHITE);
		panel_E1.add(panel_E12);
		
		panel_E13 = new JPanel();
		panel_E13.setBackground(Color.WHITE);
		panel_E1.add(panel_E13);
		panel_E13.setLayout(new GridLayout(0, 1, 0, 0));
		
		panel_E131 = new JPanel();
		panel_E131.setBackground(Color.WHITE);
		panel_E13.add(panel_E131);
		panel_E131.setLayout(new CardLayout(20, 50));
		
		panel_E132 = new JPanel();
		panel_E132.setBackground(Color.WHITE);
		panel_E13.add(panel_E132);
		panel_E132.setLayout(new CardLayout(20, 50));
		
		buttonReturn = new JButton("Retour");
		panel_E132.add(buttonReturn, "name_8011034768512");
		buttonReturn.setAlignmentY(0.0f);
		
		this.labelIDHidden.setVisible(false);
		
		this.txtFieldName.setVisible(false);
		this.textFieldDescription.setVisible(false);
		this.textFieldIPAddress.setVisible(false);
		this.textFieldDetail.setVisible(false);
		this.textFieldService.setVisible(false);
		
		this.buttonModify.addActionListener(this);
		
	}
	
	/**
	 * Displays info about one strategic point
	 * @param SP is a strategic point from Main Window
	 * @since 1.00
	 *  
	 */
	public void displayDetail(StrategicPoint thisSP)
	{
		tempSP = thisSP;
		this.labelIDHidden.setText(String.valueOf(tempSP.getID()));
		
		this.setLabelLed(new ImageIcon(MainWindow.class.getResource(thisSP.getLed().getUNC())));
		this.setLabelName(thisSP.getName());
		this.setLabelDescription(thisSP.getDescription());
		this.setLabelIPAddress(thisSP.getIPAddress());
		
		this.setLabelService(thisSP.getService().getName());
		
		if(thisSP.getListBug().isEmpty() == false)
		{
			this.setLabelBug(thisSP.getListBug().get(0).getDetail());
			this.setLabelSolution(thisSP.getListBug().get(0).getListSolution().get(0).getDescription());
		}
		else
		{
			this.setLabelBug("");
			this.setLabelSolution("");
			this.getLabelBug().setVisible(false);
			this.getLabelSolution().setVisible(false);
		}
		
		if(thisSP instanceof Equipment)
		{
			this.setLabelDetail(((Equipment) thisSP).getType());
		}
		
		if(thisSP instanceof Network)
		{
			this.setLabelDetail(((Network) thisSP).getIPAddress2());
		}
		
		if(thisSP instanceof DataStorage)
		{
			this.setLabelDetail(((DataStorage) thisSP).getUNC());
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		/**
		 * Modify field into panel. When click on modify button, label becomes textfield for modifying
		 * @param action event for modify button
		 * @since 1.00
		 *  
		 */
		if(e.getSource() == this.buttonModify) 
		{	
			this.labelName.setVisible(false);
			this.labelDescription.setVisible(false);
			this.labelIPAddress.setVisible(false);
			this.labelDetail.setVisible(false);
			this.labelService.setVisible(false);	

			this.txtFieldName.setText(this.labelName.getText());
			this.textFieldDescription.setText(this.labelDescription.getText());
			this.textFieldIPAddress.setText(this.labelIPAddress.getText());
			this.textFieldDetail.setText(this.labelDetail.getText());
			this.textFieldService.setText(this.labelService.getText());			
			
			this.txtFieldName.setVisible(true);
			this.textFieldDescription.setVisible(true);
			this.textFieldIPAddress.setVisible(true);
			this.textFieldDetail.setVisible(true);
			this.textFieldService.setVisible(true);
		}
		

	}
	
	
	/////////////************************    Getters and Setters *************************

	public JLabel getLabelLed() {
		return labelLed;
	}

	public void setLabelLed(ImageIcon labelLed) {
		this.labelLed.setIcon(labelLed);
	}

	public JLabel getLabelDescription() {
		return labelDescription;
	}

	public void setLabelDescription(String labelDescription) {
		this.labelDescription.setText(labelDescription);
	}

	public JLabel getLabelType() {
		return labelDetail;
	}

	public void setLabelType(String labelType) {
		this.labelDetail.setText(labelType);
	}

	public JLabel getLabelIPAddress() {
		return labelIPAddress;
	}

	public void setLabelIPAddress(String labelIPAddress) {
		this.labelIPAddress.setText(labelIPAddress);
	}

	public JLabel getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName.setText(labelName);
	}

	public JLabel getLabelService() {
		return labelService;
	}

	public void setLabelService(String labelService) {		
		this.labelService.setText(labelService);
	}

	public JLabel getLabelBug() {
		return labelBug;
	}

	public void setLabelBug(String labelBug) {
		this.labelBug.setText(labelBug);
	}

	public JLabel getLabelSolution() {
		return labelSolution;
	}

	public void setLabelSolution(String labelSolution) {
		this.labelSolution.setText(labelSolution);
	}


	public JLabel getLabelDetail() {
		return labelDetail;
	}

	public void setLabelDetail(String labelDetail) {
		this.labelDetail.setText(labelDetail); 
		}

	public JTextField getTxtFieldName() {
		return txtFieldName;
	}

	public void setTxtFieldName(JTextField txtFieldName) {
		this.txtFieldName = txtFieldName;
	}

	public JTextField getTextFieldDescription() {
		return textFieldDescription;
	}

	public void setTextFieldDescription(JTextField textFieldDescription) {
		this.textFieldDescription = textFieldDescription;
	}

	public JTextField getTextFieldIPAddress() {
		return textFieldIPAddress;
	}

	public void setTextFieldIPAddress(JTextField textFieldIPAddress) {
		this.textFieldIPAddress = textFieldIPAddress;
	}

	public JTextField getTextFieldDetail() {
		return textFieldDetail;
	}

	public void setTextFieldDetail(JTextField textFieldDetail) {
		this.textFieldDetail = textFieldDetail;
	}

	public JTextField getTextFieldService() {
		return textFieldService;
	}

	public void setTextFieldService(JTextField textFieldService) {
		this.textFieldService = textFieldService;
	}

	public JLabel getLabelIDHidden() {
		return labelIDHidden;
	}

	public void setLabelIDHidden(JLabel labelIDHidden) {
		this.labelIDHidden = labelIDHidden;
	}
}
