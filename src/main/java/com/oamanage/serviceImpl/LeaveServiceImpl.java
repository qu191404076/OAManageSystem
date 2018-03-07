package com.oamanage.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oamanage.dao.LeaveDao;
import com.oamanage.dao.LoginDao;
import com.oamanage.po.Leave;
import com.oamanage.service.LeaveService;

/**
 * 请假申请实现类
 * @author Administrator
 *
 */
@Service
public class LeaveServiceImpl implements LeaveService {

	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private LeaveDao leaveDao;
	@Autowired
	private LoginDao loginDao;
	
	//保存请假流程
	public void saveLeaveFlow(Leave leave) {
		InputStream fis = this.getClass().getClassLoader().getResourceAsStream("diagrams/diagrams.zip");
		ZipInputStream zis = new ZipInputStream(fis);
		Deployment deploy = repositoryService.createDeployment().name("leaveFlow").addZipInputStream(zis).deploy();
		//保存请假单信息
		leaveDao.saveLeaveOrders(leave);
	}

	//查询请假信息
	@Override
	public List<Leave> queryLeaveMessage(String userName) {
		List<Leave> leaveList = leaveDao.queryLeaveMessageByName(userName);
		// TODO Auto-generated method stub
		return leaveList;
	}
	
	//确认请假单
	@Override
	public void submitLeaveOrders(int orderId,String userName) {
		String state = "等待提交";
		leaveDao.submitOrder(orderId,state);
		Map<String,Object> variable = new HashMap();
		variable.put("currentUser", userName);
		String key = "leave";
		String objId = key + "." + orderId;
		variable.put("objId", objId);
		runtimeService.startProcessInstanceByKey(key,objId,variable);
		
	}

	//删除请假单
	@Override
	public void deleteLeaveOrder(int id) {
		// TODO Auto-generated method stub
		leaveDao.deleteLeaveOrder(id);
	}

	//查询当前个人任务
	public List<Task> queryTask(String userName) {
		List<Task> taskList = taskService.createTaskQuery().taskAssignee(userName).orderByTaskCreateTime().asc().list();
		return taskList;
	}

	//提交请假单
	public void continueFlow(String taskId,String currentUser) {
		//查询请假单ID并更改状态
		String processInstanceId = taskService.createTaskQuery().taskId(taskId).singleResult().getProcessInstanceId();
		String businessKey = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult().getBusinessKey();
		int orderId = Integer.valueOf(businessKey.split("\\.")[1]);
		String state = "审批中";
		leaveDao.submitOrder(orderId,state);
		//查询下一审批人姓名
		String userName = nextAuditor(currentUser);
		Map<String, Object> variables = new HashMap();
		variables.put("nextUser", userName);
		taskService.complete(taskId, variables );
	}

	//查询下一审核人
	public String nextAuditor(String currentUser) {
		String role = null;
		String currentRoleName = loginDao.loginUser(currentUser).getRoles().get(0).getRoleName();
		switch(currentRoleName){
			case "ROLE_USER" : role = "ROLE_MANAGER";
							   break;
			case "ROLE_MANAGER" : role = "ROLE_PERSONNEL";
							   break;
			case "ROLE_PERSONNEL" : role = "ROLE_BOSS";
							   break;
			default : role = "ROLE_MANAGER";
							   break;
		}
		String deptName = null;
		if("ROLE_MANAGER".equals(role)){
			deptName = leaveDao.queryDept(currentUser);
		}else if("ROLE_PERSONNEL".equals(role)){
			deptName = "人事部";
		}else{
			deptName = "高管";
		}
		String userName = leaveDao.queryManager(deptName,role);
		return userName;
	}

