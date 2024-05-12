package ru.karyeragame.paymentsystem.user.service;

import ru.karyeragame.paymentsystem.user.dto.UserFullDto;
import ru.karyeragame.paymentsystem.user.model.User;

import java.util.List;

public interface UserService {

    User findById(long id);

    UserFullDto getByEmail(String email);

    List<UserFullDto> getAll();

    UserFullDto update(Long id, UserFullDto userFullDto);

    void delete(Long id);
}
