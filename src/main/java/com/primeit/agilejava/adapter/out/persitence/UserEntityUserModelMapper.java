package com.primeit.agilejava.adapter.out.persitence;

import com.primeit.agilejava.domain.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Property mapping class
 */
@Component
public class UserEntityUserModelMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public UserEntityUserModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserEntity toUserEntity(User user) {
        return modelMapper.map(user, UserEntity.class);
    }

    public User toUser(UserEntity userEntity) {
        return modelMapper.map(userEntity, User.class);
    }
}
