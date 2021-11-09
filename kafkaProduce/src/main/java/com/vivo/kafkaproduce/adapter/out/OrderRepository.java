package com.vivo.kafkaproduce.adapter.out;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vivo.kafkaproduce.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByDescriptionAndName(String Description, String name);
}
