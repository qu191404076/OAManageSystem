package com.oamanage.exception;

/**
 * 没有查询到用户的异常
 * @author Administrator
 *
 */
public class NoUserException extends RuntimeException {
	
	public NoUserException(String message){
		super(message);
	}
	
	public NoUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
