package com.ali.hunter.service.impl;

import com.ali.hunter.config.JwtService;
import com.ali.hunter.domain.entity.User;
import com.ali.hunter.exception.EmailAlreadyExisteException;
import com.ali.hunter.exception.InvalidPasswordException;
import com.ali.hunter.exception.ResourceNotFoundException;
import com.ali.hunter.repository.UserRepository;
import com.ali.hunter.service.UserService;
import com.ali.hunter.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Override
    public Page<User> searchUsers(User user, Pageable pageable) {
        if (user.getCin() == null &&
                user.getEmail() == null ) {
            return userRepository.findAll(pageable);
        }

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<User> example = Example.of(user, matcher);

        return userRepository.findAll(example, pageable);


    }


    @Override
    public User addSUser(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExisteException("Email already exists");
        }
        user.setPassword(PasswordUtil.hashPassword(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User updateUser(UUID id, User user) {
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!userToUpdate.getEmail().equals(user.getEmail())) {
            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                throw new EmailAlreadyExisteException("Email already exists");
            }

        }


        userToUpdate.setCin(user.getCin());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(PasswordUtil.hashPassword(user.getPassword()));
        userToUpdate.setRole(user.getRole());
        return userRepository.save(userToUpdate);

    }



    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public User login(User user) {
        User userEntity = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("this email does not exist"));

        if (!PasswordUtil.checkPassword(user.getPassword(), userEntity.getPassword())) {
            throw new InvalidPasswordException("this password does not match ");
        }

        return userEntity;
    }



    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }


//    public AuthResponse loginAuth(AuthRequest authRequest) {
//        try {
//            User user = userRepository.findByEmail(authRequest.getEmail())
//                    .orElseThrow(() -> new BadCredentialsException("Email not found. Please check your email address."));
//
//            try {
//                authenticationManager.authenticate(
//                        new UsernamePasswordAuthenticationToken(
//                                authRequest.getEmail(),
//                                authRequest.getPassword()
//                        )
//                );
//            } catch (BadCredentialsException e) {
//                throw new BadCredentialsException("Incorrect password. Please try again.");
//            }
//
//            var token = jwtService.generateToken(user);
//
//            return AuthResponse.builder()
//                    .token(token)
//                    .build();
//
//        } catch (BadCredentialsException e) {
//            throw e;
//        } catch (Exception e) {
//            throw new RuntimeException("Authentication failed", e);
//        }
//    }


}
