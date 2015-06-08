package com.jatyap.ordermgmt.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jatyap.ordermgmt.entity.Product;
import com.jatyap.ordermgmt.entity.SalesProductPricing;
import com.jatyap.ordermgmt.repository.ProductRepository;
import com.jatyap.ordermgmt.repository.SalesPricingRepository;

@Service
public class DefaultSalesPricingManager implements
		ProductPricingService<SalesProductPricing> {

	@Autowired
	private SalesPricingRepository pricingRepository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<SalesProductPricing> getPrices(Date referenceDate) {
		return this.pricingRepository.findActive(new Date());
	}

	@Override
	public List<SalesProductPricing> getCurrentPrices() {
		return this.getPrices(new Date());
	}

	@Override
	public SalesProductPricing getPricing(String productId, Date referenceDate) {
		return this.pricingRepository.findActive(
				this.productRepository.getOne(productId), referenceDate).get(0);
	}

	@Override
	@Transactional
	public void addPricing(SalesProductPricing pricing) {
		// search pricing matching validFrom and validTo
		// if begin date matches, get previous pricing
		// if end date matches, get next pricing
		Date validFrom = pricing.getValidFrom();
		Date validTo = pricing.getValidTo();

		processUpdateBeforeAndAfterPricings(pricing.getProduct(), validFrom, validTo);
		
		if(null != validFrom && null != validTo){
			processDeleteEnclosedPricings(pricing.getProduct(), validFrom, validTo);
		}

		// add pricing
		this.pricingRepository.save(pricing);
	}

	private void processDeleteEnclosedPricings(Product product, Date validFrom,
			Date validTo) {
		// delete all enclosed pricings
		List<SalesProductPricing> enclosedPricings = this.pricingRepository
				.findByProductAndValidFromGreaterThanAndValidToLessThan(product, validFrom,
						validTo);
		this.pricingRepository.delete(enclosedPricings);
	}
	
	private void processUpdateBeforeAndAfterPricings(Product product, Date validFrom,
			Date validTo){
		
		SalesProductPricing beforePricing = null;
		SalesProductPricing afterPricing = null;

		if (null != validFrom) {
			List<SalesProductPricing> beforeList = this.pricingRepository.findActive(product, validFrom);
			if(beforeList.size() > 0){
				beforePricing = beforeList.get(0);
			}

		} else {
			throw new IllegalArgumentException();
		}
		if (null != validTo) {
			if (validTo.before(validFrom)) {
				throw new IllegalArgumentException();
			}
			List<SalesProductPricing> afterList = this.pricingRepository.findActive(product, validTo);
			if(afterList.size() > 0){
				afterPricing = afterList.get(0);
			}
		}

		if (null != beforePricing) {
			if (null != afterPricing) {
				if (beforePricing.getPricingId() == afterPricing.getPricingId()) {
					// if previous and next pricing are the same
					// create one for next
					afterPricing = new SalesProductPricing();
					afterPricing.setProduct(beforePricing.getProduct());
					afterPricing.setPrice(beforePricing.getPrice());
					afterPricing.setValidFrom(validTo);
					afterPricing.setValidTo(beforePricing.getValidTo());
				} else { // else update next
					afterPricing.setValidFrom(validTo);
				}
				this.pricingRepository.save(afterPricing);
			}
			// update previous
			beforePricing.setValidTo(validFrom);
			this.pricingRepository.save(beforePricing);
		}
	}

}
