package com.storeapi.store.mappers;

import com.storeapi.store.dtos.ProductDto;
import com.storeapi.store.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "categoryId", source = "category.id")
    ProductDto toDto(Product product);
}
