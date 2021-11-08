package com.begenerous.repository;

import com.begenerous.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByFullName(String userFullName);
}
