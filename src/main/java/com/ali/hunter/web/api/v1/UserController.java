package com.ali.hunter.web.api.v1;

import com.ali.hunter.domain.entity.User;
import com.ali.hunter.service.UserService;
import com.ali.hunter.web.vm.user.UserMapper;
import com.ali.hunter.web.vm.user.UserRequest;
import com.ali.hunter.web.vm.user.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserResponse>> searchUsers(@Valid UserRequest userSearchRequest ,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size
    ) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("nationality").ascending());
        User user = userMapper.toUser(userSearchRequest);
        Page<User> users = userService.searchUsers(user,pageable);
        List<UserResponse> usersDTO = userMapper.toUsersResponceList(users);

        return ResponseEntity.ok(usersDTO);
    }



    @PostMapping
    public ResponseEntity<UserResponse> addUser(
            @Valid @RequestBody UserRequest userRequest) {
        User userEntity = userMapper.toUser(userRequest);
        User user = userService.addSUser(userEntity);
        return ResponseEntity.ok(userMapper.toUserResponse(user));
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable UUID id,
            @Valid @RequestBody UserRequest userRequest) {

        User userEntity = userMapper.toUser(userRequest);

        User user = userService.updateUser(id, userEntity);
        return ResponseEntity.ok(userMapper.toUserResponse(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponse> deleteSpeciesById(@PathVariable UUID id) {
        User user = new User();
        user.setId(id);
   //     User deletedUser = userService.deleteUser(user);
        return ResponseEntity.ok(userMapper.toUserResponse(user));
    }






}
