package com.excelr.shopping.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excelr.shopping.dto.ProductRequestDTO;
import com.excelr.shopping.dto.ProductResponseDTO;
import com.excelr.shopping.entity.Product;
import com.excelr.shopping.entity.Rating;
import com.excelr.shopping.exception.ResourseNotFoundException;
import com.excelr.shopping.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public Product addProduct(Product product) {
		return productRepository.save(product);
		
	}

	public List<Product> addProducts(List<Product> products) {
		return productRepository.saveAll(products);
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product getSingleProduct(int pid) 
	{
		Optional<Product> optionalProduct=productRepository.findById(pid);
		Product product=null;
		if(optionalProduct.isPresent())
		{
			product=optionalProduct.get();
			return product;
		}
		
		throw new RuntimeException("Product Not Found");
	}

	public List<Product> getProductByCategory(String category) {
		return productRepository.findByCategory(category);
	}

	public List<Product> getProductByPricegreaterthan(double basePrice) {
		return productRepository.findByPriceGreaterThanEqual(basePrice);
	}

	public void deleteProduct(int pid) {
		if(!productRepository.existsById(pid))
		{
			throw new ResourseNotFoundException("Product Numbder "+pid+ " does not exist in the database");
		}
		productRepository.deleteById(pid);
	}

	public Product updateProduct(int pid, Product newValues) {
		if(!productRepository.existsById(pid))
		{
			throw new ResourseNotFoundException("Product Numbder "+pid+ " does not exist in the database");
		}
		Product prodFromDB=getSingleProduct(pid);
		prodFromDB.setCategory(newValues.getCategory());
		prodFromDB.setDescription(newValues.getDescription());
		prodFromDB.setImage(newValues.getImage());
		prodFromDB.setPrice(newValues.getPrice());
		prodFromDB.setTitle(newValues.getTitle());
		if(newValues.getRating()!=null)
		{
		prodFromDB.getRating().setRate(newValues.getRating().getRate());
		prodFromDB.getRating().setCount(newValues.getRating().getCount());
		}
		return productRepository.save(prodFromDB);
	}

	public ProductResponseDTO addProductByDTO(ProductRequestDTO productReqDTO) {
		Product product = modelMapper.map(productReqDTO, Product.class);
		Product savedProduct=productRepository.save(product);
		return modelMapper.map(savedProduct, ProductResponseDTO.class);
	}
	
}
