package com.oamanage.dao;

import java.util.List;

import com.oamanage.po.Dept;

public interface DeptDao {
	
	//添加部门
	public void addDept(String deptName);
	
	//查询全部部门
	public List<Dept> queryAllDept();
	
	//查询部门总数
	public int queryDeptCount();
}
