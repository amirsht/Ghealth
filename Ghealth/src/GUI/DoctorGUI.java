package GUI;
import client.*;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Label;


public class DoctorGUI extends LoggingOut {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3233126342207430542L;
	
	private JPanel contentPane1;
	private JButton btnCancel1;
	private JButton findPatient1;
	//private JButton btnCrtPt;
	private JLabel lblwarningMessage1 = null;
	private JTextField InsertPatientId1;
	
	/**
	 * Create the frame.
	 */
	public DoctorGUI() {
		setResizable(false);
		setTitle("G-Health");
		setIconImage(Toolkit.getDefaultToolkit().getImage(DoctorGUI.class.getResource("/images/logo2.PNG")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane1 = new JPanel();
		contentPane1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane1);
		contentPane1.setLayout(null);
		contentPane1.setLayout(null);
		
		JLabel label = new JLabel("New label");
		label.setBounds(0, 0, 0, 0);
		contentPane1.add(label);
		JLabel lblLogo;
		if(LoginControl.getUser_full_name() == null)
			lblLogo = new JLabel("Welcome DOCTOR!");
		else lblLogo = new JLabel("Hi "+LoginControl.getUser_full_name()+"!");
		lblLogo.setIcon(new ImageIcon(DoctorGUI.class.getResource("/images/logo2.png")));
		lblLogo.setBounds(0, 0, 794, 79);
		contentPane1.add(lblLogo);
		
		findPatient1 = new JButton("Search Patient");
		
		findPatient1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		findPatient1.setBounds(202, 157, 140, 23);
		contentPane1.add(findPatient1);
		
		btnCancel1 = new JButton("Cancel");
		
		btnCancel1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel1.setBounds(352, 157, 140, 23);
		contentPane1.add(btnCancel1);
		btnCancel1.addActionListener(new CancelListener());
		
		InsertPatientId1 = new JTextField();
		InsertPatientId1.setText("Insert ID here...");
		InsertPatientId1.setBounds(270, 126, 137, 20);
		
		/* MouseListener for clear JTextField when mouse clicks */
		InsertPatientId1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
            	InsertPatientId1.setText("");
            }
        });
		
		contentPane1.add(InsertPatientId1);
		InsertPatientId1.setColumns(10);
		
		Label label_1 = new Label("Patient ID");
		label_1.setBounds(202, 126, 62, 22);
		contentPane1.add(label_1);
		
		
		setLocationRelativeTo(null);
		
		setVisible(true);
		
		System.out.println("END OF DOC GUI CONSTRUCTOR");
	
	}
	
	public void setWarningMessageVisibleTrue() {
		lblwarningMessage1.setVisible(true);	
	}
	
	public void setWarningMessageVisibleTrue(String st) {
		lblwarningMessage1.setText(st);
		lblwarningMessage1.setForeground(Color.RED);
		lblwarningMessage1.setBounds(10, 165, 245, 30);
		lblwarningMessage1.setVisible(true);	
		
	}
	
	
	
	public void undisplayWarningMessage() {
		lblwarningMessage1.setVisible(false);
		
	}
	
	
	public void findPatientActionListener(ActionListener e)
	{
		findPatient1.addActionListener(e);
	}
	
	public void addCancelActionListener(ActionListener e)
	{
		btnCancel1.addActionListener(e);
	}
	

	
	public String getPtID() {
		return InsertPatientId1.getText();
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
