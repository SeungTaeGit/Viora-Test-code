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

    // 개별 조회
    public Optional<Review> findById(Long id) {
        return Optional.ofNullable(reviews.get(id));
    }

    // 수정
    public void update(Long id, String content) {
        Review review = reviews.get(id);
        if (review != null) {
            review.setContent(content);
        }
    }

    // 삭제
    public void delete(Long id) {
        reviews.remove(id);
    }
}
