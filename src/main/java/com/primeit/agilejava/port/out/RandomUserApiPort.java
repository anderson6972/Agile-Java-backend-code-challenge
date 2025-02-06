package com.primeit.agilejava.port.out;

import com.primeit.agilejava.adapter.in.rest.dto.CustomUserDTO;

import java.util.List;

public interface RandomUserApiPort {
    List<CustomUserDTO> fetchRandomUsers(int number);
}
