package environment;

import java.util.ArrayList;
import java.util.List;

import user.User;


public class AuctionHall {
	/*
	 * Classe qui sera instanci�e au tout d�but de l'ex�cution.
	 * Elle est le cadre d'action des �changes entre les Utilisateurs et aussi entre les Utilisateurs et les produits.
	 * 
	 * Les deux listes r�pertorient les utilisateurs et les produits.
	 * 
	 */
	private List<User> knownUsers;
	private List<Product> auctions;
	
	public AuctionHall(){
		this.knownUsers = new ArrayList<User>();
		this.auctions = new ArrayList<Product>();
	}

	public List<User> getKnownUsers() {
		return knownUsers;
	}

	public List<Product> getAuctions() {
		return auctions;
	}
	
	public void addAuction(Product p){
		if(p==null){
			System.out.println("[addAuction]: Initialisation error.");
			//securite
			return;
		}
		boolean lock = false;
		for(User i : knownUsers){
			if(p.getOwner().getFirstname().equals(i.getFirstname()) && p.getOwner().getLastname().equals(i.getLastname())){
				lock = true;
				break;
			}
		}
		if(lock == false) {
			System.out.println("[addAuction]: Unknown User, quitting.");
			return;
		}
		for(Product j : auctions){
			//Dans le cas ou un autre produit du meme nom est trouve on ne l'ajoute pas dans la liste
			if(p.getName().equals(j.getName())) {
				System.out.println("[addAuction]: Product name already in use.");
				return;
			}
		}
		//Dans le cas o� le produit n'existe pas d�ja, on l'ajoute
		auctions.add(p);
		System.out.println("[addAuction]: Product added to the list.");
	}
	
	public void addUser(User u){
		if(u==null){
			//s�curit�
			return;
		}
		
		for(User i : knownUsers){
			if(u == i){
				System.out.println("[addUser]: User already in the list.");
				return;
			}
		}
		//Utilisateur non trouv� dans la liste des connus donc on l'ajoute
		knownUsers.add(u);
		System.out.println("[addUser]: User added to the list");
	}


	
	/*
	 * POUR LA SUITE : 
	 * 	Impl�menter les fonctions raise Price et remove product
	 * 
	 * 	--> Il faut encore la classe price du coup..
	 * 
	 */
	
	public void raisePrice(User u, Product p, Price contestingPrice) {
		if(u==null || p==null || contestingPrice==null){
			System.out.println("[raisePrice]: Initialisation error.");
			return;
		}
		
		if(u == p.getOwner()){
			System.out.println("Owner is not allowed to raise the bid.");
			return;
		}
		User contextUser = null;
		for(User us : knownUsers){
			if(u.getFirstname().equals(us.getFirstname())&&u.getLastname().equals(us.getLastname())){
				contextUser = us;
				break;
			}
		}
		
		if(contextUser==null){
			System.out.println("User specified unknown.");
			return;
		}
		
		Product contextProduct = null;
		for(Product i : auctions){
			if(p.getName().equals(i.getName())){
				contextProduct = i;
				break;
			}
		}
		if(contextProduct==null){
			System.out.println("Product specified unknown.");
			return;
		}
		
		double actalValue = contextProduct.getCurrentPrice().getValue()/contextProduct.getCurrentPrice().getCurrency().getRate();
		double contestingValue = contestingPrice.getValue()/contestingPrice.getCurrency().getRate();
		if(actalValue < contestingValue){
			System.out.println("Raising "+p.getName()+" from : "+p.getCurrentPrice().getValue()+" ("
					+p.getCurrentPrice().getCurrency()
					+") to : "+contestingPrice+" ("+contestingPrice.getCurrency()+") .");
			contextProduct.raisePrice(contextUser, contestingPrice);
		}

	}

	
}
