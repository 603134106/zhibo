package com.live.zbproject.douyu.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author 庄科炜
 * @className DyUserRoom
 * @description
 * @create 2021/6/28 15:36
 **/
@Data
@Builder
public class DyUserRoom {
    private Long id;
    private Integer rid;//房id
    private String rn;//房名
    private Integer uid;//用户id
    private String nn;//昵称
    private Integer gender;//性别
    private String title;
    private Integer fans;
    private Integer hot;
    private String notice;
    private Integer weekBarrageNum;
    private Integer weekGiftPrice;
    private Date createAt;
    private Date updateAt;
    private String c2url;
    private String c2name;
}
