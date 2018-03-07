package com.oamanage.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oamanage.dao.LoginDao;
import com.oamanage.po.Dept;
import com.oamanage.po.Role;
import com.oamanage.po.Staff;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private LoginDao loginDao;
	
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		Staff user = loginDao.loginUser(username);
		String password = user.getLoginPassword();
		
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(Role role : user.getRoles()){
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		
		org.springframework.security.core.userdetails.User securityUser = new org.springframework.security.core.userdetails.User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		
		
		return securityUser;
	}

}
