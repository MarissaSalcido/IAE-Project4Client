package models;


public class OrderItem {
	private String productId;
	private String imageSrc;
	private String itemName;
	private String price;
	private String quantity;
			
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
	
	public String getPrice() {
		return this.price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getQuantity() {
		return this.quantity;
	}
		
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
}

