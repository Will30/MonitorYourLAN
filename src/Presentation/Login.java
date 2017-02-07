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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

/**
 * 
 * @author Cyril or Will30 (GitHub) or Will15 (GitLab)
 * @version 1.00
 * First class started by controller. Identification is needed to launch application, in such case, user have to save a new account, 
 	though registration panel
 *
 */
public class Login extends JPanel
{

	public JPanel contentPane;
	public JButton registerButton;
	public JButton connectButton;
	
	private JPasswordField password;
	private JTextField username;
	

	/**
	 * Create the panel.
	 */
	public Login() 
	{
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
		
		JLabel label_1 = new JLabel("Nom d'utilisateur :");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(200, 20, 120, 30);
		panel_2.add(label_1);
		
		username = new JTextField();
		username.setFont(new Font("Arial", Font.ITALIC, 18));
		username.setColumns(10);
		username.setBounds(350, 20, 250, 30);
		panel_2.add(username);
				
		JPanel panel_3 = new JPanel();
		centerPanel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel label = new JLabel("Mot de passe :");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(200, 20, 120, 30);
		panel_3.add(label);
		
		password = new JPasswordField();
		password.setBounds(350, 20, 250, 30);
		panel_3.add(password);

		JPanel panel_4 = new JPanel();
		centerPanel.add(panel_4);
		panel_4.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		centerPanel.add(panel_5);
		panel_5.setLayout(null);
		
		JPanel southPanel = new JPanel();
		this.add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panel_9 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_9.getLayout();
		southPanel.add(panel_9);
		
		connectButton = new JButton("Valider");
		connectButton.setMnemonic(KeyEvent.VK_ENTER);
		panel_9.add(connectButton);
		
		JPanel panel_10 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_10.getLayout();
		southPanel.add(panel_10);
		
		registerButton = new JButton("Creer un compte");
		panel_10.add(registerButton);
		
		
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

	/////////////************************    Getters and Setters *************************
	public JTextField getPassword() {
		return password;
	}


	public void setPassword(JPasswordField password) {
		this.password = password;
	}


	public JTextField getUsername() {
		return username;
	}


	public void setUsername(JTextField username) {
		this.username = username;
	}
	}
	
	

