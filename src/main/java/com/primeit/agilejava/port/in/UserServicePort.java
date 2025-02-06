package com.primeit.agilejava.port.in;

import com.primeit.agilejava.adapter.in.rest.dto.CustomUserDTO;
import com.primeit.agilejava.adapter.in.rest.dto.UserTreeDTO;

import java.util.*;

/**
 * Service port interface for user-related operations.
 */
public interface UserServicePort {
    /**
     * Retrieves all users.
     *
     * @return a list of {@link CustomUserDTO} containing all users.
     */
    List<CustomUserDTO> getAllUsers();

    /**
     * Retrieves a user by their username.
     *
     * @param username the username to search for.
     * @return an {@link Optional} containing the user if found,
     *         or empty if not found.
     */
    Optional<CustomUserDTO> getUserByUsername(String username);

    /**
     * Creates a new user.
     *
     * @param userDTO the user data transfer object containing the user details.
     */
    void createUser(CustomUserDTO userDTO);

    /**
     * Updates an existing user's information.
     *
     * @param username the username of the user to update.
     * @param userDTO the new user data.
     */
    void updateUser(String username, CustomUserDTO userDTO);

    /**
     * Deletes a user by their username.
     *
     * @param username the username of the user to delete.
     */
    void deleteUser(String username);

    /**
     * Generates a specified number of random users.
     *
     * @param number the number of random users to generate.
     */
    void generateRandomUsers(int number);

    /**
     * Retrieves the entire user tree structure.
     *
     * @return a {@link UserTreeDTO} representing the complete user hierarchy.
     */
    UserTreeDTO getUserTree();
}
