package Env;



public class Price {

	//private String current;
	private int value;
	private Currency current;
		
	public Currency getCurrency(){
		return current;

	}
	
	public Price (int value, Currency current) {
	
		switch(current.getCurrent()){
			case "$":
				//code
				break;
		}
		this.value=value;
		
	}
	
	public int getValue() {
		return value;
	}

	
	public void setValue(int value) {
		this.value=value;
		
	}
	
	
	
}
