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


public class GM_GUI extends LoggingOut {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1583234608338407492L;
	
	private JPanel contentPane;
	private JLabel lblwarningMessage = null;
	private JButton btnMonthlyReport;
	private JButton weeklyBtn;
	
	private JDatePickerImpl datePickerFrom;
	private Panel cal_from;
	private JButton btnChooseDateFrom;
	private JLabel lblNewLabelFrom;
	
	private JLabel lblDateTo;
	
	private JDatePickerImpl datePickerTo;
	private Panel cal_to;
	private JButton btnChooseDateTo;
	private JLabel lblNewLabelTo;
	private JLabel lblSelectDateFrom;
	private JLabel lblSelectDateTo;
	
	
	
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
		
		weeklyBtn.setBounds(237, 223, 271, 68);
		contentPane.add(weeklyBtn);
		
		
		
		JButton LogOut = new JButton("Log Out");
		LogOut.setBounds(138, 400, 245, 68);
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
		cal_from.setBounds(33, 170, 278, 33);
		cal_from.setVisible(true);
		contentPane.add(cal_from,BorderLayout.WEST);		
		btnChooseDateFrom = new JButton("OK");
		cal_from.add(btnChooseDateFrom);		

		datePickerTo = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePickerTo.setVisible(true);
		cal_to = new Panel();
		cal_to.add(datePickerTo);
		cal_to.setBounds(439, 170, 278, 33);
		cal_to.setVisible(true);
		contentPane.add(cal_to,BorderLayout.WEST);		
		btnChooseDateTo = new JButton("OK");
		cal_to.add(btnChooseDateTo);		
		
		lblSelectDateFrom = new JLabel("Select date From");
		lblSelectDateFrom.setBounds(33, 131, 278, 33);
		contentPane.add(lblSelectDateFrom);
		
		lblSelectDateTo = new JLabel("Select date To");
		lblSelectDateTo.setBounds(439, 131, 278, 33);
		contentPane.add(lblSelectDateTo);
		
		JComboBox MonthBox = new JComboBox();
		MonthBox.setBounds(33, 304, 55, 20);
		contentPane.add(MonthBox);
		
		JLabel lblLastMonthsAmount = new JLabel("Last months amount");
		lblLastMonthsAmount.setBounds(33, 277, 130, 14);
		contentPane.add(lblLastMonthsAmount);
		
		JButton OKmonthBtn = new JButton("OK");
		OKmonthBtn.setBounds(98, 303, 47, 23);
		contentPane.add(OKmonthBtn);
		
		setLocationRelativeTo(null);
		
		setVisible(true);
		
	
	}
	
	public void setWarningMessageVisibleTrue() {
		lblwarningMessage.setVisible(true);	
	}
	
	public void setWarningMessageVisibleTrue(String st) {
		lblwarningMessage.setText(st);
		lblwarningMessage.setForeground(Color.RED);
		lblwarningMessage.setBounds(10, 165, 245, 30);
		lblwarningMessage.setVisible(true);	
		
	}
	
	
	
	public void undisplayWarningMessage() {
		lblwarningMessage.setVisible(false);
		
	}
	
	
	public void weeklyActionListener(ActionListener e)
	{
		weeklyBtn.addActionListener(e);
	}
	
	public void monthlyActionListener(ActionListener e)
	{
		btnMonthlyReport.addActionListener(e);
	}
	
	public Panel getCal() {
		return cal_from;
	}	
	
	public JDatePickerImpl getDatePicker() {
		return datePickerFrom;
	}	
	
	public JButton getChoosenDateOK() {
		return btnChooseDateFrom;
	}
	
	

	public class CancelListener implements ActionListener 
    {
    	@Override
    	public void actionPerformed(ActionEvent e)
    	{
    		dispose();
    	}	
    }//CancelListener
}
