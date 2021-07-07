package com.live.zbproject.douyu.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("week_barrage_num")
    private Integer weekBarrageNum;
    @JsonProperty("week_gift_price")
    private Integer weekGiftPrice;
    @JsonProperty("create_at")
    private Date createAt;
    @JsonProperty("update_at")
    private Date updateAt;
    private String c2url;
    private String c2name;
    @JsonProperty("zone1_id")
    private String zone1Id;
    @JsonProperty("zone2_id")
    private String zone2Id;
    @JsonProperty("zone1_name")
    private String zone1Name;
    @JsonProperty("zone2_name")
    private String zone2Name;
    private String update_time;
    private Integer avgGiftWorth;
    private Integer avgInteractNum;
}
