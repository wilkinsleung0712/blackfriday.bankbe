package com.blackfriday.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blackfriday.dao.UserDao;
import com.blackfriday.dao.UserDaoImpl;
import com.blackfriday.generated.User;

@Service("userService")
public class UserServiceImpl implements UserService {
    /**
     * General Logger Instance.
     */
    protected static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
    
    @Autowired
    private UserDao userDao;
    
    @Override
    public User userLogin(String userId, String userPassword) {
        // TODO: Varify the user password in the returned user object.
        logger.info("-> userLogin()");
        User user = null;
        try {
            user = userDao.getUserDetails(userId);
        } catch(Exception e){
            logger.error("unable to retrieve user details from db",e);
        }
        return user;
    }

}
