package com.blackfriday.services;

import com.blackfriday.generated.User;

public interface UserService {
    public User userLogin(final String userId, final String userPassword);
}
