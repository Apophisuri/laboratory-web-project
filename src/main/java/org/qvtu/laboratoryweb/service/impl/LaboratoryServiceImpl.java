package org.qvtu.laboratoryweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.qvtu.laboratoryweb.entity.Laboratory;
import org.qvtu.laboratoryweb.mapper.LaboratoryMapper;
import org.qvtu.laboratoryweb.service.LaboratoryService;
import java.util.List;

@Service
public class LaboratoryServiceImpl implements LaboratoryService {
    
    private final LaboratoryMapper laboratoryMapper;

    public LaboratoryServiceImpl(LaboratoryMapper laboratoryMapper) {
        this.laboratoryMapper = laboratoryMapper;
    }
    
    @Override
    public List<Laboratory> findAll() {
        return laboratoryMapper.findAll();
    }
    
    @Override
    public Laboratory findByLabId(String labId) {
        return laboratoryMapper.findByLabId(labId);
    }
    
    @Override
    public int insert(Laboratory laboratory) {
        return laboratoryMapper.insert(laboratory);
    }
    
    @Override
    public int updateByLabId(Laboratory laboratory) {
        return laboratoryMapper.updateByLabId(laboratory);
    }
    
    @Override
    public int deleteByLabId(String labId) {
        return laboratoryMapper.deleteByLabId(labId);
    }
}
