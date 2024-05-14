package ru.karyeragame.paymentsystem.user.mapper;

import lombok.experimental.UtilityClass;
import ru.karyeragame.paymentsystem.user.dto.UserFullDto;
import ru.karyeragame.paymentsystem.user.dto.UserOutDto;
import ru.karyeragame.paymentsystem.user.model.User;

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

    public UserOutDto toUserOutDto(User user) {
        return UserOutDto.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public UserOutDto userFullDtoToUserOutDto(UserFullDto userFullDto) {
        return UserOutDto.builder()
                .id(userFullDto.getId())
                .nickname(userFullDto.getNickname())
                .email(userFullDto.getEmail())
                .role(userFullDto.getRole())
                .build();
    }

}
