package com.vivo.kafkaconsumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vivo.kafkaconsumer.domain.OrderDto;
import com.vivo.kafkaconsumer.domain.StatusEnum;
import com.vivo.kafkaconsumer.port.in.ProcessOrderPortIn;
import com.vivo.kafkaconsumer.port.out.ProcessOrderPortOut;

@Service
public class ProcessOrderService implements ProcessOrderPortIn {

	@Autowired
	private ProcessOrderPortOut processOrderPortOut;
	
	@Override
	public void process(OrderDto orderDto) {
		
	

		OrderDto dto = processOrderPortOut.find(orderDto);
		
		if(dto==null) 
			throw new RuntimeException("Ordem não localizada");
		
		if(!dto.getStatus().equals(StatusEnum.NOT_PROCESSED))
			throw new RuntimeException("Ordem já processada");
		
		dto.setStatus(StatusEnum.PROCESSED);
		
		processOrderPortOut.updateOrder(dto);
			
			
		
	
		
	}

}
