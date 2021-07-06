package com.live.zbproject.common.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 庄科炜
 * @className TaskList
 * @description
 * @create 2021/7/2 10:31
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskList implements Serializable {
    private Long id;
    @JsonProperty("zone1_id")
    private String zone1Id;
    @JsonProperty("zone2_id")
    private String zone2Id;
    private Date createAt;
    private Date updateAt;
    private Integer valid;
    private String category;
    @JsonProperty("zone1")
    private String zone1Name;
    @JsonProperty("zone2")
    private String zone2Name;
    private String update_time;
    private Integer anchor_num;
}
