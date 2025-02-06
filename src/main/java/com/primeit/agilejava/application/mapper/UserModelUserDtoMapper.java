package com.primeit.agilejava.application.mapper;

import com.primeit.agilejava.adapter.in.rest.dto.CustomUserDTO;
import com.primeit.agilejava.domain.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Property mapping class
 */
@Component
public class UserModelUserDtoMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public UserModelUserDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CustomUserDTO toCustomUser(User user) {
        return modelMapper.map(user, CustomUserDTO.class);
    }

    public User toUser(CustomUserDTO customUserDTO) {
        return modelMapper.map(customUserDTO, User.class);
    }
}
