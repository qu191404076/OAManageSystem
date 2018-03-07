package com.oamanage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oamanage.po.Staff;

public interface StaffService {
	
	//查询全部人员
	public List<Staff> queryAllStaff(int page,int row);
	
	//添加人员
	public void addStaff(Staff staff);
	
	//添加人员部门关系
	public void addStaffDept(Staff staff);
	
	//添加人员角色关系
	public void addStaffRole(Staff staff);
	
	//修改密码
	public void modifyPwd(String userName,String loginPassword);
	
	//查询人员总数
	public int queryStaffCount();
	
	//删除人员
	public void deleteStaff(String userId);
}
