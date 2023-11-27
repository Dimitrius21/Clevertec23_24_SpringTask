package ru.clevertec.springtask.SpringTask.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.springtask.SpringTask.domain.AppUser;
import ru.clevertec.springtask.SpringTask.domain.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "channels", target = "channels", defaultExpression = "java(new java.util.ArrayList<>())")
    public UserDto toUserDto(AppUser user);
    public AppUser toUser(UserDto dto);

}
