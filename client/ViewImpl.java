package client;

import java.awt.Color;
import java.awt.GridLayout;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
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
	
	// GUI Objects
	private JPanel boardPanel;
	
	public ViewImpl() throws RemoteException{
		super();
		myFrame = new JFrame("MonopolyGame");
		myFrame.setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
	}
	
	public void setUpGUI() throws RemoteException{
		System.out.println("Setting up GUI.");
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
	private void setupBoard() throws RemoteException {
		boardPanel = new JPanel();
		boardPanel.setLayout(new GridLayout(11,11));
		
		LinkedHashMap<String, Square> listOfSquares = model.getBoard().getSquares();
		/*
		 * 
		for(int i=0;i<listOfSquares.size();i++){
			Square s = listOfSquares.get(i);
			SquareView aSquare = new SquareView(s);
			boardPanel.add(aSquare);
		}
		 */
		// add boardPanel to JFrame
		myFrame.add(boardPanel);
	}
}
