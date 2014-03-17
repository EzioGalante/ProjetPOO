package environment;

import user.User;

public class Product {
	
	/*
	 *	The Product Class represents the bidding situations.
	 *	
	 *	It is created by the owner, who also gives his minimum selling price.
	 *	This owner may keep the Product private (not for sale), but may also
	 *	make it public (adds the Product to the list of sales).
	 *
	 *  Once the Product is public, the class keeps trace of the User offering
	 *  the highest bid and of course, how much that User is willing to pay.
	 *  
	 *  The bidding stops at a certain date.
	 * 
	 */
	
	private String name;
	// private String description;
	// private String reference;
	private Price currentPrice;
	private User owner;
	private User highestPriceUser;
	private boolean isPublic = false;

	public Product(User o, Price minPrice, String name) {
		if (o == null || minPrice == null || name == null)
			throw new IllegalArgumentException(
					"Error in product builder parameters.");
		if (name == "")
			throw new IllegalArgumentException(
					"Error, product names cannot be empty.");

		this.name = name;
		this.currentPrice = minPrice;
		this.owner = o;
		o.addtoMyProductList(this);
	}

	

	/*
	 * 	GETTERS : 
	 * 
	 */
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
	 * 	SETTERS :
	 * 
	 */
	private void setHighestPriceUser(User contestingPriceUser) {
		this.highestPriceUser = contestingPriceUser;
	}
	
	private void setPublic() {
		this.isPublic = true;
	}

	/*
	 * 	METHODS :
	 * 
	 */
	public boolean calltopublish(User u) {
		if (this.owner == u) {
			setPublic();
			return true;
		} else {
			System.out.println("[Product][calltopublish] : Pas la permission requise");
			return false;

		}
	}
	
	public void raisePrice(User u, Price contestingPrice) {
		if (u == this.getOwner()) {
			System.out.println("Owners are not allowed to raise their bids");
			return;
		}

		if (u == null) {
			System.err.println("[Product][raisePrice] : null user input");
			return;
		}

		if (!this.currentPrice.isWorthMore(contestingPrice)) {
			System.out
					.println("Not accepting context price, it is lower or equal to the current sale price.");
			return;
		}

		this.setHighestPriceUser(u);
		this.currentPrice = contestingPrice;
	}

	@Override
	public String toString() {
		return "Product [Owner :"+owner.getFirstname()+" "+ owner.getLastname()+", price= "+currentPrice.getValue()+" "+currentPrice.getCurrency()+" name: "+this.getName()+" ]";
	}
}
