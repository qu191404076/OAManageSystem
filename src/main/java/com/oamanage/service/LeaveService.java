package com.oamanage.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;

import com.oamanage.po.Leave;

/**
 * 请假申请
 * @author Administrator
 *
 */
public interface LeaveService {
	
	//保存请假信息
	public void saveLeaveFlow(Leave leave);
	
	//查询请假信息
	public List<Leave> queryLeaveMessage(String userName);
	
	//确认请假单
	public void submitLeaveOrders(int orderId,String userName);
	
	//删除请假单
	public void deleteLeaveOrder(int id);
	
	//查询个人任务
	public List<Task> queryTask(String userName);
	
	//提交请假单
	public void continueFlow(String taskId,String currentUser);
	
	//查询下一审核人
	public String nextAuditor(String currentUser);
	
	//审核请假单
	public Map<String,Object> reviewLeaveOrder(String taskId);
	
	//获取批注内容
	public List<Comment> getProcessComments(String taskId);
	
	//同意申请
	public void agreeApply(String taskId,String currentUser,String comment);
	
	//拒绝申请
	public void rejectApply(String taskId,String currentUser,String comment);
	
	//申请人查看批注信息
	public Map<String,Object> queryCommentByProposer(String leaveId);
	
	//删除任务并结束流程
	public void deleteTask(String taskId);
	
}
