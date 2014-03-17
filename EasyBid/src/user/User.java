package user;
import environment.Product;

import java.util.ArrayList;
import java.util.List;

import environment.AuctionHall;
import environment.Currency;
import environment.Price;


public class User {

	private String firstname = "";
	private String lastname = "";
	private int id = 0;
	private String password = "";
	private AuctionHall hall = null;
	private Price money = null;
	private List<Product> myProductList;
	
	public User(String firstname, String lastname, String pass, Price money, AuctionHall h) {
		
		/*
		 * 	Pour g�rer les cas d'arguments que nous ne consid�rons pas valides, 
		 * 	nous levons des exceptions pour quitter le constructeur.
		 * 
		 * Cela sous-entend que l'appelant de ce constructeur devra : 
		 * 	-	l'utiliser dans un "try{ ... }"
		 * 	-	R�cup�rer l'exception lev�e (si elle survient) dans un "catch (IllegalArgumentException votre_nom_dexception)"
		 */
		int currentId = h.giveUserID();
		
		if(currentId == 0)
			throw new IllegalArgumentException("User failed to provide correct ID.");
		
		if(firstname == null || lastname == null || firstname.equals("") || lastname.equals(""))
			throw new IllegalArgumentException("User failed to provide first name or last name.");
		if(pass == null || pass.equals(""))
			throw new IllegalArgumentException("User failed to provide password.");
		if(h == null)
			throw new IllegalArgumentException("User failed to provide valid AuctionHall");
		
		if(money == null)
			throw new IllegalArgumentException("User failed to provide valid money");
		
		
		/*
		 * 	Si le constructeur consid�re que les param�tres d'appel sont satisfaisants :
		 */
		this.firstname=firstname;
		this.lastname=lastname;
		this.password = pass;
		this.id=currentId;
		this.money=money;
		this.hall = h;
		this.myProductList= new ArrayList<>();
		
	}
	
	
	public String getPass(){
		return password;
	}
	
	public String getFirstname(){
		return firstname;
	}
	public String getLastname(){
		return lastname;
	}
	public int getId(){
		return id;
	}
	public AuctionHall getHall(){
		return hall;
	}
	public List<Product> getmyProductList(){
		return myProductList;
	}
	
	public Currency getCurrency() {
		return money.getCurrency();
	}
	
	public void addtoMyProductList(Product p){
		myProductList.add(p);
		System.out.println("[User] [addtoMyProductList] Product added to your list");
	}
	
	
	public void Publish(Product p){
		if(p.calltopublish(this)){
			this.hall.addAuction(p);
		}
	}
	
	
	public void addMoney(Price p){		
		this.money.giveMoney(p);
	}
	
	@Override
	public String toString() {
		return "User [lastname=" + lastname + ", firstname=" + firstname + ",id=" + id +",money="+money+" ]";
	}
	
	
}
