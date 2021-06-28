package com.live.zbproject.douyu.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 庄科炜
 * @className MixList
 * @description mixList
 * @create 2021/6/28 10:42
 **/
@Data
public class MixList implements Serializable {
    private static final long serialVersionUID = -1735641329925343055L;
    private CT ct;
    private List<RL> rl;
    private Integer pgcnt;
}
