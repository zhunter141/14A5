package common;

import javax.swing.JButton;
import cs414.a5.Square;

public interface ControllerInterface extends java.rmi.Remote{
	public void addModel(ModelInterface m)throws java.rmi.RemoteException;
	public void addView(ViewInterface v)throws java.rmi.RemoteException;
	public void auctionMenu(Square s)throws java.rmi.RemoteException;
	public JButton getBuyButton()throws java.rmi.RemoteException;
	public JButton getRollDiceButton()throws java.rmi.RemoteException;
	public JButton getEndTurnButton()throws java.rmi.RemoteException;
	public JButton getMyPropertiesButton()throws java.rmi.RemoteException;
	public JButton getEndGameButton()throws java.rmi.RemoteException;
}
