package com.example.usermanagement.service;

import com.example.usermanagement.dto.UserDTO;
import com.example.usermanagement.model.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    User createUser(UserDTO userDTO);

    User updateUser(String email, UserDTO userDTO);

    void deleteUser(String email);

    List<User> searchUsersByBirthDateRange(LocalDate from, LocalDate to);
}
