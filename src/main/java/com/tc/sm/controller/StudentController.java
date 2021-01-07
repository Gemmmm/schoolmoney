package com.tc.sm.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tc.sm.model.TiancekjStudent;
import com.tc.sm.service.TiancekjOrderService;
import com.tc.sm.service.TiancekjStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("student")
public class StudentController {

    @Autowired
    private TiancekjStudentService studentService;

    @Autowired
    private TiancekjOrderService orderService;

    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView allStudents(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String schoolName,
            @RequestParam(required = false) Integer gradeid,
            @RequestParam(required = false) Integer classid,
            @RequestParam(required = false) String sname,
            @RequestParam(required = false) String subject,
            @RequestParam(required = false) String isPay) {
        ModelAndView modelAndView = new ModelAndView();
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        System.out.println("查询学生:");
        List<TiancekjStudent> students = null;
        if (schoolName != null && !"".equals(schoolName)) {
            modelAndView.addObject("schoolName", schoolName);
        } else {
            modelAndView.addObject("schoolName", "");
        }
        if (gradeid != null && gradeid != 0) {
            modelAndView.addObject("gradeid", gradeid);
        } else {
            modelAndView.addObject("gradeid", "");
        }
        if (classid != null && classid != 0) {
            modelAndView.addObject("classid", classid);
        } else {

            modelAndView.addObject("classid", "");
        }
        if (sname != null && !"".equals(sname)) {
            modelAndView.addObject("sname", sname);
        } else {
            modelAndView.addObject("sname", "");
        }
        if (subject != null && !"".equals(subject)) {
            modelAndView.addObject("subject", subject);
        } else {
            modelAndView.addObject("subject", "");
        }
        if (isPay != null && !"".equals(isPay)) {
            modelAndView.addObject("isPay", isPay);
        } else {
            modelAndView.addObject("isPay", "");
        }


        PageHelper.startPage(pageNum, pageSize);
        if (schoolName != null || gradeid != null || classid != null || sname != null || subject != null || isPay != null) {
            students = studentService.getByParameters(schoolName, gradeid, classid, sname, subject, isPay);

        } else {
            students = studentService.getAll();
        }

        PageInfo<TiancekjStudent> page = new PageInfo<>();
        if (students != null) {
            page = new PageInfo<>(students);
            for (TiancekjStudent stu : students) {
                map = new HashMap<>();

                Integer id = stu.getId();
                map.put("id", id);
                map.put("sname", stu.getSname());
                gradeid = stu.getGradeid();
                String gradename = "";
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
                map.put("gradename", gradename);

                classid = stu.getClassid();
                String classname = "";
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
                map.put("classname", classname);
                map.put("sno", stu.getSno());

                map.put("phone", stu.getPhone());
                map.put("city", stu.getCity());
                map.put("schoolname", stu.getSchoolName());
                if (stu.getSchoolType() == null) {

                    map.put("schooltype", "其他");
                } else {
                    if (stu.getSchoolType() == 1) {

                        map.put("schooltype", "小学");
                    } else {
                        map.put("schooltype", "初中学");
                    }
                }

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                map.put("createTime", sdf.format(stu.getCreateTime()));
                if (stu.getSubjcetCtj() != null) {
                    map.put("ctj", stu.getSubjcetCtj() + " " + stu.getSubjectCtjMoney() + "元");
                }else{
                    map.put("ctj", "");
                }

                if (stu.getSubjectYun() != null) {

                    map.put("ykt", stu.getSubjectYun() + " " + stu.getSubjectYunMoney() + "元");
                }else {

                    map.put("ykt", "");
                }
                Integer ctjIsPay = stu.getCtjIsPay();
                if (ctjIsPay == 1) {
                    map.put("ctjIsPay", "已付款");
                } else {
                    map.put("ctjIsPay", "未付款");
                }
                Integer yunIsPay = stu.getYunIsPay();
                if (yunIsPay == 1) {
                    map.put("yunIsPay", "已付款");
                } else {
                    map.put("yunIsPay", "未付款");
                }
                maps.add(map);
            }
        }


        modelAndView.addObject("students", maps);
        modelAndView.addObject("page", page);
        modelAndView.setViewName("students");
        return modelAndView;

    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPage(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("student", new TiancekjStudent());
        return "studentAdd";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView modelAndView(TiancekjStudent student, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ctj = request.getParameter("ctj");
        if ("ctj".equals(ctj)) {
            String ctjMoney = request.getParameter("ctjMoney");
            student.setSubjcetCtj("错题集");
            student.setSubjectCtjMoney(new BigDecimal(ctjMoney));
            student.setCtjIsPay(0);
        }
        String ykt = request.getParameter("ykt");
        if ("ykt".equals(ykt)) {
            String yktMoney = request.getParameter("yktMoney");
            student.setSubjectYun("云课堂");
            student.setSubjectYunMoney(new BigDecimal(yktMoney));
            student.setYunIsPay(0);
        }

        student.setCreateTime(new Date());
        int count1 = studentService.insert(student);

        modelAndView.setViewName("redirect:/student/all");
        return modelAndView;

    }

    @RequestMapping(value ="/export",method = RequestMethod.GET)
    @ResponseBody
    public void exportExcel(
            @RequestParam(required = false) String schoolName,
            @RequestParam(required = false) Integer gradeid,
            @RequestParam(required = false) Integer classid,
            @RequestParam(required = false) String sname,
            @RequestParam(required = false) String subject,
            @RequestParam(required = false) String isPay,
            HttpServletResponse response) {
        try {
            int flag = 0;
            if (subject != null && !"".equals(subject)) {
                if (subject.equals("ctj")) {
                    if (isPay != null && !"无".equals(isPay)) {
                        if (isPay.equals("1")) {
                            flag = 11;
                        } else if (isPay.equals("0")) {
                            flag = 10;
                        }
                    }
                } else if (subject.equals("ykt")) {
                    if (isPay != null && !"无".equals(isPay)) {
                        if (isPay.equals("1")) {
                            flag = 21;
                        } else if (isPay.equals("0")) {
                            flag = 20;
                        }
                    }
                }
            }

            // 获取数据源
            List<TiancekjStudent> studentList = null;
            if (schoolName != null || gradeid != null || classid != null || sname != null || subject != null || isPay != null) {
                studentList = studentService.getByParameters(schoolName, gradeid, classid, sname, subject, isPay);

            } else {
                studentList = studentService.getAll();
            }
            // 导出excel
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + new String("学生缴费表.xls".getBytes(), "ISO-8859-1"));
            response.setContentType("application/x-excel;charset=UTF-8");
            OutputStream outputStream = response.getOutputStream();

            // 导出
            studentService.exportExcel(studentList, flag, outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/import",method = RequestMethod.GET)
    public String importExcel(MultipartFile studentExcel, HttpServletRequest request, HttpSession session) {
        if (studentExcel == null) {
            session.setAttribute("excelName", "未上传文件，上传失败！");
            return "redirect:/student/all";
        }
        String studentExcelFileName = studentExcel.getOriginalFilename();
        if (!studentExcelFileName.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
            session.setAttribute("excelName", "文件格式不正确！请使用.xls或.xlsx后缀的文档，导入失败！");
            return "redirect:/student/all";
        }
        studentService.importExcel(studentExcel);
        session.setAttribute("excelName", "导入成功！");
        return "redirect:/student/all";
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public ModelAndView deleteStudent(@PathVariable(value = "id", required = true) Integer id) {
        ModelAndView mav = new ModelAndView();
        int count = studentService.deleteById(id);
        if (count < 1) {
            mav.addObject("studentDeleteError", "学生删除失败");
        }
        mav.setViewName("redirect:/student/all");
        return mav;

    }

    // 跳到修改科目信息页面
    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public ModelAndView modifyStudnetPages(@PathVariable(value = "id", required = true) Integer id) {
        ModelAndView mav = new ModelAndView();
        TiancekjStudent student = studentService.getById(id);

        mav.addObject("student", student);

        mav.setViewName("studentModify");
        return mav;
    }

    // 修改学生信息
    @RequestMapping(value = "/modify/{id}", method = RequestMethod.POST)
    public ModelAndView modifyStudent(@PathVariable(value = "id", required = true) Integer id,
                                      TiancekjStudent student, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();

        student.setId(id);
        int count = studentService.modify(student);
        if (count < 1) {
            mav.setViewName("studentModify");
            mav.addObject("subjectModifyError", "学生修改失败");
        } else {
            mav.setViewName("redirect:/student/all");
        }
        return mav;

    }
}
