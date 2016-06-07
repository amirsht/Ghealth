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
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Label;


public class LabGUI extends LoggingOut {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3233126342207430542L;
	
	private JPanel contentPane;
	private JButton btnCancel;
	private JButton findPatient;
	//private JButton btnCrtPt;
	private JLabel lblwarningMessage = null;
	private JTextField InsertPatientId;
	private JComboBox<?> Lab_History;
	private JButton btnChooseLab;
	
	/**
	 * Create the frame.
	 */
	public LabGUI() {
		setResizable(false);
		setTitle("G-Health");
		setIconImage(Toolkit.getDefaultToolkit().getImage(LabGUI.class.getResource("/images/logo2.PNG")));
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
			lblLogo = new JLabel("Welcome DOCTOR!");
		else lblLogo = new JLabel("Hi "+LoginControl.getUser_full_name()+"!");
		lblLogo.setIcon(new ImageIcon(LabGUI.class.getResource("/images/logo2.png")));
		lblLogo.setBounds(0, 0, 794, 79);
		contentPane.add(lblLogo);
		
		findPatient = new JButton("Search Patient");
		
		findPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		findPatient.setBounds(202, 157, 140, 23);
		contentPane.add(findPatient);
		
		btnCancel = new JButton("Cancel");
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel.setBounds(352, 157, 140, 23);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(new CancelListener());
		
		InsertPatientId = new JTextField();
		InsertPatientId.setText("Insert ID here...");
		InsertPatientId.setBounds(270, 126, 137, 20);
		
		/* MouseListener for clear JTextField when mouse clicks */
		InsertPatientId.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
            	InsertPatientId.setText("");
            }
        });
		
		contentPane.add(InsertPatientId);
		InsertPatientId.setColumns(10);
		
		Label label_1 = new Label("Patient ID");
		label_1.setBounds(202, 126, 62, 22);
		contentPane.add(label_1);
		
		
		
		JButton LogOut = new JButton("Log Out");
		LogOut.setBounds(138, 400, 245, 68);
		LogOut.addActionListener(new LogOutListener());
		contentPane.add(LogOut);
		
		
		Lab_History = new JComboBox<Object>();
		Lab_History.setBounds(197, 214, 328, 20);
		Lab_History.setVisible(false);
		contentPane.add(Lab_History);
		
		btnChooseLab = new JButton("Choose Lab");
		btnChooseLab.setBounds(270, 258, 178, 23);
		btnChooseLab.setVisible(false);
		contentPane.add(btnChooseLab);
		
		
		
		
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
	
	
	public void findPatientActionListener(ActionListener e)
	{
		findPatient.addActionListener(e);
	}
	
	public void addCancelActionListener(ActionListener e)
	{
		btnCancel.addActionListener(e);
	}
	
	public JComboBox<?> getLabHistoryComboBox()
	{
		return Lab_History;
	}

	
	public String getPtID() {
		return InsertPatientId.getText();
	}

	
	public JButton getbtnChooseLab()
	{
		return btnChooseLab;
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
