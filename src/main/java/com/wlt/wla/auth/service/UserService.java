package com.wlt.wla.auth.service;

import com.wlt.wla.auth.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
