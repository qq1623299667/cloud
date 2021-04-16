package com.demo.teacher.spi;

import com.demo.teacher.entity.Teacher;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 石佳
 * @since 2020/05/09/17:31
 */
public interface TeacherService {
    Teacher findById(Long id);
}
