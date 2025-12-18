package com.excelr.shopping.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class APIError {
	
	private String message;
    private String field;
    private Object rejectedValue;


}
