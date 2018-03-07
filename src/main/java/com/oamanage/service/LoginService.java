package com.oamanage.service;

import com.oamanage.po.Staff;

public interface LoginService {
	
	//用户登录
	public Staff login(String loginName);
}
