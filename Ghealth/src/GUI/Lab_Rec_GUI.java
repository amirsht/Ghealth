package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.Controller;
import client.LoginControl;
import enums.task;
import models.LabSettings;
import models.Patient;

import javax.swing.JTextField;

import java.awt.Window.Type;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Label;
import javax.swing.SwingConstants;


public class Lab_Rec_GUI extends LoggingOut {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5798215983453009657L;
	
	private JPanel contentPane;
	private JTextPane textRecPane;
	private JButton btnCancel;
	private JButton saveRec;
	//private JButton btnCrtPt;
	private JLabel lblwarningMessage = null;
	private JPanel patientDetails;
	private JButton SearchPatient;
	private JButton LogOut;
	private LabSettings lb;
	private JButton btnUploadFile;
	private JButton Browse;
	private JFileChooser fc;
	/**
	 * Create the frame.
	 */
	public Lab_Rec_GUI(LabSettings lb) 
	{

		this.lb = lb;
		setResizable(false);
		setTitle("G-Health");
		setIconImage(Toolkit.getDefaultToolkit().getImage(DoctorGUI.class.getResource("/images/logo2.PNG")));
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			lblLogo = new JLabel("Welcome Lab-Worker!");
		else lblLogo = new JLabel("Hi "+LoginControl.getUser_full_name()+"!");
		lblLogo.setIcon(new ImageIcon(DoctorGUI.class.getResource("/images/logo2.png")));
		lblLogo.setBounds(0, 0, 794, 79);
		contentPane.add(lblLogo);
		
		saveRec = new JButton("SAVE RECORD");
		saveRec.setBounds(202, 503, 140, 23);
		contentPane.add(saveRec);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel.setBounds(374, 503, 140, 23);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(new CancelListener());
		
		textRecPane = new JTextPane();
		textRecPane.setText("Add your lab record here...");
		textRecPane.setBounds(202, 203, 273, 228);
		contentPane.add(textRecPane);
		
		JLabel lblDoctor = new JLabel("Doctor Requirements:");
		lblDoctor.setBounds(202, 102, 178, 14);
		contentPane.add(lblDoctor);
		
		JLabel DoctorReq = new JLabel(lb.getLabDoctorReq());
		DoctorReq.setVerticalAlignment(SwingConstants.TOP);
		DoctorReq.setBounds(202, 127, 288, 50);
		contentPane.add(DoctorReq);
		
		
		
		
		
		Browse = new JButton("Upload File");
		Browse.setBounds(202, 455, 140, 23);
		contentPane.add(Browse);
		
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
	
	
	public void RecordLabActionListener(ActionListener e)
	{
		saveRec.addActionListener(e);
	}
	
	public void addCancelActionListener(ActionListener e)
	{
		btnCancel.addActionListener(e);
	}
	
public void SetPatient(Patient pt) {
		
		JLabel lblPatientDetails = new JLabel("Patient Details:");
		lblPatientDetails.setBounds(541, 135, 107, 20);
		contentPane.add(lblPatientDetails);
		patientDetails = new JPanel();
		patientDetails.setBounds(497, 170, 273, 232);
		contentPane.add(patientDetails);
		patientDetails.setLayout(null);
		
		
		Label label_1 = new Label("Patient ID");
		label_1.setBounds(5, 5, 62, 22);
		patientDetails.add(label_1);
		
		Label label_2 = new Label("First Name");
		label_2.setBounds(5, 35, 62, 22);
		patientDetails.add(label_2);
		
		Label label_3 = new Label("Last Name");
		label_3.setBounds(5, 65, 62, 22);
		patientDetails.add(label_3);
		
		Label label_4 = new Label("Email");
		label_4.setBounds(5, 95, 62, 22);
		patientDetails.add(label_4);
		
		Label label_5 = new Label("Phone");
		label_5.setBounds(5, 125, 62, 22);
		patientDetails.add(label_5);
		
		Label label_6 = new Label("Private Clinic");
		label_6.setBounds(5, 155, 82, 22);
		patientDetails.add(label_6);
		
		Label label_7 = new Label("Doctor ID");
		label_7.setBounds(5, 185, 62, 22);
		patientDetails.add(label_7);
		
		JLabel PationID = new JLabel(pt.getpID());
		PationID.setBounds(95,5,300, 22);
		patientDetails.add(PationID);
		

		JLabel fName = new JLabel(pt.getpFirstName());
		fName.setBounds(95, 35, 300, 22);
		patientDetails.add(fName);	
		

		JLabel lName = new JLabel(pt.getpLastName());
		lName.setBounds(95, 65, 300, 22);
		patientDetails.add(lName);

		JLabel eMail = new JLabel(pt.getPtEmail());
		eMail.setBounds(95, 95, 300, 22);
		patientDetails.add(eMail);
		
		JLabel phone = new JLabel(pt.getPtPhone());
		phone.setBounds(95, 125, 300, 22);
		patientDetails.add(phone);
		
		JLabel pClinic = new JLabel(pt.getPtPrivateClinic());
		pClinic.setBounds(95, 155, 90, 22);
		patientDetails.add(pClinic);
		

		JLabel doctorID = new JLabel(pt.getPd());
		doctorID.setBounds(95, 185, 90, 22);
		patientDetails.add(doctorID);
		
		
		
		patientDetails.setVisible(true);
		
	}

	
	public JButton getUploadFileButton()
	{
		return btnUploadFile;
	}

	public String getRecordField() {
		return textRecPane.getText();
	}
	
	public JButton getBrowseButton()
	{
		return Browse;
	}
	
	public JFileChooser getFileChooser()
	{
		return fc;
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
