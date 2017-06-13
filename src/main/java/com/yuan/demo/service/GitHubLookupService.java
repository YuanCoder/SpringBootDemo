package com.yuan.demo.service;


import java.util.concurrent.Future;

import com.yuan.demo.entity.UserPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
/**
 * Created by fangzhipeng on 2017/4/19.
 * ① 通过@Async注解表明该方法是个异步方法，如果注解在类级别，则表明该类所有的方法都是异步方法，
 *   而这里的方法自动被注入使用ThreadPoolTaskExecutor作为TaskExecutor。
 * 创建一个请求的　githib的service
 */
@Service
public class GitHubLookupService {

    private static final Logger logger = LoggerFactory.getLogger(GitHubLookupService.class);

    private final RestTemplate restTemplate;

    public GitHubLookupService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

   @Async  //@Async 表明是一个异步任务
    public Future<UserPojo> findUser(String user) throws InterruptedException {
        logger.info("Looking up " + user);
        String url = String.format("https://api.github.com/users/%s", user);
       UserPojo results = restTemplate.getForObject(url, UserPojo.class);
        // Artificial delay of 1s for demonstration purposes
        Thread.sleep(1000L);
        return new AsyncResult<>(results);
    }

}