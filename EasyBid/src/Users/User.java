package Users;
//import java.util.List;

import Env.AuctionHall;
import Env.Product;


public class User {

	private String firstname = "";
	private String lastname = "";
	private int id = 0;
	private AuctionHall hall;
	private double money = 0.0;
	
	//private List<Product> CurrentProduct;
	//private List<Product> personnalProducts;
	
	public User(String firstname, String lastname, int id, double money, AuctionHall h) {
		this.firstname=firstname;
		this.lastname=lastname;
		this.id=id;
		this.money=money;
		this.hall = h;
	}
	
	public String getFirstname(){
		return firstname;
	}
	public String getLastname(){
		return lastname;
	}
	
	
	@Override
	public String toString() {
		return "User [lastname=" + lastname + ", firstname=" + firstname + ",id=" + id +",money="+money+" ]";
	}
}
