/* 
 * Team cs414d
 */
package cs414.a5;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.LinkedHashMap;


public class Board {
	private Square start;
	private String SqKey;
	private HashMap<String, Square> mySquares;
	

	DeckOfCards chanceDeck;
	DeckOfCards comDeck;
	
	public Board(){
		mySquares = new LinkedHashMap<String,Square>();
	}
	
	
	

	public void initCards(){
		Card[] chanceCards = new Card[16];

		chanceCards[0] = new Card("Advance to Go.","GO",0); // get the number of moved needed to go to GO
		chanceCards[1] = new Card("Advance to Mayfair.","MAYFAIR",0);
		chanceCards[2] = new Card("You are assessed for street sepairs.","pay",100);
		chanceCards[3] = new Card("Go to Jail.","toJail",0);
		chanceCards[4] = new Card("Bank pays you Dividend of $50.","collect",50);
		chanceCards[5] = new Card("Go back 3 spaces.","move",37); //we don't have move back
		chanceCards[6] = new Card("Pay school fees of $150.","pay",150);
		chanceCards[7] = new Card("Make general repairs","pay",50);
		chanceCards[8] = new Card("Speeding fine $15.","pay",15);
		chanceCards[9] = new Card("You have won a Crossword Competition, Collect $100.","collect",100);
		chanceCards[10] = new Card("Your building and loan matures, Collect $150.","collect",150);
		chanceCards[11] = new Card("Get out of jail free","outJail",0);
		chanceCards[12] = new Card("Advance to Traflgar Square","TRAFLGAR SQUARE",0);
		chanceCards[13] = new Card("Take a trip to Marylebone Station.","MARYLEBONE STATION",0);
		chanceCards[14] = new Card("Advance to Pall Mall.","PALL MALL",0);
		chanceCards[15] = new Card("\"Drunk in Charge\", Fine $20.","pay",20);
		
		this.chanceDeck = new DeckOfCards(chanceCards);

		Card[] comCards = new Card[16];
		
		comCards[0] = new Card("Income tax refund, Collect $20","collect",20);
		comCards[1] = new Card("From sale of stock you get $50.","collect",50);
		comCards[2] = new Card("It is your birthday, Collect $20","collect",20);
		comCards[3] = new Card("Receive interest on 7% preference shares, Collect $25.","collect",25);
		comCards[4] = new Card("Get out of Jail free","outJail",0);
		comCards[5] = new Card("Advance to Go.","GO",0);
		comCards[6] = new Card("Pay hospital $100","pay",100);
		comCards[7] = new Card("You have won second prize in a beauty contest, Collect $10","collect",10);
		comCards[8] = new Card("Bank error in your favor, Collect $200","collect",200);
		comCards[9] = new Card("You inherit $100","collect",100);
		comCards[10] = new Card("Go to Jail.","toJail",0);
		comCards[11] = new Card("Pay your insurance premium $50","pay",50);
		comCards[12] = new Card("Pay a $10 Fine","pay",10);
		comCards[13] = new Card("Doctor's fee pay, $50 ","pay",50);
		comCards[14] = new Card("Go to Old Kent Road","OLD KENT ROAD",0);
		comCards[15] = new Card("Annuity Matures, Collect $100","collect",100);

		this.comDeck = new DeckOfCards(comCards);
		
	}
	
