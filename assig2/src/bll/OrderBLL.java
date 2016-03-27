package bll;

import data.access.OrderAccess;
import data.access.ProductAccess;
import model.Order;
import model.OrderDisplay;
import model.Product;

public class OrderBLL {
	private OrderAccess database = new OrderAccess();
	private ClientBLL clientControl=new ClientBLL();
	private ProductBLL productControl=new ProductBLL();
	private ProductAccess productDatabase=new ProductAccess();
	private Bill print=new Bill();
	private int selectedOrder;
	private int ordersNr;

	public OrderDisplay[] read() {
		Order[] orders;
		OrderDisplay[] showOrder = new OrderDisplay[50];
		orders=database.readAll();
		int i=0;
		while(orders[i]!=null){
			showOrder[i]=new OrderDisplay();
			showOrder[i].setOrderID(orders[i].getOrderID());
			showOrder[i].setQuantity(orders[i].getQuantity());
			showOrder[i].setCustomer(clientControl.read(orders[i].getCustomerID()).getName());
			showOrder[i].setProduct(productControl.read(orders[i].getProductID()).getName());
			showOrder[i].setAddress(clientControl.read(orders[i].getCustomerID()).getAddress());
			showOrder[i].setPrice(productControl.read(orders[i].getProductID()).getPrice()*orders[i].getQuantity());
			i++;
		}
		ordersNr=i;
		return showOrder;
	}
	
	public boolean update(Order order) {
		if (selectedOrder != 0 && (order.getQuantity()!=0 || order.getProductID()!=0)) {
			database.update(order, selectedOrder);
			return true;
		} else
			return false;

	}
	
	public boolean delete() {
		if (selectedOrder != 0)
			
			database.delete(selectedOrder);
		else
			System.out.println("Invalid ID");
		return true;
	}
	
	public boolean insert(Order order) {
		
		if (order.getCustomerID() == 0 || order.getQuantity() == 0 || order.getProductID()==0 || order.getQuantity()>productControl.read(order.getProductID()).getQuantity()) {
			return false;
		} else {
			order.setPrice(productControl.read(order.getProductID()).getPrice()*order.getQuantity());
			database.insert(order);
			OrderDisplay[] ord=read();
			print.bill(ord[ordersNr-1]);
			Product aux=productControl.read(order.getProductID());
			aux.setQuantity(aux.getQuantity()-order.getQuantity());
			productDatabase.updateQuantity(aux, order.getProductID());
			return true;
		}
	}

	public int getSelectedOrder() {
		return selectedOrder;
	}

	public void setSelectedOrder(int selectedOrder) {
		this.selectedOrder = selectedOrder;
	}
}
