package com.tc.sm.service.impl;

import com.tc.sm.dao.TiancekjOrderMapper;
import com.tc.sm.model.TiancekjOrder;
import com.tc.sm.service.TiancekjOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TiancekjOrderServiceImpl implements TiancekjOrderService {


    @Autowired(required = false)
    private TiancekjOrderMapper mapper;

    @Override
    public int deleteById(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<TiancekjOrder> getAll() {
        return mapper.selectAll();
    }

    @Override
    public TiancekjOrder getById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int modify(TiancekjOrder order) {
        return mapper.updateByPrimaryKeySelective(order);
    }

}
