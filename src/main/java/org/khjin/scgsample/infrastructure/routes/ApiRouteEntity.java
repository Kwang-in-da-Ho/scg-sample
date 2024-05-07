package org.khjin.scgsample.infrastructure.routes;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "api_routes")
@Data
public class ApiRouteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String path;
    private String uri;
    private String method;

}
