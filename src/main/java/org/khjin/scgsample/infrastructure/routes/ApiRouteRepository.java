package org.khjin.scgsample.infrastructure.routes;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ApiRouteRepository extends ReactiveCrudRepository<ApiRouteEntity, Long> {
}
