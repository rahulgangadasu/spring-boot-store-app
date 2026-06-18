package com.storeapi.store.controllers;

import com.storeapi.store.dtos.UserDto;
import com.storeapi.store.mappers.UserMapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

import com.storeapi.store.repositories.UserRepository;

import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping()
    public Iterable<UserDto> getAllUsers(
            @RequestParam(required = false, defaultValue = "", name= "sort")
            String sort) {

        if (!Set.of("name", "email").contains(sort)) sort = "name";

        return userRepository.findAll(Sort.by(sort))
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        var user =  userRepository.findById(id).orElse(null);
        if(user == null)
            return ResponseEntity.notFound().build();
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.OK);
    }
}
