package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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


public class CostumerServiceGUI extends JFrame {

	
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
	private JTextField pClinic;
	private JTextField PationID;
	private JTextField doctorID;


	/**
	 * Create the frame.
	 */
	public CostumerServiceGUI() {
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
		
		JLabel lblLogo = new JLabel("Welcome CS");
		lblLogo.setIcon(new ImageIcon(DoctorGUI.class.getResource("/images/logo2.png")));
		lblLogo.setBounds(0, 0, 794, 79);
		contentPane.add(lblLogo);
		
		btnAddPatient = new JButton("ADD PATIENT");
		btnAddPatient.setBounds(319, 444, 109, 23);
		contentPane.add(btnAddPatient);
		
		fName = new JTextField();
		fName.setBounds(438, 161, 86, 20);
		contentPane.add(fName);
		fName.setColumns(10);
		
		lName = new JTextField();
		lName.setBounds(438, 203, 86, 20);
		contentPane.add(lName);
		lName.setColumns(10);
		
		eMail = new JTextField();
		eMail.setBounds(438, 244, 86, 20);
		contentPane.add(eMail);
		eMail.setColumns(10);
		
		phone = new JTextField();
		phone.setBounds(438, 285, 86, 20);
		contentPane.add(phone);
		phone.setColumns(10);
		
		pClinic = new JTextField();
		pClinic.setBounds(438, 324, 86, 20);
		contentPane.add(pClinic);
		pClinic.setColumns(10);
		
		PationID = new JTextField();
		PationID.setBounds(438, 123, 86, 20);
		contentPane.add(PationID);
		PationID.setColumns(10);
		
		doctorID = new JTextField();
		doctorID.setBounds(438, 368, 86, 20);
		contentPane.add(doctorID);
		doctorID.setColumns(10);
		
		JTextPane txtpnPationId = new JTextPane();
		txtpnPationId.setText("Pation ID");
		txtpnPationId.setBounds(340, 123, 71, 20);
		contentPane.add(txtpnPationId);
		
		JTextPane txtpnFirstName = new JTextPane();
		txtpnFirstName.setText("First Name");
		txtpnFirstName.setBounds(340, 161, 71, 20);
		contentPane.add(txtpnFirstName);
		
		JTextPane txtpnLastName = new JTextPane();
		txtpnLastName.setText("Last Name");
		txtpnLastName.setBounds(340, 203, 71, 20);
		contentPane.add(txtpnLastName);
		
		JTextPane txtpnEmail = new JTextPane();
		txtpnEmail.setText("Email");
		txtpnEmail.setBounds(340, 244, 71, 20);
		contentPane.add(txtpnEmail);
		
		JTextPane txtpnPhone = new JTextPane();
		txtpnPhone.setText("Phone");
		txtpnPhone.setBounds(340, 285, 71, 20);
		contentPane.add(txtpnPhone);
		
		JTextPane txtpnPrivateClinic = new JTextPane();
		txtpnPrivateClinic.setText("Private Clinic");
		txtpnPrivateClinic.setBounds(340, 324, 71, 20);
		contentPane.add(txtpnPrivateClinic);
		
		JTextPane txtpnPatientDoctorId = new JTextPane();
		txtpnPatientDoctorId.setText("Doctor ID");
		txtpnPatientDoctorId.setBounds(340, 368, 71, 20);
		contentPane.add(txtpnPatientDoctorId);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(508, 444, 89, 23);
		contentPane.add(btnCancel);
		
		
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

	public String getpClinic() {
		return pClinic.getText();
	}

	public String getPationID() {
		return PationID.getText();
	}

	public String getDoctorID() {
		return doctorID.getText();
	}
	
	// Getters


}
