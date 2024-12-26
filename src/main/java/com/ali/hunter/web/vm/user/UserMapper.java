package com.ali.hunter.web.vm.user;

import com.ali.hunter.domain.entity.User;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {




    User toUser(UserRequest userRequest);

    UserResponse toUserResponse(User user);
    List<UserResponse> toUsersResponceList(Page<User> users);


}

