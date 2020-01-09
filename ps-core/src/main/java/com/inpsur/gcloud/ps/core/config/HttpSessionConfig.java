package com.inpsur.gcloud.ps.core.config;

import com.inpsur.gcloud.ps.core.support.PsGenericFastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

@Configuration
public class HttpSessionConfig {

    /*
     * 配置redis序列化方式
     * */
    //@Bean(name = "springSessionDefaultRedisSerializer")
    public RedisSerializer redisSerializer() {
        PsGenericFastJsonRedisSerializer fastJsonRedisSerializer = new PsGenericFastJsonRedisSerializer();
        return fastJsonRedisSerializer;
    }

    /**
     *
     * We have a header a the name of X-Auth-Token and that contains a new session ID.
     * We can now use the X-Auth-Token to make another request without providing the username and password again.
     * 会生成X-Auth-Token放到header中，
     *
     */
    @Bean
    public HttpSessionIdResolver httpSessionIdResolver() {
        return HeaderHttpSessionIdResolver.xAuthToken(); // <3>
    }


}
