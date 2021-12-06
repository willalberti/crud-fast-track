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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vivo.kafkaproduce.adapter.exception.AbstractBusinessException;
import com.vivo.kafkaproduce.domain.OrderDto;
import com.vivo.kafkaproduce.port.in.OrderPortIn;

@RequestMapping("/orders")
@RestController
public class OrderController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

  
    @Autowired
    private OrderPortIn orderPortIn;
 
    @GetMapping
    public ResponseEntity<List<OrderDto>> list() throws AbstractBusinessException {
		
    	try {
    		
    		List<OrderDto> lstDto = orderPortIn.list();
        	
        	return ResponseEntity.status(HttpStatus.OK).body(lstDto);
        	
		} catch (AbstractBusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Class:{} metodo:{} Error:{}", this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage() +" :: "+ e.getStackTrace());
			throw new AbstractBusinessException(400, "Erro ao listar ordens");
		}	    	
    	
    	
    	
    }    
 
    @PostMapping
    public ResponseEntity<OrderDto> create(@RequestBody @Valid OrderDto orderDto) throws AbstractBusinessException {
    	
    	try {
    		OrderDto dto = orderPortIn.create(orderDto);
        	
    		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        	
		} catch (AbstractBusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Class:{} metodo:{} Error:{}", this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage() +" :: "+ e.getStackTrace());
			throw new AbstractBusinessException(400, "Erro ao criar ordem");
		}	 

    }
    
    

    
    @GetMapping("/search")
    public ResponseEntity<List<OrderDto>> search(
    		@RequestParam(name = "max_total", required = true) Double max_total,
    		@RequestParam(name = "min_total", required = true) Double min_total,
    		@RequestParam(name = "status", required = true) String status,
    		@RequestParam(name = "q", required = false) String q) throws AbstractBusinessException {
    	try {
        	
//    		if (max_total==null || max_total==null)
//    			throw new AbstractBusinessException(400, "informe os valores max_total e min_total");

    		List<OrderDto> lstDto = orderPortIn.search(max_total, min_total, status, q);
        	return ResponseEntity.status(HttpStatus.OK).body(lstDto);
        	
		} catch (AbstractBusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Class:{} metodo:{} Error:{}", this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage() +" :: "+ e.getStackTrace());
			throw new AbstractBusinessException(400, "Erro ao procurar ordem");
		}	  
    }  
    
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> findById(@PathVariable Long id) throws AbstractBusinessException {
    	try {
    		OrderDto dto = orderPortIn.findById(id);
        	
        	return ResponseEntity.status(HttpStatus.OK).body(dto);
        	
		} catch (AbstractBusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Class:{} metodo:{} Error:{}", this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage() +" :: "+ e.getStackTrace());
			throw new AbstractBusinessException(400, "Erro ao obter uma ordem");
		}	 
    }    
    
    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> update(@RequestBody @Valid OrderDto orderDto, @PathVariable Long id) throws AbstractBusinessException {
    	try {
    		OrderDto dto = orderPortIn.update(orderDto, id);
        	
        	return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        	
		} catch (AbstractBusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Class:{} metodo:{} Error:{}", this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage() +" :: "+ e.getStackTrace());
			throw new AbstractBusinessException(400, "Erro ao atualizar ordem");
		}	 
    }      
    
    @DeleteMapping("/{id}")
    public ResponseEntity<OrderDto> delete(@PathVariable Long id) throws AbstractBusinessException {
    	try {
    		OrderDto dto = orderPortIn.delete(id);
        	
        	return ResponseEntity.status(HttpStatus.NO_CONTENT).body(dto);
        	
		} catch (AbstractBusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Class:{} metodo:{} Error:{}", this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage() +" :: "+ e.getStackTrace());
			throw new AbstractBusinessException(400, "Erro ao deletar ordem");
		}	 
    }     
    

}

