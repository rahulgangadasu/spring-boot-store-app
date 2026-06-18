package com.storeapi.store.mappers;

import com.storeapi.store.dtos.RegisterUserRequest;
import com.storeapi.store.dtos.UpdateUserRequest;
import com.storeapi.store.dtos.UserDto;
import com.storeapi.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);
    void update(UpdateUserRequest request, @MappingTarget User user);
}
