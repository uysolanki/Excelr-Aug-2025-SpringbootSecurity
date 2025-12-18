package com.excelr.shopping.model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Builder
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String title;
	private double price;
	private String description;
	private String category;
	private String image;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="rating", referencedColumnName = "rid")
	Rating rating;
	
	private LocalDateTime createdAt;
	
    private LocalDateTime modifiedAt;
	
	@PrePersist
	protected void atCreation()
	{
		LocalDateTime now=LocalDateTime.now();
		this.createdAt=now;
		this.modifiedAt=now;
	}
	
	@PreUpdate
	protected void atUpdation()
	{
		this.modifiedAt=LocalDateTime.now();
	}


	
}
