package ru.karyeragame.paymentsystem.game.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;
import ru.karyeragame.paymentsystem.game.dto.GameCreateDto;
import ru.karyeragame.paymentsystem.game.dto.GameOutputDto;
import ru.karyeragame.paymentsystem.game.entities.Game;
import ru.karyeragame.paymentsystem.user.model.User;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GameMapper {

    @Mapping(target = "admin", source = "admin")
    Game gameCreateDtoToGame(GameCreateDto gameCreateDto, User admin);

    @Mapping(target = "adminEmail", source = "admin.email")
    GameOutputDto gameToGameOutputDto(Game game);
}
