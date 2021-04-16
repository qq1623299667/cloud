package com.demo.student.service.impl;

import com.demo.student.entity.Student;
import com.demo.student.spi.StudentService;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 石佳
 * @since 2020/05/09/16:46
 */
public class StudentServiceImpl implements StudentService {
    @Override
    public Student findById(Long id) {
        Student student = new Student();
        student.setAge(18);
        student.setId(1L);
        student.setName("马云");
        return student;
    }
}
