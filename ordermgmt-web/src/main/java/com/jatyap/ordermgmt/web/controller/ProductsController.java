package com.jatyap.ordermgmt.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jatyap.ordermgmt.entity.Product;
import com.jatyap.ordermgmt.repository.ProductRepository;

@Controller
public class ProductsController {

	@Autowired
	private ProductRepository productRepo;

	@RequestMapping("/products")
	public String productsMain(Model model) {
		Iterable<Product> productList = this.productRepo.findAll();
		model.addAttribute("product", new Product());
		model.addAttribute("products", productList);
		return "product/products";
	}

	@RequestMapping("/saveProduct")
	public ModelAndView saveProduct(Product product, Model model) {
		this.productRepo.save(product);
		return new ModelAndView("redirect:/products");
	}
	
	@RequestMapping("/deleteProduct")
	public ModelAndView deleteProduct(@RequestParam(value="productId", required=true) String productId){
		this.productRepo.delete(productId);
		return new ModelAndView("redirect:/products");
	}

}
