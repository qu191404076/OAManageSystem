package com.oamanage.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oamanage.exception.AddStaffInfoException;
import com.oamanage.po.Staff;
import com.oamanage.service.StaffService;
import com.oamanage.serviceImpl.StaffServiceImpl;

/**
 * 员工Control
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/staff")
public class StaffControl {
	private Logger logger = LoggerFactory.getLogger(StaffControl.class);
	
	@Autowired
	private StaffService staffService = new StaffServiceImpl();
	private int page = 1;
	private int row = 5;
	
	
	public StaffService getStaffService() {
		return staffService;
	}

	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}
	
	//添加人员
	@RequestMapping(value="/addStaff",method=RequestMethod.POST)
	public String addStaff(@RequestBody Staff staff){
		staffService.addStaff(staff);
		staffService.addStaffDept(staff);
		staffService.addStaffRole(staff);
		return "main";
		
	}
	
	
	//人员列表
	@RequestMapping(value="/queryAllStaff")
	public String queryAllStaff(HttpServletRequest request){
		HttpSession session = request.getSession();
		int sum = staffService.queryStaffCount();
		int sumPage = sum % row == 0 ? sum / row : sum / row + 1;
		
		String nextPage = request.getParameter("page");
		if(nextPage == null){
			List<Staff> sList = staffService.queryAllStaff(page,row);
			session.setAttribute("sumPage", sumPage);
			session.setAttribute("staffList", sList);
			session.setAttribute("nowPage", page);
		}else{
			int page = Integer.valueOf(nextPage).intValue();
			List<Staff> sList = staffService.queryAllStaff(page, row);
			session.setAttribute("staffList", sList);
			session.setAttribute("nowPage", page);
		}
		
		
		
		return "staff";
	}
	
	//删除人员
	@RequestMapping(value="/deleteStaff")
	public String deleteStaff(String userId){
		staffService.deleteStaff(userId);
		return "redirect:queryAllStaff.do";
	}
	
	//修改密码
	@RequestMapping(value="/modifyPwd",method=RequestMethod.POST)
	public String modifyPwd(@RequestParam("loginPassword") String loginPassword,HttpSession session){
		
		staffService.modifyPwd((String)session.getAttribute("loginName"), loginPassword);
		return "index";
	}
}
