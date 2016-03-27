package bll;

import data.access.ProductAccess;
import model.Product;

public class ProductBLL {
	private ProductAccess database = new ProductAccess();
	private int selectedProd;
	
	public int getSelectedProd(){
		return this.selectedProd;
	}
	
	public boolean setSelectedProd(int selectedProd) {
		if (selectedProd > 0) {
			this.selectedProd = selectedProd;
			return true;
		}
		else return false;
	}

	public boolean update(Product prod) {
		if (selectedProd != 0 && ((prod.getName()!=null && !prod.getName().isEmpty()) || prod.getQuantity() != 0 || prod.getPrice()!=0)) {
			database.update(prod, selectedProd);
			return true;
		} else
			return false;

	}

	public boolean insert(Product prod) {

		if (prod.getName() == null || prod.getQuantity() == 0 || prod.getName().isEmpty() || prod.getPrice()==0) {
			return false;
		} else {
			database.insert(prod);
			return true;
		}
	}

	public boolean delete() {
		Product prod = database.read(selectedProd);
		if (prod.getId() != 0)
			database.delete(selectedProd);
		else
			System.out.println("Invalid ID");
		return true;
	}

	public Product read(int id) {

		Product prod = database.read(id);
		return prod;
	}

	public Product[] readAll() {
		Product prod[] = database.readAll();
		return prod;
	}

}
