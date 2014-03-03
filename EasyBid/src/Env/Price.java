package env;



public class Price {

	private double value;
	private Currency current;
		
	
	public Price (double value, Currency current) {
		this.current = current;
		this.value=value;
	}
	
	public void conversion( double value,Currency current ) {
		
		if(value<=0 || current==null ) {
		
			System.out.println("Error price initialisation");
			return;
			
		}
		switch (current.getCurrent()) {
			
		case "D":
			value=value/current.getRate();	
			break;
			
		case "L":
			value=value/current.getRate();
			break;
			
		case "Y":
			value=value/current.getRate();
			break;
		
		case "E":
			value=value/current.getRate();
			break;
			
		default:
		System.out.println("Unknown current");
					
		}
			
		return;	
	
	}
	
	public double getValue() {
		return value;
	}

	public Currency getCurrency(){
		return current;
	}
	
	public void setValue(double value) {
		this.value=value;
	}

}
