package com.primeit.agilejava.adapter.out.restclient.mapper;

import com.primeit.agilejava.adapter.in.rest.dto.CustomUserDTO;
import com.primeit.agilejava.domain.common.CustomGender;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RandomUserMapper {
    public static CustomUserDTO mapToDTO(Map<String, Object> userData) {
        Map<String, Object> name = (Map<String, Object>) userData.get("name");
        Map<String, Object> location = (Map<String, Object>) userData.get("location");
        Map<String, Object> login = (Map<String, Object>) userData.get("login");
        Map<String, Object> picture = (Map<String, Object>) userData.get("picture");

        CustomUserDTO dto = new CustomUserDTO();
        dto.setUsername((String) login.get("username"));
        dto.setName(name.get("first") + " " + name.get("last"));
        dto.setEmail((String) userData.get("email"));
        dto.setGender(mapGender((String) userData.get("gender")));
        dto.setPhoto((String) picture.get("large"));
        dto.setCountry((String) location.get("country"));
        dto.setState((String) location.get("state"));
        dto.setCity((String) location.get("city"));
        return dto;
    }

    public static List<CustomUserDTO> mapToDTOList(List<Map<String, Object>> usersData) {
        return usersData.stream().map(RandomUserMapper::mapToDTO).collect(Collectors.toList());
    }

    private static CustomGender mapGender(String gender) {
        switch (gender.toLowerCase()) {
            case "male":
                return CustomGender.MALE;
            case "female":
                return CustomGender.FEMALE;
            default:
                return CustomGender.OTHER;
        }
    }
}