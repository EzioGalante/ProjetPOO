package Env;

import Users.User;

public class Product {

	private String name;
	//private String description;
	//private String reference;
	private Price currentPrice;
	private User owner;
	private User highestPriceUser;
	
	public Product(User o, Price minPrice, String name){
		this.name = name;
		this.currentPrice = minPrice;
		this.owner = o;
	}

	public String getName() {
		return name;
	}

	public Price getCurrentPrice() {
		return currentPrice;
	}
	
	public User getOwner() {
		return owner;
	}

	public User getHighestPriceUser() {
		return highestPriceUser;
	}
	
	/*
	 * 
	 * 
	 */
	
	public void raisePrice(User u, Price currentPrice) {
		if(this.currentPrice.getValue() > currentPrice.getValue()){
			System.out.println("Not accepting context price, it is lower or equal to the current sale price.");
			return;
		}
		setHighestPriceUser(u);
		this.currentPrice = currentPrice;
	}
	private void setHighestPriceUser(User highestPriceUser) {
		this.highestPriceUser = highestPriceUser;
	}
	

	
	/*
	public Product(User o, Price minPrice, String name, String description, String reference){
		this.name = name;
		this.currentPrice = minPrice;
		this.owner = o;
		this.description = description;
		this.reference = reference;
	}
	*/
	
	
}
