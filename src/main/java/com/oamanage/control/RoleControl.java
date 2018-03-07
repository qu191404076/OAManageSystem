package com.oamanage.control;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oamanage.po.Role;
import com.oamanage.service.RoleService;
import com.oamanage.serviceImpl.RoleServiceImpl;

/**
 * 角色控制类
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/role")
public class RoleControl {
	
	
	@Autowired
	private RoleService roleService = new RoleServiceImpl();
	
	//查询全部角色名称
	@RequestMapping(value="/queryAllRole")
	public String queryRoles(Model model){
		List<Role> roleList = roleService.queryAllRoles();
		model.addAttribute("roleList", roleList);
		return "role";
	}
	
	//添加角色
	@RequestMapping(value="/addRole", method=RequestMethod.POST)
	public String addRole(String roleName){
		roleService.addRole(roleName);
		return "main";
	}
	
	//添加员工时查询角色
	@RequestMapping(value="/queryRoleByAddStaff", method=RequestMethod.POST)
	public @ResponseBody List<Role> queryRoleByAddStaff(){
		List<Role> roleList = roleService.queryAllRoles();
		return roleList;
	}
}
