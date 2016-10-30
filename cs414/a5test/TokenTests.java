package cs414.a5test;
import cs414.a5.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TokenTests {
	private Token t1;
	private Board b1;
	private Player p1;
	@Before
	public void setUp(){
		t1 = new Token("bird");
		p1 = new Player(1,"tj",t1);
		b1 = new Board();
	}

	@Test
	public void testDescription() {
		assertEquals("bird",t1.getDescription());
		//System.out.println(t1.getDescription());
	}
	@Test
	//Ensure that the board and the token has the same current location information of the player
    public void testMove(){
		b1.initialize();
		//b1.move(1,p1.getToken());
		t1.setLoc(b1.getStart());
		t1.move();
		assertEquals(p1.getToken().getLoc().getName(),"OLD KENT ROAD");
		//System.out.println(p1.getToken().getLoc().getName());
		
	}

}
