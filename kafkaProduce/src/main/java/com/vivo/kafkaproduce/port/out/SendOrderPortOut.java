package com.vivo.kafkaproduce.port.out;

import com.vivo.kafkaproduce.domain.OrderDto;

public interface SendOrderPortOut {
	void send(OrderDto orderDto) throws Exception;

}
