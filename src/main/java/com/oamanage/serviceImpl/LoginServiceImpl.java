package com.oamanage.serviceImpl;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oamanage.dao.LoginDao;
import com.oamanage.exception.NoUserException;
import com.oamanage.po.Staff;
import com.oamanage.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	
	private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Autowired
	private LoginDao loginDao;
	
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	@Override
	public Staff login(String loginName) {
		try {
			Staff staff = loginDao.loginUser(loginName);
			return staff;
		} catch (NoUserException e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
			return null;
		}catch(TooManyResultsException e1){
			logger.error(e1.getMessage(),e1);
			return null;
		}
		
		
		
		
		// TODO Auto-generated method stub
		
	}

}
