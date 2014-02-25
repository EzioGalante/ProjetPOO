package Env;

public enum Currency {
	DOLLARD ("$",1.375),
	LIVRE ("£",0.824),
	YEN ("´",140.483),
	EURO ("Û",1);
	
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
