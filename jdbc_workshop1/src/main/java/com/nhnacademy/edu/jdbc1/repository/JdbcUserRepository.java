package com.nhnacademy.edu.jdbc1.repository;

import com.nhnacademy.edu.jdbc1.service.login.UserRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class JdbcUserRepository implements UserRepository {

    private final DataSource dataSource; //connection Pool

    public JdbcUserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean matches(String username, String password) {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement psmt = connection.prepareStatement("select username, password from JdbcUsers")) {
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
               if(rs.getString("username").equals(username) && rs.getString("password").equals(password)){
                   return true;
               }
            }
            return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
