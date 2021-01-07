package com.tc.sm.component;

import com.tc.sm.model.TiancekjAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author DELL
 * @date 2020/8/13 16:13
 */
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(LoginHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TiancekjAdmin admin = (TiancekjAdmin) request.getSession().getAttribute("admin");
        String uri = request.getRequestURI();
        Date date = new Date();
        if (admin == null) {
            System.out.println("未登录,时间:" + sdf.format(date) );
            response.sendRedirect("/login");
           return false;
        } else {
            System.out.println("已登录,时间:" + sdf.format(date)+ " " + admin.getUname());
           // return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
