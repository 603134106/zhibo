package com.live.zbproject.douyu.service;

import com.live.zbproject.common.entity.TaskList;
import com.live.zbproject.common.vo.DouyuReqParams;
import com.live.zbproject.common.vo.DouyuTaskDetailVo;
import com.live.zbproject.douyu.entity.DyUserRoom;
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

    void douyuTaskSchedule()throws Exception;

    void initTask(DouyuReqParams douyuReqParams);

    void deleteTask(DouyuReqParams douyuReqParams);

    void douyuOneTaskSchedule(String zone1_id,String zone2_id)throws Exception;

    DouyuTaskDetailVo getTaskDetail(DouyuReqParams douyuReqParams);

    List<TaskList> getTaskList();
}
