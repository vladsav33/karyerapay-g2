package ru.karyeragame.paymentsystem.user.controller;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.karyeragame.paymentsystem.user.dto.UserOutDto;
import ru.karyeragame.paymentsystem.user.mapper.UserMapper;
import ru.karyeragame.paymentsystem.user.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/id/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserOutDto getUser(@PathVariable @Positive Long userId) {
        return UserMapper.toUserOutDto(userService.findById(userId));
    }

    @GetMapping("/email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public UserOutDto getUserByEmail(@PathVariable String email) {
        return UserMapper.userFullDtoToUserOutDto(userService.getByEmail(email));
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserOutDto> getUsers() {
        return userService.getAll().stream()
                .map(UserMapper::userFullDtoToUserOutDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/del/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable @Positive Long userId) {
        userService.delete(userId);
    }

}
