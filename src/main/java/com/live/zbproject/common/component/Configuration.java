package com.live.zbproject.common.component;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @author 庄科炜
 * @className Configuration
 * @description
 * @create 2021/7/1 11:39
 **/
@Component
public class Configuration {

    @Bean("executorServer")
    public ExecutorService executorServer(){
        int max = Runtime.getRuntime().availableProcessors() + 1;//cpu线程数+1 //处理非密集形计算时可以放宽一倍
        return new ThreadPoolExecutor(max, max, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    }
}
