package com.jatyap.ordermgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jatyap.ordermgmt.entity.PurchaseProductPricing;

public interface PurchaseProductPricingRepository extends
		JpaRepository<PurchaseProductPricing, Long> {

}
