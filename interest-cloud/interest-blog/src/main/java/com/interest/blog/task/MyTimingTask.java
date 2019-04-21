package com.interest.blog.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

@Component
@EnableScheduling
@Slf4j
public class MyTimingTask {

	@Autowired
    private StringRedisTemplate stringRedisTemplate;

	/**
	 * 每日更新
	 */
	@Scheduled(cron="0 0 0 * * ?")
	public void updateViewTime() {
        Set<String> list = stringRedisTemplate.keys("article_*");
        if(!CollectionUtils.isEmpty(list)){
            log.info("delete article sign in redis");
            stringRedisTemplate.delete(list);
        }
	}

}
