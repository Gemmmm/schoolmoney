package com.tc.sm.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.tc.sm.model.TiancekjAdmin;
import com.tc.sm.service.TiancekjAdminService;
import com.tc.sm.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static sun.management.Agent.error;

@Controller
@RequestMapping("/")
public class HomeController {
    Logger logger = LoggerFactory.getLogger(TiancekjAdmin.class);
    @Autowired
    private TiancekjAdminService adminService;
    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String test(Model model) {
        model.addAttribute("admin", new TiancekjAdmin());
        return "redirect:/login";
    }


    // 请求登录页面
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm(Model model) {
        // 构建一个空对象，否则前台表单不能正常渲染（）
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(TiancekjAdmin admin, BindingResult bindingResult, Model model) {

        // 登录的用户信息
        logger.info("login post admin:" + admin.toString());
        // 如果校验有错，重回登录页面
        if (bindingResult.hasErrors()) {
            // 输出错误信息
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError objectError : allErrors) {
                // 输出错误信息
                error(objectError.getDefaultMessage());
            }
            model.addAttribute("allErrors", allErrors);
            return "login";
        }


        TiancekjAdmin findAdmin = adminService.findAdmin(admin.getUname(), StringUtil.EncoderByMd5(admin.getPassword()));
        System.out.println("admin:" + findAdmin);

        if (findAdmin == null) {
            System.out.println("用户名或密码错误");
            model.addAttribute("loginError", "用户名或密码错误");
            return "redirect:/login";
        }


        //将user类存在session中
        session.setAttribute("admin", findAdmin);
        session.setMaxInactiveInterval(-1);
        return "redirect:/index";
    }

    // 处理对首页--index发起的请求
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Map<String, Object>> resultList = new ArrayList<>();

        TiancekjAdmin admin = (TiancekjAdmin) session.getAttribute("admin");
        if (admin == null) {
            return "login";
        }
        model.addAttribute("admin", admin);
        return "redirect:/order/all";
    }

    //无权限页面
    @RequestMapping(value = "/powerOff", method = RequestMethod.GET)
    public String powerOffPage() {
        return "powerOff";
    }
}
