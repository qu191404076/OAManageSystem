package com.oamanage.exception;

/**
 * 添加员工信息异常
 * @author Administrator
 *
 */
public class AddStaffInfoException extends RuntimeException{

	public AddStaffInfoException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AddStaffInfoException(String msg){
		super(msg);
		
	}
}
