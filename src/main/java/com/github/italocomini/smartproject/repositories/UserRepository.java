package com.github.italocomini.smartproject.repositories;

import com.github.italocomini.smartproject.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}