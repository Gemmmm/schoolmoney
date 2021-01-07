package com.tc.sm.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tc.sm.model.TiancekjOrder;
import com.tc.sm.service.TiancekjOrderService;
import com.tc.sm.service.TiancekjStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("order")
public class OrderController {
    @Autowired
    public TiancekjOrderService orderService;
    @Autowired
    public TiancekjStudentService studentService;
    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteOrder(@PathVariable(value = "id", required = true) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        int count = orderService.deleteById(id);
        if (count < 1) {
            modelAndView.addObject("orderDeleteError", "订单删除失败");
        }
        modelAndView.setViewName("redirect:/order/all");
        return modelAndView;

    }


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView allOrders(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        ModelAndView modelAndView = new ModelAndView();
        Object admin = session.getAttribute("admin");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;

        List<TiancekjOrder> orderList = orderService.getAll();

        PageInfo<TiancekjOrder> page = new PageInfo<>();
        if (orderList != null && orderList.size() > 0) {
            page = new PageInfo<TiancekjOrder>(orderList);
            for (TiancekjOrder order : orderList) {
                map = new HashMap<String, Object>();
                map.put("id", order.getId());
                BigDecimal money = order.getMoney();
                if (money != null) {

                    map.put("money", money.toString());
                } else {

                    map.put("money", "无");
                }
                map.put("uId", order.getUid());
                Date createTime = order.getCreateTime();
                if (createTime != null) {
                    map.put("createTime", sdf.format(order.getCreateTime()));
                } else {
                    map.put("createTime", "无");
                }
                Date payTime = order.getPayTime();
                if (payTime != null) {
                    map.put("payTime", sdf.format(order.getPayTime()));
                } else {
                    map.put("payTime", "无");
                }
                map.put("schoolName", order.getSchoolName());

                Integer gradeid = order.getGradeid();
                String gradename = "";
                if (gradeid == null) {
                    gradename = "无";
                } else {
                    switch (gradeid) {
                        case 1:
                            gradename = "小一";
                            break;
                        case 2:
                            gradename = "小二";
                            break;
                        case 3:
                            gradename = "小三";
                            break;
                        case 4:
                            gradename = "小四";
                            break;
                        case 5:
                            gradename = "小五";
                            break;
                        case 6:
                            gradename = "小六";
                            break;
                        case 7:
                            gradename = "初一";
                            break;
                        case 8:
                            gradename = "初二";
                            break;
                        case 9:
                            gradename = "初三";
                            break;
                        case 10:
                            gradename = "高一";
                            break;
                        case 11:
                            gradename = "高二";
                            break;
                        case 12:
                            gradename = "高三";
                            break;

                        default:
                            break;
                    }
                }

                map.put("gradeName", gradename);

                Integer classid = order.getClassid();
                String classname = "";
                if (classid == null) {
                    classname = "无";
                } else {
                    switch (classid) {
                        case 1:
                            classname = "1班";
                            break;
                        case 2:
                            classname = "2班";
                            break;
                        case 3:
                            classname = "3班";
                            break;
                        case 4:
                            classname = "4班";
                            break;
                        case 5:
                            classname = "5班";
                            break;
                        case 6:
                            classname = "6班";
                            break;
                        case 7:
                            classname = "7班";
                            break;
                        case 8:
                            classname = "8班";
                            break;
                        case 9:
                            classname = "9班";
                            break;
                        case 10:
                            classname = "10班";
                            break;
                        case 11:
                            classname = "11班";
                            break;
                        case 12:
                            classname = "12班";
                            break;
                        case 13:
                            classname = "13班";
                            break;
                        case 14:
                            classname = "14班";
                            break;
                        case 15:
                            classname = "15班";
                            break;
                        case 16:
                            classname = "16班";
                            break;
                        case 17:
                            classname = "17班";
                            break;
                        case 18:
                            classname = "18班";
                            break;
                        case 19:
                            classname = "19班";
                            break;
                        case 20:
                            classname = "20班";
                            break;

                        default:
                            break;
                    }
                }
                map.put("className", classname);

                map.put("sName", order.getStudentName());
                String subjectName = order.getSubjectName();
                if ("1".equals(subjectName)) {
                    map.put("subjectName", "错题集");
                } else if ("2".equals(subjectName)) {
                    map.put("subjectName", "云课堂");
                } else if ("3".equals(subjectName)) {
                    map.put("subjectName", "错题集、云课堂");
                }
                Integer isPay = order.getIsPay();
                if (isPay == 1) {
                    map.put("isPay", "已付款");
                } else {
                    map.put("isPay", "未付款");
                }
                Date returnTime = order.getReturnTime();
                if (returnTime != null) {
                    map.put("returnTime", sdf.format(returnTime));
                } else {
                    map.put("returnTime", "无");
                }
                map.put("id", order.getId());
                map.put("phone", order.getPhone());
                map.put("ordersn", order.getOrdersn());
                int flag = order.getFlag();
                if (flag == 1) {
                    map.put("flag", "未付款");
                } else if (flag == 2) {
                    map.put("flag", "已付款");
                } else if (flag == 3) {
                    map.put("flag", "已退款");
                }
                maps.add(map);
            }
        }


        modelAndView.addObject("orderList", maps);
        modelAndView.addObject("page", page);
        modelAndView.setViewName("orders");
        return modelAndView;

    }

    @RequestMapping(value = "/updateFlag/{id}", method = RequestMethod.GET)
    public ModelAndView updateOrderFlag(@PathVariable(value = "id", required = true) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        TiancekjOrder order=orderService.getById(id);
        order.setFlag(3);
        int count = orderService.modify(order);
        modelAndView.setViewName("redirect:/order/all");
        return modelAndView;


    }

}
