package com.demo.kafka.beans;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 石佳
 * @since 2020/06/11/19:51
 */
@Data
public class Message implements Serializable {
    private static final long serialVersionUID = -7460473461402269424L;
    private Long id;    //id
    private String msg; //消息
    private Date sendTime;  //时间戳
}
