package com.vivo.kafkaconsumer.adapter.in;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.vivo.kafkaconsumer.domain.OrderDto;
import com.vivo.kafkaconsumer.port.in.ProcessOrderPortIn;
 
@Component
public class ConsumerOrder {
 
	@Autowired
	private ProcessOrderPortIn processOrder;
	
    @KafkaListener(topics = "${topic.produce.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(String TopicValue) throws InterruptedException {
    	
    	try {
	    	//converte json em domain.OrderDto
	    	OrderDto orderDto = new Gson().fromJson(TopicValue, OrderDto.class);
	    	
	    	//espera 5 seg
	    	Thread.sleep(5000); 
	    	   	
	    	//inicia processamento da ordem
	    	processOrder.process(orderDto);  
	    	//ack.acknowledge();
	    	System.out.println("Ordem processada! - " + orderDto.toString());
	    	
		} catch (Exception e) {
			System.out.println("não foi possível processar a mensagem. Motivo: "+ e.getMessage());
		}    	
    }
}
