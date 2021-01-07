package com.tc.sm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tc.sm.model.TiancekjAdmin;
import com.tc.sm.service.TiancekjAdminService;
import com.tc.sm.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private HttpSession session;
	@Autowired
	private TiancekjAdminService adminService;

	@RequestMapping(value = "/modifyPassword", method = RequestMethod.GET)
	public ModelAndView modifypasswordpage() {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("modifyPassword");
		return modelAndView;

	}

	@RequestMapping(value = "/modifyPassword", method = RequestMethod.POST)
	public ModelAndView modifypassword(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		TiancekjAdmin loginAdmin = (TiancekjAdmin) session.getAttribute("admin");

		String currentPassword = request.getParameter("currentPassword");
		String password = request.getParameter("password");
		String checkPassword = request.getParameter("checkPassword");
		System.out.println("password:" + currentPassword + " " + password + " " + checkPassword);

		if (currentPassword != null && !"".equals(currentPassword)) {
			if (!loginAdmin.getPassword().equals(StringUtil.EncoderByMd5(currentPassword))) {
				modelAndView.addObject("error1", "当前密码不正确");
				modelAndView.setViewName("modifyPassword");
				System.out.println("当前密码不正确");
			} else {
				if ((password != null && !"".equals(password)) && (checkPassword != null && !"".equals(checkPassword))) {
					if (!password.equals(checkPassword)) {
						modelAndView.addObject("error1", "两次输入的密码不相同");
						modelAndView.setViewName("modifyPassword");
						System.out.println( "两次输入的密码不相同");
					} else {
						loginAdmin.setPassword(StringUtil.EncoderByMd5(password));
						int count1 = adminService.modify(loginAdmin);
						if (count1 < 0) {
							modelAndView.addObject("error1", "修改密码失败");
							modelAndView.setViewName("modifyPassword");
							System.out.println( "修改密码失败");
						} else {
							session.setAttribute("admin", loginAdmin);
							modelAndView.setViewName("redirect:/order/all");
						}
					}
				} else {
					modelAndView.setViewName("modifyPassword");
					System.out.println( "密码或者确认密码为空");
				}
			}
		} else {
			modelAndView.setViewName("modifyPassword");
			modelAndView.addObject("error1", "当前密码为空");
		}
		return modelAndView;

	}

	@RequestMapping(value = "logout")
	public String logout() {
		if (session.getAttribute("admin") != null) {
			session.removeAttribute("admin");
		}
		return "redirect:/login";
	}

}
