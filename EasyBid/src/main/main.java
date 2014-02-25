package main;



import java.util.ArrayList;
import java.util.List;

import Env.AuctionHall;
import Users.User;


public class main {

	public static void main(String[] args) {
		
		AuctionHall hall = new AuctionHall();
		
		User User = new  User("Pierre","Hewins",1,0.0, hall);
		User User1 = new  User("Ezio","Galante",2,0.0, hall);
		
		
		hall.addUser(User);
		hall.addUser(User1);
		
		//Bidder bid = new Bidder()
		
		List<User> maliste = new ArrayList<User>();
		maliste.add(User);
		maliste.add(User1);
		
		
		System.out.println("HALL : "+hall);
		System.out.println("Users in the hall :");
		for(User i : hall.getKnownUsers())
			System.out.println(i.toString());
		System.out.println(maliste);
		
	}

}
 