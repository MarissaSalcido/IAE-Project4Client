package models;

import java.util.ArrayList;
import java.util.List;


public class Order {
    private int orderId = 0;
    
    // For customer table
    private String firstName;
    private String lastName;
    private String phoneNum;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zipcode;
    private String shippingMethod;
    
    // For billing table
    private String cardType;
    private String cardNumber;
    private String expMonth;
    private String expYear;
    private String cvv;
    private String subtotal;
    private String tax;
    private String shippingCost;
    private String total;
    

    // For order_items table
    List<OrderItem> orderItems = new ArrayList<OrderItem>();
    
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getFirstName() {
    	return firstName;
    }
    
    public void setFirstName(String firstName) {
    	this.firstName = firstName;
    }

    public String getLastName() {
    	return lastName;
    }
    
    public void setLastName(String lastName) {
    	this.lastName = lastName;
    }
    
    public String getPhoneNum() {
    	return phoneNum;
    }
    
    public void setPhoneNum(String phoneNum) {
    	this.phoneNum = phoneNum;
    }
    
    public String getAddress1() {
    	return address1;
    }
    
    public void setAddress1(String address1) {
    	this.address1 = address1;
    }
    
    public String getAddress2() {
    	return address2;
    }
    
    public void setAddress2(String address2) {
    	this.address2 = address2;
    }
    
    public String getCity() {
    	return city;
    }
    
    public void setCity(String city) {
    	this.city = city;
    }
    
    public String getState() {
    	return state;
    }
    
    public void setState(String state) {
    	this.state = state;
    }

    public String getZipcode() {
    	return zipcode;
    }
    
    public void setZipcode(String zipcode) {
    	this.zipcode = zipcode;
    }

    public String getShippingMethod() {
    	return shippingMethod;
    }
    
    public void setShippingMethod(String shippingMethod) {
    	this.shippingMethod = shippingMethod;
    }
    
    public String getCardType() {
    	return cardType;
    }
    
    public void setCardType(String cardType) {
    	this.cardType = cardType;
    }

    public String getCardNumber() {
    	return cardNumber;
    }
    
    public void setCardNumber(String cardNumber) {
    	this.cardNumber = cardNumber;
    }
    
    public String getExpMonth() {
    	return expMonth;
    }
    
    public void setExpMonth(String expMonth) {
    	this.expMonth = expMonth;
    }

    public String getExpYear() {
    	return expYear;
    }
    
    public void setExpYear(String expYear) {
    	this.expYear = expYear;
    }
    
    public String getCvv() {
    	return cvv;
    }
    
    public void setCvv(String cvv) {
    	this.cvv = cvv;
    }

    public String getSubtotal() {
    	return subtotal;
    }
    
    public void setSubtotal(String subtotal) {
    	this.subtotal = subtotal;
    }

    public String getTax() {
    	return tax;
    }
    
    public void setTax(String tax) {
    	this.tax = tax;
    }
        
    public String getShippingCost() {
    	return shippingCost;
    }
    
    public void setShippingCost(String shippingCost) {
    	this.shippingCost = shippingCost;
    }
    
    public String getTotal() {
    	return total;
    }
    
    public void setTotal(String total) {
    	this.total = total;
    }
    
    public List<OrderItem> getOrderItems() {
    	List<OrderItem> itemsList = new ArrayList<OrderItem>();
    	OrderItem itemToAdd;
    	
    	for (int i = 0; i < this.orderItems.size(); ++i) {
    		itemToAdd = new OrderItem();
    		itemToAdd.setProductId(this.orderItems.get(i).getProductId());
    		itemToAdd.setImageSrc(this.orderItems.get(i).getImageSrc());
    		itemToAdd.setItemName(this.orderItems.get(i).getItemName());
    		itemToAdd.setPrice(this.orderItems.get(i).getPrice());
    		itemToAdd.setQuantity(this.orderItems.get(i).getQuantity());
    		itemsList.add(itemToAdd);
    	}
    	
    	return itemsList;
    }
    
    public void setOrderItems(List<OrderItem> orderItems) {
    	OrderItem itemToAdd;
    	
    	for (int i = 0; i < orderItems.size(); ++i) {
    		itemToAdd = new OrderItem();
    		itemToAdd.setProductId(orderItems.get(i).getProductId());
    		itemToAdd.setImageSrc(orderItems.get(i).getImageSrc());
    		itemToAdd.setItemName(orderItems.get(i).getItemName());
    		itemToAdd.setPrice(orderItems.get(i).getPrice());
    		itemToAdd.setQuantity(orderItems.get(i).getQuantity());
    		this.orderItems.add(itemToAdd);
    	}
    }
}
