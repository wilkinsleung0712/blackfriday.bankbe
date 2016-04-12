package com.blackfriday.webservices.endpoint;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.blackfriday.generated.UserLoginRequest;
import com.blackfriday.generated.UserLoginResponse;
import com.blackfriday.services.UserService;
import com.blackfriday.services.UserServiceImpl;

@Endpoint
public class UserServicesEndPoint {
    private static final String USER_LOGIN_REQUEST = "http://www.blackfridaybank.org/webservices/UserOperation";

    private UserService userService;
    /**
     * General Logger Instance.
     */
    protected static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
    
    @Autowired
    public UserServicesEndPoint(UserService userService){
        this.userService = userService;
    }
    
    @PayloadRoot(localPart = "UserLoginRequest", namespace = USER_LOGIN_REQUEST)
    @ResponsePayload //token from the return parameter to here, to define the return type to map a xml response.
    public UserLoginResponse getUserDetails(@RequestPayload UserLoginRequest request) {
        // 1.we need to make an instance of a response
        UserLoginResponse response = new UserLoginResponse();
        // 2.use the request data to in the services class and populated to
        // response
        try {

            response.setUserDetails(userService.userLogin(request.getUserId(), request.getPassword()));
        }catch(Exception e) {
            logger.error(e);
        }
        // 3.return a data populated response.
        return response;
    }
    
}
