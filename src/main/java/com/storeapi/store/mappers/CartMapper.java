package com.storeapi.store.mappers;

import com.storeapi.store.dtos.CartDto;
import com.storeapi.store.dtos.CartItemDto;
import com.storeapi.store.entities.Cart;
import com.storeapi.store.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(target = "totalPrice", expression = "java(cart.getTotalCartPrice())")
    CartDto toDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
    CartItemDto toDto(CartItem cartItem);
}
