package com.kodilla.carrentalservice.mapper;

import com.kodilla.carrentalservice.domain.User;
import com.kodilla.carrentalservice.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User mapToUser(final UserDto userDto);

    UserDto mapToUserDto(final User user);

    List<UserDto> mapToUserDtoList(final List<User> userList);
}
