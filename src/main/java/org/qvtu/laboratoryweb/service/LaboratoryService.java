package org.qvtu.laboratoryweb.service;

import org.qvtu.laboratoryweb.entity.Laboratory;
import java.util.List;

public interface LaboratoryService {
    
    /**
     * 查询所有实验室
     * @return 实验室列表
     */
    List<Laboratory> findAll();
    
    /**
     * 根据房间号查询实验室
     * @param labId 房间号
     * @return 实验室信息
     */
    Laboratory findByLabId(String labId);
    
    /**
     * 添加实验室
     * @param laboratory 实验室信息
     * @return 影响行数
     */
    int insert(Laboratory laboratory);
    
    /**
     * 根据房间号修改实验室信息
     * @param laboratory 实验室信息
     * @return 影响行数
     */
    int updateByLabId(Laboratory laboratory);
    
    /**
     * 根据房间号删除实验室
     * @param labId 房间号
     * @return 影响行数
     */
    int deleteByLabId(String labId);
}
