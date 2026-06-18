package com.storeapi.store.mappers;

import com.storeapi.store.dtos.UserDto;
import com.storeapi.store.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
}
