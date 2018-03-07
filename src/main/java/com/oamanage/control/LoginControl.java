package com.oamanage.control;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.oamanage.po.Staff;
import com.oamanage.service.LoginService;

/**
 * 登陆控制
 * @author Administrator
 *
 */
@Controller
public class LoginControl{
	
	private Logger log = LoggerFactory.getLogger(LoginControl.class);
	
	@Autowired
	private LoginService loginService;
	
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	
	//用户登录控制
	/*
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@RequestParam("username") String loginName,@RequestParam("password") String loginPassword,HttpSession session,Model model){
		Staff staff = loginService.login(loginName);
		
		System.out.println(loginName);
		
		if(staff == null){
			return "index";
		}else if(loginName.equals(staff.getLoginName()) && loginPassword.equals(staff.getLoginPassword())){
			
			session.setAttribute("loginName", staff.getUserName());
			return "main";
		}else{
			return "index";
		}	
	}
	*/
	
	//登陆页面
	@RequestMapping(value="/loginPage", method=RequestMethod.GET)
	public String loginPage(@RequestParam(value="error", required=false) String error){
		if(error != null){
			return "index";
		}else{
			return "index";
		}
	}
	
	
	//登录成功跳转
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(HttpSession session){
		log.info("登录开始");
		String userName = getPrincipal();
		if(userName == null){
			log.error("登录用户名未获取到");
			return "index";
		}else{
			Staff staff = loginService.login(userName);
			session.setAttribute("loginName", staff.getUserName());
			log.info("登录成功");
			return "main";
		}
		
		
	}
	
	//退出登录
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "index";
	}
	
	//获取登录验证成功后的用户名
	public String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails){
			userName = ((UserDetails)principal).getUsername();
		}else{
			userName = principal.toString();
		}
		return userName;
	}
}
