package org.qvtu.laboratoryweb.aop;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.qvtu.laboratoryweb.entity.Result;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoginAspect {

    private final StringRedisTemplate redisTemplate;

    public LoginAspect(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 定义切点：匹配 controller 包下所有的类及其方法
     * 排除 LoginController 里的 login 方法
     */
    @Pointcut("execution(* org.qvtu.laboratoryweb.controller.*.*(..)) && !execution(* org.qvtu.laboratoryweb.controller.LoginController.login(..))")
    public void loginPointcut() {}

    @Around("loginPointcut()")
    public Object verifyLogin(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return joinPoint.proceed();
        }
        HttpServletRequest request = attributes.getRequest();

        // 从请求头获取 token
        String token = request.getHeader("token");
        if (token == null || token.trim().isEmpty()) {
            // 如果头里没有，尝试从参数里获取
            token = request.getParameter("token");
        }

        // 验证 Token 是否存在于 Redis 中
        if (token == null || Boolean.FALSE.equals(redisTemplate.hasKey(token))) {
            return new Result(false, "未授权，请先登录", null);
        }

        // 验证通过，继续执行业务逻辑
        return joinPoint.proceed();
    }
}
