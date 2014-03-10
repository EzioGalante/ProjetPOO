package environment;

import static org.junit.Assert.*;

import org.junit.Test;

import user.User;

public class ProductTests {
	private AuctionHall testHall = new AuctionHall();
	
	
	
	@Test
	public void testProduct() {
		
		fail("Not yet implemented");
	}

	@Test
	public void testGetName() {
		User i = new User("Product", "Owner", new Price(20, Currency.EURO), this.testHall);
		Product testproduct = new Product(i, new Price(750, Currency.EURO), "Coffe Table");
		//this.testHall.addUser(i);
		assertEquals("Coffe Table", testproduct.getName());
				
		//fail("Not yet implemented");
	}

	@Test
	public void testGetCurrentPrice() {
		User i = new User("Product", "Owner", new Price(20, Currency.EURO), this.testHall);
		Product testproduct = new Product(i, new Price(750, Currency.EURO), "Coffe Table");
		//this.testHall.addUser(i);
		assertEquals(750, testproduct.getCurrentPrice().getValue(),0);
		assertEquals(Currency.EURO, testproduct.getCurrentPrice().getCurrency());
		
		
		
		//fail("Not yet implemented");
	}

	@Test
	public void testGetOwner() {
		User i = new User("Product", "Owner", new Price(20, Currency.EURO), this.testHall);
		Product testproduct = new Product(i, new Price(750, Currency.EURO), "Coffe Table");
		
		assertEquals(i, testproduct.getOwner());
		//fail("Not yet implemented");
	}

	@Test
	public void testGetHighestPriceUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testRaisePrice() {
		fail("Not yet implemented");
	}

}
