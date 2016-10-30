package cs414.a5test;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.*;

public class UtilityTest {
	
	private Utility myUtil;
	private final static Color COLOR = Color.YELLOW;
	private final static String NAME = "XCEL";
	private int cost = 80;
	private int rent = 15;
	@Before
	public void setUp(){
		myUtil = new Utility(COLOR,NAME,cost,rent);
		new Token("boot");
	}
	@After
	public void tearDown(){
		myUtil = null;
	}
	
	@Test
	public void testConstructorColor() {
		assertEquals(Color.YELLOW, myUtil.getColor());	
	}
	@Test
	public void testConstructorColorF() {
		assertNotEquals(Color.RED, myUtil.getColor());	
	}
	
	@Test
	public void testConstructorNm() {
		assertEquals("XCEL",myUtil.getName());	
	}
	
	@Test
	public void testConstructorNmF() {
		assertNotEquals("Massive Soft",myUtil.getName());	
	}
	
	@Test
	public void testConstructorCost() {
		assertEquals(80, myUtil.getCost());	
	}
	
	@Test
	public void testConstructorCostF() {
		assertNotEquals(10, myUtil.getCost());	
	}
	
	@Test
	public void testConstructorCost2() {
		assertEquals(15, myUtil.getRentCost());	
	}
	
	@Test
	public void testConstructorCost2F() {
		assertNotEquals(16, myUtil.getRentCost());	
	}

}