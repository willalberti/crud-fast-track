package com.vivo.kafkaproduce.port.out;

import java.util.List;

import com.vivo.kafkaproduce.adapter.exception.AbstractBusinessException;
import com.vivo.kafkaproduce.domain.OrderDto;

public interface PersistOrderPortOut {
	void insert(OrderDto orderDto) throws AbstractBusinessException;
	OrderDto findById(Long id) throws AbstractBusinessException;

	List<OrderDto> list() throws AbstractBusinessException;

	//List<OrderDto> search(OrderDto orderDto) throws AbstractBusinessException;
	List<OrderDto> search(String max_total, String min_total, String status, String q) throws AbstractBusinessException;

	OrderDto update(OrderDto orderDto) throws AbstractBusinessException;

	void delete(Long id) throws AbstractBusinessException;


}
