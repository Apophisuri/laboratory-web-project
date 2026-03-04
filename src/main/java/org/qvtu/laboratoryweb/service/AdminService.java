package org.qvtu.laboratoryweb.service;

import org.qvtu.laboratoryweb.entity.Admin;

public interface AdminService {
    
    /**
     * 根据账号查询管理员
     * @param account 账号
     * @return 管理员信息
     */
    Admin findByAccount(String account);
}
