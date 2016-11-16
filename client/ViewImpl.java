package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedHashMap;

import javax.swing.*;
import common.ModelInterface;
import common.ViewInterface;
import cs414.a5.SquareView;
import cs414.a5.Square;

public class ViewImpl extends UnicastRemoteObject implements ViewInterface{
	private static final long serialVersionUID = 1L;
	public static final int DEFAULT_WIDTH = 900;
	public static final int DEFAULT_HEIGHT = 900;
	private JFrame myFrame;
	private ModelInterface model;
	
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

	
	public ViewImpl() throws RemoteException{
		super();
		myFrame = new JFrame("MonopolyGame");
		myFrame.setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
	}
	
	public void setUpGUI() throws RemoteException{
		System.out.println("Setting up GUI.");
		startMenu();
		addMsgPanel();
		addButtonPanel();
		setupBoard();
		myFrame.setVisible(true);
	}
	
	@Override
	public void addModel(ModelInterface model) throws RemoteException {
		this.model = model;
	}

	@Override
	public void update() throws RemoteException {
		System.out.println("I need to update the board!");
	}
	
	// HELPER METHODS
	private void startMenu() throws RemoteException{	
		String playerName = JOptionPane.showInputDialog("Enter your name "); 
		model.addPlayer(playerName);
	}
	
	private void addMsgPanel() {
		// initialization
		gameMsgPanel = new JPanel();
		gameMsgPanel.setBackground(Color.cyan);
		gameMsgPanel.setLayout(new GridLayout(2,0));

		// msgTextFiled initialization 
		msgTextArea = new JTextArea(30,20);
		msgTextArea.setEditable(false);
		JScrollPane scroll = new JScrollPane (msgTextArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		// Add a textfield to the gameMsgPanel
		gameMsgPanel.add(scroll);
	
		// add gameMsgPanel to MonopolyGameFrame
		myFrame.add(gameMsgPanel, BorderLayout.EAST);
	}
	
	private void addButtonPanel() {
		// setup button panel
		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.blue);
		buttonPanel.setLayout(new GridLayout(4,2));

		// Buttons initialization
		buyButton = new JButton("Buy");
		rollButton = new JButton("Roll");
		endTurnButton = new JButton("End Turn");
		endTurnButton.setEnabled(false);

		buildButton = new JButton("Build");
		endGameButton = new JButton("End Game");
		
		// Add buttons to buttonPanel
		buttonPanel.add(buyButton);
		buttonPanel.add(rollButton);
		buttonPanel.add(endTurnButton);
		buttonPanel.add(buildButton);
		buttonPanel.add(endGameButton);
		
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
}
