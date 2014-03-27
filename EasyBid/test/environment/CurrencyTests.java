package environment;

import static org.junit.Assert.*;

import org.junit.Test;


public class CurrencyTests {

	private Currency testEuroCurrency = Currency.EURO;
	private Currency testDollardCurrency = Currency.DOLLARD;
	private Currency testLivreCurrency = Currency.LIVRE;
	private Currency testYenCurrency = Currency.YEN;
	

	@Test
	public void testGetCurrent() {
		System.out.println("_______________________  testGetCurrent  _________________________");
		String EuroCurrency = this.testEuroCurrency.getCurrent();
		assertEquals("E",EuroCurrency);
	
		String DollardCurrency = this.testDollardCurrency.getCurrent();
		assertEquals("D",DollardCurrency);
	
		String LivreCurrency = this.testLivreCurrency.getCurrent();
		assertEquals("L",LivreCurrency);
	
		String YenCurrency = this.testYenCurrency.getCurrent();
		assertEquals("Y",YenCurrency);
	}

	
	
	@Test
	public void testGetRate() {
		System.out.println("_______________________  testGetRate  _________________________");
		double EuroRate = this.testEuroCurrency.getRate();
		assertEquals(1,EuroRate,0);
		
		double DollardRate = this.testDollardCurrency.getRate();
		assertEquals(1.375,DollardRate,0);
	
		double YenRate = this.testYenCurrency.getRate();
		assertEquals(140.483,YenRate,0);
		
		double LivreRate = this.testLivreCurrency.getRate();
		assertEquals(0.824,LivreRate,0);
	}

}
