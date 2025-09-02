package com.example.demo.controller;

import com.example.demo.model.Review;
import com.example.demo.service.ReviewService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public String reviewBoard(Model model, HttpSession session) {
        List<Review> reviews = reviewService.getAllReviews();
        model.addAttribute("reviews", reviews);
        model.addAttribute("username", session.getAttribute("username"));
        return "reviews"; // templates/reviews.html
    }

    @GetMapping("/reviews/new")
    public String newReview(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login"; // 로그인 필요
        }
        return "newReview";
    }

    @PostMapping("/reviews")
    public String createReview(@RequestParam String content, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }
        reviewService.writeReview(username, content);
        return "redirect:/reviews";
    }

    @GetMapping("/reviews/{id}")
    public String reviewDetail(@PathVariable Long id, Model model, HttpSession session) {
        return reviewService.getReviewById(id)
                .map(review -> {
                    model.addAttribute("review", review);
                    model.addAttribute("username", session.getAttribute("username"));
                    return "reviewDetail"; // 상세 페이지
                })
                .orElse("redirect:/reviews"); // 없는 리뷰일 경우 게시판으로
    }

    // 리뷰 수정 폼
    @GetMapping("/reviews/{id}/edit")
    public String editReviewForm(@PathVariable Long id, Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        return reviewService.getReviewById(id)
                .map(review -> {
                    if (!review.getAuthor().equals(username)) {
                        return "redirect:/reviews"; // 작성자만 수정 가능
                    }
                    model.addAttribute("review", review);
                    return "editReview";
                })
                .orElse("redirect:/reviews");
    }

    // 리뷰 수정 처리
    @PostMapping("/reviews/{id}/edit")
    public String updateReview(@PathVariable Long id, @RequestParam String content, HttpSession session) {
        String username = (String) session.getAttribute("username");
        return reviewService.getReviewById(id)
                .map(review -> {
                    if (review.getAuthor().equals(username)) {
                        reviewService.updateReview(id, content);
                    }
                    return "redirect:/reviews/" + id; // 수정 후 상세 페이지로 이동
                })
                .orElse("redirect:/reviews");
    }

    // 리뷰 삭제
    @PostMapping("/reviews/{id}/delete")
    public String deleteReview(@PathVariable Long id, HttpSession session) {
        String username = (String) session.getAttribute("username");
        return reviewService.getReviewById(id)
                .map(review -> {
                    if (review.getAuthor().equals(username)) {
                        reviewService.deleteReview(id);
                    }
                    return "redirect:/reviews"; // 삭제 후 목록으로 이동
                })
                .orElse("redirect:/reviews");
    }
}
