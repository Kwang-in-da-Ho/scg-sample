package org.khjin.scgsample.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RedisRouteDefinitionRepository;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import reactor.core.publisher.Mono;

import java.net.URI;

@SpringBootTest
public class RedisTest {

    @Autowired
    ReactiveRedisTemplate<String, RouteDefinition> reactiveRedisTemplate;

    @Autowired
    RouteDefinitionRepository redisRouteDefinitionRepository;

    @Test
    void testInsert() throws Exception{
        RouteDefinition testDefinition = new RouteDefinition();
        testDefinition.setId("TEST");
        testDefinition.setOrder(1);
        PredicateDefinition pathPredicate = new PredicateDefinition();
        pathPredicate.setName("Path");
        pathPredicate.addArg("pattern", "/test");
        testDefinition.getPredicates().add(pathPredicate);
        testDefinition.setUri(new URI("http://3.233.6.75/get"));

        redisRouteDefinitionRepository.save(Mono.just(testDefinition)).subscribe();
    }

    @Test
    void getRouteInfoTest() throws Exception {
        redisRouteDefinitionRepository.getRouteDefinitions().subscribe(
                value -> System.out.println("=============>" + value)
        );
    }

}
