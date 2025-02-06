package com.primeit.agilejava.port.out;

import com.primeit.agilejava.domain.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Interface that defines the user data access port.
 */
public interface UserRepositoryPort {

    /**
     * Retrieves all stored users.
     *
     * @return a list of all available users.
     */
    List<User> findAll();

    /**
     * Searches for a user by their username.
     *
     * @param username the username to search for.
     * @return an {@link Optional} containing the user if found,
     *         or empty if not found.
     */
    Optional<User> findByUsername(String username);

    /**
     * Saves a new user in the repository.
     *
     * @param user the user to save.
     */
    void save(User user);

    /**
     * Updates the data of an existing user.
     *
     * @param username the username of the user to update.
     * @param user the new user data.
     */
    void update(String username, User user);

    /**
     * Deletes a user from the repository by their username.
     *
     * @param username the username of the user to delete.
     */
    void deleteByUsername(String username);
}

