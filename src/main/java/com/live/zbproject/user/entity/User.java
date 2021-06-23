package com.live.zbproject.user.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 庄科炜
 * @className User
 * @description user类
 * @create 2021/6/23 15:41
 **/
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -6002324232809961827L;
    private Long id;
    private String userName;
    private String password;
    private Integer age;
    private Integer lock;
    private Integer onlineStatus;
}
