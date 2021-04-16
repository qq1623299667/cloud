package com.demo.teacher.service.impl;

import com.demo.teacher.entity.Teacher;
import com.demo.teacher.spi.TeacherService;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 石佳
 * @since 2020/05/09/18:03
 */
public class TeacherServiceImpl implements TeacherService {
    @Override
    public Teacher findById(Long id) {
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setAge(28);
        teacher.setName("马化腾");
        return teacher;
    }
}
