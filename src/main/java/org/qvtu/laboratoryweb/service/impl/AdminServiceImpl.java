package org.qvtu.laboratoryweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.qvtu.laboratoryweb.entity.Admin;
import org.qvtu.laboratoryweb.mapper.AdminMapper;
import org.qvtu.laboratoryweb.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
    
    private final AdminMapper adminMapper;

    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }
    
    @Override
    public Admin findByAccount(String account) {
        return adminMapper.findByAccount(account);
    }
}
