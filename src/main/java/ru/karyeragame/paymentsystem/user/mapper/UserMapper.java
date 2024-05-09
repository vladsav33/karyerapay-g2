package ru.karyeragame.paymentsystem.user.mapper;

import lombok.experimental.UtilityClass;
import ru.karyeragame.paymentsystem.user.model.User;
import ru.karyeragame.paymentsystem.user.dto.UserFullDto;

@UtilityClass
public class UserMapper {
    public UserFullDto toUserFullDto(User user) {
        return UserFullDto.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }
}
