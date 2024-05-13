package org.khjin.scgsample.config;

import org.khjin.scgsample.routes.service.RoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.ReactiveRedisMessageListenerContainer;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

    @Bean
    public ReactiveRedisMessageListenerContainer reactiveRedisMessageListenerContainer(
            ReactiveRedisConnectionFactory factory
    ){
        ReactiveRedisMessageListenerContainer container = new ReactiveRedisMessageListenerContainer(factory);

        container.receive(ChannelTopic.of("khjin_test"))
                .doOnNext(message -> {
                    System.out.println("===========>"+message);

                })
                .subscribe();

        return container;
    }
}
