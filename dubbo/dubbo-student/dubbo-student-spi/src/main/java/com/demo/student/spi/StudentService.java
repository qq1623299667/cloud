package com.demo.student.spi;


import com.demo.student.entity.Student;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 石佳
 * @since 2020/05/09/16:43
 */
public interface StudentService {
    Student findById(Long id);
}
