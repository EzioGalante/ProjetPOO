package environment;

import static org.junit.Assert.*;

import org.junit.Test;


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
	
	@Test
	public void testConvertTo() {
		Price test = new Price(1, Currency.EURO);
		
		test.convertTo(Currency.DOLLARD);
		
		assertEquals(Currency.DOLLARD, test.getCurrency());
		assertEquals(1.375, test.getValue(), 0);
		

		test.convertTo(Currency.LIVRE);
		
		assertEquals(Currency.LIVRE, test.getCurrency());
		assertEquals(0.824, test.getValue(), 0);
		
		test.setValue(test.getValue()*100);
		test.convertTo(Currency.YEN);
		
		assertEquals(Currency.YEN, test.getCurrency());
		assertEquals(14048.3, test.getValue(), 0.00001);
	}


	@Test
	public void testSetValue() {
		Price test = new Price(1, Currency.EURO);
		
		test.setValue(1);
		assertEquals(1, test.getValue(), 0);
		test.setValue(222.22);
		assertEquals(222.22, test.getValue(), 0);
		test.setValue(10000);
		assertEquals(10000, test.getValue(), 0);
		test.setValue(-1);
		assertEquals(10000, test.getValue(), 0);
	}

	
	@Test
	public void testIsWorthMore(){
		Price test = this.testgoodPrice;
		boolean falseBool = true;
		Price falsePrice = null;
		
		assertFalse(test.isWorthMore(null));
		
		try {
			falsePrice = new Price(-5, Currency.EURO);
		} catch(Exception e) {
			assertNull(falsePrice);
		}
		
		try {
			falseBool = test.isWorthMore(falsePrice);
		} catch(Exception e) {
			assertFalse(falseBool);
		}
		
		assertFalse(test.isWorthMore(new Price(5, Currency.EURO)));
		assertTrue(test.isWorthMore(new Price(155, Currency.EURO)));
	}
}
