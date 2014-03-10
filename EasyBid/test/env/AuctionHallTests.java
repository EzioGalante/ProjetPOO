package env;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import user.User;

public class AuctionHallTests {

	private AuctionHall testHall = new AuctionHall();
	
	@Test
	public void testAuctionHall() {
		assertNotNull(this.testHall);
	}

	@Test
	public void testGetKnownUsers() {
		assertEquals(this.testHall.getKnownUsers().size(), 0);
		
		User i = new User("Product", "Owner", new Price(20, Currency.EURO), this.testHall);
		User j = new User("Buyer", "One", new Price(420, Currency.EURO), this.testHall);
		User k = new User("Buyer", "Two", new Price(50, Currency.EURO), this.testHall);
		
		this.testHall.addUser(i);
		this.testHall.addUser(j);
		this.testHall.addUser(k);
		
		assertEquals(this.testHall.getKnownUsers().size(), 3);
		
		assertEquals(this.testHall.getKnownUsers().get(0), i);
		assertEquals(this.testHall.getKnownUsers().get(1), j);
		assertEquals(this.testHall.getKnownUsers().get(2), k);
		
	}

	@Test
	public void testGetAuctions() {
		assertEquals(this.testHall.getAuctions().size(), 0);
		
		User i = new User("Product", "Owner", new Price(20, Currency.EURO), this.testHall);
		this.testHall.addUser(i);
		
		Product p = new Product(i, new Price(750, Currency.EURO), "Coffe Table");
		Product q = new Product(i, new Price(1450, Currency.EURO), "Comfy chair");
		Product r = new Product(i, new Price(50, Currency.EURO), "Antique coffe set");
		Product s = new Product(i, new Price(750, Currency.EURO), "Coffe Table");
		this.testHall.addAuction(p);
		this.testHall.addAuction(q);
		this.testHall.addAuction(r);
		
		/* La prochaine ligne verifie aussi que le produit s n'a pas été ajouté !*/
		assertEquals(this.testHall.getAuctions().size(), 3);
		
		assertEquals(this.testHall.getAuctions().get(0), p);
		assertEquals(this.testHall.getAuctions().get(1), q);
		assertEquals(this.testHall.getAuctions().get(2), r);
	}

	@Test
	public void testAddAuction() {
		User user = new User("Product", "Owner", new Price(20, Currency.EURO), this.testHall);
		Product p = new Product(user, new Price(1450, Currency.EURO), "Coffe Table");
		
		boolean lockUser = false, lockAuction = false;
		
		assertNotNull(user);
		assertNotNull(p);
		
		this.testHall.addAuction(p);
		
		for(Product i : this.testHall.getAuctions())
		{
			if(p == i)
				lockAuction = true;
		}
		if(lockAuction == true)
			fail("This product should not have been added");

		/**
		 * 	At this point lockAuction is false, otherwise test just failed.
		 * 
		 * 	A user is not allowed to add a product to the auction hall if he is not a member of it's "knownUsers" list.
		 * 
		 */
		this.testHall.addUser(user);
		for(User i : this.testHall.getKnownUsers())
		{
			if(user == i)
				lockUser = true;
		}
		if(lockUser == false)
			fail("This user should have been added");
		
		this.testHall.addAuction(p);
		
		for(Product i : this.testHall.getAuctions())
		{
			if(p == i)
				lockAuction = true;
		}
		if(lockAuction == false)
			fail("This product should have been added");
		
		
	}

	@Test
	public void testAddUser() {
		User u = new User("ee", "ee", new Price(20, Currency.EURO), this.testHall);
		assertNotNull(u);
		boolean lock = false;
		this.testHall.addUser(u);

		for(User i : this.testHall.getKnownUsers()){
			if(u == i)
			{
					lock = true;
			}
		}
		if(lock == false){
			fail("The user was not found in the list.");
		}
	}

	@Test
	public void testRaisePrice() {
		User i = new User("Product", "Owner", new Price(20, Currency.EURO), this.testHall);
		User j = new User("Buyer", "One", new Price(420, Currency.EURO), this.testHall);
		
		Product p = new Product(i, new Price(750, Currency.EURO), "Coffe Table");
		assertEquals(p.getCurrentPrice().getValue(), 750, 0);

		this.testHall.raisePrice(i, p, new Price(850, Currency.EURO));
		assertEquals(750, p.getCurrentPrice().getValue(), 0);
		assertNotSame(p.getHighestPriceUser(), i);
		
		this.testHall.raisePrice(j, p, new Price(600, Currency.EURO));
		assertEquals(750, p.getCurrentPrice().getValue(), 0);
		assertNotSame(p.getHighestPriceUser(), j);
		
		p.raisePrice(j, new Price(800, Currency.EURO));
		assertEquals(800, p.getCurrentPrice().getValue(), 0);
		assertEquals(p.getHighestPriceUser(), j);
	}

}
