package ru.karyeragame.paymentsystem.user.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.karyeragame.paymentsystem.exceptions.ExceptionNotFound;
import ru.karyeragame.paymentsystem.user.dao.UserDao;
import ru.karyeragame.paymentsystem.user.model.User;

@AllArgsConstructor
@Service
public class CustomUserService implements UserDetailsService {
    private final UserDao userDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findUserByEmail(username);
        if (user == null) {
            throw new ExceptionNotFound("Пользователя нет в базе" + username);
        }
        return user;
    }

}
