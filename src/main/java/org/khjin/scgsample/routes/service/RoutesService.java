package org.khjin.scgsample.routes.service;

import org.springframework.cloud.gateway.route.RouteDefinition;
import reactor.core.publisher.Flux;

public interface RoutesService {

    public void getRouteDefinitions();
}
