package com.example.usermanagement.test;

import com.example.usermanagement.dto.UserDTO;
import com.example.usermanagement.model.User;
import com.example.usermanagement.service.UserService;
import com.example.usermanagement.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceImplTest {

    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl();
    }

    @Test
    public void testCreateUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("test@example.com");
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        userDTO.setBirthDate(LocalDate.of(2000, 1, 1));

        User user = userService.createUser(userDTO);
        assertNotNull(user);
        assertEquals("test@example.com", user.getEmail());
    }

    @Test
    public void testCreateUserInvalidAge() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("test@example.com");
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        userDTO.setBirthDate(LocalDate.now().minusYears(17));

        assertThrows(IllegalArgumentException.class, () -> userService.createUser(userDTO));
    }

    @Test
    public void testSearchUsersByBirthDateRange() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("test@example.com");
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        userDTO.setBirthDate(LocalDate.of(2000, 1, 1));

        userService.createUser(userDTO);

        List<User> users = userService.searchUsersByBirthDateRange(LocalDate.of(1999, 1, 1), LocalDate.of(2001, 1, 1));
        assertEquals(1, users.size());
    }
}
