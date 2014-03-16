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
	private User testGoodUser = new User("First", "Last", money, testHall);
	
	@Test
	public void addtoMyproductList(){
		/*Product r = new Product(testGoodUser, new Price(50, Currency.EURO), "Antique coffe set");
		assertEquals(testGoodUser.getMyproductList()., r);
		*/
		
		
		fail("Not yet implemented");
	}
	
	@Test
	public void testUser() {
		assertNotNull(this.testGoodUser);
		
		User testBadFirstNameUser = null;
		User testBadLastNameUser = null;
		User testBadPriceUser = null;
		User testBadHallUser = null;
		
		//Testing with bad first name
		try {
			testBadFirstNameUser = new User(null, "ee", new Price(20, Currency.EURO), testHall);
		} catch(Exception e){
			System.err.println("bad first name");
			assertNull(testBadFirstNameUser);
		}
		
		//Testing with bad last name
		try {
			testBadLastNameUser = new User("ee", null, new Price(20, Currency.EURO), testHall);
		} catch(Exception e){
			System.err.println("bad last name");
			assertNull(testBadLastNameUser);
		}
		
		//Testing with bad Price 
		try {
			testBadPriceUser = new User("ee", "ee", null, testHall);
		} catch(Exception e){
			System.err.println("bad Price");
			assertNull(testBadPriceUser);
		}
		
		//Testing with bad Hall
		try {
			testBadHallUser = new User("ee", "ee", new Price(20, Currency.EURO), null);
		} catch(Exception e){
			System.err.println("bad Hall");
			assertNull(testBadHallUser);
		}
	}

	@Test
	public void testGetFirstname() {
		String first = this.testGoodUser.getFirstname();
		
		assertEquals("First", first);

	}

	@Test
	public void testGetLastname() {
		String last = this.testGoodUser.getLastname();
		
		assertEquals("Last", last);
	}

	/*
	@Test
	public void testToString() {
		fail("Not yet implemented");
	}
	*/

}
