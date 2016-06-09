package GUI;
import client.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

import javax.swing.JButton;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Label;


public class CM_GUI extends LoggingOut {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8152476922277860117L;
	
	private JPanel contentPane;
	private JLabel lblwarningMessage = null;
	private JButton btnMonthlyReport;
	private JButton weeklyBtn;
	
	/**
	 * Create the frame.
	 */
	public CM_GUI() {
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
			lblLogo = new JLabel("Welcome Clinic Manager!");
		else lblLogo = new JLabel("Hi "+LoginControl.getUser_full_name()+"!");
		lblLogo.setIcon(new ImageIcon(DoctorGUI.class.getResource("/images/logo2.png")));
		lblLogo.setBounds(0, 0, 794, 79);
		contentPane.add(lblLogo);
		
		weeklyBtn = new JButton("Show weekly report");
		
		weeklyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		weeklyBtn.setBounds(138, 146, 173, 68);
		contentPane.add(weeklyBtn);
		
		
		
		JButton LogOut = new JButton("Log Out");
		LogOut.setBounds(138, 400, 245, 68);
		LogOut.addActionListener(new LogOutListener());
		contentPane.add(LogOut);
		
		btnMonthlyReport = new JButton("Show monthly report");
		btnMonthlyReport.setBounds(138, 253, 173, 68);
		contentPane.add(btnMonthlyReport);
		
		setLocationRelativeTo(null);
		
		setVisible(true);
		
		System.out.println("END OF DOC GUI CONSTRUCTOR");
	
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
	
	

	public class CancelListener implements ActionListener 
    {
    	@Override
    	public void actionPerformed(ActionEvent e)
    	{
    		dispose();
    	}	
    }//CancelListener
}
