package com.vivo.kafkaproduce.adapter.out;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vivo.kafkaproduce.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	//List<Order> findByDescriptionAndName(String Description, String name);

/*	
	@Query(value = "SELECT o.* FROM ORDEM o WHERE "
			+ " TO_CHAR(ID) like %:id% "
			+ " and TO_CHAR(TOTAL,'99999.99') like %:total% "
			+ " and DESCRIPTION LIKE %:description% "
			+ " and NAME LIKE %:name% "
			+ " and STATUS LIKE %:status% ", nativeQuery = true)
	List<Order> findBySearch(
			@Param("id") String id,
			@Param("total") String total,
			@Param("description") String description,
			@Param("name") String name,
			@Param("status") String status
			);
	
	
	@Query(value = "SELECT o.* FROM ORDEM o WHERE "
			+ " TOTAL > :min  and TOTAL < :max "
			+ " and STATUS LIKE %:status% ", nativeQuery = true)
	List<Order> findBySearchv2(
			@Param("max") String max,
			@Param("min") String min,
			@Param("status") String status
			);
*/	
	
	@Query(value="SELECT p.* FROM ORDEM p "
			+ "WHERE "
			+ " ( "
			+ "		:name IS NULL OR LOWER (p.name) LIKE LOWER(CONCAT('%',:name,'%')) "
			+ "		OR \r\n"
			+ "		:description IS NULL OR LOWER (p.description) LIKE  LOWER(CONCAT('%',:description,'%')) "
			+ " ) "
			+ "     AND (:status IS NULL OR p.status = :status) "
			+ "     AND (:min_total IS NULL OR p.total >= :min_total) "
			+ "     AND (:max_total IS NULL OR p.total <= :max_total) ", nativeQuery = true)
	List<Order> findByCustomValues(
			@Param("name") String name, 
			@Param("description") String description, 
			@Param("status") String status, 
			@Param("min_total") Double min_total,
			@Param("max_total") Double max_total);
	
}


