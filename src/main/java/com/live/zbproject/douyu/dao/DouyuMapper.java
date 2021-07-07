package com.live.zbproject.douyu.dao;

import com.live.zbproject.common.entity.TaskList;
import com.live.zbproject.common.entity.ToubangInfo;
import com.live.zbproject.common.vo.DouyuReqParams;
import com.live.zbproject.douyu.entity.DyUserRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DouyuMapper {

    int deleteByRid(@Param("rid") Integer rid);

    int insert(DyUserRoom dyUserRoom);

    int initTask(TaskList build);

    void deleteTask(DouyuReqParams douyuReqParams);

    List<TaskList> findTaskList(@Param("category")String category);

    DyUserRoom findOneByRid(@Param("rid") Integer rid);

    List<DyUserRoom> getTaskDetail(DouyuReqParams douyuReqParams);

    List<TaskList> getTaskList();

    int getDyCountByZoneId(@Param("zone1Id") String zone1Id,@Param("zone2Id")String zone2Id);

    void deleteBatch(List<DyUserRoom> dyUserRoomList);

    int saveOrUpdateTouBang(List<ToubangInfo> toubangInfoList);
}
