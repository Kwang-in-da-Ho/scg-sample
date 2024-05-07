package org.khjin.scgsample.service.routes;

import org.khjin.scgsample.infrastructure.routes.ApiRouteEntity;
import reactor.core.publisher.Flux;

public interface ApiRouteService {
    Flux<ApiRouteEntity> findApiRoutes();
}
