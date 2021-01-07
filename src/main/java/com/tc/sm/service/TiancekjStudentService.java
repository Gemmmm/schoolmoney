package com.tc.sm.service;

import com.tc.sm.model.TiancekjStudent;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.util.List;

public interface TiancekjStudentService {
    List<TiancekjStudent> getAll();

    int insert(TiancekjStudent student);

    int exportExcel(List<TiancekjStudent> studentList, int flag, OutputStream outputStream);

    int importExcel(MultipartFile studentExcel);

    int deleteById(Integer id);

    TiancekjStudent getById(Integer id);

    int modify(TiancekjStudent student);

    List<TiancekjStudent> getByParameters(String schoolName, Integer gradeid, Integer classid, String sname, String subject, String isPay);
}
