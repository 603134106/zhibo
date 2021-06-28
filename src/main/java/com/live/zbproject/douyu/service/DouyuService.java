package com.live.zbproject.douyu.service;

import com.live.zbproject.douyu.pojo.ColumnDetail;
import com.live.zbproject.douyu.pojo.MixList;
import com.live.zbproject.douyu.pojo.ShortName;

import java.util.List;

/**
 * @author 庄科炜
 * @className DouyuService
 * @description
 * @create 2021/6/25 10:52
 **/
public interface DouyuService {

    List<ShortName> getShortName(String url) throws Exception;

    List<ColumnDetail> getColumnDetail ()throws Exception;

    List<MixList> mixList(String tag_Id)throws Exception;
}
