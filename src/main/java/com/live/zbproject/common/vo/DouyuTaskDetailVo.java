package com.live.zbproject.common.vo;

import com.live.zbproject.douyu.entity.DyUserRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 庄科炜
 * @className DouyuTaskDetailVo
 * @description
 * @create 2021/7/2 16:29
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DouyuTaskDetailVo {
    private String zone1;
    private String zone1_id;
    private String zone2;
    private String zone2_id;
    private Integer anchor_num;
    List<DyUserRoom> list;
}
