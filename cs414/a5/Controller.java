package cs414.a5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Controller {
	private Model model;
	private View view;

	public void addModel(Model m){
		model = m;
	}
	 
	public void addView(View v){
		view = v;
	}
	
	// Buttons and Action Listeners
	public JButton getBuyButton(){
		  JButton buyButton = new JButton("Buy");
		  
		  buyButton.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e){
				  //System.out.println("Debug-Controller: " + "Buy button pressed");
				  model.buyDeed();
				  } 
		  });
		  return buyButton;
	}
	
	public JButton getRollDiceButton(){
		  JButton rollButton = new JButton("Roll");
		  
		  rollButton.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e){
				  ////System.out.println("Debug-Controller: " + "Roll Dice button pressed");
				  model.rollDice();
				  } 
		  });
		  
		  return rollButton;
	}
	
	public JButton getEndTurnButton(){
		JButton endTurnButton = new JButton("End Turn");
		
		endTurnButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//System.out.println("Debug-Controller: "+ "End turn button pressed");
				model.endTurn();
			}
		});
		return endTurnButton;
	}
	
	public JButton getMyPropertiesButton(){
		JButton myPropertiesButton = new JButton("My properties");
		myPropertiesButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//System.out.println("My Deeds button pressed!");
				view.chooseDeeds(model.getDeeds());
			}
		});
		return myPropertiesButton;
	}
	
	public JButton getEndGameButton(){
		JButton endGameButton = new JButton("End Game");
		endGameButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//System.out.println("End Game button pressed!");
				JOptionPane.showMessageDialog(null, model.endGame());
				view.dispose();
			}
		});
		return endGameButton;
	}
	public void auctionMenu(Square s){	
		int numPlayers = model.getNumPlayer();// get number of players
		int []bits = new int [numPlayers];
		//System.out.println(s.getName());
	    String[] bit = new String [numPlayers];	    
	    for(int i = 0; i < numPlayers; i++){   	
	    	bit[i] = JOptionPane.showInputDialog(model.getPlayers()[i].getName()
	    			+ "! Enter your bit for : "+ s.getName());
			//Send model the name of each player 
			bits[i] = Integer.parseInt(bit[i]);	
			//System.out.println(bits[i]);
	    }
	    
		model.auction(s,bits);
	}
}