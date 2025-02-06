package com.primeit.agilejava.adapter.in.rest;

import com.primeit.agilejava.adapter.in.rest.dto.CustomUserDTO;
import com.primeit.agilejava.adapter.in.rest.dto.UserTreeDTO;
import com.primeit.agilejava.adapter.in.rest.mapper.UserTree200toUserTreeDTOMapper;
import com.primeit.agilejava.api.UsersApi;
import com.primeit.agilejava.models.GetUsersTree200Response;
import com.primeit.agilejava.models.UserData;
import com.primeit.agilejava.adapter.in.rest.mapper.UserApiUserDtoMapper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.primeit.agilejava.port.in.UserServicePort;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController implements UsersApi {

    @Autowired
    private UserServicePort userService;

    @Autowired
    private UserApiUserDtoMapper userApiUserDtoMapper;

    @Autowired
    private UserTree200toUserTreeDTOMapper userTree200toUserTreeDTOMapper;

    @Override
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserData user) {
        log.info("Creating user: {}", user);
        CustomUserDTO customUserDTO = userApiUserDtoMapper.toUserDto(user);
        userService.createUser(customUserDTO);
        log.info("Created user: {}", customUserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> deleteUser(@PathVariable("username") String username) {
        log.info("Deleting user: {}", username);
        userService.deleteUser(username);
        log.info("Deleted user: {}", username);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> generateRandomUsers(@PathVariable("number") Integer number) {
        log.info("Generating random users: {}", number);
        userService.generateRandomUsers(number);
        log.info("Generated random users: {}", number);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<UserData>> getAllUsers() {
        log.info("Getting all users");
        List<UserData> users = userService.getAllUsers().stream().map(userApiUserDtoMapper::toUserData).collect(Collectors.toList());
        log.info("Got all users: {}", users);
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<UserData> getUserByUsername(@PathVariable("username") String username) {
        log.info("Getting user: {}", username);
        Optional<CustomUserDTO> user = userService.getUserByUsername(username);
        log.info("Got user: {}", user);
        return ResponseEntity.ok().body(userApiUserDtoMapper.toUserData(user.get()));
    }

    @Override
    public ResponseEntity<GetUsersTree200Response> getUsersTree() {
        log.info("Getting users tree");
        UserTreeDTO userTreeDTO = userService.getUserTree();
        GetUsersTree200Response result = userTree200toUserTreeDTOMapper.toUserData(userTreeDTO);
        log.info("Got users tree: {}", result);
        return ResponseEntity.ok().body(result);
    }

    @Override
    public ResponseEntity<Void> updateUser(@PathVariable("username") String username, @Valid @RequestBody UserData user) {
        log.info("Updating user: {}", username);
        CustomUserDTO customUserDTO = userApiUserDtoMapper.toUserDto(user);
        userService.updateUser(username, customUserDTO);
        log.info("Updated user: {}", customUserDTO);
        return ResponseEntity.ok().build();
    }

}
