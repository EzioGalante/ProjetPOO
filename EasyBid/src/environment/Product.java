package environment;

import java.util.Date;

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
	
	private String name = "";
	// private String description;
	// private String reference;
	private Price currentPrice = null;
	private User owner = null;
	private User highestPriceUser = null;
	private boolean isPublic = false;
	private BidTimer endOfSale = null;
	private Date t = null;

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
	private void setPrivate() {
		this.isPublic = false;
	}

	/*
	 * 	METHODS :
	 * 
	 */
	public boolean calltopublish(User u) {
		if(this.getPublic()==true){
			System.out.println("[Product][calltopublish] : ce produit est deja public");
			return false;
		}
		if (this.owner.equals(u)) {
			setPublic();
			return true;
		} else {
			System.out.println("[Product][calltopublish] : Pas la permission requise pour publier ce produit.");
			return false;

		}
	}
	
	public boolean calltounpublish(User u) {
		if(this.getPublic()==false){
			System.out.println("[Product][calltounpublish] : ce produit est deja privé.");
			return false;
		}
		if (this.owner.equals(u) && this.getHighestPriceUser()==null){
			setPrivate();
			return true;
		} else {
			System.out.println("[Product][calltounpublish] : Pas la permission requise pour rendre le produit privé.");
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
		return "Product :\n\tOwner :"+owner.getFirstname()+" "+ owner.getLastname()
				+"\n\tprice= "+currentPrice.getValue()+" "+currentPrice.getCurrency()+" name: "+this.getName();
	}

	@Override
	public boolean equals(Object o){
		if(this == o)
			return true;
		else if(o instanceof Product){
			return this.name.equals(((Product) o).name);
		}
		else
			return false;
	}

	public void setProductTime(long time){
		this.t = new Date(System.currentTimeMillis()+time);
		this.endOfSale = new BidTimer(t);
	}
	public long getRemainingTime(){
		BidTimer bt = new BidTimer();
		 return this.endOfSale.getTime()-bt.getTime();
	}

	public void realiseSale() {
		if(!isPublic){
			return;
		}
		if(endOfSale == null || t == null)
			return;
		if(getRemainingTime()>=0){
			System.out.println("Still time left for bidding.");
			return;
		}
		calltounpublish(owner);
		if(highestPriceUser.pay(currentPrice, owner) == null)
		{
			System.out.println("[Product][realiseSale] : The price was not payed.");
			return;
		}
		
		highestPriceUser.addtoMyProductList(this);
		highestPriceUser.removeFromMyAuctionList(this);
		owner.removeFromMyProducList(this);
		
		
	}
	
}
