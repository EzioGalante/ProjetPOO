package environment;

import static org.junit.Assert.*;

import org.junit.Test;

import user.User;

public class ProductTests {
	private AuctionHall testHall = new AuctionHall();
	

	private String testname = "testname";
	private User testUser = new User("Pierre", "lante", new Price(10, Currency.EURO), testHall);
	private User highestPriceUser = new User("ezio", "galante", new Price(10, Currency.EURO), testHall);
	private User raisePriceUser = new User("Matthias", "galante", new Price(10, Currency.EURO), testHall); 
	private User i = new User("Product", "Owner", new Price(20, Currency.EURO), this.testHall);
	
	private Product testproduct = new Product(i, new Price(750, Currency.EURO), "Coffe Table");
	private Product testgoodProduct = new Product(testUser, new Price(10, Currency.EURO), testname);
	
	@Test
	public void calltopublish(){
		testproduct.calltopublish(i);
		assertEquals(true, testproduct.getPublic());
	}
	
	
	@Test
	public void testProduct() {

		assertNotNull(this.testgoodProduct);
		
		Product testBadUser = null;
		Product testBadPrice = null;
		Product testBadName = null;
		
		//Testing with bad User
		try { 
			testBadUser = new Product(null, new Price(10, Currency.EURO), testname);
		}catch(Exception e){
			System.err.println("TestProduct: User Error");
			assertNull(testBadUser);
		}
		
		//Testing with bad name
		try { 
			testBadName = new Product(testUser, new Price(10, Currency.EURO), null);
		}catch(Exception e){
			System.err.println("TestProduct: Name Error");
			assertNull(testBadName);
		}
		
		//Testing with bad Price
		try { 
			testBadPrice = new Product(testUser, new Price(10, Currency.EURO), testname);
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
		User u = new User("price", "raiser", new Price(20, Currency.EURO), this.testHall);
		this.testHall.addUser(u);
		this.testHall.addUser(this.testUser);
		this.testHall.addAuction(this.testgoodProduct);
		this.testHall.raisePrice(u, testgoodProduct, new Price(50000, Currency.EURO));
		
		assertNotNull(this.testgoodProduct.getHighestPriceUser());
		assertEquals(u, this.testgoodProduct.getHighestPriceUser());
		assertEquals(50000, this.testgoodProduct.getCurrentPrice().getValue(), 0);
	}

	@Test
	public void testRaisePrice() {
		this.testHall.addUser(this.raisePriceUser);
		this.testHall.addUser(this.testUser);
		this.testHall.addAuction(this.testgoodProduct);
		
		this.testHall.raisePrice(this.raisePriceUser, this.testgoodProduct, new Price(5, Currency.EURO));
		assertEquals(10, this.testgoodProduct.getCurrentPrice().getValue(),0);
	
		this.testHall.raisePrice(this.raisePriceUser, this.testgoodProduct, new Price(15, Currency.EURO));
		assertEquals(15, this.testgoodProduct.getCurrentPrice().getValue(),0);
	}

}
