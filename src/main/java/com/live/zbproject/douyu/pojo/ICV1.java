package com.live.zbproject.douyu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 庄科炜
 * @className ICV1
 * @description icv1
 * @create 2021/6/28 10:30
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ICV1 implements Serializable {
    private static final long serialVersionUID = -6744008008065606574L;
    private String id;
    private String url;
    private String score;
    private String w;
    private String h;
}
