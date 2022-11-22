package com.nhnacademy.edu.jdbc1.service.login;

public interface UserRepository {

    boolean matches(String id, String password);
}
