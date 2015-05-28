package com.jatyap.ordermgmt.service;

import java.util.List;

import com.jatyap.ordermgmt.entity.Product;

public interface ProductService {
	
	void addProduct(Product product);
	List<Product> getProductList();
	Product getProduct(String productId);
	void updateProduct(Product product);
	void deleteProduct(String productId);
}
