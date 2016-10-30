package cs414.a5test;

import static org.junit.Assert.*;

import java.awt.Color;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import cs414.a5.*;

public class SquareTests {
	private Square mySquare;
	private final static Color COLOR = Color.GREEN;
	private final static String NAME = "GO";
	@Before
	public void setUp(){
		mySquare = new Square(COLOR,NAME);
		new Token("boot");
	}
	@After
	public void tearDown(){
		mySquare = null;
	}
	@Test
	public void testConstructor1() {
		// test to see correct color has been set for this square.
		assertEquals(Color.GREEN,mySquare.getColor());
	}
	@Test
	public void testConstructor2(){
		// test to see that the name of the square has been correctly set.
		assertEquals("GO",mySquare.getName());
	}

	@Test
	public void testIsPurchaseable(){
		// test that a regular square is not purchasable by default
		assertFalse(mySquare.isPurchasable());
	}
	@Test
	public void testGetNext(){
		// check null return value when square has no reference to another square 'next'
		assertEquals(null,mySquare.getNext());
	}
	@Test 
	public void testSetNext(){
		// check correct square is returned 
		// note equals method overridden for Square class so that two 
		// Squares are equal if the have the same name. 
		Square nextSquare = new Square(Color.GRAY,"GO TO JAIL");
		mySquare.setNext(nextSquare);
		assertEquals(nextSquare, mySquare.getNext());
	}
	@Test
	public void testGetOwner1(){
		// a default square has no owner, this method should return a null reference
		assertEquals(null,mySquare.getOwner());
	}
}