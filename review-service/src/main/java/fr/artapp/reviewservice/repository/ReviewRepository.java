package fr.artapp.reviewservice.repository;

import fr.artapp.reviewservice.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends MongoRepository<Review, Long> { // Long: Type of Employee ID.

   // Review findByReviewId(String idReview);

   List<Review> findAll();

    Optional<Review> findByIdAvis(String id);

    void deleteByIdAvis(String id);

    boolean existsByIdAvis(String id);
}
