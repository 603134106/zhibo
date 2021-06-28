package com.live.zbproject.douyu.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 庄科炜
 * @className ICV1
 * @description icv1
 * @create 2021/6/28 10:30
 **/
@Data
public class ICV1 implements Serializable {
    private static final long serialVersionUID = -6744008008065606574L;
    private String id;
    private String url;
    private String score;
    private String w;
    private String h;
}
