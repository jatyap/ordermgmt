package com.jatyap.ordermgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jatyap.ordermgmt.entity.SalesOrder;

public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> {

}
