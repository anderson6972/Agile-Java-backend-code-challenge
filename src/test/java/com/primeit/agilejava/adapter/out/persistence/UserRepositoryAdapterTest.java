package com.primeit.agilejava.adapter.out.persistence;

import com.primeit.agilejava.adapter.out.persitence.JpaUserRepository;
import com.primeit.agilejava.adapter.out.persitence.UserEntity;
import com.primeit.agilejava.adapter.out.persitence.UserEntityUserModelMapper;
import com.primeit.agilejava.adapter.out.persitence.UserReposotoryAdapter;
import com.primeit.agilejava.domain.exception.UserNotFoundException;
import com.primeit.agilejava.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserReposotoryAdapterTest {

    @Mock
    private JpaUserRepository jpaUserRepository;

    @Mock
    private UserEntityUserModelMapper userEntityUserModelMapper;

    @InjectMocks
    private UserReposotoryAdapter userRepositoryAdapter;

    private User mockUser;
    private UserEntity mockUserEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockUser = new User("user1", "John Doe", "john@example.com", null, "photo1", "Spain", "Madrid", "Madrid");
        mockUserEntity = new UserEntity(1L,"user1", "John Doe", "john@example.com", null, "photo1", "Spain", "Madrid", "Madrid");
        // Inyectar manualmente el mock en la clase de prueba
        userRepositoryAdapter = new UserReposotoryAdapter(jpaUserRepository,userEntityUserModelMapper);
        ReflectionTestUtils.setField(userRepositoryAdapter, "userEntityUserModelMapper", userEntityUserModelMapper);
    }

    @Test
    void findAll_ShouldReturnUsers() {
        when(jpaUserRepository.findAll()).thenReturn(Arrays.asList(mockUserEntity));
        when(userEntityUserModelMapper.toUser(mockUserEntity)).thenReturn(mockUser);

        List<User> result = userRepositoryAdapter.findAll();

        assertEquals(1, result.size());
        assertEquals(mockUser.getUsername(), result.get(0).getUsername());
        verify(jpaUserRepository, times(1)).findAll();
    }

    @Test
    void findByUsername_ShouldReturnUser() {
        when(jpaUserRepository.findByUsername("user1")).thenReturn(Optional.of(mockUserEntity));
        when(userEntityUserModelMapper.toUser(mockUserEntity)).thenReturn(mockUser);

        Optional<User> result = userRepositoryAdapter.findByUsername("user1");

        assertTrue(result.isPresent());
        assertEquals(mockUser.getUsername(), result.get().getUsername());
        verify(jpaUserRepository, times(1)).findByUsername("user1");
    }

    @Test
    void findByUsername_ShouldReturnEmpty_WhenUserNotFound() {
        when(jpaUserRepository.findByUsername("user1")).thenReturn(Optional.empty());

        Optional<User> result = userRepositoryAdapter.findByUsername("user1");

        assertFalse(result.isPresent());
    }

    @Test
    void save_ShouldSaveUser() {
        when(userEntityUserModelMapper.toUserEntity(mockUser)).thenReturn(mockUserEntity);
        when(jpaUserRepository.save(mockUserEntity)).thenReturn(mockUserEntity);

        userRepositoryAdapter.save(mockUser);

        verify(jpaUserRepository, times(1)).save(mockUserEntity);
    }

    @Test
    void update_ShouldUpdateExistingUser() {
        when(jpaUserRepository.findByUsername("user1")).thenReturn(Optional.of(mockUserEntity));
        when(userEntityUserModelMapper.toUserEntity(mockUser)).thenReturn(mockUserEntity);

        userRepositoryAdapter.update("user1", mockUser);

        verify(jpaUserRepository, times(1)).save(mockUserEntity);
    }

    @Test
    void update_ShouldThrowException_WhenUserNotFound() {
        when(jpaUserRepository.findByUsername("user1")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userRepositoryAdapter.update("user1", mockUser));
    }

    @Test
    void deleteByUsername_ShouldDeleteUser() {
        when(jpaUserRepository.findByUsername("user1")).thenReturn(Optional.of(mockUserEntity));

        userRepositoryAdapter.deleteByUsername("user1");

        verify(jpaUserRepository, times(1)).deleteByUsername("user1");
    }

    @Test
    void deleteByUsername_ShouldThrowException_WhenUserNotFound() {
        when(jpaUserRepository.findByUsername("user1")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userRepositoryAdapter.deleteByUsername("user1"));
    }
}