	//审核请假单
	@Override
	public Map<String,Object> reviewLeaveOrder(String taskId) {
		//获取流程实例ID
		String processInstanceId = taskService.createTaskQuery().taskId(taskId).singleResult().getProcessInstanceId();
		//通过流程实例ID获取BUSINESS_KEY
		String businessKey = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult().getBusinessKey();
		String orderId = businessKey.split("\\.")[1];
		Leave leave = leaveDao.queryLeaveMessageByID(orderId);
		
		List<Comment> historyComments = getProcessComments(taskId);
		Map<String,Object> orderMesCom = new HashMap();
		orderMesCom.put("leave", leave);
		orderMesCom.put("historyComments", historyComments);
		
		return orderMesCom;
	}
	
	//获取批注内容
	@Override
	public List<Comment> getProcessComments(String taskId){
		List<Comment> historyComments = new ArrayList();
		//通过任务ID获取任务类
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		//获取历史流程实例
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
		List<HistoricActivityInstance> hais =  historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId()).activityType("userTask").list();
		for(HistoricActivityInstance hai : hais){
			String historyTaskId = hai.getTaskId();
			List<Comment> comment = taskService.getTaskComments(historyTaskId);
			if(comment != null && comment.size() > 0){
				historyComments.addAll(comment);
			}
		}
		return historyComments;
	}

	//同意申请
	@Override
	public void agreeApply(String taskId,String currentUser,String comment) {
		
		String processInstanceId = taskService.createTaskQuery().taskId(taskId).singleResult().getProcessInstanceId();
		Authentication.setAuthenticatedUserId(currentUser);
		//添加批注
		taskService.addComment(taskId, processInstanceId, comment);
		
		int userId = leaveDao.queryUserId(currentUser);
		int roleId = leaveDao.queryRoleId(userId);
		String roleName = leaveDao.queryRoleName(roleId);
		Map<String,Object> variables = new HashMap();
		if("ROLE_BOSS".equals(roleName)){
			String state = "审核通过";
			String businessKey = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult().getBusinessKey();
			int orderId = Integer.valueOf(businessKey.split("\\.")[1]);
			leaveDao.submitOrder(orderId, state);
			variables.put("approve", "同意");
			taskService.complete(taskId, variables);
		}else{
			//获取下一级审批人
			String userName = nextAuditor(currentUser);
			variables.put("nextUser", userName);
			variables.put("approve", "同意");
			taskService.complete(taskId, variables);
		}
		
	}

	//拒绝申请
	@Override
	public void rejectApply(String taskId, String currentUser, String comment) {
		// TODO Auto-generated method stub
		Authentication.setAuthenticatedUserId(currentUser);
		String processInstanceId = taskService.createTaskQuery().taskId(taskId).singleResult().getProcessInstanceId();
		taskService.addComment(taskId, processInstanceId, comment);
		Map<String,Object> variables = new HashMap();
		variables.put("approve", "拒绝");
		taskService.complete(taskId, variables);
		
		//查询请假单ID并更改状态
		String businessKey = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult().getBusinessKey();
		int orderId = Integer.valueOf(businessKey.split("\\.")[1]);
		String state = "未通过";
		leaveDao.submitOrder(orderId,state);
	}

	//申请人查看批注信息
	@Override
	public Map<String,Object> queryCommentByProposer(String leaveId) {
		Leave leave = leaveDao.queryLeaveMessageByID(leaveId);
		String businessKey = "leave" + "." + leaveId;
		//通过历史流程实例查询历史批注
		HistoricProcessInstance hisProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceBusinessKey(businessKey).singleResult();
		String processInstanceId = hisProcessInstance.getId();
		
		
		List<Comment> comments = taskService.getProcessInstanceComments(processInstanceId);
		Map<String,Object> leaveAndComments = new HashMap();
		leaveAndComments.put("leave", leave);
		leaveAndComments.put("comments", comments);
		return leaveAndComments;
	}

	//删除任务并结束流程
	@Override
	public void deleteTask(String taskId) {
		// TODO Auto-generated method stub
		String processInstanceId = taskService.createTaskQuery().taskId(taskId).singleResult().getProcessInstanceId();
		runtimeService.deleteProcessInstance(processInstanceId, "撤销请假申请");
	}

	
	
	
	
}
