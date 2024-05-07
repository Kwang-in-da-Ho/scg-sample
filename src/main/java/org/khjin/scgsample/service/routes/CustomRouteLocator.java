package org.khjin.scgsample.service.routes;

import lombok.AllArgsConstructor;
import org.khjin.scgsample.infrastructure.routes.ApiRouteEntity;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.BooleanSpec;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import reactor.core.publisher.Flux;

@AllArgsConstructor
public class CustomRouteLocator implements RouteLocator {

    private final ApiRouteService apiRouteService;
    private final RouteLocatorBuilder routeLocatorBuilder;
    @Override
    public Flux<Route> getRoutes() {
        RouteLocatorBuilder.Builder routesBuilder = routeLocatorBuilder.routes();
        return apiRouteService.findApiRoutes()
                .map(apiRoute -> routesBuilder.route(String.valueOf(apiRoute.getId()),
                        predicateSpec -> setPredicateSpec(apiRoute, predicateSpec)))
                .collectList()
                .flatMapMany(builders -> routesBuilder.build()
                        .getRoutes());
    }

    private Buildable<Route> setPredicateSpec(ApiRouteEntity apiRoute, PredicateSpec predicateSpec) {
        BooleanSpec booleanSpec = predicateSpec.path(apiRoute.getPath());
        if (!apiRoute.getMethod().isEmpty()) {
            booleanSpec.and()
                    .method(apiRoute.getMethod());
        }
        return booleanSpec.uri(apiRoute.getUri());
    }

}
