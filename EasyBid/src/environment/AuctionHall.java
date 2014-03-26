package environment;

import java.util.ArrayList;
import java.util.List;

import user.User;


public class AuctionHall {
	/*
	 * 
	 * Cadre d'action des echanges entre les Utilisateurs et aussi entre les Utilisateurs et les produits.
	 * 
	 * Les deux listes knownUsers et auctions repertorient les utilisateurs et les produits.
	 * 
	 */
	private List<User> knownUsers;
	private List<Product> auctions;
	private int currentID;
	
	public AuctionHall(){
		this.knownUsers = new ArrayList<User>();
		this.auctions = new ArrayList<Product>();
		this.currentID = 0;
	}

	// Getters (no setters for this class)
	public List<User> getKnownUsers() {
		return knownUsers;
	}
	public List<Product> getAuctions() {
		return auctions;
	}
	
	/*
	 * The AuctionHall class gives the users their ID numbers.
	 */
	public int giveUserID(){
		this.currentID++;
		
		if(this.currentID < 0)
			return 0;
		return this.currentID;
	}
	/*
	 *	METHODS FOR ADDING & REMOVING : USERS & AUCTIONS
	 *
	 */
	public void addUser(User u){
		this.knownUsers.add(u);
		System.out.println("[AuctionHall][addUser]: User "+u.getLogin()+" added to the list");
	}
	
	public void addAuction(Product p){
		this.auctions.add(p);
		System.out.println("[AuctionHall][addAuction]: Public auction "+p.getName()+" added to the list");
	}
	
	
	public void removeUser(User u){
		knownUsers.remove(u);
		System.out.println("[AuctionHall][removeUser]: User "+u.getLogin()+" just removed.");
		return;
	}
	
	public void removeProduct(Product p) {
		auctions.remove(p);
		System.out.println("[AuctionHall][removeProduct]: Public auction "+p.getName()+" just removed.");
		return;
	}
	
	
	
	
	/*
	 * 
	 * 
	 */
	/*
	public void raisePrice(User u, Product p, Price contestingPrice) {
		if(u==null || p==null || contestingPrice==null){
			System.out.println("[AuctionHall][raisePrice]: Initialisation error.");
			return;
		}
		
		if(u.equals(p.getOwner())){
			System.out.println("[AuctionHall][raisePrice]Owner is not allowed to raise the bid.");
			return;
		}
		User contextUser = null;
		for(User us : this.knownUsers){
			if(u.equals(us)){
				contextUser = us;
				break;
			}
		}
		
		if(contextUser==null){
			System.out.println("[AuctionHall][raisePrice] : User specified unknown.");
			return;
		}
		
		Product contextProduct = null;
		for(Product i : this.auctions){
			if(p.equals(i)){
				contextProduct = i;
				break;
			}
		}
		if(contextProduct==null){
			System.out.println("[AuctionHall][raisePrice] : Product specified unknown.");
			return;
		}
		
		if(contextProduct.getCurrentPrice().isWorthMore(contestingPrice)){
			
			System.out.println("Raising "+p.getName()+" from : "+p.getCurrentPrice().getValue()+" ("
					+p.getCurrentPrice().getCurrency()
					+") to : "+contestingPrice.getValue()+" ("+contestingPrice.getCurrency()+") .");
			
			contextProduct.raisePrice(contextUser, contestingPrice);
		}

	}
	*/
	
}
