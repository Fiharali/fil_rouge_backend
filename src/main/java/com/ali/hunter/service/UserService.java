package com.ali.hunter.service;

import com.ali.hunter.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    Page<User> searchUsers(User user, Pageable pageable);

    User addSUser(User user);

    User updateUser(UUID id, User user);

    Optional<User> findById(UUID id);

    User login(User user);

    User findByEmail(String email);
}
