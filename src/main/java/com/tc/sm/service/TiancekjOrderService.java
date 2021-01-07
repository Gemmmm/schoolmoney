package com.tc.sm.service;

import com.tc.sm.model.TiancekjOrder;

import java.util.List;

public interface TiancekjOrderService {
    int deleteById(Integer id);

    List<TiancekjOrder> getAll();

    TiancekjOrder getById(Integer id);

    int modify(TiancekjOrder order);
}
