package com.vivo.kafkaproduce.port.in;

import java.util.List;

import com.vivo.kafkaproduce.domain.OrderDto;

public interface OrderPortIn {
	void create(OrderDto orderDto) throws Exception;

	List<OrderDto> list();

	List<OrderDto> search(OrderDto orderDto);

	OrderDto update(OrderDto orderDto);

	void delete(Long id);

	OrderDto findById(Long id);

}
