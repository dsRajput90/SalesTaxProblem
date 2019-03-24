package com.boku.util;

/**
 * List of response codes which is sent back as a response post execution
 * */
public enum ResponseCodes {

	SUCCESS("Success"),
	EMPTY("Empty"),
	EMPTY_FILE("Empty Files"),
	ERROR("Error"),
	FILE_INPUT_ERROR("File Input Error");
	
	private String value;
	
	ResponseCodes(String value){
		this.value=value;
	}
	
	public String getValue() {
		return this.value;
	}
}
