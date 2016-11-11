package cs414.a5.distro;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WelcomeScreen extends JFrame implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private int playerNum;
	private static final int DEFAULT_WIDTH = 500;
	private static final int DEFAULT_HEIGHT = 100;
	private PlayerDummy player;
	private WelcomeScreenController myController;
	private JTextField nameField;
	
	public WelcomeScreen(int playerNum,PlayerDummy player){
		this.player = player;
		this.playerNum = playerNum;
		this.setTitle("Player "+this.playerNum);
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	public void setupGUI(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationByPlatform(true);
		this.setVisible(true);
		setupFields();
	}
	
	private void setupFields(){
		JPanel main = new JPanel();
		main.setBackground(Color.WHITE);
		
		JLabel nameLabel = new JLabel("Enter your name.");
		nameField = new JTextField(10);
		JButton goButton = myController.getGoButton();
		
		// components to panel
		main.add(nameLabel);
		main.add(nameField);
		main.add(goButton);
		
		// Add everything to JFrame
		add(main);
	}
	
	public void setController(WelcomeScreenController mc){
		myController = mc;
	}
	
	public JTextField getNameField(){
		return nameField;
	}
	
	public void setName(String s){
		player.setName(s);;
	}
	
	public String getName(){
		return player.getName();
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				/*WelcomeScreenController myController = new WelcomeScreenController();
				WelcomeScreen myScreen = new WelcomeScreen(1);
				myController.setScreen(myScreen);
				myScreen.setController(myController);
				myScreen.setupGUI();
				*/
			}
		});
	}
}
