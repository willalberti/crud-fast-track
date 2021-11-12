package com.vivo.kafkaproduce.port.in;

import java.util.List;

import com.vivo.kafkaproduce.adapter.exception.AbstractBusinessException;
import com.vivo.kafkaproduce.domain.OrderDto;

public interface OrderPortIn {
	OrderDto create(OrderDto orderDto) throws AbstractBusinessException;

	List<OrderDto> list() throws AbstractBusinessException;

	//List<OrderDto> search(OrderDto orderDto) throws AbstractBusinessException;

	OrderDto update(OrderDto orderDto, Long id) throws AbstractBusinessException;

	OrderDto delete(Long id) throws AbstractBusinessException;

	OrderDto findById(Long id) throws AbstractBusinessException;

	List<OrderDto> search(String max_total, String min_total, String status, String q) throws AbstractBusinessException;

}
