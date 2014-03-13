package environment;

import static org.junit.Assert.*;

import org.junit.Test;

import user.User;

public class ProductTests {
	private AuctionHall testHall = new AuctionHall();
	
	private Price p = new Price(10, Currency.EURO);
	private String testname = "testname";
	private User testUser = new User("Pierre","lante",p,testHall);
	private User highestPriceUser = new User("ezio","galante",new Price(10, Currency.EURO),testHall);
	private User raisePriceUser = new User("Matthias","galante",new Price(10, Currency.EURO),testHall); 
	private User i = new User("Product", "Owner", new Price(20, Currency.EURO), this.testHall);
	
	private Product testproduct = new Product(i, new Price(750, Currency.EURO), "Coffe Table");
	private Product testgoodProduct = new Product(testUser, p, testname);
	
	@Test
	public void testProduct() {

		assertNotNull(this.testgoodProduct);
		
		testHall.addUser(testUser);
		testHall.addUser(highestPriceUser);
		testHall.addUser(raisePriceUser);
		
		testHall.raisePrice(highestPriceUser, testgoodProduct, new Price(15, Currency.EURO));
		
		
		Product testBadUser = null;
		Product testBadPrice = null;
		Product testBadName = null;
		
		//Testing with bad User
		try { 
			testBadUser = new Product(null,p,testname);
		}catch(Exception e){
			System.err.println("TestProduct: User Error");
			assertNull(testBadUser);
		}
		
		//Testing with bad name
		try { 
			testBadName = new Product(testUser,p,null);
		}catch(Exception e){
			System.err.println("TestProduct: Name Error");
			assertNull(testBadName);
		}
		
		//Testing with bad Price
		try { 
			testBadPrice = new Product(testUser,null,testname);
		}catch(Exception e){
			System.err.println("TestProduct: Price Error");
			assertNull(testBadPrice);
		}	
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
		User u = this.testgoodProduct.getHighestPriceUser();
		assertEquals(raisePriceUser, u);
	}

	@Test
	public void testRaisePrice() {
		testHall.raisePrice(raisePriceUser, testgoodProduct, new Price(5, Currency.EURO));
		assertEquals(10, testgoodProduct.getCurrentPrice().getValue(),0);
	
		testHall.raisePrice(raisePriceUser, testgoodProduct, new Price(15, Currency.EURO));
		assertEquals(15, testgoodProduct.getCurrentPrice().getValue(),0);
	}

}
