/* 
 * Team cs414d
 */
package cs414.a5test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import cs414.a5.*;

public class BoardTests {
	
	private Board board;
	@Before
	public void setUp() throws Exception {
		board = new Board();
	}
	@After
	public void tearDown() throws Exception {
		board = null;
	}

	@Test
	public void testInitialize() {
		board.initialize();
		assertNotEquals(null,board.getStart());
	}
	
	@Test
	public void testInitializeF() {
		assertEquals(null,board.getStart());
	}
	
}
