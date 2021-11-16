package com.begenerous.service.impl;

import com.begenerous.exception.DuplicatedEmailException;
import com.begenerous.exception.RowNotFoundException;
import com.begenerous.model.Role;
import com.begenerous.model.User;
import com.begenerous.repository.RoleRepo;
import com.begenerous.repository.UserRepo;
import com.begenerous.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

import static com.begenerous.util.RoleNameUtils.ROLE_USER;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    /**
     * This is the method which tells Spring how to load the users from the db
     */
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(userEmail);
        if (user == null) {
            log.info("Couldn't find user: " + userEmail);
            throw new UsernameNotFoundException("Couldn't find user!");
        } else {
            log.info("User found!");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        /** This is the user provided by spring */
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving {}", user.getFullName());

        if(userRepo.findByEmail(user.getEmail()) != null) { throw new DuplicatedEmailException(); }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newUser = userRepo.save(user);
        newUser.getRoles().add(roleRepo.findByName(ROLE_USER));
        return newUser;
    }

    @Override
    public User getUser(Long userId) {
        return userRepo.findById(userId).get();
    }

    @Override
    public User updateUser(User user) throws RowNotFoundException {
        User userToUpdate = userRepo.findById(user.getUserId()).orElseThrow(() -> new RowNotFoundException("Couldn't find user!"));

        if(!userToUpdate.getPassword().equals(passwordEncoder.encode(user.getPassword())) && !user.getPassword().equals("")) {
            userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if(!userToUpdate.getFullName().equals(user.getFullName()) && !user.getFullName().equals("")) {
            userToUpdate.setFullName(user.getFullName());
        }
        if(!userToUpdate.getEmail().equals(user.getEmail()) && !user.getEmail().equals("")) {
            userToUpdate.setEmail(user.getEmail());
        }
        if(!userToUpdate.getAvatarURL().equals(user.getAvatarURL()) && !user.getAvatarURL().equals("")) {
            userToUpdate.setAvatarURL(user.getAvatarURL());
        }

        return userRepo.save(userToUpdate);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving {}", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public boolean addRoleToUser(Long userId, Long roleId) throws RowNotFoundException {
        User user = userRepo.findById(userId).orElseThrow(() -> new RowNotFoundException("Couldn't find user!"));
        Role role = roleRepo.findById(roleId).orElseThrow(() -> new RowNotFoundException("Couldn't find role!"));

        return user.getRoles().add(role);
    }
}
