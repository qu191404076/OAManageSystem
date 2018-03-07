package com.oamanage.dao;

import java.util.List;

import com.oamanage.po.Role;

/**
 * 角色接口
 * @author Administrator
 *
 */
public interface RoleDao {
	
	//查询全部角色
	public List<Role> queryAllRoles();
	
	//添加角色
	public void addRole(String roleName);
}
