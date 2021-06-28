package com.live.zbproject.douyu.controller;

import com.live.zbproject.douyu.pojo.ColumnDetail;
import com.live.zbproject.douyu.pojo.MixList;
import com.live.zbproject.douyu.pojo.ShortName;
import com.live.zbproject.douyu.service.DouyuService;
import com.live.zbproject.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 庄科炜
 * @className DouyuController
 * @description 控制层
 * @create 2021/6/25 10:49
 **/
@Slf4j
@RestController
@RequestMapping("/api/douyu")
public class DouyuController {
    @Autowired private DouyuService douyuService;

    @PostMapping("/getShortName")
    public Response getShortName(){
        try{
            List<ShortName> result = douyuService.getShortName("http://capi.douyucdn.cn/api/v1/getColumnList");
            return Response.success(result,"查询成功");
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.failure(null,"查询失败");
        }
    }

    @PostMapping("/getColumnDetail")
    public Response getColumnDetail(){
        try{
            List<ColumnDetail> result = douyuService.getColumnDetail();
            return Response.success(result,"查询成功");
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.failure(null,"查询失败");
        }
    }

    @PostMapping("mixList")
    public Response mixList(String tag_id){
        try{
            List<MixList> result = douyuService.mixList(tag_id);
            return Response.success(result,"查询成功");
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return Response.failure(null,"查询失败");
        }
    }
}
