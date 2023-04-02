package com.pictu.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String resourceName;
	String fieldName;
	Integer fieldValue;
	String fieldValueText;
	public ResourceNotFoundException(String resourceName, String fieldName, Integer fieldValue) {
		super(String.format("%s not found with this %s : %s", resourceName , fieldName, fieldValue ));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	public ResourceNotFoundException(String resourceName, String fieldName, String fieldValueText) {
		super(String.format("%s not found with this %s : %s", resourceName , fieldName, fieldValueText ));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValueText = fieldValueText;
	}
	
	
	

}
