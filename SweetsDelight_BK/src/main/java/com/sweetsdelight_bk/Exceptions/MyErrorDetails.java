package com.sweetsdelight_bk.Exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyErrorDetails {
	private String message;
	private String description;
	private LocalDateTime timestamp;
	
}
