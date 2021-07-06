package com.live.zbproject.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 庄科炜
 * @className DouyuReqParams
 * @description
 * @create 2021/7/2 9:53
 **/
@Data
public class DouyuReqParams implements Serializable {
    private static final long serialVersionUID = 7164177908773743897L;
    private String platform;
    private String zone1_id;
    private String zone2_id;
    private String zone1_name;
    private String zone2_name;
}
