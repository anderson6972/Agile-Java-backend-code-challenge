package com.primeit.agilejava.adapter.out.persitence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * JPA Repository interface for managing {@link UserEntity} persistence.
 */
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    /**
     * Finds a user entity by their username.
     *
     * @param username the username to search for.
     * @return an {@link Optional} containing the user entity if found,
     *         or empty if not found.
     */
    Optional<UserEntity> findByUsername(String username);
    /**
     * Deletes a user entity by their username.
     *
     * @param username the username of the user entity to delete.
     */
    void deleteByUsername(String username);
}
