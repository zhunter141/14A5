package cs414.a5test;

import static org.junit.Assert.*;

import org.junit.Test;

import cs414.a5.Dice;

public class DiceTest {
	
	@Test
	public void testDice1WontStartAtZero() {
		Dice d1 = new Dice ();
		assertNotEquals(0, d1.getDie1());
	}
	
	@Test
	public void testDice2WontStartAtZero() {
		Dice d1 = new Dice ();
		assertNotEquals(0, d1.getDie2());
	}

	@Test
	public void testDiceReturnsGreaterThan0() {
		Dice d1 = new Dice ();
		assertTrue(0 < d1.roll());
	}
	
	@Test
	public void testDiceReturnsNoGreaterThan12() {
		Dice d1 = new Dice ();
		assertTrue(13 > d1.roll());
	}
	
	

}
