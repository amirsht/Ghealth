package Server;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * this class is Abstract GUI Server that extends JFrame
 *
 */
public abstract class AbstractGuiServer extends JFrame
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4109991313549908385L;
	protected JButton backButton=null;

	
	
	public void displayInfoMessage(String message,String title,int messageType)
	{
		JOptionPane.showMessageDialog(this, message, title,messageType);
	}
	
	/**
	 * This method adds an action listener to back to menu button.
	 * @param listener
	 */
	public void addBackActionListener(ActionListener listener){
		backButton.addActionListener(listener);
	}
}
