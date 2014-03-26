package easybid;

import java.io.FileInputStream;
import java.util.Date;
import java.util.Scanner;

import user.User;
import environment.AuctionHall;
import environment.Currency;
import environment.Price;
import environment.Product;


public class EasyBid {

	private AuctionHall hall;
	private Scanner sc;
	private User currentUser;
	
	
	public EasyBid(){
		this.hall= new AuctionHall();
		this.sc = new Scanner(System.in);
		this.currentUser = null;
	}
	
	public EasyBid(FileInputStream i){
		this.hall= new AuctionHall();
		this.sc = new Scanner(i);
		this.currentUser = null;
	}

	public void run(){
		String scan = "";
		while(true)
		{	
			System.out.println("_________________________________________________");
			if(currentUser == null){
				System.out.println("\n\nWelcome to EasyBid :\n");
				System.out.println("u: Creation of a new User\nl: Login as User\nq: Quit program\n___________\n");
				scan =sc.nextLine();
				
				switch(scan)
				{
					case "u":
						createUser();
						break;
					
					case "l":
						logIn();
						break;
					
					case "q":
						sc.close();
						return;
						
					default: 
				}
			}
			
			else {
				System.out.println("\n\nWhat do you want to do "+currentUser.getLogin()+"?\n");
				System.out.println("users: List users\nauctions: list public auctions\n" 
						+ "\nadd product: Creation of a new Product\npublish: Publish your product\nm: Show personnal product\ndelete product : delete a publish product \n "
						+ "\nq: Quit program\nlogout of EasyBid : logout\ndelete account : delete your account \n___________\n");
				
				scan =sc.nextLine();
				
				switch(scan)
				{
					
					case "delete product":
						removeProduct();
						break;
						
					case "delete account":
						removeUser();
						currentUser=null;
						break;
				
					case "users":
						showUser();
						break;
					
					case"auctions":
						showAuction();
						break;
					
					case "add product":
						createProduct();
						break;
					
					case "publish":
						publishProduct();
						break;
						
					case"my":
						showPersonnalProduct();
						break;
					
					case "logout":
						logOut();
						break;
					case "Raise Price":
						raisePrice();
						break;
						
					case "q":
						sc.close();
						return;
						
					default: 
				}
			}
		}
		
	}

	private void raisePrice() {
		
		if(hall.getAuctions().isEmpty()) {
			System.out.println("Sorry, there are no bids to raise.");
			return;
		}
		
		String newPrice = "";
		double newPriceValue = -1;
		
		System.out.println("What product do you want to raise?");
		String productToRaise = sc.nextLine();
		Price pr = null;
		Product targetProduct = null;
		
		boolean lock = false;
		for (Product p : hall.getAuctions()) {
			if ( p.getName().equals(productToRaise) && !p.getOwner().equals(currentUser)){
				lock = true;
				targetProduct = p;
				break;
			}				
		}
		if(!lock){
			System.out.println("You can't raise the price this product\n Return to EasyBid");
			return;
		}
		
		System.out.println("What is your offer for this product\nPrice :?");
		
		do{
			newPrice=sc.nextLine();
			try{ 
				newPriceValue = Double.parseDouble(newPrice);
			} catch (Exception e){
				System.out.println("Error, enter your value again :");
				newPriceValue = -1;
				continue;
			}
			
			pr = new Price(newPriceValue,currentUser.getCurrency());
			pr.convertTo(targetProduct.getOwner().getCurrency());
			
			if(pr.isWorthMore(targetProduct.getCurrentPrice()))
			{
				System.out.println("The actual price of this product is :\n"
						+targetProduct.getCurrentPrice().getValue()
						+" "+targetProduct.getCurrentPrice().getCurrency()
						+ "\nPlease refer a superiror price :");
				newPriceValue=-1;
			}
		}while(newPriceValue==-1);
		
		targetProduct.raisePrice(currentUser, pr );
	}
		
		
	private void removeUser() {
		
		System.out.println("Plesae enter your password: \n");
		String pass;
		int i=0,t=3;
		
		do {
			pass = sc.nextLine();
			
			if(!pass.equals(currentUser.getPass())) {
				i++;
				t--;
				System.out.println("Bad password, "+t+" chance more");
			}
			else break;
		}while(i<3);
		
		if( t == 0) {
			System.out.println("Back on EasyBid");
			return;
		}
		else {
			hall.removeUser(currentUser);
			System.out.println("Back on EasyBid");
			return;
		}
	}

	private void removeProduct() {
		
		if( currentUser.getmyProductList().size()==0) {
			System.out.println("You have no product to remove");
			return;
		}
		
		System.out.println("What product would you want to remove? Please enter your product name : \n");
		String removeP = sc.nextLine();
		
		for(Product p : hall.getAuctions()){		
			if(removeP.equals(p.getName()) && p.getOwner().equals(currentUser)) {
				currentUser.unpublish(p);
				System.out.println("Produit just removed");
				return;
			}
		}
		System.out.println("Specified product was not found");
	}

	private void logOut() {
		currentUser=null;
		System.out.println("logout suceeded");
		return;
	}

	private void logIn() {
		
		if(hall.getKnownUsers().isEmpty()) {
			System.out.println("you have to create an user before loggin");
			return;
		}
		
		System.out.println("Please refer your login and password:\nlogin: ");
		String login = sc.nextLine();
		System.out.println("password");
		String password = sc.nextLine();
		
		for (User i : hall.getKnownUsers()){
			if(i.getLogin().equals(login) && i.getPass().equals(password)){
			
				currentUser=i;
				System.out.println("Login suceeded");
				return;
			}
		}
		
		System.out.println("Error, check your login and password, they were not found.");
		
	}

