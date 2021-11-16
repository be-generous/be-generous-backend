package com.begenerous.service;

import com.begenerous.exception.RowNotFoundException;
import com.begenerous.model.Role;
import com.begenerous.model.User;

public interface UserService {
    User saveUser(User user);

    User getUser(Long userId) throws RowNotFoundException;

    User updateUser(User user) throws RowNotFoundException;

    Role saveRole(Role role);

    boolean addRoleToUser(Long userId, Long roleId) throws RowNotFoundException;
}
