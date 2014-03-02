package Env;

public enum Currency {
	DOLLARD ("D",1.375),
	LIVRE ("L",0.824),
	YEN ("Y",140.483),
	EURO ("E",1);
	
	private String current;
	private double rate;
	
	private Currency(String current, double d){
        this.current = current;
        this.rate = d;
	}
	
	public String getCurrent() {
		return current;
	}

	public double getRate() {
		return rate;
	}

	

}
