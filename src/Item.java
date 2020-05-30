class Item {
	String id;
	String imageSrc;
	String item;
	double price;
	int quantity;
	
	// Constructors
	Item(String id, String imageSrc, String item, double price, int quantity) {
		this.id = id;
		this.imageSrc = imageSrc;
		this.item = item;
		this.price = price;
		this.quantity = quantity;		
	}
	
	
	Item(String id, String imageSrc, String item, double price) {
		this.id = id;
		this.imageSrc = imageSrc;
		this.item = item;
		this.price = price;
		this.quantity = 0;		
	}
	
	// Returns the quantity as an int
	int getQuantity() {
		return this.quantity;
	}
	
	// Sets the quantity as an int
	void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
