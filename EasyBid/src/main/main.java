package main;



import java.util.ArrayList;
import java.util.List;

import env.AuctionHall;
import env.Currency;
import env.Price;

import user.User;



public class main {

	public static void main(String[] args) {
		
		AuctionHall hall = new AuctionHall();
		User user = null;
		User user1 = null;
		
		try{
			user = new  User("Pierre","Hewins", new Price(50, Currency.EURO), hall);
			user1 = new  User("Ezio","Galante",new Price(50, Currency.EURO), hall);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return;
		}
		hall.addUser(user);
		hall.addUser(user1);
		
		System.out.println("HALL : "+hall);
		System.out.println("Users in the hall :");
		for(User i : hall.getKnownUsers())
			System.out.println(i.toString());
		//System.out.println(maliste);
		
	}

}
 