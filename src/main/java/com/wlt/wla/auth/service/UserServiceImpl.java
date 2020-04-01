package com.wlt.wla.auth.service;

import com.wlt.wla.auth.model.Role;
import com.wlt.wla.auth.model.User;
import com.wlt.wla.auth.repository.RoleRepository;
import com.wlt.wla.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        //user.setRoles(new HashSet<>(roleRepository.findById(1l).orElse()));
        Role myRole = new Role();
        //This will by default new user give role 1
        myRole.setId(2l);
        myRole.setName("ROLE_USER");
        Set<Role> myRoles = new HashSet<Role>();
        myRoles.add(myRole);
		user.setRoles(myRoles);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
