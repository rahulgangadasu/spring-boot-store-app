package com.storeapi.store.controllers;

import com.storeapi.store.dtos.AddItemToCartRequest;
import com.storeapi.store.dtos.CartDto;
import com.storeapi.store.dtos.CartItemDto;
import com.storeapi.store.dtos.UpdateCartItemRequest;
import com.storeapi.store.entities.Cart;
import com.storeapi.store.mappers.CartMapper;
import com.storeapi.store.repositories.CartRepository;
import com.storeapi.store.repositories.ProductRepository;
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

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<CartDto> createCart(
            UriComponentsBuilder uriBuilder
    ) {
        Cart cart = new Cart();
        cartRepository.save(cart);

        CartDto cartDto = cartMapper.toDto(cart);

        var uri = uriBuilder.path("/carts/{id}").buildAndExpand(cart.getId()).toUri();

        return ResponseEntity.created(uri).body(cartDto);
    }

    @PostMapping("{id}/items")
    public ResponseEntity<CartItemDto> addItemToCart(
            @PathVariable("id") UUID cartId,
            @Valid @RequestBody AddItemToCartRequest request
    ){
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null) return ResponseEntity.notFound().build();

        var product = productRepository.findById(request.getProductId()).orElse(null);
        if (product == null) return ResponseEntity.badRequest().build();

        var cartItem = cart.addItem(product);

        cartRepository.save(cart);

        CartItemDto cartItemDto = cartMapper.toDto(cartItem);

        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDto> getCart(
            @PathVariable("id") UUID cartId
    ){
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null) return ResponseEntity.notFound().build();

        CartDto cartDto = cartMapper.toDto(cart);

        return ResponseEntity.ok(cartDto);
    }


    @PutMapping("/{cart_id}/items/{product_id}")
    public ResponseEntity<?> updateItem(
            @PathVariable("cart_id") UUID cartId,
            @PathVariable("product_id") Long productId,
            @Valid @RequestBody UpdateCartItemRequest request
    ){
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("error", "Cart not found")
        );

        var cartItem = cart.getItem(productId);
        if (cartItem == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("error", "product not found in cart")
        );

        cartItem.setQuantity(request.getQuantity());
        cartRepository.save(cart);

        return ResponseEntity.ok(cartMapper.toDto(cartItem));
    }

    @DeleteMapping("/{cart_id}/items/{product_id}")
    public ResponseEntity<?> removeItem(
            @PathVariable("cart_id") UUID cartId,
            @PathVariable("product_id") Long ProductId
    ){
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if(cart == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("error", "Cart not found")
            );
        cart.removeItem(ProductId);
        cartRepository.save(cart);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{cart_id}/items")
    public ResponseEntity<?> clearCart(
            @PathVariable("cart_id") UUID cartId){
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if(cart == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("error", "Cart not found")
            );

        cart.clear();
        cartRepository.save(cart);
        return ResponseEntity.noContent().build();
    }
}

