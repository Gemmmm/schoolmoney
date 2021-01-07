package com.tc.sm.service.impl;

import com.tc.sm.dao.TiancekjStudentMapper;
import com.tc.sm.model.TiancekjStudent;
import com.tc.sm.service.TiancekjStudentService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class TiancekjStudentServiceImpl implements TiancekjStudentService {
    @Autowired(required = false)
    private TiancekjStudentMapper mapper;

    @Override
    public List<TiancekjStudent> getAll() {
        return mapper.selectAll();
    }

    @Override
    public int insert(TiancekjStudent student) {
        return mapper.insertSelective(student);
    }

    @Override
    public int exportExcel(List<TiancekjStudent> studentList, int flag, OutputStream outputStream) {
        // TODO Auto-generated method stub
        // 1.创建工作簿
        HSSFWorkbook hwb = new HSSFWorkbook();
        // 1.1创建合并单元格
        // CellRangeAddress cellRangeAddress =new CellRangeAddress(0,0,0,4);
        // 2.创建工作表
        HSSFSheet sheet = hwb.createSheet("学生缴费表");
        // 2.1添加合并单元格
        // sheet.addMergedRegion(cellRangeAddress);
        // 3.1创建第一行及单元格
//        HSSFRow row1 = sheet.createRow(0);
//        HSSFCell cell1 = row1.createCell(0);
//        cell1.setCellValue("用户信息");
        // 3.2创建第二行及单元格
        HSSFRow row2 = sheet.createRow(0);
        String[] row2Cell = {"编号", "学校", "年级", "班级", "学号", "学生", "项目", "是否缴费"};
        for (int i = 0; i < row2Cell.length; i++) {
            row2.createCell(i).setCellValue(row2Cell[i]);
        }
        // 3.3创建第三行及单元格
        if (studentList != null && studentList.size() > 0) {
            System.out.println("flag:" + flag);
            for (int j = 0, k = 1; j < studentList.size(); j++) {
                System.out.println(studentList.get(j));
                HSSFRow rowUser = sheet.createRow(k);
                Integer ctjIsPay = studentList.get(j).getCtjIsPay();
                Integer yunIsPay = studentList.get(j).getYunIsPay();
                if (flag == 0) {
                    rowUser.createCell(0).setCellValue(k);
                    rowUser.createCell(1).setCellValue(studentList.get(j).getSchoolName());
                    rowUser.createCell(2).setCellValue(studentList.get(j).getGradeid());
                    rowUser.createCell(3).setCellValue(studentList.get(j).getClassid());
                    rowUser.createCell(4).setCellValue(studentList.get(j).getSno());
                    rowUser.createCell(5).setCellValue(studentList.get(j).getSname());
                    String ctj = studentList.get(j).getSubjcetCtj();
                    String yun = studentList.get(j).getSubjectYun();
                    rowUser.createCell(6).setCellValue(ctj + "|" + yun);
                    String ctjispay = "";
                    if (ctjIsPay == 0) {

                        ctjispay = "否";
                    } else if (ctjIsPay == 1) {

                        ctjispay = "是";
                    }

                    String yunispay = "";
                    if (yunIsPay == 0) {

                        ctjispay = "否";
                    } else if (yunIsPay == 1) {

                        ctjispay = "是";
                    }
                    rowUser.createCell(7).setCellValue(ctjispay + "|" + yunispay);

                } else if (flag == 10) {
                    if (ctjIsPay == 0) {
                        rowUser.createCell(0).setCellValue(k);
                        rowUser.createCell(1).setCellValue(studentList.get(j).getSchoolName());
                        rowUser.createCell(2).setCellValue(studentList.get(j).getGradeid());
                        rowUser.createCell(3).setCellValue(studentList.get(j).getClassid());
                        rowUser.createCell(4).setCellValue(studentList.get(j).getSno());
                        rowUser.createCell(5).setCellValue(studentList.get(j).getSname());
                        rowUser.createCell(6).setCellValue(studentList.get(j).getSubjcetCtj());
                        rowUser.createCell(7).setCellValue("否");
                    }
                } else if (flag == 11) {
                    if (ctjIsPay == 1) {
                        rowUser.createCell(0).setCellValue(k);
                        rowUser.createCell(1).setCellValue(studentList.get(j).getSchoolName());
                        rowUser.createCell(2).setCellValue(studentList.get(j).getGradeid());
                        rowUser.createCell(3).setCellValue(studentList.get(j).getClassid());
                        rowUser.createCell(4).setCellValue(studentList.get(j).getSno());
                        rowUser.createCell(5).setCellValue(studentList.get(j).getSname());
                        rowUser.createCell(6).setCellValue(studentList.get(j).getSubjcetCtj());
                        rowUser.createCell(7).setCellValue("是");
                    }

                } else if (flag == 20) {
                    if (yunIsPay == 0) {
                        rowUser.createCell(0).setCellValue(k);
                        rowUser.createCell(1).setCellValue(studentList.get(j).getSchoolName());
                        rowUser.createCell(2).setCellValue(studentList.get(j).getGradeid());
                        rowUser.createCell(3).setCellValue(studentList.get(j).getClassid());
                        rowUser.createCell(4).setCellValue(studentList.get(j).getSno());
                        rowUser.createCell(5).setCellValue(studentList.get(j).getSname());
                        rowUser.createCell(6).setCellValue(studentList.get(j).getSubjectYun());
                        rowUser.createCell(7).setCellValue("否");
                    }
                } else if (flag == 21) {
                    if (yunIsPay == 1) {
                        rowUser.createCell(0).setCellValue(k);
                        rowUser.createCell(1).setCellValue(studentList.get(j).getSchoolName());
                        rowUser.createCell(2).setCellValue(studentList.get(j).getGradeid());
                        rowUser.createCell(3).setCellValue(studentList.get(j).getClassid());
                        rowUser.createCell(4).setCellValue(studentList.get(j).getSno());
                        rowUser.createCell(5).setCellValue(studentList.get(j).getSname());
                        rowUser.createCell(6).setCellValue(studentList.get(j).getSubjectYun());
                        rowUser.createCell(7).setCellValue("是");
                    }
                }
                k++;
            }
        }
        // 5.输出
        try {
            hwb.write(outputStream);
            return 1;
        } catch (IOException e) {
            return -1;

        }


    }

    @Override
    public int importExcel(MultipartFile studentExcel) {
        // 获取输入流
        InputStream inputStream = null;
        // 创建读取工作簿
        Workbook workbook = null;
        try {
            inputStream = studentExcel.getInputStream();
            workbook = WorkbookFactory.create(inputStream);
            // 获取工作表
            Sheet sheet = workbook.getSheetAt(0);
            // 获取总行
            int rows = sheet.getPhysicalNumberOfRows();
            if (rows > 1) {
                // 获取单元格
                for (int i = 1; i < rows; i++) {
                    Row row = sheet.getRow(i);
                    TiancekjStudent student = new TiancekjStudent();
                    String school = row.getCell(0).getStringCellValue();
                    if (school != null && !"".equals(school)) {
                        student.setSchoolName(school);
                        try {
                            int grade = (int) row.getCell(1).getNumericCellValue();
                            student.setGradeid(grade);
                        } catch (Exception e) {
                            String grade = row.getCell(1).getStringCellValue();
                            student.setGradeid(Integer.valueOf(grade));
                        }

                        try {
                            int classn = (int) row.getCell(2).getNumericCellValue();
                            student.setClassid(classn);
                        } catch (Exception e) {
                            String classn = row.getCell(2).getStringCellValue();
                            student.setClassid(Integer.valueOf(classn));

                        }

                        String sname = row.getCell(3).getStringCellValue();
                        student.setSname(sname);
                        int money1 = -1;
                        String subjcet1 = null;
                        if (row.getCell(4) != null && row.getCell(5) != null) {

                            subjcet1 = row.getCell(4).getStringCellValue();
                            student.setSubjcetCtj(subjcet1);
                            try {
                                money1 = (int) row.getCell(5).getNumericCellValue();
                                student.setSubjectCtjMoney(new BigDecimal(money1));
                            } catch (Exception e) {
                                String money11 = row.getCell(5).getStringCellValue();
                                student.setSubjectCtjMoney(new BigDecimal(money11));
                            }

                            student.setCtjIsPay(0);
                        }
                        String subjcet2 = null;
                        int money2 = -1;
                        if (row.getCell(6) != null && row.getCell(7) != null) {
                            subjcet2 = row.getCell(6).getStringCellValue();
                            student.setSubjectYun(subjcet2);
                            try {
                                money2 = (int) row.getCell(7).getNumericCellValue();
                                student.setSubjectYunMoney(new BigDecimal(money2));
                            } catch (Exception e) {
                                String money22 = row.getCell(7).getStringCellValue();
                                student.setSubjectYunMoney(new BigDecimal(money22));
                            }
                            student.setYunIsPay(0);
                        }
                        if (row.getCell(8) != null) {
                            try {
                                String sno = row.getCell(8).getStringCellValue();
                                student.setSno(sno);
                            } catch (Exception e) {
                                int sno = (int) row.getCell(8).getNumericCellValue();
                                student.setSno(String.valueOf(sno));
                            }

                        }
                        student.setCreateTime(new Date());
                        mapper.insertSelective(student);

                    }
                }

            }

            inputStream.close();
        } catch (IOException e) {
            return -1;
        } catch (InvalidFormatException e) {
            return -1;
        }
        return 1;
    }

    @Override
    public int deleteById(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public TiancekjStudent getById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int modify(TiancekjStudent student) {
        return mapper.updateByPrimaryKeySelective(student);
    }

    @Override
    public List<TiancekjStudent> getByParameters(String schoolName, Integer gradeid, Integer classid, String sname, String subject, String isPay) {
        Example example = new Example(TiancekjStudent.class);
        Example.Criteria criteria = example.createCriteria();
        if (schoolName != null && !"".equals(schoolName)) {
            criteria.andEqualTo("schoolName", schoolName);
        }
        if (gradeid != null && gradeid != 0) {
            criteria.andEqualTo("gradeid", gradeid);
        }
        if (classid != null && classid != 0) {
            criteria.andEqualTo("classid", classid);
        }
        if (sname != null && !"".equals(sname)) {
            criteria.andEqualTo("sname", sname);
        }
        if (subject != null && !"".equals(subject)) {
            if (subject.equals("ctj")) {
                if (isPay != null && !"".equals(isPay) && !"无".equals(isPay)) {
                    if (isPay.equals("1")) {
                        criteria.andEqualTo("ctjIsPay", 1);
                    } else if (isPay.equals("0")) {
                        criteria.andEqualTo("ctjIsPay", 0);
                    }
                }
            } else if (subject.equals("ykt")) {
                if (isPay != null && !"".equals(isPay) && !"无".equals(isPay)) {
                    if (isPay.equals("1")) {
                        criteria.andEqualTo("yktIsPay", 1);
                    } else if (isPay.equals("0")) {
                        criteria.andEqualTo("yktIsPay", 0);
                    }
                }
            }
        }
        List<TiancekjStudent> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

}
