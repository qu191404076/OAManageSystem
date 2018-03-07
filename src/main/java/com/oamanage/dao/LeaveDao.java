package com.oamanage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.oamanage.po.Leave;

/**
 * 请假单
 * @author Administrator
 *
 */
public interface LeaveDao {
	//保存请假信息
	public void saveLeaveOrders(Leave leave);
	
	//通过姓名查询请假信息
	public List<Leave> queryLeaveMessageByName(String userName);
	
	//通过ID查询请假信息
	public Leave queryLeaveMessageByID(String id);
	
	//删除请假单
	public void deleteLeaveOrder(int id);
	
	//确认请假单
	public void submitOrder(@Param("id") int orderId, @Param("state") String state);
	
	//查询当前人所在部门
	public String queryDept(String currentUser);
	
	//查询领导姓名
	public String queryManager(@Param("deptName") String deptName,@Param("roleName") String roleName);
	
	//查询当前人的id
	public int queryUserId(String loginName);
	
	//查询当前人的角色id
	public int queryRoleId(int userId);
	
	//查询当前人角色
	public String queryRoleName(int roleId);
}
