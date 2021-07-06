package com.live.zbproject.douyu.controller;

import com.live.zbproject.common.entity.TaskList;
import com.live.zbproject.common.vo.DouyuReqParams;
import com.live.zbproject.common.vo.DouyuTaskDetailVo;
import com.live.zbproject.common.vo.DouyuZoneVo;
import com.live.zbproject.douyu.entity.DyUserRoom;
import com.live.zbproject.douyu.pojo.ColumnDetail;
import com.live.zbproject.douyu.pojo.MixList;
import com.live.zbproject.douyu.pojo.ShortName;
import com.live.zbproject.douyu.service.DouyuService;
import com.live.zbproject.response.Response;
import javafx.concurrent.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author 庄科炜
 * @className DouyuController
 * @description 控制层
 * @create 2021/6/25 10:49
 **/
@Slf4j
@RestController
@RequestMapping("/douyu")
@CrossOrigin
public class DouyuController {
    @Autowired private DouyuService douyuService;

    @PostMapping("/getZone1")
    public Response getShortName(){
        try{
            List<ShortName> result = douyuService.getShortName("http://capi.douyucdn.cn/api/v1/getColumnList");
            List<DouyuZoneVo> data = new ArrayList<>();
            result.stream().forEach(p->{
                data.add(DouyuZoneVo.builder()
                        .id(p.getCate_id())
                        .name(p.getCate_name())
                        .build());
            });
            return Response.success(data,"success");
        }catch (Exception e){
            log.error(e.getMessage());
            return Response.failure(null,"fail");
        }
    }

    @PostMapping("/getZone2")
    public Response getColumnDetail(){
        try{
            List<ColumnDetail> result = douyuService.getColumnDetail();
            List<DouyuZoneVo> data = new ArrayList<>();
            result.stream().forEach(p->{
                data.add(DouyuZoneVo.builder()
                        .id(p.getTag_id())
                        .name(p.getTag_name())
                        .build());
            });
            return Response.success(data,"success");
        }catch (Exception e){
            log.error(e.getMessage());
            return Response.failure(null,"fail");
        }
    }

    @PostMapping("getTaskList")
    public Response getTaskList(){
        try {
            List<TaskList> result = douyuService.getTaskList();
            return Response.success(result,"success");
        } catch (Exception e) {
            log.error(e.getMessage());
            return Response.failure(null,"fail");
        }
    }

    @PostMapping("/addTask")
    public Response addTask(@RequestBody DouyuReqParams douyuReqParams){
        try {
            douyuService.initTask(douyuReqParams);
            //提醒开启任务
            douyuService.douyuOneTaskSchedule(douyuReqParams.getZone1_id(),douyuReqParams.getZone2_id());
            return Response.success(null,"success");
        } catch (Exception e) {
            log.error(e.getMessage());
            return Response.failure(null,"fail");
        }
    }

    @PostMapping("/deleteTask")
    public Response deleteTask(@RequestBody DouyuReqParams douyuReqParams){
        try {
            douyuService.deleteTask(douyuReqParams);
            return Response.success(null,"success");
        } catch (Exception e) {
            log.error(e.getMessage());
            return Response.failure(null,"fail");
        }
    }

    @Scheduled(cron = "0 0 11,23 * * ?")
    public void douyuTaskSchedule(){
        try {
            douyuService.douyuTaskSchedule();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @PostMapping("/getTaskDetail")
    public Response getTaskDetail(@RequestBody DouyuReqParams douyuReqParams){
        try {
            DouyuTaskDetailVo douyuTaskDetailVo = douyuService.getTaskDetail(douyuReqParams);
            return Response.success(douyuTaskDetailVo,"success");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.failure(null,"fail");
        }
    }
}
