package com.maosencantadas.api.mapper;

import com.maosencantadas.api.dto.UserResponse;
import com.maosencantadas.model.domain.user.RegisterDTO;
import com.maosencantadas.model.domain.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserResponse toDTO(User user) {
        return modelMapper.map(user, com.maosencantadas.api.dto.UserResponse.class);
    }

    public User toEntity(UserResponse userResponse) {
        return modelMapper.map(userResponse, User.class);
    }

    public RegisterDTO toRegisterDTO(User user) {
        return modelMapper.map(user, RegisterDTO.class);
    }

    public User toUser(RegisterDTO registerDTO) {
        return modelMapper.map(registerDTO, User.class);
    }

    public UserResponse toUserResponse(User user) {
        return modelMapper.map(user, UserResponse.class);
    }
}
