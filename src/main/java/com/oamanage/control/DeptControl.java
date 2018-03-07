package com.oamanage.control;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.oamanage.po.Dept;
import com.oamanage.service.DeptService;
import com.oamanage.serviceImpl.DeptServiceImpl;

/**
 * 部门控制类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/dept")
public class DeptControl {
	
	private Logger log = LoggerFactory.getLogger(DeptControl.class);
	
	@Autowired
	private DeptService deptService = new DeptServiceImpl();
	private int page = 1;
	private int row = 14;
	
	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}
	
	//添加部门
	@RequestMapping("/addDept")
	@ResponseBody
	public String addDept(HttpServletRequest request){
		log.info("addDept Start");
		
		String deptName = request.getParameter("deptName");
		deptService.addDept(deptName);
		
		log.info("addDept End");
		return deptName;
	}
	
	
	//分页查询全部部门
	@RequestMapping("/queryAllDept")
	public String queryAllDept(HttpServletRequest request,Model model){
		log.info("queryAllDept Start");
		
		int count = deptService.queryDeptCount();
		int sumPage = count % row == 0 ? count / row :count / row + 1;
		
		String nowPage = request.getParameter("page");
		if(nowPage != null){
			int page = Integer.valueOf(nowPage).intValue();
			List<Dept> deptList = deptService.queryAllDept(page, row);
			model.addAttribute("nowPage", page);
			model.addAttribute("deptList", deptList);
		}else{
			List<Dept> deptList = deptService.queryAllDept(page,row);
			model.addAttribute("deptList",deptList);
			model.addAttribute("nowPage", page);
			model.addAttribute("sumPage", sumPage);
		}
		
		log.info("queryAllDept End");
		return "dept";
	}
	
	//查询全部部门
	@RequestMapping(value="/allDept")
	public @ResponseBody List<Dept> getAllDept(){
		List<Dept> deptList = deptService.allDept();
		return deptList;
	}
}
