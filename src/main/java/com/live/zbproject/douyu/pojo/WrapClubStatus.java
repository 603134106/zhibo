package com.live.zbproject.douyu.pojo;

import lombok.Data;

/**
 * @author 庄科炜
 * @className WrapClubStatus
 * @description TODO
 * @create 2021/6/28 15:33
 **/
@Data
public class WrapClubStatus {
    private Integer code;
    private String msg;
    private ClubStatus data;
}
