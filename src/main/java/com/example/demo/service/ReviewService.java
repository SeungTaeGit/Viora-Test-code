package com.example.demo.service;

import com.example.demo.model.Review;
import com.example.demo.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    // 리뷰 작성
    public void writeReview(String author, String content) {
        Review review = new Review(null, author, content);
        reviewRepository.save(review);
    }

    // 리뷰 전체 가져오기
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // 리뷰 개별 가져오기
    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    public void updateReview(Long id, String content) {
        reviewRepository.update(id, content);
    }

    public void deleteReview(Long id) {
        reviewRepository.delete(id);
    }
}
