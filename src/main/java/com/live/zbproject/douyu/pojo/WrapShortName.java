package com.live.zbproject.douyu.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author 庄科炜
 * @className WrapShortName
 * @description TODO
 * @create 2021/6/25 13:47
 **/
@Data
public class WrapShortName {
    private Integer error;
    private List<ShortName> data;
}
