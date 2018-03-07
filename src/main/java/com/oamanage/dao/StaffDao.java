package com.oamanage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.oamanage.po.Staff;

public interface StaffDao {
	
	//查询全部人员
	public List<Staff> queryAllStaff();
	
	//添加人员
	public void addStaff(Staff staff);
	
	//添加人员部门关系
	public void addStaffDept(Staff staff);
	
	//添加人员角色关系
	public void addStaffRole(Staff staff);
	
	//修改密码
	public void modifyPwd(@Param("userName") String userName,@Param("loginPassword") String loginPassword);
	
	//查询人员总数
	public int queryStaffCount();
	
	//删除人员
	public void deleteStaff(String userId);
	
	//删除人员部门关系
	public void deleteStaffDept(String userId);
	
	//删除人员角色关系
	public void deleteStaffRole(String userId);
}
