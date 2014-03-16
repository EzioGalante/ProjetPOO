package environment;

import user.User;

public class Product {

	private String name;
	//private String description;
	//private String reference;
	private Price currentPrice;
	private User owner;
	private User highestPriceUser;
	private boolean isPublic = false; 
	
	public Product(User o, Price minPrice, String name){
		if(o == null || minPrice == null || name == null)
			throw new IllegalArgumentException("Error in product builder parameters.");
		if(name == "")
			throw new IllegalArgumentException("Error, product names cannot be empty.");
		
		this.name = name;
		this.currentPrice = minPrice;
		this.owner = o;
		o.addtoMyproductList(this);
	}
	
	private void setPublic(){
		this.isPublic=true;
	}
	public void calltopublish(User u){
		if (this.owner == u){
			setPublic();
		}
	}
	public boolean getPublic() {
		return isPublic;
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
	
	public void raisePrice(User u, Price contestingPrice) {
		System.out.println("+++ [Product][raisePrice] +++");
		if(u == this.getOwner()){
			System.out.println("Owners are not allowed to raise their bids");
			return;
		}

		if(u == null){
			System.err.println("[Product][raisePrice] : null user input");
			return;
		}
		
		if(this.currentPrice.getValue() > contestingPrice.getValue()){
			System.out.println("Not accepting context price, it is lower or equal to the current sale price.");
			return;
		}
		
		System.out.println("+++ !!!![Product][raisePrice]!!!! +++");
		this.setHighestPriceUser(u);
		this.currentPrice = contestingPrice;
	}
	
	private void setHighestPriceUser(User contestingPriceUser) {
		this.highestPriceUser = contestingPriceUser;
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
