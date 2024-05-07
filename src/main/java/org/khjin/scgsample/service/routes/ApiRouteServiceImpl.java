package org.khjin.scgsample.service.routes;

import lombok.AllArgsConstructor;
import org.khjin.scgsample.infrastructure.routes.ApiRouteEntity;
import org.khjin.scgsample.infrastructure.routes.ApiRouteRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class ApiRouteServiceImpl implements ApiRouteService{

    private ApiRouteRepository apiRouteRepository;

    @Override
    public Flux<ApiRouteEntity> findApiRoutes() {
        return apiRouteRepository.findAll();
    }
}
