package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.Controller;
import client.LoginControl;
import enums.DoctorSpeciallity;
import enums.task;
import models.*;

import javax.swing.JTextField;

import java.awt.Window.Type;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import java.awt.Label;
import javax.swing.JSpinner;
import javax.swing.JComboBox;


public class CS_GUI_addPatient extends LoggingOut {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4516583930581651307L;
	
	private JPanel contentPane;
	private JButton btnCancel;
	private JButton btnAddPatient;
	private JLabel lblwarningMessage = null;
	private JTextField fName;
	private JTextField lName;
	private JTextField eMail;
	private JTextField phone;
	private JTextField PationID;
	private JTextField doctorID;
	private JComboBox ClinicBox;
	private JComboBox eMailcomboBox;
	


	/**
	 * Create the frame.
	 */
	public CS_GUI_addPatient() {
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
		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
		/*--------------------------------------------------------------------------
		frame.addWindowListener(new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		    	System.out.println("------------window closed-------------");
		    }
		});
		*/ //-------------------------------------------------------------------------
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
		
		btnAddPatient = new JButton("ADD PATIENT");
		btnAddPatient.setBounds(220, 444, 109, 23);
		contentPane.add(btnAddPatient);
		
		fName = new JTextField();
		fName.setBounds(372, 163, 86, 20);
		contentPane.add(fName);
		fName.setColumns(10);
		
		lName = new JTextField();
		lName.setBounds(372, 205, 86, 20);
		contentPane.add(lName);
		lName.setColumns(10);
		
		eMail = new JTextField();
		eMail.setBounds(372, 242, 86, 20);
		contentPane.add(eMail);
		eMail.setColumns(10);
		
		phone = new JTextField();
		phone.setBounds(372, 285, 86, 20);
		contentPane.add(phone);
		phone.setColumns(10);
		
		
		PationID = new JTextField();
		PationID.setBounds(372, 123, 86, 20);
		contentPane.add(PationID);
		PationID.setColumns(10);
		
		doctorID = new JTextField();
		doctorID.setBounds(372, 368, 86, 20);
		contentPane.add(doctorID);
		doctorID.setColumns(10);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(392, 444, 109, 23);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(new CancelListener());
		
		Label at = new Label("@");
		at.setBounds(464, 240, 15, 22);
		contentPane.add(at);
		
		Label label_2 = new Label("First Name");
		label_2.setBounds(252, 161, 62, 22);
		contentPane.add(label_2);
		
		Label label_3 = new Label("Last Name");
		label_3.setBounds(252, 203, 62, 22);
		contentPane.add(label_3);
		
		Label label_4 = new Label("Email");
		label_4.setBounds(252, 242, 62, 22);
		contentPane.add(label_4);
		
		Label label_5 = new Label("Phone");
		label_5.setBounds(252, 283, 62, 22);
		contentPane.add(label_5);
		
		Label label_6 = new Label("Private Clinic");
		label_6.setBounds(252, 324, 77, 22);
		contentPane.add(label_6);
		
		Label label_7 = new Label("Doctor ID");
		label_7.setBounds(252, 366, 62, 22);
		contentPane.add(label_7);
		
		Envelope en = Controller.Control(null, task.GET_PRIVATE_CLINIC_LIST);
		String [] str = new String[en.getobjList().size()];
		
		System.out.println(en.getobjList().size());
		int i=0;
		for(Object obj : en.getobjList())
		{
			PrivateClinic pc;
			pc = (PrivateClinic)obj;
			str[i++] = (pc.getPrivateClinicName());
		}
		
		ClinicBox = new JComboBox<Object>(str);
		ClinicBox.setBounds(372, 324, 115, 20);
		contentPane.add(ClinicBox);
		
		String [] eMailList = {"gmail.com","hotmail.com","braude.ac.il","yahoo.com","walla.co.il","iol.com"};
		eMailcomboBox = new JComboBox<Object>(eMailList);
		eMailcomboBox.setBounds(485, 242, 115, 20);
		contentPane.add(eMailcomboBox);
		
		Label label_8 = new Label("Pation ID");
		label_8.setBounds(267, 123, 62, 22);
		contentPane.add(label_8);
		
		
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
	
	
	public void addPatientActionListener(ActionListener e)
	{
		btnAddPatient.addActionListener(e);
	}
	
	public void addCancelActionListener(ActionListener e)
	{
		btnCancel.addActionListener(e);
	}

	
	public String getfName() {
		return fName.getText();
	}

	public String getlName() {
		return lName.getText();
	}

	public String geteMail() {
		return eMail.getText();
	}

	public String getPhone() {
		return phone.getText();
	}


	public void setPationID(String id){
		PationID.setText(id);
	}
	public String getPationID() {
		return PationID.getText();
	}

	public String getDoctorID() {
		return doctorID.getText();
	}
	
	public JComboBox getClinicBox()
	{
		return ClinicBox;
	}
	
	public JComboBox geteMailBox()
	{
		return eMailcomboBox;
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
