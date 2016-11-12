package cs414.a5.distro;

public class PlayerDummy {
	private String name;
	private boolean hasName;
	
	public PlayerDummy(){
		name = "";
		hasName = false;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String s){
		name = s;
		hasName = true;
	}
	
	public boolean hasName(){
		return hasName;
	}
}
