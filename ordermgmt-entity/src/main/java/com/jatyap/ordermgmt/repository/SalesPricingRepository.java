package com.jatyap.ordermgmt.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jatyap.ordermgmt.entity.Product;
import com.jatyap.ordermgmt.entity.SalesProductPricing;

public interface SalesPricingRepository extends
		JpaRepository<SalesProductPricing, Long> {

	/**
	 * Selects all matching SalesProductPricing based on referenceDate
	 * @param referenceDate
	 * @return a List of SalesProductPricing
	 */
	@Query("select p from SalesProductPricing p where p.validFrom <= ?1 and "
			+ "(p.validTo >= ?1 or p.validTo is null)")
	List<SalesProductPricing> findActive(Date referenceDate);
	
	
	/**
	 * Selects matching SalesProductPricing based on product and referenceDate
	 * @param product
	 * @param referenceDate
	 * @return a List of relevant SalesProductPricing
	 */
	@Query("select p from SalesProductPricing p where "
			+ "p.product = ?1 and "
			+ "p.validFrom <= ?2 and "
			+ "(p.validTo >= ?2 or p.validTo is null)")
	List<SalesProductPricing> findActive(Product product, Date referenceDate);
	
	/**
	 * Selects all SalesProductPricing enclosed by the referenced valid from
	 * and valid to dates
	 * @param product
	 * @param refValidFrom The referenced ValidFrom date
	 * @param refValidTo The referenced ValidTo date
	 * @return
	 */
	List<SalesProductPricing> findByProductAndValidFromGreaterThanAndValidToLessThan(
			Product product, Date refValidFrom, Date refValidTo);
	
}
