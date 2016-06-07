package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.JList;
import javax.swing.JTree;

import java.awt.FlowLayout;

//import org.eclipse.wb.swing.FocusTraversalOnArray;


import java.awt.Component;

import javax.swing.BoxLayout;

import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import models.Envelope;
import models.User;
import net.miginfocom.swing.MigLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

public class MainWindowGUI extends LoggingOut {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8911802687405241262L;
	
	private JPanel contentPane;
	private JButton btnHome;
	private JButton btnManageFiles;
	private JButton btnManageGroups;
	private JPanel panel;
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindowGUI frame = new MainWindowGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame. This is the Home window.
	 */
	public MainWindowGUI(User user) {

		setResizable(false);
		setTitle("G-Health");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindowGUI.class.getResource("/images/logo2.png")));
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("New label");
		label.setBounds(0, 0, 0, 0);
		contentPane.add(label);
		
		JLabel lblLogo = new JLabel("Logo");
		lblLogo.setIcon(new ImageIcon(MainWindowGUI.class.getResource("/images/logo2.png")));
		lblLogo.setBounds(0, 0, 794, 79);
		contentPane.add(lblLogo);

		JLabel lblIPersonalBox = new JLabel("USER "+user.getuID());
		lblIPersonalBox.setForeground(new Color(51, 51, 255));
		lblIPersonalBox.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblIPersonalBox.setBounds(315, 169, 156, 63);
		contentPane.add(lblIPersonalBox);
		
		setLocationRelativeTo(null);
		setVisible(true);
		
	} 
	
	

	
	
	// Action Listeners
	
	public void addbtnHomeActionListener(ActionListener e)
	{
		btnHome.addActionListener(e);
	}
	
	public void addbtnManageFilesActionListener(ActionListener e)
	{
		btnManageFiles.addActionListener(e);
	}
	
	public void addbtnManageGroupsActionListener(ActionListener e)
	{
		btnManageGroups.addActionListener(e);
	}
}
