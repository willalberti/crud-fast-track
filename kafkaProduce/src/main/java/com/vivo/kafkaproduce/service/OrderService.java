package com.vivo.kafkaproduce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vivo.kafkaproduce.domain.OrderDto;
import com.vivo.kafkaproduce.domain.StatusEnum;
import com.vivo.kafkaproduce.port.in.OrderPortIn;
import com.vivo.kafkaproduce.port.out.PersistOrderPortOut;
import com.vivo.kafkaproduce.port.out.SendOrderPortOut;

@Service
public class OrderService implements OrderPortIn {
	
	@Autowired
	private SendOrderPortOut sendOrderPortOut;
	
	@Autowired
	private PersistOrderPortOut persistOrderPortOut;	

	@Override
	public void create(OrderDto orderDto) throws Exception {
		
		//recupera objeto do banco
		OrderDto dto = persistOrderPortOut.findById(orderDto.getId());
		
		//verifica se já existe objeto no banco com o mesmo Id
		if(dto!=null) 
			throw new RuntimeException("Ordem já existe localizada");
		
		//troca status para não pocessado
		orderDto.setStatus(StatusEnum.NOT_PROCESSED);
		
		//Envia msg ao kafka
		sendOrderPortOut.send(orderDto);
		
		//grava ordem no database
		persistOrderPortOut.insert(orderDto);
		
		
	}

	@Override
	public List<OrderDto> list() {
		return persistOrderPortOut.list();
	}

	@Override
	public List<OrderDto> search(OrderDto orderDto) {
		return persistOrderPortOut.search(orderDto);
	}

	@Override
	public OrderDto update(OrderDto orderDto) {
		
		OrderDto dto = persistOrderPortOut.findById(orderDto.getId());
		if(dto==null) 
			throw new RuntimeException("Ordem não localizada");

		return persistOrderPortOut.update(orderDto);
	}

	@Override
	public void delete(Long id) {
		OrderDto dto = persistOrderPortOut.findById(id);
		if(dto==null) 
			throw new RuntimeException("Ordem não localizada");		
		persistOrderPortOut.delete(id);
		
	}

	@Override
	public OrderDto findById(Long id) {
		return persistOrderPortOut.findById(id);
	}

}
