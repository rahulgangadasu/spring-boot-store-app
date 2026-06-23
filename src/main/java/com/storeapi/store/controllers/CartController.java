package com.storeapi.store.controllers;

import com.storeapi.store.dtos.AddItemToCartRequest;
import com.storeapi.store.dtos.CartDto;
import com.storeapi.store.dtos.CartItemDto;
import com.storeapi.store.dtos.UpdateCartItemRequest;
import com.storeapi.store.exceptions.CartNotFoundException;
import com.storeapi.store.exceptions.ProductNotFoundException;
import com.storeapi.store.services.CartService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<CartDto> createCart(
            UriComponentsBuilder uriBuilder
    ) {
        var cartDto = cartService.createCart();
        var uri = uriBuilder.path("/carts/{id}").buildAndExpand(cartDto.getId()).toUri();
        return ResponseEntity.created(uri).body(cartDto);
    }

    @PostMapping("{id}/items")
    public ResponseEntity<CartItemDto> addItemToCart(
            @PathVariable("id") UUID cartId,
            @Valid @RequestBody AddItemToCartRequest request
    ){
        var cartItemDto = cartService.addToCart(cartId, request.getProductId());

        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemDto);
    }

    @GetMapping("/{cartId}")
    public CartDto getCart(@PathVariable UUID cartId) {
        return cartService.getCart(cartId);
    }

    @PutMapping("/{cart_id}/items/{product_id}")
    public CartItemDto updateItem(
            @PathVariable("cart_id") UUID cartId,
            @PathVariable("product_id") Long productId,
            @Valid @RequestBody UpdateCartItemRequest request
    ) {
        return cartService.updateItem(cartId, productId, request.getQuantity());
    }

    @DeleteMapping("/{cart_id}/items/{product_id}")
    public ResponseEntity<?> removeItem(
            @PathVariable("cart_id") UUID cartId,
            @PathVariable("product_id") Long productId
    ) {
        cartService.removeItem(cartId, productId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cartId}/items")
    public ResponseEntity<Void> clearCart(@PathVariable UUID cartId) {
        cartService.clearCart(cartId);

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCartNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            Map.of("error", "Cart not found")
        );
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCartItemNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            Map.of("error", "Product not found in cart")
        );
    }
}

