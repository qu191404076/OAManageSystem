package com.oamanage.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/exception")
public class AccessDeniedControl {
	private Logger logger = LoggerFactory.getLogger(AccessDeniedControl.class);
	
	@RequestMapping(value="/accessDenied")
	public void showErrorMessage(HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw;
		try {
			pw = response.getWriter();
			pw.print("<script type=\"text/javascript\">alert('没有权限执行此操作');history.back(-1);</script>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
	}
}
