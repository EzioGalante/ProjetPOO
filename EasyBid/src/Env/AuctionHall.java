package Env;

import java.util.ArrayList;
import java.util.List;

import Users.User;

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
	
	public void addAuction(Product p){
		if(p==null){
			//s�curit�
			return;
		}
		for(Product i : auctions){
			//Dans le cas o� un autre produit du m�me nom est trouv� on ne l'ajoute pas dans la liste
			if(p.getName().equals(i.getName())) {
				System.out.println("Product name already in use.");
				return;
			}
		}
		//Dans le cas o� le produit n'existe pas d�ja, on l'ajoute
		auctions.add(p);
		System.out.println("Product added to the list.");
	}
	
	public void addUser(User u){
		if(u==null){
			//s�curit�
			return;
		}
		
		for(User i : knownUsers){
			if(u == i){
				System.out.println("User already in the list.");
				return;
			}
		}
		//Utilisateur non trouv� dans la liste des connus donc on l'ajoute
		knownUsers.add(u);
		System.out.println("User added to the list");
	}
	
	/*
	 * POUR LA SUITE : 
	 * 	Impl�menter les fonctions raise Price et remove product
	 * 
	 */
}
