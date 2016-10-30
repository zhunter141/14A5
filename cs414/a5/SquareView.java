package cs414.a5;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SquareView extends JPanel{
	private JLabel nameLabel;
	private Square mySquare;
	
	public SquareView(Square s){
		this.mySquare = s;
		setUp();
		addPlayers();
	}
	
	private void setUp(){
		if(mySquare.getName().compareTo("")!=0){
			this.setLayout(new GridLayout(5,0));
			this.setBorder(BorderFactory.createLineBorder(Color.black));	
		}
		// Initialize name Label
		nameLabel = new JLabel(mySquare.getName());
		
		if(mySquare.getColor() != Color.LIGHT_GRAY){
			nameLabel.setBackground(mySquare.getColor());
			nameLabel.setOpaque(true);
			this.add(nameLabel);
		}
	}
	private void addPlayers(){
		ArrayList<Token> myTokens = mySquare.getTokens();
		for(int i=0;i<myTokens.size();i++){
			Token t = myTokens.get(i);
			JLabel pLabel = new JLabel(t.getDescription());
			this.add(pLabel);
		}
	}
}
