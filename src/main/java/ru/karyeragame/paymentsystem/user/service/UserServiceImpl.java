package ru.karyeragame.paymentsystem.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.karyeragame.paymentsystem.user.dto.UserFullDto;
import ru.karyeragame.paymentsystem.user.model.User;
import ru.karyeragame.paymentsystem.user.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User findById(long id) {
        return userRepository.getById(id);
    }

    @Override
    public UserFullDto getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    public List<UserFullDto> getAll() {
        return userRepository.getAll();
    }

    @Override
    public UserFullDto update(Long id, UserFullDto userFullDto) {
        return userRepository.update(id, userFullDto);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

}