	public Square initialize(){
		/*
		 *  Board layout is based on traditional Monopoly Game US version
		 *  From 'Start' Square go counter clockwise.
		 *  As per the assignment: 
		 *  "While there will be positions on the monopoly board for Chance and Community Chest, 
		 *   you do not need to implement the corresponding cards."
		 */
		  
		// ***************** row 1 *********************
		start = new Square(Color.GRAY,"GO");
		Deed kr = new Deed(Color.BLUE,"OLD KENT ROAD",60,50,50,50);
		Square cc1 = new Square(Color.GRAY,"COMMUNITY CHEST");
		Deed wr = new Deed(Color.BLUE,"WHITECHAPEL ROAD",60,50,50,50);
		Square it = new Square(Color.GRAY,"INCOME TAX");
		RailRoad kc = new RailRoad(Color.GRAY,"KINGS CROSS STATION",200,50);
		Deed ai = new Deed(Color.CYAN,"THE ANGEL ISLINGTON",100,50,50,50);
		Square ch1 = new Square(Color.GRAY,"CHANCE");
		Deed er = new Deed(Color.CYAN,"EUSTON ROAD",100,50,50,50);
		Deed pr = new Deed(Color.CYAN,"PENTONVILLE ROAD",120,50,50,50);
		Square ja = new Square(Color.GRAY,"JAIL");
		
		// Adding first 11 squares to list
		add("GO", start);
		add("OLD KENT ROAD",kr);
		add("COMMUNITY CHEST 1",cc1);
		add("WHITECHAPEL ROAD",wr);
		add("INCOME TAX",it);
		add("KINGS CROSS STATION",kc);
		add("THE ANGEL ISLINGTON",ai);
		add("CHANCE 1",ch1);
		add("EUSTON ROAD",er);
		add("PENTONVILLE ROAD",pr);
		add("JAIL",ja);
		
		//****************** row 2 **********************
		Deed mr = new Deed(Color.GREEN,"MAYFAIR",400,50,50,50);
		add("MAYFAIR",mr);
		// Dummy squares
		for(int i=0;i<9;i++){
			mySquares.put("dummy"+i,new Square(Color.LIGHT_GRAY,""));
		}
		Deed pm = new Deed(Color.MAGENTA,"PALL MALL",140,50,50,50);
		add("PALL MALL",pm);
		
		// **************** row 3 ********************
		Square lt = new Square(Color.GRAY,"LUXURY TAX");
		add("LUXURY TAX",lt);
		// Dummy squares
		for(int i=9;i<18;i++){
			mySquares.put("dummy"+i,new Square(Color.LIGHT_GRAY,""));
		}
		Utility ec = new Utility(Color.GRAY, "ELECTRIC COMPANY", 150, 25);
		add("ELECTRIC COMPANY",ec);
		
		// ****************** row 4 ********************
		Deed pl = new Deed(Color.GREEN,"PARK LANE",350,50,50,50);
		add("PARK LANE",pl);
		// Dummy squares
		for(int i=18;i<27;i++){
			mySquares.put("dummy"+i,new Square(Color.LIGHT_GRAY,""));
		}
		Deed wh = new Deed(Color.MAGENTA,"WHITEHALL",140,50,50,50);
		add("WHITEHALL",wh);
		
		// ****************** row 5 ********************
		Square ch3 = new Square(Color.GRAY,"CHANCE");
		add("CHANCE 3",ch3);
		// Dummy squares
		for(int i=27;i<36;i++){
			mySquares.put("dummy"+i,new Square(Color.LIGHT_GRAY,""));
		}
		Deed na = new Deed(Color.MAGENTA,"NORTHUMRLD AVENUE",160,50,50,50);
		add("NORTHUMRLD AVENUE",na);
		
		// row 6
		RailRoad lsr = new RailRoad(Color.GRAY,"LIVERPOOL ST. STATION",200,50);
		add("LIVERPOOL ST. STATION",lsr);
		// Dummy squares
		for(int i=36;i<45;i++){
			mySquares.put("dummy"+i,new Square(Color.LIGHT_GRAY,""));
		}
		RailRoad ms = new RailRoad(Color.GRAY,"MARYLEBONE STATION",200,50);
		add("MARYLEBONE STATION",ms);
		
		// row 7
		Deed bn = new Deed(Color.GREEN,"BOND STREET",320,50,50,50);
		add("BOND STREET",bn);
		// Dummy squares
		for(int i=45;i<54;i++){
			mySquares.put("dummy"+i,new Square(Color.LIGHT_GRAY,""));
		}	
		Deed bs = new Deed(Color.ORANGE,"BOW STREET",180,50,50,50);
		add("BOW STREET",bs);
		
		// row 8
		Square cc3 = new Square(Color.GRAY,"COMMUNITY CHEST");
		add("COMMUNITY CHEST 3",cc3);
		// Dummy squares
		for(int i=54;i<63;i++){
			mySquares.put("dummy"+i,new Square(Color.LIGHT_GRAY,""));
		}
		Square cc2 = new Square(Color.GRAY,"COMMUNITY CHEST");
		add("COMMUNITY CHEST",cc2);
		
		// ******************** row 9 *****************
		Deed os = new Deed(Color.GREEN,"OXFORD STREET",300,50,50,50);
		add("OXFORD STREET",os);
		// Dummy squares
		for(int i=63;i<72;i++){
			mySquares.put("dummy"+i,new Square(Color.LIGHT_GRAY,""));
		}
		Deed mso = new Deed(Color.ORANGE,"MARLBOROUGH STREET",180,50,50,50);
		add("MARLBOROUGH STREET",mso);
		
		// **************** row 10 **********************
		Deed rs = new Deed(Color.GREEN,"REGENT STREET",300,50,50,50);
		add("REGENT STREET",rs);
		// Dummy squares
		for(int i=72;i<81;i++){
			add("dummy"+i,new Square(Color.LIGHT_GRAY,""));
		}
		Deed vs = new Deed(Color.ORANGE,"VINE STREET",200,50,50,50);
		add("VINE STREET",vs);
		
		// **************** row 11 ********************
		Square gj = new Square(Color.GRAY,"GO TO JAIL");
		Deed py = new Deed(Color.YELLOW,"PICCADILLY",280,50,50,50);
		Utility ww = new Utility(Color.GRAY, "WATER WORKS", 150, 25);
		Deed cs = new Deed(Color.YELLOW,"CONVENTRY STREET",260,50,50,50);
		Deed ls = new Deed(Color.YELLOW,"LEICESTER SQUARE",260,50,50,50);
		RailRoad fsr = new RailRoad(Color.GRAY,"FENCHURCH ST. STATION",200,50);
		Deed ts = new Deed(Color.RED,"TRAFLGAR SQUARE",240,50,50,50);
		Deed fs = new Deed(Color.RED,"FLEET STREET",220,50,50,50);
		Square ch2 = new Square(Color.GRAY,"CHANCE");
		Deed sr = new Deed(Color.RED,"STRAND",220,50,50,50);
		Square fp = new Square(Color.GRAY,"FREE PARKING");
		
		add("GO TO JAIL",gj);
		add("PICCADILLY",py);
		add("WATER WORKS",ww);
		add("CONVENTRY STREET",cs);
		add("LEICESTER SQUARE",ls);
		add("FENCHURCH ST. STATION",fsr);
		add("TRAFLGAR SQUARE",ts);
		add("FLEET STREET",fs);
		add("CHANCE 2",ch2);
		add("STRAND",sr);
		add("FREE PARKING",fp);
		
		// Connection of first 10 squares
		start.setNext(kr);
		kr.setNext(cc1);
		cc1.setNext(wr);
		wr.setNext(it);
		it.setNext(kc);
		kc.setNext(ai);
		ai.setNext(ch1);
		ch1.setNext(er);
		er.setNext(pr);
		
		// Connection of second 11 squares
		pr.setNext(ja);
		ja.setNext(pm);
		pm.setNext(ec);
		ec.setNext(wh);
		wh.setNext(na);
		na.setNext(ms);
		ms.setNext(bs);
		bs.setNext(cc2);
		cc2.setNext(mso);
		mso.setNext(vs);
		
		// Connection of third 10 squares
		vs.setNext(fp);
		fp.setNext(sr);
		sr.setNext(ch2);
		ch2.setNext(fs);
		fs.setNext(ts);
		ts.setNext(fsr);
		fsr.setNext(ls);
		ls.setNext(cs);
		cs.setNext(ww);
		ww.setNext(py);
		
		// Connection of fourth 10 squares
		py.setNext(gj);
		gj.setNext(rs);
		rs.setNext(os);
		os.setNext(cc3);
		cc3.setNext(bn);
		bn.setNext(lsr);
		lsr.setNext(ch3);
		ch3.setNext(pl);
		pl.setNext(lt);
		lt.setNext(mr);
	
		// connect back to GO
		mr.setNext(start);
		return start;
	}
	
	public void move(int steps, Token t){
		for(int i=0;i<steps;i++){
			t.move();
		}
	}
	
	public Square getStart(){
		return start;
	}
	
	public LinkedHashMap<String, Square> getSquares(){
		return (LinkedHashMap<String, Square>) mySquares;
	}
	
	private void add(String key, Square s){
		mySquares.put(key, s);
	}
}
