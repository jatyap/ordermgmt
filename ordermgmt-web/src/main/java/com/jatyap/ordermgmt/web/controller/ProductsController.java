package com.jatyap.ordermgmt.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jatyap.ordermgmt.entity.Product;
import com.jatyap.ordermgmt.service.ProductService;

@Controller
public class ProductsController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping("/products")
	public String productsMain(Model model) {
		Iterable<Product> productList = this.productService.getProductList();
		model.addAttribute("product", new Product());
		model.addAttribute("products", productList);
		return "product/products";
	}
	
	@RequestMapping(value="/products/add", method=RequestMethod.GET)
	public String addProduct(Model model) {
		model.addAttribute("product", new Product());
		return "product/add";
	}

	@RequestMapping(value="/products/add", method=RequestMethod.POST, params = { "save" })
	public String addProduct(Product product, Model model) {
		this.productService.addProduct(product);
		return "redirect:/products";
	}
	

	@RequestMapping(value = "/products/modify")
	public String modifyProduct(@RequestParam(value="productId", required=true) String productId, 
			Model model){
		Product productToModify = this.productService.getProduct(productId);
		model.addAttribute("product", productToModify);
		return "product/modify";
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/products/modify", params = { "save" })
	public String modifyProduct(Product product, Model model){
		this.productService.updateProduct(product);
		return "redirect:/products";
	}
	
	@RequestMapping("/products/delete")
	public ModelAndView deleteProduct(@RequestParam(value="productId", required=true) String productId){
		this.productService.deleteProduct(productId);
		return new ModelAndView("redirect:/products");
	}
	
	@RequestMapping(value="/products/add", method=RequestMethod.POST, params = { "cancel" })
	public String cancelAdd() {
		return "redirect:/products";
	}
	@RequestMapping(value="/products/modify", method=RequestMethod.POST, params = { "cancel" })
	public String cancelModify() {
		return "redirect:/products";
	}
	

}
