package bll;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import model.OrderDisplay;

public class Bill {
	public void bill(OrderDisplay ord) {
		PrintWriter writer;
		try {
			writer = new PrintWriter("BILL"+ord.getOrderID()+".txt", "UTF-8");
			writer.println("BILL");
			writer.println(ord.getProduct()+"			"+ord.getQuantity());
			writer.println("Buyer: "+ord.getCustomer()+" 		"+ord.getAddress());
			writer.println();
			writer.println("				TOTAL: "+ord.getPrice());
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
