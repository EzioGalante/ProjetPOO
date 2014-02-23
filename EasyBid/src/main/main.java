package main;



import java.util.ArrayList;
import java.util.List;

import Users.User;


public class main {

	public static void main(String[] args) {
		
		User User = new  User("Pierre","Hewins",1);
		User User1 = new  User("Ezio","Galante",2);
		//Bidder bid = new Bidder()
		
		List<User> maliste = new ArrayList<User>();
		maliste.add(User);
		maliste.add(User1);
		
		
	}

}
 