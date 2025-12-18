package com.excelr.shopping.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class ProductRequestDTO {

	@NotBlank(message = "Title is required")
	@Size(min = 5, max = 100, message = "Product Title must be between 5 and 100 characters")
	private String title;
	
	@NotNull
	@Min(value = 1, message = "Product Price must be greater than 0 Rs")
	@Max(value = 10000, message = "Product Price must be less than or equal to 10000 Rs")
	private double price;
	
	@NotBlank(message = "Title is required")
	@Size(min = 5, max = 300, message = "Product Description must be between 5 and 300 characters")
	private String description;
	
	@NotBlank(message = "Title is required")
	private String category;
	
	@NotBlank(message = "Title is required")
	private String image;
	
	RatingRequestDTO rating;
}

//table name User
//cid
//cname
//dob
//gender
//List<Address> address			houseno area city pincode
//username  
//password
