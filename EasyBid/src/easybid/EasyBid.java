package easybid;

import java.io.InputStream;
import java.util.Scanner;

import user.User;
import environment.AuctionHall;
import environment.Currency;
import environment.Price;

public class EasyBid {

	private AuctionHall hall;
	private Scanner sc;
	
	
	public EasyBid(){
		this.hall= new AuctionHall();
		this.sc = new Scanner(System.in); 
		 
	}

	public void run(){
		
		while(true)
		{	
			System.out.println("What do you want to do? \nu: Creation of a new User\np: Creation of a new Product\npublish: Publish your product\nq: Quit program");
			String scan =sc.nextLine();
			
			
			switch(scan)
			{
			case "u":
				createUser();
				break;
			case "p":
				//createProduct();
				break;
			case "publish":
				publishProduct();
				break;
			case "q":
				return;
				
			default: 
			}
		}	
	}

	private void publishProduct() {
		// TODO Auto-generated method stub
		
	}

	private void createProduct() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter your user id and password to create a product\nUser id:");
		String id = sc.nextLine();
		System.out.println("User password:");
		String pwd = sc.nextLine();
		
		for(User i : )
		
		Double.parseDouble(money)
	
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
	
		Scanner sc = new Scanner(System.in);
		Price p = new Price(Double.parseDouble(money),c);
		
		System.out.println("You have to create a password user account, please enter it: ");
		String pwd = sc.nextLine();
		User u = new User(firstname, lastname, pwd,p,this.hall);
		hall.addUser(u);
		System.out.println("Your user id is :"+ u.getId()+" and your password is :"+pwd+"  please remember your id and password, there will be asked later on");
		
		
	}
}	

