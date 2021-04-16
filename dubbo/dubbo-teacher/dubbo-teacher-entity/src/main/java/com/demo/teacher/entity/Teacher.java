package com.demo.teacher.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 石佳
 * @since 2020/05/09/17:30
 */
@Data
public class Teacher implements Serializable {

    private static final long serialVersionUID = -524578376410974321L;
    private Long id;
    private String name;
    private Integer age;
}
