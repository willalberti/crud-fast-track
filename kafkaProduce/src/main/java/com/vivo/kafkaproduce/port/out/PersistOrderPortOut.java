package com.vivo.kafkaproduce.port.out;

import java.util.List;

import com.vivo.kafkaproduce.domain.OrderDto;

public interface PersistOrderPortOut {
	void insert(OrderDto orderDto);
	OrderDto findById(Long id);
	
	
	
	

	List<OrderDto> list();

	List<OrderDto> search(OrderDto orderDto);

	OrderDto update(OrderDto orderDto);

	void delete(Long id);


}
