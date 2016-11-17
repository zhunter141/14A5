package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import javax.swing.*;
import common.ControllerInterface;
import common.ModelInterface;
import common.ViewInterface;
import cs414.a5.SquareView;
import cs414.a5.Square;

public class ViewImpl implements ViewInterface{ 
	public static final int DEFAULT_WIDTH = 900;
	public static final int DEFAULT_HEIGHT = 900;
	private JFrame myFrame;
	private ModelInterface model;
	private ControllerInterface ctrl;
	private ArrayList<JButton> buttonArray;
	
	// Window objects
	private JButton buyButton;
	private JButton endTurnButton;
	private JButton rollButton;
	private JButton buildButton;
	private JButton endGameButton;
	
	private JPanel buttonPanel;
	private JPanel gameMsgPanel;
	private JPanel boardPanel;
	private JTextArea msgTextArea;

	
	public ViewImpl() throws RemoteException{ // remove remoteException
		myFrame = new JFrame("MonopolyGame");
		myFrame.setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		buttonArray = new ArrayList<JButton>();
	}

	public void setUpGUI() throws RemoteException{
		System.out.println("Setting up GUI.");
		addMsgPanel();
		addButtonPanel();
 		setupBoard();
 		setAllButtonsTo(false);
 		startMenu();
		myFrame.setVisible(true);
	}
	
	@Override
	public void setAllButtonsTo(boolean state) throws RemoteException{
		for(JButton b : buttonArray){
			b.setEnabled(state);
		}
	}

	@Override
	public void addModel(ModelInterface model) throws RemoteException {
		this.model = model;
	}

	@Override
	public synchronized void update() throws RemoteException {   // needs to be move to its own class?
		msgTextArea.append(model.getMsg());
	}
	
	//HJ add 
	@Override
	public synchronized void updateBoard() throws RemoteException{
		System.out.println("Updating the board!");
		setupBoard();
		boardPanel.setVisible(false);
		boardPanel.setVisible(true);	
	}
	
	// HELPER METHODS
	private void startMenu() throws RemoteException{	
		String playerName = JOptionPane.showInputDialog("Enter your name "); 
		model.addPlayer(playerName);   // client side got stuck here......
	}
	
	private void addMsgPanel() {
		// initialization
		gameMsgPanel = new JPanel();
		gameMsgPanel.setBackground(Color.cyan);
		gameMsgPanel.setLayout(new GridLayout(2,0));

		// msgTextFiled initialization 
		msgTextArea = new JTextArea(30,20);
		msgTextArea.setText("Waiting for players to connect...");
		msgTextArea.setEditable(false);
		JScrollPane scroll = new JScrollPane (msgTextArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		// Add a textfield to the gameMsgPanel
		gameMsgPanel.add(scroll);
	
		// add gameMsgPanel to MonopolyGameFrame
		myFrame.add(gameMsgPanel, BorderLayout.EAST);
	}
	
	private void addButtonPanel() throws RemoteException {
		// setup button panel
		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.blue);
		buttonPanel.setLayout(new GridLayout(4,2));

		// Buttons initialization
		buyButton = ctrl.getBuyButton();
		rollButton = ctrl.getRollDiceButton();
		endTurnButton = ctrl.getEndTurnButton();
		endTurnButton.setEnabled(false);

		buildButton = ctrl.getBuildButton();
		endGameButton = ctrl.getEndGameButton();
		
		// Add buttons to buttonPanel & buttonArray
		buttonPanel.add(buyButton);
		buttonPanel.add(rollButton);
		buttonPanel.add(endTurnButton);
		buttonPanel.add(buildButton);
		buttonPanel.add(endGameButton);
		
		buttonArray.add(buyButton);
		buttonArray.add(rollButton);
		buttonArray.add(buildButton);
		buttonArray.add(endGameButton);
		
		// Add button panel to gameMsgPanel
		gameMsgPanel.add(buttonPanel);
	}
	
	private void setupBoard() throws RemoteException {
		System.out.println("Setting up board.");
		boardPanel = new JPanel();
		boardPanel.setLayout(new GridLayout(11,11));
		
		LinkedHashMap<String, Square> listOfSquares = model.getBoard().getSquares();
		/*
		 * 
		 */
		System.out.println("size of board = "+listOfSquares.size());
		for(Square s : listOfSquares.values()){
			SquareView aSquare = new SquareView(s);
			boardPanel.add(aSquare);
			//System.out.println("adding square to view.");
		}
		// add boardPanel to JFrame
		myFrame.add(boardPanel);
	}
	
	
	public void chooseDeeds(HashSet<Square> myDeeds) throws RemoteException {
		if(myDeeds.size()==0){			
			JOptionPane.showMessageDialog( null, "You do not have any properties! \n "
			,null, JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		HashMap <String,Square> myMap = new HashMap<String, Square>();
		String labels[] = new String[myDeeds.size()];
		int i = 0;
		for(Square s: myDeeds){
			labels[i] = s.getName();
			myMap.put(labels[i], s);
			i++;
		}
		String input = (String) JOptionPane.showInputDialog(null, "Choose property to modify",
		        "Shop Smart", JOptionPane.QUESTION_MESSAGE, null, // Use
		                                                                        // default
		                                                                        // icon
		        labels, // Array of choices
		        labels[0]); // Initial choice
		if(input != null){	     
		    modifyDeed(myMap.get(input));
		}
	}
	public void dispose(){
		System.exit(0);
	}
	public void modifyDeed(Square myDeed) throws RemoteException{
		String options[] = {"Sell","Build House","Build Hotel","Mortgage","Unmortgage","Auction"};
		String decision = (String) JOptionPane.showInputDialog(null, "What would you like to do with your property?",
		        "The Choice of a Lifetime", JOptionPane.QUESTION_MESSAGE, null, // Use
		                                                                        // default
		                                                                        // icon
		        options, // Array of choices
		        options[0]); // Initial choice
		    System.out.println("I want to : "+decision);	
		 if(decision != null){
		    switch(decision){
		    	case "Sell":
					model.sellDeed(myDeed);break;
		    	case "Build House":
		    		model.buildHouse(myDeed);break;
		    	case "Build Hotel":
		    		model.buildHotel(myDeed);break;
		    	case "Mortgage":
		    		model.mortgage(myDeed);break;
		    	case "Unmortgage":
		    		model.umMortgage(myDeed);break;
		    	case "Auction":
		    		ctrl.auctionMenu(myDeed);break;
		    	default:
		    		throw new IllegalArgumentException("You have to pick one!");
		    }  
		 }  
	}

	@Override
	public void addController(ControllerInterface controller) throws RemoteException{
		this.ctrl = controller;
	}
}
