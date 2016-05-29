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
		
		JLabel lblLogo = new JLabel("Welcome CS");
		lblLogo.setIcon(new ImageIcon(DoctorGUI.class.getResource("/images/logo2.png")));
		lblLogo.setBounds(0, 0, 794, 79);
		contentPane.add(lblLogo);
		
		btnCrtAppoint = new JButton("CREATE APPOINTMENT");
		btnCrtAppoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCrtAppoint.setBounds(142, 399, 172, 68);
		contentPane.add(btnCrtAppoint);
		
		btnCancelAppoint = new JButton("CANCEL APPOINTMENT");
		btnCancelAppoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancelAppoint.setBounds(392, 399, 172, 68);
		contentPane.add(btnCancelAppoint);
		btnCancelAppoint.addActionListener(new CancelListener());
		
		Label label_1 = new Label("Patient ID");
		label_1.setBounds(142, 106, 62, 22);
		contentPane.add(label_1);
		
		Label label_2 = new Label("First Name");
		label_2.setBounds(252, 106, 62, 22);
		contentPane.add(label_2);
		
		Label label_3 = new Label("Last Name");
		label_3.setBounds(364, 106, 62, 22);
		contentPane.add(label_3);
		
		Label label_4 = new Label("Email");
		label_4.setBounds(485, 106, 62, 22);
		contentPane.add(label_4);
		
		Label label_5 = new Label("Phone");
		label_5.setBounds(142, 179, 62, 22);
		contentPane.add(label_5);
		
		Label label_6 = new Label("Private Clinic");
		label_6.setBounds(252, 179, 77, 22);
		contentPane.add(label_6);
		
		Label label_7 = new Label("Doctor ID");
		label_7.setBounds(364, 179, 62, 22);
		contentPane.add(label_7);
	
		
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
	
	public void cancelAppointActionListener(ActionListener e)
	{
		btnCancelAppoint.addActionListener(e);
	}

	public void setfName(String ffName) {

		
		JLabel fName = new JLabel(ffName);
		fName.setBounds(252, 137, 46, 14);
		contentPane.add(fName);	

	}

	public void setlName(String llName) {
		JLabel lName = new JLabel(llName);
		lName.setBounds(364, 137, 46, 14);
		contentPane.add(lName);
	}

	public void seteMail(String Mail) {
		JLabel eMail = new JLabel(Mail);
		eMail.setBounds(482, 137, 82, 14);
		contentPane.add(eMail);
	}

	public void setPhone(String pphone) {
		JLabel phone = new JLabel(pphone);
		phone.setBounds(142, 207, 46, 14);
		contentPane.add(phone);
		
	}

	public void setpClinic(String Clinic) {
		JLabel pClinic = new JLabel(Clinic);
		pClinic.setBounds(252, 210, 46, 14);
		contentPane.add(pClinic);
	}

	public void setPationID(String ppationID) {
		JLabel PationID = new JLabel(ppationID);
		PationID.setBounds(142, 137, 100, 14);
		contentPane.add(PationID);
	}

	public void setDoctorID(String ddoctorID) {
		JLabel doctorID = new JLabel(ddoctorID);
		doctorID.setBounds(364, 210, 46, 14);
		contentPane.add(doctorID);
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

