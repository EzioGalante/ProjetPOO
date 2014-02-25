package Env;



public class Price {

	private double value;
	private Currency current;
		
	
	public Price (double value, Currency current) {
		this.current = current;
		this.value=value;
	}
	
	public double getValue() {
		return value;
	}

	public Currency getCurrency(){
		return current;
	}
	
	
	public void setValue(int value) {
		this.value=value;
		
	}
	
	
	
}
