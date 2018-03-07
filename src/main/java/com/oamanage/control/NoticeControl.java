package com.oamanage.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 公告控制类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/notice")
public class NoticeControl {

	@RequestMapping("/addNotice")
	public String getNoticeContent(@RequestParam("notice") String notice, HttpServletRequest request){
		
		System.out.println(notice);
		request.setAttribute("Notice", notice);
		return "notice";
		
	}
}
