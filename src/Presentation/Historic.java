package Presentation;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import Metier.Bug;
import Metier.DateLabelFormatter;
import Metier.StrategicPoint;

import javax.swing.border.CompoundBorder;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

/**
 * 
 * @author Cyril or Will30 (GitHub) or Will15 (GitLab)
 * @version 1.00
 * Class consists of chart which describe info for only one strategic point. It allows to define a diagnostic about it.  
 *
 */

public class Historic extends JPanel implements ActionListener
{
	public JPanel contentPane;
	
	public JButton returnButton;
	
	private JPanel panel_E1;
	private JPanel panel_E11;
	private JPanel panel_E12;
	private JPanel panel_E13;
	private JPanel panel_E131;
	private JPanel panel_E132;
	private JPanel panel_C1;
	private JPanel panel_C2;
	private JPanel panel_C11;
	private JPanel panel_C11_1;
	private JPanel panel_C12;
	private JPanel panel_C21;
	private JPanel panel_C211;
	private JPanel panel_C212;
	private JLabel labelDate1;
	private JLabel labelError1;
	
	
	private JLabel labelPeriode;
	private JLabel labelInitialDate;
	private JLabel labelEndDate;
	private JPanel panel_C22;
	private JPanel panel_C221;
	private JPanel panel_C222;
	private JLabel labelDate2;
	private JLabel labelError2;
	private JPanel panel_C23;
	private JPanel panel_C231;
	private JPanel panel_C232;
	private JPanel panel_C24;
	private JPanel panel_C241;
	private JPanel panel_C242;
	private JPanel panel_C25;
	private JPanel panel_C251;
	private JPanel panel_C252;
	private JPanel panel_C26;
	private JPanel panel_C261;
	private JPanel panel_C262;
	private JPanel panel_C27;
	private JPanel panel_C271;
	private JPanel panel_C272;
	private JLabel labelDate3;
	private JLabel labelDate4;
	private JLabel labelDate5;
	private JLabel labelDate6;
	private JLabel labelDate7;
	private JLabel labelError3;
	private JLabel labelError4;
	private JLabel labelError5;
	private JLabel labelError6;
	private JLabel labelError7;
	private JButton updateButton;
	
	protected JTextField textFieldInitialDate;
	protected JTextField textFieldEndDate;
	private JDatePickerImpl datePicker1;
	private JDatePickerImpl datePicker2;
	private StrategicPoint thisSP;
	
	// For graphic
	private TimeSeries timeSeries = new TimeSeries("Sample 1");
	

	/**
	 * Create the frame.
	 */
	public Historic() 
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
		
		JLabel labelLogoEnterprise = new JLabel(new ImageIcon(MainWindow.class.getResource("/images/logo.png")));
		panel_N1.add(labelLogoEnterprise);
		
		JPanel panel_N2 = new JPanel();
		panel_N2.setBackground(Color.WHITE);
		northPanel.add(panel_N2);
		
