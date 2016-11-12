package client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import common.ViewInterface;

public class GameClient {
	public static void main(String args[]){
		ViewInterface v;
		try {
			v = (ViewInterface) Naming.lookup("rmi://localhost:2500/GameServer");
			System.out.println("I have the remote view.");
			v.setUpGUI();
		} catch (MalformedURLException murle) {
			System.out.println("MalformedURLException");
			System.out.println(murle);
			System.exit(-1);
		} catch (RemoteException re) {
			System.out.println("RemoteException"); 
			System.out.println(re);
			System.exit(-1);
		} catch (NotBoundException nbe) {
			System.out.println("NotBoundException");
			System.out.println(nbe);
			System.exit(-1);
		}
	}
}
