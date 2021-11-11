package com.vivo.kafkaproduce.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;



import lombok.Data;

@Data
@Entity
@Table(name = "Ordem")
public class Order {

	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id 
	private Long id;
	private String description;
	private String name;
	private Double total;	
	
	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	  
	public Order() {
		
	}
	
	public Order(OrderDto orderDto) {
		this.setId(orderDto.getId());
		this.setDescription(orderDto.getDescription());
		this.setName(orderDto.getName());
		this.setTotal(orderDto.getTotal());
		this.setStatus(orderDto.getStatus());		
	}
	
	public OrderDto toOrderDto() {
		OrderDto dto = new OrderDto();
		dto.setId(this.getId());
		dto.setDescription(this.getDescription());
		dto.setName(this.getName());
		dto.setTotal(this.getTotal());
		dto.setStatus(this.getStatus());
		return dto;
	}	
	
}



