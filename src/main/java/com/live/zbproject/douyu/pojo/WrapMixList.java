package com.live.zbproject.douyu.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 庄科炜
 * @className WrapMixList
 * @description TODO
 * @create 2021/6/28 10:44
 **/
@Data
public class WrapMixList implements Serializable {
    private static final long serialVersionUID = 1322053904000778027L;
    private String code;
    private String msg;
    private MixList data;
}
