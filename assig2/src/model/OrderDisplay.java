package model;

public class OrderDisplay {
	private int orderID;
	private String customer;
	private String product;
	private int quantity;
	private int price;
	private String address;
	
	public OrderDisplay(){}
	
//	public OrderDisplay(String customer,String product,int quantity){
//		this.customer=customer;
//		this.product=product;
//		this.quantity=quantity;
//	}
	
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
