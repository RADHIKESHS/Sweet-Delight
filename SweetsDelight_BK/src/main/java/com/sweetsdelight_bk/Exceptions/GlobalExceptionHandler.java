package com.sweetsdelight_bk.Exceptions;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> GeneralExceptionHandler(Exception se){
		log.error("Internal Error Occurs");
		return new ResponseEntity<>(se.getMessage() , HttpStatus.BAD_REQUEST) ;
	}
	
	//to handle Not found exception 
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> mynotFoundHandler(NoHandlerFoundException nfe,WebRequest req)  {
		log.error("NoHandlerFounds Error Occurs");
		
		MyErrorDetails err=new MyErrorDetails(nfe.getMessage(), req.getDescription(false),LocalDateTime.now());
		
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
						
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> handleStudentException(MethodArgumentNotValidException exp) {
		log.error("Validation Error Occurs");
		List<ObjectError> allErrors= exp.getBindingResult().getAllErrors();
		List<String> errorMessage= MethodArgumentNotValidException.errorsToStringList(allErrors);
		
		String error= String.join(", ", errorMessage);
		
		MyErrorDetails err = new MyErrorDetails("Validation Failed",error,LocalDateTime.now());

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

	}


	@ExceptionHandler(SweetDelightBkException.class)
	public ResponseEntity<MyErrorDetails> SweetDelightBkErrorException(SweetDelightBkException exp, WebRequest req) {
		log.error("SweetDelightBk runtime Error Occurs");
		MyErrorDetails err = new MyErrorDetails(exp.getMessage(), req.getDescription(false),LocalDateTime.now());

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

	}
}
