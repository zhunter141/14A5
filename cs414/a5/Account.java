package cs414.a5;


public class Account {
	private int curBalance;
	private Player curOwner ; 
			
			
	public Account(Player p){

		setOwner(p);
		setDefaultBalance();
	}
	public void setOwner(Player p ){		
		this.curOwner = p;
	
	}
			
	public void setDefaultBalance(){
		this.curBalance = 1500;
	}
	
	//-
	public void takeOutBalance(int due){
		this.curBalance = curBalance-due;
	}
	//+
	public void addInBalance(int due){
		this.curBalance = curBalance+due;
	}
	public int getBalance(){
		return this.curBalance;
	}
	
	public Player getOwnerOfTheAccount(){
		return this.curOwner;
	}

}
