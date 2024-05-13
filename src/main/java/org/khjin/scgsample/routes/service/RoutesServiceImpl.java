package org.khjin.scgsample.routes.service;

import lombok.RequiredArgsConstructor;
import org.khjin.scgsample.event.RoutesRefresher;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class RoutesServiceImpl implements RoutesService{

    private RouteDefinitionRepository routeDefinitionRepository;
    private RoutesRefresher routesRefresher;

    @Override
    public void getRouteDefinitions() {
        routeDefinitionRepository.getRouteDefinitions().subscribe();
        routesRefresher.refreshRoutes();

    }
}
