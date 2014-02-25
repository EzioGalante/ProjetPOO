package Env;


public class Price {

	//private String current;
	private int value;
	
	public enum currency {
		DOLLARD ("$",1.375),
		LIVRE ("£",0.824),
		YEN ("´",140.483),
		EURO ("Û",1);
	
		private String current;
		private double taux;

		private currency(String current, double taux){
	        this.current = current;
	        this.taux=taux;
		}
		
		public String getValue(){return current;}

	}
	
	public Price (int value, String current) {
	
		this.value=value;
		
	}
	
	public int getValue() {
		return value;
	}

	
	public void setValue(int value) {
		this.value=value;
		
	}
	
	
	
}
