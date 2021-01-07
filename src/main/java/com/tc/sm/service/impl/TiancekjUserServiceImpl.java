package com.tc.sm.service.impl;

import com.tc.sm.dao.TiancekjUserMapper;
import com.tc.sm.service.TiancekjUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TiancekjUserServiceImpl implements TiancekjUserService {

    @Autowired(required = false)
    private TiancekjUserMapper mapper;
}
