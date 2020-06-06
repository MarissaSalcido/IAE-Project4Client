package models;


public class OrderItem {
	private String productId;
	private String imageSrc;
	private String itemName;
	private double price;
	private int quantity;
		
	public String getProductId() {
		return this.productId;
	}
	
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public String getImageSrc() {
		return this.imageSrc;
	}
	
	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}
	
	public String getItemName() {
		return this.itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
		
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}

