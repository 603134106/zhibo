package com.live.zbproject.douyu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 庄科炜
 * @className WrapClubStatus
 * @description TODO
 * @create 2021/6/28 15:33
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WrapClubStatus implements Serializable {
    private static final long serialVersionUID = -7245532986206717866L;
    private Integer code;
    private String msg;
    private ClubStatus data;
}
