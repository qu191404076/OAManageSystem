package com.oamanage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oamanage.po.Dept;

public interface DeptService {
	
	//添加部门
	public void addDept(String deptName);
	
	//分页查询全部部门
	public List<Dept> queryAllDept(int page,int row);
	
	//查询全部部门
	public List<Dept> allDept();
	
	//查询部门总数
	public int queryDeptCount();
}
