package cs414.a5.distro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;

public class WelcomeScreenController implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WelcomeScreen myScreen;
	private ObjectOutputStream outToServer;
	
	public void setScreen(WelcomeScreen ms){
		myScreen = ms;
	}
	
	public JButton getGoButton(){
		JButton goButton = new JButton("GO!");
		goButton.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e){
				  System.out.println("Go button pressed!");
				  System.out.println("This is the name entered: "+myScreen.getNameField().getText());
				  
				  // Send the Player Thread the name entered
				  System.out.println("From GC this is the name to give the PlayerThread: "+myScreen.getNameField().getText());
				  try {
					outToServer.writeObject(myScreen.getNameField().getText());
					myScreen.gettingName = false;
				  } catch (IOException e1) {
					  // TODO Auto-generated catch block
					  e1.printStackTrace();
				  }
				  
			  } 
		  });
		return goButton;
	}
	
	public void setOutStream(ObjectOutputStream o){
		outToServer = o;
	}
}
