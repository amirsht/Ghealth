package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.LoginControl;
import models.Patient;

import javax.swing.JTextField;

import java.awt.Window.Type;

import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Label;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import java.awt.Panel;
import java.awt.RenderingHints;


public class Doctor_History_GUI extends LoggingOut {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4721311421959450478L;
	
	private JPanel contentPane;
	private JButton btnCancel;
	//private JButton btnCrtPt;
	private JLabel lblwarningMessage = null;
	private JPanel patientDetails;
	private JComboBox<?> AppointmentBox;
	private JComboBox<?> LabResBox;
	
	private JButton SearchPatient;
	private JButton LogOut;
	private JEditorPane editorPane;
	private JPanel imagePan;
	private JLabel imglabel;
	/**
	 * Create the frame.
	 */
	public Doctor_History_GUI() {
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
			lblLogo = new JLabel("Welcome DOCTOR!");
		else lblLogo = new JLabel("Hi "+LoginControl.getUser_full_name()+"!");
		lblLogo.setIcon(new ImageIcon(DoctorGUI.class.getResource("/images/logo2.png")));
		lblLogo.setBounds(0, 0, 794, 79);
		contentPane.add(lblLogo);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel.setBounds(630, 523, 140, 23);
		contentPane.add(btnCancel);
		
		AppointmentBox = new JComboBox<Object>();
		AppointmentBox.setBounds(10, 139, 465, 20);
		contentPane.add(AppointmentBox);
		
		JLabel AppointmentLabel = new JLabel("Appointment History");
		AppointmentLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		AppointmentLabel.setBounds(10, 114, 140, 14);
		contentPane.add(AppointmentLabel);
		
		JLabel lblLabResultHistory = new JLabel("Lab Result History");
		lblLabResultHistory.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLabResultHistory.setBounds(10, 184, 140, 14);
		contentPane.add(lblLabResultHistory);
		
		LabResBox = new JComboBox<Object>();
		LabResBox.setBounds(10, 209, 465, 20);
		contentPane.add(LabResBox);
		
		editorPane = new JEditorPane();
		editorPane.setBounds(10, 274, 164, 286);
		editorPane.setContentType("text/html");
		editorPane.setEditable(false);
		//editorPane.setLineWrap(true); //Makes the text wrap to the next line
		//editorPane.setWrapStyleWord(true); //Makes the text wrap full words, not just letters
		contentPane.add(editorPane);
		
		imagePan = new JPanel();
		imagePan.setLocation(175, 271);
		imagePan.setSize(300, 300);
		imagePan.setVisible(true);
    	contentPane.add(imagePan);
    	imglabel = new JLabel();
        imagePan.add(imglabel);
    	
    	
		btnCancel.addActionListener(new CancelListener());
		
		
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

	public void SetSummery(String summery) {
		editorPane.setText(summery);
	}

	public JComboBox<?> getAppointmentHistoryBox() {
		return AppointmentBox;
	}
	
	public JComboBox<?> getLabResultBox() {
		return LabResBox;
	}
	
	public void AppointmentHistoryBoxActionListener(ActionListener e){
		
		AppointmentBox.addActionListener(e);
	}
	
	public void LabResultBoxActionListener(ActionListener e){
		
		LabResBox.addActionListener(e);
	}
	
	public  JEditorPane geteditorPane()
	{
			return editorPane;
	}

	public JPanel getimagePan()
	{
		return imagePan;
	}
	
	public void setAddToImagePan(String Path)
	{
		Image image = null;
        try {
        	//File sourceimage = new File("src\\images\\aaa.jpg");
        	File sourceimage = new File(Path);
        	image = ImageIO.read(sourceimage);
        	image = getScaledImage(image,300,300);
        } catch (IOException e) {
        	e.printStackTrace();
        }
        
        
        
        //imglabel = new JLabel(new ImageIcon(image));
        imglabel.setIcon(new ImageIcon(image));
        //imagePan.add(imglabel);
              
	}
	
	
	private static Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
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