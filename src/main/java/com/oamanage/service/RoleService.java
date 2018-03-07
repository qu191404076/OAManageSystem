package com.oamanage.service;

import java.util.List;

import com.oamanage.po.Role;

public interface RoleService {
	
	//查询全部角色
	public List<Role> queryAllRoles();
	
	//添加角色
	public void addRole(String roleName);
}
