package cs414.a5test;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import cs414.a5.*;

public class DeedTests {
	private Deed myDeed;
	private Token myToken;
	private Player p1;
	private final static Color COLOR = Color.BLUE;
	private final static String NAME = "BALTIC AVENUE";
	private final static int COST = 100;
	private final static int RENT = 40;
	private final static int HOUSECOST = 100;
	private final static int HOTELCOST = 150;
	
	@Before
	public void setUp() throws Exception {
		myDeed = new Deed(COLOR,NAME,COST,RENT,HOUSECOST,HOTELCOST);
		//myToken = new Token("boot");
		p1 = new Player(101,"p1",myToken);
	}

	@After
	public void tearDown() throws Exception {
		myDeed = null;
	}

	@Test
	public void testConstructor1() {
		// test correct color has been set
		assertEquals(Color.BLUE,myDeed.getColor());
	}
	
	@Test
	public void testConstructor1F() {
		// test correct color has been set
		assertNotEquals(Color.YELLOW, myDeed.getColor());
	}
	
	@Test
	public void testConstructor2(){
		// test to see that the name of the square has been correctly set.
		assertEquals("BALTIC AVENUE",myDeed.getName());
	}
	
	@Test
	public void testConstructor2F(){
		// test to see that the name of the square has been correctly set.
		assertNotEquals("Silent Hill",myDeed.getName());
	}
	
	@Test
	public void testIsPurchaseable(){
		// a deed is purchasable after creation because
		// it is not owned by anyone.
		assertTrue(myDeed.isPurchasable());
	}
	@Test
	public void testGetNext(){
		// check null return value when square has no reference to another square 'next'
		assertEquals(null,myDeed.getNext());
	}
	@Test 
	public void testSetNext(){
		// check correct square is returned 
		// note equals method overridden for Square class so that two 
		// Squares are equal if the have the same name. 
		Square nextSquare = new Square(Color.GRAY,"GO TO JAIL");
		myDeed.setNext(nextSquare);
		assertEquals(nextSquare, myDeed.getNext());
	}
	@Test
	public void testGetOwner1(){
		// a default square has no owner, this method should return a null reference
		assertEquals(null,myDeed.getOwner());
	}
	
	@Test
	public void testGetOwner1F(){
		// a default square has no owner, this method should return a null reference
		assertNotEquals(p1,myDeed.getOwner());
	}
	
	@Test
	public void testGetOwner2(){
		/* Some squares can be considered to be owned by the bank.
		 * Such as the Luxary Tax. This way when a player lands on this
		 * square they must pay the bank.  
		 */
		myDeed.setOwner(p1); // setOwner is returning null right now, so this won't work
		assertEquals(p1, myDeed.getOwner());
	}
}