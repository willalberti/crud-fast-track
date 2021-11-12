package com.vivo.kafkaconsumer.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vivo.kafkaconsumer.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
