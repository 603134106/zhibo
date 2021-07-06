package com.live.zbproject.douyu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author 庄科炜
 * @className RL
 * @description rl
 * @create 2021/6/28 10:29
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RL implements Serializable {
    private static final long serialVersionUID = -2874718341920816838L;
    private Integer type;
    private Video video;
    private Integer rid;
    private String rn;
    private Integer uid;
    private String nn;
    private Integer cid1;
    private Integer cid2;
    private Integer cid3;
    private Integer iv;
    private String av;
    private Integer ol;
    private String c2url;
    private String c2name;
    private Icdata icdata;
    private Integer dot;
    private Integer subrt;
    private Integer topid;
    private Integer oaid;
    private Integer bid;
    private Integer gldid;
    private String rs1;
    private String rs16;
    private List<UTAG> utag;
    private Integer rpos;
    private Integer rgrpt;
    private String rkic;
    private Integer rt;
    private Integer ot;
    private Integer clis;
    private Integer chanid;
    private List<List<ICV1>> icv1;
    private Integer ioa;
    private String od;
    private Integer isShowUp;
    private AuthInfo authInfo;
    private String url;
}
