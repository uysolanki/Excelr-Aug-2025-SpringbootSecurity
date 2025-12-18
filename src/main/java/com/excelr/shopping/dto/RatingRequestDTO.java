package com.excelr.shopping.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class RatingRequestDTO {
	
	@Min(value = 1, message = "Rating must be greater than 0")
	@Max(value = 5, message = "Rating must be less than or equal 5s")
	private double rate;
	
	@Min(value = 1, message = "Count must be greater than 0")
    private  int count;
}
