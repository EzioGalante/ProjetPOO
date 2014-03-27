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
	private BidTimer counter = null;
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
		this.counter = new BidTimer();
		
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
	private void setOwner(User u){
		this.owner = u;
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
		if (this.owner.equals(u) && (this.getHighestPriceUser()==null || getRemainingTime()<=0)){
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
					.println("[Product][raisePrice] : Not accepting context price, it is lower or equal to the current sale price.");
			return;
		}
		
		if(!contestingPrice.isWorthMore(u.getMoney())){
			System.out
			.println("[Product][raisePrice] : Not accepting context price, it is more than "+u.getLogin()+" can pay.");
			return;
		}

		this.setHighestPriceUser(u);
		
		System.out.println("[Product][raisePrice] : The price was raised from "
				+currentPrice.getValue()+" "+currentPrice.getCurrency()
				+ " to "+contestingPrice.getValue()+" "+contestingPrice.getCurrency()+".");
		
		contestingPrice.convertTo(owner.getCurrency());
		this.currentPrice = contestingPrice;
		
	}

	@Override
	public String toString() {
		
		String result = "Product :\n\tOwner :"+owner.getFirstname()+" "+ owner.getLastname()
				+"\n\tprice= "+currentPrice.getValue()+" "+currentPrice.getCurrency()+" name: "+this.getName();
		if(highestPriceUser != null)
			result += "\n\tOffer from : "+highestPriceUser.getLogin();
		if(endOfSale != null)
			result += "\n\n\t\tTime remaining : "+getRemainingTime()+"\n";
		
		return result;
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
		if(!isPublic || endOfSale==null){
			return -100;
		}
			
		counter.refreshTime();
		return this.endOfSale.getTime()-counter.getTime();
	}

	public void realiseSale() {
		System.out.println("[Product][realiseSale] : The sale is about to take place.\n");
		if(endOfSale == null || t == null)
		{
			System.out.println("No time defined for this auction.");
			return;
		}
		if(getRemainingTime()>=0){
			System.out.println("Still time left for bidding.");
			return;
		}
		if(!calltounpublish(owner))
		{
			System.out.println("Unable to unpublish this product");
			return;
		}
		
		Price toBePayed = highestPriceUser.pay(currentPrice, owner);
		
		if(!toBePayed.equals(currentPrice))
		{
			System.out.println("The price was not payed.");
			return;
		}
		else
		{
			System.out.println("\tThe price "+toBePayed.getValue()+" "+toBePayed.getCurrency()
					+" was payed by : "+highestPriceUser.getLogin()+"\n");
		}
		highestPriceUser.addtoMyProductList(this);
		highestPriceUser.removeFromMyAuctionList(this);
		owner.removeFromMyProducList(this);
		
		System.out.println("[Product][realiseSale] : Product "+name+" sold by "
				+owner.getLogin()+" to "+highestPriceUser.getLogin());
		setOwner(highestPriceUser);
		
		
		
		
	}
	
}
