package com.tc.sm.service.impl;

import com.tc.sm.dao.TiancekjAdminMapper;
import com.tc.sm.model.TiancekjAdmin;
import com.tc.sm.service.TiancekjAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class TiancekjAdminServiceImpl implements TiancekjAdminService {

    @Autowired(required = false)
    private TiancekjAdminMapper mapper;

    @Override
    public TiancekjAdmin findAdmin(String uname, String password) {
        Example example = new Example(TiancekjAdmin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uname", uname);
        criteria.andEqualTo("password", password);
        List<TiancekjAdmin> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public int modify(TiancekjAdmin loginAdmin) {
        return mapper.updateByPrimaryKeySelective(loginAdmin);
    }
}
