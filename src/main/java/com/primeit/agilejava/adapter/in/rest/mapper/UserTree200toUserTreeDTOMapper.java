package com.primeit.agilejava.adapter.in.rest.mapper;

import com.primeit.agilejava.adapter.in.rest.dto.UserTreeDTO;
import com.primeit.agilejava.models.GetUsersTree200Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserTree200toUserTreeDTOMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public UserTree200toUserTreeDTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserTreeDTO toUserDto(UserTree200toUserTreeDTOMapper user) {
        return modelMapper.map(user, UserTreeDTO.class);
    }

    public GetUsersTree200Response toUserData(UserTreeDTO userDTO) {
        return modelMapper.map(userDTO, GetUsersTree200Response.class);
    }
}
