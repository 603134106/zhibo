package com.live.zbproject.douyu.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 庄科炜
 * @className ShortName
 * @description
 * @create 2021/6/25 11:25
 **/
@Data
public class ShortName implements Serializable {

    private static final long serialVersionUID = 1587078784560509933L;
    private String cate_id;
    private String cate_name;
    private String short_name;
    private String push_ios;
    private String push_show;
    private String push_vertical_screen;
    private String push_nearby;
}
