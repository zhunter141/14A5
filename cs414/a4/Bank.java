package cs414.a4;

import java.util.HashMap;

public class Bank {

	private HashMap<Integer, Account> accounts;
	public Bank(){
		accounts = new HashMap<Integer,Account>();
	}
	//Test add correctly or not
	public void addClient(Player p){
		Account account = new Account(p);
		accounts.put(p.getId(), account);
	}
	
	//Does the account match
	//Create class Due, because the bank can't hold all data.
	public boolean payDue(Player p, int amount){
		Account curAccount =  (Account) accounts.get(p.getId());
		//box in view maybe, how we convert it

		if(willBroken(curAccount,amount) == true){
		return false;
		}
		else{
			curAccount.takeOutBalance(amount);
			return true;

		}	
	}
	
	//this due is positive
	public void deposit(Player p, int d){
		Account curAccount =  (Account) accounts.get(p.getId());
		curAccount.addInBalance(d);

	}

	private boolean willBroken(Account curAccount,int d) {
		int curBalance = curAccount.getBalance();
		if(curBalance-d < 0.0 ){
			return true;
		}
		else{
			return false;
		}		
	}
	
	public HashMap<Integer,Account> getAccountListForTest(){
		return accounts;
	}
	
	public int getBalance(Player p){
		return accounts.get(p.getId()).getBalance();

	}
	
	
}
