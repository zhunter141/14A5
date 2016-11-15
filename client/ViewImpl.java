package client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/*
 * 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
 */

import javax.swing.*;

import common.ModelInterface;
import common.ViewInterface;

public class ViewImpl extends UnicastRemoteObject implements ViewInterface{
	private static final long serialVersionUID = 1L;
	public static final int DEFAULT_WIDTH = 900;
	public static final int DEFAULT_HEIGHT = 900;
	private JFrame myFrame;
	private ModelInterface model;
	
	public ViewImpl() throws RemoteException{
		super();
		myFrame = new JFrame("MonopolyGame");
		myFrame.setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
	}
	
	public void setUpGUI() throws RemoteException{
		System.out.println("Setting up GUI.");
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
}
