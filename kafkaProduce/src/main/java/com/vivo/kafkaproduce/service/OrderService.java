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
			//recupera objeto do banco
			OrderDto dto = persistOrderPortOut.findById(orderDto.getId());
			
			//verifica se já existe objeto no banco com o mesmo Id
			if(dto!=null) 
				throw new AbstractBusinessException(403, "Não é possivel criar pois ja existe um objeto com o mesmo id");
			
			//troca status para não pocessado
			orderDto.setStatus(StatusEnum.NOT_PROCESSED);
			
			//Envia msg ao kafka
			sendOrderPortOut.send(orderDto);
			
			//grava ordem no database
			persistOrderPortOut.insert(orderDto);
			
			return orderDto;
		} catch (AbstractBusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Class:{} metodo:{} Error:{}", this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage() +" :: "+ e.getStackTrace());
			throw new AbstractBusinessException(500, "Erro ao obter registro do banco");
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
			throw new AbstractBusinessException(500, "Erro ao obter registro do banco");
		}
	}

	@Override
	public List<OrderDto> search(OrderDto orderDto) throws AbstractBusinessException{
		try {
			
			List<OrderDto> lstOrderDto= persistOrderPortOut.search(orderDto);
			
			if(lstOrderDto.size()<=0)
				throw new AbstractBusinessException(404, "Registro não encontrado");
			
			return lstOrderDto;
			
		} catch (AbstractBusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Class:{} metodo:{} Error:{}", this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage() +" :: "+ e.getStackTrace());
			throw new AbstractBusinessException(500, "Erro ao obter registro do banco");
		}		
	}

	@Override
	public OrderDto update(OrderDto orderDto, Long id) throws AbstractBusinessException{		
		try {	
			OrderDto dto = persistOrderPortOut.findById(id);
			if(dto==null) 
				throw new AbstractBusinessException(404, "Não é possivel atualizar. Ordem não localizada");
			
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
			throw new AbstractBusinessException(500, "Erro ao obter registro do banco");
		}			
	}

}
