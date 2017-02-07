package Presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;


/**
 * 
 * @author Cyril or Will30 (GitHub) or Will15 (GitLab)
 * @version 1.00
 * This class allows to activate new account. When user create a new account, administrator must activate it. When it done,
 * an automatic mail is sent with his credentials.
 *
 */
public class ManageAccount extends JPanel {


public JPanel contentPane;
	public JButton buttonOK;
	public JButton buttonReturn;
	private JComboBox comboBoxUser;	
	private JCheckBox checkboxAccountState;
	
	

	/**
	 * Create the panel.
	 */
	public ManageAccount() 
	{
		//MouseAdapter ms = new MouseAdapter();
		
		this.setBorder(new EmptyBorder(5, 5, 5, 5));		
		this.setLayout(new BorderLayout(0, 0));
		
		JPanel northPanel = new JPanel();
		this.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel_11 = new JPanel();
		northPanel.add(panel_11);
		panel_11.setLayout(new BoxLayout(panel_11, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel_111 = new JLabel(new ImageIcon(MainWindow.class.getResource("/images/logo.png")));
		lblNewLabel_111.setHorizontalAlignment(SwingConstants.CENTER);
		panel_11.add(lblNewLabel_111);
		
		JPanel panel_12 = new JPanel();
		northPanel.add(panel_12);
		
		JLabel lblNewLabel_121 = new JLabel("MonitorYourLAN");
		lblNewLabel_121.setForeground(new Color(30, 144, 255));
		lblNewLabel_121.setFont(new Font("Snap ITC", Font.PLAIN, 25));
		panel_12.add(lblNewLabel_121);
		
		JPanel centerPanel = new JPanel();
		this.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(5, 1, 200, 20));
		
		JPanel panel_1 = new JPanel();
		centerPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		centerPanel.add(panel_2);
		panel_2.setLayout(null);
		
		comboBoxUser = new JComboBox();
		comboBoxUser.setBounds(275, 20, 250, 30);
		panel_2.add(comboBoxUser);
		
				
		JPanel panel_3 = new JPanel();
		centerPanel.add(panel_3);
		panel_3.setLayout(null);

		JPanel panel_4 = new JPanel();
		centerPanel.add(panel_4);
		panel_4.setLayout(null);
		
		checkboxAccountState = new JCheckBox("Actif");
		checkboxAccountState.setFont(new Font("Arial", Font.PLAIN, 14));
		checkboxAccountState.setBounds(370, 20, 97, 23);
		panel_4.add(checkboxAccountState);
		
		JPanel panel_5 = new JPanel();
		centerPanel.add(panel_5);
		panel_5.setLayout(null);
		
		JPanel southPanel = new JPanel();
		this.add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panel_9 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_9.getLayout();
		southPanel.add(panel_9);
		
		buttonReturn = new JButton("Retour");
		panel_9.add(buttonReturn);
		
		JPanel panel_10 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_10.getLayout();
		southPanel.add(panel_10);
		
		buttonOK = new JButton("Valider");
		panel_10.add(buttonOK);
		buttonOK.setMnemonic(KeyEvent.VK_ENTER);
		
		JPanel westPanel = new JPanel();
		this.add(westPanel, BorderLayout.WEST);
		westPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		westPanel.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel eastPanel = new JPanel();
		this.add(eastPanel, BorderLayout.EAST);
		eastPanel.setLayout(new GridLayout(3, 0, 0, 0));

	}

	public void displayInfo()
	{
		
	}

	/////////////************************    Getters and Setters *************************

	public JComboBox getcomboBoxUser() {
		return comboBoxUser;
	}

	public void setCcomboBoxUser(JComboBox comboBoxUser) {
		this.comboBoxUser = comboBoxUser;
	}

	public JCheckBox getCheckboxAccountState() {
		return checkboxAccountState;
	}

	public void setCheckboxAccountState(boolean checkboxAccountState) {
		this.checkboxAccountState.setSelected(checkboxAccountState); 
	}
	
}
	
	

