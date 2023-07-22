package com.sweetsdelight_bk.Exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	

	@ExceptionHandler(AdminException.class)
	public ResponseEntity<ErrorDetails> myExpHandler(AdminException ie, WebRequest req)  {
		log.error("Admin Exception occuers");
			ErrorDetails err=new ErrorDetails(LocalDateTime.now(), ie.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> myExpHandler(MethodArgumentNotValidException ie, WebRequest req)  {	
		log.error("Method argument not validation Exception occuers");
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(), ie.getMessage(), req.getDescription(false));
	 return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	
	}
	
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorDetails> myExpHandler(ConstraintViolationException ie, WebRequest req)  {
		log.error("Constraint Voilation Exception occuers");
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(), ie.getMessage(), req.getDescription(false));

	 return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	
	}
	

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> myanyExphandler(Exception ie, WebRequest req)  {
		log.error("Exception occuers");
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(), ie.getMessage(), req.getDescription(false));

	 return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetails> mynotFoundHandler(NoHandlerFoundException nfe,WebRequest req)  {				
		log.error("Not found Exception occuers");
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(), nfe.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
						
	}
	
	
	@ExceptionHandler(CartsException.class)
	public ResponseEntity<ErrorDetails> cartExceptionHandler(CartsException nfe,WebRequest req)  {				
		log.error("Carts Exception occuers");
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(), nfe.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
						
	}
	
	@ExceptionHandler(CategoryException.class)
	public ResponseEntity<ErrorDetails> CategoryExceptionHandler(CategoryException nfe,WebRequest req)  {				
		log.error("Category Exception occuers");
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(), nfe.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
						
	}
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<ErrorDetails> CustomerExceptionHandler(CustomerException nfe,WebRequest req)  {				
		log.error("Customer Exception occuers");
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(), nfe.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
						
	}
	
	@ExceptionHandler(OrderBillException.class)
	public ResponseEntity<ErrorDetails> OrderBillExceptionHandler(OrderBillException nfe,WebRequest req)  {				
		log.error("Order Bill Exception occuers");
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(), nfe.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
						
	}
	
	@ExceptionHandler(OrderException.class)
	public ResponseEntity<ErrorDetails> OrderExceptionHandler(OrderException nfe,WebRequest req)  {				
		log.error("Order Exception occuers");
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(), nfe.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
						
	}
	
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<ErrorDetails> ProductExceptionHandler(ProductException nfe,WebRequest req)  {				
		log.error("Product Exception occuers");
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(), nfe.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
						
	}
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorDetails> userExceptionHandler(UserException nfe,WebRequest req)  {				
		log.error("User Exception occuers");
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(), nfe.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
						
	}
	
	
}
