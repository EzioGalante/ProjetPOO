package user;
//import java.util.List;

import env.AuctionHall;
import env.Price;


public class User {

	private String firstname = "";
	private String lastname = "";
	private int id = 0;
	private AuctionHall hall = null;
	private Price money = null;
	
	//private List<Product> currentProducts;
	//private List<Product> personnalProducts;
	
	public User(String firstname, String lastname, Price money, AuctionHall h) {
		
		/*
		 * 	Pour gérer les cas d'arguments que nous ne considérons pas valides, 
		 * 	nous levons des exceptions pour quitter le constructeur.
		 * 
		 * Cela sous-entend que l'appelant de ce constructeur devra : 
		 * 	-	l'utiliser dans un "try{ ... }"
		 * 	-	Récupérer l'exception levée (si elle survient) dans un "catch (IllegalArgumentException votre_nom_dexception)"
		 */
		
		if(firstname == null || lastname == null || firstname.equals("") || lastname.equals(""))
			throw new IllegalArgumentException("User failed to provide first name or last name.");
		
		if(h == null)
			throw new IllegalArgumentException("User failed to provide valid AuctionHall");
		
		if(money == null)
			throw new IllegalArgumentException("User failed to provide valid AuctionHall");
		
		
		/*
		 * 	Si le constructeur considère que les paramètres d'appel sont satisfaisants :
		 */
		this.firstname=firstname;
		this.lastname=lastname;
		this.id=h.getKnownUsers().size();
		this.money=money;
		this.hall = h;
		
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
	
	
	@Override
	public String toString() {
		return "User [lastname=" + lastname + ", firstname=" + firstname + ",id=" + id +",money="+money+" ]";
	}
	
	
}
