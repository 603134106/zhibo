package com.live.zbproject;



import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultHttpClientConnectionOperator;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author 庄科炜
 * @className Test01
 * @description TODO
 * @create 2021/6/23 13:28
 **/
public class Test01 {
  public static void main(String[] args) {
      String s = Long.toBinaryString(888888888888888888L);
    System.out.println(s);
//      CloseableHttpClient httpClient = HttpClients.createDefault();
//      HttpPost httpPost = new HttpPost("https://www.douyu.com/member/quiz/become_banker");
//      httpPost.setHeader("authority","www.douyu.com");
//      httpPost.setHeader("sec-ch-ua","' Not;A Brand';v='99', 'Microsoft Edge'; v='91','Chromium';v='91'");
//      httpPost.setHeader("sec-ch-ua-mobile","?0");
//      httpPost.setHeader("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36 Edg/91.0.864.54");
//      httpPost.setHeader("content-type","application/x-www-form-urlencoded");
//      httpPost.setHeader("accept","*/*");
//      httpPost.setHeader("origin","https://www.douyu.com");
//      httpPost.setHeader("sec-fetch-site","same-origin");
//      httpPost.setHeader("sec-fetch-mode","cors");
//      httpPost.setHeader("sec-fetch-dest","empty");
//      httpPost.setHeader("referer","https://www.douyu.com/topic/LPLXJS?rid=288016");
//      httpPost.setHeader("accept-language","zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
//      httpPost.setHeader("cookie","acf_did=1478c572064da28d403b6c5a02021601; dy_did=1478c572064da28d403b6c5a02021601; acf_uid=37568284; acf_username=37568284; acf_nickname=%E5%B0%8F%E6%B7%B3XD; acf_own_room=0; acf_groupid=1; acf_phonestatus=1; acf_auth=0a21fY4EvTrxdmHGFwjJ2eQCSNrFCQl6SB2Cbe3QqvKhbQGZpfbyDUZMoQn70yvPi%2Fw3YdB9F3PmogcEBQv23tBaItRHVr%2BnpCXqrQzJoxYBbuFTLw78; dy_auth=8439V9RCOmY4QcGPPl1GCiJcGD4n%2FUdq8T62ANtKYrN4jio9jZbm9ojVQVImWsbrJ%2FA%2BvQsVY2DiZOa81bO2Olk7lwylr7qhnQDLSfMwfd6hhnhDNtnT; wan_auth37wan=3b365f4e16c5wrS2IaqDuAqn6n%2BHoZS6aQvldVDLH7ptFWnYrjkwoVElq5D8OZM%2FQkKQbUbcHSzxRqYWEa0wZ7FLpfA%2Fw54Uu%2FVJjIRW03Tmnm2ZNA; acf_ct=0; acf_ltkid=94360176; acf_biz=1; acf_stk=c0680c1534899eda; Hm_lvt_4dc4fb0549a56fe03ba53c022b1ff455=1624342827,1624344737,1624345457,1624411893; Hm_lvt_e99aee90ec1b2106afe7ec3b199020a7=1624344737,1624345458,1624411896,1624418218; acf_avatar=//apic.douyucdn.cn/upload/avatar_v3/202009/ac2c81213b094340a92f30930f0de2f2_; acf_ccn=4def2e11eabd6cd0a2f0c798761c5408; PHPSESSID=dvf5m3r204an7h1a5hmvma1qk6");
//      List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//      nvps.add(new BasicNameValuePair("ctn", "4def2e11eabd6cd0a2f0c798761c5408"));
//      nvps.add(new BasicNameValuePair("room_id", "288016"));
//      nvps.add(new BasicNameValuePair("quiz_id", "0"));
//      nvps.add(new BasicNameValuePair("option", "1"));
//      nvps.add(new BasicNameValuePair("amount", "1000"));
//      nvps.add(new BasicNameValuePair("loss_per_cent", "40"));
//      nvps.add(new BasicNameValuePair("money_type", "1"));
//      httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
//
//      try {
//          for (int i=0;i<5;i++) {
//              long start = System.currentTimeMillis();
//              CloseableHttpResponse response = httpClient.execute(httpPost);
//              System.err.println("返回请求码为:"+response.getStatusLine().getStatusCode());
//              long end = System.currentTimeMillis() - start;
//              System.err.println("毫秒数为:"+end);
//          }
//      } catch (Exception e) {
//          e.printStackTrace();
//      } finally {
//          try {
//              httpClient.close();
//          } catch (IOException e) {
//              e.printStackTrace();
//          }
//      }
  }
}
