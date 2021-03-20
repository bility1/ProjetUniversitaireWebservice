package fr.artapp.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route( r-> r.path("/api/art/**")
                        .filters(f->f.rewritePath("/api/art/(?<remains>.*)","/${remains}")
                                .preserveHostHeader()
                        )
                        .uri("lb://art-service")
                        .id("art-service")
                )
                .route(r -> r.path("/api/review/**")
                        .filters(f -> f.rewritePath("/api/review/(?<remains>.*)", "/${remains}")                               )
                        .uri("lb://review-service")
                        .id("review-service"))
                .route( r-> r.path("/api/auth/**")
                .filters(f->f.rewritePath("/api/auth/(?<remains>.*)","/${remains}")
                        .preserveHostHeader()
                )
                .uri("lb://keycloak-service")
                .id("keycloak-service")

        )
                .build();


    }
}
