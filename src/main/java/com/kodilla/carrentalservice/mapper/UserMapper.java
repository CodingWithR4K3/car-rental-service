package com.kodilla.carrentalservice.mapper;

import com.kodilla.carrentalservice.domain.User;
import com.kodilla.carrentalservice.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "rentals", ignore = true)
    User mapToUser(final UserDto userDto);

    UserDto mapToUserDto(final User user);

    List<UserDto> mapToUserDtoList(final List<User> userList);
}
