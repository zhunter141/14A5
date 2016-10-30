package cs414.a5test;
import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import cs414.a5.*;


public class AccountTests {
	private Bank myBank = new Bank();
	private Token token1 ;
	private Token token2 ;
	private Player p1 = new Player(1,"Alice",token1);
	private Player p2 = new Player(2,"Tom",token2);
	private Account a1 ;
	private HashMap<Integer,Account> bankAccounts ;

	
	@Before
	public void setUp(){
		myBank.addClient(p1);
		myBank.addClient(p2);
		bankAccounts = myBank.getAccountListForTest();
		a1 = (Account) bankAccounts.get(p1.getId());
	}
	
	@Test
	public void testsetOwner() {
	assertEquals(p1,a1.getOwnerOfTheAccount());
	}
	
	@Test
	public void testsetDefaultMoney() {
	assertEquals(1500,a1.getBalance());
	}
	
	@Test
	public void testTakeOutBalance() {
	a1.takeOutBalance(120);
	assertEquals(1380,a1.getBalance());
	}
	@Test
	public void testAddInBalance() {
	a1.addInBalance(12);
	assertEquals(1512,a1.getBalance());
	}

}
