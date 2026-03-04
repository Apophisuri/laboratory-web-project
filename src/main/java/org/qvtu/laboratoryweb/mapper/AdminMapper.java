package org.qvtu.laboratoryweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.qvtu.laboratoryweb.entity.Admin;

@Mapper
public interface AdminMapper {
    
    /**
     * 根据账号查询管理员
     * @param account 账号
     * @return 管理员信息
     */
    Admin findByAccount(@Param("account") String account);
}
