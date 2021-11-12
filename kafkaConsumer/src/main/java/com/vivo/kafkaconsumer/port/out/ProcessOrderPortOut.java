package com.vivo.kafkaconsumer.port.out;

import com.vivo.kafkaconsumer.domain.OrderDto;

public interface ProcessOrderPortOut {
	
	void updateOrder(OrderDto orderDto);
	OrderDto find(OrderDto orderDto);

}
