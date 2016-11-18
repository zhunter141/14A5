package common;

import java.rmi.RemoteException;
import java.util.HashSet;
import cs414.a5.Square;

public interface ViewInterface extends java.rmi.Remote{
	public void setUpGUI() throws java.rmi.RemoteException;
	public void addModel(ModelInterface model) throws java.rmi.RemoteException;
	public void update() throws java.rmi.RemoteException;
	public void modifyDeed(Square myDeed) throws java.rmi.RemoteException;
	
	//HJ : Only for non error to check with Professor
	public void auctionMenu(Square s)throws java.rmi.RemoteException;
	public void chooseDeeds(HashSet<Square> myDeeds) throws java.rmi.RemoteException;
	public void dispose() throws java.rmi.RemoteException;
	public void updateBoard() throws java.rmi.RemoteException;
	public void addController(ControllerInterface controller) throws java.rmi.RemoteException;
	public void setAllButtonsTo(boolean state) throws java.rmi.RemoteException;
	public void enableEndTurn() throws java.rmi.RemoteException;
	public void disableEndTurn() throws RemoteException;
	public void enableRoll() throws java.rmi.RemoteException;
	public void disableRoll() throws java.rmi.RemoteException;
}