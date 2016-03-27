package model;

public class Order {
	private int orderID;
	private int productID;
	private int customerID;
	private int quantity;
	private int price;
	
	public Order(int order,int product,int customer,int quantity,int price){
		this.orderID=order;
		this.productID=product;
		this.customerID=customer;
		this.quantity=quantity;
		this.price=price;
	}
	
	public Order(int product,int customer,int quantity){
		this.productID=product;
		this.customerID=customer;
		this.quantity=quantity;
	}
	
	public Order(int product,int quantity){
		this.productID=product;
		this.quantity=quantity;
	}
	
	
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
