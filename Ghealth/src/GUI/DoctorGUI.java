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
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JList;
import java.awt.event.ActionEvent;

public class DoctorGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3196248489135261695L;
	
	private JPanel contentPane;
	private JButton btnOpenFile;
	private JButton btnRename;
	private JButton btnChangeDescription;
	private JButton btnUpdateFile;
	private JButton btnDeleteFile;
	private JList fileList;
	private JLabel lblFiles;
	private JButton btnManageFiles;
	private JButton btnManageGroups;
	private JButton btnGetFileAssociatedUsers;
	

	/**
	 * Create the frame. Admin can see all the files.
	 */
	public DoctorGUI() {
		
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
		
		JLabel lblLogo = new JLabel("Welcome Admin");
		lblLogo.setIcon(new ImageIcon(DoctorGUI.class.getResource("/images/logo2.png")));
		lblLogo.setBounds(0, 0, 794, 79);
		contentPane.add(lblLogo);
		
		
		setLocationRelativeTo(null);
		
		setVisible(true);
	}
	
	// Action Listeners
	
	
}
