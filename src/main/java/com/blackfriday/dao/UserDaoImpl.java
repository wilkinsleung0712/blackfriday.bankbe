package com.blackfriday.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.blackfriday.generated.User;
import com.blackfriday.startup.Startup;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    private final String connectionURL = "jdbc:mysql://localhost:3306/blackfridaybank?user=blackfriday&password=blackfriday&useUnicode=yes&characterEncoding=UTF-8&autoReconnect=true&connectTimeout=300000";
    private static final String DRIVER_PREFIX = "jdbc:apache:commons:dbcp:";
    /**
     * General Logger Instance.
     */
    protected static final Logger logger = Logger.getLogger(UserDaoImpl.class.getName());
    
    
    @Override
    public User getUserDetails(String userId) {
        // TODO Make the database as centralized connection pool.
        logger.info("-> getUserDetails()");
        Connection conn = null;
        User user = new User();
        try {
            
            
            conn = getConnection(connectionURL);
            conn.setAutoCommit(true);
            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery("select * from user where id="+userId);
            
            while(rs.next()){
                user.setUserId(rs.getString("id"));
                user.setUserName(rs.getString("name"));
                user.setUserGender(rs.getString("gender"));
                user.setUserStatus(rs.getString("status"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            logger.error("An exception! Oops!",e);
            e.printStackTrace();
        } finally {
            try{
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return user;
    }

    private Connection getConnection(final String connectURL) throws SQLException {
     // This will load the MySQL driver, each DB has its own driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return DriverManager.getConnection(connectURL);
    }

}
