package com.flipkart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flipkart.entity.OrderEntity;

@Repository
public interface OrderRepository  extends JpaRepository<OrderEntity, Long>{

}
