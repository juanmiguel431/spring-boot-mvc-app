package com.example.demo.AOP;

import com.example.demo.models.Student;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(2)
@Component
public class CloudLoggingAspect {

    @Before("execution(public void com.example.demo.DAO.BaseRepository.add(*)) && args(student))")
    public void beforeExecution(JoinPoint joinPoint, Student student) {
        System.out.println("Before during CloudLoggingAspect - StudentRepository");
    }
}
