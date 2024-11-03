package com.example.demo.AOP;

import com.example.demo.models.Student;
import org.aspectj.lang.annotation.Pointcut;

public class AopExpression {
    public static final String addNewStudent = "execution(public void com.example.demo.DAO.BaseRepository.add(*)) && args(student)";

    @Pointcut("execution(public void com.example.demo.DAO.BaseRepository.add(*)) && args(student)")
    public void addNewStudent(Student student){}
}
