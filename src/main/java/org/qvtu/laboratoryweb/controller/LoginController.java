package org.qvtu.laboratoryweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.qvtu.laboratoryweb.entity.Admin;
import org.qvtu.laboratoryweb.entity.LoginRequest;
import org.qvtu.laboratoryweb.entity.Result;
import org.qvtu.laboratoryweb.service.AdminService;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
public class LoginController {
    
    private final AdminService adminService;
    private final StringRedisTemplate redisTemplate;

    public LoginController(AdminService adminService, StringRedisTemplate redisTemplate) {
        this.adminService = adminService;
        this.redisTemplate = redisTemplate;
    }
    
    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest loginRequest) {
        // 校验账号
        if (loginRequest.getAccount() == null || loginRequest.getAccount().trim().isEmpty()) {
            return new Result(false, "请输入账号", null);
        }
        
        // 校验密码
        if (loginRequest.getPassword() == null || loginRequest.getPassword().trim().isEmpty()) {
            return new Result(false, "请输入密码", null);
        }
        
        String account = loginRequest.getAccount().trim();
        String password = loginRequest.getPassword().trim();
        
        // 查询管理员
        Admin admin = adminService.findByAccount(account);
        if (admin == null) {
            return new Result(false, "登录失败，账号不存在", null);
        }
        
        // 验证密码
        if (!password.equals(admin.getPassword())) {
            return new Result(false, "登录失败，密码错误", null);
        }
        
        // 生成 UUID 作为登录凭证
        String token = UUID.randomUUID().toString();
        
        // 将用户信息存储到 Redis，设置 30 分钟过期时间
        redisTemplate.opsForValue().set(token, account, 30, TimeUnit.MINUTES);

        return new Result(true, "登录成功", token);
    }
}
