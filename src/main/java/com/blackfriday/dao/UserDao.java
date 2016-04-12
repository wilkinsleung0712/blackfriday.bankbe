package com.blackfriday.dao;

import com.blackfriday.generated.User;

public interface UserDao {
    public User getUserDetails(final String userId);
}
