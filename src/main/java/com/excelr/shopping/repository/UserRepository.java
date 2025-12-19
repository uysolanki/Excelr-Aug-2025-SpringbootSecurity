package com.excelr.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excelr.shopping.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

			public User findByUsername(String str);
}
