package com.example.demo.AOP;

import com.example.demo.models.Course;
import com.example.demo.models.Student;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(public void add(*)) && args(course)")
    public void beforeAddCourse(JoinPoint joinPoint, Course course) {
        System.out.println("Executing @Before advice on CourseRepository.");
    }

//    @Before("execution(public void com.example.demo.DAO.BaseRepository.add(*))")
//    @Before("execution(public void add(Object))")
//    @Before("execution(public void add(*))")
    @Before("execution(public void add(*)) && args(student)")
    public void beforeAddStudent(JoinPoint joinPoint, Student student) {
        System.out.println("Executing @Before advice on StudentRepository.");
    }
}
