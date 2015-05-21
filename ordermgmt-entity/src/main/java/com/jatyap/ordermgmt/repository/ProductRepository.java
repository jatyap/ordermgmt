package com.jatyap.ordermgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jatyap.ordermgmt.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

}
