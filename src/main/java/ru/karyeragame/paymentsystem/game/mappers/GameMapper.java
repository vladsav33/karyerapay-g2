package ru.karyeragame.paymentsystem.game.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.karyeragame.paymentsystem.game.dto.GameCreateDto;
import ru.karyeragame.paymentsystem.game.dto.GameOutputDto;
import ru.karyeragame.paymentsystem.game.entities.Game;
import ru.karyeragame.paymentsystem.user.model.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GameMapper {

    @Mapping(target = "admin", source = "admin")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "alive", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    Game gameCreateDtoToGame(GameCreateDto gameCreateDto, User admin);

    @Mapping(target = "adminEmail", source = "admin.email")
    GameOutputDto gameToGameOutputDto(Game game);
}
