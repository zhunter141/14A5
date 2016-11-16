package common;

public interface ViewInterface extends java.rmi.Remote{
	public void setUpGUI() throws java.rmi.RemoteException;
	public void addModel(ModelInterface model) throws java.rmi.RemoteException;
	// void startMenu() throws java.rmi.RemoteException;
	public void update() throws java.rmi.RemoteException;
	
	//HJ : Only for non error to check with Professor
	public void chooseDeeds(Object deeds);
	public void dispose();
}