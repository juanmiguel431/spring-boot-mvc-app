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

//    @Before(AopExpression.addNewStudent)
    @Before("com.example.demo.AOP.AopExpression.addNewStudent(student)")
    public void beforeExecution(JoinPoint joinPoint, Student student) {
        System.out.println("Before during CloudLoggingAspect - StudentRepository");
    }
}
