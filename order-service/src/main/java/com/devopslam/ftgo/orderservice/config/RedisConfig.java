package com.devopslam.ftgo.orderservice.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {


   /* @Bean
    public RedisConnectionFactory jedisConnectionFactory() {
        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
                .master("lam-redis")
                .sentinel("127.0.0.1", 6379);
        return new JedisConnectionFactory(sentinelConfig);
    }*/

  /*  @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<byte[], byte[]> redisTemplate() {
        RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }*/
}
