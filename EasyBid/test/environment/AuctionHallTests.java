package environment;

import static org.junit.Assert.*;

import org.junit.Test;
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
		
		User i = new User("Product", "Owner", "login200", "pass", new Price(20, Currency.EURO), this.testHall);
		User j = new User("Buyer", "One", "login201", "pass", new Price(420, Currency.EURO), this.testHall);
		User k = new User("Buyer", "Two", "login202", "pass", new Price(50, Currency.EURO), this.testHall);
		
		this.testHall.addUser(i);
		this.testHall.addUser(j);
		this.testHall.addUser(k);
		
		assertEquals(3, this.testHall.getKnownUsers().size());
		
		assertEquals(i, this.testHall.getKnownUsers().get(0));
		assertEquals(j, this.testHall.getKnownUsers().get(1));
		assertEquals(k, this.testHall.getKnownUsers().get(2));
		
	}

	@Test
	public void testGetAuctions() {
		assertEquals(this.testHall.getAuctions().size(), 0);
		
		User i = new User("Product", "Owner", "login203", "pass", new Price(20, Currency.EURO), this.testHall);
		this.testHall.addUser(i);
		
		Product p = new Product(i, new Price(750, Currency.EURO), "Coffe Table");
		Product q = new Product(i, new Price(1450, Currency.EURO), "Comfy chair");
		Product r = new Product(i, new Price(50, Currency.EURO), "Antique coffe set");
		this.testHall.addAuction(p);
		this.testHall.addAuction(q);
		this.testHall.addAuction(r);
		
		/* La prochaine ligne verifie aussi que le produit s n'a pas été ajouté !*/
		assertEquals(3, this.testHall.getAuctions().size());
		
		assertEquals(p, this.testHall.getAuctions().get(0));
		assertEquals(q, this.testHall.getAuctions().get(1));
		assertEquals(r, this.testHall.getAuctions().get(2));
		
	}

	@Test
	public void testAddAuction() {
		User user = new User("Product", "Owner", "login204", "pass", new Price(20, Currency.EURO), this.testHall);
		Product p = new Product(user, new Price(1450, Currency.EURO), "Coffe Table");
		
		boolean lockUser = false, lockAuction = false;
		
		assertNotNull(user);
		assertNotNull(p);
		
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
	public void testremoveProduct(){
		
		Price pTest = new Price(10, Currency.EURO);
		User u = new User("Matt", "Marchel", "login205", "pass", pTest, this.testHall);
		Product test = new Product(u, new Price(15, Currency.EURO), "productTest");
		boolean boolProduct = true;
		assertNotNull(pTest);
		assertNotNull(u);
		assertNotNull(test);
		
		int lon = this.testHall.getAuctions().size();
		
		this.testHall.addUser(u);
		this.testHall.addAuction(test);
		
		int lenght = this.testHall.getAuctions().size();
		System.out.println(lenght);
		assertEquals(lon+1,lenght,0);
		this.testHall.removeProduct(test);
		
		assertEquals(lon,this.testHall.getAuctions().size(),0);
		
		for(Product p : this.testHall.getAuctions()){
			if (p == test){
				boolProduct = false;
			}
            if (boolProduct == false)
                fail("The Product was not removed frome the list");
		}
	}

    
	@Test
	public void testAddUser() {
		User u = new User("ee", "ee", "login206", "pass", new Price(20, Currency.EURO), this.testHall);
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
	public void testremoveUser() {
		
		User u1 = new User("bruce", "lee", "login207", "pass", new Price(17, Currency.EURO), this.testHall);
		assertNotNull(u1);
		boolean boolUser = true;
		
		
		int lonUser = this.testHall.getKnownUsers().size();
		this.testHall.addUser(u1);
		int lenghtUser = this.testHall.getKnownUsers().size();
		assertEquals(lonUser+1,lenghtUser,0);
		
		//System.out.println(lenght);
		
		this.testHall.removeUser(u1);
		
		assertEquals(lonUser,this.testHall.getKnownUsers().size(),0);
		
		for(User i : this.testHall.getKnownUsers()){
			if (i == u1){
				boolUser = false;
			}
            
		}
		if (boolUser == false){
			fail("The User was not remove frome thi list");
		}
	}
	
	@Test
	public void testGiveUserID(){
		User one = null;
		User two = null;
		try{
			one = new User("Number", "One", "login210", "pass", new Price(420, Currency.EURO), this.testHall);
			two = new User("Number", "two", "login211", "pass", new Price(420, Currency.EURO), this.testHall);
		} catch(Exception e){
			assertNull(one);
			assertNull(two);
		}
		
		assertNotNull(one);
		assertNotNull(two);
		
		assertEquals(1, one.getId(), 0);
		assertEquals(2, two.getId(), 0);
		
	}
}
