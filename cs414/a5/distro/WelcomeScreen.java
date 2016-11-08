package cs414.a5.distro;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WelcomeScreen extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int playerNum;
	private static final int DEFAULT_WIDTH = 500;
	private static final int DEFAULT_HEIGHT = 100;
	
	public WelcomeScreen(int playerNum){
		this.playerNum = playerNum;
		this.setTitle("Player "+this.playerNum);
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setupGUI();
	}
	
	public void setupGUI(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationByPlatform(true);
		this.setVisible(true);
		setupFields();
	}
	
	public void setupFields(){
		JPanel main = new JPanel();
		main.setBackground(Color.WHITE);
		
		JLabel nameLabel = new JLabel("Enter your name.");
		JTextField nameField = new JTextField(10);
		JButton goButton = new JButton("GO!");
		// components to panel
		main.add(nameLabel);
		main.add(nameField);
		main.add(goButton);
		
		// Add everything to JFrame
		add(main);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				WelcomeScreen myScreen = new WelcomeScreen(1);
			}
		});
	}
}
