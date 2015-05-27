package com.jatyap.ordermgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jatyap.ordermgmt.entity.SalesProductPricing;

public interface SalesProductPricingRepository extends
		JpaRepository<SalesProductPricing, Long> {

}
