package com.oamanage.control;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oamanage.po.Leave;
import com.oamanage.service.LeaveService;
import com.oamanage.serviceImpl.LeaveServiceImpl;

@Controller
@RequestMapping("/leave")
public class LeaveControl {
	
	@Autowired
	private LeaveService leaveService = new LeaveServiceImpl();
	
	//填写请假单
	@RequestMapping("/addLeave")
	public String addLeave(){
		return "addLeave";
	}
	
	//部署流程
	@RequestMapping(value="start",method=RequestMethod.POST)
	public String deployFlow(@RequestBody Leave leave){
		
		leaveService.saveLeaveFlow(leave);
		return "main";
	}
	
	//查询请假单信息
	@RequestMapping(value="queryLeaveMessage")
	public String queryLeaveMessage(HttpSession session,Model model){
		String userName = (String)session.getAttribute("loginName");
		List<Leave> leaveList = leaveService.queryLeaveMessage(userName);
//		session.setAttribute("leaveMessage", leaveList);
		model.addAttribute("leaveMessage", leaveList);
		return "queryLeave";
	}
	
	//删除请假单
	@RequestMapping(value="/deleteLeaveOrder")
	public String deleteLeaveOrder(int id){
		leaveService.deleteLeaveOrder(id);
		return "redirect:queryLeaveMessage.do";
	}
	
	//确认请假单
	@RequestMapping(value="submitLeaveMessage")
	public String submitLeaveOrders(int orderId,HttpSession session){
		String userName = (String) session.getAttribute("loginName");
		leaveService.submitLeaveOrders(orderId,userName);
		return "redirect:queryLeaveMessage.do";
	}
	
	//查询个人任务
	@RequestMapping(value="/queryTask")
	public String queryTask(HttpSession session,Model model){
		String userName = (String) session.getAttribute("loginName");
		List<Task> taskList = leaveService.queryTask(userName);
//		session.setAttribute("taskList", taskList);
		model.addAttribute("taskList", taskList);
		return "queryTask";
	}
	
	//提交请假单
	@RequestMapping(value="/continueFlow")
	public String continueFlow(String taskId,HttpSession session){
		String userName = (String) session.getAttribute("loginName");
		leaveService.continueFlow(taskId,userName);
		return "redirect:queryTask.do";
	}
	
	//审核请假单
	@RequestMapping(value="/reviewLeaveOrder")
	public String reviewLeaveOrder(String taskId,HttpSession session, Model model){
		Map<String,Object> orderMesCom = leaveService.reviewLeaveOrder(taskId);
		Leave leave = (Leave) orderMesCom.get("leave");
		List<Comment> historyComments = (List<Comment>) orderMesCom.get("historyComments");
//		session.setAttribute("reviewLeaveMessage", leave);
//		session.setAttribute("historyComments", historyComments);
//		session.setAttribute("taskId", taskId);
		model.addAttribute("reviewLeaveMessage", leave);
		model.addAttribute("historyComments", historyComments);
		model.addAttribute("taskId", taskId);
		return "reviewLeaveOrder";
	}
	
	//同意申请
	@RequestMapping(value="/agreeApply", method=RequestMethod.POST)
	public String agreeApply(@RequestBody Map<String,Object> postilMap, HttpSession session){
		String currentUser = (String) session.getAttribute("loginName");
		String taskId = (String) postilMap.get("taskId");
		String comment = (String) postilMap.get("postil");
		leaveService.agreeApply(taskId, currentUser,comment);
		return "redirect:queryTask.do";
	}
	
	//拒绝申请
	@RequestMapping(value="/rejectApply", method=RequestMethod.POST)
	public String rejectApply(@RequestBody Map<String,Object> postilMap,HttpSession session){
		String currentUser = (String) session.getAttribute("loginName");
		String taskId = (String) postilMap.get("taskId");
		String comment = (String) postilMap.get("postil");
		leaveService.rejectApply(taskId, currentUser, comment);
		return "redirect:queryTask.do";
	}
	
	//申请人查看批注信息
	@RequestMapping(value="/queryHistoryCommentsByProposer")
	public String queryCommentByProposer(String leaveId, Model model){
		Map<String,Object> leaveAndComments = leaveService.queryCommentByProposer(leaveId);
		Leave leave = (Leave) leaveAndComments.get("leave");
		List<Comment> comments = (List<Comment>) leaveAndComments.get("comments");
//		session.setAttribute("leaveMessageByProposer", leave);
//		session.setAttribute("historyCommentsByProposer", comments);
		model.addAttribute("leaveMessageByProposer", leave);
		model.addAttribute("historyCommentsByProposer", comments);
		return "LeaveMessageByProposer";
	}
	
	//删除任务
	@RequestMapping(value="/deleteTask")
	public String deleteTask(String taskId){
		leaveService.deleteTask(taskId);
		return "redirect:queryTask.do";
	}
	
}
