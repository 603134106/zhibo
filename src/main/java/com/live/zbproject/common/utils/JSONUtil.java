package com.live.zbproject.common.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author 庄科炜
 * @className JSONUtils
 * @description
 * @create 2021/6/25 11:38
 **/
@Slf4j
public class JSONUtil {

    public static String convertStreamToString(InputStream is) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        is.close();
        return sb.toString();
    }

    public static <T> List parseList(String jsonString,Class<T> t)throws Exception{
        return (List<T>)new ObjectMapper().readValue(jsonString, List.class);
    }

    public static <T> T parseObject(String jsonString,Class<T> t) throws Exception {
        return new ObjectMapper()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(jsonString, t);
    }

    public static <T> T parseObject2(String jsonString,Class<T> t) throws Exception {
        JSONObject jsonObject = new JSONObject(jsonString);
        String data = jsonObject.getString("data");
        String trim = data.replace("[", "").replace("]", "").trim();
        if(trim.length()<3)return null;
        return parseObject(data,t);
    }
}
