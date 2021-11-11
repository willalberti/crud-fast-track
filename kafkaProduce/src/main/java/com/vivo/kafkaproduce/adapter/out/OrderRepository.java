package com.vivo.kafkaproduce.adapter.out;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vivo.kafkaproduce.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByDescriptionAndName(String Description, String name);

	
	@Query(value = "SELECT o.* FROM ORDEM o WHERE DESCRIPTION LIKE %:description% or STATUS LIKE %:status%", nativeQuery = true)
	List<Order> findBySearch(
			@Param("description") String title,
			@Param("status") String status
			);
}
