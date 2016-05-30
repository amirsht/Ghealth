package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.LoginControl;

import javax.swing.JTextField;

import java.awt.Window.Type;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Label;


public class CS_GUI_Appoint extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1645191120165568000L;
	
	private JPanel contentPane;
	private JButton btnCancelAppoint;
	private JButton btnCrtAppoint;
	private JLabel lblwarningMessage = null;
	private JPanel pationDetails;
	JButton SearchPatient;
	JButton LogOut;

 
	/**
	 * Create the frame.
	 */
	public CS_GUI_Appoint() {
		setResizable(false);
		setTitle("G-Health");
		setIconImage(Toolkit.getDefaultToolkit().getImage(DoctorGUI.class.getResource("/images/logo2.PNG")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			lblLogo = new JLabel("Welcome CS!");
		else lblLogo = new JLabel("Hi "+LoginControl.getUser_full_name()+"!");
		
		lblLogo.setIcon(new ImageIcon(DoctorGUI.class.getResource("/images/logo2.png")));
		lblLogo.setBounds(0, 0, 794, 79);
		contentPane.add(lblLogo);
		
		btnCrtAppoint = new JButton("CREATE APPOINTMENT");
		btnCrtAppoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCrtAppoint.setBounds(138, 220, 245, 68);
		contentPane.add(btnCrtAppoint);
		
		btnCancelAppoint = new JButton("CANCEL APPOINTMENT");
		btnCancelAppoint.setBounds(138, 310, 245, 68);
		contentPane.add(btnCancelAppoint);
		
		pationDetails = new JPanel();
		pationDetails.setBounds(497, 170, 273, 232);
		contentPane.add(pationDetails);
		pationDetails.setLayout(null);
		
		Label label_1 = new Label("Patient ID");
		label_1.setBounds(5, 5, 62, 22);
		pationDetails.add(label_1);
		
		Label label_2 = new Label("First Name");
		label_2.setBounds(5, 35, 62, 22);
		pationDetails.add(label_2);
		
		Label label_3 = new Label("Last Name");
		label_3.setBounds(5, 65, 62, 22);
		pationDetails.add(label_3);
		
		Label label_4 = new Label("Email");
		label_4.setBounds(5, 95, 62, 22);
		pationDetails.add(label_4);
		
		Label label_5 = new Label("Phone");
		label_5.setBounds(5, 125, 62, 22);
		pationDetails.add(label_5);
		
		Label label_6 = new Label("Private Clinic");
		label_6.setBounds(5, 155, 82, 22);
		pationDetails.add(label_6);
		
		Label label_7 = new Label("Doctor ID");
		label_7.setBounds(5, 185, 62, 22);
		pationDetails.add(label_7);
		
		SearchPatient = new JButton("SEARCH ANOTHER PATIENT");
		SearchPatient.setBounds(138, 130, 245, 68);
		contentPane.add(SearchPatient);
		
		LogOut = new JButton("Log Out");
		LogOut.setBounds(138, 400, 245, 68);
		contentPane.add(LogOut);
		
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
	
	
	public void createAppointActionListener(ActionListener e)
	{
		btnCrtAppoint.addActionListener(e);
	}
	
	public void LogOutActionListener(ActionListener e)
	{
		LogOut.addActionListener(e);
	}
	
	public void SearchPatientActionListener(ActionListener e)
	{
		SearchPatient.addActionListener(e);
	}
	
	public void cancelAppointActionListener(ActionListener e)
	{
		btnCancelAppoint.addActionListener(e);
	}
	
	
	public void setPationID(String ppationID) {
		JLabel PationID = new JLabel(ppationID);
		PationID.setBounds(95,5,300, 22);
		pationDetails.add(PationID);
	}

	public void setfName(String ffName) {
		JLabel fName = new JLabel(ffName);
		fName.setBounds(95, 35, 300, 22);
		pationDetails.add(fName);	

	}

	public void setlName(String llName) {
		JLabel lName = new JLabel(llName);
		lName.setBounds(95, 65, 300, 22);
		pationDetails.add(lName);
	}

	public void seteMail(String Mail) {
		JLabel eMail = new JLabel(Mail);
		eMail.setBounds(95, 95, 300, 22);
		pationDetails.add(eMail);
	}

	public void setPhone(String pphone) {
		JLabel phone = new JLabel(pphone);
		phone.setBounds(95, 125, 300, 22);
		pationDetails.add(phone);
		
	}

	public void setpClinic(String Clinic) {
		JLabel pClinic = new JLabel(Clinic);
		pClinic.setBounds(95, 155, 90, 22);
		pationDetails.add(pClinic);
	}



	public void setDoctorID(String ddoctorID) {
		JLabel doctorID = new JLabel(ddoctorID);
		doctorID.setBounds(95, 185, 90, 22);
		pationDetails.add(doctorID);
		JLabel lblPatientDetails = new JLabel("Patient Details:");
		lblPatientDetails.setBounds(581, 116, 107, 20);
		contentPane.add(lblPatientDetails);
	}
	
	
}

