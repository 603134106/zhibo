package com.live.zbproject.douyu.pojo;

import lombok.Data;

/**
 * @author 庄科炜
 * @className Video
 * @description video
 * @create 2021/6/28 11:31
 **/
@Data
public class Video {
    private Integer vid;
    private String hashId;
    private String title;
    private String cover;
    private String verCover;
    private Integer ct2id;
    private String ct2name;
    private Integer isVertical;
    private Integer videoDuration;
    private String nickname;
    private String avatar;
    private String upId;
    private Integer viewNum;
    private String rankType;
    private String officialCer;
}
