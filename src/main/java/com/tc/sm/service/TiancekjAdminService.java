package com.tc.sm.service;

import com.tc.sm.model.TiancekjAdmin;

public interface TiancekjAdminService {
    TiancekjAdmin findAdmin(String uname, String password);

    int modify(TiancekjAdmin loginAdmin);
}
