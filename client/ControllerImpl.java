package client;

//import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.HashSet;

//import java.util.HashSet;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import common.ControllerInterface;
import common.ModelInterface;
import common.ViewInterface;
import cs414.a5.Square;

public class ControllerImpl implements ControllerInterface{
		
		private ModelInterface model;
		private ViewInterface view;
		//private ArrayList<ViewInterface> viewList; not sure why we need a list of ViewInterfaces here - vf
		
		@Override		
		public void addModel(ModelInterface m) throws RemoteException{
			model = m;
		}
		@Override 
		public void addView(ViewInterface v) throws RemoteException{
			view = v;
		}
		
		// Buttons and Action Listeners
		@Override
		public JButton getBuyButton() throws RemoteException{
			JButton buyButton = new JButton("Buy");
			buyButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					System.out.println("Debug-Controller: " + "Buy button pressed");
					//try catch by HJ
					try {
						model.buyDeed();
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				} 
			});
			return buyButton;
		}
		@Override
		public JButton getRollDiceButton() throws RemoteException{
			JButton rollButton = new JButton("Roll");
			  
			rollButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					System.out.println("Debug-Controller: " + "Roll Dice button pressed");
					//try catch by HJ
					try {
						model.rollDice();
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				} 
			  });
			  return rollButton;
		}
		@Override
		public JButton getEndTurnButton() throws RemoteException{
			JButton endTurnButton = new JButton("End Turn");
			
			endTurnButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					System.out.println("Debug-Controller: "+ "End turn button pressed");
					//try catch by HJ
					try {
						model.endTurn();
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
			});
			return endTurnButton;
		}
		@Override
		public JButton getMyPropertiesButton() throws RemoteException{
			JButton myPropertiesButton = new JButton("My properties");
			myPropertiesButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					System.out.println("My Deeds button pressed!");
					
					 
					//try catch by HJ 
					// type cast model.getDeeds() by HJ
					try {
						view.chooseDeeds((HashSet<Square>)model.getDeeds());
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					
				}
			});
			return myPropertiesButton;
		}
		@Override
		public JButton getEndGameButton() throws RemoteException{
			JButton endGameButton = new JButton("End Game");
			endGameButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					System.out.println("End Game button pressed!");
					/*
					 * 
					//try catch by HJ
					try {
						JOptionPane.showMessageDialog(null, model.endGame());
					} catch (HeadlessException | RemoteException e1) {
						e1.printStackTrace();
					}
					// add try catch by tj
					try {
						view.dispose();
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					 */
				}
			});
			return endGameButton;
		}
		@Override
		public void auctionMenu(Square s) throws RemoteException{	
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