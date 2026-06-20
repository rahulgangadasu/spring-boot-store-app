package com.storeapi.store.repositories;

import com.storeapi.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


    boolean existsByEmail(String email);
}
