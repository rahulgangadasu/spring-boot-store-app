package com.storeapi.store.mappers;

import com.storeapi.store.dtos.CartDto;
import com.storeapi.store.entities.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartDto toDto(Cart cart);
}
