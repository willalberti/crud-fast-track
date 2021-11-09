package com.vivo.kafkaconsumer.adapter.out;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vivo.kafkaconsumer.domain.Order;
import com.vivo.kafkaconsumer.domain.OrderDto;
import com.vivo.kafkaconsumer.port.out.ProcessOrderPortOut;

@Service
public class PersistOrderCommand implements ProcessOrderPortOut{

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public void updateOrder(OrderDto orderDto) {
  	
		orderRepository.save(new Order(orderDto));
			
	}

	@Override
	public OrderDto find(OrderDto orderDto) {
		
		Optional<Order> orderDb = orderRepository.findById(orderDto.getId());
		
		if(!orderDb.isPresent()) 
			return null;
		else		
			return orderDb.get().toOrderDto();
	}

}
