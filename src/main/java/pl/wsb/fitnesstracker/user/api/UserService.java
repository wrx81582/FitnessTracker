package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction,
 * whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param user The user to be created
     * @return The created user
     */
    User createUser(User user);

    /**
     * Updates an existing user.
     *
     * @param id          ID of the user to update
     * @param updatedUser User data to apply
     * @return The updated user
     */
    User updateUser(Long id, User updatedUser);

    /**
     * Deletes a user by ID.
     *
     * @param id ID of the user to delete
     */
    void deleteUser(Long id);

    /**
     * Finds users whose email contains the given fragment (case-insensitive).
     *
     * @param emailFragment fragment to search for
     * @return list of matching users
     */
    List<User> findUsersByEmailFragment(String emailFragment);

    /**
     * Finds users born before the given date (older than).
     *
     * @param date reference date
     * @return list of users older than the given date
     */
    List<User> findUsersOlderThan(LocalDate date);
}