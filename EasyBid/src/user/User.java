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
	private String login = "";
	private String password = "";
	private AuctionHall hall = null;
	private Price money = null;
	private List<Product> myProductList;
	
	public User(String firstname, String lastname, String login, String pass, Price money, AuctionHall h) {
		
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
		
		else if(firstname == null || lastname == null)
			throw new NullPointerException("User failed to provide first name or last name.");
		else if(login == null)
			throw new NullPointerException("User failed to provide login.");
		else if(pass == null)
			throw new NullPointerException("User failed to provide password.");
		else if(h == null)
			throw new NullPointerException("User failed to provide password.");
		else if(money == null)
			throw new NullPointerException("User failed to provide valid money");
		else if(firstname.equals("") || lastname.equals("") || login.equals("") || pass.equals(""))
			throw new IllegalArgumentException("One of the user's fields was empty.");
		
		/*
		 * 	Si le constructeur consid�re que les param�tres d'appel sont satisfaisants :
		 */
		this.firstname=firstname;
		this.lastname=lastname;
		this.login = login;
		this.password = pass;
		this.id=currentId;
		this.money=money;
		this.hall = h;
		this.myProductList= new ArrayList<>();
		
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
	public String getLogin(){
		return login;
	}
	
	public String getPass(){
		return password;
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
		return "User [lastname=" + lastname + ", firstname=" + firstname + ",id=" + id +",money="+money.getValue()+" "+money.getCurrency()+"]";
	}
	
	@Override
	public boolean equals(Object o){
		if(this == o)
			return true;
		else if(o instanceof User){
			return this.login.equals(((User) o).login);
		}
		else
			return false;
	}
	
}
