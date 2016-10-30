package cs414.a5test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import cs414.a5.*;


public class BankTests {
	private Bank myBank = new Bank();
	private Token token1 ;
	private Token token2 ;
	private Player p1 = new Player(1,"Alice",token1);
	private Player p2 = new Player(2,"Tom",token2);
	private Account a1 ;
	private Account a2 ;
	private HashMap<Integer,Account> bankAccounts ;

	
	@Before
	public void setUp(){
		myBank.addClient(p1);
		myBank.addClient(p2);
		bankAccounts = myBank.getAccountListForTest();
		a1 = (Account) bankAccounts.get(p1.getId());
		a2 = (Account) bankAccounts.get(p2.getId());
	}
	@Test
	public void testaddClientANDAccountWithDefault() {
		HashMap<Integer,Account> bankAccounts = myBank.getAccountListForTest();
		a1 = (Account) bankAccounts.get(p1.getId());
		assertEquals(1500,a1.getBalance());
	}
	
	@Test
	public void testaddClientANDAccount(){
		int total =0;
		myBank.payDue(p1, 89);
		myBank.deposit(p2, 99);
		total = a1.getBalance()+a2.getBalance();
		
		assertEquals(3010,total);	
	}
	@Test
	public void testPayDueFalse(){
		boolean b = myBank.payDue(p1, 1700);
		assertEquals(false,b);
	}
	@Test
	public void testPayDue(){
		assertEquals(1500,a1.getBalance());		
	}
	@Test
	public void testWithdrawl(){
		myBank.deposit(p1, 99);
		assertEquals(1599,a1.getBalance());

	}
	@Test
	public void testGetBalance(){
		myBank.deposit(p1, 99);
		assertEquals(1599,myBank.getBalance(p1));

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
