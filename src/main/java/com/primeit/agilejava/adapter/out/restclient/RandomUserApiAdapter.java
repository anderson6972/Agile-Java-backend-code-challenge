package com.primeit.agilejava.adapter.out.restclient;

import com.primeit.agilejava.adapter.in.rest.dto.CustomUserDTO;
import com.primeit.agilejava.port.out.RandomUserApiPort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

import com.primeit.agilejava.adapter.out.restclient.mapper.RandomUserMapper;

@Component
public class RandomUserApiAdapter implements RandomUserApiPort {

    private static final String API_URL = "https://randomuser.me/api/?results=";

    private final RestTemplate restTemplate;

    public RandomUserApiAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<CustomUserDTO> fetchRandomUsers(int number) {
        RandomUserResponse response = restTemplate.getForObject(API_URL + number, RandomUserResponse.class);
        return response.getResults().stream()
                .map(RandomUserMapper::mapToDTO)
                .collect(Collectors.toList());
    }
}
