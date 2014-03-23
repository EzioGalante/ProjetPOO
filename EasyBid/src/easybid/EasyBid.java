package easybid;

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

	public void run(){
		String scan = "";
		while(true)
		{	
			System.out.println("What do you want to do? \n\n");
			if(currentUser == null){
				System.out.println("u: Creation of a new User\nl: Login as User\nq: Quit program\n\n");
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
				System.out.println("users: List users\nauctions: list public auctions\np: Creation of a new Product\n" +
						"publish: Publish your product\nm: Show personnal product\nq: Quit program\n");
				
				scan =sc.nextLine();
				
				switch(scan)
				{
					case "users":
						showUser();
						break;
					
					case"auctions":
						showAuction();
						break;
					
					case "p":
						createProduct();
						break;
					
					case "publish":
						publishProduct();
						break;
						
					case"m":
						showPersonnalProduct();
						break;
					
					case "logout":
						logOut();
						break;
						
					case "q":
						sc.close();
						return;
						
					default: 
				}
			}
		}
		
	}

	private void logOut() {
		// TODO Auto-generated method stub
		
	}

	private void logIn() {
		// TODO Auto-generated method stub
		
	}

	private void showPersonnalProduct() {
		
		if(this.hall.getKnownUsers().size() == 0){
			System.out.println("[EasyBid][showAuction] : You have to create a user before showing your personnal product");
			return;
		}
		
		System.out.println("Please enter your user id and password to publish a product from your personnal product list \nUser id:");
		String id = sc.nextLine();
		
		
		System.out.println("User password:");
		String pwd = sc.nextLine();
		
		
		for(User i : hall.getKnownUsers() ) 
		{	
			
			if (i.getId() == Integer.parseInt(id) )
			{
				if(pwd.equals(i.getPass()))
				{
					
					if(i.getmyProductList().size()==0) {
						System.out.println("[EasyBid][publishProduct] Error, your personnal product list is empty, you have to add products to your list before publish one of them \n");
						return;
					}
					
					System.out.println("Hello "+i.getFirstname()+"\nYour personnal product:");
					for(Product p : i.getmyProductList()) {
						System.out.println(p.toString()); 
					}
				}
			return;
			}
		}		
	
		System.out.println("You are not yet in the User list, you have to create an user");	
	}

	private void publishProduct() {
		
	
		System.out.println("Please enter your user id and password to publish a product from your personnal product list \nUser id:");
		String id = sc.nextLine();
		
		
		System.out.println("User password:");
		String pwd = sc.nextLine();
		
		String nameProduct = "";
		String minPrice = "";
		
		for(User i : hall.getKnownUsers() ) 
		{	
			
			if (i.getId() == Integer.parseInt(id) )
			{
				if(pwd.equals(i.getPass()))
				{
					
					if(i.getmyProductList().size()==0) {
						System.out.println("[EasyBid][publishProduct] Error, your personnal product list is empty, you have to add products to your list before publish one of them \n");
						return;
					}
					
					System.out.println("Hello "+i.getFirstname()+"\nNow refer the product you want to publish:");
					nameProduct = sc.nextLine();
					
					if(nameProduct == null) {
						System.out.println("[EasyBid][publishProduct] : Error, no product to publish");
						return;
					}
				
					for(Product t : i.getmyProductList())
					{
						if (t.getName().equals(nameProduct)) {
							i.Publish(t);
							System.out.println("Your product was publish to the AuctionHall");
						}
					}
					
				return;
				}
			}
		}
				
	
		System.out.println("You are not yet in the User list, you have to create an user before publish a product");	
	}
		
	

	private void createProduct() {
		
		System.out.println("Please enter your user id and password to create a product\nUser id:");
		String id = sc.nextLine();
		
		System.out.println("User password:");
		String pwd = sc.nextLine();
		
		String nameProduct = "";
		String minPrice = "";
		for(User i : hall.getKnownUsers() ) 
		{	
			
			if (i.getId() == Integer.parseInt(id) )
			{
				if(pwd.equals(i.getPass()))
				{
					System.out.println("Hello"+i.getFirstname()+"\nNow refer your name product:");
					nameProduct = sc.nextLine();
					System.out.println("Now refer your product minimum price, it's your minimum bid price for this product");
					minPrice = sc.nextLine();
				
					Price p1 = new Price(Double.parseDouble(minPrice), i.getCurrency());
					Product p = new Product(i, p1, nameProduct);
					i.addtoMyProductList(p);
					return;
				}
			}
		}		
	
		System.out.println("You are not yet in the User list, you have to create an user before create a product");		
	}

	private void createUser() {
		
		boolean lock = true;
		
		
		System.out.println("Please refer a firstname, a lastname and the money you dispose of with your currency (euro,dollard,yen,livre)\nfirstname:");
		String firstname = sc.nextLine();
		
		System.out.println("lastname:");
		String lastname = sc.nextLine();
		
		
		System.out.println("money you dispose of:");
		String money = sc.nextLine();
		//Double.parseDouble(money);
		
		
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
		case"livcres":
			c = Currency.LIVRE;
			lock = false;
			break;
		default:
		
		}
		
		}
	
		Price p = new Price(Double.parseDouble(money),c);
		
		System.out.println("You have to create a password user account, please enter it: ");
		String pwd = sc.nextLine();
		User u = new User(firstname, lastname, pwd,p,this.hall);
		hall.addUser(u);
		System.out.println("Your user id is :"+ u.getId()+" and your password is :"+pwd+"  please remember your id and password, there will be asked later on");
		
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

