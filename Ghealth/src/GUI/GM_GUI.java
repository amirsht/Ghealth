package GUI;
import client.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import GUI.LoggingOut.LogOutListener;
import client.LoginControl;

import javax.swing.JTextField;

import java.awt.Window.Type;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.JButton;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Label;
import java.awt.Panel;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.SwingConstants;



/**
 * The Class of the general manager windows GUI.
 */
public class GM_GUI extends LoggingOut {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1583234608338407492L;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The lblwarning message. */
	private JLabel lblwarningMessage = null;
	
	/** The btn monthly report. */
	private JButton btnMonthlyReport;
	
	/** The weekly btn. */
	private JButton weeklyBtn;
	
	/** The date picker from. */
	private JDatePickerImpl datePickerFrom;
	
	/** The cal_from. */
	private Panel cal_from;
	
	/** The btn choose date from. */
	private JButton btnChooseDateFrom;
	
	/** The lbl new label from. */
	private JLabel lblNewLabelFrom;
	
	/** The lbl date to. */
	private JLabel lblDateTo;
	
	/** The date picker to. */
	private JDatePickerImpl datePickerTo;
	
	/** The cal_to. */
	private Panel cal_to;
	
	/** The btn choose date to. */
	private JButton btnChooseDateTo;
	
	/** The lbl new label to. */
	private JLabel lblNewLabelTo;
	
	/** The lbl select date from. */
	private JLabel lblSelectDateFrom;
	
	/** The lbl select date to. */
	private JLabel lblSelectDateTo;
	
	/** The separator. */
	private JSeparator separator;
	
	/** The separator_1. */
	private JSeparator separator_1;
	
	/** The separator_2. */
	private JSeparator separator_2;
	
	
	
	/**
	 * Create the frame.
	 */
	public GM_GUI() {
		setResizable(false);
		setTitle("G-Health");
		setIconImage(Toolkit.getDefaultToolkit().getImage(DoctorGUI.class.getResource("/images/logo2.PNG")));
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("New label");
		label.setBounds(0, 0, 0, 0);
		contentPane.add(label);
		JLabel lblLogo;
		if(LoginControl.getUser_full_name() == null)
			lblLogo = new JLabel("Welcome General Manager!");
		else lblLogo = new JLabel("Hi "+LoginControl.getUser_full_name()+"!");
		lblLogo.setIcon(new ImageIcon(DoctorGUI.class.getResource("/images/logo2.png")));
		lblLogo.setBounds(0, 0, 794, 79);
		contentPane.add(lblLogo);
		
		weeklyBtn = new JButton("Show report with this date range");
		
		weeklyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		weeklyBtn.setBounds(237, 352, 240, 35);
		contentPane.add(weeklyBtn);
		
		
		
		JButton LogOut = new JButton("Log Out");
		LogOut.setBounds(617, 493, 100, 30);
		LogOut.addActionListener(new LogOutListener());
		contentPane.add(LogOut);
		
		UtilDateModel model = new UtilDateModel();
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH); 
		int year = calendar.get(Calendar.YEAR); 
		model.setDate(year, month, day);
		model.setSelected(true);
		Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
		
		JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
		
		datePickerFrom = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePickerFrom.setVisible(true);
		cal_from = new Panel();
		cal_from.add(datePickerFrom);
		cal_from.setBounds(33, 302, 278, 33);
		cal_from.setVisible(true);
		contentPane.add(cal_from,BorderLayout.WEST);		
		btnChooseDateFrom = new JButton("OK");
		cal_from.add(btnChooseDateFrom);		

		datePickerTo = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePickerTo.setVisible(true);
		cal_to = new Panel();
		cal_to.add(datePickerTo);
		cal_to.setBounds(439, 302, 278, 33);
		cal_to.setVisible(true);
		contentPane.add(cal_to,BorderLayout.WEST);		
		btnChooseDateTo = new JButton("OK");
		cal_to.add(btnChooseDateTo);		
		
		lblSelectDateFrom = new JLabel("Select date From");
		lblSelectDateFrom.setBounds(33, 262, 278, 33);
		contentPane.add(lblSelectDateFrom);
		
