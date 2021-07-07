package com.live.zbproject.douyu.service;

import com.live.zbproject.common.entity.TaskList;
import com.live.zbproject.common.entity.ToubangInfo;
import com.live.zbproject.common.utils.JSONUtil;
import com.live.zbproject.common.vo.DouyuReqParams;
import com.live.zbproject.common.vo.DouyuTaskDetailVo;
import com.live.zbproject.douyu.dao.DouyuMapper;
import com.live.zbproject.douyu.entity.DyUserRoom;
import com.live.zbproject.douyu.pojo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 庄科炜
 * @className DouyuServiceImpl
 * @description TODO
 * @create 2021/6/25 10:52
 **/
@Slf4j
@Service
public class DouyuServiceImpl implements DouyuService{
    @Autowired private DouyuMapper douyuMapper;
    @Autowired private SqlSessionFactory sqlSessionFactory;
    private static PoolingHttpClientConnectionManager cm = null;

    private static RequestConfig defaultRequestConfig = RequestConfig.custom()
            .setSocketTimeout(5000)
            .setConnectTimeout(5000)
            .setConnectionRequestTimeout(10000)
            .build();

    @Override
    public List<ShortName> getShortName(String url) throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        CloseableHttpResponse response = httpClient.execute(post);
        HttpEntity entity = response.getEntity();
        InputStream inputStream = entity.getContent();
        WrapShortName wrapShortName = null;
        if(entity!=null){
            String content = JSONUtil.convertStreamToString(inputStream);
            wrapShortName = JSONUtil.parseObject(content, WrapShortName.class);
        }
        httpClient.close();
        response.close();
        return wrapShortName == null ? null : wrapShortName.getData();
    }

    @Override
    public List<ColumnDetail> getColumnDetail() throws Exception{
        List<ShortName> shortNames = this.getShortName("http://capi.douyucdn.cn/api/v1/getColumnList");
        CloseableHttpClient httpClient = getHttpClient();
        List<ColumnDetail> result = new ArrayList<>();
        for (ShortName shortName : shortNames) {
            HttpPost post = new HttpPost(
                    "http://capi.douyucdn.cn/api/v1/getColumnDetail?shortName="+shortName.getShort_name());
            CloseableHttpResponse response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            InputStream inputStream = entity.getContent();
            String content = JSONUtil.convertStreamToString(inputStream);
            WrapColumnDetail wrapColumnDetail = JSONUtil.parseObject(content, WrapColumnDetail.class);
            List<ColumnDetail> data = wrapColumnDetail.getData()==null?null: wrapColumnDetail.getData();
            if(data!=null && data.size()>0)result.addAll(data);
        }
        //httpClient.close();
        return result;
    }

    private CloseableHttpClient getHttpClient() {
        HttpRequestRetryHandler handler = new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException arg0, int retryTimes, HttpContext arg2) {
                if (retryTimes > 3) {
                    return false;
                }
                if (arg0 instanceof UnknownHostException || arg0 instanceof ConnectTimeoutException
                        || !(arg0 instanceof SSLException) || arg0 instanceof NoHttpResponseException) {
                    return true;
                }

                HttpClientContext clientContext = HttpClientContext.adapt(arg2);
                HttpRequest request = clientContext.getRequest();
                boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
                if (idempotent) {
                    // 如果请求被认为是幂等的，那么就重试。即重复执行不影响程序其他效果的
                    return true;
                }
                return false;
            }
        };
        cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(20);

        CloseableHttpClient httpClient = HttpClients
                .custom()
                .setRetryHandler(handler)
                .setDefaultRequestConfig(defaultRequestConfig)
                .setConnectionManager(cm)
                .setMaxConnTotal(200)
                .build();
        return httpClient;
    }

    @Override
    public void douyuTaskSchedule()throws Exception{
        List<TaskList> taskLists = douyuMapper.findTaskList("douyu");
        for (TaskList taskList : taskLists) {
            taskSchedule(taskList.getZone1Id(),taskList.getZone2Id());    //分区2id
        }
    }

    @Async
    public void taskSchedule2(List<Integer> ridList) throws Exception{
        CloseableHttpClient httpClient = getHttpClient();
        List<ToubangInfo> toubangInfoList = new ArrayList<>();
        for (Integer rid : ridList) {
            //1为斗鱼
            log.info("task2 RID为:"+rid);
            String url = "http://www.toubang.tv/anchor/ajax/getinfo?pt=1&rid="+rid+"&dt=1&date=0";
            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader("Referer","http://www.toubang.tv/anchor/1_"+rid+".html");
            CloseableHttpResponse execute = httpClient.execute(httpGet);
            HttpEntity entity = execute.getEntity();
            InputStream inputStream = entity.getContent();
            String content = JSONUtil.convertStreamToString(inputStream);
            ToubangInfo toubangInfo = JSONUtil.parseObject(content, ToubangInfo.class);
            if(toubangInfo==null)continue;
            toubangInfo.setRid(rid);
            toubangInfoList.add(toubangInfo);
        }
        douyuMapper.saveOrUpdateTouBang(toubangInfoList);
    }

    //用来补全一阶段的信息 以删代更的多
    @Transactional(rollbackFor = Exception.class)
    @Async
    public void taskSchedule(String zone1Id,String zone2Id) throws Exception{
        List<MixList> result = new ArrayList<>();
        CloseableHttpClient httpClient = getHttpClient();
        int page = 1;
        for (int i = 0; i < page; i++) {
            String url = "https://www.douyu.com/gapi/rkc/directory/mixList/2_"+zone2Id+"/"+(i+1);
            HttpGet post = new HttpGet(
                    url);
            CloseableHttpResponse response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            InputStream inputStream = entity.getContent();
            String content = JSONUtil.convertStreamToString(inputStream);
            MixList mixList = JSONUtil.parseObject2(content, MixList.class);
            if(mixList!=null){
                page = mixList.getPgcnt();
            }
            post.releaseConnection();
            response.close();
            result.add(mixList);
        }
        //httpClient.close();
        List<DyUserRoom> dyUserRoomList = new ArrayList<>();
        int count = 0;
        List<Integer> ridList = new ArrayList<>();
        //删除已有工会的信息条
        for ( MixList res: result) {
            for (RL rl: res.getRl()) {
                log.info("采集的rid:"+rl.getRid()+"采集总数"+(count++));
                String url = "https://www.douyu.com/ztCache/club/getanchorclubstatus?roomid="+rl.getRid();
                HttpGet httpGet = new HttpGet(url);
                CloseableHttpResponse response = null;
                response = httpClient.execute(httpGet);
                HttpEntity entity = response.getEntity();
                if(entity==null)continue;
                InputStream inputStream = entity.getContent();
                String content = JSONUtil.convertStreamToString(inputStream);
                ClubStatus data = JSONUtil.parseObject2(content, ClubStatus.class);
                Integer rid =null;
                if(rl.getVideo()==null){
                    rid = rl.getRid();
                }else{
                    rid = rl.getVideo().getVid();
                }
                //如果 data为空就说明没加工会 信息需要入库
                if(data==null){
                    ridList.add(rl.getRid());
                    if(rl.getVideo()==null){
                        dyUserRoomList.add(
                                DyUserRoom.builder()
                                        .rid(rl.getRid())
                                        .uid(rl.getUid())
                                        .nn(rl.getNn())
                                        .gender(0)
                                        .hot(rl.getOl())
                                        .c2url(rl.getC2url())
                                        .c2name(rl.getC2name())
                                        .zone1Id(zone1Id)
                                        .zone2Id(zone2Id)
                                        .zone2Name(rl.getC2name())
                                        .createAt(new Date())
                                        .updateAt(new Date())
                                        .build()
                        );
                    }else {
                        dyUserRoomList.add(
                                DyUserRoom.builder()
                                        .rid(rl.getVideo().getVid())
                                        .uid(rl.getUid())
                                        .nn(rl.getVideo().getNickname())
                                        .gender(0)
                                        .hot(rl.getVideo().getViewNum())
                                        .c2name(rl.getVideo().getCt2name())
                                        .zone2Name(rl.getC2name())
                                        .createAt(new Date())
                                        .updateAt(new Date())
                                        .zone1Id(zone1Id)
                                        .zone2Id(zone2Id)
                                        .build()
                        );
                    }
                    httpGet.releaseConnection();
                    log.info(rl.getRid()+"没有工会，数据为:"+data);
                }else{
                    log.info(rl.getRid()+"已有工会，数据为:"+data);
                }
            }
        }
        ridList = ridList.stream().filter(p->p!=null).collect(Collectors.toList());
        //第二个补全任务开跑
        taskSchedule2(ridList);
        log.info("继续往下走");
        //批量删除
        douyuMapper.deleteBatch(dyUserRoomList);
        //批量插入
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        DouyuMapper mapper = sqlSession.getMapper(DouyuMapper.class);
        for ( DyUserRoom dyUserRoom: dyUserRoomList) {
            mapper.insert(dyUserRoom);
        }
        sqlSession.commit();
        sqlSession.clearCache();
        sqlSession.flushStatements();
        sqlSession.close();
        //return result;
    }

    /**
     * 创建任务
     * @param douyuReqParams
     * @author 庄科炜
     * @date 2021/7/2 10:10
     * @return void
     */
    @Override
    public void initTask(DouyuReqParams douyuReqParams) {
        douyuMapper.initTask(TaskList.builder()
                .zone1Id(douyuReqParams.getZone1_id())
                .zone2Id(douyuReqParams.getZone2_id())
                .zone1Name(douyuReqParams.getZone1_name())
                .zone2Name(douyuReqParams.getZone2_name())
                .category(douyuReqParams.getPlatform())
                .updateAt(new Date())
                .createAt(new Date())
                .valid(0)
                .build());
    }

    @Override
    public void deleteTask(DouyuReqParams douyuReqParams) {
        douyuMapper.deleteTask(douyuReqParams);
    }

    @Async
    @Override
    public void douyuOneTaskSchedule(String zone1_id,String zone2_id) throws Exception{
        this.taskSchedule(zone1_id,zone2_id);
    }

    @Override
    public DouyuTaskDetailVo getTaskDetail(DouyuReqParams douyuReqParams) {
        List<DyUserRoom> taskDetail = douyuMapper.getTaskDetail(douyuReqParams);
        String zone2Name = "";
        if(taskDetail!=null && taskDetail.size()>0)zone2Name = taskDetail.get(0).getC2name();
        return DouyuTaskDetailVo.builder()
                .zone1_id(douyuReqParams.getZone1_id())
                .zone2_id(douyuReqParams.getZone2_id())
                .zone2(zone2Name)
                .anchor_num(taskDetail.size())
                .list(taskDetail)
                .build();
    }

    @Override
    public List<TaskList> getTaskList() {
        List<TaskList> taskLists = douyuMapper.getTaskList();
        for (TaskList taskList: taskLists) {
            int count = douyuMapper.getDyCountByZoneId(taskList.getZone1Id(), taskList.getZone2Id());
            taskList.setAnchor_num(count);
        }
        return taskLists;
    }
}
