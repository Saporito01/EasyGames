package it.easygames.model.bean;

import java.util.HashMap;
import java.util.Map;

public class Cart {

	private String userId;
    private Map<String, Integer> products;

    public Cart(String userId) {
        this.userId = userId;
        this.products = new HashMap<>();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void addProduct(String productId) {
        products.put(productId, products.getOrDefault(productId, 0) + 1);
    }

    public void removeProduct(String productId) {
        if (products.containsKey(productId)) {
            int quantity = products.get(productId);
            if (quantity > 1) {
                products.put(productId, quantity - 1);
            } else {
                products.remove(productId);
            }
        }
    }
    
    public void removeCompletelyProduct(String productId) {
    	products.remove(productId);
    }
    
    public Map<String, Integer> getProducts() {
        return products;
    }

    public void clear() {
        products.clear();
    }
}