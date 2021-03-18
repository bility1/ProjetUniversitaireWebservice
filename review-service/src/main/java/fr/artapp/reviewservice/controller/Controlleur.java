package fr.artapp.reviewservice.controller;

import fr.artapp.reviewservice.model.Review;
import fr.artapp.reviewservice.repository.ReviewRepository;
import fr.artapp.reviewservice.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.ReplayProcessor;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class Controlleur {
    @Autowired
    ReviewService reviewService;

    private static List<Review> reviews = new ArrayList<>();

    // STREAM de notifications
    private ReplayProcessor<Review> notifications = ReplayProcessor.create(0, false);

    @GetMapping(value = "/avis/subscribe", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Review> notification() {
        return Flux.from(notifications);
    }

    @PostMapping(value = "/avis")
    public ResponseEntity<Review> create(@RequestBody Review reviewBody, UriComponentsBuilder base) {
        int id = reviews.size();
        reviewBody.setIdAvis(String.valueOf(id));
        reviews.add(reviewBody);
        reviewService.setReview(reviewBody);
        URI location = base.path("/api/avis/{id}").buildAndExpand(id).toUri();
        // notification d'un nouveau avis dans le Stream
        notifications.onNext(reviewBody);

        return ResponseEntity.created(location).body(reviewBody);
    }

    @GetMapping(value = "/avis")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Review> getAllAvis() {

        return reviewService.getAllReview();

    }

}
