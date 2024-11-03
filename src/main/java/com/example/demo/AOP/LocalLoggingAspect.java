package com.example.demo.AOP;

import com.example.demo.models.Course;
import com.example.demo.models.Student;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
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

    @AfterReturning(
            pointcut = "execution(public Object com.example.demo.DAO.BaseRepository.findById(int)) && args(studentId)",
            returning = "student",
            argNames = "joinPoint,studentId,student")
    public void afterGetStudent(JoinPoint joinPoint, int studentId, Student student) {
        System.out.println("After getting studentId " + studentId);

        var firstName = student.getFirstName();
        student.setFirstName(firstName + " - modified during AOP Proxy.");

    }

    @Pointcut("execution(public com.example.demo.models.Student com.example.demo.DAO.StudentRepository.findByEmail(String)) && args(email)")
    private void findStudentByEmail(String email) {}

    @AfterThrowing(
            pointcut = "findStudentByEmail(email)",
            throwing = "exception",
            argNames = "joinPoint,email,exception"
    )
    public void afterThrowing(JoinPoint joinPoint, String email, Throwable exception) {
        System.out.println("After trowing the exception: " + exception.getMessage());
    }

    @After(
            value = "findStudentByEmail(email)",
            argNames = "joinPoint,email")
    public void after(JoinPoint joinPoint, String email) {
        System.out.println("@After advice completed.");
    }

    @Around("execution(public String com.example.demo.services.FortuneService.getFortune(String))")
    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        var begin = System.currentTimeMillis();
        var result = proceedingJoinPoint.proceed();
        var end = System.currentTimeMillis();

        var time = end - begin;
        System.out.println("The process completed in " + time + " milliseconds.");

        return result;
    }
}
