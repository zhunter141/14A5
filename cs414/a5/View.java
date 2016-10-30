package cs414.a5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import javax.swing.*;

@SuppressWarnings("serial")
public class View extends JFrame {
	public static final int DEFAULT_WIDTH = 900;
	public static final int DEFAULT_HEIGHT = 900;

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
	
	private Timer timer;
	private long startTime = -1;
	private static final long DURATION = 5000*120;//10 min
	private JLabel countDown;
	
	// Game objects
	private Model model;
	private Controller ctrl;

	public View() {
		setTitle("Monopoly Game");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	public void setUpGUI(){
		startMenu();
		addMsgPanel();
		addButtonPanel();
		setupBoard();
		model.startGame();
		setUpTimer();
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
		add(gameMsgPanel, BorderLayout.EAST);
	}

	
	private void addButtonPanel() {
		// setup button panel
		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.blue);
		buttonPanel.setLayout(new GridLayout(4,2));

		// Buttons initialization
		buyButton = ctrl.getBuyButton();
		rollButton = ctrl.getRollDiceButton();
		endTurnButton = ctrl.getEndTurnButton();
		endTurnButton.setEnabled(false);

		buildButton = ctrl.getMyPropertiesButton();
		endGameButton = ctrl.getEndGameButton();
		countDown = new JLabel("---");
		countDown.setOpaque(true);
		countDown.setBackground(Color.WHITE);
		
		// Add buttons to buttonPanel
		buttonPanel.add(buyButton);
		buttonPanel.add(rollButton);
		buttonPanel.add(endTurnButton);
		buttonPanel.add(buildButton);
		buttonPanel.add(endGameButton);
		buttonPanel.add(countDown);
		
		// Add button panel to gameMsgPanel
		gameMsgPanel.add(buttonPanel);
	}
	
	private void startMenu(){
		int numPlayers = 0;
		// Ensure the user enter the correct amount of players
		do {
			String str1 = JOptionPane.showInputDialog("Enter number of players (2 to 4)");
			try{
				numPlayers = Integer.parseInt(str1);				
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Please enter an integer value.");
			}
		}while((numPlayers < 2) || (numPlayers > 4));
		
		Token[] allTokens = model.getTokens();
	    String[] players = new String [numPlayers];
	    
	    for(int i = 0; i < numPlayers; i++){
			players[i] = JOptionPane.showInputDialog("Enter the owner of "+allTokens[i].getDescription());
			//Send model the name of each player 
			model.addPlayer(players[i]);
	    }
	}
	
	private void setUpTimer(){
		timer = new Timer(10, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(startTime < 0){
					startTime = System.currentTimeMillis();
				}
				long now = System.currentTimeMillis();
				long clockTime = now - startTime;
				if(clockTime >= DURATION){
					clockTime = DURATION;
					timer.stop();
					JOptionPane.showMessageDialog(null, model.endGame());
					dispose();
				}
				SimpleDateFormat df = new SimpleDateFormat("mm:ss");
				//System.out.println(df.format(duration - clockTime));
				countDown.setText((df.format(DURATION - clockTime)));
			}
		});
		timer.start();
	}
	
	public void addModel(Model model) {
		this.model = model;
	}

	public void addController(Controller ctrl) {
		this.ctrl = ctrl;
	}

	private void setupBoard() {
		boardPanel = new JPanel();
		boardPanel.setLayout(new GridLayout(11,11));
		
		ArrayList<Square> listOfSquares = model.getBoard().getSquares();
		
		for(int i=0;i<listOfSquares.size();i++){
			Square s = listOfSquares.get(i);
			SquareView aSquare = new SquareView(s);
			boardPanel.add(aSquare);
		}
		// add boardPanel to JFrame
		add(boardPanel);
	}

	// The function below is edited by tj
	public void chooseDeeds(HashSet<Square> myDeeds) {
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
	
	public void modifyDeed(Square myDeed){
		String options[] = {"Sell","Build House","Build Hotel","Mortgage","Unmortgage"};
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
		    	default:
		    		throw new IllegalArgumentException("You have to pick one!");
		    }  
		 }  
	}

	public void update() {
		msgTextArea.append(model.getMsg());
	}
	
	public void updateBoard(){
		System.out.println("Updating the board!");
		boardPanel.setVisible(false);
		setupBoard();
		boardPanel.setVisible(true);
	}
	
	public void disableRoll(){
		rollButton.setEnabled(false);
	}
	
	public void enableRoll(){
		rollButton.setEnabled(true);
	}
	
	public void enableEndTurn(){
		endTurnButton.setEnabled(true);
	}
	
	public void disableEndTurn(){
		endTurnButton.setEnabled(false);
	}
	
	public void dispose(){
		System.exit(0);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Controller ctrl = new Controller();
				Model model = new Model();
				View view = new View();

				view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				view.setLocationByPlatform(true);
				view.setSize(800, 600);

				// link everything
				view.addModel(model);
				view.addController(ctrl);
				ctrl.addModel(model);
				ctrl.addView(view);
				model.addView(view);

				// initialize view
				view.setUpGUI();
				view.setVisible(true);
			}
		});
	}

}
