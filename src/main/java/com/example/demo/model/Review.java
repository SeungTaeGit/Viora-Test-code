package com.example.demo.model;

public class Review {

    private Long id;
    private String author;   // 작성자 (username)
    private String content;  // 리뷰 내용

    public Review() {}

    public Review(Long id, String author, String content) {
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public Long getId() {
        return id;
    }
    public String getAuthor() {
        return author;
    }
    public String getContent() {
        return content;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
