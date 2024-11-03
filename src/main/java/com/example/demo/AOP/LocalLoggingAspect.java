package com.example.demo.AOP;

import com.example.demo.models.Course;
import com.example.demo.models.Student;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
public class LocalLoggingAspect {
    @Pointcut("execution(public void com.example.demo.DAO.BaseRepository.add(Object))")
    private void forCourseRepositoryAdd(){}

    @Before("execution(public void add(*)) && args(course)")
    public void beforeAddCourse(JoinPoint joinPoint, Course course) {
        System.out.println("Executing @Before advice on CourseRepository.");
    }

//    @Before("forCourseRepositoryAdd()")
//    public void beforeAddCourse2(JoinPoint joinPoint) {
//        System.out.println("Executing @Before 2 advice on CourseRepository.");
//    }

//    @Before("execution(public void com.example.demo.DAO.BaseRepository.add(*))")
//    @Before("execution(public void add(Object))")
//    @Before("execution(public void add(*))")
//    @Before("execution(public void com.example.demo.DAO.BaseRepository.add(*)) && args(student)")
    @Before(AopExpression.addNewStudent)
    public void beforeAddStudent(JoinPoint joinPoint, Student student) {
        System.out.println("Before during LocalLoggingAspect - StudentRepository");

        var methodSignature = joinPoint.getSignature();
        System.out.println(methodSignature.toString());

        var args = joinPoint.getArgs();

        for (var arg : args) {
            System.out.println(arg);
        }
    }
}
