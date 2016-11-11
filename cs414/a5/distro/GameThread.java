package cs414.a5.distro;

import javax.swing.JFrame;

import cs414.a5.Controller;
import cs414.a5.Model;
import cs414.a5.View;

public class GameThread extends Thread{
	private PlayerThread p1;
	private PlayerThread p2;
	private Controller ctrl;
	private Model model;
	private View view;
	
	public GameThread(PlayerThread p1,PlayerThread p2){
		System.out.println("I am a new GameThread!");
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public void run(){
		/*
		 * 
		 */
		// Instantiate MVC
		ctrl = new Controller();
		model = new Model();
		view = new View();

		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setLocationByPlatform(true);
		
		// link everything
		view.addModel(model);
		view.addController(ctrl);
		ctrl.addModel(model);
		ctrl.addView(view);
		model.addView(view);
		
		// Add MVC to threads
		p1.setModel(model);
		p2.setModel(model);
		
		p1.setController(ctrl);
		p2.setController(ctrl);
		
		p1.setView(view);
		p2.setView(view);
		
		// Start both threads
		p1.start();
		p2.start();
		
		// 
	}
}
