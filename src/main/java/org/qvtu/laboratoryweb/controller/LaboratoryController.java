package org.qvtu.laboratoryweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.qvtu.laboratoryweb.entity.Laboratory;
import org.qvtu.laboratoryweb.entity.Result;
import org.qvtu.laboratoryweb.service.LaboratoryService;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LaboratoryController {
    
    private final LaboratoryService laboratoryService;

    public LaboratoryController(LaboratoryService laboratoryService) {
        this.laboratoryService = laboratoryService;
    }
    
    /**
     * 查询全部实验室
     * @return 统一返回结果
     */
    @GetMapping("/laboratories")
    public Result findAll() {
        List<Laboratory> laboratories = laboratoryService.findAll();
        return new Result(true, "获取成功", laboratories);
    }
    
    /**
     * 根据房间号查询实验室
     * @param labId 房间号
     * @return 统一返回结果
     */
    @GetMapping("/laboratories/{labId}")
    public Result findByLabId(@PathVariable String labId) {
        // 校验房间号
        if (labId == null || labId.trim().isEmpty()) {
            return new Result(false, "请传递代码", null);
        }
        
        Laboratory laboratory = laboratoryService.findByLabId(labId.trim());
        if (laboratory == null) {
            return new Result(false, "代码无效", null);
        }
        
        return new Result(true, "获取成功", laboratory);
    }
    
    /**
     * 添加实验室
     * @param laboratory 实验室信息
     * @return 统一返回结果
     */
    @PostMapping("/laboratories")
    public Result insert(@RequestBody Laboratory laboratory) {
        // 校验必填项
        if (laboratory.getLabId() == null || laboratory.getLabId().trim().isEmpty()) {
            return new Result(false, "请必填项", null);
        }
        if (laboratory.getLabName() == null || laboratory.getLabName().trim().isEmpty()) {
            return new Result(false, "请必填项", null);
        }
        
        try {
            int rows = laboratoryService.insert(laboratory);
            if (rows > 0) {
                return new Result(true, "添加成功", rows);
            } else {
                return new Result(false, "添加失败", null);
            }
        } catch (Exception e) {
            return new Result(false, "添加失败", null);
        }
    }
    
    /**
     * 根据房间号修改实验室
     * @param labId 房间号
     * @param laboratory 实验室信息
     * @return 统一返回结果
     */
    @PutMapping("/laboratories/{labId}")
    public Result update(@PathVariable String labId, @RequestBody Laboratory laboratory) {
        // 校验路径参数
        if (labId == null || labId.trim().isEmpty()) {
            return new Result(false, "请必填项", null);
        }
        
        // 校验必填项
        if (laboratory.getLabName() == null || laboratory.getLabName().trim().isEmpty()) {
            return new Result(false, "请必填项", null);
        }
        
        try {
            laboratory.setLabId(labId);
            int rows = laboratoryService.updateByLabId(laboratory);
            if (rows > 0) {
                return new Result(true, "修改成功", rows);
            } else {
                return new Result(false, "修改失败", null);
            }
        } catch (Exception e) {
            return new Result(false, "修改失败", null);
        }
    }
    
    /**
     * 根据房间号删除实验室
     * @param labId 房间号
     * @return 统一返回结果
     */
    @DeleteMapping("/laboratories/{labId}")
    public Result delete(@PathVariable String labId) {
        // 校验房间号
        if (labId == null || labId.trim().isEmpty()) {
            return new Result(false, "请传递房间号", null);
        }
        
        try {
            int rows = laboratoryService.deleteByLabId(labId.trim());
            if (rows > 0) {
                return new Result(true, "删除成功", rows);
            } else {
                return new Result(false, "删除失败", null);
            }
        } catch (Exception e) {
            return new Result(false, "删除失败", null);
        }
    }
}
