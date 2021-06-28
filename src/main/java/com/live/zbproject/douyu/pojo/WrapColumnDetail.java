package com.live.zbproject.douyu.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author 庄科炜
 * @className WarpColumnDetail
 * @description
 * @create 2021/6/25 16:01
 **/
@Data
public class WrapColumnDetail {
    private Integer error;
    private List<ColumnDetail> data;
}
