package com.jatyap.ordermgmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jatyap.ordermgmt.entity.Product;
import com.jatyap.ordermgmt.repository.ProductRepository;

public class DefaultProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getProductList() {
		return this.productRepository.findAll();
	}

	@Override
	public Product getProduct(String productId) {
		return this.productRepository.getOne(productId);
	}

	@Override
	public void updateProduct(Product product) {
		this.productRepository.save(product);
	}

	@Override
	public void deleteProduct(String productId) {
		this.productRepository.delete(productId);
	}

	@Override
	public void addProduct(Product product) {
		if (this.productRepository.exists(product.getProductId())) {
			throw new IllegalArgumentException();
		}
		this.productRepository.save(product);
	}
}
