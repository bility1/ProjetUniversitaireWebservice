package fr.artapp.reviewservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;


@SpringBootApplication
public class ReviewServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReviewServiceApplication.class, args);
    }

}
