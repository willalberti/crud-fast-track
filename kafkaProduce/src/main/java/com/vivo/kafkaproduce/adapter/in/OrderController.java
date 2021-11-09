package com.vivo.kafkaproduce.adapter.in;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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

import com.vivo.kafkaproduce.domain.OrderDto;
import com.vivo.kafkaproduce.domain.Result;
import com.vivo.kafkaproduce.port.in.OrderPortIn;
 
@RestController
@RequestMapping("/api")
public class OrderController {
 
  
    @Autowired
    private OrderPortIn orderPortIn;
 
    @GetMapping("/order")
    public ResponseEntity<List<OrderDto>> list() {
		
    	try {
    		List<OrderDto> lstDto = orderPortIn.list();
        	
        	return ResponseEntity.status(HttpStatus.OK).body(lstDto);
        	
		} catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
		}    	
    	
    	
    }    
 
    @PostMapping("/order")
    public ResponseEntity<Result> create(@RequestBody @Valid OrderDto orderDto) {
    	
    	try {
    		orderPortIn.create(orderDto);
        	
        	return ResponseEntity.status(HttpStatus.CREATED).body(new Result(0, "ordem criada") );
        	
		} catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Result(1, "Erro na criação. Causa: " +e.getMessage()));
		}

    }
    
    

    
    @GetMapping("/order/search")
    public ResponseEntity<List<OrderDto>> search(@Valid OrderDto orderDto) {
    	try {
    		List<OrderDto> lstDto = orderPortIn.search(orderDto);
        	
        	return ResponseEntity.status(HttpStatus.OK).body(lstDto);
        	
		} catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
		}  
    }  
    
    @GetMapping("/order/{id}")
    public ResponseEntity<OrderDto> findById(@RequestParam Long id) {
    	try {
    		OrderDto dto = orderPortIn.findById(id);
        	
        	return ResponseEntity.status(HttpStatus.OK).body(dto);
        	
		} catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}  
    }    
    
    @PutMapping("/order")
    public ResponseEntity<OrderDto> update(@RequestBody @Valid OrderDto orderDto) {
    	try {
    		OrderDto dto = orderPortIn.update(orderDto);
        	
        	return ResponseEntity.status(HttpStatus.OK).body(dto);
        	
		} catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} 
    }      
    
    @DeleteMapping("/order{id}")
    public ResponseEntity<Result> delete(@RequestParam Long id) {
    	try {
    		orderPortIn.delete(id);
        	
        	return ResponseEntity.status(HttpStatus.CREATED).body(new Result(0, "ordem com id:"+ id.toString() +" excluida") );
        	
		} catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Result(1, "Erro na exclusão. Causa: " +e.getMessage()));
		}
    }     
    

}

