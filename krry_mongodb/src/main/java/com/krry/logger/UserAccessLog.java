package com.krry.logger;

import com.krry.dao.IUserDao;
import com.krry.entity.User;
import com.krry.util.HttpServletRequestUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * date: 2020/1/2 13:07 <br>
 * author: Administrator <br>
 */
@Aspect
@Component
public class UserAccessLog {
    @Autowired
    IUserDao userDao;

    /**
     * 用户登录日志
     * @param joinPoint
     */
    @AfterReturning("execution(* com.krry.controller.KrryController.logined(..))")
    public void userLogin(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        HttpServletRequest request = (HttpServletRequest) args[0];
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setIp(HttpServletRequestUtil.getInstance().getIpAddr(request));
        User u = userDao.findByUsername(username);
        if (u == null){
            user.setResult("登陆失败");
        }else {
            user.setResult("登陆成功");
        }
        userDao.save(user);
    }

}
