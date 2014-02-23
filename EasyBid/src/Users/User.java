package Users;
import java.util.List;

import Env.Product;


public class User {

	private String firstname = "";
	private String lastname = "";
	private int id = 0;
	
	private double money = 0.0;
	
	//private List<Product> recentProduct;
	//private List<Product> personnalProducts
	
	public User(String firstname, String lastname, int id) {
		this.firstname=firstname;
		this.lastname=lastname;
		this.id=id;
	}
	
	
}
