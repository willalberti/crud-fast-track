package com.vivo.kafkaconsumer.port.in;

import com.vivo.kafkaconsumer.domain.OrderDto;

public interface ProcessOrderPortIn {
	void process(OrderDto orderDto);
}
