package environment;

import static org.junit.Assert.*;

import org.junit.Test;

import user.User;

public class ProductTests {
	private AuctionHall testHall = new AuctionHall();
	User i = new User("Product", "Owner", new Price(20, Currency.EURO), this.testHall);
	Product testproduct = new Product(i, new Price(750, Currency.EURO), "Coffe Table");
	
	@Test
	public void testProduct() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetName() {
		assertEquals("Coffe Table", testproduct.getName());
	}

	@Test
	public void testGetCurrentPrice() {
		assertEquals(750, testproduct.getCurrentPrice().getValue(),0);
		assertEquals(Currency.EURO, testproduct.getCurrentPrice().getCurrency());
	}

	@Test
	public void testGetOwner() {
		assertEquals(i, testproduct.getOwner());
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
