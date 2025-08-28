package com.example.demo.repository;

import com.example.demo.model.Review;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ReviewRepository {

    private final Map<Long, Review> reviews = new HashMap<>();
    private Long sequence = 0L;

    // 저장
    public Review save(Review review) {
        review.setId(++sequence);
        reviews.put(review.getId(), review);
        return review;
    }

    // 전체 조회
    public List<Review> findAll() {
        return new ArrayList<>(reviews.values());
    }
}
