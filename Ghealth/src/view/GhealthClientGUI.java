package view;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GhealthClientGUI extends JFrame{

	private static final long serialVersionUID = -2806545028733372660L;
	
	
	private JLabel LabelPort = null;
	private JLabel LabelHost = null;
	private JTextField TextHost = null;
	private JTextField TextPort = null;
	private JButton ButtonOk = null;
	private JButton ButtonCancel = null;

	public GhealthClientGUI() {
		getContentPane().setLayout(null);
		
		LabelPort = new JLabel("Port");
		LabelPort.setBounds(10, 40, 46, 14);
		getContentPane().add(LabelPort);
		
		LabelHost = new JLabel("Host");
		LabelHost.setBounds(10, 11, 46, 14);
		getContentPane().add(LabelHost);
		
		getContentPane().add(getTextHost());
		getContentPane().add(getTextPort());
		
		ButtonOk = new JButton("OK");
		ButtonOk.setBounds(23, 180, 89, 23);
		getContentPane().add(ButtonOk);
		
		ButtonCancel = new JButton("Cancel");
		ButtonCancel.setBounds(122, 180, 89, 23);
		getContentPane().add(ButtonCancel);
		
		this.setTitle("GHealth Connection to Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(500, 200, 300, 250);
		this.setVisible(true);
		this.setResizable(false);
	}
	
	private JTextField getTextHost(){
		if(TextHost == null){
			TextHost = new JTextField();
			TextHost.setBounds(51, 9, 160, 20);
			TextHost.setColumns(10);
			TextHost.setText("localhost");
		}
		return TextHost;
	}

	private JTextField getTextPort(){
		if(TextPort == null){
			TextPort = new JTextField();
			TextPort.setBounds(51, 37, 160, 20);
			TextPort.setColumns(10);
			TextPort.setText("5555");
		}
		return TextPort;
	}
	
	public void clearFields(){
		TextHost.setText("");
		TextPort.setText("");
	}
	
	public String getHost(){
		return TextHost.getText();
	}
	
	public int getPort(){
		if(TextPort.getText().equals(""))
			return 0;
		return Integer.parseInt(TextPort.getText());
	}

	public void addOKActionListener(ActionListener listener){
		ButtonOk.addActionListener(listener);
	}

	public void addCancelActionListener(ActionListener listener){
		ButtonCancel.addActionListener(listener);
	}
	
	public void displayWarnningMessage(String msg)
	{
		JOptionPane.showMessageDialog(this, msg);
	}
	
}