		JLabel labelLogoApplication = new JLabel(new ImageIcon(MainWindow.class.getResource("/images/title.png")));
		panel_N2.add(labelLogoApplication);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.WHITE);
		this.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		panel_C1 = new JPanel();
		centerPanel.add(panel_C1);
		panel_C1.setLayout(null);
		
		panel_C11 = new JPanel();
		panel_C11.setBorder(null);
		panel_C11.setBackground(Color.WHITE);
		panel_C11.setBounds(10, 10, 900, 400);
		panel_C11_1 = paintPanel();
		panel_C11_1.setLocation(20, 20);
		panel_C11_1.setSize(850, 400);
		panel_C1.add(panel_C11_1);
		
		panel_C12 = new JPanel();
		panel_C12.setBackground(Color.WHITE);
		panel_C12.setBounds(950, 10, 200, 400);

		
		panel_C1.add(panel_C12);
		panel_C12.setLayout(new GridLayout(5, 0, 0, 0));
		
		JPanel panel_C121 = new JPanel();
		panel_C12.add(panel_C121);
		
		JPanel panel_C122 = new JPanel();
		panel_C122.setBorder(new MatteBorder(3, 3, 0, 3, (Color) new Color(0, 0, 0)));
		panel_C122.setBackground(Color.WHITE);
		panel_C12.add(panel_C122);
		panel_C122.setLayout(new BorderLayout(0, 0));
		
		labelPeriode = new JLabel("P\u00E9riode :");
		panel_C122.add(labelPeriode);
		labelPeriode.setHorizontalAlignment(SwingConstants.CENTER);
		labelPeriode.setFont(new Font("Arial", Font.PLAIN, 26));
		
		JPanel panel_C123 = new JPanel();
		panel_C123.setBorder(new MatteBorder(0, 3, 0, 3, (Color) new Color(0, 0, 0)));
		panel_C123.setBackground(Color.WHITE);
		panel_C12.add(panel_C123);
		panel_C123.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		labelInitialDate = new JLabel("D\u00E9but : ");
		panel_C123.add(labelInitialDate);
		labelInitialDate.setFont(new Font("Arial", Font.PLAIN, 20));

		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker1 = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker1.setBorder(new MatteBorder(0, 3, 0, 3, (Color) new Color(0, 0, 0)));
		datePicker1.getJFormattedTextField().setHorizontalAlignment(SwingConstants.CENTER);
		datePicker1.setToolTipText("Cliquer pour changer la date");
		datePicker1.getJFormattedTextField().setBackground(Color.WHITE);
		datePicker1.setBackground(Color.WHITE);
		datePicker1.getJFormattedTextField().setFont(new Font("Arial", Font.PLAIN, 14));
		
		panel_C123.add(datePicker1);

		JPanel panel_C124 = new JPanel();
		panel_C124.setBorder(new MatteBorder(0, 3, 3, 3, (Color) new Color(0, 0, 0)));
		panel_C124.setBackground(Color.WHITE);
		panel_C12.add(panel_C124);
		panel_C124.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		labelEndDate = new JLabel("    Fin :     ");
		panel_C124.add(labelEndDate);
		labelEndDate.setFont(new Font("Arial", Font.PLAIN, 20));

		UtilDateModel model2 = new UtilDateModel();
		Properties p2 = new Properties();
		p2.put("text.today", "Today");
		p2.put("text.month", "Month");
		p2.put("text.year", "Year");
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p2);
		datePicker2 = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
		datePicker2.setBorder(new MatteBorder(0, 3, 0, 3, (Color) new Color(0, 0, 0)));
		datePicker2.getJFormattedTextField().setFont(new Font("Arial", Font.PLAIN, 14));
		datePicker2.getJFormattedTextField().setHorizontalAlignment(SwingConstants.CENTER);
		datePicker2.setBackground(Color.WHITE);
		datePicker2.setToolTipText("Cliquer pour changer la date");
		datePicker2.getJFormattedTextField().setBackground(Color.WHITE);
		panel_C124.add(datePicker2);

		
		JPanel panel_C125 = new JPanel();
		panel_C12.add(panel_C125);
		
		updateButton = new JButton(new ImageIcon(Historic.class.getResource("/images/updateButton.png")));
		updateButton.setToolTipText("Actualiser les donn\u00E9es");
		panel_C125.add(updateButton);
		
		panel_C2 = new JPanel();
		centerPanel.add(panel_C2);
		panel_C2.setLayout(new GridLayout(7, 1, 10, 0));
		
		panel_C21 = new JPanel();
		panel_C2.add(panel_C21);
		panel_C21.setBorder(null);
		panel_C21.setBackground(Color.WHITE);
		panel_C21.setLayout(null);
		
		panel_C211 = new JPanel();
		panel_C211.setBounds(10, 10, 200, 50);
		panel_C211.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		panel_C21.add(panel_C211);
		panel_C211.setLayout(new CardLayout(0, 0));
		
		labelDate1 = new JLabel("New label");
		labelDate1.setHorizontalAlignment(SwingConstants.CENTER);
		labelDate1.setFont(new Font("Arial", Font.PLAIN, 20));
		panel_C211.add(labelDate1, "name_2847627016584865");
		
		panel_C212 = new JPanel();
		panel_C212.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)), null));
		panel_C212.setBounds(250, 10, 800, 50);
		panel_C21.add(panel_C212);
		panel_C212.setLayout(new CardLayout(0, 0));
		
		labelError1 = new JLabel("New label");
		labelError1.setHorizontalAlignment(SwingConstants.CENTER);
		labelError1.setFont(new Font("Arial", Font.PLAIN, 20));
		panel_C212.add(labelError1, "name_2847635558628097");
		
		panel_C22 = new JPanel();
		panel_C22.setLayout(null);
		panel_C22.setBorder(null);
		panel_C22.setBackground(Color.WHITE);
		panel_C2.add(panel_C22);
		
		panel_C221 = new JPanel();
		panel_C221.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		panel_C221.setBounds(10, 10, 200, 50);
		panel_C22.add(panel_C221);
		panel_C221.setLayout(new CardLayout(0, 0));
		
		labelDate2 = new JLabel("New label");
		labelDate2.setHorizontalAlignment(SwingConstants.CENTER);
		labelDate2.setFont(new Font("Arial", Font.PLAIN, 20));
		panel_C221.add(labelDate2, "name_2847657128914808");
		
		panel_C222 = new JPanel();
		panel_C222.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)), null));
		panel_C222.setBounds(250, 10, 800, 50);
		panel_C22.add(panel_C222);
		panel_C222.setLayout(new CardLayout(0, 0));
		
		labelError2 = new JLabel("New label");
		labelError2.setHorizontalAlignment(SwingConstants.CENTER);
		labelError2.setFont(new Font("Arial", Font.PLAIN, 20));
		panel_C222.add(labelError2, "name_2847661281320443");
		
		panel_C23 = new JPanel();
		panel_C23.setLayout(null);
		panel_C23.setBorder(null);
		panel_C23.setBackground(Color.WHITE);
		panel_C2.add(panel_C23);
		
		panel_C231 = new JPanel();
		panel_C231.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		panel_C231.setBounds(10, 10, 200, 50);
		panel_C23.add(panel_C231);
		panel_C231.setLayout(new CardLayout(0, 0));
		
		labelDate3 = new JLabel("New label");
		labelDate3.setHorizontalAlignment(SwingConstants.CENTER);
		labelDate3.setFont(new Font("Arial", Font.PLAIN, 20));
		panel_C231.add(labelDate3, "name_2847838798187559");
		
		panel_C232 = new JPanel();
		panel_C232.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)), null));
		panel_C232.setBounds(250, 10, 800, 50);
		panel_C23.add(panel_C232);
		panel_C232.setLayout(new CardLayout(0, 0));
		
		labelError3 = new JLabel("New label");
		labelError3.setHorizontalAlignment(SwingConstants.CENTER);
		labelError3.setFont(new Font("Arial", Font.PLAIN, 20));
		panel_C232.add(labelError3, "name_2847857585091861");
		
		panel_C24 = new JPanel();
		panel_C24.setLayout(null);
		panel_C24.setBorder(null);
		panel_C24.setBackground(Color.WHITE);
		panel_C2.add(panel_C24);
		
		panel_C241 = new JPanel();
		panel_C241.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		panel_C241.setBounds(10, 10, 200, 50);
		panel_C24.add(panel_C241);
		panel_C241.setLayout(new CardLayout(0, 0));
		
		labelDate4 = new JLabel("New label");
		labelDate4.setHorizontalAlignment(SwingConstants.CENTER);
		labelDate4.setFont(new Font("Arial", Font.PLAIN, 20));
		panel_C241.add(labelDate4, "name_2847841116141564");
		
		panel_C242 = new JPanel();
		panel_C242.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)), null));
		panel_C242.setBounds(250, 10, 800, 50);
		panel_C24.add(panel_C242);
		panel_C242.setLayout(new CardLayout(0, 0));
		
		labelError4 = new JLabel("New label");
		labelError4.setHorizontalAlignment(SwingConstants.CENTER);
		labelError4.setFont(new Font("Arial", Font.PLAIN, 20));
		panel_C242.add(labelError4, "name_2847859338594474");
		
		panel_C25 = new JPanel();
		panel_C25.setLayout(null);
		panel_C25.setBorder(null);
		panel_C25.setBackground(Color.WHITE);
		panel_C2.add(panel_C25);
		
		panel_C251 = new JPanel();
		panel_C251.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		panel_C251.setBounds(10, 10, 200, 50);
		panel_C25.add(panel_C251);
		panel_C251.setLayout(new CardLayout(0, 0));
		
		labelDate5 = new JLabel("New label");
		labelDate5.setHorizontalAlignment(SwingConstants.CENTER);
		labelDate5.setFont(new Font("Arial", Font.PLAIN, 20));
		panel_C251.add(labelDate5, "name_2847843574017689");
		
		panel_C252 = new JPanel();
		panel_C252.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)), null));
		panel_C252.setBounds(250, 10, 800, 50);
		panel_C25.add(panel_C252);
		panel_C252.setLayout(new CardLayout(0, 0));
		
		labelError5 = new JLabel("New label");
		labelError5.setHorizontalAlignment(SwingConstants.CENTER);
		labelError5.setFont(new Font("Arial", Font.PLAIN, 20));
		panel_C252.add(labelError5, "name_2847862367677811");
		
		panel_C26 = new JPanel();
		panel_C26.setLayout(null);
		panel_C26.setBorder(null);
		panel_C26.setBackground(Color.WHITE);
		panel_C2.add(panel_C26);
		
		panel_C261 = new JPanel();
		panel_C261.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		panel_C261.setBounds(10, 10, 200, 50);
		panel_C26.add(panel_C261);
		panel_C261.setLayout(new CardLayout(0, 0));
		
		labelDate6 = new JLabel("New label");
		labelDate6.setHorizontalAlignment(SwingConstants.CENTER);
		labelDate6.setFont(new Font("Arial", Font.PLAIN, 20));
		panel_C261.add(labelDate6, "name_2847845368281606");
		
		panel_C262 = new JPanel();
		panel_C262.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)), null));
		panel_C262.setBounds(250, 10, 800, 50);
		panel_C26.add(panel_C262);
		panel_C262.setLayout(new CardLayout(0, 0));
		
		labelError6 = new JLabel("New label");
		labelError6.setHorizontalAlignment(SwingConstants.CENTER);
		labelError6.setFont(new Font("Arial", Font.PLAIN, 20));
		panel_C262.add(labelError6, "name_2847864027605959");
		
		panel_C27 = new JPanel();
		panel_C27.setLayout(null);
		panel_C27.setBorder(null);
		panel_C27.setBackground(Color.WHITE);
		panel_C2.add(panel_C27);
		
		panel_C271 = new JPanel();
		panel_C271.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		panel_C271.setBounds(10, 10, 200, 50);
		panel_C27.add(panel_C271);
		panel_C271.setLayout(new CardLayout(0, 0));
		
		labelDate7 = new JLabel("New label");
		labelDate7.setHorizontalAlignment(SwingConstants.CENTER);
		labelDate7.setFont(new Font("Arial", Font.PLAIN, 20));
		panel_C271.add(labelDate7, "name_2847847783699994");
		
		panel_C272 = new JPanel();
		panel_C272.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)), null));
		panel_C272.setBounds(250, 10, 800, 50);
		panel_C27.add(panel_C272);
		panel_C272.setLayout(new CardLayout(0, 0));
		
		labelError7 = new JLabel("New label");
		labelError7.setHorizontalAlignment(SwingConstants.CENTER);
		labelError7.setFont(new Font("Arial", Font.PLAIN, 20));
		panel_C272.add(labelError7, "name_2847866403578549");
		
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
		westPanel.setLayout(new BorderLayout(0, 0));
		
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
		panel_E1.add(panel_E13);
		panel_E13.setLayout(new GridLayout(2, 0, 0, 0));
		
		panel_E131 = new JPanel();
		panel_E131.setBackground(Color.WHITE);
		panel_E13.add(panel_E131);
		
		panel_E132 = new JPanel();
		panel_E132.setBackground(Color.WHITE);
		panel_E132.setForeground(Color.BLACK);
		panel_E13.add(panel_E132);
		panel_E132.setLayout(new CardLayout(20, 50));
		
		returnButton = new JButton("Retour");
		panel_E132.add(returnButton, "name_2842377616465455");
		returnButton.setAlignmentY(0.0f);
		
		updateButton.addActionListener(this);
	}

	/**
	 * Displays historical bug about one strategic point
	 * @param SP is a strategic point from Detail Panel, beginningDate is the first date correspond of the chart begginning
	  		  endDate is the last date correspond of the end of the chart
	 * @since 1.00
	 *  
	 */
	
	public void displaySPs(StrategicPoint SP, String beginningDate, String endDate)
	{
		int indexList = 0;
		Bug tempBug = new Bug();		
		ArrayList<Bug> listBug = new ArrayList<Bug>();
		thisSP = SP;
		
		this.labelDate1.setVisible(false);
		this.labelError1.setVisible(false);
		this.labelDate2.setVisible(false);
		this.labelError2.setVisible(false);
		this.labelDate3.setVisible(false);
		this.labelError3.setVisible(false);
		this.labelDate4.setVisible(false);
		this.labelError4.setVisible(false);
		this.labelDate5.setVisible(false);
		this.labelError5.setVisible(false);
		this.labelDate6.setVisible(false);
		this.labelError6.setVisible(false);
		this.labelDate7.setVisible(false);
		this.labelError7.setVisible(false);

		// By default, historic panel displays chart during one week.
		tempBug.getBugDuringPeriod(listBug,SP.getID(),beginningDate, endDate);
		
		datePicker1.getJFormattedTextField().setText(beginningDate);
		datePicker2.getJFormattedTextField().setText(endDate);
		
		if(listBug.size()>0)
		{			
			this.labelDate1.setVisible(true);
			this.labelError1.setVisible(true);
			this.labelDate1.setText(listBug.get(indexList).getDate().substring(0, 10));
			this.labelError1.setText(listBug.get(indexList).getDetail());			
		}
		
		if(listBug.size()>1)
		{			
			indexList ++;
			this.labelDate2.setVisible(true);
			this.labelError2.setVisible(true);
			this.labelDate2.setText(listBug.get(indexList).getDate().substring(0, 10));
			this.labelError2.setText(listBug.get(indexList).getDetail());			
		}
		
		if(listBug.size()>2)
		{			
			indexList++;
			this.labelDate3.setVisible(true);
			this.labelError3.setVisible(true);
			this.labelDate3.setText(listBug.get(indexList).getDate().substring(0, 10));
			this.labelError3.setText(listBug.get(indexList).getDetail());			
		}
		
		if(listBug.size()>3)
		{			
			indexList++;
			this.labelDate4.setVisible(true);
			this.labelError4.setVisible(true);
			this.labelDate4.setText(listBug.get(indexList).getDate().substring(0, 10));
			this.labelError4.setText(listBug.get(indexList).getDetail());			
		}
		
		if(listBug.size()>4)
		{			
			indexList++;
			this.labelDate5.setVisible(true);
			this.labelError5.setVisible(true);
			this.labelDate5.setText(listBug.get(indexList).getDate().substring(0, 10));
			this.labelError5.setText(listBug.get(indexList).getDetail());			
		}
		
		if(listBug.size()>5)
		{			
			indexList++;
			this.labelDate6.setVisible(true);
			this.labelError6.setVisible(true);
			this.labelDate6.setText(listBug.get(indexList).getDate().substring(0, 10));
			this.labelError6.setText(listBug.get(indexList).getDetail());			
		}
		
		if(listBug.size()>6)
		{		
			indexList++;
			this.labelDate7.setVisible(true);
			this.labelError7.setVisible(true);
			this.labelDate7.setText(listBug.get(indexList).getDate().substring(0, 10));
			this.labelError7.setText(listBug.get(indexList).getDetail());			
		}
		
		// Update the graphics
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
		Long tempBeginningDate;
		Date tempDate = null;
		Date tempEndDate = null ;
		int i = 0;
		int indexDay = 0;
		try 
		{
			tempDate = dateFormat.parse(beginningDate);
			tempEndDate  = dateFormat.parse(endDate);
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		
		System.out.println("Date en milli seconds depuis 1970 -->"+tempDate.getTime());
		tempBeginningDate = tempDate.getTime() ;
		tempDate.setTime(tempDate.getTime() + Long.parseLong("86400000"));
		System.out.println("Date en milli seconds (+1 jour) -->"+tempDate.getTime());

	    do
		{
			tempBeginningDate = tempDate.getTime()  + ( Long.parseLong("86400000")* indexDay );
			System.out.println("Ce qui donne  -->"+dateFormat.format(tempBeginningDate));
			if(i < listBug.size() )
			{
				if(listBug.get(i).getDate().substring(0,4).equals(dateFormat.format(tempBeginningDate).substring(0,4)) && listBug.get(i).getDate().substring(5,7).equals(dateFormat.format(tempBeginningDate).substring(5,7)) && listBug.get(i).getDate().substring(8,10).equals(dateFormat.format(tempBeginningDate).substring(8,10)))
				{
					System.out.println("Date de la liste :"+listBug.get(i).getDate());
					timeSeries.add(new Day(Integer.parseInt(listBug.get(i).getDate().substring(8,10)),Integer.parseInt(listBug.get(i).getDate().substring(5,7)),Integer.parseInt(listBug.get(i).getDate().substring(0,4))), listBug.get(i).getIDcolor());
					i++;
				}
				else
				{
					timeSeries.add(new Day(Integer.parseInt(dateFormat.format(tempBeginningDate).substring(8,10)),Integer.parseInt(dateFormat.format(tempBeginningDate).substring(5,7)),Integer.parseInt(dateFormat.format(tempBeginningDate).substring(0,4))), 4);	// if no bug at this date, we force green color 
				}				
			}
			else
			{
				timeSeries.add(new Day(Integer.parseInt(dateFormat.format(tempBeginningDate).substring(8,10)),Integer.parseInt(dateFormat.format(tempBeginningDate).substring(5,7)),Integer.parseInt(dateFormat.format(tempBeginningDate).substring(0,4))), 4);	// if no bug at this date, we force green color
			}
			
			indexDay++;
		}
		while(dateFormat.format(tempEndDate).equals(dateFormat.format(tempBeginningDate)) == false );

	
	    System.out.println("Historic.DisplaySPS()   Chart updated");
	}



	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == this.updateButton) 
		{
			if(datePicker1.getJFormattedTextField().getText().equals("") == false && datePicker2.getJFormattedTextField().getText().equals("") == false)
			{
				this.displaySPs(thisSP, datePicker1.getJFormattedTextField().getText(), datePicker2.getJFormattedTextField().getText());
			}
			else
			{
				JOptionPane.showMessageDialog(this,"La période sélectionnée comporte des erreurs. \n Vérifiez votre saisie.","Période non définie",JOptionPane.ERROR_MESSAGE);
			}
		}		
	}
	
	public JPanel paintPanel() 
	{		
		String titre = "Historique du point stratégique";
		String titre_x = "Periode";
		String titre_y = "LED";

		XYDataset dataset =  createDataset();
		
		JFreeChart chart = ChartFactory.createTimeSeriesChart(titre, titre_x, titre_y,  dataset);
		return new ChartPanel(chart);
	}

	protected XYDataset createDataset() 
	{
		TimeSeriesCollection dataset = new TimeSeriesCollection();        
		DefaultKeyedValues data = new DefaultKeyedValues();		
		Date date = new Date();

		dataset.addSeries(timeSeries);

		return dataset;	
	}	
}
