package com.live.zbproject.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 庄科炜
 * @className Response
 * @description 响应类
 * @create 2021/6/25 11:01
 **/
@Data
@Builder
public class Response implements Serializable {
    private static final long serialVersionUID = 4201917038941409234L;
    private int code;
    private Object data;
    private String msg;
    private boolean success;
    private Integer error;

    public static Response success(Object data,String msg){
        return Response.builder()
                .code(200)
                .success(true)
                .msg(msg)
                .data(data).error(0)
                .build();
    }

    public static Response success(Object data,String msg,int code){
        return Response.builder()
                .code(code)
                .success(true)
                .msg(msg)
                .data(data).error(0)
                .build();
    }

    public static Response failure(Object data,String msg){
        return Response.builder()
                .code(400)
                .success(false)
                .msg(msg)
                .data(data).error(1)
                .build();
    }

    public static Response failure(Object data,String msg,int code){
        return Response.builder()
                .code(code)
                .success(false)
                .msg(msg)
                .data(data).error(1)
                .build();
    }

}
