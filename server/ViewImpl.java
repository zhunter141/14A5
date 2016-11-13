package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import common.ViewInterface;
import cs414.a5.*;

public class ViewImpl extends UnicastRemoteObject implements ViewInterface {
	private View view;
	private Model model;
	private Controller ctrl;
	
	public ViewImpl() throws RemoteException {
		super();
		view = new View();
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void setUpGUI() throws RemoteException {
		view.setUpGUI();
	}
	
	@Override
	public void addModel(Model model) {
		this.model = model;
	}
	
	@Override
	public void addController(Controller ctrl) {
		this.ctrl = ctrl;
	}
	
	@Override
	public View getView(){
		return this.view;
	}
}