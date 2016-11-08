package cs414.a5;

import java.util.HashMap;

public class Bank {

	private HashMap<Integer, Account> accounts;
	private Model monopolyModel;

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
	public String auction(Object o, int[] bits) {
		String msg = "";
		
		if(o instanceof Card){
			Card card = (Card)o;
			//no check of owner cuz given card has to be owned
			//to be continued
			
		}
		else if(o instanceof Deed){
			Deed deed = (Deed)o;
			//has owner	& no owner
			
				int[] indexAndMax =getMax(bits);
				if(indexAndMax[1] == 0){
					msg = "Nobody want this deed"+'\n';
				}
				else{
					Player winner = monopolyModel.getPlayers()[indexAndMax[0]];
					int bitAmount =  indexAndMax[1];
					if(this.payDue(winner, bitAmount) == true){
						if(deed.getOwner() != null){this.deposit(deed.getOwner(), bitAmount);}
						deed.setOwner(winner);
						deed.setPurchasable(false);
						winner.addDeed(deed);

						msg = "Congraduations! Deed "+deed.getName()+" goes to "+deed.getOwner()+'\n';
					}
					else{
						msg = "No enough money"+'\n';
					}
				}
			
			
			

			
		}
		else {
			
		}
		return msg;
		

		
	}
	private int[] getMax(int[] bits) {
		int max = bits[0];
		int[] res = {0,0};

		for (int i = 1; i < bits.length; i++) {
		    if (bits[i] > max) {
		      max = bits[i];
		      res[0] = i;
		      res[1] = max;

		    }
		}
		return res;
		
	}
	
	
	
}
