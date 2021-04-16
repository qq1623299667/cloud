package com.demo.student.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 石佳
 * @since 2020/05/09/16:43
 */
@Data
public class Student implements Serializable {

    private static final long serialVersionUID = 8786907026588206567L;
    private Long id;
    private String name;
    private Integer age;
}
