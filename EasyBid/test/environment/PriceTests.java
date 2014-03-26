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
		
		test.giveMoney(new Price(test.getValue()*99, Currency.LIVRE));
		test.convertTo(Currency.YEN);
		
		assertEquals(Currency.YEN, test.getCurrency());
		assertEquals(14048.3, test.getValue(), 0.01);
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
	
	@Test
	public void testGiveMoney(){
		Price test = new Price(5.5, Currency.YEN);
		
		test.giveMoney(new Price(5.5, Currency.YEN));
		assertEquals(11, test.getValue(), 0);
	}
	
	@Test
	public void testTakeMoney(){
		Price test = new Price(10, Currency.YEN);
		Price testTwo = new Price(10, Currency.LIVRE);
		
		test.takeMoney(new Price(5.5, Currency.YEN));
		assertEquals(4.5, test.getValue(), 0);
		
		test.takeMoney(new Price(5.5, Currency.LIVRE));
		assertEquals(4.5, test.getValue(), 0);
		
		testTwo.takeMoney(new Price(10, Currency.EURO));
		assertEquals(1.76, testTwo.getValue(), 0.01);
		
	}
}
