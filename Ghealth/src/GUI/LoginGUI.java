package GUI;

import Games.Pacman.*;
import Games.Snake.*;
import Games.SpaceInvaders.*;
import Games.Tetris.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.Window.Type;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


public class LoginGUI extends LoggingOut {

	/**
	 * 
	 */
	private static final long serialVersionUID = -510417858888515993L;
	
	private JPanel contentPane;
	private JTextField userField;
	private JPasswordField passwordField;
	//private JTextField passwordField;
	private JButton btnCancel;
	private JButton btnLogin;
	private JLabel lblwarningMessage = null;




	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginGUI.class.getResource("/images/logo2.png")));
		setTitle("Ghealth Login");
		setResizable(false);
		//setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);

		
		
		JLabel label = new JLabel("");
		label.setBounds(10, 30, 1200, 200);
		label.setIcon(new ImageIcon(LoginGUI.class.getResource("/images/logo2.png")));
		contentPane.add(label);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Arial", Font.BOLD, 16));
		lblUsername.setBounds(200, 300, 87, 16);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Arial", Font.BOLD, 16));
		lblPassword.setBounds(200, 330, 87, 16);
		contentPane.add(lblPassword);
		
		userField = new JTextField();
		userField.setBounds(290, 300, 139, 22);
		contentPane.add(userField);
		userField.setColumns(10);
		
		passwordField = new JPasswordField();
				//JTextField();
		passwordField.setColumns(10);
		passwordField.setBounds(290, 330, 139, 22);
		contentPane.add(passwordField);
		
		btnLogin = new JButton("Login");
		btnLogin.setIcon(new ImageIcon(LoginGUI.class.getResource("/images/login.PNG")));
		btnLogin.setBounds(210, 370, 97, 25);
		contentPane.add(btnLogin);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setIcon(new ImageIcon(LoginGUI.class.getResource("/images/cancel.PNG")));
		btnCancel.setBounds(350, 370, 97, 25);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		
		
		JButton btnSecret = new JButton("Ghealth Entertainment Generator");
		btnSecret.setBounds(441, 437, 243, 23);
		contentPane.add(btnSecret);
		
		btnSecret.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				

				
				int x = ThreadLocalRandom.current().nextInt(1, 4 + 1);
				
				//x is now 1-4
				
				switch(x){
				
				case 1:
					
					   EventQueue.invokeLater(new Runnable() {
				            @Override
				            public void run() {                
				                JFrame ex = new Snake();
				                ex.setVisible(true);                
				            }
				        });
					
				
					break;
					
				case 2:
					  EventQueue.invokeLater(new Runnable() {

				            @Override
				            public void run() {
				                Pacman ex = new Pacman();
				                ex.setVisible(true);
				            }
				        });
					
					break;
				
				case 3:
					
					new SpaceInvaders();
					break;
				case 4:
					
					Tetris game = new Tetris();
			        game.setLocationRelativeTo(null);
			        game.setVisible(true);
			        
					break;
					
				default:
					break;
				
				}
				 
			}
		});
		
		
		setLocationRelativeTo(null);
		
		setVisible(true);
	
	}
	// Action Listeners
	
	
	public JLabel getLblwarningMessage() {
		if(lblwarningMessage == null){
			lblwarningMessage = new JLabel("user name or password is wrong");
			lblwarningMessage.setForeground(Color.RED);
			lblwarningMessage.setBounds(10, 165, 200, 30);
			lblwarningMessage.setVisible(false);
		}
		return lblwarningMessage;
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
	
	
	public void addLoginActionListener(ActionListener e)
	{
		btnLogin.addActionListener(e);
	}
	
	public void addCancelActionListener(ActionListener e)
	{
		btnCancel.addActionListener(e);
	}
	
	// Getters
	
	public String getUserField() {
		return userField.getText();
	}

	public String getPasswordField() {
		return passwordField.getText();
	}
}