		lblSelectDateTo = new JLabel("Select date To");
		lblSelectDateTo.setBounds(439, 262, 278, 33);
		contentPane.add(lblSelectDateTo);
		
		JComboBox MonthBox = new JComboBox();
		MonthBox.addItem(1);MonthBox.addItem(2);MonthBox.addItem(3);
		MonthBox.addItem(4);MonthBox.addItem(5);MonthBox.addItem(6);
		MonthBox.addItem(7);MonthBox.addItem(8);MonthBox.addItem(9);
		MonthBox.addItem(10);MonthBox.addItem(11);MonthBox.addItem(12);
		MonthBox.setBounds(33, 447, 55, 20);
		contentPane.add(MonthBox);
		
		JLabel lblLastMonthsAmount = new JLabel("Last months amount");
		lblLastMonthsAmount.setBounds(33, 407, 130, 14);
		contentPane.add(lblLastMonthsAmount);
		
		JButton OKmonthBtn = new JButton("Show reports with this number of months back");
		OKmonthBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		OKmonthBtn.setBounds(203, 440, 310, 35);
		contentPane.add(OKmonthBtn);
		
		separator = new JSeparator();
		separator.setBounds(0, 398, 795, 2);
		contentPane.add(separator);
		
		JList<String> list = new JList<String>(new String[] { "Haifa", "Tel-Aviv", "Jerusalem", "Eilat", "Acko", "Karmiel", "Aza" }); //JList list = new JList();//
		list.setToolTipText("Use 'CTRL' to select more than one item");

		list.setBounds(237, 111, 240, 140);
		contentPane.add(list);
		
		JLabel lblNewLabel = new JLabel("Please choose the clinics you want reports on");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(223, 86, 270, 14);
		contentPane.add(lblNewLabel);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(0, 263, 795, 2);
		contentPane.add(separator_1);
		
		separator_2 = new JSeparator();
		separator_2.setBounds(0, 480, 795, 2);
		contentPane.add(separator_2);
		
		setLocationRelativeTo(null);
		
		setVisible(true);
		
	
	}
	
	/**
	 * Sets the warning message visible true.
	 */
	public void setWarningMessageVisibleTrue() {
		lblwarningMessage.setVisible(true);	
	}
	
	/**
	 * Sets the warning message visible true.
	 *
	 * @param st the new warning message visible true
	 */
	public void setWarningMessageVisibleTrue(String st) {
		lblwarningMessage.setText(st);
		lblwarningMessage.setForeground(Color.RED);
		lblwarningMessage.setBounds(10, 165, 245, 30);
		lblwarningMessage.setVisible(true);	
		
	}
	
	
	
	/**
	 * Undisplay warning message.
	 */
	public void undisplayWarningMessage() {
		lblwarningMessage.setVisible(false);
		
	}
	
	
	/**
	 * Weekly action listener.
	 *
	 * @param e the e
	 */
	public void weeklyActionListener(ActionListener e)
	{
		weeklyBtn.addActionListener(e);
	}
	
	/**
	 * Monthly action listener.
	 *
	 * @param e the e
	 */
	public void monthlyActionListener(ActionListener e)
	{
		btnMonthlyReport.addActionListener(e);
	}
	
	/**
	 * Gets the cal.
	 *
	 * @return the cal
	 */
	public Panel getCal() {
		return cal_from;
	}	
	
	/**
	 * Gets the date picker.
	 *
	 * @return the date picker
	 */
	public JDatePickerImpl getDatePicker() {
		return datePickerFrom;
	}	
	
	/**
	 * Gets the choosen date ok.
	 *
	 * @return the choosen date ok
	 */
	public JButton getChoosenDateOK() {
		return btnChooseDateFrom;
	}
	
	

	/**
	* Cancell listener of the button.
	*/
	public class CancelListener implements ActionListener 
    {
    	
	    /** 
	     * closes the current frame of the class
	     */
	    @Override
    	public void actionPerformed(ActionEvent e)
    	{
    		dispose();
    	}	
    }//CancelListener
}
