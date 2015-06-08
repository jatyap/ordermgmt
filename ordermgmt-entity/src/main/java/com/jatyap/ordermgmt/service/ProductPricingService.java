package com.jatyap.ordermgmt.service;

import java.util.Date;
import java.util.List;

import com.jatyap.ordermgmt.entity.ProductPricing;

public interface ProductPricingService<T extends ProductPricing> {
	
	List<T> getPrices(Date referenceDate);
	List<T> getCurrentPrices();
	T getPricing(String productId, Date referenceDate);
	
	void addPricing(T pricing);
}
