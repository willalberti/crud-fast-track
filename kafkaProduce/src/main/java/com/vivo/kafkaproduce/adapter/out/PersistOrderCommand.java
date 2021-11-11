package com.vivo.kafkaproduce.adapter.out;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vivo.kafkaproduce.adapter.exception.AbstractBusinessException;
import com.vivo.kafkaproduce.domain.Order;
import com.vivo.kafkaproduce.domain.OrderDto;
import com.vivo.kafkaproduce.port.out.PersistOrderPortOut;
 
@Component
public class PersistOrderCommand implements PersistOrderPortOut {
  
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/*
	  @Repository
public interface PermissionPersistence extends JpaRepository<PermissaoLinhaPup, Long>{
	
	@Cacheable("retrieveByLine")
	@Query(value="select p.* from NFO_OW.PERMISSAOLINHAPUP p where p.CDAREAREGISTRO = ?1 and p.NRLINHA = ?2", nativeQuery = true)
	List<PermissaoLinhaPup> findByNumberLine(String codeArea, String numberLine);
		
	@Query(value="SELECT * FROM NFO_OW.PERMISSAOLINHAPUP WHERE CDAREAREGISTRO = ?1 AND NRLINHA = ?2 AND SGPERMISSAOPUP = 'BLKTEAT'	AND IDUSUARIOALTERACAO = ?3 AND INATIVO=1", nativeQuery = true)
	List<PermissaoLinhaPup> getBlockProcon(String code, String networkNumber, String idUserProconOwner);	

}
	 * */
	
	
	
	@Autowired
	private OrderRepository orderRepository;


	@Override
	public void insert(OrderDto orderDto) throws AbstractBusinessException{
		try {
			orderRepository.save(new Order(orderDto));
		} catch (Exception e) {
			logger.error("Class:{} metodo:{} Error:{}", this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage() +" :: "+ e.getStackTrace());
			throw new AbstractBusinessException(500, "Erro ao inserir registro do banco");
		}		
	}


	@Override
	public OrderDto findById(Long id) throws AbstractBusinessException {
		
		try {
			Optional<Order> orderDb = orderRepository.findById(id);
				
			if(orderDb.isPresent()) 
				return orderDb.get().toOrderDto();
			else		
				return null;
			
		} catch (Exception e) {
			logger.error("Class:{} metodo:{} Error:{}", this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage() +" :: "+ e.getStackTrace());
			throw new AbstractBusinessException(500, "Erro ao obter registro do banco");
		}			
		
	}


	@Override
	public List<OrderDto> list() throws AbstractBusinessException {
		try {
			List<Order> orderDb = orderRepository.findAll();
			
		
			List<OrderDto> lstDto= new ArrayList<>();
			for (Order order : orderDb) {
				lstDto.add(order.toOrderDto());
			}
			
			return lstDto;
			
		} catch (Exception e) {
			logger.error("Class:{} metodo:{} Error:{}", this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage() +" :: "+ e.getStackTrace());
			throw new AbstractBusinessException(500, "Erro ao obter registro do banco");
		}			
	}


	@Override
	public List<OrderDto> search(OrderDto orderDto) throws AbstractBusinessException {
		try {
			List<Order> orderDb = orderRepository.findBySearch(orderDto.getDescription(), orderDto.getStatus().toString());
			//List<Order> orderDb = orderRepository.findAll();
			
			
			//orderDb  = orderRepository.findAll(Example)
			List<OrderDto> lstDto= new ArrayList<>();
			for (Order order : orderDb) {
				lstDto.add(order.toOrderDto());
			}
			
			return lstDto;
		
		} catch (Exception e) {
			logger.error("Class:{} metodo:{} Error:{}", this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage() +" :: "+ e.getStackTrace());
			throw new AbstractBusinessException(500, "Erro ao obter registro do banco");
		}			
	}


	@Override
	public OrderDto update(OrderDto orderDto) throws AbstractBusinessException{
		try {
			orderRepository.save(new Order(orderDto));
			return orderDto;
		} catch (Exception e) {
			logger.error("Class:{} metodo:{} Error:{}", this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage() +" :: "+ e.getStackTrace());
			throw new AbstractBusinessException(500, "Erro ao obter registro do banco");
		}			

	}


	@Override
	public void delete(Long id) throws AbstractBusinessException{
		
		try {
			orderRepository.deleteById(id);
		} catch (Exception e) {
			logger.error("Class:{} metodo:{} Error:{}", this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage() +" :: "+ e.getStackTrace());
			throw new AbstractBusinessException(500, "Erro ao deletar registro do banco");
		}			
		
		
	}
	
	

 


}