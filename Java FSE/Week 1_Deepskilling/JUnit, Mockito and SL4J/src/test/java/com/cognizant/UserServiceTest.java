package com.cognizant;

import org.junit.jupiter.api.*;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit Exercise 4: Arrange-Act-Assert (AAA) Pattern
 * Demonstrates @BeforeEach, @AfterEach, and AAA structure.
 */
class UserServiceTest {

    private UserService userService;

    // Setup: runs before EACH test
    @BeforeEach
    void setUp() {
        userService = new UserService();
        System.out.println("[SETUP] Fresh UserService instance created.");
    }

    // Teardown: runs after EACH test
    @AfterEach
    void tearDown() {
        userService = null;
        System.out.println("[TEARDOWN] UserService instance released.\n");
    }

    @Test
    @DisplayName("Should add a user and verify count increases")
    void testAddUser() {
        // ARRANGE
        User user = new User(1, "Aishwarya", "aish@example.com");

        // ACT
        userService.addUser(user);

        // ASSERT
        assertEquals(1, userService.getUserCount(), "User count should be 1 after adding one user.");
    }

    @Test
    @DisplayName("Should find user by ID after adding")
    void testFindUserById() {
        // ARRANGE
        User expectedUser = new User(2, "Ravi", "ravi@example.com");
        userService.addUser(expectedUser);

        // ACT
        Optional<User> result = userService.findById(2);

        // ASSERT
        assertTrue(result.isPresent(), "User should be found by ID.");
        assertEquals("Ravi", result.get().getName(), "Found user name should match.");
    }

    @Test
    @DisplayName("Should return empty Optional when user not found")
    void testFindUserByIdNotFound() {
        // ARRANGE — no users added

        // ACT
        Optional<User> result = userService.findById(99);

        // ASSERT
        assertFalse(result.isPresent(), "No user should be found for ID 99.");
    }

    @Test
    @DisplayName("Should delete user and reduce count")
    void testDeleteUser() {
        // ARRANGE
        userService.addUser(new User(3, "Meera", "meera@example.com"));
        userService.addUser(new User(4, "Kiran", "kiran@example.com"));

        // ACT
        boolean deleted = userService.deleteUser(3);

        // ASSERT
        assertTrue(deleted, "Delete should return true when user exists.");
        assertEquals(1, userService.getUserCount(), "Count should reduce to 1.");
    }

    @Test
    @DisplayName("Should throw exception when null user is added")
    void testAddNullUserThrowsException() {
        // ARRANGE — null user

        // ACT + ASSERT
        assertThrows(IllegalArgumentException.class,
                () -> userService.addUser(null),
                "Adding null user should throw IllegalArgumentException.");
    }
}
