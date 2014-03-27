package environment;

import static org.junit.Assert.*;

import org.junit.Test;

import user.User;

public class ProductTests {
	private AuctionHall testHall = new AuctionHall();
	

	private String testname = "testname";
	private User testUser = new User("Pierre", "lante", "login100", "pass", new Price(10, Currency.EURO), testHall);
	private User raisePriceUser = new User("Matthias", "galante", "login102", "pass", new Price(100, Currency.EURO), testHall); 
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
		System.out.println("_______________________  testProduct  _________________________");
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
		System.out.println("_______________________  testGetName  _________________________");
		assertEquals("Coffe Table", testproduct.getName());
	}

	@Test
	public void testGetCurrentPrice() {
		System.out.println("_______________________  testGetCurrentPrice  _________________________");
		assertEquals(750, testproduct.getCurrentPrice().getValue(),0);
		assertEquals(Currency.EURO, testproduct.getCurrentPrice().getCurrency());
	}

	@Test
	public void testGetOwner() {
		System.out.println("_______________________  testGetOwner  _________________________");
		assertEquals(owner, testproduct.getOwner());
	}

	@Test
	public void testGetHighestPriceUser() {
		System.out.println("_______________________  testGetHighestPriceUser  _________________________");
		User u = new User("price", "raiser", "login104", "pass", new Price(100000, Currency.EURO), this.testHall);
		this.testHall.addUser(u);
		this.testHall.addUser(this.testUser);
		this.testHall.addAuction(this.testUserProduct);
		this.testUserProduct.raisePrice(u, new Price(50000, Currency.EURO));
		
		assertNotNull(this.testUserProduct.getHighestPriceUser());
		assertEquals(u, this.testUserProduct.getHighestPriceUser());
	}

	@Test
	public void testRaisePrice() {
		System.out.println("_______________________  testRaisePrice  _________________________");
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
		System.out.println("_______________________  testEquals  _________________________");
		Product p = new Product(testUser, new Price(15, Currency.EURO), "name");
		Product q = new Product(testUser, new Price(15, Currency.EURO), "name");

		assertNotSame(p, q);
		assertEquals(true, p.equals(q));
	}
	
	@Test
	public void testRealiseSale(){
		System.out.println("_______________________  testRealiseSale  _________________________");
		User one = null;
		User two = null;
		try{
			one = new User("Number", "One", "login210", "pass", new Price(420, Currency.EURO), this.testHall);
			two = new User("Number", "two", "login211", "pass", new Price(520, Currency.EURO), this.testHall);
		} catch(Exception e){
			assertNull(one);
			assertNull(two);
		}
		
		assertNotNull(one);
		assertNotNull(two);
		testHall.addUser(one);
		testHall.addUser(two);
		
		//currentUser.publish(t);
		Product inQuestion = new Product(one, new Price(220, one.getCurrency()), "testSaleProduct");
		inQuestion.setProductTime(10);
		
		one.addtoMyProductList(inQuestion);
		one.publish(inQuestion);
		
		inQuestion.raisePrice(two, new Price(420, Currency.EURO));
		
		while(inQuestion.getRemainingTime()>0);
		inQuestion.realiseSale();
		
		assertEquals(100, two.getMoney().getValue(), 0.01);
		assertEquals(840, one.getMoney().getValue(), 0.01);
		if(!two.equals(inQuestion.getOwner()))
			fail("User bought the product and is not owner");
	}
}
