package Env;

import java.util.ArrayList;
import java.util.List;

import Users.User;

public class AuctionHall {
	/*
	 * Classe qui sera instanciée au tout début de l'exécution.
	 * Elle est le cadre d'action des échanges entre les Utilisateurs et aussi entre les Utilisateurs et les produits.
	 * 
	 * Les deux listes répertorient les utilisateurs et les produits.
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
	
	public void addAuction(User u, Product p){
		if(p==null || u==null){
			System.out.println("[addAuction]: Initialisation error.");
			//sécurité
			return;
		}
		boolean lock = false;
		for(User i : knownUsers){
			if(u==i){
				lock = true;
				break;
			}
		}
		if(lock == false) {
			System.out.println("[addAuction]: Unknown User, quitting.");
			return;
		}
		for(Product j : auctions){
			//Dans le cas où un autre produit du même nom est trouvé on ne l'ajoute pas dans la liste
			if(p.getName().equals(j.getName())) {
				System.out.println("[addAuction]: Product name already in use.");
				return;
			}
		}
		//Dans le cas où le produit n'existe pas déja, on l'ajoute
		auctions.add(p);
		System.out.println("[addAuction]: Product added to the list.");
	}
	
	public void addUser(User u){
		if(u==null){
			//sécurité
			return;
		}
		
		for(User i : knownUsers){
			if(u == i){
				System.out.println("[addUser]: User already in the list.");
				return;
			}
		}
		//Utilisateur non trouvé dans la liste des connus donc on l'ajoute
		knownUsers.add(u);
		System.out.println("[addUser]: User added to the list");
	}


	
	/*
	 * POUR LA SUITE : 
	 * 	Implémenter les fonctions raise Price et remove product
	 * 
	 * 	--> Il faut encore la classe price du coup..
	 * 
	 */
	
	public void raisePrice(User u, Product p, Price contestingPrice) {
		if(u==null || p==null || contestingPrice==null){
			System.out.println("[raisePrice]: Initialisation error.");
			return;
		}
		
		User userInQuestion = null;
		for(User us : knownUsers){
			if(u.getFirstname().equals(us.getFirstname())&&u.getLastname().equals(us.getLastname())){
				userInQuestion = us;
				break;
			}
		}
		
		if(userInQuestion==null){
			System.out.println("User specified unknown.");
			return;
		}
		
		Product productInQuestion = null;
		for(Product i : auctions){
			if(p.getName().equals(i.getName())){
				productInQuestion = i;
				break;
			}
		}
		if(productInQuestion==null){
			System.out.println("Product specified unknown.");
			return;
		}
		
		productInQuestion.raisePrice(userInQuestion, contestingPrice);
	}
	
}
