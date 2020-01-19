package com.krry.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * date: 2019/12/31 15:11 <br>
 * author: Administrator <br>
 */
@Aspect
@Component
public class LogAspect {
    private final Logger logger = LoggerFactory.getLogger("Controller日志");

    @Around("execution(* com.krry.controller..*.*(..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;
        HttpServletRequest request = sra.getRequest();
        String targetName = pjp.getTarget().getClass().getSimpleName();
        // 获取请求地址
        String requestPath = request.getRequestURI();
        // 执行完方法的返回值：调用proceed()方法，就会触发切入点方法执行
        Map<String, Object> outputParamMap = new HashMap<String, Object>();
        Object result = pjp.proceed();// result的值就是被拦截方法的返回值
        outputParamMap.put("result", result);
        printOptLog(targetName,requestPath,outputParamMap);
        return result;
    }

    /**
     * 输出日志
     * @param targetName
     * @param requestPath
     * @param outputParamMap
     */
    private void printOptLog(String targetName,String requestPath,Map<String,Object> outputParamMap){
        logger.info("类名:"+targetName+" => url: "+requestPath+" => result: 相应List数据");
    }
}
