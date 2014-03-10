package env;

import static org.junit.Assert.*;

import org.junit.Test;

import user.User;

public class PriceTests {

	private double testValeur = 10;
	private Currency testCurrent =  Currency.EURO;
	private Price testgoodPrice = new Price(testValeur, testCurrent );
	
	
	@Test
	public void testPrice() {
		assertNotNull(this.testgoodPrice);
	
		Price testBadValue = null;
		Price testBadCurrent = null;
		
		//Testing with bad value
		try {
			testBadValue = new Price(0,testCurrent);
		}catch( Exception e){
			System.err.println("bad value");
			assertNull(testBadValue);
		}
	
		//Testing with bad Current
		try {
			testBadCurrent = new Price(testValeur,null);
		}catch (Exception e){
			System.err.println("bad current");
			assertNull(testBadCurrent);
		}
		
		
	}

	@Test
	public void testGetValue() {
		double testvaleur = this.testgoodPrice.getValue();
		assertEquals(testValeur, testvaleur, 0);
	}
	
	@Test
	public void testGetCurrency() {
		Currency current = this.testgoodPrice.getCurrency();
		assertEquals(testCurrent,current);
	}
	
	/*@Test
	public void testConversion() {
		fail("Not yet implemented");
	}

	

	@Test
	public void testSetValue() {
		fail("Not yet implemented");
	}
*/
}
