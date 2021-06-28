package com.live.zbproject.douyu.service;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.live.zbproject.common.utils.JSONUtil;
import com.live.zbproject.douyu.dao.DouyuMapper;
import com.live.zbproject.douyu.entity.DyUserRoom;
import com.live.zbproject.douyu.pojo.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
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
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 庄科炜
 * @className DouyuServiceImpl
 * @description TODO
 * @create 2021/6/25 10:52
 **/
@Service
public class DouyuServiceImpl implements DouyuService{
    @Autowired private DouyuMapper douyuMapper;
    @Autowired private SqlSessionFactory sqlSessionFactory;
    private static PoolingHttpClientConnectionManager cm = null;

    private static RequestConfig defaultRequestConfig = RequestConfig.custom()
            .setSocketTimeout(5000)
            .setConnectTimeout(5000)
            .setConnectionRequestTimeout(5000)
            .build();

    @PostConstruct
    public void init() {
        cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(1000);
        cm.setDefaultMaxPerRoute(1000);
    }

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
                if (retryTimes > 2) {
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
        CloseableHttpClient httpClient = HttpClients
                .custom()
                .setRetryHandler(handler)
                .setDefaultRequestConfig(defaultRequestConfig)
                .setConnectionManager(cm)
                .build();
        return httpClient;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<MixList> mixList(String tag_id) throws Exception{
        List<MixList> result = new ArrayList<>();
        CloseableHttpClient httpClient = this.getHttpClient();
        int page = 1;
        for (int i = 0; i < page; i++) {
            String url = "https://www.douyu.com/gapi/rkc/directory/mixList/2_"+tag_id+"/"+(i+1);
            HttpGet post = new HttpGet(
                    url);
            CloseableHttpResponse response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            InputStream inputStream = entity.getContent();
            String content = JSONUtil.convertStreamToString(inputStream);
            WrapMixList wrapMixList = JSONUtil.parseObject(content, WrapMixList.class);
            if(wrapMixList.getData().getPgcnt()!=null){
                page = wrapMixList.getData().getPgcnt();
            }
            result.add(wrapMixList.getData());
        }
        //httpClient.close();
        List<DyUserRoom> dyUserRoomList = new ArrayList<>();
        //删除已有工会的信息条
        for ( MixList res: result) {
            for (RL rl: res.getRl()) {
                String url = "https://www.douyu.com/ztCache/club/getanchorclubstatus?roomid="+rl.getRid();
                HttpGet httpGet = new HttpGet();
                CloseableHttpResponse response = httpClient.execute(httpGet);
                HttpEntity entity = response.getEntity();
                if(entity==null)continue;
                InputStream inputStream = entity.getContent();
                String content = JSONUtil.convertStreamToString(inputStream);
                WrapClubStatus wrapClubStatus = JSONUtil.parseObject(content, WrapClubStatus.class);
                ClubStatus data = wrapClubStatus.getData();
                //删除uid重复的(一般不会删除成功，用来兜底)
                douyuMapper.deleteByRid(rl.getRid());
                //如果 data为空就说明没加工会 信息需要入库
                if(data==null){
                    dyUserRoomList.add(
                            DyUserRoom.builder()
                            .rid(rl.getRid())
                            .uid(rl.getUid())
                            .nn(rl.getNn())
                            .gender(0)
                            .hot(rl.getOl())
                            .c2url(rl.getC2url())
                            .c2name(rl.getC2name())
                            .createAt(new Date())
                            .updateAt(new Date())
                            .build()
                    );
                }
            }
            //批量插入
            SqlSession sqlSession = sqlSessionFactory.openSession();
            DouyuMapper mapper = sqlSession.getMapper(DouyuMapper.class);
            for ( DyUserRoom dyUserRoom: dyUserRoomList) {
                mapper.insert(dyUserRoom);
            }
            sqlSession.commit();
            sqlSession.clearCache();
            sqlSession.flushStatements();
        }
        return result;

    }
}
