package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.LoginControl;
import enums.DoctorSpeciallity;

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
import javax.swing.JComboBox;

import enums.*;

public class CS_GUI_newAppoint extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1645191120165568000L;
	
	private JPanel contentPane;
	private JButton btnCancelAppoint;
	private JButton btnCrtAppoint;
	private JLabel lblwarningMessage = null;
	public JComboBox<?> docBox;
	


	/**
	 * Create the frame.
	 */
	public CS_GUI_newAppoint() {
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
		lblLogo.setBounds(0, 0, 794, 109);
		contentPane.add(lblLogo);
		
		btnCrtAppoint = new JButton("CREATE APPOINTMENT");
		btnCrtAppoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCrtAppoint.setBounds(142, 470, 172, 68);
		contentPane.add(btnCrtAppoint);
		
		btnCancelAppoint = new JButton("CANCEL APPOINTMENT");
		btnCancelAppoint.setBounds(369, 470, 172, 68);
		contentPane.add(btnCancelAppoint);
		btnCancelAppoint.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		
		docBox = new JComboBox<Object>(DoctorSpeciallity.values());
		docBox.setBounds(263, 141, 106, 20);
		contentPane.add(docBox);
		
		JLabel lblChooseDoctorType = new JLabel("Choose Doctor Type");
		lblChooseDoctorType.setBounds(123, 144, 114, 14);
		contentPane.add(lblChooseDoctorType);
		
		JLabel lblPatientName = new JLabel("Patient name:");
		lblPatientName.setBounds(123, 120, 106, 14);
		contentPane.add(lblPatientName);	
		
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
	
	
	public void createNewAppointActionListener(ActionListener e)
	{
		btnCrtAppoint.addActionListener(e);
	}
	
	public void cancelNewAppointActionListener(ActionListener e)
	{
		btnCancelAppoint.addActionListener(e);
	}
	
	public void SelectDocTypeActionListener(ActionListener e){
		
		docBox.addActionListener(e);
		
	}
	

	public void setfName(String ffName) {

		
		JLabel displayData = new JLabel(ffName);
		displayData.setBounds(247, 120, 122, 14);
		contentPane.add(displayData);	

	}

	public void setPtID(String ptIDstr) {
		
		JLabel ptID = new JLabel(ptIDstr);
		ptID.setBounds(372, 120, 100, 14);
		contentPane.add(ptID);
	}


}

