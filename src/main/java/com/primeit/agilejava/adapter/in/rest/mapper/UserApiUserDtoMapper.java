package com.primeit.agilejava.adapter.in.rest.mapper;

import com.primeit.agilejava.adapter.in.rest.dto.CustomUserDTO;
import com.primeit.agilejava.domain.common.CustomGender;
import com.primeit.agilejava.models.UserData;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserApiUserDtoMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public UserApiUserDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        configureMappings();
    }

    private void configureMappings() {
        // Conversión de GenderEntity (BD) a Gender (Lógica de negocio)
        Converter<CustomGender, UserData.GenderEnum> toDomainConverter = context -> {
            return context.getSource() != null ? UserData.GenderEnum.valueOf(context.getSource().name()) : null;
        };

        // Conversión de Gender (Lógica de negocio) a GenderEntity (BD)
        Converter<UserData.GenderEnum, CustomGender> toEntityConverter = context -> {
            return context.getSource() != null ? CustomGender.valueOf(context.getSource().name()) : null;
        };

        // Configurar el ModelMapper
        modelMapper.typeMap(CustomUserDTO.class, UserData.class)
                .addMappings(mapper -> mapper.using(toDomainConverter).map(CustomUserDTO::getGender, UserData::setGender));

        modelMapper.typeMap(UserData.class, CustomUserDTO.class)
                .addMappings(mapper -> mapper.using(toEntityConverter).map(UserData::getGender, CustomUserDTO::setGender));
    }

    public CustomUserDTO toUserDto(UserData user) {
        return modelMapper.map(user, CustomUserDTO.class);
    }

    public UserData toUserData(CustomUserDTO userDTO) {
        return modelMapper.map(userDTO, UserData.class);
    }
}