	private void showPersonnalProduct() {
							
		if(currentUser.getmyProductList().size()==0) {
			System.out.println("[EasyBid][publishProduct] Error, your personnal product list is empty, you have to add products to your list before\n");
			return;
		}
		
		for(Product p : currentUser.getmyProductList()) {
			System.out.println(p.toString()); 
		}
		return;
	}

	
	private void publishProduct() {
		if(currentUser.getmyProductList().size()==0) {
			System.out.println("Error, your personnal list of products is empty, you have to add products to your list before publishing them \n");
			return;
		}
		
		System.out.println("\nNow refer the product you want to publish:");
		String nameProduct = sc.nextLine();
		
		if(nameProduct == null) {
			System.out.println("[EasyBid][publishProduct] : Error, no product to publish");
			return;
		}
		
		String minTime = "";
		long time = -1;
		while(time == -1){
			minTime = sc.nextLine();
			try{ 
				time = Long.parseLong(minTime);
			} catch (Exception e){
				System.out.println("Error, enter your value for "+nameProduct+" again :");
				time = -1;
			}
			if(time < 30*1000){
				System.out.println("Sorry, you cannot create an auction that lasts under 30 seconds");
				time = -1;
			}
		}
	
		for(Product t : currentUser.getmyProductList())
		{
			if (t.getName().equals(nameProduct)) {
				t.setProductTime(time);
				currentUser.publish(t);
				//currentUser.getmyProductList().remove(t);
				System.out.println("Your product was published in the AuctionHall and remove from your personnal product list");
				break;
			}
		}
		
		return;
	}

	private void createProduct() {
	
		System.out.println("Please refer your product's name :");
		String nameProduct = sc.nextLine();
		System.out.println("Now refer your product's minimum price :");
		String minPrice = "";
		
		Price p1 = null;
		while(p1==null)
		{
			minPrice = sc.nextLine();
			try{ 
				p1 = new Price(Double.parseDouble(minPrice), currentUser.getCurrency());
			} catch (Exception e){
				System.out.println("Error, enter your value again :");
				p1 = null;
			}

		}

		
		Product p = new Product(currentUser, p1, nameProduct);
		currentUser.addtoMyProductList(p);
		return;
	}


	private void createUser() {
		
		boolean lock = true;
		
		System.out.println("Please refer a firstname, a lastname and the money you dispose of with your currency (euro,dollard,yen,livre)\nfirstname:");
		String firstname = sc.nextLine();
		
		System.out.println("lastname:");
		String lastname = sc.nextLine();
		
		
		System.out.println("money you dispose of:");
		String money = sc.nextLine();
		
		Currency c = null;
		
		while(lock == true) {
			System.out.println("your currency:");
			String currency = sc.nextLine();	 
	
			switch(currency)
			{
				case"euro":
				case"euros":
					c = Currency.EURO;
					lock = false;
					break;
				case"dollard":
				case"dollards":
					c = Currency.DOLLARD;
					lock = false;
					break;
				case"yen":
				case"yens":
					c = Currency.YEN;
					lock = false;
					break;
				case"livre":
				case"livres":
					c = Currency.LIVRE;
					lock = false;
					break;
				default:
			
			}
		
		}
	
		Price p = new Price(Double.parseDouble(money),c);
		
		System.out.println("You have to create a login and a password user account, please enter it: \nLogin: ");
		String log = sc.nextLine();
		
		//Sécurité sur l'unicité du loggin
		
		for(User i : hall.getKnownUsers()) {
			while (i.getLogin().equals(log)) {
				System.out.println("An other user use already this login, please refer an other one to finish creating of your account\nNew login please :");
				log = sc.nextLine();
			}
		}
		
		System.out.println("Password: ");
		String pwd = sc.nextLine();
			
		User u = new User(firstname, lastname, log, pwd, p, this.hall);
		hall.addUser(u);
		System.out.println("User "+log+" created");
		
	}
	
	private void showUser(){
		
		if(this.hall.getKnownUsers().size() == 0){
			System.out.println("[EasyBid][showUser] : no user in the list");
			return;
		}
		else 
			System.out.println("[EasyBid][showUser] : Users :");
		
		for(User i : this.hall.getKnownUsers()){
			System.out.println(i.toString());
		}
	}
	
	private void showAuction(){
		if(this.hall.getAuctions().size() == 0){
			System.out.println("[EasyBid][showAuction] : no auctions in the list");
			return;
		}
		else 
			System.out.println("[EasyBid][showAuction] : Auctions :");
		
		for(Product p : this.hall.getAuctions()){
			System.out.println(p.toString());
		}
	}
}	
/* POUR LA SUITE:
 * 	- creation de la méthode showUser qui affiche la list d'user d'AuctionHall
 * 	- creation de la méthode showAuction qui affiche la liste de product d'Auctionhall
 * 	- creation de la méthode showPersonnalProduct qui affiche la liste perso de Product
 * 	- creation de la méthode removeUser qui permet de supprimer son compte user d'AuctionHall
 * 	- creation de la méthode removePersonnelProduct qui supprime un produit personnel de la liste perso d'un user
 */ 

