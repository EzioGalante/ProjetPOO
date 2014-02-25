package Env;


public class Price {

	private final String current="";
	private int value;
	
	public enum currency {
		DOLLARD ("$"),
		LIVRE ("£"),
		YEN ("´"),
		EURO ("Û");
	
		private currency(String current){
	        //this.current = current;
	    }


	   // public String getValue(){return current;}

	}
	
	
	
	

	
	public void setValue(int value) {
		this.value=value;
		
	}
	
	
	
}
