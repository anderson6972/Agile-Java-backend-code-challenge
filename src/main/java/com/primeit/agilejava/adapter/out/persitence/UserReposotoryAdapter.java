package com.primeit.agilejava.adapter.out.persitence;

import com.primeit.agilejava.domain.exception.UserNotFoundException;
import com.primeit.agilejava.domain.model.User;
import com.primeit.agilejava.port.out.UserRepositoryPort;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of {@link UserRepositoryPort}.
 */
@Repository
public class UserReposotoryAdapter implements UserRepositoryPort {

    private final JpaUserRepository jpaUserRepository;
    private final UserEntityUserModelMapper userEntityUserModelMapper;

    public UserReposotoryAdapter(JpaUserRepository jpaUserRepository, UserEntityUserModelMapper userEntityUserModelMapper) {
        this.jpaUserRepository = jpaUserRepository;
        this.userEntityUserModelMapper = userEntityUserModelMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> findAll() {
        List<UserEntity> all = jpaUserRepository.findAll();
        System.out.println("In DB all: " + all);
        return all.stream().map(it -> userEntityUserModelMapper.toUser(it)).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<User> findByUsername(String username) {
        return jpaUserRepository.findByUsername(username).map(userEntity -> userEntityUserModelMapper.toUser(userEntity));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(User user) {
        jpaUserRepository.save(userEntityUserModelMapper.toUserEntity(user));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(String username, User user) {
        Optional<UserEntity> byUsername = jpaUserRepository.findByUsername(username);
        if (byUsername.isPresent()) {
            UserEntity userEntityUpdate = userEntityUserModelMapper.toUserEntity(user);
            BeanUtils.copyProperties(userEntityUpdate, byUsername.get(), getNullPropertyNames(userEntityUpdate));
            jpaUserRepository.save(byUsername.get());
        }else {
            throw new UserNotFoundException("The user you are trying to delete does not exist..");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteByUsername(String username) {
        Optional<UserEntity> byUsername = jpaUserRepository.findByUsername(username);
        if (byUsername.isPresent()) {
            jpaUserRepository.deleteByUsername(username);
        }else {
            throw new UserNotFoundException("The user you are trying to delete does not exist..");
        }
    }

    // Auxiliary method to ignore null properties
    private String[] getNullPropertyNames(Object source) {
        return Arrays.stream(BeanUtils.getPropertyDescriptors(source.getClass()))
                .map(PropertyDescriptor::getName)
                .filter(name -> {
                    try {
                        return Objects.isNull(new PropertyDescriptor(name, source.getClass()).getReadMethod().invoke(source));
                    } catch (Exception e) {
                        return false;
                    }
                }).toArray(String[]::new);
    }
}
