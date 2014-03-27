package users;

import static org.junit.Assert.*;

import org.junit.Test;

import environment.AuctionHall;
import environment.Currency;
import environment.Price;
import environment.Product;
import user.User;

public class UserTests {

	private AuctionHall testHall = new AuctionHall();
	private Price money = new Price(20, Currency.EURO);
	private User testGoodUser = new User("First", "Last", "login1", "pass", money, testHall);
	
	@Test
	public void testUser() {
		System.out.println("_______________________  testUser  _________________________");
		assertNotNull(this.testGoodUser);
		
		User testBadFirstNameUser = null;
		User testBadLastNameUser = null;
		User testBadPasswordUser = null;
		User testBadPriceUser = null;
		User testBadHallUser = null;
		
		//Testing with bad first name
		try {
			testBadFirstNameUser = new User(null, "ee", "login2", "pass", new Price(20, Currency.EURO), testHall);
		} catch(Exception e){
			System.err.println("[UserTests][testUser] : tested bad first name");
			assertNull(testBadFirstNameUser);
		}
		
		//Testing with bad last name
		try {
			testBadLastNameUser = new User("ee", null, "login3", "pass", new Price(20, Currency.EURO), testHall);
		} catch(Exception e){
			System.err.println("[UserTests][testUser] : tested bad last name");
			assertNull(testBadLastNameUser);
		}
		
		//Testing with bad login
		try {
			testBadPasswordUser = new User("ee", "ee", null, "pass", new Price(20, Currency.EURO), testHall);
		} catch(Exception e){
			System.err.println("[UserTests][testUser] : tested bad login");
			assertNull(testBadPasswordUser);
		}
		
		//Testing with bad password
		try {
			testBadPasswordUser = new User("ee", "ee", "login4", null, new Price(20, Currency.EURO), testHall);
		} catch(Exception e){
			System.err.println("[UserTests][testUser] : tested bad password");
			assertNull(testBadPasswordUser);
		}
		
		//Testing with bad Price 
		try {
			testBadPriceUser = new User("ee", "ee", "login5", "pass", null, testHall);
		} catch(Exception e){
			System.err.println("[UserTests][testUser] : tested bad Price");
			assertNull(testBadPriceUser);
		}
		
		//Testing with bad Hall
		try {
			testBadHallUser = new User("ee", "ee", "login6", "pass", new Price(20, Currency.EURO), null);
		} catch(Exception e){
			System.err.println("[UserTests][testUser] : tested bad Hall");
			assertNull(testBadHallUser);
		}
	}


	@Test
	public void addtoMyproductList(){
		System.out.println("_______________________  addtoMyproductList  _________________________");
		Product r = new Product(testGoodUser, new Price(50, Currency.EURO), "Antique coffe set");
		testGoodUser.addtoMyProductList(r);
		assertEquals(r, testGoodUser.getmyProductList().get(0));
	}
	
	@Test
	public void testGetFirstname() {
		System.out.println("_______________________  testGetFirstname  _________________________");
		String first = this.testGoodUser.getFirstname();
		
		assertEquals("First", first);
	}

	@Test
	public void testGetLastname() {
		System.out.println("_______________________  testGetLastname  _________________________");
		String last = this.testGoodUser.getLastname();
		
		assertEquals("Last", last);
	}

	@Test
	public void testEquals() {
		System.out.println("_______________________  testEquals  _________________________");
		User i = new User("ee", "ee", "login7", "pass", new Price(20, Currency.EURO), testHall);
		User j = i;
		User k = new User("ee", "ee", "login7", "pass", new Price(20, Currency.EURO), testHall);
		User l = new User("ee", "ee", "login8", "pass", new Price(20, Currency.EURO), testHall);
		
		assertSame(i, j);
		assertEquals(true, i.equals(j));
		
		assertNotSame(i, k);
		assertEquals(true, i.equals(k));
		
		assertEquals(false, i.equals(l));
	}
	
	@Test
	public void testPay(){
		System.out.println("_______________________  testPay  _________________________");
		User i = new User("ee", "ee", "login9", "pass", new Price(20, Currency.EURO), testHall);
		User j = new User("ee", "ee", "login10", "pass", new Price(20, Currency.EURO), testHall);
		
		Price iPayed = new Price(10, Currency.EURO);
		Price endPrice = new Price(30, Currency.EURO);
		
		assertEquals(null, i.pay(new Price(10, Currency.LIVRE), i));
		assertEquals(null, i.pay(new Price(20, Currency.LIVRE), j));
		
		assertEquals(iPayed, i.pay(iPayed, j));
		assertEquals(endPrice.getValue(), j.getMoney().getValue(), 0);
		
		
		
	}
}
