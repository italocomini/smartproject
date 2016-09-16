package io.italocomini.smartproject.repositories;

import io.italocomini.smartproject.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}