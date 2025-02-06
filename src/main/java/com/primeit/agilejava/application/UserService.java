package com.primeit.agilejava.application;

import com.primeit.agilejava.adapter.in.rest.dto.CustomUserDTO;
import com.primeit.agilejava.adapter.in.rest.dto.UserTreeDTO;
import com.primeit.agilejava.adapter.in.rest.mapper.UserApiUserDtoMapper;
import com.primeit.agilejava.adapter.out.persitence.UserEntityUserModelMapper;
import com.primeit.agilejava.application.mapper.UserModelUserDtoMapper;
import com.primeit.agilejava.domain.exception.DuplicateUserException;
import com.primeit.agilejava.domain.exception.UserNotFoundException;
import com.primeit.agilejava.domain.model.User;
import com.primeit.agilejava.models.UserData;
import com.primeit.agilejava.port.in.UserServicePort;
import com.primeit.agilejava.port.out.RandomUserApiPort;
import com.primeit.agilejava.port.out.UserRepositoryPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService implements UserServicePort {
    @Autowired
    private UserApiUserDtoMapper userApiUserDtoMapper;

    @Autowired
    private UserEntityUserModelMapper userEntityUserModelMapper;

    @Autowired
    private UserModelUserDtoMapper userModelUserDtoMapper;

    private final UserRepositoryPort userRepository;
    private final RandomUserApiPort randomUserApi;

    public UserService(UserRepositoryPort userRepository, RandomUserApiPort randomUserApi) {
        this.userRepository = userRepository;
        this.randomUserApi = randomUserApi;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public List<CustomUserDTO> getAllUsers() {
        log.info("Get all users");
        return userRepository.findAll().stream()
                .map(it -> userModelUserDtoMapper.toCustomUser(it)).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<CustomUserDTO> getUserByUsername(String username) {
        log.info("Find user by username {}", username);
        Optional<User> byUsername = userRepository.findByUsername(username);
        if(byUsername.isEmpty()) {
            throw new UserNotFoundException("El usuario con username '" + username + "' no existe.");
        }
        return byUsername.map(user -> userModelUserDtoMapper.toCustomUser(user));
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void createUser(CustomUserDTO userDTO) {
        log.info("Starting Create user");
        User user = userModelUserDtoMapper.toUser(userDTO);
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateUserException("El usuario con username '" + user.getUsername() + "' ya existe.");
        }

    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void updateUser(String username, CustomUserDTO userDTO) {
        log.info("Starting Update user");
        User user = userModelUserDtoMapper.toUser(userDTO);
        userRepository.update(username,user);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void deleteUser(String username) {
        log.info("Starting Delete user");
        userRepository.deleteByUsername(username);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void generateRandomUsers(int number) {
        log.info("Starting Generate random users");
        List<CustomUserDTO> customUserDTOS = randomUserApi.fetchRandomUsers(number);
        customUserDTOS.stream().map(userModelUserDtoMapper::toUser).forEach(userRepository::save);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserTreeDTO getUserTree() {
        log.info("Get user tree");
        List<UserData> users = userRepository.findAll().stream()
                .map(userModelUserDtoMapper::toCustomUser)
                .map(userApiUserDtoMapper::toUserData)
                .collect(Collectors.toList());

        Map<String, Map<String, Map<String, List<UserData>>>> tree = users.stream()
                .collect(Collectors.groupingBy(
                        UserData::getCountry, // Agrupación por país
                        Collectors.groupingBy(
                                UserData::getState, // Agrupación por estado
                                Collectors.groupingBy(
                                        UserData::getCity, // Agrupación por ciudad
                                        Collectors.toList()
                                )
                        )
                ));

        return new UserTreeDTO(tree);
    }
}
