package com.vivo.kafkaproduce.adapter.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.vivo.kafkaproduce.domain.OrderDto;
import com.vivo.kafkaproduce.port.out.SendOrderPortOut;
 
@Component
public class PublishEventOrderBroker implements SendOrderPortOut {
 
	@Value("${topic.produce.name}")
    private String pedidoTopic;
 
	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
	

	@Override
	public void send(OrderDto orderDto) throws Exception {
    	try {
			kafkaTemplate.send(pedidoTopic, String.valueOf(orderDto.getId()), new Gson().toJson(orderDto)).get();
		} catch (Exception e) {
			throw new Exception("Erro ao publicar evento de criação da ordem. Causa: "+ e.getMessage());
		}
		
	}
	
	

 


}
