List of automatically run JUnit tests :

--> environment :
	
	
	--> AuctionHall :
	
		- testAuctionHall : testing constructor
		
		- testGetKnownUsers : 
			+ adds 3 users to the "knownUsers" list
			+ uses the getter to check they are all there in the right order
			
		- testGetAuctions : 
			+ adds 1 users to the "knownUsers" list
			+ adds 3 products to the "auctions" list
			+ checks that the 3 products are all in, in the right order
		
		- testAddAuction :
			+ makes valid user
			+ tries addAuction without being in the "knownUsers" list 
			+ checks that the product was not added
			+ adds user to the "knownUsers" list
			+ adds product to the "auctions" list
			+ checks that the product was added
			
		
		- testAddUser :
			+ makes valid user
			+ adds user to the "knownUsers" list
			+ checks that the user was added
		
		
		- testRaisePrice : 
			+ makes 2 valid users and a valid product (which one of the users own)
			+ tries raisePrice from owner, checks it fails
			+ tries raisePrice with inferior & superior price, checks it only accepts superior prices
			+ also checks that other currencies are accepted.
	
	--> Currency :
	
	--> Price :
	
	--> Product :
	
	
	
--> users :


	--> User :

		- testUser : testing constructor
			+ tries to create invalid users (having one of their parameters to null)
			+ checks invalid users are set to null.
			
		- testGetFirstname :
			+ uses firstname getter to get global user's first name
			+ checks it is equal to the value we first entered
			
		- testGetLastname :
			+ uses lasttname getter to get global user's last name
			+ checks it is equal to the value we first entered
			
