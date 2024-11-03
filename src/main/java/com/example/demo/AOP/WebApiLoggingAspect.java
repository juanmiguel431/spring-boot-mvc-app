package com.example.demo.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class WebApiLoggingAspect {

    private final Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.example.demo.services.*.*(..))")
    private void forServicePackage() {}

    @Pointcut("execution(* com.example.demo.controllers.*.*(..))")
    private void forControllerPackage() {}

    @Pointcut("execution(* com.example.demo.DAO.*.*(..))")
    private void forDaoPackage() {}

    @Pointcut("forServicePackage() || forControllerPackage() || forDaoPackage()")
    private void forAppFlow() {}

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {
        var method = joinPoint.getSignature().toShortString();
        logger.info("logger =====> in @Before: calling method " + method);
    }
}
