package it.easygames.model.bean;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	private List<Game> products;
	
	public Cart() {
		products = new ArrayList<Game>();
	}
	
	public void addProduct(Game product) {
		products.add(product);
	}
	
	public void deleteProduct(Game product) {
		for(Game prod : products) {
			if(prod.getId() == product.getId()) {
				products.remove(prod);
				break;
			}
		}
 	}
	
	public List<Game> getProducts() {
		return  products;
	}
}