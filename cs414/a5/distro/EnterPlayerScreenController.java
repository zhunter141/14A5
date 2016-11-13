package cs414.a5.distro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class EnterPlayerScreenController implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PlayerDummy player;
	private EnterPlayerScreen myScreen;
	
	public void setScreen(EnterPlayerScreen ms){
		myScreen = ms;
	}
	
	public JButton getGoButton(){
		JButton goButton = new JButton("GO!");
		goButton.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e){
				  System.out.println("Go button pressed!");
				  System.out.println("This is the name entered: "+myScreen.getNameField().getText());
				  player.setName(myScreen.getNameField().getText());
				  System.out.println("Player has name now = "+player.hasName());
				  myScreen.dispose();
			  } 
		  });
		return goButton;
	}
	
	public void setPlayer(PlayerDummy player){
		this.player = player;
	}
}
