package com.excelr.shopping.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.excelr.shopping.model.Product;
import com.excelr.shopping.service.ProductService;

@Controller
@RequestMapping("/amazon")
public class ProductControllerFE {

	@Autowired
	ProductService productService;
	
	@RequestMapping("/greet")
	public String greet()
	{
		return "virat";
	}
	
	@RequestMapping("/add-product-form")
	public String addProductForm(Model model)
	{
		Product product=new Product();
		model.addAttribute("product",product);
		return "add-product-form-updated";
	}
	
	@PostMapping("/add-product")
	public String addProductForm(@ModelAttribute Product product, @RequestParam("imageFile") MultipartFile file) throws IOException
	{
		if (!file.isEmpty()) {

	        String uploadDir = "uploads/";
	        Files.createDirectories(Paths.get(uploadDir));

	        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
	        Path filePath = Paths.get(uploadDir, fileName);

	        Files.write(filePath, file.getBytes());

	        // Save only filename or relative path
	        product.setImage("http://localhost:8084/uploads/" + fileName);
	    }

		
		productService.addProduct(product);
		return "redirect:/amazon/all-products";
	}
	
	@RequestMapping("/all-products")
	public String allProducts(Model model)
	{
		List<Product> products=productService.getAllProducts();
		model.addAttribute("products",products);
		return "all-products";
	}
	
	@RequestMapping("/delete-product/{pid}")
	public String deleteProduct(@PathVariable int pid)
	{
		productService.deleteProduct(pid);
		return "redirect:/amazon/all-products";
	}
	
	@RequestMapping("/update-product-form/{pid}")
	public String updateProductForm(@PathVariable int pid, Model model)
	{
		Product product=productService.getSingleProduct(pid);
		model.addAttribute("product",product);
		return "update-product-form";
		
	}
	
	@PostMapping("/update-product/{prodId}")
	public String updateProduct(@PathVariable int prodId,@ModelAttribute Product newValues)
	{
		productService.updateProduct(prodId,newValues);
		return "redirect:/amazon/all-products";
	}
	
	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() 
			+ ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", 
			    "you do not have permission to access this page!");
		}

		model.setViewName("403");
		return model;

	}

}

