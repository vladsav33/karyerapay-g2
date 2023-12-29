package ru.karyeragame.paymentsystem.user.service;

import org.springframework.stereotype.Service;
import ru.karyeragame.paymentsystem.user.User;
import ru.karyeragame.paymentsystem.user.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(long id) {
        return userRepository.getById(id);
    }
}
