package environment;

import static org.junit.Assert.*;

import org.junit.Test;

import user.User;

public class ProductTests {
	private AuctionHall testHall = new AuctionHall();
	

	private String testname = "testname";
	private User testUser = new User("Pierre", "lante", "login100", "pass", new Price(10, Currency.EURO), testHall);
	private User raisePriceUser = new User("Matthias", "galante", "login102", "pass", new Price(10, Currency.EURO), testHall); 
	private User owner = new User("Product", "Owner", "login103", "pass", new Price(20, Currency.EURO), this.testHall);
	
	private Product testproduct = new Product(owner, new Price(750, Currency.EURO), "Coffe Table");
	private Product testUserProduct = new Product(testUser, new Price(10, Currency.EURO), testname);
	
	@Test
	public void calltopublish(){
		testproduct.calltopublish(owner);
		assertEquals(true, testproduct.getPublic());
	}
	
	
	@Test
	public void testProduct() {

		assertNotNull(this.testUserProduct);
		
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
		
		//Testing with bad Price
		try { 
			testBadPrice = new Product(testUser, null, testname);
		}catch(Exception e){
			System.err.println("TestProduct: Price Error");
			assertNull(testBadPrice);
		}
		//Testing with bad name
		try { 
			testBadName = new Product(testUser, new Price(10, Currency.EURO), null);
		}catch(Exception e){
			System.err.println("TestProduct: Name Error");
			assertNull(testBadName);
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
		assertEquals(owner, testproduct.getOwner());
	}

	@Test
	public void testGetHighestPriceUser() {
		User u = new User("price", "raiser", "login104", "pass", new Price(20, Currency.EURO), this.testHall);
		this.testHall.addUser(u);
		this.testHall.addUser(this.testUser);
		this.testHall.addAuction(this.testUserProduct);
		this.testUserProduct.raisePrice(u, new Price(50000, Currency.EURO));
		
		assertNotNull(this.testUserProduct.getHighestPriceUser());
		assertEquals(u, this.testUserProduct.getHighestPriceUser());
	}

	@Test
	public void testRaisePrice() {
		this.testHall.addUser(this.raisePriceUser);
		this.testHall.addUser(this.testUser);
		this.testHall.addAuction(this.testUserProduct);
		
		this.testUserProduct.raisePrice(this.raisePriceUser, new Price(5, Currency.EURO));
		assertEquals(10, this.testUserProduct.getCurrentPrice().getValue(),0);
	
		this.testUserProduct.raisePrice(this.raisePriceUser, new Price(15, Currency.EURO));
		assertEquals(15, this.testUserProduct.getCurrentPrice().getValue(),0);
	}

	@Test
	public void testEquals() {
		Product p = new Product(testUser, new Price(15, Currency.EURO), "name");
		Product q = new Product(testUser, new Price(15, Currency.EURO), "name");

		assertNotSame(p, q);
		assertEquals(true, p.equals(q));
	}
	

}
