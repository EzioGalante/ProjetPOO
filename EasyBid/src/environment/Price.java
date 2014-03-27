package environment;



public class Price {

	/*
	 * Class for representing money. Different currencies may be used (see "Currency" for more details).
	 * 
	 * Defines methods for getting and setting its attributes, for changing currency and for comparing two Prices.
	 */
	
	private double value;
	private Currency current;
		
	
	public Price (double value, Currency current) {
		
		if(value <= 0)
			throw new IllegalArgumentException("[Price][Price] : A negative value is not allowed.");
		if(current == null)
			throw new IllegalArgumentException("[Price][Price] : The specified currency was not recognized.");
		
		this.current = current;
		this.value=value;
	}
	
	/*
	 * 	GETTERS :
	 * 
	 */
	public double getValue() {
		return value;
	}

	public Currency getCurrency(){
		return current;
	}
	
	
	/*
	 * 	SETTERS :
	 * 
	 */
	
	private void setRate(Currency c) {
		this.current = c;
	}
	
	public void giveMoney(Price p) {
		
		if(!p.getCurrency().equals(this.getCurrency())){
			p.convertTo(this.getCurrency());
		}
		
		this.value += p.getValue();
	}
	
	public double takeMoney(Price p) {
		
		if(!p.getCurrency().equals(this.getCurrency())){
			p.convertTo(this.getCurrency());
		}
		
		if(this.value >= p.getValue()){
			this.value -= p.getValue();
			return p.getValue();
		}
		return -1;
		//If method returned -1 it means that the money was not taken
	}
	
	/*
	 * 	METHODS :
	 * 
	 */
	public void convertTo(Currency current) {
		
		if(current==null) {
			System.out.println("[Price][convertTo] Error in initialisation : currency not found");
			return;
		}
		
		this.value = (this.value/this.getCurrency().getRate())*current.getRate();
		this.setRate(current);
	}
	
	public boolean isWorthMore(Price p){
		
		if(p == null)
			return true;
		
		double currentValue = this.getValue() / this.getCurrency().getRate();
		double paramValue = p.getValue() / p.getCurrency().getRate();
		
		return currentValue < paramValue;
		
	}

}
