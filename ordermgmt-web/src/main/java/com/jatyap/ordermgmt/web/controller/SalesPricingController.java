package com.jatyap.ordermgmt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jatyap.ordermgmt.entity.Product;
import com.jatyap.ordermgmt.entity.SalesProductPricing;
import com.jatyap.ordermgmt.service.ProductPricingService;
import com.jatyap.ordermgmt.service.ProductService;

@Controller
public class SalesPricingController {

	@Autowired
	private ProductPricingService<SalesProductPricing> pricingService;
	
	@Autowired
	private ProductService productService;

	@RequestMapping("/products/pricing/sales")
	public String pricingMain(Model model) {
		List<SalesProductPricing> priceList = this.pricingService
				.getCurrentPrices();
		model.addAttribute("priceList", priceList);
		return "product/pricing/index";
	}

	@RequestMapping("/products/pricing/sales/newprice")
	public String newPrice(Model model) {
		SalesProductPricing newPrice = new SalesProductPricing();
		List<Product> productList = this.productService.getProductList();
		model.addAttribute("products", productList);
		model.addAttribute("newprice", newPrice);
		return "product/pricing/newprice";
	}

	@RequestMapping(value = "/products/pricing/sales/newprice", 
			method = RequestMethod.POST, params = { "save" })
	public String savePricing(SalesProductPricing pricing, Model model) {
		if(null == pricing){
			System.out.println("pricing is null");
		} else {
			this.pricingService.addPricing(pricing);
		}
		return "redirect:/products/pricing/sales";
	}
}
