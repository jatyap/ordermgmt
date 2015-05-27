package com.jatyap.ordermgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jatyap.ordermgmt.entity.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

}
