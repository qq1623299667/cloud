package com.demo.teacher.controller;

import com.demo.student.entity.Student;
import com.demo.student.spi.StudentService;
import com.demo.teacher.entity.Teacher;
import com.demo.teacher.spi.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 石佳
 * @since 2020/05/09/17:32
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @GetMapping("/student/id")
    public Student findStudentById(@RequestParam Long id){
        return studentService.findById(id);
    }
    @GetMapping("/id")
    public Teacher findById(@RequestParam Long id){
        return teacherService.findById(id);
    }

}
