package cs414.a5test;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.*;

public class RailRoadTest {

	private Utility myRR;
	private final static String NAME = "RTD";
	private int cost = 50;
	private int rent = 10;
	@Before
	public void setUp(){
		myRR = new Utility(Color.RED, NAME, cost, rent);
		new Token("boot");
	}
	@After
	public void tearDown(){
		myRR = null;
	}
	
	@Test
	public void testConstructorColor() {
		assertEquals(Color.RED, myRR.getColor());	
	}
	
	@Test
	public void testConstructorColorF() {
		assertNotEquals(Color.BLUE, myRR.getColor());	
	}
	
	@Test
	public void testConstructorNm() {
		assertEquals("RTD",myRR.getName());	
	}
	
	@Test
	public void testConstructorNmF() {
		assertNotEquals("METRO",myRR.getName());	
	}
	
	@Test
	public void testConstructorCost() {
		assertEquals(50, myRR.getCost());	
	}
	
	@Test
	public void testConstructorCostF() {
		assertNotEquals(250, myRR.getCost());	
	}
	
	@Test
	public void testConstructorRent() {
		assertEquals(10, myRR.getRentCost());	
	}
	
	@Test
	public void testConstructorRentF() {
		assertNotEquals(81, myRR.getRentCost());	
	}

}