package com.orderservice.repository;

import com.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRespository extends JpaRepository<Order, String> {
}
