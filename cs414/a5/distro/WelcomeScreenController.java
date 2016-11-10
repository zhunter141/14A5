package cs414.a5.distro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class WelcomeScreenController implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WelcomeScreen myScreen;
	
	public void setScreen(WelcomeScreen ms){
		myScreen = ms;
	}
	
	public JButton getGoButton(){
		JButton goButton = new JButton("GO!");
		goButton.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e){
				  System.out.println("Go button pressed!");
				  System.out.println("This is the name entered: "+myScreen.getNameField().getText());
				  myScreen.setName(myScreen.getNameField().getText());
				  myScreen.setVisible(false);
			  } 
		  });
		return goButton;
	}
}
