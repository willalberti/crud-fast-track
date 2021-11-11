package com.vivo.kafkaproduce.adapter.in;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vivo.kafkaproduce.adapter.exception.AbstractBusinessException;
import com.vivo.kafkaproduce.domain.OrderDto;
import com.vivo.kafkaproduce.port.in.OrderPortIn;

@RequestMapping("/order")
@RestController
public class OrderController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

  
    @Autowired
    private OrderPortIn orderPortIn;
 
    @GetMapping("/")
    public ResponseEntity<List<OrderDto>> list() throws AbstractBusinessException {
		
    	try {
    		
    		List<OrderDto> lstDto = orderPortIn.list();
        	
        	return ResponseEntity.status(HttpStatus.OK).body(lstDto);
        	
		} catch (AbstractBusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Class:{} metodo:{} Error:{}", this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage() +" :: "+ e.getStackTrace());
			throw new AbstractBusinessException(500, "Erro ao listar ordens");
		}	    	
    	
    	
    	
    }    
 
    @PostMapping("/")
    public ResponseEntity<OrderDto> create(@RequestBody @Valid OrderDto orderDto) throws AbstractBusinessException {
    	
    	try {
    		OrderDto dto = orderPortIn.create(orderDto);
        	
    		return ResponseEntity.status(HttpStatus.OK).body(dto);
        	
		} catch (AbstractBusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Class:{} metodo:{} Error:{}", this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage() +" :: "+ e.getStackTrace());
			throw new AbstractBusinessException(500, "Erro ao criar ordem");
		}	 

    }
    
    

    
    @GetMapping("/search")
    public ResponseEntity<List<OrderDto>> search(@Valid OrderDto orderDto) throws AbstractBusinessException {
    	try {
    		List<OrderDto> lstDto = orderPortIn.search(orderDto);
        	
        	return ResponseEntity.status(HttpStatus.OK).body(lstDto);
        	
		} catch (AbstractBusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Class:{} metodo:{} Error:{}", this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage() +" :: "+ e.getStackTrace());
			throw new AbstractBusinessException(500, "Erro ao procurar ordem");
		}	  
    }  
    
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> findById(@RequestParam Long id) throws AbstractBusinessException {
    	try {
    		OrderDto dto = orderPortIn.findById(id);
        	
        	return ResponseEntity.status(HttpStatus.OK).body(dto);
        	
		} catch (AbstractBusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Class:{} metodo:{} Error:{}", this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage() +" :: "+ e.getStackTrace());
			throw new AbstractBusinessException(500, "Erro ao obter uma ordem");
		}	 
    }    
    
    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> update(@RequestBody @Valid OrderDto orderDto, @RequestParam Long id) throws AbstractBusinessException {
    	try {
    		OrderDto dto = orderPortIn.update(orderDto, id);
        	
        	return ResponseEntity.status(HttpStatus.OK).body(dto);
        	
		} catch (AbstractBusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Class:{} metodo:{} Error:{}", this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage() +" :: "+ e.getStackTrace());
			throw new AbstractBusinessException(500, "Erro ao atualizar ordem");
		}	 
    }      
    
    @DeleteMapping("/{id}")
    public ResponseEntity<OrderDto> delete(@RequestParam Long id) throws AbstractBusinessException {
    	try {
    		OrderDto dto = orderPortIn.delete(id);
        	
        	return ResponseEntity.status(HttpStatus.OK).body(dto);
        	
		} catch (AbstractBusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Class:{} metodo:{} Error:{}", this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage() +" :: "+ e.getStackTrace());
			throw new AbstractBusinessException(500, "Erro ao deletar ordem");
		}	 
    }     
    

}

