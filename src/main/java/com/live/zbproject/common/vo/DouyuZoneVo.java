package com.live.zbproject.common.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 庄科炜
 * @className DouyuZoneVo
 * @description
 * @create 2021/7/2 9:30
 **/
@Data
@Builder
public class DouyuZoneVo implements Serializable {
    private static final long serialVersionUID = -2531321766468844230L;
    private String id;
    private String name;
}
