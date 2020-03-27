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
        myRole.setId(1l);
        myRole.setName("User");
        Set<Role> myRoles = new HashSet<Role>();
        myRoles.add(myRole);
		user.setRoles(myRoles);
        //user.setRoles(roleRepository.findById(1l).orElse(null));
        //Foo foo = roleRepository.findById(1l).orElse(null);
        //roleRepository.find
        //user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
