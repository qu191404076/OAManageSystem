package com.oamanage.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oamanage.dao.RoleDao;
import com.oamanage.po.Role;
import com.oamanage.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;
	
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	//查询全部角色
	@Override
	public List<Role> queryAllRoles() {
		List<Role> roleList = roleDao.queryAllRoles();
		// TODO Auto-generated method stub
		return roleList;
	}

	//添加角色
	@Override
	public void addRole(String roleName) {
		// TODO Auto-generated method stub
		roleDao.addRole(roleName);
	}

}
