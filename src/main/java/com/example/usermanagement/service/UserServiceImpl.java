package com.example.usermanagement.service;

import com.example.usermanagement.dto.UserDTO;
import com.example.usermanagement.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final List<User> users = new ArrayList<>();
    private final int minAge = 18;

    @Override
    public User createUser(UserDTO userDTO) {
        if (LocalDate.now().minusYears(minAge).isBefore(userDTO.getBirthDate())) {
            throw new IllegalArgumentException("User must be at least " + minAge + " years old.");
        }

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setBirthDate(userDTO.getBirthDate());
        user.setAddress(userDTO.getAddress());
        user.setPhoneNumber(userDTO.getPhoneNumber());

        users.add(user);
        return user;
    }

    @Override
    public User updateUser(String email, UserDTO userDTO) {
        User user = users.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setBirthDate(userDTO.getBirthDate());
        user.setAddress(userDTO.getAddress());
        user.setPhoneNumber(userDTO.getPhoneNumber());

        return user;
    }

    @Override
    public void deleteUser(String email) {
        users.removeIf(u -> u.getEmail().equals(email));
    }

    @Override
    public List<User> searchUsersByBirthDateRange(LocalDate from, LocalDate to) {
        if (from.isAfter(to)) {
            throw new IllegalArgumentException("'From' date must be earlier than 'To' date.");
        }

        return users.stream()
                .filter(u -> !u.getBirthDate().isBefore(from) && !u.getBirthDate().isAfter(to))
                .collect(Collectors.toList());
    }
}
