package com.oamanage.dao;

import org.apache.ibatis.annotations.Param;

import com.oamanage.po.Staff;


public interface LoginDao {
	
	//用户登录
	public Staff loginUser(@Param("loginName") String username);
	
}
