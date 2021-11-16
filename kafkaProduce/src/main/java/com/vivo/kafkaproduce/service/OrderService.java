package com.vivo.kafkaproduce.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vivo.kafkaproduce.adapter.exception.AbstractBusinessException;
import com.vivo.kafkaproduce.domain.OrderDto;
import com.vivo.kafkaproduce.domain.StatusEnum;
import com.vivo.kafkaproduce.port.in.OrderPortIn;
import com.vivo.kafkaproduce.port.out.PersistOrderPortOut;
import com.vivo.kafkaproduce.port.out.SendOrderPortOut;

@Service
public class OrderService implements OrderPortIn {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SendOrderPortOut sendOrderPortOut;
	
	@Autowired
	private PersistOrderPortOut persistOrderPortOut;	

	@Override
	public OrderDto create(OrderDto orderDto) throws AbstractBusinessException {
		try {	
			
			
			//troca status para não pocessado
			orderDto.setStatus(StatusEnum.NOT_PROCESSED);
			
			//grava ordem no database
			OrderDto o = persistOrderPortOut.insert(orderDto);
			
			//Envia msg ao kafka
			sendOrderPortOut.send(o);
			

			
			return o;
		} catch (AbstractBusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Class:{} metodo:{} Error:{}", this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage() +" :: "+ e.getStackTrace());
			throw new AbstractBusinessException(400, "Erro ao criar ordem");
		}			
		
	}

	@Override
	public List<OrderDto> list() throws AbstractBusinessException{

		try {
			
			List<OrderDto> lstOrderDto= persistOrderPortOut.list();
			
			if(lstOrderDto.size()<=0)
				throw new AbstractBusinessException(404, "Registro não encontrado");
			
			return lstOrderDto;
			
		} catch (AbstractBusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Class:{} metodo:{} Error:{}", this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage() +" :: "+ e.getStackTrace());
			throw new AbstractBusinessException(400, "Erro ao obter registro do banco");
		}
	}

	@Override
	public List<OrderDto> search(Double max_total, Double min_total, String status, String q) throws AbstractBusinessException{
		try {
			
			List<OrderDto> lstOrderDto= persistOrderPortOut.search(max_total, min_total, status, q);
			
			if(lstOrderDto.size()<=0)
				throw new AbstractBusinessException(404, "Registro não encontrado");
			
			return lstOrderDto;
			
		} catch (AbstractBusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Class:{} metodo:{} Error:{}", this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage() +" :: "+ e.getStackTrace());
			throw new AbstractBusinessException(400, "Erro ao obter registro do banco");
		}		
	}

	@Override
	public OrderDto update(OrderDto orderDto, Long id) throws AbstractBusinessException{		
		try {	
			OrderDto dtoDb = persistOrderPortOut.findById(id);
			if(dtoDb==null) 
				throw new AbstractBusinessException(404, "Não é possivel atualizar. Ordem não localizada");
			
			orderDto.setId(id);
			return persistOrderPortOut.update(orderDto);
			
		} catch (AbstractBusinessException e) {
			throw e;
		}		
	}

	@Override
	public OrderDto delete(Long id) throws AbstractBusinessException{
		try {
			OrderDto dto = persistOrderPortOut.findById(id);
			if(dto==null) 
				throw new AbstractBusinessException(404, "Não é possivel deletar. Ordem não localizada");	
			 
			persistOrderPortOut.delete(id);
			return dto;
		} catch (AbstractBusinessException e) {
			throw e;
		}			
	}

	@Override
	public OrderDto findById(Long id) throws AbstractBusinessException{
		
		
		try {
			
			OrderDto orderDto= persistOrderPortOut.findById(id);
			
			if(orderDto==null)
				throw new AbstractBusinessException(404, "Registro não encontrado");
			
			return orderDto;
			
		} catch (AbstractBusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Class:{} metodo:{} Error:{}", this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage() +" :: "+ e.getStackTrace());
			throw new AbstractBusinessException(400, "Erro ao obter registro do banco");
		}			
	}

}
