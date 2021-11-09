package com.vivo.kafkaproduce.adapter.out;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vivo.kafkaproduce.domain.Order;
import com.vivo.kafkaproduce.domain.OrderDto;
import com.vivo.kafkaproduce.port.out.PersistOrderPortOut;
 
@Component
public class PersistOrderCommand implements PersistOrderPortOut {
  
	
	@Autowired
	private OrderRepository orderRepository;


	@Override
	public void insert(OrderDto orderDto) {
		orderRepository.save(new Order(orderDto));
	}


	@Override
	public OrderDto findById(Long id) {
		Optional<Order> orderDb = orderRepository.findById(id);
			
		if(!orderDb.isPresent()) 
			return null;
		else		
			return orderDb.get().toOrderDto();
		
	}


	@Override
	public List<OrderDto> list() {
		List<Order> orderDb = orderRepository.findAll();
		
		List<OrderDto> lstDto= new ArrayList<>();
		for (Order order : orderDb) {
			lstDto.add(order.toOrderDto());
		}
		
		return lstDto;
	}


	@Override
	public List<OrderDto> search(OrderDto orderDto) {
		List<Order> orderDb = orderRepository.findByDescriptionAndName(orderDto.getDescription(), orderDto.getName());
		
		//orderDb  = orderRepository.findAll(Example)
		List<OrderDto> lstDto= new ArrayList<>();
		for (Order order : orderDb) {
			lstDto.add(order.toOrderDto());
		}
		
		return lstDto;
	}


	@Override
	public OrderDto update(OrderDto orderDto) {
		orderRepository.save(new Order(orderDto));
		return orderDto;
	}


	@Override
	public void delete(Long id) {
		orderRepository.deleteById(id);
		
	}
	
	

 


}