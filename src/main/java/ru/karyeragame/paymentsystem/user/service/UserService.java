package ru.karyeragame.paymentsystem.user.service;

import ru.karyeragame.paymentsystem.user.dto.UserDtoRegistration;

public interface UserService {
    String createUser(UserDtoRegistration userDtoRegistration);

    String confirmationRegistration(String key, String email);
}
