package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {

    private final Map<String, User> users = new HashMap<>();

    // 회원 저장
    public void save(User user) {
        users.put(user.getUsername(), user);
    }

    // 사용자 조회
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(users.get(username));
    }

    // 모든 사용자 확인 (테스트용)
    public Collection<User> findAll() {
        return users.values();
    }
}
