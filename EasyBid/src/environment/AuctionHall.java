package environment;

import java.util.ArrayList;
import java.util.List;

import user.User;


public class AuctionHall {
	/*
	 * 
	 * Cadre d'action des echanges entre les Utilisateurs et aussi entre les Utilisateurs et les produits.
	 * 
	 * Les deux listes repertorient les utilisateurs et les produits.
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

	public List<User> getKnownUsers() {
		return knownUsers;
	}
	public List<Product> getAuctions() {
		return auctions;
	}
	
	/*
	 *
	 * 
	 */
	public void addUser(User u){
		if(u==null){
			//securite
			return;
		}
		
		for(User i : this.knownUsers){
			if(u.equals(i)){
				System.out.println("[AuctionHall][addUser]: User already in the list.");
				return;
			}
		}
		//Utilisateur non trouve dans la liste des connus donc on l'ajoute
		this.knownUsers.add(u);
		System.out.println("[AuctionHall][addUser]: User added to the list");
	}
	
	public void addAuction(Product p){
		if(p==null){
			System.out.println("[AuctionHall][addAuction]: Initialisation error.");
			//securite
			return;
		}
		boolean lock = false;
		for(User i : this.knownUsers){
			if(p.getOwner().equals(i)){
				lock = true;
				break;
			}
		}
		if(lock == false) {
			System.out.println("[AuctionHall][addAuction]: Unknown User, quitting.");
			return;
		}
		for(Product j : this.auctions){
			//Dans le cas ou un autre produit du meme nom est trouve on ne l'ajoute pas dans la liste
			if(p.equals(j)) {
				System.out.println("[AuctionHall][addAuction]: Product name already in use.");
				return;
			}
		}
		//Dans le cas ou le produit n'existe pas deja, on l'ajoute
		this.auctions.add(p);
	}
	
    // Remove Product Method
	public void removeProduct(Product p) {
		  		
		auctions.remove(p);
		System.out.println("[AuctionHall][removeProduct]: Product just removed.");
		return;
	}
		



    // removeUser Method
	
	public void removeUser(User u){
		
		knownUsers.remove(u);
		System.out.println("[AuctionHall][removeUser]: User just removed.");
		return;
	}
	
	/*
	 * 
	 * 
	 */
	
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

	public int giveUserID(){
		this.currentID++;
		
		if(this.currentID < 0)
			return 0;
		return this.currentID;
	}
}
