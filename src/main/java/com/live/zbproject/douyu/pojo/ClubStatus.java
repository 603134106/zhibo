package com.live.zbproject.douyu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 庄科炜
 * @className ClubStatus
 * @description clubstatus
 * @create 2021/6/28 15:31
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubStatus implements Serializable {
    private static final long serialVersionUID = -8685641745495327600L;
    private String orgmask;
    private String org_sname;
    private String room_label;
}
